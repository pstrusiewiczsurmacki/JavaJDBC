//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.11 at 01:09:16 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Director complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Director">
 *   &lt;complexContent>
 *     &lt;extension base="{}Employee">
 *       &lt;sequence>
 *         &lt;element name="allowance" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="card" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costLimit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Director", propOrder = {
    "allowance",
    "card",
    "costLimit"
})
public class Director
    extends Employee
{

    protected int allowance;
    @XmlElement(required = true)
    protected String card;
    protected int costLimit;

    /**
     * Gets the value of the allowance property.
     * 
     */
    public int getAllowance() {
        return allowance;
    }

    /**
     * Sets the value of the allowance property.
     * 
     */
    public void setAllowance(int value) {
        this.allowance = value;
    }

    /**
     * Gets the value of the card property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCard() {
        return card;
    }

    /**
     * Sets the value of the card property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCard(String value) {
        this.card = value;
    }

    /**
     * Gets the value of the costLimit property.
     * 
     */
    public int getCostLimit() {
        return costLimit;
    }

    /**
     * Sets the value of the costLimit property.
     * 
     */
    public void setCostLimit(int value) {
        this.costLimit = value;
    }

}