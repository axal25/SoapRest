package rest.connector.client.exceptions;

public class TestsFailedException extends Exception {
    public final static String className = "TestsFailedException";
    public TestsFailedException(String callingClassName, String callingFunctionName, int i) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> TESTS FAILED ON TEST #" + i);
    }
}
