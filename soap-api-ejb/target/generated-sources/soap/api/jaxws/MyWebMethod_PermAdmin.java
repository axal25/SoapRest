
package soap.api.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "myWebMethod_PermAdmin", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myWebMethod_PermAdmin", namespace = "http://api.soap/")
public class MyWebMethod_PermAdmin {

    @XmlElement(name = "my_soap_param", namespace = "")
    private String mySoapParam;

    /**
     * 
     * @return
     *     returns String
     */
    public String getMySoapParam() {
        return this.mySoapParam;
    }

    /**
     * 
     * @param mySoapParam
     *     the value for the mySoapParam property
     */
    public void setMySoapParam(String mySoapParam) {
        this.mySoapParam = mySoapParam;
    }

}
