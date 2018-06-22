package cl.blueprintsit.framework.auth;



import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
@Stateless
public class AuthorizationManager extends AuthorizationMethod {


    @EJB
    DummyAuthorizationMethod dummyAuthorizationMethod;

    @EJB
    TableAuthorizationMethod tableAuthorizationMethod;



    public User getUser(String username) throws UserNotFoundException {
        return getAuthorizationMethod().getUser(username);
    }


    @Override
    public void lockUser(String username) throws UserNotFoundException {
        getAuthorizationMethod().lockUser(username);
    }

    @Override
    public void unlockUser(String username) throws UserNotFoundException {
        getAuthorizationMethod().unlockUser(username);
    }

    public void markLogin(String username) throws UserNotFoundException {
          getAuthorizationMethod().markLogin(username);
    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException {
        getAuthorizationMethod().setPassword(username,password);
    }


    private AuthorizationMethod getAuthorizationMethod(){
        return dummyAuthorizationMethod;
    }

}
