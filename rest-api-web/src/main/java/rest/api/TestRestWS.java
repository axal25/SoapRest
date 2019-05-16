package rest.api;

/**
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
**/

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.rest.AtLeastOneParamReqException;
import model.rest.BadMatchRegExpException;
import model.rest.MessageCollection;
import model.rest.TestRestStudent;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// The Java class will be hosted at the URI path "/helloworld"
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
    private final static String className = "TestRestWS";
    private final static boolean isDebugging = true;
    private static String indexAddress = "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/";
    private final static String[] paths = { "[ SOAP - WSDL | GET ]", "http://localhost:8080/soap-api-ejb/SingletonSessionBeanWebService?wsdl",
            "Swagger-UI", "http://localhost:8080/rest-api-web/swagger-ui.html",
            "-----------------------------------", indexAddress,
            "[ REST - getClichedMessage | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getClichedMessage",
            "[ REST - getHelloWorldMessage | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorldMessage",
            "[ REST - getPaths | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getPaths",
            "[ REST - getDefault | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/",
            "-----------------------------------", indexAddress,
            "[ REST - getHelloWorld/{pathName} | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld/Jack1",
            "[ REST - getHelloWorld/?QueryName=QueryName | GET ] !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld/pathParam?QueryName=Jack2",
            "[ REST - getHelloWorld/{pathName}?QueryName=QueryName | GET ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld/Jack1?QueryName=Jack2",
            "-----------------------------------", indexAddress,
            "[ REST - getStudent/{pathStudentKey} | GET -> Returns JSON / XML ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent/JacekObjectMapper",
            "[ REST - getStudent?QueryStudentKey=StudentKey | GET -> Returns JSON / XML ] !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent/pathParam?QueryStudentKey=JacekObjectMapper",
            "[ REST - getStudent/{pathStudentKey}?QueryStudentKey={QueryStudentKey} | GET -> Returns JSON / XML ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getStudent/JacekObjectMapper?QueryStudentKey=JacekObjectMapper",
            "-----------------------------------", indexAddress,
            "[ REST - getListOfStudents | GET -> Returns JSON / XML ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getListOfStudents",
            "[ REST - addStudent | POST -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/addStudent",
            "[ REST - updateStudent | PUT -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/updateStudent",
            "-----------------------------------", indexAddress,
            "[ REST - deleteStudent | DELETE -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent/JacekObjectMapper",
            "[ REST - deleteStudent | DELETE -> Returns RESPONSE ] !!! CANT WORK WITHOUT pathParam !!!", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent/pathParam?QueryStudentKey=JacekObjectMapper",
            "[ REST - deleteStudent | DELETE -> Returns RESPONSE ]", "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/deleteStudent/JacekObjectMapper?QueryStudentKey=JacekObjectMapper"};

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
        try {
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

            tmp_student = objectMapper.readValue(complexJsonString, TestRestStudent.class);
            mapOfStudents.put(tmp_student.getName(), tmp_student);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        String functionName = "getHelloWorldMessage()";
        String wrappedPaths = wrapInAHref( paths );
        String response = "<h1>" + className + " >>> " + functionName + " >> Paths: </h1>\n\r" + wrappedPaths;
        response = wrapInHtml( "title: getPaths", response );
        return response;
    }

//    @GET
//    @Path("/")
//    @Produces("text/html")
//    public Response getDefault() {
//        String functionName = "getHelloWorldMessage()";
//        String wrappedPaths = wrapInAHref( paths );
//        String response = "<h1>" + className + " >>> " + functionName + " >> Paths: </h1>\n\r" + wrappedPaths;
//        response = wrapInHtml( "title: getDefault", response );
//
//        return Response.status(200).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .entity( response )
//                .build();
//        /*
//        return Response.ok( response ).build();
//        */
//    }

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
                response = response + MessageCollection.getParamsReq( new String[]{"pathName", "QueryName"} );
                Response.status( Response.Status.BAD_REQUEST ).entity( response ).build();
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
    public TestRestStudent getStudent( @PathParam("pathStudentKey") String pathStudentKey,
                                       @QueryParam("QueryStudentKey") String queryStudentKey ) throws AtLeastOneParamReqException {
        final String functionName = "getStudent( @PathParam(\"pathStudentKey\") String pathStudentKey,\n" +
                "\t                                @QueryParam(\"QueryStudentKey\") String queryStudentKey )";
        String studentKey = null;

        if( queryStudentKey == null || queryStudentKey.isEmpty() ) {
            if( pathStudentKey == null || pathStudentKey.isEmpty() ) {
                String errorMsg = className + " >>> " + functionName + ": \n\r" +
                        MessageCollection.getParamsReq( new String[]{"pathStudentKey", "QueryStudentKey"} );
                throw new AtLeastOneParamReqException( errorMsg );
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
            return new TestRestStudent( MessageCollection.getNotModStudentNotFound(studentKey),-1);
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
        String response = className + " >>> " + functionName + ": + \n\r";

        if( mapOfStudents.containsKey( student.getName() ) ) {
            response = response + MessageCollection.getNotAddStudentExists( student.getName() );
            return Response.status( Response.Status.NOT_MODIFIED ).entity( response ).build();
        }
        else {
            try {
                mapOfStudents.put( student.getName(), student );
                response = response + MessageCollection.getStudentAdded( student );
                return Response.status( Response.Status.CREATED ).entity( response ).build();
            }
            catch( Exception e ) {
                response = response + MessageCollection.getException( e );
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
        String response = className + " >>> " + functionName + ": \n\r";

        if( mapOfStudents.containsKey( student.getName() ) ) {
            try {
                mapOfStudents.remove( student.getName() );
                mapOfStudents.put( student.getName(), student );
                response = response + MessageCollection.getStudentUpdated( student );
                return Response.status( Response.Status.OK ).entity( response ).build();
            }
            catch( Exception e ) {
                response = response + MessageCollection.getException( e );
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }
        }
        else {
            response = response + MessageCollection.getNotModStudentNotFound( student.getName() );
            return Response.status( Response.Status.NOT_FOUND ).entity( response ).build();
        }
    }

    @DELETE
    @Path("/deleteStudent/{pathStudentKey}")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteStudent( @PathParam("pathStudentKey") String pathStudentKey,
                                   @QueryParam("QueryStudentKey") String queryStudentKey,
                                   TestRestStudent jsonStudent ) {
        final String functionName = "deleteStudent( @PathParam(\"pathStudentKey\") String pathStudentKey,\n" +
                "\t                                   @QueryParam(\"QueryStudentKey\") String queryStudentKey,\n" +
                "\t                                   TestRestStudent jsonStudent )";
        String response = className + " >>> " + functionName + ": \n\r";

        String studentKey;
        if( jsonStudent == null ) {
            if( queryStudentKey == null || queryStudentKey.isEmpty() ) {
                if( pathStudentKey== null || pathStudentKey.isEmpty() ) {
                        response = response + MessageCollection.getParamsReq( new String[]{"pathStudentKey", "QueryStudentKey", "jsonStudent"} );
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
            response = response + MessageCollection.getNotModStudentNotFound( studentKey );
            return Response.status( Response.Status.NOT_FOUND ).entity( response ).build();
        }
        else {
            try {
                mapOfStudents.remove( tmpStudent );
                response = response + MessageCollection.getStudentRemoved( tmpStudent );
                return Response.status( Response.Status.OK ).entity( response ).build();
            }
            catch( Exception e ) {
                response = response + MessageCollection.getException( e );
                return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( response ).build();
            }
        }
    }


    /**
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
    **/

    public static String wrapInHtml( String pageTitle, String response ) {

        response = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t <head>\n" +
                "\t\t <title>" + pageTitle + "</title>\n" +
                "\t </head>\n" +
                "\t <body>\n" + response + "</body>\n" +
                "</html>\n";

        return response;
    }

    public static String wrapInAHref( String[] sArray ) {
        String wrapped = "";
        for ( int i = 0; i < sArray.length - 1; i = i+2 ) {
            wrapped = wrapped + "<a href=\"" + sArray[i+1] + "\">" + sArray[i] + "</a> </br>\n";
        }
        return wrapped;
    }
}
