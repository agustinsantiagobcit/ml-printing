package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class AuthorizationException extends Exception{
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
