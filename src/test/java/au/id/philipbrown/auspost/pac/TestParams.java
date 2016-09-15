package au.id.philipbrown.auspost.pac;

import au.id.philipbrown.auspost.pac.model.Country;
import au.id.philipbrown.auspost.pac.request.DomesticLetterServiceRequest;
import au.id.philipbrown.auspost.pac.request.DomesticParcelServiceRequest;
import au.id.philipbrown.auspost.pac.request.InternationalServiceRequest;

import java.io.IOException;
import java.util.Properties;

/**
 * Get request params from test resource properties.
 */
public class TestParams {
    private final Properties properties = new Properties();

    public TestParams() {
        try {
            properties.load(getClass().getResourceAsStream("/params.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DomesticLetterServiceRequest getDomesticLetterServiceRequest() {
        return new DomesticLetterServiceRequest(
                Integer.parseInt(properties.getProperty("postage.letter.domestic.length")),
                Integer.parseInt(properties.getProperty("postage.letter.domestic.width")),
                Integer.parseInt(properties.getProperty("postage.letter.domestic.thickness")),
                Integer.parseInt(properties.getProperty("postage.letter.domestic.weight"))
        );
    }

    public InternationalServiceRequest getInternationalLetterServiceRequest() {
        return new InternationalServiceRequest(
                new Country(properties.getProperty("postage.international.country")),
                Integer.parseInt(properties.getProperty("postage.international.letter.weight"))
        );
    }

    public DomesticParcelServiceRequest getDomesticParcelServiceRequest() {
        return new DomesticParcelServiceRequest(
                properties.getProperty("postage.parcel.domestic.postcode.from"),
                properties.getProperty("postage.parcel.domestic.postcode.to"),
                Integer.parseInt(properties.getProperty("postage.parcel.domestic.length")),
                Integer.parseInt(properties.getProperty("postage.parcel.domestic.width")),
                Integer.parseInt(properties.getProperty("postage.parcel.domestic.height")),
                Double.parseDouble(properties.getProperty("postage.parcel.domestic.weight"))
        );
    }

    public InternationalServiceRequest getInternationalParcelServiceRequest() {
        return new InternationalServiceRequest(
                new Country(properties.getProperty("postage.international.country")),
                Double.parseDouble(properties.getProperty("postage.international.parcel.weight"))
        );
    }
}
