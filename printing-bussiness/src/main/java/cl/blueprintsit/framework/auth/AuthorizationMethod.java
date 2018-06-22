package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public abstract class AuthorizationMethod {

    public abstract User getUser(String username) throws UserNotFoundException;

    public abstract void lockUser(String username) throws UserNotFoundException;
    public abstract void unlockUser(String username) throws UserNotFoundException;

    public abstract void markLogin(String username)  throws UserNotFoundException;
    public abstract void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException;


}
