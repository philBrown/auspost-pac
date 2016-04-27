package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public abstract class CoverableModel extends CodedModel {
    @XmlElement(name = "max_extra_cover", required = true)
    private BigDecimal maxExtraCover;

    protected CoverableModel() {
    }

    protected CoverableModel(final String code) {
        super(code);
    }

    public BigDecimal getMaxExtraCover() {
        return maxExtraCover;
    }

    public void setMaxExtraCover(final BigDecimal maxExtraCover) {
        this.maxExtraCover = maxExtraCover;
    }
}
