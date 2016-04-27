package au.id.philipbrown.auspost.pac.model;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;

/**
 * Countries supported by the PAC API
 */
@XmlRootElement
public class Country extends CodedModel {
    @XmlElement(required = true)
    private String description;
    @XmlElement(nillable = true)
    private String postalServiceName;
    @XmlElement(nillable = true)
    private URL postalServiceUrl;

    /**
     * Private constructor for de-serialisation
     */
    private Country() {
    }

    public Country(final String code) {
        super(code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Nullable
    public String getPostalServiceName() {
        return postalServiceName;
    }

    public void setPostalServiceName(final String postalServiceName) {
        this.postalServiceName = postalServiceName;
    }

    @Nullable
    public URL getPostalServiceUrl() {
        return postalServiceUrl;
    }

    public void setPostalServiceUrl(URL postalServiceUrl) {
        this.postalServiceUrl = postalServiceUrl;
    }
}
