package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Service extends CoverableModel {
    @XmlElement(required = true)
    private BigDecimal price;

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private final List<Option> options = new ArrayList<>();

    private Service() {
    }

    public Service(final String code) {
        super(code);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public List<Option> getOptions() {
        return options;
    }
}
