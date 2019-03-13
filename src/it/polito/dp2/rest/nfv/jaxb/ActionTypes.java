
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ActionTypes.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ActionTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ALLOW"/>
 *     &lt;enumeration value="DENY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActionTypes")
@XmlEnum
public enum ActionTypes {

    ALLOW,
    DENY;

    public String value() {
        return name();
    }

    public static ActionTypes fromValue(String v) {
        return valueOf(v);
    }

}
