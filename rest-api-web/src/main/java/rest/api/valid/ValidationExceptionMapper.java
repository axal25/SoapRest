package rest.api.valid;


import model.rest.MessageCollection;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    public static final String className = "@Provider ValidationExceptionMapper implements ExceptionMapper<ValidationException>";
    @Override
    public Response toResponse( ValidationException exception ) {
        final String functionName = "@Override Response toResponse( ValidationException exception )";
        String response = className + " >>> " + functionName + ": Custom ValidationExceptionHandler" + "\n\r" +
                MessageCollection.getException(className, functionName, exception);
        return Response.status( Response.Status.BAD_REQUEST).entity( response ).encoding("text/plain;charset=UTF-8").type( MediaType.TEXT_PLAIN ).build();
    }
}
