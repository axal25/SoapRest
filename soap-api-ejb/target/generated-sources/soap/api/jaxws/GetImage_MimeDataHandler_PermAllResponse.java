
package soap.api.jaxws;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getImage_MimeDataHandler_PermAllResponse", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImage_MimeDataHandler_PermAllResponse", namespace = "http://api.soap/")
public class GetImage_MimeDataHandler_PermAllResponse {

    @XmlMimeType("application/octet-stream")
    @XmlElement(name = "return", namespace = "")
    private DataHandler _return;

    /**
     * 
     * @return
     *     returns DataHandler
     */
    public DataHandler getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(DataHandler _return) {
        this._return = _return;
    }

}
