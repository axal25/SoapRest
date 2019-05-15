package model.rest;

public class MessageCollection {
    private static final String[] studentAdded = {"Added student: \n\r"};
    private static final String[] studentUpdated = {"Updated student: \n\r"};
    private static final String[] studentRemoved = { "Removed Student: \n\r" };
    private static final String[] paramsReq = { "One name parameter ( ", " ) must be NOT NULL && NOT EMPTY" };
    private static final String[] notModStudentNotFound = { "Not modfified because of: \n\r Student with name \"", "\" doesn't exist." };
    private static final String[] notAddStudentExists = {"Not modfified because of: \n\r Student with name \"", "\" already exists."};
    private static final String[] exception = { "\n\r Exception Class: \n\r", "\n\r",
            "\n\r Exception Cause: \n\r", "\n\r",
            "\n\r Exception Message: \n\r", "\n\r",
            "\n\r Exception Stack Trace: \n\r", "\n\r" };

    public MessageCollection() {}

    public static String getStudentAdded( TestRestStudent student ) {
        return studentRemoved[0] + student.toString();
    }

    public static String getStudentUpdated( TestRestStudent student ) {
        return studentUpdated[0] + student.toString();
    }

    public static String getStudentRemoved( TestRestStudent student ) {
        return studentRemoved[0] + student.toString();
    }

    public static String getParamsReq( String [] paramSArray ) {
        String paramS = "";
        for (int i = 0; i < paramSArray.length; i++) {
            paramS = paramS + paramSArray[i];

            if( i!=0 && i!=(paramSArray.length-1) ) {
                paramS = paramS + ", ";
            }
        }
        return paramsReq[0] + paramS + paramsReq[1];
    }

    public static String getNotModStudentNotFound( String studentName ) {
        return notModStudentNotFound[0] + studentName + notModStudentNotFound[1];
    }

    public static String getNotAddStudentExists( String studentName ) {
        return notAddStudentExists[0] + studentName + notAddStudentExists[1];
    }

    public static String getException( Exception e ) {
        return exception[0] + e.getClass()  + exception[1] +
                exception[2] + e.getCause()  + exception[3] +
                exception[4] + e.getMessage()  + exception[5] +
                exception[5] + e.getStackTrace() + exception[6];
    }
}
