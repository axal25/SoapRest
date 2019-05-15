package soap.connector.client;

import soap.api.wsconsume.autogen.WebServiceEJBServiceName;
import soap.api.wsconsume.autogen.WebServiceEJBName;

import javax.xml.ws.BindingProvider;

import javax.activation.DataHandler;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;

public class TestSoapClient {
    private static String className = "TestSoapClient";

    public static void main(String[] args) {
        final String functionName = "main(String[] args)";
        String response = null;

        System.out.println("TestSoapClient.main(String[] args) --- START");
        int n = 1;

        WebServiceEJBServiceName object_ServiceName_WebServiceEJB = TestSoapClient.getWebService();
        System.out.println("\t ^ #" + n + " ^ object_ServiceName_WebServiceEJB [ Webservice ] = " + object_ServiceName_WebServiceEJB );
        n++;
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getPort( object_ServiceName_WebServiceEJB );
        System.out.println("\t ^ #" + n + " ^ interface_PortName_WebServiceEJB [ Port ] = " + interface_PortName_WebServiceEJB );
        n++;
        TestSoapClient.mySetCredentials( interface_PortName_WebServiceEJB, "admin",  "admin");
        System.out.println("\t ^ #" + n + " ^ interface_PortName_WebServiceEJB [ Auth Port ] = " + interface_PortName_WebServiceEJB );
        n++;
        WebServiceEJBName auth_interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort( "admin", "admin");
        System.out.println("\t ^ #" + n + " ^ auth_interface_PortName_WebServiceEJB [ NEW Auth Port ] = " + auth_interface_PortName_WebServiceEJB );
        n++;

        response = TestSoapClient.use_myWebMethod_PermAdmin_FromApi( "admin", "admin", "mySoapParam from TestSoapClient.main(String[] args)");
        System.out.println("\t ^ #" + n + " ^ response [ use_myWebMethod_PermAdmin_FromApi ] = " + response);
        n++;
        response = TestSoapClient.use_myWebMethod_PermAdmin_FromApi();
        System.out.println("\t ^ #" + n + " ^ response [ use_myWebMethod_PermAdmin_FromApi ] = " + response);
        n++;

        java.util.List aList = TestSoapClient.use_getListOfStudents_PermUser_FromApi();
        System.out.println("\t ^ #" + n + " ^ aList [ use_getListOfStudents_PermUser_FromApi ] = " + aList.toString() );
        n++;
        String stringList = TestSoapClient.listToString( aList );
        System.out.println("\t ^ #" + n + " ^ stringList [ listToString ] = " + stringList );
        n++;
        response = TestSoapClient.use_getListOfStudents_PermUser_FromApi_AsString();
        System.out.println("\t ^ #" + n + " ^ response [ use_getListOfStudents_PermUser_FromApi_AsString ] = " + response);
        n++;
        response = TestSoapClient.use_removeElementFromListOfStudennts_PermAll_FromApi("exampleThatDoesn'tExists");
        System.out.println("\t ^ #" + n + " ^ response [ use_removeElementFromListOfStudennts_PermAll_FromApi - exampleThatDoesn'tExists ] = " + response);
        n++;
        response = TestSoapClient.use_addElementToListOfStudents_PermUser_FromApi("exampleNewlyCreatedDuringTesting");
        System.out.println("\t ^ #" + n + " ^ response [ use_addElementToListOfStudents_PermUser_FromApi - exampleNewlyCreatedDuringTesting ] = " + response);
        n++;
        response = TestSoapClient.use_removeElementFromListOfStudennts_PermAll_FromApi("exampleNewlyCreatedDuringTesting");
        System.out.println("\t ^ #" + n + " ^ response [ use_removeElementFromListOfStudennts_PermAll_FromApi - exampleNewlyCreatedDuringTesting ] = " + response);
        n++;
        java.util.List filteredList = TestSoapClient.use_getListOfStudentsFilteredByName_PermAll_FromApi( "Jacek" );
        System.out.println("\t ^ #" + n + " ^ filteredList [ use_getListOfStudentsFilteredByName_PermAll_FromApi - Jacek ] = " + filteredList);
        n++;
        response = TestSoapClient.use_getListOfStudentsFilteredByName_PermAll_FromApi_AsString("Jacek");
        System.out.println("\t ^ #" + n + " ^ response [ use_getListOfStudentsFilteredByName_PermAll_FromApi_AsString - Jacek ] = " + response);
        n++;

        DataHandler dataHandler = TestSoapClient.use_getImageMimeDataHandler_PermAll_FromApi( "doggo.jpg" );
        System.out.println("\t ^ #" + n + " ^ dataHandler [ use_getImageMimeDataHandler_PermAll_FromApi ] = " + dataHandler);
        n++;
        Image image = TestSoapClient.getImage("doggo.jpg" );
        System.out.println("\t ^ #" + n + " ^ image [ getImage ] = " + image);
        n++;
        response = TestSoapClient.saveFile("doggo.jpg");
        System.out.println("\t ^ #" + n + " ^ response [ saveFile ] = " + response);
        n++;

        final String fileName = "doggo.png";
        String base64 = TestSoapClient.use_getImageMimeDataHandler2StringPermAll_FromApi( fileName );
        System.out.println("\t ^ #" + n + " ^ base64 [ use_getImageMimeDataHandler2StringPermAll_FromApi ] = " + base64);
        n++;
        byte[] byteArray = TestSoapClient.decodeBase64( base64 );
        System.out.println("\t ^ #" + n + " ^ byteArray [ decodeBase64 ] = " + byteArray);
        n++;
        response = TestSoapClient.saveByteArray( byteArray, fileName );
        System.out.println("\t ^ #" + n + " ^ response [ saveByteArray ] = " + response);
        n++;
        response = TestSoapClient.getEncodedByteArrayInStringDecodeAndSave( "doggo.gif" );
        System.out.println("\t ^ #" + n + " ^ response [ getEncodedByteArrayInStringDecodeAndSave ] = " + response);
        n++;

        TestSoapClient.openImage( "doggo.png" );
        TestSoapClient.openImage( "doggo.jpg" );
        TestSoapClient.openImage( "doggo.gif" );

        System.out.println("TestSoapClient.main(String[] args) --- STOP");
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************* WebMethods (without IMAGE) *******************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public static String use_myWebMethod_PermAdmin_FromApi() {
        final String functionName = "TestSoapClient.use_myWebMethod_PermAdmin_FromApi()";
        String mySoapParam = "mySoapParam !!!!!!!! From INSIDE OF " + className + " >>> " + functionName + " !!!!!!!!";
        String username = "admin";
        String password = "admin";

        return TestSoapClient.use_myWebMethod_PermAdmin_FromApi( username, password, mySoapParam );
    }

    public static String use_myWebMethod_PermAdmin_FromApi( String username,
                                                            String password,
                                                            String mySoapParam ) {
        final String functionName = "TestSoapClient.use_myWebMethod_PermAdmin_FromApi(username, password, mySoapParam)";
        String response = "initiated in " + className + " >>> " + functionName;
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("admin","admin");

        try {
            response = interface_PortName_WebServiceEJB.myWebMethodPermAdmin( mySoapParam );
            return response;
        }
        catch( Exception e ) {
            response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: response = interface_PortName_WebServiceEJB.myWebMethod_PermAdmin( mySoapParam ) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            return response;
        }
    }

    public static java.util.List<String> use_getListOfStudents_PermUser_FromApi() {
        final String functionName = "use_getListOfStudents_PermUser_FromApi";
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("user","user");

        try {
            java.util.List<String> response = null;
            response = interface_PortName_WebServiceEJB.getListOfStudentsPermUser();
            return response;
        }
        catch( Exception e ) {
            java.util.List<String> response = new java.util.ArrayList<String>();

            response.add( className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: tmpListOfStudents = interface_PortName_WebServiceEJB.getListOfStudents_PermUser() \n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r" );
            return response;
        }
    }

    public static String use_getListOfStudents_PermUser_FromApi_AsString() {
        java.util.List<String> tmpListOfStudents = TestSoapClient.use_getListOfStudents_PermUser_FromApi();
        return TestSoapClient.listToString( tmpListOfStudents );
    }

    public static String use_addElementToListOfStudents_PermUser_FromApi( String elementToAdd ) {
        final String functionName = "use_addElementToListOfStudennts_PermUser_FromApi";
        String response = "initiated in " + className + " >>> " + functionName;
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("user","user");

        try {
            response = interface_PortName_WebServiceEJB.addElementToListOfStudentsPermUser( elementToAdd );
            return response;
        }
        catch( Exception e ) {
            response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: response = interface_PortName_WebServiceEJB.addElementToListOfStudennts_PermUser( elementToAdd ) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            return response;
        }
    }

    public static String use_removeElementFromListOfStudennts_PermAll_FromApi( String elementToRemove ) {
        final String functionName = "use_removeElementFromListOfStudennts_PermAll_FromApi";
        String response = "initiated in " + className + " >>> " + functionName;
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("SoapLab1","SoapLab1");

        try {
            response = interface_PortName_WebServiceEJB.removeElementFromListOfStudentsPermAll( elementToRemove );
            return response;
        }
        catch( Exception e ) {
            response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: response = interface_PortName_WebServiceEJB.removeElementFromListOfStudennts_PermAll( elementToRemove ) \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            return response;
        }
    }

    public static java.util.List use_getListOfStudentsFilteredByName_PermAll_FromApi( String name ) {
        final String functionName = "use_getListOfStudentsFilteredByName_PermAll";
        String response = "initiated in " + className + " >>> " + functionName;
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("SoapLab1","SoapLab1");

        try {
            java.util.List returnList = interface_PortName_WebServiceEJB.getListOfStudentsFilteredByNamePermAll( name );
            return returnList;
        }
        catch( Exception e ) {
            response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: returnList = interface_PortName_WebServiceEJB.getListOfStudentsFilteredByNamePermAll( name ) \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            return null;
        }
    }

    public static String use_getListOfStudentsFilteredByName_PermAll_FromApi_AsString( String name ) {
        java.util.List tmpList = TestSoapClient.use_getListOfStudentsFilteredByName_PermAll_FromApi( name );
        return TestSoapClient.listToString( tmpList );
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /*********************************************** IMAGE WebMethods *************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    /**
     * For client side of implementation of Image
     */

//    import javax.xml.ws.soap.MTOMFeature

//    String FOO = "FOO";
//    MtomService service = new MtomService()
//    MtomPortType port = service.getMtomPortTypePort(new MTOMFeature());
//    String result = null;
//    result = port.echoBinaryAsString(FOO.getBytes());
//    MtomPortType port = service.getMtomPortTypePort(new MTOMFeature(3072));

    public static DataHandler use_getImageMimeDataHandler_PermAll_FromApi( String imageName ) {
        final String functionName = "use_getImageMimeDataHandler_PermAll_FromApi";
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("SoapLab1","SoapLab1");
        DataHandler dataHandler = null;

        try {
            dataHandler = interface_PortName_WebServiceEJB.getImageMimeDataHandlerPermAll( imageName );
            if( dataHandler == null ) {
                throw new Exception( className + " >>> " + functionName + ": Returned dataHandler is null!");
            }
            return dataHandler;
        }
        catch( Exception e ) {
            String response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: interface_PortName_WebServiceEJB.getImageMimeDataHandler_PermAll( imageName ) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            System.out.println( response );
            return dataHandler;
        }
    }

    public static Image getImage( String imageName ) {
        final String functionName = "getImage";
        final boolean isDebugging = false;
        String response = "default RESPONSE from " + className + " >>> " + functionName;
        DataHandler dataHandler = TestSoapClient.use_getImageMimeDataHandler_PermAll_FromApi( imageName );
        try {
            InputStream inputStream = dataHandler.getInputStream();
            Image image = ImageIO.read( inputStream );

            if( isDebugging ) {
                System.out.println( className + " >>> " + functionName + " - isDebugging = true: ");
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.toString(): " + dataHandler.toString() );
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.getDataSource().toString(): " + dataHandler.getDataSource().toString() );
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.getContentType(): " + dataHandler.getContentType() );
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.getContent().toString(): " + dataHandler.getContent().toString() );
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "image.toString(): " + image.toString() );

                String dhFileName = null;
                String dsFileName = null;

                try {
                    dhFileName = dataHandler.getName();
                    dsFileName = dataHandler.getDataSource().getName();
                }
                catch( Exception e ) {
                    System.out.println( className + " >>> " + functionName + ": \n\r " +
                            "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: dsFileName = dataHandler.getName() \n\r \t " +
                            "\t\t\t\t\t\t\t\t\t\t\t dhFileName = dataHandler.getDataSource().getName() \n\r \t " +
                            "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                            "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                            "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r");
                }

                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.getName(): " + dhFileName );
                System.out.println( "\t\t\t\t\t\t\t\t\t\t\t" + "dataHandler.getDataSource().getName(): " + dsFileName );
            }

            return image;
        }
        catch (Exception e) {
            response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: inputStream = dataHandler.getInputStream() \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            System.out.println( response );
            return null;
        }
    }

    public static String saveFile( String imageName ) {
        final String functionName = "saveFile";
        final boolean isDebugging = false;
        String response = "default RESPONSE from " + className + " >>> " + functionName;

        DataHandler dataHandler = TestSoapClient.use_getImageMimeDataHandler_PermAll_FromApi( imageName );

        String savePath = "/home/jackdaeel/IdeaProjects/MavenProjects/SoapRest/soap-connector-ejb/src/main/resources/responses/";
        String contentType = null;

        String dsFileName = null;
        String dhFileName = null;
        String fileName = null;

        int dsIndexOfDot = -1;
        int dhIndexOfDot = -1;

        String dsExtension = null;
        String dhExtension = null;
        String extension = null;

        File newFile = null;

        try {
            contentType = dataHandler.getContentType();
        }
        catch( Exception contentTypeE ) {
            response = className + " >>> " + functionName + "\n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: contentType = dataHandler.getContentType() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + contentTypeE.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + contentTypeE.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + contentTypeE.getClass() + "\n\r \n\r";

            System.out.println(response);

            return response;
        }

        try {
            dsFileName = dataHandler.getDataSource().getName();
        }
        catch( Exception dataSourceNameE ) {
            response = className + " >>> " + functionName + "\n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: dsFileName = dataHandler.getDataSource().getName() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataSourceNameE.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataSourceNameE.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataSourceNameE.getClass() + "\n\r \n\r";

            System.out.println(response);

            return response;
        }

        try {
            dhFileName = dataHandler.getName();
        }
        catch( Exception dataHandlerNameE ) {
            response = className + " >>> " + functionName + "\n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: dhFileName = dataHandler.getName() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataHandlerNameE.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataHandlerNameE.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + dataHandlerNameE.getClass() + "\n\r \n\r";

            System.out.println(response);

            return response;
        }

        try {
            if( dsFileName != null ) {
                dsIndexOfDot = dsFileName.indexOf(".");
                dsExtension = dsFileName.substring(dsIndexOfDot+1, dsFileName.length());
            }
            if( dhFileName != null ) {
                dhIndexOfDot = dhFileName.indexOf(".");
                dhExtension = dhFileName.substring(dhIndexOfDot+1, dhFileName.length());
            }


            if( isDebugging ) {
                System.out.println( className + " >>> " + functionName + " - isDebugging = true #1: \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t Content Type: " + contentType + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t Data Handler: \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t File Name: " + dhFileName + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t Index of \".\": " + dhIndexOfDot + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t Extension: " + dhExtension + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t Data Source: \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t File Name: " + dsFileName + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t Index of \".\": " + dsIndexOfDot + "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t Extension: " + dsExtension + "\n" +
                        "Can we get Name on the SingletonSessionBean side?");
            }

            if( dsFileName != null && imageName.compareTo( dsFileName ) == 0 ) {
                fileName = dsFileName;
                extension = dsExtension;
            }
            else {
                if( dhFileName != null && imageName.compareTo( dhFileName ) == 0 ) {
                    fileName = dhFileName;
                    extension = dhExtension;
                }
                else {
                    if( ( dsFileName == null || dsFileName.isEmpty()  )
                            && ( dhFileName == null || dhFileName.isEmpty() ) ) {
                        fileName = imageName;
                        int indexOfDot = imageName.indexOf(".");
                        extension = imageName.substring(indexOfDot+1, imageName.length());
                    }
                }
            }

            if( isDebugging ) {
                System.out.println( className + " >>> " + functionName + " - isDebugging = true #2: \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t Chosen fileName: " + fileName + "\n\r" +
                        "\t\t\t\t\t\t\t\t\t\t\t Chosen extension: " + extension + "\n\r" +
                        "");
            }

            newFile = new File(savePath + fileName);
            InputStream inputStream = dataHandler.getInputStream();
            BufferedImage bufferedImage = ImageIO.read( inputStream );
            ImageIO.write( bufferedImage, extension, newFile );
            response = className + " >>> " + functionName + ": successfully created file: " + fileName + " with extention: " + extension  + " newFile = " + newFile.toString();

            return response;
        }
        catch( Exception e ) {
            response = className + " >>> " + functionName + "\n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t path + fileName: " + savePath + fileName + "\n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: inputStream = dataHandler.getInputStream() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";

            System.out.println(response);

            return response;
        }
    }

    /******************************************************************************************************************/
    /************************************************** as STRING *****************************************************/
    /******************************************************************************************************************/

    public static String use_getImageMimeDataHandler2StringPermAll_FromApi( String imageName ) {
        final String functionName = "use_getImageMimeDataHandler2StringPermAll_FromApi";
        WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("SoapLab1","SoapLab1");
        String stringByteArray = null;

        try {
            stringByteArray = interface_PortName_WebServiceEJB.getImageAsByteArray2StringPermAll( imageName );
            if( stringByteArray == null || stringByteArray.isEmpty() ) {
                throw new Exception( className + " >>> " + functionName + ": Returned stringDataHandler is null or empty!");
            }
            return stringByteArray;
        }
        catch( Exception e ) {
            String response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: interface_PortName_WebServiceEJB.getImageMimeDataHandler_PermAll( imageName ) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            System.out.println( response );
            return response;
        }
    }

    public static byte[] decodeBase64( String base64 ) {
        final String functionName = "decodeBase64";
        try {
            byte[] byteArray = java.util.Base64.getDecoder().decode( base64 );
            return byteArray;
        }
        catch( Exception e ) {
            String response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: byteArray = java.util.Base64.getDecoder().decode( base64 ) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            System.out.println( response );
            return null;
        }
    }

    public static String saveByteArray( byte[] byteArray, String imageName ) {
        final String functionName = "saveByteArray";
        final String savePath = "/home/jackdaeel/IdeaProjects/MavenProjects/SoapRest/soap-connector-ejb/src/main/resources/responses/";
        try {
            File newFile = new File(savePath + imageName);
            java.io.DataOutputStream dataOutputStream = new DataOutputStream(new java.io.FileOutputStream(newFile, false));
            dataOutputStream.write(byteArray);
            String response = className + " >>> " + functionName + ": successfully created file: " + imageName + " newFile = " + newFile.toString();
            return response;
        }
        catch( Exception e ) {
            String response = className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: newFile = new File(savePath + imageName) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t dataOutputStream = new DataOutputStream(new java.io.FileOutputStream(newFile, false)) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t dataOutputStream.write(byteArray) \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r";
            System.out.println( response );
            return response;
        }
    }

    public static String getEncodedByteArrayInStringDecodeAndSave( String imageName ) {
        String base64 = TestSoapClient.use_getImageMimeDataHandler2StringPermAll_FromApi( imageName );
        byte[] byteArray = TestSoapClient.decodeBase64( base64 );
        String response = TestSoapClient.saveByteArray( byteArray, imageName );
        return  response;
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /*********************************************** Helper functions *************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public static boolean wasSingletonCreated() {
        final String functionName = "wasSingletonCreated";
        final boolean isDebugging = false;
        boolean response = false;
        try {
            if( isDebugging ) {
                System.out.println(className + " >>> " + functionName + " - isDebugging = true #1: ");
                System.out.println("\t\t\t\t interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort(\"admin\", \"admin\");");
            }
            WebServiceEJBName interface_PortName_WebServiceEJB = TestSoapClient.getAuthenticatedPort("admin", "admin");

            if( isDebugging ) {
                System.out.println(className + " >>> " + functionName + " - isDebugging = true #1: ");
                System.out.println("\t\t\t\t response = interface_PortName_WebServiceEJB.isCreatedWeb();");
            }
            response = interface_PortName_WebServiceEJB.isCreatedWebPermAdmin();

            return response;
        } catch ( Exception e ) {
            System.out.println("\t\t\t\t " + className + " >>> " + functionName + " Exception >>> \n\r" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getMessage() + "\n\r  " +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getCause() + "\n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + e.getClass() + "\n\r \n\r");

            response = false;

            return response;
        }
    }

    public static WebServiceEJBServiceName getWebService() {
        final String functionName = "getWebService";
        try {
            WebServiceEJBServiceName object_ServiceName_WebServiceEJB = null;
            object_ServiceName_WebServiceEJB = new WebServiceEJBServiceName();
            return object_ServiceName_WebServiceEJB;
        }
        catch( Exception e ) {
            System.out.println( className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: object_ServiceName_WebServiceEJB = new WebServiceEJBServiceName() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r");
            return null;
        }
    }

    public static WebServiceEJBName getPort( WebServiceEJBServiceName object_ServiceName_WebServiceEJB ) {
        final String functionName = "getPort";
        try {
            WebServiceEJBName interface_PortName_WebServiceEJB = null;
            interface_PortName_WebServiceEJB = object_ServiceName_WebServiceEJB.getWebServiceEJBPortName();
            return interface_PortName_WebServiceEJB;
        }
        catch( Exception e ) {
            System.out.println( className + " >>> " + functionName + " \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: interface_PortName_WebServiceEJB = object_ServiceName_WebServiceEJB.getWebServiceEJBPortName() \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r");
            return null;
        }
    }

    public static boolean isAuthenticationRequired( WebServiceEJBServiceName object_ServiceName_WebServiceEJB ) {
        /*************************************
        ** throws ugly uncatchable warning ***
        **************************************/
        /**
        final String functionName = "isAuthenticationRequired";
        try {
            WebServiceEJBName interface_PortName_WebServiceEJB = null;
            interface_PortName_WebServiceEJB = TestSoapClient.getPort( object_ServiceName_WebServiceEJB );
            interface_PortName_WebServiceEJB.isCreatedWebPermAdmin();
            return false;
        }
        catch( javax.xml.ws.WebServiceException e ) {
            return true;
        }
        **/
        /*************************************
        ** throws ugly uncatchable warning ***
        **************************************/
        return true;
    }

    public static void mySetCredentials(WebServiceEJBName interface_PortName_WebServiceEJB, String username, String password) {
        final String functionName = "mySetCredentials";
        try {
            BindingProvider bindingProvider = (BindingProvider) interface_PortName_WebServiceEJB;
            bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
            bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        }
        catch( Exception e ) {
            System.out.println( className + " >>> " + functionName + ": \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: BindingProvider bindingProvider = (BindingProvider) interface_PortName_WebServiceEJB \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username) \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password) \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r");
        }
    }

    public static WebServiceEJBName getAuthenticatedPort( String username, String password ) {
        final String functionName = "getAuthenticatedPort";
        WebServiceEJBServiceName object_ServiceName_WebServiceEJB = null;
        WebServiceEJBName interface_PortName_WebServiceEJB = null;

        object_ServiceName_WebServiceEJB = getWebService();
        interface_PortName_WebServiceEJB = getPort( object_ServiceName_WebServiceEJB );
        if( isAuthenticationRequired(object_ServiceName_WebServiceEJB) ) {
            mySetCredentials(interface_PortName_WebServiceEJB, username, password);
        }

        return interface_PortName_WebServiceEJB;
    }

    public static String listToString( java.util.List<String> tmpListOfStudents ) {
        String stringList = null;

        if( tmpListOfStudents == null ) {
            stringList = "tmpListOfStudents == null";
        }
        else {
            if( tmpListOfStudents.isEmpty() ) {
                stringList = "tmpListOfStudents.isEmpty() == true";
            }
            else {
                stringList = "";
                java.util.ListIterator<String> iterator = tmpListOfStudents.listIterator();
                int i = 1;
                while( iterator.hasNext() ) {
                    String current = iterator.next();
                    stringList = stringList + i + ". " + current + "\n\r";
                    i++;
                }
            }
        }

        return stringList;
    }

    private static void openImage( String imageName ) {
        final String functionName = "openImage";
        final String savedPath = "/home/jackdaeel/IdeaProjects/MavenProjects/SoapRest/soap-connector-ejb/src/main/resources/responses/";
        try {
            File file = new File( savedPath + imageName );
            BufferedImage image = ImageIO.read(file);
            javax.swing.JLabel label = new javax.swing.JLabel(new javax.swing.ImageIcon(image));
            javax.swing.JFrame f = new javax.swing.JFrame();
            f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            f.getContentPane().add(label);
            f.pack();
            f.setLocation(200,200);
            f.setVisible(true);
            System.out.println("\t ^ #22 ^ " + className + " >>> " + functionName + ": \n\r " +
                    "\t\t Opened image: \"" + imageName + "\" at \"" + savedPath + "\"");
        }
        catch( Exception e ) {
            System.out.println( className + " >>> " + functionName + ": \n\r " +
                    "\t\t\t\t\t\t\t\t\t\t\t Caught exception on: image = ImageIO.read(file) \n\r \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getMessage() + "\n\r  \t " +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getCause() + "\n\r \t" +
                    "\t\t\t\t\t\t\t\t\t\t\t" + e.getClass() + "\n\r \n\r");
        }
    }

}
