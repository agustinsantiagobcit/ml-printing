package cl.metlife.ws.clients.auth;

import cl.blueprintsit.framework.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
//@Startup
@Singleton(name = "MetlifeAuthenticationServiceInstanceEJB")
public class MetlifeAuthenticationServiceInstanceBean {

    static final Logger LOGGER = LoggerFactory.getLogger(MetlifeAuthenticationServiceInstanceBean.class);


    @EJB
    ConfigurationManager configurationBean;

    private ServiceSoap_PortType service;

    public ServiceSoap_PortType getServiceInstance(){

        if (service == null) {
            try {
                makeService();
            } catch (MalformedURLException e) {
                LOGGER.error("Error en URL servicio Authenticacion Metlife: {}",e.getMessage(),e);
            } catch (ServiceException e) {
                LOGGER.error("Error en servicio Authenticacion Metlife: {}",e.getMessage(),e);
            }
        }
        return service;


    }

    private void makeService() throws MalformedURLException, ServiceException {

        String url = configurationBean.getByKey("metlife.auth.service.endpoint").getValor();

        ServiceLocator serviceLocator = new ServiceLocator();
        URL endpoint = new URL(url);
        service = serviceLocator.getServiceSoap12(endpoint);

    }




}
