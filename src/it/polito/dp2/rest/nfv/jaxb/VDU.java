
package it.polito.dp2.rest.nfv.jaxb;

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
 *         &lt;element name="Computational_requirements" type="{}Computational_propertiesType"/>
 *         &lt;element name="Memory_requirements" type="{}Memory_propertiesType" minOccurs="0"/>
 *         &lt;element name="Network_requirements" type="{}Network_propertiesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vm_image" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "computationalRequirements",
    "memoryRequirements",
    "networkRequirements"
})
@XmlRootElement(name = "VDU")
public class VDU {

    @XmlElement(name = "Computational_requirements", required = true)
    protected ComputationalPropertiesType computationalRequirements;
    @XmlElement(name = "Memory_requirements")
    protected MemoryPropertiesType memoryRequirements;
    @XmlElement(name = "Network_requirements")
    protected NetworkPropertiesType networkRequirements;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "vm_image")
    protected String vmImage;

    /**
     * Recupera il valore della proprietà computationalRequirements.
     * 
     * @return
     *     possible object is
     *     {@link ComputationalPropertiesType }
     *     
     */
    public ComputationalPropertiesType getComputationalRequirements() {
        return computationalRequirements;
    }

    /**
     * Imposta il valore della proprietà computationalRequirements.
     * 
     * @param value
     *     allowed object is
     *     {@link ComputationalPropertiesType }
     *     
     */
    public void setComputationalRequirements(ComputationalPropertiesType value) {
        this.computationalRequirements = value;
    }

    /**
     * Recupera il valore della proprietà memoryRequirements.
     * 
     * @return
     *     possible object is
     *     {@link MemoryPropertiesType }
     *     
     */
    public MemoryPropertiesType getMemoryRequirements() {
        return memoryRequirements;
    }

    /**
     * Imposta il valore della proprietà memoryRequirements.
     * 
     * @param value
     *     allowed object is
     *     {@link MemoryPropertiesType }
     *     
     */
    public void setMemoryRequirements(MemoryPropertiesType value) {
        this.memoryRequirements = value;
    }

    /**
     * Recupera il valore della proprietà networkRequirements.
     * 
     * @return
     *     possible object is
     *     {@link NetworkPropertiesType }
     *     
     */
    public NetworkPropertiesType getNetworkRequirements() {
        return networkRequirements;
    }

    /**
     * Imposta il valore della proprietà networkRequirements.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkPropertiesType }
     *     
     */
    public void setNetworkRequirements(NetworkPropertiesType value) {
        this.networkRequirements = value;
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
     * Recupera il valore della proprietà vmImage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVmImage() {
        return vmImage;
    }

    /**
     * Imposta il valore della proprietà vmImage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVmImage(String value) {
        this.vmImage = value;
    }

}
