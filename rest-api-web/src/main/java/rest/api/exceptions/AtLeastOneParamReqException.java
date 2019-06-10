package rest.api.exceptions;

import model.rest.MessageCollection;

public class AtLeastOneParamReqException extends Exception {
    public final static String className = "AtLeastOneParamReqException";
    public AtLeastOneParamReqException( String callingClassName, String callingFunctionName, String [] paramSArray ) {
        super( MessageCollection.getAtLeastOneParamRequired( callingClassName, callingFunctionName, paramSArray ) );
    }
}
