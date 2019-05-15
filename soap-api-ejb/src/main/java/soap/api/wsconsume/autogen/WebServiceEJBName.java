package soap.api.wsconsume.autogen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.6
 * 2019-05-15T19:54:50.342+02:00
 * Generated source version: 3.2.6
 *
 */
@WebService(targetNamespace = "http://api.soap/", name = "WebServiceEJB_Name")
@XmlSeeAlso({ObjectFactory.class})
public interface WebServiceEJBName {

    @WebMethod(operationName = "removeElementFromListOfStudents_PermAll")
    @RequestWrapper(localName = "removeElementFromListOfStudents_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.RemoveElementFromListOfStudentsPermAll")
    @ResponseWrapper(localName = "removeElementFromListOfStudents_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.RemoveElementFromListOfStudentsPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String removeElementFromListOfStudentsPermAll(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod(operationName = "getImage_AsImage2String_PermAll")
    @RequestWrapper(localName = "getImage_AsImage2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImage2StringPermAll")
    @ResponseWrapper(localName = "getImage_AsImage2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImage2StringPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getImageAsImage2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getImage_AsImage_Binary_PermAll")
    @RequestWrapper(localName = "getImage_AsImage_Binary_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImageBinaryPermAll")
    @ResponseWrapper(localName = "getImage_AsImage_Binary_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImageBinaryPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public byte[] getImageAsImageBinaryPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getImage_AsByteArray2String_PermAll")
    @RequestWrapper(localName = "getImage_AsByteArray2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsByteArray2StringPermAll")
    @ResponseWrapper(localName = "getImage_AsByteArray2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsByteArray2StringPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getImageAsByteArray2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getImage_MimeDataHandler_PermAll")
    @RequestWrapper(localName = "getImage_MimeDataHandler_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageMimeDataHandlerPermAll")
    @ResponseWrapper(localName = "getImage_MimeDataHandler_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageMimeDataHandlerPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public javax.activation.DataHandler getImageMimeDataHandlerPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getlistOfStudentsCourses_PermAll")
    @RequestWrapper(localName = "getlistOfStudentsCourses_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetlistOfStudentsCoursesPermAll")
    @ResponseWrapper(localName = "getlistOfStudentsCourses_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetlistOfStudentsCoursesPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<soap.api.wsconsume.autogen.Student> getlistOfStudentsCoursesPermAll();

    @WebMethod(operationName = "myWebMethod_PermAdmin")
    @RequestWrapper(localName = "myWebMethod_PermAdmin", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.MyWebMethodPermAdmin")
    @ResponseWrapper(localName = "myWebMethod_PermAdminResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.MyWebMethodPermAdminResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String myWebMethodPermAdmin(
        @WebParam(name = "my_soap_param", targetNamespace = "")
        java.lang.String mySoapParam
    );

    @WebMethod(operationName = "getImage_AsImage_MimeJpg_PermAll")
    @RequestWrapper(localName = "getImage_AsImage_MimeJpg_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImageMimeJpgPermAll")
    @ResponseWrapper(localName = "getImage_AsImage_MimeJpg_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsImageMimeJpgPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.awt.Image getImageAsImageMimeJpgPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getImage_MimeDataHandler2String_PermAll")
    @RequestWrapper(localName = "getImage_MimeDataHandler2String_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageMimeDataHandler2StringPermAll")
    @ResponseWrapper(localName = "getImage_MimeDataHandler2String_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageMimeDataHandler2StringPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String getImageMimeDataHandler2StringPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "isCreatedWeb_PermAdmin")
    @RequestWrapper(localName = "isCreatedWeb_PermAdmin", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.IsCreatedWebPermAdmin")
    @ResponseWrapper(localName = "isCreatedWeb_PermAdminResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.IsCreatedWebPermAdminResponse")
    @WebResult(name = "isCreatedResult", targetNamespace = "")
    public boolean isCreatedWebPermAdmin();

    @WebMethod(operationName = "getWrappedListOfStudents_PermSoapLab1")
    @RequestWrapper(localName = "getWrappedListOfStudents_PermSoapLab1", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetWrappedListOfStudentsPermSoapLab1")
    @ResponseWrapper(localName = "getWrappedListOfStudents_PermSoapLab1Response", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetWrappedListOfStudentsPermSoapLab1Response")
    @WebResult(name = "Element", targetNamespace = "")
    public java.util.List<java.lang.String> getWrappedListOfStudentsPermSoapLab1();

    @WebMethod(operationName = "addElementToListOfStudents_PermUser")
    @RequestWrapper(localName = "addElementToListOfStudents_PermUser", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.AddElementToListOfStudentsPermUser")
    @ResponseWrapper(localName = "addElementToListOfStudents_PermUserResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.AddElementToListOfStudentsPermUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String addElementToListOfStudentsPermUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod(operationName = "getListOfStudentsFilteredByName_PermAll")
    @RequestWrapper(localName = "getListOfStudentsFilteredByName_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetListOfStudentsFilteredByNamePermAll")
    @ResponseWrapper(localName = "getListOfStudentsFilteredByName_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetListOfStudentsFilteredByNamePermAllResponse")
    @WebResult(name = "filteredElement", targetNamespace = "")
    public java.util.List<java.lang.String> getListOfStudentsFilteredByNamePermAll(
        @WebParam(name = "nameToFilterBy", targetNamespace = "")
        java.lang.String nameToFilterBy
    );

    @WebMethod(operationName = "getImage_AsByteArray_PermAll")
    @RequestWrapper(localName = "getImage_AsByteArray_PermAll", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsByteArrayPermAll")
    @ResponseWrapper(localName = "getImage_AsByteArray_PermAllResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetImageAsByteArrayPermAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public byte[] getImageAsByteArrayPermAll(
        @WebParam(name = "imageName", targetNamespace = "")
        java.lang.String imageName
    );

    @WebMethod(operationName = "getListOfStudents_PermUser")
    @RequestWrapper(localName = "getListOfStudents_PermUser", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetListOfStudentsPermUser")
    @ResponseWrapper(localName = "getListOfStudents_PermUserResponse", targetNamespace = "http://api.soap/", className = "soap.api.wsconsume.autogen.GetListOfStudentsPermUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<java.lang.String> getListOfStudentsPermUser();
}
