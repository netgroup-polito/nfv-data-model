
package it.polito.dp2.rest.nfv.jaxb;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element ref="{}elements" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="defaultAction" type="{}ActionTypes" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "elements"
})
@XmlRootElement(name = "firewall")
public class Firewall {

    protected List<Elements> elements;
    @XmlAttribute(name = "defaultAction")
    protected ActionTypes defaultAction;

    /**
     * Gets the value of the elements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Elements }
     * 
     * 
     */
    public List<Elements> getElements() {
        if (elements == null) {
            elements = new ArrayList<Elements>();
        }
        return this.elements;
    }

    /**
     * Recupera il valore della proprietà defaultAction.
     * 
     * @return
     *     possible object is
     *     {@link ActionTypes }
     *     
     */
    public ActionTypes getDefaultAction() {
        return defaultAction;
    }

    /**
     * Imposta il valore della proprietà defaultAction.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionTypes }
     *     
     */
    public void setDefaultAction(ActionTypes value) {
        this.defaultAction = value;
    }

}
