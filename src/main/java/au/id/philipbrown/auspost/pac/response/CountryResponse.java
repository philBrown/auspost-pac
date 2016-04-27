package au.id.philipbrown.auspost.pac.response;

import au.id.philipbrown.auspost.pac.model.Country;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "countries")
public class CountryResponse {
    @XmlElement(name = "country")
    private final List<Country> countries = new ArrayList<>();

    public List<Country> getCountries() {
        return countries;
    }
}
