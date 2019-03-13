
package it.polito.dp2.rest.nfv.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Memory_propertiesType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Memory_propertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="disk_storage" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="memory" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="virtual_mem_res" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Memory_propertiesType")
public class MemoryPropertiesType {

    @XmlAttribute(name = "disk_storage", required = true)
    protected int diskStorage;
    @XmlAttribute(name = "memory", required = true)
    protected int memory;
    @XmlAttribute(name = "virtual_mem_res")
    protected Integer virtualMemRes;

    /**
     * Recupera il valore della proprietà diskStorage.
     * 
     */
    public int getDiskStorage() {
        return diskStorage;
    }

    /**
     * Imposta il valore della proprietà diskStorage.
     * 
     */
    public void setDiskStorage(int value) {
        this.diskStorage = value;
    }

    /**
     * Recupera il valore della proprietà memory.
     * 
     */
    public int getMemory() {
        return memory;
    }

    /**
     * Imposta il valore della proprietà memory.
     * 
     */
    public void setMemory(int value) {
        this.memory = value;
    }

    /**
     * Recupera il valore della proprietà virtualMemRes.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVirtualMemRes() {
        return virtualMemRes;
    }

    /**
     * Imposta il valore della proprietà virtualMemRes.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVirtualMemRes(Integer value) {
        this.virtualMemRes = value;
    }

}
