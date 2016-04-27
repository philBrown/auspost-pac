package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Option extends CodedModel {
    @XmlElementWrapper(name = "suboptions")
    @XmlElement(name = "option")
    private final List<SubOption> options = new ArrayList<>();

    private Option() {
    }

    public Option(final String code) {
        super(code);
    }

    public List<SubOption> getOptions() {
        return options;
    }
}
