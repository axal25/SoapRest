package rest.connector.client;

import model.rest.TestRestStudent;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import rest.api.forms.FileUploadForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/TestRestWS")
public interface ClientServiceInterface {

    @GET
    @Path("/getClichedMessage")
    @Produces("text/plain")
    @Consumes("text/plain")
    String getClichedMessage();

    @GET
    @Path("/getHelloWorldMessage")
    @Produces("text/plain")
    Response getHelloWorldMessage();

    @GET
    @Path("/getPaths")
    @Produces("text/html")
    String getPaths();

    @GET
    @Path("/")
    @Produces("text/html")
    Response getDefault();

    @GET
    @Path("/getHelloWorld/{pathName}")
    @Produces("text/html")
    Response getHelloWorld( @PathParam("pathName") String pathName,
                            @QueryParam("QueryName") String queryName );

    @GET
    @Path("/getStudent/{pathStudentKey}")
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    TestRestStudent getStudent( @PathParam("pathStudentKey") String pathStudentKey,
                                @QueryParam("QueryStudentKey") String queryStudentKey );

    @GET
    @Path("/getListOfStudents")
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    java.util.List<TestRestStudent> getListOfStudents();

    @POST
    @Path("/addStudent")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    Response addStudent( TestRestStudent student );

    @PUT
    @Path("/updateStudent")
    @Produces( { MediaType.TEXT_HTML })
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    Response updateStudent( TestRestStudent student );

    @DELETE
    @Path("/deleteStudent/{pathStudentKey}")
    @Produces( { MediaType.TEXT_HTML } )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    Response deleteStudent( @PathParam("pathStudentKey") String pathStudentKey,
                            @QueryParam("QueryStudentKey") String queryStudentKey,
                            TestRestStudent jsonStudent );

    @POST
    @Path("/uploadImageV2/{pathImageName}")
    @Consumes("multipart/form-data")
    Response uploadImageV2( @PathParam("pathImageName") String pathImageName,
                            @MultipartForm FileUploadForm form );
}
