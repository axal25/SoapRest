package rest.connector.client.exceptions;

public class NoContentParamException extends Exception {
    public final static String className = "NoContentParamException";

    public NoContentParamException(String callingClassName, String callingFunctionName, String requestType) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> Method " + requestType + " requires content param.");
    }
}
