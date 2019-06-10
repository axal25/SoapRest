package rest.api.exceptions;

import model.rest.MessageCollection;

public class AuthenticationException extends  Exception {
    public final static String className = "AuthenticationException";
    public String callingClassName = null;
    public String callingFunctionName = null;
    public String msg = null;

    public AuthenticationException( String callingClassName, String callingFunctionName, String msg ) {
        super( MessageCollection.getAuthenticationException( callingClassName, callingFunctionName, msg ) );
        this.callingClassName = callingClassName;
        this.callingFunctionName = callingFunctionName;
        this.msg = msg;
    }
}
