
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Computational_propertiesType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Computational_propertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="cpu" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="cores" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nOfOperations" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Computational_propertiesType")
public class ComputationalPropertiesType {

    @XmlAttribute(name = "cpu", required = true)
    protected int cpu;
    @XmlAttribute(name = "cores", required = true)
    protected int cores;
    @XmlAttribute(name = "nOfOperations")
    protected Integer nOfOperations;

    /**
     * Recupera il valore della proprietà cpu.
     * 
     */
    public int getCpu() {
        return cpu;
    }

    /**
     * Imposta il valore della proprietà cpu.
     * 
     */
    public void setCpu(int value) {
        this.cpu = value;
    }

    /**
     * Recupera il valore della proprietà cores.
     * 
     */
    public int getCores() {
        return cores;
    }

    /**
     * Imposta il valore della proprietà cores.
     * 
     */
    public void setCores(int value) {
        this.cores = value;
    }

    /**
     * Recupera il valore della proprietà nOfOperations.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNOfOperations() {
        return nOfOperations;
    }

    /**
     * Imposta il valore della proprietà nOfOperations.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNOfOperations(Integer value) {
        this.nOfOperations = value;
    }

}
