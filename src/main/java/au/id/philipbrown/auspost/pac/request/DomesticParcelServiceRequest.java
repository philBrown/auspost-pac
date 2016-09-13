package au.id.philipbrown.auspost.pac.request;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class DomesticParcelServiceRequest implements RequestParams {
    private final String fromPostCode;
    private final String toPostcode;
    private final int length;
    private final int width;
    private final int height;
    private final double weight;

    public DomesticParcelServiceRequest(final String fromPostCode, final String toPostcode, final int length, final int width, final int height, final double weight) {
        this.fromPostCode = fromPostCode;
        this.toPostcode = toPostcode;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public MultiValueMap<String, String> getParams() {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(FROM_POSTCODE, fromPostCode);
        params.add(TO_POSTCODE, toPostcode);
        params.add(LENGTH, Integer.toString(length));
        params.add(WIDTH, Integer.toString(width));
        params.add(HEIGHT, Integer.toString(height));
        params.add(WEIGHT, Double.toString(weight));
        return params;
    }
}
