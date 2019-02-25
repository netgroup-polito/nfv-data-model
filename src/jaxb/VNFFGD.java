
package jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}NetworkForwardingPaths" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vnffgd_security" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "networkForwardingPaths"
})
@XmlRootElement(name = "VNFFGD")
public class VNFFGD {

    @XmlElement(name = "NetworkForwardingPaths")
    protected List<NetworkForwardingPaths> networkForwardingPaths;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "vnffgd_security")
    protected String vnffgdSecurity;

    /**
     * Gets the value of the networkForwardingPaths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the networkForwardingPaths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNetworkForwardingPaths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NetworkForwardingPaths }
     * 
     * 
     */
    public List<NetworkForwardingPaths> getNetworkForwardingPaths() {
        if (networkForwardingPaths == null) {
            networkForwardingPaths = new ArrayList<NetworkForwardingPaths>();
        }
        return this.networkForwardingPaths;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà vnffgdSecurity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnffgdSecurity() {
        return vnffgdSecurity;
    }

    /**
     * Imposta il valore della proprietà vnffgdSecurity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnffgdSecurity(String value) {
        this.vnffgdSecurity = value;
    }

}
