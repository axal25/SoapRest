
package soap.api.wsconsume.autogen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for isCreatedWeb_PermAdminResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isCreatedWeb_PermAdminResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isCreatedResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isCreatedWeb_PermAdminResponse", propOrder = {
    "isCreatedResult"
})
public class IsCreatedWebPermAdminResponse {

    protected boolean isCreatedResult;

    /**
     * Gets the value of the isCreatedResult property.
     * 
     */
    public boolean isIsCreatedResult() {
        return isCreatedResult;
    }

    /**
     * Sets the value of the isCreatedResult property.
     * 
     */
    public void setIsCreatedResult(boolean value) {
        this.isCreatedResult = value;
    }

}
