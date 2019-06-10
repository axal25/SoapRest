package rest.connector.client.exceptions;

import javax.ws.rs.core.MediaType;

public class BadResponseMediaTypeException extends Exception {
    public final static String className = "BadResponseMediaTypeException";
    public final static String[] acceptableTypes = new String[]{
            MediaType.TEXT_PLAIN,
            MediaType.TEXT_HTML,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.WILDCARD
    };

    public BadResponseMediaTypeException(String callingClassName, String callingFunctionName, MediaType badRequestType) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + className + " >>> Bad Request Type: " + "\n\r" +
                "Your response media type == " + badRequestType + "\n\r" +
                "Acceptable types == " + getAcceptableTypesToString() );
    }

    public static String getAcceptableTypesToString() {
        String returnString = "{ ";
        for (int i = 0; i < acceptableTypes.length; i++) {
            returnString = returnString + acceptableTypes[i];
            if( i != acceptableTypes.length-1 ) {
                returnString = returnString + ", ";
            }
        }
        returnString = returnString + " }";
        return returnString;
    }

}
