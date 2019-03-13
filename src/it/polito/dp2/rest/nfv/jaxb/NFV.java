
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}PNI"/>
 *         &lt;element ref="{}NS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pni",
    "ns"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(name = "PNI", required = true)
    protected PNI pni;
    @XmlElement(name = "NS")
    protected NS ns;

    /**
     * Recupera il valore della proprietà pni.
     * 
     * @return
     *     possible object is
     *     {@link PNI }
     *     
     */
    public PNI getPNI() {
        return pni;
    }

    /**
     * Imposta il valore della proprietà pni.
     * 
     * @param value
     *     allowed object is
     *     {@link PNI }
     *     
     */
    public void setPNI(PNI value) {
        this.pni = value;
    }

    /**
     * Recupera il valore della proprietà ns.
     * 
     * @return
     *     possible object is
     *     {@link NS }
     *     
     */
    public NS getNS() {
        return ns;
    }

    /**
     * Imposta il valore della proprietà ns.
     * 
     * @param value
     *     allowed object is
     *     {@link NS }
     *     
     */
    public void setNS(NS value) {
        this.ns = value;
    }

}
