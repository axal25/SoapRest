package rest.connector.client.exceptions;

public class ReceivedNullResponseException extends Exception {
    public final static String className = "ReceivedNullResponseException";
    public ReceivedNullResponseException(String callingClassName, String callingFunctionName) {
        super( callingClassName + " >>> " + callingFunctionName + " >>> " + className + " Response returned was == null" );
    }
}
