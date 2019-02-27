
package jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per test_accessType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="test_accessType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Passive"/>
 *     &lt;enumeration value="Active"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "test_accessType")
@XmlEnum
public enum TestAccessType {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Passive")
    PASSIVE("Passive"),
    @XmlEnumValue("Active")
    ACTIVE("Active");
    private final String value;

    TestAccessType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TestAccessType fromValue(String v) {
        for (TestAccessType c: TestAccessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
