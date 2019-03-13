
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per P-Name.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="P-Name">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IsolationProperty"/>
 *     &lt;enumeration value="ReachabilityProperty"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "P-Name")
@XmlEnum
public enum PName {

    @XmlEnumValue("IsolationProperty")
    ISOLATION_PROPERTY("IsolationProperty"),
    @XmlEnumValue("ReachabilityProperty")
    REACHABILITY_PROPERTY("ReachabilityProperty");
    private final String value;

    PName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PName fromValue(String v) {
        for (PName c: PName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
