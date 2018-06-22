package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
