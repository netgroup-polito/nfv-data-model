
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
 *         &lt;element ref="{}VNF_dependency" maxOccurs="unbounded"/>
 *         &lt;element ref="{}PropertyDefinition" maxOccurs="unbounded"/>
 *         &lt;element ref="{}VNFD" maxOccurs="unbounded"/>
 *         &lt;element ref="{}VNFFGD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}PNFD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Service_deployment_flavour" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Connection_point" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ParsingString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "vnfDependency",
    "propertyDefinition",
    "vnfd",
    "vnffgd",
    "pnfd",
    "serviceDeploymentFlavour",
    "connectionPoint",
    "parsingString"
})
@XmlRootElement(name = "NSD")
public class NSD {

    @XmlElement(name = "VNF_dependency", required = true)
    protected List<VNFDependency> vnfDependency;
    @XmlElement(name = "PropertyDefinition", required = true)
    protected List<PropertyDefinition> propertyDefinition;
    @XmlElement(name = "VNFD", required = true)
    protected List<VNFD> vnfd;
    @XmlElement(name = "VNFFGD")
    protected List<VNFFGD> vnffgd;
    @XmlElement(name = "PNFD")
    protected List<PNFD> pnfd;
    @XmlElement(name = "Service_deployment_flavour")
    protected List<ServiceDeploymentFlavour> serviceDeploymentFlavour;
    @XmlElement(name = "Connection_point")
    protected List<ConnectionPoint> connectionPoint;
    @XmlElement(name = "ParsingString")
    protected String parsingString;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "vendor")
    protected String vendor;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the vnfDependency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vnfDependency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVNFDependency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VNFDependency }
     * 
     * 
     */
    public List<VNFDependency> getVNFDependency() {
        if (vnfDependency == null) {
            vnfDependency = new ArrayList<VNFDependency>();
        }
        return this.vnfDependency;
    }

    /**
     * Gets the value of the propertyDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyDefinition }
     * 
     * 
     */
    public List<PropertyDefinition> getPropertyDefinition() {
        if (propertyDefinition == null) {
            propertyDefinition = new ArrayList<PropertyDefinition>();
        }
        return this.propertyDefinition;
    }

    /**
     * Gets the value of the vnfd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vnfd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVNFD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VNFD }
     * 
     * 
     */
    public List<VNFD> getVNFD() {
        if (vnfd == null) {
            vnfd = new ArrayList<VNFD>();
        }
        return this.vnfd;
    }

    /**
     * Gets the value of the vnffgd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vnffgd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVNFFGD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VNFFGD }
     * 
     * 
     */
    public List<VNFFGD> getVNFFGD() {
        if (vnffgd == null) {
            vnffgd = new ArrayList<VNFFGD>();
        }
        return this.vnffgd;
    }

    /**
     * Gets the value of the pnfd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pnfd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPNFD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PNFD }
     * 
     * 
     */
    public List<PNFD> getPNFD() {
        if (pnfd == null) {
            pnfd = new ArrayList<PNFD>();
        }
        return this.pnfd;
    }

    /**
     * Gets the value of the serviceDeploymentFlavour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceDeploymentFlavour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceDeploymentFlavour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDeploymentFlavour }
     * 
     * 
     */
    public List<ServiceDeploymentFlavour> getServiceDeploymentFlavour() {
        if (serviceDeploymentFlavour == null) {
            serviceDeploymentFlavour = new ArrayList<ServiceDeploymentFlavour>();
        }
        return this.serviceDeploymentFlavour;
    }

    /**
     * Gets the value of the connectionPoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectionPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectionPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnectionPoint }
     * 
     * 
     */
    public List<ConnectionPoint> getConnectionPoint() {
        if (connectionPoint == null) {
            connectionPoint = new ArrayList<ConnectionPoint>();
        }
        return this.connectionPoint;
    }

    /**
     * Recupera il valore della proprietà parsingString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParsingString() {
        return parsingString;
    }

    /**
     * Imposta il valore della proprietà parsingString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParsingString(String value) {
        this.parsingString = value;
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
