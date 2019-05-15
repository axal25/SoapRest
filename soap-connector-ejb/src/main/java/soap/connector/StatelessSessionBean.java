package soap.connector;



import javax.activation.DataHandler;
import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import soap.api.SingletonSessionBean;
import org.jboss.ws.api.annotation.WebContext;
import org.jboss.annotation.security.SecurityDomain;
import org.junit.Before;
import soap.connector.client.TestSoapClient;

import javax.annotation.security.PermitAll;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.awt.Image;

@DependsOn(value = {"SingletonSessionEJB"}) // Not the same JAR?
@Stateless(name = "StatelessSessionEJB")
@WebService(name = "ConnectorWebServiceEJB_Name", serviceName = "ConnectorWebServiceEJB_ServiceName", portName = "ConnectorWebServiceEJB_PortName")
@WebContext(authMethod = "BASIC", transportGuarantee = "NONE", secureWSDLAccess = false,
        contextRoot = "/soap-connector-ejb", urlPattern = "/StatelessSessionBeanWebService")
@SecurityDomain("SoapLab1-connector")
// @BindingType(value="http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true")
public class StatelessSessionBean {
    private javax.ejb.embeddable.EJBContainer ejbContainer = null;
    private javax.naming.Context namingContext = null;
    private SingletonSessionBean source = null;

    private static final String className = "StatelessSessionBean";

    /** Connected  to statefull annotation in SingletonSessionBean.java (for time being it's disabled)

     java:global/SoapLab1-ear/SoapLab1-api/SingletonSessionEJB!SingletonSessionBean
     java:app/SoapLab1-api/SingletonSessionEJB!SingletonSessionBean
     java:module/SingletonSessionEJB!SingletonSessionBean
     ejb:SoapLab1-ear/SoapLab1-api/SingletonSessionEJB!SingletonSessionBean
     java:global/SoapLab1-ear/SoapLab1-api/SingletonSessionEJB
     java:app/SoapLab1-api/SingletonSessionEJB
     java:module/SingletonSessionEJB
    **/

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /*********************************************** CONSTRUCTOR(S) ***************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public StatelessSessionBean() {
        System.out.println("\n\n\n \t\t\t\t " + className + " - Constructor called ");
        final String functionName = "StatelessSessionBean() [constructor]";
        boolean wasSingletonCreated = TestSoapClient.wasSingletonCreated();
        try {
            if (wasSingletonCreated == false) {
                throw new Exception( className + " >>> " + functionName + " --> wasSingletonCreated()" +
                        " --> SingletonSessionBean.isCreatedWeb_PermAdmin == false;");
            }
        }
        catch( Exception e ) {
            System.out.println("\t\t\t\t " + className + " >>> " + functionName + " --> wasSingletonCreated(): \n\r" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getMessage() + "\n\r  " +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getCause() + "\n\r " +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getClass() + "\n\r \n\r");
        }
        System.out.println("\n\n\n \t\t\t\t " + className + " - Constructor ended \n\n\n");
    }

    @Before
    @WebMethod(exclude = true)
    public void initialiazeBefore() {
        System.out.println("<initialiazeBefore>");
        ejbContainer = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        namingContext = ejbContainer.getContext();
        System.out.println("</initialiazeBefore>");
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************* WebMethods (without IMAGE) *******************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    @WebMethod(operationName = "DEFAULT_use_myWebMethod_PermAdmin_FromApi")
    @PermitAll
    public String use_myWebMethod_PermAdmin_FromApi() {
        final String functionName = "use_myWebMethod_PermAdmin_FromApi";
        String mySoapParam = "mySoapParam !!!!!!!! From INSIDE OF " + className + " >>> " + functionName + " !!!!!!!!";
        String username = "admin";
        String password = "admin";

        return use_myWebMethod_PermAdmin_FromApi( username, password, mySoapParam );
    }

    @WebMethod
    @PermitAll
    public String use_myWebMethod_PermAdmin_FromApi( @WebParam(name = "username") String username,
                                           @WebParam(name = "password") String password,
                                           @WebParam(name = "mySoapParam") String mySoapParam  ) {
        return TestSoapClient.use_myWebMethod_PermAdmin_FromApi( username, password, mySoapParam );
    }


    @XmlElementWrapper(name = "listOfStudents")
    @XmlElement(name = "aStudent")
    @WebMethod
    @PermitAll
    public java.util.List<String> use_getListOfStudents_PermUser_FromApi() {
        return TestSoapClient.use_getListOfStudents_PermUser_FromApi();
    }

    @XmlElement(name = "stringOfStudents")
    @WebMethod
    @PermitAll
    public String use_getListOfStudents_PermUser_FromApi_AsString() {
        return TestSoapClient.use_getListOfStudents_PermUser_FromApi_AsString();
    }

    @WebMethod
    @PermitAll
    public String use_addElementToListOfStudents_PermUser_FromApi( @WebParam(name = "ElementToAdd") String elementToAdd ) {
        return TestSoapClient.use_addElementToListOfStudents_PermUser_FromApi( elementToAdd );
    }

    @WebMethod
    @PermitAll
    public String use_removeElementFromListOfStudennts_PermAll_FromApi( @WebParam(name = "ElementToRemove") String elementToRemove ) {
        return TestSoapClient.use_removeElementFromListOfStudennts_PermAll_FromApi( elementToRemove );
    }

    @WebMethod
    @PermitAll
    public java.util.List use_getListOfStudentsFilteredByName_PermAll_FromApi( @WebParam(name = "NameToFilterBy") String name ) {
        return TestSoapClient.use_getListOfStudentsFilteredByName_PermAll_FromApi( name );
    }

    @WebMethod
    @PermitAll
    public String use_getListOfStudentsFilteredByName_PermAll_FromApi_AsString( @WebParam(name = "NameToFilterBy") String name ) {
        return TestSoapClient.use_getListOfStudentsFilteredByName_PermAll_FromApi_AsString( name );
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /*********************************************** IMAGE WebMethods *************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    @WebMethod
    @PermitAll
    @WebResult(name = "returnedDataHandlerOfImage")
    public DataHandler use_getImageMimeDataHandler_PermAll_FromApi( @WebParam(name = "imageName") String imageName ) {
        return TestSoapClient.use_getImageMimeDataHandler_PermAll_FromApi( imageName );
    }


    @WebMethod
    @PermitAll
    @WebResult(name = "returnedInfoOnSavingFile")
    public String saveFile( @WebParam(name = "imageName") String imageName ) {
        return TestSoapClient.saveFile( imageName );
    }

    @WebMethod
    @PermitAll
    @WebResult(name = "returnedJavaAwtImage")
    public Image getImage( @WebParam(name = "imageName") String imageName ) {
        return TestSoapClient.getImage( imageName );
    }

    @WebMethod
    @PermitAll
    @WebResult(name = "returnedDataHandlerToByteArrayToEncodedString")
    public String use_getImageMimeDataHandler2StringPermAll_FromApi( @WebParam(name = "imageName") String imageName ) {
        return TestSoapClient.use_getImageMimeDataHandler2StringPermAll_FromApi( imageName );
    }

    @WebMethod
    @PermitAll
    @WebResult(name = "returnedResponseAfterDecodingAndSavingImage")
    public String getEncodedByteArrayInStringDecodeAndSave( @WebParam(name = "imageName") String imageName ) {
        return TestSoapClient.getEncodedByteArrayInStringDecodeAndSave( imageName );
    }
}
