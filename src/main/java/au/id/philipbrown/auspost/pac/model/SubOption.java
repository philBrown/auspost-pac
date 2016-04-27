package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "option")
public class SubOption extends CoverableModel {
    private SubOption() {
    }

    public SubOption(final String code) {
        super(code);
    }
}
