
package jaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}NSD" maxOccurs="unbounded" minOccurs="0"/>
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
    "nsd"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(name = "PNI", required = true)
    protected PNI pni;
    @XmlElement(name = "NSD")
    protected List<NSD> nsd;

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
     * Gets the value of the nsd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nsd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNSD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NSD }
     * 
     * 
     */
    public List<NSD> getNSD() {
        if (nsd == null) {
            nsd = new ArrayList<NSD>();
        }
        return this.nsd;
    }

}
