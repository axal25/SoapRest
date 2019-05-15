
package soap.api.wsimport.autogen;

import java.awt.Image;
import java.util.List;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceEJB_Name", targetNamespace = "http://api.soap/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceEJBName {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "removeElementFromListOfStudents_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeElementFromListOfStudents_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.RemoveElementFromListOfStudentsPermAll")
    @ResponseWrapper(localName = "removeElementFromListOfStudents_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.RemoveElementFromListOfStudentsPermAllResponse")
    public String removeElementFromListOfStudentsPermAll(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param imageName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "getImage_AsImage2String_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_AsImage2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImage2StringPermAll")
    @ResponseWrapper(localName = "getImage_AsImage2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImage2StringPermAllResponse")
    public String getImageAsImage2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @param imageName
     * @return
     *     returns byte[]
     */
    @WebMethod(operationName = "getImage_AsImage_Binary_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_AsImage_Binary_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImageBinaryPermAll")
    @ResponseWrapper(localName = "getImage_AsImage_Binary_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImageBinaryPermAllResponse")
    public byte[] getImageAsImageBinaryPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @param imageName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "getImage_AsByteArray2String_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_AsByteArray2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsByteArray2StringPermAll")
    @ResponseWrapper(localName = "getImage_AsByteArray2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsByteArray2StringPermAllResponse")
    public String getImageAsByteArray2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @param imageName
     * @return
     *     returns javax.activation.DataHandler
     */
    @WebMethod(operationName = "getImage_MimeDataHandler_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_MimeDataHandler_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageMimeDataHandlerPermAll")
    @ResponseWrapper(localName = "getImage_MimeDataHandler_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageMimeDataHandlerPermAllResponse")
    public DataHandler getImageMimeDataHandlerPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @return
     *     returns java.util.List<soap.api.wsimport.autogen.Student>
     */
    @WebMethod(operationName = "getlistOfStudentsCourses_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getlistOfStudentsCourses_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetlistOfStudentsCoursesPermAll")
    @ResponseWrapper(localName = "getlistOfStudentsCourses_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetlistOfStudentsCoursesPermAllResponse")
    public List<Student> getlistOfStudentsCoursesPermAll();

    /**
     * 
     * @param mySoapParam
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "myWebMethod_PermAdmin")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "myWebMethod_PermAdmin", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.MyWebMethodPermAdmin")
    @ResponseWrapper(localName = "myWebMethod_PermAdminResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.MyWebMethodPermAdminResponse")
    public String myWebMethodPermAdmin(
        @WebParam(name = "my_soap_param", targetNamespace = "")
        String mySoapParam);

    /**
     * 
     * @param imageName
     * @return
     *     returns java.awt.Image
     */
    @WebMethod(operationName = "getImage_AsImage_MimeJpg_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_AsImage_MimeJpg_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImageMimeJpgPermAll")
    @ResponseWrapper(localName = "getImage_AsImage_MimeJpg_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsImageMimeJpgPermAllResponse")
    public Image getImageAsImageMimeJpgPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @param imageName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "getImage_MimeDataHandler2String_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_MimeDataHandler2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageMimeDataHandler2StringPermAll")
    @ResponseWrapper(localName = "getImage_MimeDataHandler2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageMimeDataHandler2StringPermAllResponse")
    public String getImageMimeDataHandler2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "isCreatedWeb_PermAdmin")
    @WebResult(name = "isCreatedResult", targetNamespace = "")
    @RequestWrapper(localName = "isCreatedWeb_PermAdmin", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.IsCreatedWebPermAdmin")
    @ResponseWrapper(localName = "isCreatedWeb_PermAdminResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.IsCreatedWebPermAdminResponse")
    public boolean isCreatedWebPermAdmin();

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod(operationName = "getWrappedListOfStudents_PermSoapLab1")
    @WebResult(name = "Element", targetNamespace = "")
    @RequestWrapper(localName = "getWrappedListOfStudents_PermSoapLab1", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetWrappedListOfStudentsPermSoapLab1")
    @ResponseWrapper(localName = "getWrappedListOfStudents_PermSoapLab1Response", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetWrappedListOfStudentsPermSoapLab1Response")
    public List<String> getWrappedListOfStudentsPermSoapLab1();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "addElementToListOfStudents_PermUser")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addElementToListOfStudents_PermUser", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.AddElementToListOfStudentsPermUser")
    @ResponseWrapper(localName = "addElementToListOfStudents_PermUserResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.AddElementToListOfStudentsPermUserResponse")
    public String addElementToListOfStudentsPermUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param nameToFilterBy
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod(operationName = "getListOfStudentsFilteredByName_PermAll")
    @WebResult(name = "filteredElement", targetNamespace = "")
    @RequestWrapper(localName = "getListOfStudentsFilteredByName_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetListOfStudentsFilteredByNamePermAll")
    @ResponseWrapper(localName = "getListOfStudentsFilteredByName_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetListOfStudentsFilteredByNamePermAllResponse")
    public List<String> getListOfStudentsFilteredByNamePermAll(
        @WebParam(name = "nameToFilterBy", targetNamespace = "")
        String nameToFilterBy);

    /**
     * 
     * @param imageName
     * @return
     *     returns byte[]
     */
    @WebMethod(operationName = "getImage_AsByteArray_PermAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getImage_AsByteArray_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsByteArrayPermAll")
    @ResponseWrapper(localName = "getImage_AsByteArray_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetImageAsByteArrayPermAllResponse")
    public byte[] getImageAsByteArrayPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        String imageName);

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod(operationName = "getListOfStudents_PermUser")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getListOfStudents_PermUser", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetListOfStudentsPermUser")
    @ResponseWrapper(localName = "getListOfStudents_PermUserResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsimport.autogen.GetListOfStudentsPermUserResponse")
    public List<String> getListOfStudentsPermUser();

}
