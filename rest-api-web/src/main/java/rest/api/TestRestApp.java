package rest.api;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/TestRestApp")
public class TestRestApp extends Application {
    private final static String className = "TestRestApp";
    private Set<Object> singletons = new HashSet<Object>();

    public TestRestApp() {
        init();
        singletons.add( new TestRestWS() );
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add( TestRestWS.class );
        return hashSet;
    }

    private void init() {
        final String functionName = "init()";
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion( className + " >>> " + functionName + " version: 0.99.99 beta" );
        beanConfig.setSchemes(   new String[]{ "http" }   );
        beanConfig.setHost( "localhost:8080" );
        beanConfig.setBasePath( "/rest-api-web/TestRestApp/TestRestWS/" );
        beanConfig.setResourcePackage( TestRestWS.class.getPackage().getName() ) ;
        beanConfig.setTitle( "RESTEasy, Swagger and Swagger UI Example" );
        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan( true );
    }
}
