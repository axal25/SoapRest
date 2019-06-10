package rest.connector.client.exceptions;

public class PathWasNullException extends Exception {
    public final static String className = "PathWasNullException";
    public PathWasNullException(String callingClassName, String callingFunctionName) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + ": Path was NULL.");
    }
}
