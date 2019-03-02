
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
 *       &lt;attribute name="nodeRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Node_Connection")
public class NodeConnection {

    @XmlAttribute(name = "nodeRef", required = true)
    protected String nodeRef;

    /**
     * Recupera il valore della proprietà nodeRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeRef() {
        return nodeRef;
    }

    /**
     * Imposta il valore della proprietà nodeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeRef(String value) {
        this.nodeRef = value;
    }

}
