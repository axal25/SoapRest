package rest.api.exceptions;

import model.rest.MessageCollection;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException( String callingClassName, String callingFunctionName, String studentName ) {
        super( MessageCollection.getNotModedStudentNotFound( callingClassName, callingFunctionName, studentName ) );
    }
}
