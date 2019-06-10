package rest.api;

import io.swagger.jaxrs.config.BeanConfig;
import model.rest.TestRestStudent;
import rest.api.auth.UserEndpoint;
import rest.api.auth.filter.JWTTokenNeededFilter;
import rest.api.forms.FileUploadForm;
import rest.api.proto.ProtoStudent;
import rest.api.valid.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/TestRestApp")
public class TestRestApp extends Application {
    private final static String className = "TestRestApp";
    private Set<Object> singletons = new HashSet<Object>();

    public TestRestApp() {
        init();
        singletons.add( new TestRestWS() );
        singletons.add( new UserEndpoint() );
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {

        /*****************************/
        /************ v1 *************/
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add( TestRestWS.class );
        /************ v1 *************/
        /*****************************/

        /*****************************/
        /************ v2 *************/
        HashSet newHashSet = new HashSet<>(
                Arrays.asList(
                        TestRestWS.class,
                        TestRestStudent.class,
                        io.swagger.jaxrs.listing.ApiListingResource.class,
                        io.swagger.jaxrs.listing.SwaggerSerializers.class,
                        FileUploadForm.class,
                        UserEndpoint.class,
                        JWTTokenNeededFilter.class,
                        ValidationExceptionMapper.class,
                        ProtoStudent.class
                )
        );
        hashSet = newHashSet;
        /************ v2 *************/
        /*****************************/

        return hashSet;
    }

    private void init() {
        final String functionName = "init()";
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion( className + " >>> " + functionName + " version: 0.99.99 beta" );
        beanConfig.setSchemes(   new String[]{ "http" }   );
        beanConfig.setHost( "localhost:8080" );
        beanConfig.setBasePath( "/rest-api-web/TestRestApp" );
        beanConfig.setResourcePackage( TestRestWS.class.getPackage().getName() ) ;
        beanConfig.setTitle( "RESTEasy, Swagger and Swagger UI Example" );
        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan( true );
    }
}
