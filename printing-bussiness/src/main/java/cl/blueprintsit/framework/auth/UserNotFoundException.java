package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
