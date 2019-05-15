
package soap.api.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getListOfStudentsFilteredByName_PermAll", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getListOfStudentsFilteredByName_PermAll", namespace = "http://api.soap/")
public class GetListOfStudentsFilteredByName_PermAll {

    @XmlElement(name = "nameToFilterBy", namespace = "")
    private String nameToFilterBy;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNameToFilterBy() {
        return this.nameToFilterBy;
    }

    /**
     * 
     * @param nameToFilterBy
     *     the value for the nameToFilterBy property
     */
    public void setNameToFilterBy(String nameToFilterBy) {
        this.nameToFilterBy = nameToFilterBy;
    }

}
