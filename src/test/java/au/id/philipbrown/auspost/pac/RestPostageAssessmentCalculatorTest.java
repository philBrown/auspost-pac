package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.interceptor.AuthKeyHttpHeaderInterceptor;
import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.model.Service;
import au.id.philipbrown.auspost.pac.request.DomesticLetterServiceRequest;
import au.id.philipbrown.auspost.pac.request.DomesticParcelServiceRequest;
import au.id.philipbrown.auspost.pac.request.InternationalServiceRequest;
import au.id.philipbrown.auspost.pac.request.RequestParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static au.id.philipbrown.auspost.pac.RestPostageAssessmentCalculator.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RestPostageAssessmentCalculatorTest {

    private static final String API_KEY = "12345678";
    private final TestParams testParams = new TestParams();

    private MockRestServiceServer server;
    private PostageAssessmentCalculator pac;

    @Before
    public void setUp() throws Exception {
        final RestTemplate responseCaptureRestTemplate = new RestTemplate() {
            @Override
            protected void handleResponse(final URI url, final HttpMethod method, final ClientHttpResponse response) throws IOException {
                final String filename = Paths.get(url.getPath()).getFileName().toString();
                final Path out = Paths.get(RestPostageAssessmentCalculatorTest.this.getClass().getResource("/").getPath(), filename);
                Files.copy(response.getBody(), out, StandardCopyOption.REPLACE_EXISTING);
                super.handleResponse(url, method, response);
            }
        };
        final RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.createServer(restTemplate);
        pac = new RestPostageAssessmentCalculator(restTemplate, API_KEY);

        // Switch to the rest template below to capture output from the real service.
        // pac = new RestPostageAssessmentCalculator(responseCaptureRestTemplate, API_KEY);
    }

    @After
    public void tearDown() throws Exception {
        server.verify();
    }

    @Test
    public void getCountries() throws Exception {
        expectRequest(COUNTRY_URI);
        List<Country> countries = pac.getCountries();
        assertThat(countries.size(), is(227));
    }

    @Test
    public void getDomesticLetterServices() throws Exception {
        DomesticLetterServiceRequest params = testParams.getDomesticLetterServiceRequest();
        expectRequest(DOMESTIC_LETTER_SERVICE_URI, params);
        List<Service> services = pac.getDomesticLetterServices(params);
        assertThat(services.size(), is(3));
    }

    @Test
    public void getInternationalLetterServices() throws Exception {
        InternationalServiceRequest params = testParams.getInternationalLetterServiceRequest();
        expectRequest(INTERNATIONAL_LETTER_SERVICE_URI, params);
        List<Service> services = pac.getInternationalLetterServices(params);
        assertThat(services.size(), is(5));
    }

    @Test
    public void getDomesticParcelServices() throws Exception {
        DomesticParcelServiceRequest params = testParams.getDomesticParcelServiceRequest();
        expectRequest(DOMESTIC_PARCEL_SERVICE_URI, params);
        List<Service> services = pac.getDomesticParcelServices(params);
        assertThat(services.size(), is(6));
    }

    @Test
    public void getInternationalParcelServices() throws Exception {
        InternationalServiceRequest params = testParams.getInternationalParcelServiceRequest();
        expectRequest(INTERNATIONAL_PARCEL_SERVICE_URI, params);
        List<Service> services = pac.getInternationalParcelServices(params);
        assertThat(services.size(), is(4));
    }

    private void expectRequest(final String uri, final RequestParams params) throws URISyntaxException {
        expectRequest(buildUri(uri, params));
    }

    private void expectRequest(final String uri) throws URISyntaxException {
        final String path = new URI(uri).getPath();
        server.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(AuthKeyHttpHeaderInterceptor.HEADER, API_KEY))
                .andRespond(withSuccess(new ClassPathResource(path), MediaType.APPLICATION_XML));
    }
}