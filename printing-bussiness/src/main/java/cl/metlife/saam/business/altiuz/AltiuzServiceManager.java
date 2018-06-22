package cl.metlife.saam.business.altiuz;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.ws.clients.altiuz.WorkerQuery;
import cl.metlife.ws.clients.altiuz.WorkerQueryService;

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
public class AltiuzServiceManager {

    private final String WSDL_CONFIG_KEY = "altiuz.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY = "altiuz.service.endpoint";

    @EJB
    ConfigurationManager configurationManager;

    private WorkerQuery workerQuery;

    public WorkerQuery getService(){
        if (workerQuery == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY).getValor();

            WorkerQueryService service = null;
            try {
                service = new WorkerQueryService(new URL(wsdl_url), new QName("http://altiuz.cl/reports/definitions", "WorkerQueryService"));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.workerQuery = service.getWorkerQuerySoap11();
            setEndpointAddress(workerQuery, endpoint_url);
        }
        return workerQuery;
    }


    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }

}
