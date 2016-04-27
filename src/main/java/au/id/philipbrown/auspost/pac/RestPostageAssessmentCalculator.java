package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.interceptor.AuthKeyHttpHeaderInterceptor;
import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.model.Service;
import au.id.philipbrown.auspost.pac.response.CountryResponse;
import au.id.philipbrown.auspost.pac.response.ServiceResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Component
public class RestPostageAssessmentCalculator implements PostageAssessmentCalculator {
    private static final String BASE_URI = "https://digitalapi.auspost.com.au";

    private static final String COUNTRY_URI = BASE_URI + "/postage/country.xml";
    private static final String DOMESTIC_LETTER_SERVICE_URI = BASE_URI + "/postage/letter/domestic/service.xml";
    private static final String DOMESTIC_PARCEL_SERVICE_URI = BASE_URI + "/postage/parcel/domestic/service.xml";
    private static final String INTERNATIONAL_LETTER_SERVICE_URI = BASE_URI + "/postage/letter/international/service.xml";
    private static final String INTERNATIONAL_PARCEL_SERVICE_URI = BASE_URI + "/postage/parcel/international/service.xml";

    private static final String DOMESTIC_LETTER_POSTAGE_URI = BASE_URI + "/postage/letter/domestic/calculate.xml";
    private static final String DOMESTIC_PARCEL_POSTAGE_URI = BASE_URI + "/postage/parcel/domestic/calculate.xml";
    private static final String INTERNATIONAL_LETTER_POSTAGE_URI = BASE_URI + "/postage/letter/international/calculate.xml";
    private static final String INTERNATIONAL_PARCEL_POSTAGE_URI = BASE_URI + "/postage/parcel/international/calculate.xml";

    private static final String FROM_POSTCODE = "from_postcode";
    private static final String TO_POSTCODE = "to_postcode";
    private static final String LENGTH = "length";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String THICKNESS = "thickness";
    private static final String WEIGHT = "weight";
    private static final String COUNTRY_CODE = "country_code";
    private static final String SERVICE_CODE= "service_code";
    private static final String OPTION_CODE= "option_code";
    private static final String SUB_OPTION_CODE= "suboption_code";
    private static final String EXTRA_COVER = "extra_cover";

    private final RestOperations restClient;

    public RestPostageAssessmentCalculator(final String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new AuthKeyHttpHeaderInterceptor(apiKey)));
        this.restClient = restTemplate;
    }

    @Override
    public List<Country> getCountries() {
        return restClient.getForObject(COUNTRY_URI, CountryResponse.class).getCountries();
    }

    @Override
    public List<Service> getDomesticLetterServices(final int length, final int width, final int thickness, final int weight) {
        final String url = buildUri(DOMESTIC_LETTER_SERVICE_URI, weight, length, width)
                .queryParam(THICKNESS, thickness)
                .toUriString();
        return getServices(url);
    }

    @Override
    public List<Service> getInternationalLetterServices(final Country country, final int weight) {
        final String url = buildUri(INTERNATIONAL_LETTER_SERVICE_URI, weight)
                .queryParam(COUNTRY_CODE, country)
                .toUriString();
        return getServices(url);
    }

    @Override
    public List<Service> getDomesticParcelServices(final String fromPostcode, final String toPostcode, final int length, final int width, final int height, final double weight) {
        final String url = buildUri(DOMESTIC_PARCEL_SERVICE_URI, weight, length, width)
                .queryParam(FROM_POSTCODE, fromPostcode)
                .queryParam(TO_POSTCODE, toPostcode)
                .queryParam(HEIGHT, height)
                .toUriString();
        return getServices(url);
    }

    @Override
    public List<Service> getInternationalParcelServices(final Country country, final double weight) {
        final String url = buildUri(INTERNATIONAL_PARCEL_SERVICE_URI, weight)
                .queryParam(COUNTRY_CODE, country)
                .toUriString();
        return getServices(url);
    }

    private List<Service> getServices(final String url) {
        return restClient.getForObject(url, ServiceResponse.class).getServices();
    }

    private UriComponentsBuilder buildUri(final String url, final Number weight) {
        return UriComponentsBuilder.fromHttpUrl(url).queryParam(WEIGHT, weight);
    }

    private UriComponentsBuilder buildUri(final String url, final Number weight, final int length, final int width) {
        return buildUri(url, weight)
                .queryParam(LENGTH, length)
                .queryParam(WIDTH, width);
    }
}
