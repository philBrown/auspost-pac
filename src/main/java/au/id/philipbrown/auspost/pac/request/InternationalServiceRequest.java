package au.id.philipbrown.auspost.pac.request;

import au.id.philipbrown.auspost.pac.model.Country;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class InternationalServiceRequest implements RequestParams {
    private final Country country;
    private final Number weight;

    public InternationalServiceRequest(final Country country, final Number weight) {
        this.country = country;
        this.weight = weight;
    }

    public Country getCountry() {
        return country;
    }

    public Number getWeight() {
        return weight;
    }

    @Override
    public MultiValueMap<String, String> getParams() {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(COUNTRY_CODE, country.getCode());
        params.add(WEIGHT, weight.toString());
        return params;
    }
}
