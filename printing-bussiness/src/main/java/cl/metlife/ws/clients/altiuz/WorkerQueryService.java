
package cl.metlife.ws.clients.altiuz;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WorkerQueryService", targetNamespace = "http://altiuz.cl/reports/definitions", wsdlLocation = "http://mlfprdibmarp.alico.corp:9080/ws/worker.wsdl")
//@WebServiceClient(name = "WorkerQueryService", targetNamespace = "http://altiuz.cl/reports/definitions", wsdlLocation = "http://mlfdesibmarp.alico.corp:9080/ws/worker.wsdl")
public class WorkerQueryService
    extends Service
{

    private  static URL WORKERQUERYSERVICE_WSDL_LOCATION;
    private final static WebServiceException WORKERQUERYSERVICE_EXCEPTION;
    private final static QName WORKERQUERYSERVICE_QNAME = new QName("http://altiuz.cl/reports/definitions", "WorkerQueryService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://mlfprdibmarp.alico.corp:9080/ws/worker.wsdl");
            //url = new URL("http://mlfdesibmarp.alico.corp:9080/ws/worker.wsdl");

        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WORKERQUERYSERVICE_WSDL_LOCATION = url;
        WORKERQUERYSERVICE_EXCEPTION = e;
    }

    public WorkerQueryService(String wsdl) throws MalformedURLException {
        //super(__getWsdlLocation(), WORKERQUERYSERVICE_QNAME);
        super(new URL(wsdl), WORKERQUERYSERVICE_QNAME);

        URL url = null;
        try {
            url = new URL(wsdl);
            //url = new URL("http://mlfdesibmarp.alico.corp:9080/ws/worker.wsdl");

        } catch (MalformedURLException ex) {
            throw new WebServiceException(ex);
        }
        WORKERQUERYSERVICE_WSDL_LOCATION = url;

    }

    public WorkerQueryService(URL wsdlLocation) {
        super(wsdlLocation, WORKERQUERYSERVICE_QNAME);
    }

    public WorkerQueryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }


    /**
     * 
     * @return
     *     returns WorkerQuery
     */
    @WebEndpoint(name = "WorkerQuerySoap11")
    public WorkerQuery getWorkerQuerySoap11() {

        return super.getPort(new QName("http://altiuz.cl/reports/definitions", "WorkerQuerySoap11"), WorkerQuery.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WorkerQuery
     */
    @WebEndpoint(name = "WorkerQuerySoap11")
    public WorkerQuery getWorkerQuerySoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://altiuz.cl/reports/definitions", "WorkerQuerySoap11"), WorkerQuery.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WORKERQUERYSERVICE_EXCEPTION!= null) {
            throw WORKERQUERYSERVICE_EXCEPTION;
        }
        return WORKERQUERYSERVICE_WSDL_LOCATION;
    }

}
