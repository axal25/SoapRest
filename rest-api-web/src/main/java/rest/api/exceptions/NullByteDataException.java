package rest.api.exceptions;

public class NullByteDataException extends Exception {
    public final static String className = "NullByteDataException";
    public NullByteDataException(String callingClassName, String callingFunctionName) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + ": byte[] data == null");
    }
}
