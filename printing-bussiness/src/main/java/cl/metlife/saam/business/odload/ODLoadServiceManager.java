package cl.metlife.saam.business.odload;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.ws.clients.od.OnDemandLoader;
import cl.metlife.ws.clients.od.OnDemandLoaderService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by BluePrints Developer on 13-02-2017.
 */
@Singleton
public class ODLoadServiceManager {

    private final String WSDL_CONFIG_KEY = "od.load.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY = "od.load.service.endpoint";

    @EJB
    ConfigurationManager configurationManager;

    private OnDemandLoader onDemandLoader;

    public OnDemandLoader getService(){
        if (onDemandLoader == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY).getValor();

            OnDemandLoaderService service = null;
            try {
                service = new OnDemandLoaderService(new URL(wsdl_url), new QName("http://ws.ondemand.metlife.cl/", "OnDemandLoaderService"));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.onDemandLoader = service.getOnDemandLoaderPort();
            setEndpointAddress(onDemandLoader, endpoint_url);
        }
        return onDemandLoader;
    }


    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }



}
