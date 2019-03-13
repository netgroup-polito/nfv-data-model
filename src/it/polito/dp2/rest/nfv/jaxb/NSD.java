
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
 *         &lt;element ref="{}VNF_dependency" minOccurs="0"/>
 *         &lt;element ref="{}PropertyDefinition"/>
 *         &lt;element ref="{}VNF" minOccurs="0"/>
 *         &lt;element ref="{}VNFFGD" minOccurs="0"/>
 *         &lt;element ref="{}PNF" minOccurs="0"/>
 *         &lt;element ref="{}flavours" minOccurs="0"/>
 *         &lt;element ref="{}Connection_points" minOccurs="0"/>
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
    "vnf",
    "vnffgd",
    "pnf",
    "flavours",
    "connectionPoints",
    "parsingString"
})
@XmlRootElement(name = "NSD")
public class NSD {

    @XmlElement(name = "VNF_dependency")
    protected VNFDependency vnfDependency;
    @XmlElement(name = "PropertyDefinition", required = true)
    protected PropertyDefinition propertyDefinition;
    @XmlElement(name = "VNF")
    protected VNF vnf;
    @XmlElement(name = "VNFFGD")
    protected VNFFGD vnffgd;
    @XmlElement(name = "PNF")
    protected PNF pnf;
    protected Flavours flavours;
    @XmlElement(name = "Connection_points")
    protected ConnectionPoints connectionPoints;
    @XmlElement(name = "ParsingString")
    protected String parsingString;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "vendor")
    protected String vendor;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Recupera il valore della proprietà vnfDependency.
     * 
     * @return
     *     possible object is
     *     {@link VNFDependency }
     *     
     */
    public VNFDependency getVNFDependency() {
        return vnfDependency;
    }

    /**
     * Imposta il valore della proprietà vnfDependency.
     * 
     * @param value
     *     allowed object is
     *     {@link VNFDependency }
     *     
     */
    public void setVNFDependency(VNFDependency value) {
        this.vnfDependency = value;
    }

    /**
     * Recupera il valore della proprietà propertyDefinition.
     * 
     * @return
     *     possible object is
     *     {@link PropertyDefinition }
     *     
     */
    public PropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

    /**
     * Imposta il valore della proprietà propertyDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyDefinition }
     *     
     */
    public void setPropertyDefinition(PropertyDefinition value) {
        this.propertyDefinition = value;
    }

    /**
     * Recupera il valore della proprietà vnf.
     * 
     * @return
     *     possible object is
     *     {@link VNF }
     *     
     */
    public VNF getVNF() {
        return vnf;
    }

    /**
     * Imposta il valore della proprietà vnf.
     * 
     * @param value
     *     allowed object is
     *     {@link VNF }
     *     
     */
    public void setVNF(VNF value) {
        this.vnf = value;
    }

    /**
     * Recupera il valore della proprietà vnffgd.
     * 
     * @return
     *     possible object is
     *     {@link VNFFGD }
     *     
     */
    public VNFFGD getVNFFGD() {
        return vnffgd;
    }

    /**
     * Imposta il valore della proprietà vnffgd.
     * 
     * @param value
     *     allowed object is
     *     {@link VNFFGD }
     *     
     */
    public void setVNFFGD(VNFFGD value) {
        this.vnffgd = value;
    }

    /**
     * Recupera il valore della proprietà pnf.
     * 
     * @return
     *     possible object is
     *     {@link PNF }
     *     
     */
    public PNF getPNF() {
        return pnf;
    }

    /**
     * Imposta il valore della proprietà pnf.
     * 
     * @param value
     *     allowed object is
     *     {@link PNF }
     *     
     */
    public void setPNF(PNF value) {
        this.pnf = value;
    }

    /**
     * Recupera il valore della proprietà flavours.
     * 
     * @return
     *     possible object is
     *     {@link Flavours }
     *     
     */
    public Flavours getFlavours() {
        return flavours;
    }

    /**
     * Imposta il valore della proprietà flavours.
     * 
     * @param value
     *     allowed object is
     *     {@link Flavours }
     *     
     */
    public void setFlavours(Flavours value) {
        this.flavours = value;
    }

    /**
     * Recupera il valore della proprietà connectionPoints.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionPoints }
     *     
     */
    public ConnectionPoints getConnectionPoints() {
        return connectionPoints;
    }

    /**
     * Imposta il valore della proprietà connectionPoints.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionPoints }
     *     
     */
    public void setConnectionPoints(ConnectionPoints value) {
        this.connectionPoints = value;
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
