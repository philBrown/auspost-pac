package au.id.philipbrown.auspost.pac.request;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class DomesticLetterServiceRequest implements RequestParams {
    private final int length;
    private final int width;
    private final int thickness;
    private final int weight;

    /**
     * Parameters for domestic letter service request.
     *
     * @param length       length in mm
     * @param width        width in mm
     * @param thickness    thickness in mm
     * @param weight       weight in gm
     */
    public DomesticLetterServiceRequest(
            final int length,
            final int width,
            final int thickness,
            final int weight
    ) {
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getThickness() {
        return thickness;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public MultiValueMap<String, String> getParams() {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(LENGTH, Integer.toString(length));
        params.add(WIDTH, Integer.toString(width));
        params.add(THICKNESS, Integer.toString(thickness));
        params.add(WEIGHT, Integer.toString(weight));
        return params;
    }
}
