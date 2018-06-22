package cl.metlife.auth;

import cl.blueprintsit.framework.auth.AuthenticationException;
import cl.blueprintsit.framework.auth.AuthenticationMethod;
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
public class MetlifeAuthenticationMethod extends AuthenticationMethod {

    private static final Logger logger = LoggerFactory.getLogger(MetlifeAuthenticationMethod.class);

    @EJB
    MetlifeAuthenticationServiceManager service;

    public void authenticate(String username, String password) throws AuthenticationException {

        try {
            Check_claveResponseCheck_claveResult result = service.getServiceInstance().check_clave(username,password,"1","");

            Document asDocument = result.get_any()[0].getAsDocument();


            String textContent = asDocument.getElementsByTagName("status").item(0).getTextContent();

            if (!textContent.contains("NOK"))
                return;


        } catch (RemoteException e) {
            logger.error("Error de red when retrieving the authentication result for username {}: {}. " , username , e.getMessage(),e);
            throw new AuthenticationException("Error al consultar servicio de autenticacion",e);
        } catch (Exception e) {

            logger.error("Error when retrieving the authentication result for username {}: {}. " , username , e.getMessage(),e);
            throw new AuthenticationException("Error desconocido al consultar servicio de autenticacion",e);
        }

        throw new AuthenticationException("Nombre de usuario y/o contraseña incorrectos");
    }



}
