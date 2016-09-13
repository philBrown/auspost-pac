package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.interceptor.AuthKeyHttpHeaderInterceptor;
import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.model.Service;
import au.id.philipbrown.auspost.pac.request.DomesticLetterServiceRequest;
import au.id.philipbrown.auspost.pac.request.DomesticParcelServiceRequest;
import au.id.philipbrown.auspost.pac.request.InternationalServiceRequest;
import au.id.philipbrown.auspost.pac.request.RequestParams;
import au.id.philipbrown.auspost.pac.response.CountryResponse;
import au.id.philipbrown.auspost.pac.response.ServiceResponse;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class RestPostageAssessmentCalculator implements PostageAssessmentCalculator {
    protected static final String BASE_URI = "https://digitalapi.auspost.com.au";

    protected static final String COUNTRY_URI = BASE_URI + "/postage/country.xml";
    protected static final String DOMESTIC_LETTER_SERVICE_URI = BASE_URI + "/postage/letter/domestic/service.xml";
    protected static final String DOMESTIC_PARCEL_SERVICE_URI = BASE_URI + "/postage/parcel/domestic/service.xml";
    protected static final String INTERNATIONAL_LETTER_SERVICE_URI = BASE_URI + "/postage/letter/international/service.xml";
    protected static final String INTERNATIONAL_PARCEL_SERVICE_URI = BASE_URI + "/postage/parcel/international/service.xml";

    protected static final String DOMESTIC_LETTER_POSTAGE_URI = BASE_URI + "/postage/letter/domestic/calculate.xml";
    protected static final String DOMESTIC_PARCEL_POSTAGE_URI = BASE_URI + "/postage/parcel/domestic/calculate.xml";
    protected static final String INTERNATIONAL_LETTER_POSTAGE_URI = BASE_URI + "/postage/letter/international/calculate.xml";
    protected static final String INTERNATIONAL_PARCEL_POSTAGE_URI = BASE_URI + "/postage/parcel/international/calculate.xml";

    private final RestOperations restClient;

    public RestPostageAssessmentCalculator(final RestTemplate restTemplate, final String apiKey) {
        restTemplate.getInterceptors().add(new AuthKeyHttpHeaderInterceptor(apiKey));
        this.restClient = restTemplate;
    }

    @Override
    public List<Country> getCountries() {
        return restClient.getForObject(COUNTRY_URI, CountryResponse.class).getCountries();
    }

    @Override
    public List<Service> getDomesticLetterServices(final DomesticLetterServiceRequest request) {
        return getServices(buildUri(DOMESTIC_LETTER_SERVICE_URI, request));
    }

    @Override
    public List<Service> getInternationalLetterServices(final InternationalServiceRequest request) {
        return getServices(buildUri(INTERNATIONAL_LETTER_SERVICE_URI, request));
    }

    @Override
    public List<Service> getDomesticParcelServices(final DomesticParcelServiceRequest request) {
        return getServices(buildUri(DOMESTIC_PARCEL_SERVICE_URI, request));
    }

    @Override
    public List<Service> getInternationalParcelServices(final InternationalServiceRequest request) {
        return getServices(buildUri(INTERNATIONAL_PARCEL_SERVICE_URI, request));
    }

    protected List<Service> getServices(final String url) {
        return restClient.getForObject(url, ServiceResponse.class).getServices();
    }

    protected static String buildUri(final String url, final RequestParams params) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(params.getParams())
                .toUriString();
    }
}
