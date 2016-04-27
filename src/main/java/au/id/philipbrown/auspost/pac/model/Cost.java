package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cost {
    @XmlElement(required = true)
    private BigDecimal cost;

    @XmlElement(required = true)
    private String item;

    private Cost() {
    }

    public Cost(final BigDecimal cost, final String item) {
        this.cost = cost;
        this.item = item;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getItem() {
        return item;
    }
}
