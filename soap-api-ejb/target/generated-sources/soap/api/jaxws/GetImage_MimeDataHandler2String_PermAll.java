
package soap.api.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getImage_MimeDataHandler2String_PermAll", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImage_MimeDataHandler2String_PermAll", namespace = "http://api.soap/")
public class GetImage_MimeDataHandler2String_PermAll {

    @XmlElement(name = "imageName", namespace = "")
    private String imageName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getImageName() {
        return this.imageName;
    }

    /**
     * 
     * @param imageName
     *     the value for the imageName property
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
