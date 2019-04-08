
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
 *         &lt;element name="qos" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="latency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="jitter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="src" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dst" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="test_access" use="required" type="{}test_accessType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qos"
})
@XmlRootElement(name = "Virtual_Link")
public class VirtualLink {

    protected List<VirtualLink.Qos> qos;
    @XmlAttribute(name = "src", required = true)
    protected String src;
    @XmlAttribute(name = "dst", required = true)
    protected String dst;
    @XmlAttribute(name = "test_access", required = true)
    protected TestAccessType testAccess;

    /**
     * Gets the value of the qos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VirtualLink.Qos }
     * 
     * 
     */
    public List<VirtualLink.Qos> getQos() {
        if (qos == null) {
            qos = new ArrayList<VirtualLink.Qos>();
        }
        return this.qos;
    }

    /**
     * Recupera il valore della proprietà src.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrc() {
        return src;
    }

    /**
     * Imposta il valore della proprietà src.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Recupera il valore della proprietà dst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDst() {
        return dst;
    }

    /**
     * Imposta il valore della proprietà dst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDst(String value) {
        this.dst = value;
    }

    /**
     * Recupera il valore della proprietà testAccess.
     * 
     * @return
     *     possible object is
     *     {@link TestAccessType }
     *     
     */
    public TestAccessType getTestAccess() {
        return testAccess;
    }

    /**
     * Imposta il valore della proprietà testAccess.
     * 
     * @param value
     *     allowed object is
     *     {@link TestAccessType }
     *     
     */
    public void setTestAccess(TestAccessType value) {
        this.testAccess = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="latency" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="jitter" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "latency",
        "jitter"
    })
    public static class Qos {

        protected Integer latency;
        protected Integer jitter;

        /**
         * Recupera il valore della proprietà latency.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getLatency() {
            return latency;
        }

        /**
         * Imposta il valore della proprietà latency.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setLatency(Integer value) {
            this.latency = value;
        }

        /**
         * Recupera il valore della proprietà jitter.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getJitter() {
            return jitter;
        }

        /**
         * Imposta il valore della proprietà jitter.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setJitter(Integer value) {
            this.jitter = value;
        }

    }

}
