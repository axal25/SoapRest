package rest.connector.client.exceptions;

public class UnsupportedMediaTypeException extends Exception {
    public final static String className = "UnsupportedMediaTypeException";
    public UnsupportedMediaTypeException(String callingClassName, String callingFunctionName, String badMediaType) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> Bad Media Type: " + badMediaType);

    }
}
