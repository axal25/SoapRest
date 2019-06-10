package model.rest;

public class MessageCollection {
    private static final String[] studentAdded = {"Added student: \n\r"};
    private static final String[] studentUpdated = {"Updated student: \n\r"};
    private static final String[] studentRemoved = { "Removed Student: \n\r" };
    private static final String[] notAddedStudentExists = {"Not modfified because of: \n\r Student with name \"", "\" already exists."};
    private static final String[] notModedStudentNotFound = { "Not modfified because of: \n\r Student with name \"", "\" doesn't exist." };
    private static final String[] imageSaved = { "Image saved correctly: \n\r" + "Path image name = \"", "\"\n\r" + "Headers' image name = \"", "\"" };
    private static final String[] exception = { "\n\r Exception Class: \n\r", "\n\r",
            "\n\r Exception Cause: \n\r", "\n\r",
            "\n\r Exception Message: \n\r", "\n\r",
            "\n\r Exception Stack Trace: \n\r", "\n\r" };

    public MessageCollection() {}

    /*************************************** Response (200) Messages **************************************************/
    public static String getStudentAdded(String callingClassName, String callingFunctionName, TestRestStudent student ) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + studentAdded[0] + student.toString();
    }

    public static String getStudentUpdated(String callingClassName, String callingFunctionName, TestRestStudent student ) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + studentUpdated[0] + student.toString();
    }

    public static String getStudentRemoved(String callingClassName, String callingFunctionName, TestRestStudent student ) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + studentRemoved[0] + student.toString();
    }

    /*************************************** Response (not 200) Messages **********************************************/
    public static String getNotAddedStudentExists(String callingClassName, String callingFunctionName, String studentName ) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" +
                notAddedStudentExists[0] + studentName + notAddedStudentExists[1];
    }

    public static String getException( String callingClassName, String callingFunctionName, Exception e ) {
        if( e != null ) {
            return callingClassName + " >>> " + callingFunctionName + ": \n\r" + exception[0] + e.getClass()  + exception[1] +
                    exception[2] + e.getCause()  + exception[3] +
                    exception[4] + e.getMessage()  + exception[5] +
                    exception[5] + e.getStackTrace() + exception[6];
        }
        else {
            return callingClassName + " >>> " + callingFunctionName + ": \n\r" + exception[0] + e.getClass()  + exception[1];
        }
    }

    /*************************************** Exception ( & Response ) Messages ****************************************/
    private static final String[] paramsReq = { "One parameter ( ", " ) must be NOT NULL && NOT EMPTY" };
    public static String getAtLeastOneParamRequired( String callingClassName, String callingFunctionName, String [] paramSArray ) {
        String paramS = "";
        for (int i = 0; i < paramSArray.length; i++) {
            paramS = paramS + paramSArray[i];

            if( i!=0 && i!=(paramSArray.length-1) ) {
                paramS = paramS + ", ";
            }
        }
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + paramsReq[0] + paramS + paramsReq[1];
    }

    public static String getNotModedStudentNotFound(String callingClassName, String callingFunctionName, String studentName ) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" +
                notModedStudentNotFound[0] + studentName + notModedStudentNotFound[1];
    }

    public static String getImageUploaded(String callingClassName, String callingFunctionName, String pathImageName, String headerImageName) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + imageSaved[0] + pathImageName + imageSaved[1] + headerImageName + imageSaved [2];
    }

    public static String getAuthenticationException(String callingClassName, String callingFunctionName, String msg) {
        return callingClassName + " >>> " + callingFunctionName + ": \n\r" + msg;
    }
}
