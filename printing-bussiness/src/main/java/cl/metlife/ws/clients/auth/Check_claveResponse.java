/**
 * Check_claveResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class Check_claveResponse  implements java.io.Serializable {
    private cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult check_claveResult;

    public Check_claveResponse() {
    }

    public Check_claveResponse(
           cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult check_claveResult) {
           this.check_claveResult = check_claveResult;
    }


    /**
     * Gets the check_claveResult value for this Check_claveResponse.
     * 
     * @return check_claveResult
     */
    public cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult getCheck_claveResult() {
        return check_claveResult;
    }


    /**
     * Sets the check_claveResult value for this Check_claveResponse.
     * 
     * @param check_claveResult
     */
    public void setCheck_claveResult(cl.metlife.ws.clients.auth.Check_claveResponseCheck_claveResult check_claveResult) {
        this.check_claveResult = check_claveResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Check_claveResponse)) return false;
        Check_claveResponse other = (Check_claveResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.check_claveResult==null && other.getCheck_claveResult()==null) || 
             (this.check_claveResult!=null &&
              this.check_claveResult.equals(other.getCheck_claveResult())));
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
        if (getCheck_claveResult() != null) {
            _hashCode += getCheck_claveResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_claveResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">check_claveResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("check_claveResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "check_claveResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>check_claveResponse>check_claveResult"));
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
