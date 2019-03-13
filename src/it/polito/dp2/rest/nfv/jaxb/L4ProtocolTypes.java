
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per L4ProtocolTypes.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="L4ProtocolTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANY"/>
 *     &lt;enumeration value="TCP"/>
 *     &lt;enumeration value="UDP"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "L4ProtocolTypes")
@XmlEnum
public enum L4ProtocolTypes {

    ANY,
    TCP,
    UDP,
    OTHER;

    public String value() {
        return name();
    }

    public static L4ProtocolTypes fromValue(String v) {
        return valueOf(v);
    }

}
