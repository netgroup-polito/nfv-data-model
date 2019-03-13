
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per functionalTypes.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="functionalTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FIREWALL"/>
 *     &lt;enumeration value="ENDHOST"/>
 *     &lt;enumeration value="ENDPOINT"/>
 *     &lt;enumeration value="ANTISPAM"/>
 *     &lt;enumeration value="CACHE"/>
 *     &lt;enumeration value="DPI"/>
 *     &lt;enumeration value="MAILCLIENT"/>
 *     &lt;enumeration value="MAILSERVER"/>
 *     &lt;enumeration value="NAT"/>
 *     &lt;enumeration value="VPNACCESS"/>
 *     &lt;enumeration value="VPNEXIT"/>
 *     &lt;enumeration value="WEBCLIENT"/>
 *     &lt;enumeration value="WEBSERVER"/>
 *     &lt;enumeration value="FIELDMODIFIER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "functionalTypes")
@XmlEnum
public enum FunctionalTypes {

    FIREWALL,
    ENDHOST,
    ENDPOINT,
    ANTISPAM,
    CACHE,
    DPI,
    MAILCLIENT,
    MAILSERVER,
    NAT,
    VPNACCESS,
    VPNEXIT,
    WEBCLIENT,
    WEBSERVER,
    FIELDMODIFIER;

    public String value() {
        return name();
    }

    public static FunctionalTypes fromValue(String v) {
        return valueOf(v);
    }

}
