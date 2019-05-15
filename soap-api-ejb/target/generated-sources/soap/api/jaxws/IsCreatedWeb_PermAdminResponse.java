
package soap.api.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "isCreatedWeb_PermAdminResponse", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isCreatedWeb_PermAdminResponse", namespace = "http://api.soap/")
public class IsCreatedWeb_PermAdminResponse {

    @XmlElement(name = "isCreatedResult", namespace = "")
    private boolean isCreatedResult;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isIsCreatedResult() {
        return this.isCreatedResult;
    }

    /**
     * 
     * @param isCreatedResult
     *     the value for the isCreatedResult property
     */
    public void setIsCreatedResult(boolean isCreatedResult) {
        this.isCreatedResult = isCreatedResult;
    }

}
