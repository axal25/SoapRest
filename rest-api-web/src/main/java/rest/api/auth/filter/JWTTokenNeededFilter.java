package rest.api.auth.filter;

import io.jsonwebtoken.Jwts;
import rest.api.auth.util.KeyGenerator;
import rest.api.my.utils.Utils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {
    public static final String className = "JWTTokenNeededFilter";

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private KeyGenerator keyGenerator;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final String functionName = "filter(ContainerRequestContext requestContext)";

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println(className + " >>> " + functionName + ": " + "\n\r" +
                "authorizationHeader = " + authorizationHeader);


        String response = null;
        String token = null;
        try {
            // Check if the HTTP Authorization header is present and formatted correctly
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                response = "\n\r" + className + " >>> " + functionName + ": " + "\n\r" +
                        "Authorization header must be provided" + "\n\r" +
                        "No authorization, no content" + "\n\r" +
                        "INVALID authorizationHeader : " + authorizationHeader;
                throw new NotAuthorizedException("Authorization header must be provided");
            }
            else {
                // Extract the token from the HTTP Authorization header
                token = authorizationHeader.substring("Bearer".length()).trim();
                response = className + " >>> " + functionName + ": " + "\n\r" +
                        "Authorization header HAVE BEEN provided" + "\n\r" +
                        "Yes to authorization, yes to content" + "\n\r" +
                        "valid authorizationHeader : " + authorizationHeader;
                System.out.println(response);
            }

            // Validate the token
            Key key = keyGenerator.generateKey();
            response = className + " >>> " + functionName + ": " + "\n\r" +
                    "INVALID token = " + token;
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            response = className + " >>> " + functionName + ": " + "\n\r" +
                    "valid token = " + token;
            System.out.println(response);
        } catch (Exception e) {
            System.err.println(response);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(
                    Utils.wrapInHtml("Unauthorized " + className + " >>> " + functionName, response)
            ).build());
        }
    }
}
