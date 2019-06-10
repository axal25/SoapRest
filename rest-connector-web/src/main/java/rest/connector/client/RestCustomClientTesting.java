package rest.connector.client;

import model.rest.MessageCollection;
import model.rest.TestRestStudent;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import rest.api.exceptions.AtLeastOneParamReqException;
import rest.api.forms.FileUploadForm;
import rest.connector.client.exceptions.BadRequestTypeException;
import rest.connector.client.exceptions.NoContentParamException;
import rest.connector.client.exceptions.PathWasNullException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestCustomClientTesting {
    public static final String className = "RestCustomClientTesting";
    public static final boolean isDebugging = false;

    public static void restCustomClientTesting(int numberOfTests, TestRestStudent newStudent, TestRestStudent existingStudent ) throws Exception {
        final String functionName = "restClientTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/ \n\r");

        RequestInfo requestInfo = null;
        MultivaluedMap<String, Object> queryParam = null;
        int testNumber = Integer.MIN_VALUE;

        String[][] responses = new String[numberOfTests][];
        for (int i = 0; i < numberOfTests; i++) {
            responses[i] = new String[]{null, null};
        }
        /**
         String path,
         String requestType,
         String requestMediaType,
         String responseMediaType,
         Class<?> responseReturnClass,
         String pathParam,
         MultivaluedMap<String, Object> queryParam,
         MultivaluedMap<String, Object> contentParam,
         String description
         **/
        testNumber = 0;
        requestInfo = new RequestInfo(
                "/getClichedMessage",
                "GET",
                MediaType.TEXT_PLAIN_TYPE,
                MediaType.TEXT_PLAIN_TYPE,
                null,
                null,
                null,
                null,
                null
        );
        responses[testNumber] = new String[]{requestInfo.toJson(), null};
        responses[testNumber][1] = getResponse(requestInfo);
        /******************************************************************************************************/
        try {
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getHelloWorldMessage",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_PLAIN_TYPE,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getPaths",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            requestInfo.setResponseReturnClass( new GenericType<String>(){} );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getPaths",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    null,
                    null,
                    null,
                    "(methodName = getDefault)"
            );
            requestInfo.setResponseReturnClass( new GenericType<String>(){} );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    null,
                    null,
                    null,
                    "(methodName = getDefault)"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getHelloWorld",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    "aPathName",
                    null,
                    null,
                    "#6. | " +
                            "/{pathName} ( QueryName=null )"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            queryParam = new MultivaluedHashMap<>();
            queryParam.add("QueryName","aQueryName");
            requestInfo = new RequestInfo(
                    "/getHelloWorld",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    "aPathName",
                    queryParam,
                    null,
                    "#7. | " +
                            "/{aPathName}?{QueryName=aQueryName}"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getHelloWorld/?QueryName=aQueryName",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    "",
                    null,
                    null,
                    "#8. | " +
                            "path = /getHelloWorld/?QueryName=aQueryName | " +
                            "pathParam = / | " +
                            "queryParam = null"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getHelloWorld/?QueryName=aQueryName",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    null,
                    null,
                    null,
                    null,
                    "#9. | " +
                            "path = /getHelloWorld/?QueryName=aQueryName | " +
                            "pathParam = / | " +
                            "queryParam = null"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            /**   404 code - checkResponseStatusCode throws Exception and returns response = null **/
            /**   This results in getResponse returning String (response) = null
            testNumber++;
            queryParam = new MultivaluedHashMap<>();
            queryParam.add("QueryName","aQueryName");
            requestInfo = new RequestInfo(
                    "/getHelloWorld",
                    "GET",
                    MediaType.TEXT_PLAIN,
                    MediaType.TEXT_HTML,
                    null,
                    "",
                    queryParam,
                    null,
                    "#10. | " +
                            "path = /getHelloWorld | " +
                            "pathParam = / | " +
                            "queryParam = ?QueryName=aQueryName"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            **/
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getStudent",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.APPLICATION_JSON_TYPE,
                    new GenericType<TestRestStudent>(){},
                    "JacekObjectMapper",
                    null,
                    null,
                    "#10. | " +
                            "path = /getStudent | " +
                            "pathParam = null | " +
                            "queryParam = null"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            queryParam = new MultivaluedHashMap<>();
            queryParam.add("QueryStudentKey","Jack #8");
            requestInfo = new RequestInfo(
                    "/getStudent",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.APPLICATION_JSON_TYPE,
                    new GenericType<TestRestStudent>(){},
                    "JacekObjectMapper",
                    queryParam,
                    null,
                    "#11. | " +
                            "path = /getStudent | " +
                            "pathParam = /JacekObjectMapper | " +
                            "queryParam = Jack #8"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            /****************************** Bad StudentKey - InternalServerErrorCheck *****************************/
            /**
            testNumber++;
            queryParam = new MultivaluedHashMap<>();
            queryParam.add("QueryStudentKey","Jack #812321312312");
            requestInfo = new RequestInfo(
                    "/getStudent",
                    "GET",
                    MediaType.TEXT_PLAIN,
                    MediaType.APPLICATION_JSON,
                    new GenericType<TestRestStudent>(){},
                    "JacekObjectMapper",
                    queryParam,
                    null,
                    "#12. | " +
                            "path = /getStudent | " +
                            "pathParam = /JacekObjectMapper | " +
                            "queryParam = Jack #812321312312"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            **/
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getListOfStudents",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.APPLICATION_JSON_TYPE,
                    new GenericType<String>(){},
                    null,
                    null,
                    null,
                    "#12. | " +
                            "path = /getListOfStudents | " +
                            "pathParam = null | " +
                            "queryParam = null"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/getListOfStudents",
                    "GET",
                    MediaType.TEXT_PLAIN_TYPE,
                    MediaType.APPLICATION_JSON_TYPE,
                    new GenericType<List<TestRestStudent>>(){},
                    null,
                    null,
                    null,
                    "#13. | " +
                            "path = /getListOfStudents | " +
                            "pathParam = null | " +
                            "queryParam = null"
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/addStudent",
                    "POST",
                    MediaType.APPLICATION_JSON_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    null,
                    null,
                    newStudent,
                    "#14. | " +
                            "path = /addStudent | " +
                            "pathParam = null | " +
                            "queryParam = null | " +
                            "contentParam = " + newStudent
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            existingStudent.setAge(-15);
            requestInfo = new RequestInfo(
                    "/updateStudent",
                    "PUT",
                    MediaType.APPLICATION_JSON_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    null,
                    null,
                    existingStudent,
                    "#15. | " +
                            "path = /updateStudent | " +
                            "pathParam = null | " +
                            "queryParam = null | " +
                            "contentParam = " + existingStudent
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            /**
             * Source: https://stackoverflow.com/questions/25229880/how-to-send-enclose-data-in-delete-request-in-jersey-client
             * Based on the code in Jersey 2.18 version, The class JerseyInvocation use a predefined HashMap to
             * validate HTTP method and its Entity as below:
             *
             * map.put("DELETE", EntityPresence.MUST_BE_NULL);
             * map.put("GET", EntityPresence.MUST_BE_NULL);
             */
            /**
            testNumber++;
            contentParam = existingStudent;
            requestInfo = new RequestInfo(
                    "/deleteStudent",
                    "DELETE",
                    MediaType.APPLICATION_JSON,
                    MediaType.TEXT_HTML,
                    new GenericType<String>(){},
                    null,
                    null,
                    contentParam,
                    "#16. | " +
                            "path = /deleteStudent | " +
                            "pathParam = null | " +
                            "queryParam = null | " +
                            "contentParam = " + contentParam
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
             /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/deleteStudent",
                    "DELETE",
                    MediaType.APPLICATION_JSON_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    "JacekObjectMapper",
                    null,
                    null,
                    "#16. | " +
                            "path = /deleteStudent | " +
                            "pathParam = " + "JacekObjectMapper" + " | " +
                            "queryParam = " + null + " | " +
                            "contentParam = " + null
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            queryParam = new MultivaluedHashMap<>();
            queryParam.add("QueryStudentKey","JacekObjectMapper");
            requestInfo = new RequestInfo(
                    "/deleteStudent",
                    "DELETE",
                    MediaType.APPLICATION_JSON_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    "obligatoryPathParam",
                    queryParam,
                    null,
                    "#17. | " +
                            "path = /deleteStudent | " +
                            "pathParam = " + "obligatoryPathParam" + " | " +
                            "queryParam = " + queryParam + " | " +
                            "contentParam = " + null
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
             /******************************************************************************************************/
            testNumber++;
            requestInfo = new RequestInfo(
                    "/deleteStudent",
                    "DELETE",
                    MediaType.APPLICATION_JSON_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    "obligatoryPathParam",
                    null,
                    existingStudent,
                    "#18. | " +
                            "path = /deleteStudent | " +
                            "pathParam = " + "obligatoryPathParam" + " | " +
                            "queryParam = " + null + " | " +
                            "contentParam = " + existingStudent
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            testNumber++;
            String imageName = "doggo.png";
            byte[] bytes = null;
            try {
                bytes = ImageFetcher.getImageNameToBytes(imageName);
            }
            catch( Exception e ) {
                String eMsg = MessageCollection.getException(className, functionName, e);
                requestInfo = new RequestInfo(
                        "/uploadImage",
                        "POST",
                        MediaType.MULTIPART_FORM_DATA_TYPE,
                        MediaType.TEXT_HTML_TYPE,
                        new GenericType<String>(){},
                        imageName,
                        null,
                        eMsg,
                        "#19. | " +
                                "path = /uploadImage | " +
                                "pathParam = " + imageName + " | " +
                                "queryParam = " + null + " | " +
                                "contentParam = " + eMsg
                );
                responses[testNumber] = new String[]{requestInfo.toJson(), null};
            }
            MultipartFormDataOutput output = new MultipartFormDataOutput();
            output.addFormData(imageName, bytes, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            GenericEntity<MultipartFormDataOutput> newEntity  = new GenericEntity<MultipartFormDataOutput>( output ) {};
            Object contentParam = newEntity;
            requestInfo = new RequestInfo(
                    "/uploadImage",
                    "POST",
                    MediaType.MULTIPART_FORM_DATA_TYPE,
                    MediaType.TEXT_HTML_TYPE,
                    new GenericType<String>(){},
                    imageName,
                    null,
                    contentParam,
                    "#19. | " +
                            "path = /uploadImage | " +
                            "pathParam = " + imageName + " | " +
                            "queryParam = " + null + " | " +
                            "contentParam = " + contentParam
            );
            responses[testNumber] = new String[]{requestInfo.toJson(), null};
            responses[testNumber][1] = getResponse(requestInfo);
            /******************************************************************************************************/
            /**
             * RESTEASY004655: Unable to invoke request: javax.ws.rs.ProcessingException:
             * RESTEASY003215: could not find writer for content-type multipart/form-data type: rest.api.forms.FileUploadForm
             *
             */
//            testNumber++;
//            imageName = "doggo.jpg";
//            bytes = ImageFetcher.getImageNameToBytes( imageName );
//            FileUploadForm fileUploadForm = new FileUploadForm();
//            fileUploadForm.setData( bytes );
//            GenericEntity<FileUploadForm> fileUploadFormGenericEntity = new GenericEntity<FileUploadForm>( fileUploadForm ) {};
//            contentParam = fileUploadForm;
//            requestInfo = new RequestInfo(
//                    "/uploadImageV2",
//                    "POST",
//                    MediaType.MULTIPART_FORM_DATA_TYPE,
//                    MediaType.TEXT_HTML_TYPE,
//                    new GenericType<String>(){},
//                    imageName,
//                    null,
//                    contentParam,
//                    "#20. | " +
//                            "path = /uploadImageV2 | " +
//                            "pathParam = " + imageName + " | " +
//                            "queryParam = " + null + " | " +
//                            "contentParam = " + contentParam
//            );
//            responses[testNumber] = new String[]{requestInfo.toJson(), null};
//            responses[testNumber][1] = getResponse(requestInfo);

        } catch (Exception e) {
            System.out.println( MessageCollection.getException(className, functionName, e) );
        } finally {
            RestClientTesting.finallyCheckTestsArray(className, functionName, responses);
        }

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\ \n\r");
    }

    public static String getResponse( RequestInfo requestInfo )
            throws Exception {
        final String functionName = "getResponse( String url )";
        final boolean boolPrintMetadataAndHeaders = false;
        final boolean boolPrintRealUrl = false;
        final boolean boolPrintPostAndPutInfo = false;
        Client client = null;
        WebTarget webTarget = null;
        Response response = null;
        Object oResponse = null;
        String path = null;

        client = ClientBuilder.newClient().register( FileUploadForm.class );
        if( !client.getConfiguration().isRegistered( FileUploadForm.class ) ) {
            System.err.println("FileUploadForm.class is not registered");
        }

        webTarget = client.target(TestRestWSClient.webServiceUrl);

        if( requestInfo.getPath() != null ) {
            path = requestInfo.getPath();
        }
        else {
            throw new PathWasNullException(className, functionName);
        }

        if( requestInfo.getPathParam() != null ) {
            path = path + "/" + requestInfo.getPathParam();
        }
        webTarget = webTarget.path( path );
        boolPrintRealUrl( path, requestInfo.getPathParam(), boolPrintRealUrl );

        if( requestInfo.getQueryParam() != null ) {
            Map<String, String> sQueryParam = convertMultiToRegularMap( requestInfo.getQueryParam() );
            for(Map.Entry<String,String> entry : sQueryParam.entrySet() ) {
                webTarget = webTarget.queryParam( entry.getKey(), entry.getValue() );
            }
        }

        Invocation.Builder builder = webTarget
                .request( requestInfo.getResponseMediaType() )
                .accept( requestInfo.getRequestMediaType() );


        switch( requestInfo.getRequestType() ) {
            case "GET":
                break;
            case "POST":
            case "PUT":
                boolPrintPostAndPutInfo( boolPrintPostAndPutInfo, requestInfo );
                if( requestInfo.getContentParam() == null ) {
                    throw new NoContentParamException(className, functionName, requestInfo.getRequestType());
                }
                break;
            case "DELETE":
                if( requestInfo.getPathParam() == null &&
                        requestInfo.getQueryParam() == null &&
                        requestInfo.getContentParam() == null ) {
                    throw new AtLeastOneParamReqException(className, functionName, new String[]{"PathParam", "QueryParam", "ContentParam"});
                }
                break;
            case "PATCH":
                throw new Exception(className + " >>> " + functionName + ": Not implemented - on client side & server side.");
            default:
                throw new BadRequestTypeException(className, functionName, requestInfo.getRequestType() );
        }
        response = builder.build(requestInfo.getRequestType(), Entity.entity(requestInfo.getContentParam(), requestInfo.getRequestMediaType())).invoke();

        // byte File
//        if( false ) {
//            InputStream inputStream = response.readEntity(InputStream.class);
//            try {
//                byte[] bytes = IOUtils.toByteArray(inputStream);
//                if( bytes == null ) {
//                    System.out.println("bytes == null");
//                }
//                else {
//                    System.out.println("\n\r" + "bytes == " + bytes.toString() + "\n\r");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        boolPrintMetadataAndHeaders( boolPrintMetadataAndHeaders, response );

        response = RestClientTesting.checkResponseStatusCode( response, path );

        if (response != null) {
            oResponse = response.readEntity( requestInfo.getResponseReturnClass() );
            response.close();
            return oResponse.toString();
        }
        else {
            return null;
        }
    }

    public static void boolPrintPostAndPutInfo(boolean doPrintPostAndPutInfo, RequestInfo requestInfo ) {
        if( isDebugging && doPrintPostAndPutInfo ) {
            System.out.println( getPostInfoAsString(requestInfo) );
        }
    }

    public static void boolPrintMetadataAndHeaders(boolean boolPrintMetadataAndHeaders, Response response ) {
        if( isDebugging && boolPrintMetadataAndHeaders ) {
            System.out.println("MetaData: " + "\n\r" + getMetadataAsString( response ));
        }

        if( isDebugging && boolPrintMetadataAndHeaders ) {
            System.out.println("Headers: " + "\n\r" + getHeadersAsString( response ));
        }
    }

    public static void boolPrintRealUrl(String path, String pathParam, boolean boolPrintRealUrl) {
        if( isDebugging && boolPrintRealUrl ) {
            System.out.println("Real Url: " + "\n\r" + getRealUrlString(path, pathParam));
        }
    }

    public static Map<String, String> convertMultiToRegularMap(MultivaluedMap<String, Object> m) {
        Map<String, String> map = new HashMap<String, String>();
        if (m == null) {
            return map;
        }
        for (Map.Entry<String, List<Object>> entry : m.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (Object s : entry.getValue()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(s);
            }
            map.put(entry.getKey(), sb.toString());
        }
        return map;
    }

    public static String getMetadataAsString(Response response ) {
        String stringMetaData = "";
        MultivaluedMap<String, Object> mMMetaData = response.getMetadata();
        Map<String, String> sMMetaData = convertMultiToRegularMap( mMMetaData );
        for( Map.Entry<String, String> entry : sMMetaData.entrySet() ) {
            stringMetaData = stringMetaData + "sMMetaData< " + entry.getKey() + ", " + entry.getValue() + " >" + "\n\r";
        }
        return stringMetaData + "\n\r";
    }

    public static String getHeadersAsString(Response response ) {
        String stringHeaders = "";
        MultivaluedMap<String, Object> mMHeaders = response.getHeaders();
        Map<String, String> sMHeaders = convertMultiToRegularMap( mMHeaders );
        for( Map.Entry<String, String> entry : sMHeaders.entrySet() ) {
            stringHeaders = stringHeaders + "Header< " + entry.getKey() + ", " + entry.getValue() + " >" + "\n\r";
        }
        return stringHeaders + "\n\r";
    }

    public static String getPostInfoAsString(RequestInfo requestInfo ) {
        return "contentParam = " + requestInfo.getContentParam() + "\n\r" +
                "TestRestStudent.toJson((TestRestStudent) contentParam) = " + TestRestStudent.toJson((TestRestStudent) requestInfo.getContentParam()) + "\n\r" +
                "requestInfo.getResponseMediaType() <<< .request( ... ) =" + requestInfo.getResponseMediaType() + "\n\r" +
                "requestInfo.getRequestMediaType() >>> .request( ... ) = " + requestInfo.getRequestMediaType() + "\n\r" +
                "contentParam.getClass() = " + requestInfo.getContentParam().getClass() +
                "requestInfo.getResponseReturnClass() = " + requestInfo.getResponseReturnClass() +
                "requestInfo = " + "\n\r" + requestInfo;
    }

    public static String getRealUrlString(String path, String pathParam) {
        return "Real url = " + TestRestWSClient.webServiceUrl + path + "\n\r" +
        "webServiceUrl = \"" + TestRestWSClient.webServiceUrl + "\"" + "\n\r" +
        "path = \"" + path + "\"" + "\n\r" +
        "pathParam = \"" + pathParam + "\"";
    }
}
