package cl.blueprintsit.framework.auth;


import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
@Stateless
public class AuthenticationManager {

    @EJB
    DummyAuthenticationMethod dummyAuthenticationBean;

    @EJB
    TableAuthenticationMethod tableAuthenticationMethod;

    public void authenticate(String username, String password) throws AuthenticationException {
         getAuthenticationMethod().authenticate(username,password);
    }

    //TODO retornar dependiendo de una configuracion
    private AuthenticationMethod getAuthenticationMethod(){
             return dummyAuthenticationBean;
    }

}
