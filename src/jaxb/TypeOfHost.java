
package jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TypeOfHost.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeOfHost">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CLIENT"/>
 *     &lt;enumeration value="SERVER"/>
 *     &lt;enumeration value="MIDDLEBOX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TypeOfHost")
@XmlEnum
public enum TypeOfHost {

    CLIENT,
    SERVER,
    MIDDLEBOX;

    public String value() {
        return name();
    }

    public static TypeOfHost fromValue(String v) {
        return valueOf(v);
    }

}
