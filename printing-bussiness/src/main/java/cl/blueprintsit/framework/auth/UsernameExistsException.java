package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class UsernameExistsException extends Exception{
    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
