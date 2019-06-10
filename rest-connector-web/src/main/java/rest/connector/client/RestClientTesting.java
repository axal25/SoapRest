package rest.connector.client;

import model.rest.MessageCollection;
import model.rest.TestRestStudent;
import rest.connector.client.exceptions.NotModifiedException;
import rest.connector.client.exceptions.ReceivedNullResponseException;
import rest.connector.client.exceptions.TestsFailedException;
import rest.connector.client.exceptions.UnsupportedMediaTypeException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.Thread.sleep;

public class RestClientTesting {
    public static final String className = "RestClientTesting";
    public static final boolean isDebugging = false;

    public static void restClientTesting() throws Exception {
        String functionName = "restClientTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/");

        final int numberOfTestsForProxyRequests = 15; // 15
        final int numberOfTestForCustomClient = numberOfTestsForProxyRequests + 5; // 20

        TestRestStudent newStudent = TestRestStudent.getNewTestRestStudent(
                "{" +
                        "   \"name\":\"JacekOlesKoles\", " +
                        "   \"age\":\"-15\", " +
                        "   \"courses\":[" +
                        "       \"course alpha 0.0.0.1\", " +
                        "       \"course alpha 0.0.0.2\", " +
                        "       \"course beta 0.0.0.2\", " +
                        "       \"course beta 0.0.0.2\" " +
                        "   ]" +
                "}"
        );

        TestRestStudent existingStudent = TestRestStudent.getNewTestRestStudent(
                "{" +
                        "   \"name\":\"JacekObjectMapper\", " +
                        "   \"age\":\"25\", " +
                        "   \"courses\":[" +
                        "       \"course 999.0\", " +
                        "       \"course 999.1\", " +
                        "       \"course 999.2\", " +
                        "       \"course 999.3\" " +
                        "   ]" +
                        "}"
        );

        RestEasyClientProxyFrameworkTesting.restEasyClientProxyFrameworkTesting(numberOfTestsForProxyRequests, newStudent, existingStudent);
        RestCustomClientTesting.restCustomClientTesting(numberOfTestForCustomClient, newStudent, existingStudent);

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\");
    }

    public static Response checkResponseStatusCode(Response response, String path ) throws ReceivedNullResponseException, NotModifiedException, UnsupportedMediaTypeException {
        final String functionName = "checkResponseStatusCode( Response response, String path )";
        if( response != null ) {
            if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
                response.close();
                throw new BadRequestException("Bad request: " + response.getAllowedMethods() + "\n\r" +
                        "MetaData: " + "\n\r" + RestCustomClientTesting.getMetadataAsString( response ) +
                        "Headers: " + "\n\r" + RestCustomClientTesting.getHeadersAsString( response ) +
                        "Make sure Request and Response MediaTypes are correct." + "\n\r" +
                        "Make sure Response return class (GenericType) is matching Response MediaType." +
                        "(Use variable doPrintPostInfo)");
            }
            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                response.close();
                throw new NotFoundException( className + " >>> " + functionName + " >>> NotFoundException >>> " +
                        RestCustomClientTesting.getRealUrlString(path, "<NoData>") );
            }
            if(response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                response.close();
                throw new InternalServerErrorException(className + " >>> " + functionName + " >>> " +
                        "InternelServerErrorException >>> (Server side): " + "\n\r" +
                        "getStudent >>> AtLeastOneParamReqException OR " + "\n\r" +
                        "getStudent >>> StudentNotFoundException " + "\n\r" +
                        "uploadImageV2 >>> FileUploadForm form.getData == null" + "\n\r" +
                        "MetaData: " + "\n\r" + RestCustomClientTesting.getMetadataAsString( response ) +
                        "Headers: " + "\n\r" + RestCustomClientTesting.getHeadersAsString( response ) );
            }
            if(response.getStatus() == Response.Status.NOT_MODIFIED.getStatusCode()) {
                /****************************************************************************************************
                 **A 304 response cannot contain a message-body; it is always terminated  ***************************
                 * by the first empty line after the header fields.                       ***************************
                 * Source: https://tools.ietf.org/html/rfc7232#section-4.1                ***************************
                 ****************************************************************************************************/
                response.close();
                throw new NotModifiedException(className, functionName);
            }
            if(response.getStatus() == Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode()) {
                response.close();
                MediaType mediaType = response.getMediaType();
                String sMediaType = null;
                if( mediaType != null ) sMediaType = mediaType.toString();
                throw new UnsupportedMediaTypeException(className, functionName,
                        sMediaType + "\n\r" +
                        "Received response status == " + response.getStatus() + " (" + response.getStatusInfo() + ")" + "\n\r" +
                        "Headers: " + "\n\r" + RestCustomClientTesting.getHeadersAsString( response ) +
                        "Metadata: " + "\n\r" + RestCustomClientTesting.getMetadataAsString(response) );
            }
            if(response.getStatus() != Response.Status.OK.getStatusCode() &&
                    response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                response.close();
                throw new BadRequestException(className + " >>> " + functionName + " >>> BadRequestException >>> " +
                        "Response status is !!! NOT !!!: " + "\n\r" +
                        "\t" + "OK (200) or CREATED (201): " + "\n\r" +
                        "Received response status == " + response.getStatus() + " (" + response.getStatusInfo() + ")." + "\n\r" +
                        "MetaData: " + "\n\r" + RestCustomClientTesting.getMetadataAsString( response ) +
                        "Headers: " + "\n\r" + RestCustomClientTesting.getHeadersAsString( response ) +
                        "Real url: " + "\n\r" + RestCustomClientTesting.getRealUrlString(path, "<NoData>") );
            }
            return response;
        }
        else {
            throw new ReceivedNullResponseException(className, functionName);
        }
    }

    public static void finallyCheckTestsArray(String className, String functionName, String[][] responses) throws TestsFailedException {
        System.out.println(className + " >>> " + functionName + " ------------------------------------- FINALLY ------------------------------------- ");
        for (int i = 0; i < responses.length; i++) {
            if (responses[i][1] == null) {
                if (responses[i][0] == null) {
                    System.out.println("Test #" + i + ". --- EMPTY TEST --- | path == " + responses[i][0] + " | Response == " + responses[i][1] + "\n\r");
                } else {
                    System.out.println("Test #" + i + ". path == " + responses[i][0] + " !!! FAILED !!! (response == null)" + "\n\r");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println( MessageCollection.getException( className, functionName, e ) );
                    }
                    throw new TestsFailedException(className, functionName, i);
                }
            } else {
                System.out.print("Test #" + i + ". path == " + responses[i][0]);
                if (isDebugging) {
                    System.out.println(" | !!! SUCCESSFUL !!! Response == \n\r\t" + responses[i][1] + "\n\r");
                } else {
                    System.out.println(" | !!! SUCCESSFUL !!! (response != null)" + "\n\r");
                }
            }
        }
        System.out.println();
    }
}
