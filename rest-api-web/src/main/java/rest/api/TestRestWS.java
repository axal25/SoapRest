package rest.api;

/**
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
**/

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.rest.BadMatchRegExpException;
import model.rest.MessageCollection;
import model.rest.TestRestStudent;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import rest.api.auth.filter.JWTTokenNeeded;
import rest.api.exceptions.AtLeastOneParamReqException;
import rest.api.exceptions.NullByteDataException;
import rest.api.exceptions.StudentNotFoundException;
import rest.api.forms.FileUploadForm;
import rest.api.my.utils.Utils;
import rest.api.proto.ProtoBuffImpl;
import rest.api.proto.ProtoStudent;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Source(s):
 * 1)   http://www.mastertheboss.com/jboss-frameworks/resteasy/resteasy-tutorial
 * 2)   https://www.baeldung.com/resteasy-tutorial
 * 2.1) https://github.com/eugenp/tutorials/blob/master/resteasy/src/main/java/com/baeldung/server/MovieCrudService.java
 *      ^ github
 * 3)   https://www.mkyong.com/webservices/jax-rs/resteasy-hello-world-example/
 * 4)   http://blog.bdoughan.com/2012/07/jaxb-and-root-elements.html - object factory
 * 5)   https://docs.jboss.org/resteasy/docs/3.0.6.Final/userguide/html_single/?fbclid=IwAR3RPXvaYVcGD2pPQfcTe7Sc0nqRH7ZA3in0i4iXgoeazn4kPx6q2S4aKQ8#Using_Path
 *      ^ 8.2.2. Your first links injected
 * 6)   https://rphgoossens.wordpress.com/2018/01/20/putting-spring-boot-to-rest-with-swagger-and-grab-a-beer-in-the-process/
 *      ^ JPA
 * 7)   https://dzone.com/articles/creating-documented-rest-apis-with-wildfly-swarm
 *      ^ Wildfly Swarm Plugin
 * 8)   https://codecouple.pl/2017/01/07/9-spring-boot-swagger2-dokumentujemy-api/
 *      ^ presentations' "source"
 * 9)   https://stackoverflow.com/questions/49598003/configure-swagger-ui-with-maven
 *      ^ maven auto download swagger-ui and replace url
 */
@Path("/TestRestWS")
@Api(value="/TestRestWS", description = "Test Rest WebService - deprected description")
public class TestRestWS {
    public final static String className = "TestRestWS";
    public final static boolean isDebugging = false;
    public static String indexAddress = "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/";
    public static String line = "------------------------------------------------------------------------------------";
    public static String noParams = "---";
    public static String jsonParam = "JSon Param";
    public static String noExampleParam = "";
    public static String[] defaultPath = new String[] { System.getProperty("jboss.server.data.dir"), "uploads"};
    public final static String[][] paths = {
        /**[0]: method, [1]: params,     [2]: description,                    [3]: url,                                 [4]: exampleParam  **/
            { "GET", noParams, "SOAP - WSDL", "http://localhost:8080/soap-api-ejb/SingletonSessionBeanWebService?wsdl", noExampleParam },
            { "GET", noParams, "RestEasy Registry", "http://localhost:8080/rest-api-web/TestRestApp/resteasy/registry", noExampleParam } ,
            { "GET", noParams, "Swagger-UI", "http://localhost:8080/rest-api-web/index.html", noExampleParam } ,
            { "GET", noParams, "Swagger.json", "http://localhost:8080/rest-api-web/TestRestApp/swagger.json", noExampleParam },
            { "GET", noParams, "HTML - File upload form", "http://localhost:8080/rest-api-web/uploadFileForm.html", noExampleParam },
            { "GET", noParams, "HTML - Authentication form", "http://localhost:8080/rest-api-web/authForm.html", noExampleParam },
            { line },
            { "GET", noParams, "REST - getClichedMessage", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getClichedMessage", noExampleParam },
            { "GET", noParams, "REST - getHelloWorldMessage", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorldMessage", noExampleParam },
            { "GET", noParams, "REST - getPaths", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getPaths", noExampleParam },
            { "GET", noParams, "REST - getDefault", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/", noExampleParam },
            { line },
            { "GET", "pathName", "REST - getHelloWorld/{pathName}", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld", "/Jack1" },
            { "GET", "QueryName", "REST - getHelloWorld/?QueryName=QueryName !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld/pathParam", "?QueryName=Jack2" },
            { "GET", "pathName, QueryName", "REST - getHelloWorld/{pathName}?QueryName=QueryName", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld", "/Jack1?QueryName=Jack2" },
            { line },
            { "GET", "pathStudentKey", "REST - getStudent/{pathStudentKey} -> Returns JSON / XML", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent", "/JacekObjectMapper" },
            { "GET", "QueryStudentKey", "REST - getStudent?QueryStudentKey=StudentKey -> Returns JSON / XML ] !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent/pathParam", "?QueryStudentKey=JacekObjectMapper" },
            { "GET", "pathStudentKey, QueryStudentKey", "REST - getStudent/{pathStudentKey}?QueryStudentKey={QueryStudentKey} -> Returns JSON / XML ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent", "/JacekObjectMapper?QueryStudentKey=JacekObjectMapper" },
            { line },
            { "GET", noParams, "REST - getListOfStudents -> Returns JSON / XML", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getListOfStudents", noExampleParam },
            { "POST", jsonParam, "REST - addStudent -> Returns RESPONSE", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/addStudent", noExampleParam },
            { "PUT", jsonParam, "REST - updateStudent -> Returns RESPONSE", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/updateStudent", noExampleParam },
            { line },
            { "DELETE", "pathStudentKey", "[ REST - deleteStudent | DELETE -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent", "/JacekObjectMapper" },
            { "DELETE", "QueryStudentKey", "[ REST - deleteStudent | DELETE -> Returns RESPONSE ] !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent/pathParam", "?QueryStudentKey=JacekObjectMapper" },
            { "DELETE", "pathStudentKey, QueryStudentKey", "[ REST - deleteStudent | DELETE -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent", "/JacekObjectMapper1?QueryStudentKey=JacekObjectMapper" },
            { line },
            { "GET", "pathParam, number, message", "[ REST - validGet | GET -> Returns RESPONSE ]", "/rest-api-web/TestRestApp/TestRestWS/validGet", "/pathParam?number=12345&message=messa" }
    };

    private Map<String, TestRestStudent> mapOfStudents = null;

    public TestRestWS() {
        if( isDebugging ) {
            System.out.println("\n\r \n\r \n\r \n\r \n\r" +
                    "\t\t\t\t\t" + "TestRestWS() - CONSTRUCTOR \\/\\/\\/ \n\r" +
                    "\n\r \n\r \n\r \n\r \n\r");
        }

        mapOfStudents = new HashMap<String, TestRestStudent>();

        TestRestStudent tmp_student = new TestRestStudent( "Jacek Oles", 26 );
        String[] tmp_courses = {"Course 0.1", "Course 0.2", "Course 0.3", "Course 0.4", "Course 0.5", "Course 0.6", "Course 0.7"};
        mapOfStudents.put( tmp_student.getName(), tmp_student );

        for (int i = 0; i < 20; i++) {
            try {
                String jsonString = "{\"name\":\"Jack #" + i + "\", \"age\":\"" + (26+i) + "\"}";
                if( isDebugging ) System.out.println("jsonString: " + jsonString);

                tmp_student = new TestRestStudent( jsonString );

                tmp_courses = new String[7];
                for (int j = 0; j < 7; j++) {
                    tmp_courses[j] = "Course " + i + "." + j;
                }
                tmp_student.setCourses( tmp_courses );

                mapOfStudents.put( tmp_student.getName(), tmp_student );
                if( isDebugging ) System.out.println( "added student: " + tmp_student + "\n\r" );
            } catch (BadMatchRegExpException e) {
                e.printStackTrace();
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String complexJsonString = "{" +
                "   \"name\":\"JacekObjectMapper\", " +
                "   \"age\":\"25\", " +
                "   \"courses\":[" +
                "       \"course 999.0\", " +
                "       \"course 999.1\", " +
                "       \"course 999.2\", " +
                "       \"course 999.3\" " +
                "   ]" +
                "}";
        if(isDebugging) System.out.println("complexJsonString: " + complexJsonString);

        tmp_student = TestRestStudent.getNewTestRestStudent( complexJsonString );
        mapOfStudents.put(tmp_student.getName(), tmp_student);

        if( isDebugging ) {
            System.out.println("\n\r \n\r \n\r \n\r \n\r" +
                    "\t\t\t\t\t" + "TestRestWS() - CONSTRUCTOR /\\/\\/\\ \n\r" +
                    "\n\r \n\r \n\r \n\r \n\r");
        }
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/getClichedMessage")
    @Produces("text/plain")
    @Consumes("text/plain")
    @ApiOperation(value="GET Cliched Message")
    public String getClichedMessage() {
        String functionName = "getClichedMessage()";
        // Return some cliched textual content
        return className + " >>> " + functionName + " >> Hello World";
    }

    @GET
    @Path("/getHelloWorldMessage")
    @Produces("text/plain")
    public Response getHelloWorldMessage() {
        String functionName = "getHelloWorldMessage()";
        return Response.ok(className + " >>> " + functionName + " >> Hello World").build();
    }

    @GET
    @Path("/getPaths")
    @Produces("text/html")
    public String getPaths() {
        String functionName = "getPaths()";
        String wrappedPaths = Utils.wrapInAHref( paths );
        String response = "<h1>" + className + " >>> " + functionName + " >> Paths: </h1>\n\r" + wrappedPaths;
        response = Utils.wrapInHtml( "title: getPaths", response );
        return response;
    }

    @GET
    @Path("/")
    @Produces("text/html")
    public Response getDefault() {
        String functionName = "getDefault()";
        String wrappedPaths = Utils.wrapInAHref( paths );
        String response = "<h1>" + className + " >>> " + functionName + " >> Paths: </h1>\n\r" + wrappedPaths;
        response = Utils.wrapInHtml( "title: getDefault", response );

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity( response )
                .build();
    }

    @GET
    @Path("/getHelloWorld/{pathName}")
    @Produces("text/html")
    public Response getHelloWorld( @PathParam("pathName") String pathName,
                                   @QueryParam("QueryName") String queryName ) {
        final String functionName = "getHelloWorld( @PathParam(\"pathName\") String pathName, \n" +
                "\t                                   @QueryParam(\"QueryName\") String queryName )";
        String response = className + " >>> " + functionName + ": \n\r";

        String name = null;
        if( queryName == null || queryName.isEmpty() ) {
            if( pathName == null || pathName.isEmpty() ) {
                response = response + MessageCollection.getAtLeastOneParamRequired( className, functionName, new String[]{"pathName", "QueryName"} );
                return Response.status( Response.Status.BAD_REQUEST ).entity( response ).build();
            }
            else {
                name = pathName;
            }
        }
        else {
            name = queryName;
        }

        response = response + "Hello, " + name + ".";
        return Response.status(200).entity( response ).build();
    }

    @GET
    @Path("/getStudent/{pathStudentKey}")
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @ApiOperation(
            value = "Returns TestRestStudent",
            notes = "Returns TestRest Student from mapOfStudents based on field \"name\". Possible parameters (one required): pathParam = name, queryParam = name",
            response = TestRestStudent.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "XML or JSON representation for TestRestStudent",
                    response = TestRestStudent.class
            )
    })
    public TestRestStudent getStudent( @PathParam("pathStudentKey") String pathStudentKey,
                                       @QueryParam("QueryStudentKey") String queryStudentKey ) throws AtLeastOneParamReqException, StudentNotFoundException {
        final String functionName = "getStudent( @PathParam(\"pathStudentKey\") String pathStudentKey,\n" +
                "\t                                @QueryParam(\"QueryStudentKey\") String queryStudentKey )";
        String studentKey = null;

        if( queryStudentKey == null || queryStudentKey.isEmpty() ) {
            if( pathStudentKey == null || pathStudentKey.isEmpty() ) {
                throw new AtLeastOneParamReqException( className, functionName, new String[]{"pathStudentKey", "QueryStudentKey"} );
            }
            else {
                studentKey = pathStudentKey;
            }
        }
        else {
            studentKey = queryStudentKey;
        }

        if( mapOfStudents.containsKey( studentKey ) ) {
            return mapOfStudents.get( studentKey );
        }
        else {
            throw new StudentNotFoundException(className, functionName, studentKey);
        }
    }

    @GET
    @Path("/getListOfStudents")
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public java.util.List<TestRestStudent> getListOfStudents() {
        return mapOfStudents.values().stream().collect(   Collectors.toCollection( ArrayList::new )   );
    }

    @POST
    @Path("/addStudent")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response addStudent( TestRestStudent student ) {
        final String functionName = "addStudent( TestRestStudent student )";

        if( mapOfStudents.containsKey( student.getName() ) ) {
            String response = MessageCollection.getNotAddedStudentExists( className, functionName, student.getName() );
            System.out.println("\n\n\n\nRESPONSE: " + response + "\n\n\n\n\n");
            /****************************************************************************************************
             **A 304 response cannot contain a message-body; it is always terminated  ***************************
             * by the first empty line after the header fields.                       ***************************
             * Source: https://tools.ietf.org/html/rfc7232#section-4.1                ***************************
             ****************************************************************************************************/
            /**
            return Response.status( Response.Status.NOT_MODIFIED ).entity( response ).build();
            **/
            return Response.status( Response.Status.NOT_MODIFIED ).entity(null).build();
        }
        else {
            try {
                mapOfStudents.put( student.getName(), student );
                String response = MessageCollection.getStudentAdded( className, functionName, student );
                return Response.status( Response.Status.CREATED ).entity( response ).build();
            }
            catch( Exception e ) {
                String response = MessageCollection.getException(className, functionName, e);
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }
        }
    }

    @PUT
    @Path("/updateStudent")
    @Produces( { MediaType.TEXT_HTML })
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateStudent( TestRestStudent student ) {
        final String functionName = "updateStudent( TestRestStudent student )";

        if( mapOfStudents.containsKey( student.getName() ) ) {
            try {
                mapOfStudents.remove( student.getName() );
                mapOfStudents.put( student.getName(), student );
                String response = MessageCollection.getStudentUpdated( className, functionName, student );
                return Response.status( Response.Status.OK ).entity( response ).build();
            }
            catch( Exception e ) {
                String response = MessageCollection.getException(className, functionName, e);
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }
        }
        else {
            String response = MessageCollection.getNotModedStudentNotFound( className, functionName, student.getName() );
            return Response.status( Response.Status.NOT_FOUND ).entity( response ).build();
        }
    }

    @DELETE
    @Path("/deleteStudent/{pathStudentKey}")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response deleteStudent( @PathParam("pathStudentKey") String pathStudentKey,
                                   @QueryParam("QueryStudentKey") String queryStudentKey,
                                   TestRestStudent jsonStudent ) {
        final String functionName = "deleteStudent( @PathParam(\"pathStudentKey\") String pathStudentKey,\n" +
                "\t                                   @QueryParam(\"QueryStudentKey\") String queryStudentKey,\n" +
                "\t                                   TestRestStudent jsonStudent )";

        String studentKey;
        if( jsonStudent == null ) {
            if( queryStudentKey == null || queryStudentKey.isEmpty() ) {
                if( pathStudentKey== null || pathStudentKey.isEmpty() ) {
                        String response = MessageCollection.getAtLeastOneParamRequired(
                                className,
                                functionName,
                                new String[]{"pathStudentKey", "QueryStudentKey", "jsonStudent"}
                                );
                        return Response.status( Response.Status.BAD_REQUEST ).entity( response ).build();
                }
                else {
                    studentKey = pathStudentKey;
                }
            }
            else {
                studentKey = queryStudentKey;
            }
        }
        else {
            studentKey = jsonStudent.getName();
        }

        TestRestStudent tmpStudent = mapOfStudents.get( studentKey );
        if( null == tmpStudent ) {
            String response = MessageCollection.getNotModedStudentNotFound( className, functionName, studentKey );
            return Response.status( Response.Status.NOT_FOUND ).entity( response ).build();
        }
        else {
            try {
                mapOfStudents.remove( tmpStudent );
                String response = MessageCollection.getStudentRemoved( className, functionName, tmpStudent );
                return Response.status( Response.Status.OK ).entity( response ).build();
            }
            catch( Exception e ) {
                String response = MessageCollection.getException(className, functionName, e);
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }
        }
    }

    @POST
    @Path("/uploadImage/{pathImageName}")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.MULTIPART_FORM_DATA } )
    public Response uploadImage( @PathParam("pathImageName") String pathImageName,
                                 MultipartFormDataInput multipartFormDataInput ) {
        final String functionName = "uploadImage(MultipartFormDataInput multipartFormDataInput)";

        /**********************************************************************************************************************
         ****************************** HOW TO HANDLE SAVING FILES ON SERVER **************************************************
         *  https://stackoverflow.com/questions/18664579/recommended-way-to-save-uploaded-files-in-a-servlet-application ******
         **********************************************************************************************************************/
        /***************************** Testing via rest-api-web/src/main/webapp/uploadFileForm.html ***************************
         ***************************** Url: http://localhost:8080/rest-api-web/uploadFileForm.html ****************************
         ****** Sends to: http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/uploadImage/pathImageName *****************
         **********************************************************************************************************************/

        String headerImageName = null;
        Map<String, List<InputPart>> uploadFormMap = multipartFormDataInput.getFormDataMap();
        List<InputPart> inputPartList = uploadFormMap.get( pathImageName );
            try {
                for( InputPart inputPart : inputPartList ) {
                    MultivaluedMap<String, String> headers = inputPart.getHeaders();

                    // pathImageName = "formFileName" == file name given during sending
                    // headerImageName = original file name (with extension file format)
                    headerImageName = getImageNameFromHeaders( headers );
                    // deleteFileIfExists( defaultPath, pathImageName );
                    if( headerImageName == null ) headerImageName = pathImageName;
                    deleteFileIfExists( defaultPath, headerImageName );

                    InputStream inputStream = inputPart.getBody( InputStream.class, null );
                    byte[] bytes = IOUtils.toByteArray( inputStream );

                    // writeFile(bytes, defaultPath, pathImageName);
                    writeFile(bytes, defaultPath, headerImageName);
                }
            }
            catch( Exception e ) {
                String response = MessageCollection.getException(className, functionName, e) + "\n\r" +
                        "defaultPath = " + defaultPath[0] + File.separator + defaultPath[1] + "\n\r" +
                        "Path + pathImageName = " + defaultPath[0] + File.separator + defaultPath[1] + File.separator + pathImageName + "\n\r" +
                        "pathImageName = " + pathImageName + "\n\r" +
                        "Path + headerImageName = " + defaultPath[0] + File.separator + defaultPath[1] + File.separator + headerImageName + "\n\r" +
                        "headerImageName = " + headerImageName + "\n\r";
                System.out.println("\n\r" + response + "\n\r");
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }

        String response = MessageCollection.getImageUploaded(className, functionName, pathImageName, headerImageName);
        return Response.status( Response.Status.OK ).entity( response ).build();
    }

    @POST
    @Path("/uploadImageV2/{pathImageName}")
    @Consumes("multipart/form-data")
    public Response uploadImageV2(@PathParam("pathImageName") String pathImageName,
                                  @MultipartForm FileUploadForm form) {
        final String functionName = "uploadImageV2(@MultipartForm FileUploadForm form)";
        try {
            deleteFileIfExists( defaultPath, pathImageName );
            writeFile( form.getData(), defaultPath, pathImageName );
        } catch (Exception e) {
            String response = MessageCollection.getException(className, functionName, e);
            System.out.println("\n\r" + response + "\n\r");
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
        }

        String response = MessageCollection.getImageUploaded(className, functionName, pathImageName, "NO_headerImageName_IN_uploadImageV2");
        return Response.status( Response.Status.OK ).entity( response ).build();
    }

    /**
     * source:
     * https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/
    **/
    @POST
    @Path("/authPOST")
    @JWTTokenNeeded
    @Consumes({MediaType.APPLICATION_JSON})
    public Response authPOST( TestRestStudent student ) {
        System.out.println("authPOST( TestRestStudent student )");
        if( student == null ) {
            System.err.println("student == null");
        }
        return Response.ok().entity(student == null ? "student == null" : "student != null: " + student).build();
    }

    @GET
    @Path("/authGet")
    @JWTTokenNeeded
    public Response authGet(@QueryParam("message") String message) {
        String response = message == null ? "no message" : message;
        response = response + "\n\r <br/>" + "super duper secret info";
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/authThenGet/{token}")
    @Produces(MediaType.TEXT_HTML)
    public Response authBeforeGet(@PathParam("token") String token, @QueryParam("message") String message) {
        final String host = "http://localhost:8080";
        final String webContext = "/rest-api-web";
        final String appUrl = host + webContext + "/TestRestApp";
        final String webServiceUrl = appUrl + "/TestRestWS";
        String fullToken = "Bearer " + token;
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(webServiceUrl).path("/authGet").queryParam("message", message);
        Response response = webTarget.request(MediaType.TEXT_HTML).header(HttpHeaders.AUTHORIZATION, fullToken).get();
        return response;
    }

    // source: https://www.javacodegeeks.com/2014/04/validating-jax-rs-resource-data-with-bean-validation-in-java-ee-7-and-wildfly.html
    @GET
    @Path("/validGet/{pathParam}")
    @Produces( { MediaType.TEXT_HTML, MediaType.TEXT_PLAIN } )
    public Response validGet(@NotEmpty(message = "{pathParam.notEmpty}")
                             @Size(min = 1, message = "{pathPara.size.min=1}")
                             @PathParam("pathParam")
                             String pathParam,
                             @NotNull
                             @Pattern(regexp = "[0-9]+", message = "{number.pattern}")
                             @Min(value = 0, message = "{number.min=0}")
                             @Max(value = 99999, message = "{number.max=99999}")
                             @QueryParam("number")
                             String number,
                             @NotEmpty(message = "{message.notEmpty}")
                             @Size(min = 1, max = 5, message = "{ message.min=1; message.max=5 }")
                             @QueryParam("message")
                             String message ) {
        String response = "{" + "<br/>" + "\n\r" +
                "\t\t@NotEmpty" + "<br/>" + "\n\r" +
                "\t\t@Size(min = 1, message = \"{pathPara.size.min=1}" + "<br/>" + "\n\r" +
                "\t\t@PathParam(\"pathParam\")" + "<br/>" + "\n\r" +
                "\tString pathParam = " + pathParam + "," + "<br/>" + "\n\r" +
                "\t\t@NotNull" + "<br/>" + "\n\r" +
                "\t\t@Pattern(regexp = \"[0-9]+\")" + "<br/>" + "\n\r" +
                "\t\t@Min(value = 0, message = \"{number.min=0}\")" + "<br/>" + "\n\r" +
                "\t\t@Max(value = 99999, message = \"{number.max=99999}\")" + "<br/>" + "\n\r" +
                "\t\t@QueryParam(\"number\")" + "<br/>" + "\n\r" +
                "\tString number" + number + "," + "<br/>" + "\n\r" +
                "\t\t@NotEmpty" + "<br/>" + "\n\r" +
                "\t\t@Size(min = 1, max = 5, message = \"{ message.min=1; message.max=5 }\")" + "<br/>" + "\n\r" +
                "\t\t@QueryParam(\"message\")" + "<br/>" + "\n\r" +
                "\tString message = " + message + "<br/>" + "\n\r" +
                "}";
        return Response.status( Response.Status.OK ).entity( response ).build();
    }

    @POST
    @Path("/validPost")
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.TEXT_HTML, MediaType.TEXT_PLAIN } )
    public Response validPost(@NotNull(message = "{student != null}") TestRestStudent student,
                              @NotEmpty(message = "{message.isEmpty() == false}") @QueryParam("message") String message ) {
        String response = "@NotNull String student = " + student + "<br/>" + "\n\r" +
                "@NotEmpty @QueryParam(\"message\") String message = \"" + message + "\"" + "<br/>" + "\n\r";
        return Response.status( Response.Status.OK ).entity( response ).build();
    }

    @POST
    @Path("/validAddStudent")
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.TEXT_HTML, MediaType.TEXT_PLAIN } )
    public Response validAddStudent( @Valid TestRestStudent student ) {
        return addStudent(student);
    }

    @GET
    @Path("/getProtoBytes")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getProtoBytes() {
        ProtoStudent protoStudent = rest.api.proto.ProtoUtils.generateExampleProtoStudent();
        ProtoBuffImpl.MyProtoBuff myProtoBuffMessage = rest.api.proto.ProtoUtils.getProtoBuffImpl_MyProtoBuff( protoStudent );
        byte[] bytes = rest.api.proto.ProtoUtils.getByteArray( myProtoBuffMessage );
        return bytes;
    }

    @GET
    @Path("/getResponseWithProtoBytes")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResponseWithProtoBytes() {
        ProtoStudent protoStudent = rest.api.proto.ProtoUtils.generateExampleProtoStudent();
        ProtoBuffImpl.MyProtoBuff myProtoBuffMessage = rest.api.proto.ProtoUtils.getProtoBuffImpl_MyProtoBuff( protoStudent );
        byte[] bytes = rest.api.proto.ProtoUtils.getByteArray( myProtoBuffMessage );
        return Response.status( Response.Status.OK ).entity( bytes ).build();
    }

    public static boolean createParentFolderIfDoesNotExist(String[] checkPath) {
        boolean wasCreated = new File( checkPath[0] + File.separator + checkPath[1] ).mkdirs();
        return wasCreated;
    }

    public static boolean deleteFileIfExists(String[] checkPath, String fileName) {
        boolean wasDeleted = false;
        createParentFolderIfDoesNotExist( checkPath );
        File checkFile = new File( checkPath[0], checkPath[1] + File.separator + fileName );
        if( checkFile.exists() ) {
            checkFile.delete();
            wasDeleted = true;
        }
        return wasDeleted;
    }

    private void writeFile(byte[] bytes, String[] filePath, String fileName) throws IOException, NullByteDataException {
        final String functionName = "writeFile(byte[] bytes, String[] filePath, String fileName)";
        if( bytes == null )
            throw new NullByteDataException(className, functionName);
        createParentFolderIfDoesNotExist( filePath );
        File file = new File(filePath[0], filePath[1] + File.separator + fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write( bytes );
        fop.flush();
        fop.close();
    }

    /**
     * header sample
     * {
     * 		Content-Type=[image/png],
     * 		Content-Disposition=[form-data; name="file"; filename="filename.extension"]
     * }
     **/
    /** source: https://www.mkyong.com/webservices/jax-rs/file-upload-example-in-resteasy/ **/
    public static String getImageNameFromHeaders(MultivaluedMap<String, String> headers) {
        String[] contentDisposition = headers.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return null;
    }


}
