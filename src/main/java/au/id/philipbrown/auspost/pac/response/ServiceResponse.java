package au.id.philipbrown.auspost.pac.response;

import au.id.philipbrown.auspost.pac.model.Service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "services")
public class ServiceResponse {
    @XmlElement(name = "service")
    private final List<Service> services = new ArrayList<>();

    public List<Service> getServices() {
        return services;
    }
}
