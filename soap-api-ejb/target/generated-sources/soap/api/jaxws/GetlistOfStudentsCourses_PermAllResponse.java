
package soap.api.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import model.soap.Student;

@XmlRootElement(name = "getlistOfStudentsCourses_PermAllResponse", namespace = "http://api.soap/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getlistOfStudentsCourses_PermAllResponse", namespace = "http://api.soap/")
public class GetlistOfStudentsCourses_PermAllResponse {

    @XmlElement(name = "return", namespace = "")
    private List<Student> _return;

    /**
     * 
     * @return
     *     returns List<Student>
     */
    public List<Student> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<Student> _return) {
        this._return = _return;
    }

}
