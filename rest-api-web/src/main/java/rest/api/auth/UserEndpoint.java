package rest.api.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.rest.MessageCollection;
import rest.api.auth.util.KeyGenerator;
import rest.api.exceptions.AuthenticationException;
import rest.api.my.utils.Utils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserEndpoint {
    public static final String className = "UserEndpoint";

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Context
    private UriInfo uriInfo;

    @Inject
    private KeyGenerator keyGenerator;

    private Map<String, String> listOfUsers = new HashMap<String, String>();

    // ======================================
    // =          Business methods          =
    // ======================================

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        final String functionName = "authenticateUser(@FormParam(\"login\") String login,\n" +
                "                                     @FormParam(\"password\") String password)";
        try {
            listOfUsers.put("admin","admin");
            listOfUsers.put("user", "user");
            System.out.println( className + " >>> " + functionName + ": " + "\n\r" +
                    "login = " + login + "\n\r" +
                    "password = " + password );

            // Authenticate the user using the credentials provided
            authenticate(login, password);

            // Issue a token for the user
            String token = issueToken(login);

            // Return the token on the response
            String fullToken = "Bearer " + token;
            String response = "<h1>Issued token for the user</h1>" +
                    "authorization header = " + fullToken + "<br/>" +
                    "<a href=\"http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/authGet?message=TheMessageHiddenBehindAuthentication\">Echo message - authentication required</a><br/>" +
                    "<a href=\"http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/authThenGet/" + token + "?message=TheMessageHiddenBehindAuthentication\">Echo message - authentication with prepared token</a><br/>";

            System.out.println(response);
            response = Utils.wrapInHtml( className + " >>> " + functionName, response );
            return Response.ok().header(HttpHeaders.AUTHORIZATION, fullToken).entity( response ).build();

        } catch (AuthenticationException e) {
            String response = MessageCollection.getException(className, functionName, e);
            return Response.status(Response.Status.UNAUTHORIZED).entity( response ).build();
        }
    }

    private void authenticate(String login, String password) throws AuthenticationException {
        final String functionName = "authenticate(String login, String password)";
        String savedPassword = listOfUsers.get(login);
        if( savedPassword == null ) {
            throw new AuthenticationException(className, functionName, "Invalid user - couldn't find password for user.");
        }
        else {
            System.out.println(className + " >>> " + functionName + ": saved password = " + savedPassword);
        }
        if( savedPassword.compareTo( password ) != 0 ) {
            throw new AuthenticationException(className, functionName, "Invalid password - password doesn't match the user.");
        }
        else {
            System.out.println(className + " >>> " + functionName + ": inputPassword == savePassword <=> " + password + " == " + savedPassword);
        }
    }

    private String issueToken(String login) {
        final String functionName = "issueToken(String login)";
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(className + " >>> " + functionName + ": " + "\n\r" +
                "jwtToken = " + jwtToken + "\n\r" +
                "key = " + key);
        return jwtToken;
    }

    // ======================================
    // =          Private methods           =
    // ======================================

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
