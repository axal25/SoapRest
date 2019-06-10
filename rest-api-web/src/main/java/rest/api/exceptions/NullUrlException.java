package rest.api.exceptions;

public class NullUrlException extends Exception {
    public final static String className = "NullUrlException";
    public NullUrlException(String callingClassName, String callingFunctionName, String imagePath ) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> Bad path: " + imagePath);
    }
}
