
package soap.api.wsimport.autogen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getListOfStudentsFilteredByName_PermAll complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getListOfStudentsFilteredByName_PermAll">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nameToFilterBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getListOfStudentsFilteredByName_PermAll", propOrder = {
    "nameToFilterBy"
})
public class GetListOfStudentsFilteredByNamePermAll {

    protected String nameToFilterBy;

    /**
     * Gets the value of the nameToFilterBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameToFilterBy() {
        return nameToFilterBy;
    }

    /**
     * Sets the value of the nameToFilterBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameToFilterBy(String value) {
        this.nameToFilterBy = value;
    }

}
