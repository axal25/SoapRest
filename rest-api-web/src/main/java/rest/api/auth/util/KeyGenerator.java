package rest.api.auth.util;


import java.security.Key;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public interface KeyGenerator {

    Key generateKey();
}
