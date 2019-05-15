package soap.api;

import model.soap.Student;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Singleton;
//import javax.ejb.Stateful;
import javax.ejb.Startup;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.URL;

import javax.activation.DataHandler;
// import com.sun.xml.ws.developer.StreamingDataHandler;


/** add to SoapLab1-api/pom.xml:
 ***********************************************************************************
 <dependency>
 <groupId>org.jboss.spec.javax.annotation</groupId>
 <artifactId>jboss-annotations-api_1.2_spec</artifactId>
 <version>1.0.0.Final-redhat-1</version>
 </dependency>
 ***********************************************************************************
 <dependency>
 <groupId>jboss</groupId>
 <artifactId>jboss-annotations-ejb3</artifactId>
 <version>4.2.2.GA</version>
 <scope>provided</scope>
 </dependency>
 ***********************************************************************************
 <dependency>
 <groupId>org.jboss.ws</groupId>
 <artifactId>jbossws-api</artifactId>
 <version>1.1.2.Final</version>
 </dependency>
 ***********************************************************************************
 how to find? - google.com:
 @securitydomain maven dependency
 */

/** To generate (wsimport) Classes based on WSDL open terminal and type
 *****************************************************************************************
 * wsimport -keep -p WsImportGen http://localhost:8080/SoapLab1-ejb/WebServiceEJB?wsdl   *
 * wsimport -keep -p WsImportGen SoapLab1-api/src/main/resources/wsdl/WebServiceEJB.wsdl *
 *****************************************************************************************
 **/

/** Or to do that automatically place in pom.xml fragmets marked as <!-- wsimport --> *
 **************************************************************************************
 * you can also save webpage as .wsdl file as use it as source                        *
 **************************************************************************************
 **/

/** To generate (wsconsume) Classes based on WSDL open terminal and type
 **********************************************************************************************************************************************************************************************
 * JAXWS 2.2 BUG with some JVMs (JDKs): https://bugzilla.redhat.com/show_bug.cgi?id=999223
 **********************************************************************************************************************************************************************************************
 * ~/wildfly-14.0.1.Final/bin/wsconsume.sh --target=2.1 --keep --package=WsConsumeGen --output=SoapLab1 --source=SoapLab1 "SoapLab1/SoapLab1-api/src/main/resources/wsdl/WebServiceEJB.wsdl"  *
 * wsconsume --target=2.1 --keep --package=WsConsumeGen --output=SoapLab1 --source=SoapLab1 "SoapLab1/SoapLab1-api/src/main/resources/wsdl/WebServiceEJB.wsdl"                                *
 * cd SoapLab1                                                                                                                                                                                *
 * wsconsume -t 2.1 -k -p "WsConsumeGen" -o "SoapLab1" -s "SoapLab1" "SoapLab1/SoapLab1-api/src/main/resources/wsdl/WebServiceEJB.wsdl"                                                       *
 * wsconsume -t 2.1 -k -p "WsConsumeGen" -o "./" -s "./" "SoapLab1-api/src/main/resources/wsdl/WebServiceEJB.wsdl"                                                                            *
 **********************************************************************************************************************************************************************************************
 **/

/** WARNING - LOGGING
 * log4j:WARN No appenders could be found for logger (org.apache.cxf.common.logging.LogUtils).
 * log4j:WARN Please initialize the log4j system properly.
 * log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
 * *********************************************************************************************************************
 * Ctrl + Alt + S > Build, Excecution, Deployment > Compiler > Java Compiler > Additional command line parameters:     *
 * -Dlog4j.configuration=/home/jackdaeel/IdeaProjects/MavenProjects/log4j.properties                            *
 ***********************************************************************************************************************
 * /home/jackdaeel/IdeaProjects/log4j.properties                                                                       *
 **************************************************************************************************************************************
 * Help > Edit Custom VM Options...                                                                                                   *
 * -Dlog4j.configuration=/home/jackdaeel/IdeaProjects/log4j.properties                                                                *
 ************************************************************************************************************************************************************************
 * Ctrl + Alt + S > Build, Excecution, Deployment > Build Tools > Maven > Runner > VM Options: -Dorg.apache.cxf.stax.allowInsecureParser=1                              *
 *                                                                                             -Dlog4j.configuration=/home/jackdaeel/IdeaProjects/log4j.properties      *
 ************************************************************************************************************************************************************************
 **/

/** To generate (wsgen) WSDL file open terminal and type
 *****************************************************************************************
 * wsgen -verbose -wsdl -classpath SoapLab1-api/target/classes/ -r SoapLab1-api/src/main/resources/wsdl/ SingletonSessionBean
 * THERE NO NEED TO DO THIS, WSDL FILE AUTOMATICALLY GENERATED @ SoapLab1-api/target/wsdl/WebServiceEJB.wsdl
 *****************************************************************************************
 **/

/**
 * @WebService handlers with @HandlerChain
 * http://tomee.apache.org/examples/webservice-handlerchain/README.html
 **/
// @Stateful(name = "StatefulSessionEJB")
@Startup
@Singleton(name = "SingletonSessionEJB", mappedName = "SingletonSessionMappedName", description = "SingletonSessionDescription")
@WebService(name = "WebServiceEJB_Name",
        serviceName = "WebServiceEJB_ServiceName",
        portName = "WebServiceEJB_PortName")
//, endpointInterface = "SingletonInterfaceBean")
@SecurityDomain("SoapLab1-connector")
@DeclareRoles({"SoapLab1"})
@WebContext(authMethod = "BASIC", transportGuarantee = "NONE", secureWSDLAccess = false,
        contextRoot = "/soap-api-ejb", urlPattern = "/SingletonSessionBeanWebService")
// @BindingType(value="http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true")
@PermitAll
public class SingletonSessionBean implements SingletonInterfaceBean {
    List<String> listOfStudents = null;
    boolean wasContructorCalled = false;
    List<Student> listOfStudentsCourses = null;

    public SingletonSessionBean() {
        if( wasContructorCalled == false ) {
            System.out.println("\n\n\n !!! SoapLab1 >>>>>>>>>>>>> \n\n\n");
            listOfStudents = new ArrayList<String>();       System.out.println("listOfStudents = new ArrayList<String>();");
            listOfStudents.add("Jan Nowak");                System.out.println("listOfStudents.add(\"Jan Nowak\");");
            listOfStudents.add("Jacek Kowalik");            System.out.println("listOfStudents.add(\"Jacek Kowalik\");");
            listOfStudents.add("Janek Kowalski");           System.out.println("listOfStudents.add(\"Janek Kowalski\");");
            listOfStudents.add("Will Smith");               System.out.println("listOfStudents.add(\"Will Smith\");");
            listOfStudents.add("Jaden Smith");              System.out.println("listOfStudents.add(\"Jaden Smith\");");
            listOfStudents.add("Genie");                    System.out.println("listOfStudents.add(\"Genie\");");
            listOfStudents.add("Banana");                   System.out.println("listOfStudents.add(\"Banana\");");
            listOfStudents.add("Potatoe");                  System.out.println("listOfStudents.add(\"Potatoe\");");
            listOfStudents.add("J. K. Rowling");            System.out.println("listOfStudents.add(\"J. K. Rowling\");");
            listOfStudents.add("Dobby");                    System.out.println("listOfStudents.add(\"Dobby\");");
            listOfStudents.add("Kylie Trashbag Jenner");    System.out.println("listOfStudents.add(\"Kylie Trashbag Jenner\");");
            listOfStudents.add("Bill OG Gates");            System.out.println("listOfStudents.add(\"Bill OG Gates\");");
            listOfStudents.add("Jacek Naworski");           System.out.println("listOfStudents.add(\"Jacek Naworski\");");
            listOfStudents.add("Jacek Tomczyk");            System.out.println("listOfStudents.add(\"Jacek Tomczyk\");");
            listOfStudents.add("Jacek Tomala");             System.out.println("listOfStudents.add(\"Jacek Tomala\");");
            listOfStudents.add("Jacek Michniuk");           System.out.println("listOfStudents.add(\"Jacek Michniuk\");");

            boolean isRemoved = listOfStudents.remove("Kylie Trashbag Jenner");     System.out.println("listOfStudents.remove(\"J. K. Rowling\");");
            String whatWasRemoved = listOfStudents.remove(10);               System.out.println("listOfStudents.remove(1); whatWasRemoved = " + whatWasRemoved);
            String cur_Student = (String) listOfStudents.get( 0 );             System.out.println("cur_Student = (String) listOfStudents.get( 0 );");


            listOfStudentsCourses = new ArrayList<Student>();
            for(int i=0; i<10; i++) {
                Student tmpStudent = new Student();
                tmpStudent.setName("name " + i);
                tmpStudent.setSurname("surname "+i);
                for(int j=0; j<10; j++) {
                    tmpStudent.addCourse( "Course " + i + ". " + j );
                }
                System.out.println("listOfStudentsCourses.add( " + tmpStudent.getCourses() + ")");
                listOfStudentsCourses.add( tmpStudent );
            }
            System.out.println("listOfStudentsCourses = " + listOfStudentsCourses);

            wasContructorCalled = true;

            System.out.println("SingletonSessionBean.SingletonSessionBean(): I have been created!");
            System.out.println("\n\n\n !!! SoapLab1 <<<<<<<<<<<<< \n\n\n");
        }
        else {
            System.out.println("\n\n\n wasConstructorCalled == true >>> do nothing \n\n\n");
        }
    }

    @PostConstruct
    private void initialiazePostConstuct() {
        if( wasContructorCalled == false  || listOfStudents == null || listOfStudents.isEmpty() || listOfStudents.size() == 0 ) {
            System.out.println("<initialiazePostConstuct>");
            if( wasContructorCalled == false ) {
                wasContructorCalled = true;
            }
            if( listOfStudents == null ) {
                listOfStudents = new ArrayList<String>();
            }
            if( listOfStudents.isEmpty() || listOfStudents.size() == 0 ) {
                listOfStudents.add("Jan Nowak - PostConstuct - PostConstuct");  System.out.println("listOfStudents.add(\"Jan Nowak - PostConstuct\");");
                listOfStudents.add("Jacek Kowalik - PostConstuct");             System.out.println("listOfStudents.add(\"Jacek Kowalik - PostConstuct\");");
                listOfStudents.add("Janek Kowalski - PostConstuct");            System.out.println("listOfStudents.add(\"Janek Kowalski - PostConstuct\");");
                listOfStudents.add("Will Smith - PostConstuct");                System.out.println("listOfStudents.add(\"Will Smith - PostConstuct\");");
                listOfStudents.add("Jaden Smith - PostConstuct");               System.out.println("listOfStudents.add(\"Jaden Smith - PostConstuct\");");
                listOfStudents.add("Genie - PostConstuct");                     System.out.println("listOfStudents.add(\"Genie - PostConstuct\");");
                listOfStudents.add("Banana - PostConstuct");                    System.out.println("listOfStudents.add(\"Banana - PostConstuct\");");
                listOfStudents.add("Potatoe - PostConstuct");                   System.out.println("listOfStudents.add(\"Potatoe - PostConstuct\");");
                listOfStudents.add("J. K. Rowling - PostConstuct");             System.out.println("listOfStudents.add(\"J. K. Rowling - PostConstuct\");");
                listOfStudents.add("Dobby - PostConstuct");                     System.out.println("listOfStudents.add(\"Dobby - PostConstuct\");");
                listOfStudents.add("Bill OG Gates - PostConstuct");             System.out.println("listOfStudents.add(\"Bill OG Gates - PostConstuct\");");
            }
            System.out.println("</initialiazePostConstuct>");
        }
    }

    @WebMethod
    @RolesAllowed(value = "admin")
    public String myWebMethod_PermAdmin( @WebParam(name = "my_soap_param") String my_web_param ) {
        // System.out.println("SingletonSessionBean.myWebMethod( my_web_param )");
        return "Your_soap_param - which is my_web_param is: " + my_web_param;
    }

    @WebMethod
    @RolesAllowed(value = "user")
    public List<String> getListOfStudents_PermUser() {
        if( wasContructorCalled == true ) {
            // System.out.println("\n\n\n wasConstructorCalled == true >>> listOfStudents.add(\"wasContructorCalled == \" + wasContructorCalled) \n\n\n");
            // listOfStudents.add("wasContructorCalled == " + wasContructorCalled);
        }
        else {
            System.out.println("\n\n\n wasConstructorCalled == false >>> ");
        }
        if( wasContructorCalled == false || listOfStudents == null || listOfStudents.isEmpty() || listOfStudents.size() == 0 ) {
            if( listOfStudents == null ) {
                System.out.println("listOfStudents == null ");
                listOfStudents = new ArrayList<String>();
            }
            System.out.println(">>> listOfStudents == null || listOfStudents.isEmpty() || listOfStudents.size() == 0 >>> Contructor didn't add elements \n\n\n");
            listOfStudents.add("Contructor didn't add elements");
        }

        return listOfStudents;
    }

    @WebMethod
    @RolesAllowed(value = "SoapLab1")
    @XmlElementWrapper(name = "ElementWrapper")
    @XmlElement(name = "Element")
    public List<String> getWrappedListOfStudents_PermSoapLab1() {
        return getListOfStudents_PermUser();
    }

    @WebMethod
    @RolesAllowed(value = "user")
    public String addElementToListOfStudents_PermUser( String newElement ) {
        if( isCreated() ) {
            Boolean wasAdded = listOfStudents.add( newElement );
            if( wasAdded ) {
                return "New element = \"" + newElement + "\" was added to the listOfStudents";
            }
            else {
                return "New element = \"" + newElement + "\" was NOT added to the listOfStudents because listOfStudents.add( newElement ) returned FALSE";
            }
        }
        else {
            return "New element = \"" + newElement + "\" was NOT added to the listOfStudents because SingletonSessionBean wasn't created!";
        }
    }

    @WebMethod
    @PermitAll
    public String removeElementFromListOfStudents_PermAll( String elementToRemove ) {
        if( isCreated() ) {
            try {
                int numberToRemove = Integer.parseInt( elementToRemove );
                if( listOfStudents.size()-1 < numberToRemove || numberToRemove < 0 ) {
                    return "index = " + numberToRemove + " is either bigger than size of list -1 (" + (listOfStudents.size()-1) + ") OR lower than 0";
                }
                else {
                    String removed = listOfStudents.remove( numberToRemove );
                    if( removed == null || removed.length() == 0 ) {
                        return "Item with index = " + numberToRemove + " was NOT removed because listOfStudents.remove( numberToRemove ) returned = \"" + removed + "\"";
                    }
                    else {
                        return "Item with index = " + numberToRemove + " was removed because listOfStudents.remove( numberToRemove ) returned = \"" + removed + "\"";
                    }
                }
            } catch( NumberFormatException e ) {
                Boolean wasRemoved = listOfStudents.remove( elementToRemove );
                if( wasRemoved ) {
                    return  "Item with value = " + elementToRemove + " was removed because listOfStudents.remove( elementToRemove ) returned = TRUE";
                }
                else {
                    return  "Item with value = " + elementToRemove + " was NOT removed because listOfStudents.remove( elementToRemove ) returned = FALSE";
                }
            }
        }
        else {
            return "Given element = \"" + elementToRemove + "\" was NOT removed from the listOfStudents because SingletonSessionBean wasn't created!";
        }
    }

    @WebMethod
    @PermitAll
    @XmlElementWrapper(name = "filteredList")
    @XmlElement(name = "filteredElement")
    public List<String> getListOfStudentsFilteredByName_PermAll( @WebParam(name = "nameToFilterBy") String name ) {
        List<String> filteredList = new ArrayList<String>();

        String tmp = null;
        String tmpName = null;
        int indexOfSpace = -1;
        for(int i=0; i<listOfStudents.size(); i++) {
            tmp = listOfStudents.get(i);
            if( tmp != null && tmp.isEmpty() != true ) {
                indexOfSpace = tmp.indexOf(" ");
                if( indexOfSpace != -1 ) {
                    tmpName = tmp.substring(0, indexOfSpace);
                    if( tmpName != null && tmpName.isEmpty() != true ) {
                        if( name.compareTo( tmpName ) == 0 ) {
                            filteredList.add( tmp );
                        }
                    }
                }
            }
        }

        return filteredList;
    }

    @WebMethod
    @PermitAll
    public List<Student> getlistOfStudentsCourses_PermAll() {
        return listOfStudentsCourses;
    }

    @WebMethod
    @RolesAllowed(value = "admin")
    @WebResult(name = "isCreatedResult")
    public boolean isCreatedWeb_PermAdmin() {
        return isCreated();
    }

    @WebMethod(exclude = true)
    public boolean isCreated() {
        if( wasContructorCalled ) {
            return true;
        }
        else {
            return false;
        }
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /*********************************************** IMAGE functions **************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** https://javapointers.com/tutorial/java-convert-image-to-base64-string-and-base64-to-image/
    **/
    // http://itdoc.hitachi.co.jp/manuals/3020/30203Y2310e/EY230728.HTM
    // https://www.theserverside.com/news/1363957/Sending-Attachments-with-SOAP

    /** Java <-> XML mapping
     * Base64Binary:
     * xs:base64Binary <- java.awt.Image, javax.activation.DataHandler, javax.xml.transform.Source
     * xsd:base64Binary -> byte[]
     */
    /** Image vs byte[]
     * http://blog.bdoughan.com/2011/03/jaxb-web-services-and-binary-data.html
     */
    // https://www.journaldev.com/770/string-byte-array-java

    // @WebResult(name = "ReturnedSingleImage")
    // @XmlElement(name = "ReturnedSingleImage")

    /************************************************************************************/
    /******************************** return STRING RESPONSE ****************************/
    /************************************************************************************/
    @WebMethod
    @PermitAll
    @XmlInlineBinaryData
    public String getImage_AsImage2String_PermAll( @WebParam(name = "imageName") String imageName) {
        java.awt.Image anImage = null;
        String response = null;

        anImage = getImage_AsImage_Binary_PermAll( imageName );

        if( anImage != null ) {
            response = ImageToString( anImage );
            return  response;
        }
        else {
            return "";
        }
    }

    @WebMethod
    @PermitAll
    @XmlInlineBinaryData
    public String getImage_AsByteArray2String_PermAll( @WebParam(name = "imageName") String imageName) {
        byte[] anArray = null;
        String response = null;

        anArray = getImage_AsByteArray_PermAll( imageName );
        response = getByteArrayToString( anArray );

        return response;
    }

    @WebMethod
    @PermitAll
    @XmlInlineBinaryData
    public String getImage_MimeDataHandler2String_PermAll( @WebParam(name = "imageName") String imageName) {
        DataHandler dataHandler = null;
        String response = null;

        dataHandler = getImage_MimeDataHandler_PermAll( imageName );
        response = DataHandlerToString( dataHandler );

        return response;
    }

    /************************************************************************************/
    /**************** return given data type Image,  byte[], DataHandler ****************/
    /************************************************************************************/
    /************************************************ IMAGE - BINARY **************************************************/
    @WebMethod
    @PermitAll
    @XmlInlineBinaryData
    public java.awt.Image getImage_AsImage_Binary_PermAll( @WebParam(name = "imageName") String imageName) {
        URL anUrl = null;
        InputStream inputStream = null;
        java.awt.Image anImage = null;

        anUrl = getUrl( imageName );
        inputStream = getInputStream( anUrl );
        anImage = getImage( inputStream );

        /** LEGIT BUT WRONG METHOD? **/
        /**
        try {
            System.out.println("anUrl: " + anUrl.getContent().toString());
        }
        catch( Exception e ) {
            String response = "SingletonSessionBean >>> getImage_AsImage_Binary_PermAll: \n\r" +
                    "\t Caught exception on anUrl.getContent().toString() \n\r" +
                    "\t Message: \n\r " + e.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + e.getCause() + "\n\r " +
                    "\t Exception class: \n\r" + e.getClass();
            System.out.println( response + "\n\r \n\r" );
        }
        System.out.println("inputStream: " + inputStream.toString());
        System.out.println("anImage: " + anImage.toString());
         **/

        return anImage;
    }

    /*********************************************** IMAGE - IMAGE/JPG ************************************************/
    @WebMethod
    @PermitAll
    @XmlMimeType("image/jpeg") // jpeg // jpg - error
    public java.awt.Image getImage_AsImage_MimeJpg_PermAll( @WebParam(name = "imageName") String imageName) {
        URL anUrl = null;
        InputStream inputStream = null;
        java.awt.Image anImage = null;

        anUrl = getUrl( imageName );
        inputStream = getInputStream( anUrl );
        anImage = getImage( inputStream );

        return anImage;
    }

    /**************************************************** BYTE[] ******************************************************/
    @WebMethod
    @PermitAll
    @XmlInlineBinaryData
    public byte[] getImage_AsByteArray_PermAll( @WebParam(name = "imageName") String imageName) {
        URL anUrl = null;
        InputStream inputStream = null;
        byte[] anArray = null;

        anUrl = getUrl( imageName );
        inputStream = getInputStream( anUrl );
        anArray = getByteArray( inputStream );

        return anArray;
    }

    /************************************************* DATA HANDLER ***************************************************/
    @WebMethod
    @PermitAll
    @XmlMimeType("application/octet-stream")
    public javax.activation.DataHandler getImage_MimeDataHandler_PermAll( @WebParam(name = "imageName") String imageName) {
        URL anUrl = null;
        javax.activation.DataHandler dataHandler = null;

        anUrl = getUrl( imageName );
        dataHandler = getDataHandler( anUrl );

        return dataHandler;
    }

    /************************************************************************************/
    /*********************** EXCLUDED - helper fuctions *********************************/
    /************************************************************************************/
    @WebMethod(exclude = true)
    public URL getUrl( String imageName ) {
        URL anUrl = null;
        try {
            anUrl = this.getClass().getClassLoader().getResource("images/" + imageName);
            if( anUrl == null ) {
                throw new Exception("SingletonSessionBean >>> getUrl: anUrl == null");
            }
            String urlContent = null;
            try {
                urlContent = anUrl.getContent().toString();
            }
            catch( Exception e ) {
                System.out.println("anUrl.content: " + urlContent );
                String response = "SingletonSessionBean >>> getUrl: \n\r" +
                        "\t Caught exception on anUrl.getContent().toString(): " + urlContent + "\n\r" +
                        "\t Message: \n\r " + e.getMessage() + "\n\r " +
                        "\t Cause: \n\r " + e.getCause() + "\n\r " +
                        "\t Exception class: \n\r" + e.getClass();
                System.out.println( response + "\n\r \n\r" );
            }
        }
        catch( Exception eResource ) {
            String response = "SingletonSessionBean >>> getUrl: \n\r" +
                    "\t Caught exception on this.getClass().getClassLoader().getResource \n\r" +
                    "\t Message: \n\r " + eResource.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + eResource.getCause() + "\n\r " +
                    "\t Exception class: \n\r" + eResource.getClass();
            System.out.println( response + "\n\r \n\r" );
            return anUrl;
        }
        return anUrl;
    }

    @WebMethod(exclude = true)
    public InputStream getInputStream( URL anUrl ) {
        InputStream inputStream = null;
        try {
            inputStream = anUrl.openStream();
            if( inputStream == null ) {
                throw new Exception("SingletonSessionBean >>> getInputStream: inputStream == null");
            }
            return inputStream;
        }
        catch( Exception eOpenStream ) {
            String response = "\t SingletonSessionBean >>> getInputStream: \n\r" +
                    "\t Caught exception on anUrl.openStream() \n\r" +
                    "\t Message: " + eOpenStream.getMessage() +"\n\r" +
                    "\t Cause: " + eOpenStream.getCause() + "\n\r" +
                    "\t Exception class: " + eOpenStream.getClass();
            System.out.println( response + "\n\r \n\r" );
            return null;
        }
    }

    @WebMethod(exclude = true)
    public java.awt.Image getImage( InputStream inputStream ) {
        java.awt.Image anImage = null;

        try {
            anImage = ImageIO.read(inputStream);
            if( anImage == null ) {
                throw new Exception("SingletonSessionBean >>> getImage: anImage == null");
            }
            inputStream.close();
            return anImage;
        }
        catch( Exception eOpenStream ) {
            String response = "\t SingletonSessionBean >>> getImage: \n\r" +
                    "\t Caught exception on ImageIO.read(inputStream) \n\r" +
                    "\t Message: " + eOpenStream.getMessage() +"\n\r" +
                    "\t Cause: " + eOpenStream.getCause() + "\n\r" +
                    "\t Exception class: " + eOpenStream.getClass();
            System.out.println( response + "\n\r \n\r" );
            return null;
        }
    }

    @WebMethod(exclude = true)
    public String ImageToString( java.awt.Image anImage ) {
        try {
            String response = null;
            response = new String( String.valueOf(anImage) );
            if( response == null ) {
                throw new Exception("SingletonSessionBean >>> ImageToString: response == null");
            }
            return response;
        }
        catch( Exception eOpenStream ) {
            String response = "\t SingletonSessionBean >>> ImageToString: \n\r" +
                    "\t Caught exception on: response = new String( String.valueOf(anImage) ) \n\r" +
                    "\t Message: " + eOpenStream.getMessage() +"\n\r" +
                    "\t Cause: " + eOpenStream.getCause() + "\n\r" +
                    "\t Exception class: " + eOpenStream.getClass();
            System.out.println( response + "\n\r \n\r" );
            return response;
        }
    }

    @WebMethod(exclude = true)
    public byte[] getByteArray( InputStream inputStream ) {
        byte[] anArray = null;
        int i = 0;
        try {
            java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();
            while ((i = inputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            inputStream.close();
            anArray = byteArrayOutputStream.toByteArray();
            if( anArray == null ) {
                throw new Exception("SingletonSessionBean >>> getByteArray: anArray == null");
            }
            byteArrayOutputStream.close();
            return anArray;
        }
        catch( Exception eOpenStream ) {
            String response = "SingletonSessionBean >>> getByteArray: \n\r" +
                    "\t Caught exception on i = inputStream.read() \n\r" +
                    "\t Message: \n\r " + eOpenStream.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + eOpenStream.getCause() + "\n\r " +
                    "\t Exception class: " + eOpenStream.getClass();
            System.out.println(response + "\n\r \n\r");
            return null;
        }
    }

    @WebMethod(exclude = true)
    public String getByteArrayToString( byte[] anArray ) {
        String response = null;
        try {
            response = java.util.Base64.getEncoder().encodeToString( anArray );
            if (response == null) {
                throw new Exception("SingletonSessionBean >>> getByteArrayToString: response == null");
            }
            return response;
        } catch( Exception e ) {
            response = "SingletonSessionBean >>> getByteArrayToString: \n\r" +
                    "\t Caught exception on response = new String( anArray ) \n\r" +
                    "\t Message: \n\r " + e.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + e.getCause() + "\n\r " +
                    "\t Exception class: " + e.getClass();
            System.out.println(response + "\n\r \n\r");
            return response;
        }
    }

    @WebMethod(exclude = true)
    public DataHandler getDataHandler( URL anUrl ) {
        DataHandler dataHandler = null;

        dataHandler = new DataHandler( anUrl );
        String dataHandlerContent = null;
        try {
            if( dataHandler.getDataSource() == null ) {
                throw new Exception("SingletonSessionBean >>> getImage_MimeDataHandler_PermAll: " +
                        "dataHandler.getDataSource() == null");
            }
            dataHandlerContent = dataHandler.getContent().toString();
        }
        catch( Exception e ) {
            String response = "SingletonSessionBean >>> getImage_MimeDataHandler_PermAll: \n\r" +
                    "\t Caught exception on dataHandler.getContent().toString(): " + dataHandlerContent + " \n\r" +
                    "\t While dataHandler: " + dataHandler.toString() + " \n\r" +
                    "\t Message: \n\r " + e.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + e.getCause() + "\n\r " +
                    "\t Exception class: \n\r" + e.getClass();
            System.out.println( response + "\n\r \n\r" );
        }

        return dataHandler;
    }

    @WebMethod(exclude = true)
    public String DataHandlerToString( DataHandler dataHandler ) {
        try {
            String response = null;
            response = new String( String.valueOf(dataHandler) );
            if (response == null) {
                throw new Exception("SingletonSessionBean >>> DataHandlerToString: response == null");
            }
            return response;
        }
        catch( Exception eOpenStream ) {
            String response = "\t SingletonSessionBean >>> DataHandlerToString: \n\r" +
                    "\t Caught exception on: response = new String( String.valueOf(dataHandler) ) \n\r" +
                    "\t Message: " + eOpenStream.getMessage() +"\n\r" +
                    "\t Cause: " + eOpenStream.getCause() + "\n\r" +
                    "\t Exception class: " + eOpenStream.getClass();
            System.out.println( response + "\n\r \n\r" );
            return response;
        }
    }

    /**optional**/
    /**
    @WebMethod
    @PermitAll
    @XmlElementWrapper(name = "ImagesElementWrapper")
    @XmlElement(name = "ImagesElement")
    public Image[] getAllImages_PermAll() {
        String path = "../../resources/images/";
        int numberOfImages = 3;
        String[] imageNames = new String[numberOfImages];
        Image[] images = new Image[imageNames.length];
        File[] imageFiles = new File[imageNames.length];

        for( int i = 0;  i < images.length; i++ ) {
            images[i] = null;
        }

        imageNames[0] = "doggo.jpg";
        imageNames[1] = "doggo.png";
        imageNames[2] = "doggo.gif";
        for( int i = 0; i < images.length; i++ ) {
            try {
                imageFiles[i] = new File( path + imageNames[i] );
                images[i] = ImageIO.read( imageFiles[i] );
            } catch( Exception e ) {
                System.out.println("Caught exception on reading a image file named \""  + imageNames[i] + "\" in path \"" + path + "\" \n\r " +
                    "\t Message: \n\r " + e.getMessage() + "\n\r " +
                    "\t Cause: \n\r " + e.getCause() + "\n\r " +
                    "\t Exception class: \n\r" + e.getClass() + "\n\r \n\r" );
            }
        }
        return images;
    }
    **/

//            // Mapped code fragment
//            public class Foo {
//            @XmlElements(
//                    @XmlElement(name="A", type=Integer.class),
//                    @XmlElement(name="B", type=Float.class)
//                    }
//                    public List items;
//}
//
//<!-- XML Representation for a List of {1,2.5}
//        XML output is not wrapped using another element -->
//        ...
//<A> 1 </A>
//<B> 2.5 </B>
//        ...
//
//<!-- XML Schema fragment -->
//<xs:complexType name="Foo">
//<xs:sequence>
//<xs:choice minOccurs="0" maxOccurs="unbounded">
//<xs:element name="A" type="xs:int"/>
//<xs:element name="B" type="xs:float"/>
//<xs:choice>
//</xs:sequence>
//</xs:complexType>

//    </security-domains>
//        ...
//        <security-domain name="SoapLab1-connector" cache-type="default">
//		    <!--
//            standalone-full-ha.xml:
//            Valid attributes are: 'default-realm, permission-mapper,
//            pre-realm-principal-transformer, post-realm-principal-transformer,
//            principal-decoder, realm-mapper, role-mapper,
//            trusted-security-domains, outflow-anonymous, outflow-security-domains,
//            security-event-listener, realms, name'
//            -->
//			<authentication>
//				<login-module code="UsersRoles" flag="required">
//					<module-option name="usersProperties" value="users.properties"/>
//					<module-option name="rolesProperties" value="roles.properties"/>
//				</login-module>
//			</authentication>
//		</security-domain>
//    </security-domains>


    /** MTOM **/
    /** Image:
     * https://www.mkyong.com/webservices/jax-ws/jax-ws-attachment-with-mtom/
     * https://docs.oracle.com/cd/E13222_01/wls/docs103/webserv_adv/mtom.html
     */
    /** Download & upload server side:
     * https://blogs.oracle.com/vijaya/streaming-soap-attachments-using-mtom-v2
     */
    /**
     @WebMethod
     @WebResult(name = "ReturnedSingleImage")
     @PermitAll
     @XmlElement(name = "ReturnedSingleImage")
     @MTOM(threshold=0)
     public DataHandler getImageAsStringWeb( @WebParam(name = "imageName") String imageName,
     @XmlMimeType("application/octet-stream") DataHandler dataHandler ) {
     URL anUrl = null;
     InputStream inputStream = null;
     OutputStream outputStream = null;
     StreamingDataHandler streamingDataHandler = (StreamingDataHandler) dataHandler;
     try {
     anUrl = this.getClass().getClassLoader().getResource("images/" + imageName);
     System.out.println("anUrl = " + anUrl);
     } catch (Exception eResource) {
     System.out.println("Caught exception on this.getClass().getClassLoader().getResource \n\r" +
     "\t Message: \n\r " + eResource.getMessage() + "\n\r " +
     "\t Cause: \n\r " + eResource.getCause() + "\n\r " +
     "\t Exception class: \n\r" + eResource.getClass() + "\n\r \n\r");
     }
     try {
     inputStream = anUrl.openStream();
     System.out.println("worked: inputStream = anUrl.openStream()");
     int c;
     while ((c = inputStream.read()) != -1) {
     outputStream.write(c);
     streamingDataHandler.getOutputStream().write(c);
     }
     } catch (Exception eOpenStream) {
     System.out.println("Caught exception on anUrl.openStream() \n\r" +
     "\t Message: \n\r " + eOpenStream.getMessage() + "\n\r " +
     "\t Cause: \n\r " + eOpenStream.getCause() + "\n\r " +
     "\t Exception class: \n\r" + eOpenStream.getClass() + "\n\r \n\r");
     } finally {
     if (inputStream != null) {
     System.out.println("worked: inputStream.close()");
     try {
     inputStream.close();
     } catch (Exception iException) {
     System.out.println("Exception on: inputStream.close()\n\r" +
     "\t Message: \n\r " + iException.getMessage() + "\n\r " +
     "\t Cause: \n\r " + iException.getCause() + "\n\r " +
     "\t Exception class: \n\r" + iException.getClass() + "\n\r \n\r");
     }
     }
     if (outputStream != null) {
     try {
     outputStream.close();
     System.out.println("worked: outputStream.close()");
     } catch (Exception oException) {
     System.out.println("Exception on: outputStream.close()\n\r" +
     "\t Message: \n\r " + oException.getMessage() + "\n\r " +
     "\t Cause: \n\r " + oException.getCause() + "\n\r " +
     "\t Exception class: \n\r" + oException.getClass() + "\n\r \n\r");
     }
     }

     }
     try {
     streamingDataHandler.writeTo(outputStream);
     dataHandler = (DataHandler) streamingDataHandler;
     return dataHandler;
     } catch (Exception streamingDataHandlerException) {
     System.out.println("Caught exception on this.getClass().getClassLoader().getResource \n\r" +
     "\t Message: \n\r " + streamingDataHandlerException.getMessage() + "\n\r " +
     "\t Cause: \n\r " + streamingDataHandlerException.getCause() + "\n\r " +
     "\t Exception class: \n\r" + streamingDataHandlerException.getClass() + "\n\r \n\r");
     return null;
     }
     }
     **/
}
