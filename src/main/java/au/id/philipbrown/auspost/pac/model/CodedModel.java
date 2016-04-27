package au.id.philipbrown.auspost.pac.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * All response objects have the below properties
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class CodedModel {
    @XmlElement(required = true)
    private String code;
    @XmlElement(required = true)
    private String name;

    protected CodedModel() {
    }

    protected CodedModel(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return code;
    }
}
