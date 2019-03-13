
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
 *         &lt;element ref="{}Hosts"/>
 *         &lt;element ref="{}Connections" minOccurs="0"/>
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
    "hosts",
    "connections"
})
@XmlRootElement(name = "PNI")
public class PNI {

    @XmlElement(name = "Hosts", required = true)
    protected Hosts hosts;
    @XmlElement(name = "Connections")
    protected Connections connections;

    /**
     * Recupera il valore della proprietà hosts.
     * 
     * @return
     *     possible object is
     *     {@link Hosts }
     *     
     */
    public Hosts getHosts() {
        return hosts;
    }

    /**
     * Imposta il valore della proprietà hosts.
     * 
     * @param value
     *     allowed object is
     *     {@link Hosts }
     *     
     */
    public void setHosts(Hosts value) {
        this.hosts = value;
    }

    /**
     * Recupera il valore della proprietà connections.
     * 
     * @return
     *     possible object is
     *     {@link Connections }
     *     
     */
    public Connections getConnections() {
        return connections;
    }

    /**
     * Imposta il valore della proprietà connections.
     * 
     * @param value
     *     allowed object is
     *     {@link Connections }
     *     
     */
    public void setConnections(Connections value) {
        this.connections = value;
    }

}
