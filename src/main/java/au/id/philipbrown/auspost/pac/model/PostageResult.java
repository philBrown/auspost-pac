package au.id.philipbrown.auspost.pac.model;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "postage_result")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostageResult {
    @XmlElement(required = true)
    private String service;
    @XmlElement(name = "total_cost", required = true)
    private BigDecimal totalCost;
    @XmlElement(name = "delivery_time", nillable = true)
    private String deliveryTime;

    @XmlElementWrapper(name = "costs")
    @XmlElement(name = "cost")
    private final List<Cost> costs = new ArrayList<>();

    private PostageResult() {
    }

    public PostageResult(final String service, final BigDecimal totalCost) {
        this.service = service;
        this.totalCost = totalCost;
    }

    public String getService() {
        return service;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Nullable
    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(final String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<Cost> getCosts() {
        return costs;
    }
}
