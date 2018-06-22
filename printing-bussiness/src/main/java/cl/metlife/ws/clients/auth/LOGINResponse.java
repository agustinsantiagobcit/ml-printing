/**
 * LOGINResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class LOGINResponse  implements java.io.Serializable {
    private cl.metlife.ws.clients.auth.LOGINResponseLOGINResult LOGINResult;

    public LOGINResponse() {
    }

    public LOGINResponse(
           cl.metlife.ws.clients.auth.LOGINResponseLOGINResult LOGINResult) {
           this.LOGINResult = LOGINResult;
    }


    /**
     * Gets the LOGINResult value for this LOGINResponse.
     * 
     * @return LOGINResult
     */
    public cl.metlife.ws.clients.auth.LOGINResponseLOGINResult getLOGINResult() {
        return LOGINResult;
    }


    /**
     * Sets the LOGINResult value for this LOGINResponse.
     * 
     * @param LOGINResult
     */
    public void setLOGINResult(cl.metlife.ws.clients.auth.LOGINResponseLOGINResult LOGINResult) {
        this.LOGINResult = LOGINResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LOGINResponse)) return false;
        LOGINResponse other = (LOGINResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LOGINResult==null && other.getLOGINResult()==null) || 
             (this.LOGINResult!=null &&
              this.LOGINResult.equals(other.getLOGINResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getLOGINResult() != null) {
            _hashCode += getLOGINResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LOGINResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">LOGINResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOGINResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LOGINResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>LOGINResponse>LOGINResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
