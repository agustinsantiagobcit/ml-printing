package cl.blueprintsit.framework.auth;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public abstract class AuthenticationMethod {

    public abstract void authenticate(String username, String password) throws AuthenticationException;

}
