
package it.polito.dp2.rest.nfv.jaxb;

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
 *         &lt;element ref="{}Node_Connection" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="n_endpoint" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="n_vl" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "nodeConnection"
})
@XmlRootElement(name = "NetworkForwardingPaths")
public class NetworkForwardingPaths {

    @XmlElement(name = "Node_Connection", required = true)
    protected List<NodeConnection> nodeConnection;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "n_endpoint")
    protected Integer nEndpoint;
    @XmlAttribute(name = "n_vl")
    protected Integer nVl;
    @XmlAttribute(name = "vnffgd_security")
    protected String vnffgdSecurity;

    /**
     * Gets the value of the nodeConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nodeConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNodeConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NodeConnection }
     * 
     * 
     */
    public List<NodeConnection> getNodeConnection() {
        if (nodeConnection == null) {
            nodeConnection = new ArrayList<NodeConnection>();
        }
        return this.nodeConnection;
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
     * Recupera il valore della proprietà nEndpoint.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNEndpoint() {
        return nEndpoint;
    }

    /**
     * Imposta il valore della proprietà nEndpoint.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNEndpoint(Integer value) {
        this.nEndpoint = value;
    }

    /**
     * Recupera il valore della proprietà nVl.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNVl() {
        return nVl;
    }

    /**
     * Imposta il valore della proprietà nVl.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNVl(Integer value) {
        this.nVl = value;
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
