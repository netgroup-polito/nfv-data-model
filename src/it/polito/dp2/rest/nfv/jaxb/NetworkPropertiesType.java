
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Network_propertiesType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Network_propertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="bandwidth" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Network_propertiesType")
public class NetworkPropertiesType {

    @XmlAttribute(name = "bandwidth")
    protected Integer bandwidth;

    /**
     * Recupera il valore della proprietà bandwidth.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBandwidth() {
        return bandwidth;
    }

    /**
     * Imposta il valore della proprietà bandwidth.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBandwidth(Integer value) {
        this.bandwidth = value;
    }

}
