package rest.connector.client.exceptions;

public class BadRequestTypeException extends Exception {
    public final static String className = "BadRequestTypeException";
    public BadRequestTypeException(String callingClassName, String callingFunctionName, String badRequestType) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> Bad Request Type: " + badRequestType);
    }
}
