
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
 *         &lt;element ref="{}Virtual_Link" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}VDU" maxOccurs="unbounded"/>
 *         &lt;element ref="{}Dependency" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "virtualLink",
    "vdu",
    "dependency"
})
@XmlRootElement(name = "VNFD")
public class VNFD {

    @XmlElement(name = "Virtual_Link")
    protected List<VirtualLink> virtualLink;
    @XmlElement(name = "VDU", required = true)
    protected List<VDU> vdu;
    @XmlElement(name = "Dependency")
    protected Dependency dependency;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "vendor")
    protected String vendor;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the virtualLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtualLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtualLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VirtualLink }
     * 
     * 
     */
    public List<VirtualLink> getVirtualLink() {
        if (virtualLink == null) {
            virtualLink = new ArrayList<VirtualLink>();
        }
        return this.virtualLink;
    }

    /**
     * Gets the value of the vdu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vdu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVDU().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VDU }
     * 
     * 
     */
    public List<VDU> getVDU() {
        if (vdu == null) {
            vdu = new ArrayList<VDU>();
        }
        return this.vdu;
    }

    /**
     * Recupera il valore della proprietà dependency.
     * 
     * @return
     *     possible object is
     *     {@link Dependency }
     *     
     */
    public Dependency getDependency() {
        return dependency;
    }

    /**
     * Imposta il valore della proprietà dependency.
     * 
     * @param value
     *     allowed object is
     *     {@link Dependency }
     *     
     */
    public void setDependency(Dependency value) {
        this.dependency = value;
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
     * Recupera il valore della proprietà vendor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Imposta il valore della proprietà vendor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
