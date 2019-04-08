
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
 *         &lt;element name="Computational_properties" type="{}Computational_propertiesType" minOccurs="0"/>
 *         &lt;element name="Memory_properties" type="{}Memory_propertiesType" minOccurs="0"/>
 *         &lt;element name="Network_properties" type="{}Network_propertiesType" minOccurs="0"/>
 *         &lt;element name="V_Node_Ref" type="{}VNodeRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="P_Node_Ref" type="{}PNodeRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SupportedVNF" type="{}SupportedVNFType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fixedEndpoint" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="maxVNF" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="type" type="{}TypeOfHost" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "computationalProperties",
    "memoryProperties",
    "networkProperties",
    "vNodeRef",
    "pNodeRef",
    "supportedVNF"
})
@XmlRootElement(name = "Host")
public class Host {

    @XmlElement(name = "Computational_properties")
    protected ComputationalPropertiesType computationalProperties;
    @XmlElement(name = "Memory_properties")
    protected MemoryPropertiesType memoryProperties;
    @XmlElement(name = "Network_properties")
    protected NetworkPropertiesType networkProperties;
    @XmlElement(name = "V_Node_Ref")
    protected List<VNodeRefType> vNodeRef;
    @XmlElement(name = "P_Node_Ref")
    protected List<PNodeRefType> pNodeRef;
    @XmlElement(name = "SupportedVNF")
    protected List<SupportedVNFType> supportedVNF;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "fixedEndpoint")
    protected String fixedEndpoint;
    @XmlAttribute(name = "active")
    protected Boolean active;
    @XmlAttribute(name = "maxVNF")
    protected Integer maxVNF;
    @XmlAttribute(name = "type")
    protected TypeOfHost type;

    /**
     * Recupera il valore della proprietà computationalProperties.
     * 
     * @return
     *     possible object is
     *     {@link ComputationalPropertiesType }
     *     
     */
    public ComputationalPropertiesType getComputationalProperties() {
        return computationalProperties;
    }

    /**
     * Imposta il valore della proprietà computationalProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link ComputationalPropertiesType }
     *     
     */
    public void setComputationalProperties(ComputationalPropertiesType value) {
        this.computationalProperties = value;
    }

    /**
     * Recupera il valore della proprietà memoryProperties.
     * 
     * @return
     *     possible object is
     *     {@link MemoryPropertiesType }
     *     
     */
    public MemoryPropertiesType getMemoryProperties() {
        return memoryProperties;
    }

    /**
     * Imposta il valore della proprietà memoryProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link MemoryPropertiesType }
     *     
     */
    public void setMemoryProperties(MemoryPropertiesType value) {
        this.memoryProperties = value;
    }

    /**
     * Recupera il valore della proprietà networkProperties.
     * 
     * @return
     *     possible object is
     *     {@link NetworkPropertiesType }
     *     
     */
    public NetworkPropertiesType getNetworkProperties() {
        return networkProperties;
    }

    /**
     * Imposta il valore della proprietà networkProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkPropertiesType }
     *     
     */
    public void setNetworkProperties(NetworkPropertiesType value) {
        this.networkProperties = value;
    }

    /**
     * Gets the value of the vNodeRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vNodeRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVNodeRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VNodeRefType }
     * 
     * 
     */
    public List<VNodeRefType> getVNodeRef() {
        if (vNodeRef == null) {
            vNodeRef = new ArrayList<VNodeRefType>();
        }
        return this.vNodeRef;
    }

    /**
     * Gets the value of the pNodeRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pNodeRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPNodeRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PNodeRefType }
     * 
     * 
     */
    public List<PNodeRefType> getPNodeRef() {
        if (pNodeRef == null) {
            pNodeRef = new ArrayList<PNodeRefType>();
        }
        return this.pNodeRef;
    }

    /**
     * Gets the value of the supportedVNF property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedVNF property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedVNF().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupportedVNFType }
     * 
     * 
     */
    public List<SupportedVNFType> getSupportedVNF() {
        if (supportedVNF == null) {
            supportedVNF = new ArrayList<SupportedVNFType>();
        }
        return this.supportedVNF;
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
     * Recupera il valore della proprietà fixedEndpoint.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedEndpoint() {
        return fixedEndpoint;
    }

    /**
     * Imposta il valore della proprietà fixedEndpoint.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedEndpoint(String value) {
        this.fixedEndpoint = value;
    }

    /**
     * Recupera il valore della proprietà active.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isActive() {
        if (active == null) {
            return false;
        } else {
            return active;
        }
    }

    /**
     * Imposta il valore della proprietà active.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Recupera il valore della proprietà maxVNF.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxVNF() {
        return maxVNF;
    }

    /**
     * Imposta il valore della proprietà maxVNF.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxVNF(Integer value) {
        this.maxVNF = value;
    }

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfHost }
     *     
     */
    public TypeOfHost getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfHost }
     *     
     */
    public void setType(TypeOfHost value) {
        this.type = value;
    }

}
