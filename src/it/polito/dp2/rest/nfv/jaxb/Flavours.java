
package it.polito.dp2.rest.nfv.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="totalPages" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="next" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element ref="{}Service_deployment_flavour" maxOccurs="unbounded" minOccurs="0"/>
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
    "totalPages",
    "page",
    "next",
    "serviceDeploymentFlavour"
})
@XmlRootElement(name = "flavours")
public class Flavours {

    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger totalPages;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger page;
    @XmlSchemaType(name = "anyURI")
    protected String next;
    @XmlElement(name = "Service_deployment_flavour")
    protected List<ServiceDeploymentFlavour> serviceDeploymentFlavour;

    /**
     * Recupera il valore della proprietà totalPages.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalPages() {
        return totalPages;
    }

    /**
     * Imposta il valore della proprietà totalPages.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalPages(BigInteger value) {
        this.totalPages = value;
    }

    /**
     * Recupera il valore della proprietà page.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPage() {
        return page;
    }

    /**
     * Imposta il valore della proprietà page.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPage(BigInteger value) {
        this.page = value;
    }

    /**
     * Recupera il valore della proprietà next.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNext() {
        return next;
    }

    /**
     * Imposta il valore della proprietà next.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNext(String value) {
        this.next = value;
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

}
