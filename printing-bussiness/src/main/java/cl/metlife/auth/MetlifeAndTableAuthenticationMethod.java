package cl.metlife.auth;

import cl.blueprintsit.framework.auth.AuthenticationException;
import cl.blueprintsit.framework.auth.AuthenticationMethod;
import cl.blueprintsit.framework.auth.TableAuthenticationMethod;
import cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult;
import cl.metlife.ws.clients.auth.MetlifeAuthenticationServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
@Stateless
public class MetlifeAndTableAuthenticationMethod extends AuthenticationMethod {

    private static final Logger logger = LoggerFactory.getLogger(MetlifeAndTableAuthenticationMethod.class);

    @EJB
    MetlifeAuthenticationMethod metlifeAuthenticationMethod;

    @EJB
    TableAuthenticationMethod tableAuthenticationMethod;

    public void authenticate(String username, String password) throws AuthenticationException {
        try{
            metlifeAuthenticationMethod.authenticate(username,password);
        }
        catch (AuthenticationException e){
            tableAuthenticationMethod.authenticate(username,password);
        }
    }



}
