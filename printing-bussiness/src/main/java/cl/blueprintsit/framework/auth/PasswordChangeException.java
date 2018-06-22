package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class PasswordChangeException extends Exception{
    public PasswordChangeException(String message) {
        super(message);
    }

    public PasswordChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
