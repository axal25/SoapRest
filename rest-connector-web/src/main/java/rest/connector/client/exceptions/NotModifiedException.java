package rest.connector.client.exceptions;

public class NotModifiedException extends Exception {
    public final static String className = "NotModifiedException";
    public NotModifiedException(String callingClassName, String callingFunctionName) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + ": Received response status is NOT_MODIFIED (304)");
    }
}
