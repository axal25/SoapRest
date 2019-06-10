package rest.connector.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.rest.MessageCollection;
import model.rest.TestRestStudent;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import rest.connector.client.exceptions.BadResponseMediaTypeException;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class RequestInfo {
    private static final String className = "RequestInfo";
    private String path;
    private String requestType;
    private MediaType requestMediaType;
    private MediaType responseMediaType;
    private GenericType<?> responseReturnClass;
    private String pathParam;
    private MultivaluedMap<String, Object> queryParam;
    private Object contentParam;
    private String description;

    public RequestInfo() {
        this.path = null;
        this.requestType = null;
        this.requestMediaType = null;
        this.responseMediaType = null;
        this.responseReturnClass = null;
        this.pathParam = null;
        this.queryParam = null;
        this.contentParam = null;
        this.description = null;
    }

    public RequestInfo(
            String path,
            String requestType,
            MediaType requestMediaType,
            MediaType responseMediaType,
            GenericType<?> responseReturnClass,
            String pathParam,
            MultivaluedMap<String, Object> queryParam,
            Object contentParam,
            String description
    ) throws BadResponseMediaTypeException {
        setPath( path );
        setRequestType( requestType );
        setRequestMediaType( requestMediaType );
        setResponseMediaType( responseMediaType );
        setResponseReturnClass( responseReturnClass );
        setPathParam( pathParam );
        setQueryParam( queryParam );
        setContentParam( contentParam );
        setDescription( description );
    }

    public String toJson() {
        final String functionName = "toJson()";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( this );
            return jsonString;
        }
        catch( Exception e ) {
            System.out.println( MessageCollection.getException(className, functionName, e) );
            return null;
        }
    }

    /*************************************/
    /******** SETTERS AND GETTERS ********/
    /*************************************/

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public MediaType getRequestMediaType() {
        return requestMediaType;
    }

    public void setRequestMediaType(MediaType requestMediaType) {
        this.requestMediaType = requestMediaType;
    }

    public MediaType getResponseMediaType() {
        return responseMediaType;
    }

    public void setResponseMediaType(MediaType responseMediaType) throws BadResponseMediaTypeException {
        final String functionName = "setResponseMediaType(String responseMediaType)";
        if( responseMediaType.isCompatible( MediaType.valueOf(MediaType.TEXT_PLAIN) ) ||
            responseMediaType.isCompatible( MediaType.valueOf(MediaType.TEXT_HTML) ) ||
            responseMediaType.isCompatible( MediaType.valueOf(MediaType.APPLICATION_JSON) ) ||
            responseMediaType.isCompatible( MediaType.valueOf(MediaType.APPLICATION_XML) ) ||
            responseMediaType.isCompatible( MediaType.MULTIPART_FORM_DATA_TYPE ) ) {
            this.responseMediaType = responseMediaType;
            setResponseReturnClass( null );
        }
        else {
            throw new BadResponseMediaTypeException(className, functionName, responseMediaType);
        }
    }

    public GenericType<?> getResponseReturnClass() {
        return responseReturnClass;
    }

    public void setResponseReturnClass(GenericType<?> responseReturnClass) throws BadResponseMediaTypeException {
        final String functionName = "setResponseReturnClass(Class<?> responseReturnClass)";
        if( responseReturnClass == null ) {
            if( this.responseReturnClass == null ) {
                if( this.responseMediaType.isCompatible( MediaType.valueOf(MediaType.TEXT_PLAIN) ) ||
                    this.responseMediaType.isCompatible( MediaType.valueOf(MediaType.TEXT_HTML ) ) ) {
                    this.responseReturnClass = new GenericType<String>() {};
                }
                else {
                    if( this.responseMediaType.isCompatible( MediaType.valueOf(MediaType.APPLICATION_JSON) ) ||
                            this.responseMediaType.isCompatible( MediaType.valueOf(MediaType.APPLICATION_XML ) ) ) {
                        this.responseReturnClass = new GenericType<TestRestStudent>() {};
                    }
                    else {
                        if( this.responseMediaType.isCompatible( MediaType.MULTIPART_FORM_DATA_TYPE ) ) {
                            this.responseReturnClass = new GenericType<MultipartFormDataOutput>() {};
                        }
                        else {
                            throw new BadResponseMediaTypeException(className, functionName, responseMediaType);
                        }
                    }
                }
            }
        }
        else {
            this.responseReturnClass = responseReturnClass;
        }
    }

    public String getPathParam() {
        return pathParam;
    }

    public void setPathParam(String pathParam) {
        this.pathParam = pathParam;
    }

    public MultivaluedMap<String, Object> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(MultivaluedMap<String, Object> queryParam) {
        this.queryParam = queryParam;
    }

    public Object getContentParam() {
        return contentParam;
    }

    public void setContentParam(Object contentParam) {
        this.contentParam = contentParam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final String functionName = "Overridden toString() method";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( this );
            return jsonString;
        }
        catch( Exception e ) {
            System.out.println( MessageCollection.getException(className, functionName, e) );
            return null;
        }
    }
}
