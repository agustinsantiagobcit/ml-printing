
package cl.metlife.ws.clients.altiuz;

import javax.xml.bind.annotation.*;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="context" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operation">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="DOC_COUNT"/>
 *               &lt;enumeration value="DOC_HIT_LIST"/>
 *               &lt;enumeration value="XML"/>
 *               &lt;enumeration value="XSL"/>
 *               &lt;enumeration value="TXT"/>
 *               &lt;enumeration value="PDF"/>
 *               &lt;enumeration value="TXT_BY_ID"/>
 *               &lt;enumeration value="XML_BY_ID"/>
 *               &lt;enumeration value="PDF_BY_ID"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="parameters" type="{http://altiuz.cl/reports/schemas}parameters"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "QueryRequest")
public class QueryRequest {

    @XmlElement(required = true)
    protected String context;
    @XmlElement(required = true)
    protected String operation;
    @XmlElement(required = true)
    protected Parameters parameters;

    /**
     * Obtiene el valor de la propiedad context.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContext() {
        return context;
    }

    /**
     * Define el valor de la propiedad context.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContext(String value) {
        this.context = value;
    }

    /**
     * Obtiene el valor de la propiedad operation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Define el valor de la propiedad operation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Obtiene el valor de la propiedad parameters.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Define el valor de la propiedad parameters.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

}
