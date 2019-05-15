
package soap.api.wsimport.autogen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for myWebMethod_PermAdmin complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="myWebMethod_PermAdmin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="my_soap_param" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myWebMethod_PermAdmin", propOrder = {
    "mySoapParam"
})
public class MyWebMethodPermAdmin {

    @XmlElement(name = "my_soap_param")
    protected String mySoapParam;

    /**
     * Gets the value of the mySoapParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMySoapParam() {
        return mySoapParam;
    }

    /**
     * Sets the value of the mySoapParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMySoapParam(String value) {
        this.mySoapParam = value;
    }

}
