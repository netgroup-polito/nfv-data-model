
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="flavour_key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="flavour_value" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Service_deployment_flavour")
public class ServiceDeploymentFlavour {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "flavour_key")
    protected String flavourKey;
    @XmlAttribute(name = "flavour_value")
    protected Integer flavourValue;

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
     * Recupera il valore della proprietà flavourKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlavourKey() {
        return flavourKey;
    }

    /**
     * Imposta il valore della proprietà flavourKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlavourKey(String value) {
        this.flavourKey = value;
    }

    /**
     * Recupera il valore della proprietà flavourValue.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlavourValue() {
        return flavourValue;
    }

    /**
     * Imposta il valore della proprietà flavourValue.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlavourValue(Integer value) {
        this.flavourValue = value;
    }

}
