
package cl.metlife.ws.clients.saam.execution;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.metlife.ws.clients.saam.execution package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InitProcessWithDataResponse_QNAME = new QName("http://printing.metlife.cl/", "initProcessWithDataResponse");
    private final static QName _InitProcess_QNAME = new QName("http://printing.metlife.cl/", "initProcess");
    private final static QName _InitProcessResponse_QNAME = new QName("http://printing.metlife.cl/", "initProcessResponse");
    private final static QName _InitProcessWithData_QNAME = new QName("http://printing.metlife.cl/", "initProcessWithData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.metlife.ws.clients.saam.execution
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InitProcessWithData }
     * 
     */
    public InitProcessWithData createInitProcessWithData() {
        return new InitProcessWithData();
    }

    /**
     * Create an instance of {@link InitProcessWithData.Parameters }
     * 
     */
    public InitProcessWithData.Parameters createInitProcessWithDataParameters() {
        return new InitProcessWithData.Parameters();
    }

    /**
     * Create an instance of {@link InitProcess }
     * 
     */
    public InitProcess createInitProcess() {
        return new InitProcess();
    }

    /**
     * Create an instance of {@link InitProcess.Arg2 }
     * 
     */
    public InitProcess.Arg2 createInitProcessArg2() {
        return new InitProcess.Arg2();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link InitProcessResponse }
     * 
     */
    public InitProcessResponse createInitProcessResponse() {
        return new InitProcessResponse();
    }

    /**
     * Create an instance of {@link InitProcessWithDataResponse }
     * 
     */
    public InitProcessWithDataResponse createInitProcessWithDataResponse() {
        return new InitProcessWithDataResponse();
    }

    /**
     * Create an instance of {@link InitProcessWithData.Parameters.Entry }
     * 
     */
    public InitProcessWithData.Parameters.Entry createInitProcessWithDataParametersEntry() {
        return new InitProcessWithData.Parameters.Entry();
    }

    /**
     * Create an instance of {@link InitProcess.Arg2 .Entry }
     * 
     */
    public InitProcess.Arg2 .Entry createInitProcessArg2Entry() {
        return new InitProcess.Arg2 .Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitProcessWithDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printing.metlife.cl/", name = "initProcessWithDataResponse")
    public JAXBElement<InitProcessWithDataResponse> createInitProcessWithDataResponse(InitProcessWithDataResponse value) {
        return new JAXBElement<InitProcessWithDataResponse>(_InitProcessWithDataResponse_QNAME, InitProcessWithDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printing.metlife.cl/", name = "initProcess")
    public JAXBElement<InitProcess> createInitProcess(InitProcess value) {
        return new JAXBElement<InitProcess>(_InitProcess_QNAME, InitProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printing.metlife.cl/", name = "initProcessResponse")
    public JAXBElement<InitProcessResponse> createInitProcessResponse( InitProcessResponse value) {
        return new JAXBElement<InitProcessResponse>(_InitProcessResponse_QNAME, InitProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitProcessWithData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printing.metlife.cl/", name = "initProcessWithData")
    public JAXBElement<InitProcessWithData> createInitProcessWithData(InitProcessWithData value) {
        return new JAXBElement<InitProcessWithData>(_InitProcessWithData_QNAME, InitProcessWithData.class, null, value);
    }

}
