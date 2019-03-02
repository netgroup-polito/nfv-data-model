
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per connection_pointType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="connection_pointType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="virtual_port"/>
 *     &lt;enumeration value="virtual_nic_addr"/>
 *     &lt;enumeration value="physical_port"/>
 *     &lt;enumeration value="physical_nic_addr"/>
 *     &lt;enumeration value="endpoint"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "connection_pointType")
@XmlEnum
public enum ConnectionPointType {

    @XmlEnumValue("virtual_port")
    VIRTUAL_PORT("virtual_port"),
    @XmlEnumValue("virtual_nic_addr")
    VIRTUAL_NIC_ADDR("virtual_nic_addr"),
    @XmlEnumValue("physical_port")
    PHYSICAL_PORT("physical_port"),
    @XmlEnumValue("physical_nic_addr")
    PHYSICAL_NIC_ADDR("physical_nic_addr"),
    @XmlEnumValue("endpoint")
    ENDPOINT("endpoint");
    private final String value;

    ConnectionPointType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConnectionPointType fromValue(String v) {
        for (ConnectionPointType c: ConnectionPointType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
