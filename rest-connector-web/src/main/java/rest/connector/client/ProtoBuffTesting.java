package rest.connector.client;

import model.rest.MessageCollection;
import org.apache.commons.io.IOUtils;
import rest.api.proto.ProtoBuffImpl;
import rest.api.proto.ProtoStudent;
import rest.api.proto.ProtoUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

/** Source: https://dzone.com/articles/using-googles-protocol-buffers-with-java
 *
 */
public class ProtoBuffTesting {
    public static final String className = "ProtoBuffTesting";

    public static void protoBuffTesting() {
        final String functionName = "protoBuffTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/");
        protoUtilsTesting();
        getProtoBytesTesting();
        getResponseWithProtoBytes();
        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\");
    }

    public static void getResponseWithProtoBytes() {
        final String functionName = "getResponseWithProtoBytes()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/");

        Client client = null;
        javax.ws.rs.client.WebTarget webTarget = null;
        InputStream inputStream = null;
        ProtoStudent protoStudentFromWebService = null;
        byte[] bytes = null;

        client = ClientBuilder.newClient();
        webTarget = client.target( TestRestWSClient.webServiceUrl ).path("/getResponseWithProtoBytes");
        inputStream = webTarget
                .request(MediaType.TEXT_HTML)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .get( InputStream.class );
        protoStudentFromWebService = null;
        try {
            protoStudentFromWebService = ProtoUtils.getProtoStudent(   IOUtils.toByteArray( inputStream )   );
        } catch (IOException e) {
            System.err.println( MessageCollection.getException(className, functionName, e) );
        }
        System.out.println("/getResponseWithProtoBytes" + "\n\r" +
                "client ---GET---> webService ---example-protoStudent---> client" + "\n\r" +
                "protoStudentFromWebService #1 (" +
                System.identityHashCode(protoStudentFromWebService) +
                "): " + "\n\r" +
                protoStudentFromWebService + "\n\r");

        /** -------------------------------------------------------------------------------------------------------- **/

        webTarget = client.target( TestRestWSClient.webServiceUrl ).path("/getResponseWithProtoBytes");
        javax.ws.rs.core.Response response = webTarget
                .request( MediaType.TEXT_HTML )
                .accept( MediaType.APPLICATION_OCTET_STREAM )
                .get( javax.ws.rs.core.Response.class );
        bytes = response.readEntity( byte[].class );
        protoStudentFromWebService = ProtoUtils.getProtoStudent( bytes );
        System.out.println("/getResponseWithProtoBytes - byte[]" + "\n\r" +
                "client ---GET---> webService ---example-protoStudent---> client" + "\n\r" +
                "protoStudentFromWebService #2 (" +
                System.identityHashCode(protoStudentFromWebService) +
                "): " + "\n\r" +
                protoStudentFromWebService + "\n\r");

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\");
    }

    public static void getProtoBytesTesting() {
        final String functionName = "getProtoBytesTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/");

        Client client = null;
        javax.ws.rs.client.WebTarget webTarget = null;
        InputStream inputStream = null;
        ProtoStudent protoStudentFromWebService = null;
        byte[] bytes = null;

        client = ClientBuilder.newClient();
        webTarget = client.target( TestRestWSClient.webServiceUrl ).path("/getProtoBytes");
        inputStream = webTarget
                .request( MediaType.TEXT_HTML )
                .accept( MediaType.APPLICATION_OCTET_STREAM )
                .get( InputStream.class );
        try {
            protoStudentFromWebService = ProtoUtils.getProtoStudent(   IOUtils.toByteArray( inputStream )   );
        }
        catch ( IOException e ) {
            System.err.println( MessageCollection.getException(className, functionName, e) );
        }
        System.out.println("/getProtoBytes - InputStream" + "\n\r" +
                "client ---GET---> webService ---example-protoStudent---> client" + "\n\r" +
                "protoStudentFromWebService #3 (" +
                System.identityHashCode(protoStudentFromWebService) +
                "): " + "\n\r" +
                protoStudentFromWebService + "\n\r");

        /** -------------------------------------------------------------------------------------------------------- **/

        webTarget = client.target( TestRestWSClient.webServiceUrl ).path("/getProtoBytes");
        bytes = webTarget
                .request( MediaType.TEXT_HTML )
                .accept( MediaType.APPLICATION_OCTET_STREAM )
                .get( byte[].class );
        protoStudentFromWebService = ProtoUtils.getProtoStudent( bytes );
        System.out.println("/getProtoBytes - byte[]" + "\n\r" +
                "client ---GET---> webService ---example-protoStudent---> client" + "\n\r" +
                "protoStudentFromWebService #4 (" +
                System.identityHashCode(protoStudentFromWebService) +
                "): " + "\n\r" +
                protoStudentFromWebService + "\n\r");

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\");
    }

    public static void protoUtilsTesting() {
        final String functionName = "protoUtilsTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/");

        ProtoStudent protoStudent = ProtoUtils.generateExampleProtoStudent();

        System.out.println( "example-protoStudent (identityHashCode = " +
                System.identityHashCode(protoStudent) +
                "): " + "\n\r" + protoStudent.toString() );

        ProtoBuffImpl.MyProtoBuff myProtoBuff = ProtoUtils.getProtoBuffImpl_MyProtoBuff( protoStudent );
        byte[] binaryMyProtoBuff = ProtoUtils.getByteArray( myProtoBuff );
        ProtoStudent protoStudentInToOut = ProtoUtils.getProtoStudent( binaryMyProtoBuff );

        System.out.println( "example-protoStudent->byte[]->ProtoStudent (" +
                System.identityHashCode(protoStudentInToOut) +
                "): " + "\n\r" + protoStudentInToOut );

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\");
    }

}
