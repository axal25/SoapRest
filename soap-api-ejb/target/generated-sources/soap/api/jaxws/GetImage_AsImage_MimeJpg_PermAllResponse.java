
package soap.api.jaxws;

import java.awt.Image;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getImage_AsImage_MimeJpg_PermAllResponse", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImage_AsImage_MimeJpg_PermAllResponse", namespace = "http://api.soap/")
public class GetImage_AsImage_MimeJpg_PermAllResponse {

    @XmlMimeType("image/jpeg")
    @XmlElement(name = "return", namespace = "")
    private Image _return;

    /**
     * 
     * @return
     *     returns Image
     */
    public Image getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Image _return) {
        this._return = _return;
    }

}
