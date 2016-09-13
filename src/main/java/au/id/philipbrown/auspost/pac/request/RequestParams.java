package au.id.philipbrown.auspost.pac.request;

import org.springframework.util.MultiValueMap;

public interface RequestParams {
    String FROM_POSTCODE = "from_postcode";
    String TO_POSTCODE = "to_postcode";
    String LENGTH = "length";
    String WIDTH = "width";
    String HEIGHT = "height";
    String THICKNESS = "thickness";
    String WEIGHT = "weight";
    String COUNTRY_CODE = "country_code";
    String SERVICE_CODE= "service_code";
    String OPTION_CODE= "option_code";
    String SUB_OPTION_CODE= "suboption_code";
    String EXTRA_COVER = "extra_cover";

    MultiValueMap<String, String> getParams();
}
