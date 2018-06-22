/**
 * ROLES_PERSONA_RUT_APLICResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class ROLES_PERSONA_RUT_APLICResponse  implements java.io.Serializable {
    private cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult ROLES_PERSONA_RUT_APLICResult;

    public ROLES_PERSONA_RUT_APLICResponse() {
    }

    public ROLES_PERSONA_RUT_APLICResponse(
           cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult ROLES_PERSONA_RUT_APLICResult) {
           this.ROLES_PERSONA_RUT_APLICResult = ROLES_PERSONA_RUT_APLICResult;
    }


    /**
     * Gets the ROLES_PERSONA_RUT_APLICResult value for this ROLES_PERSONA_RUT_APLICResponse.
     * 
     * @return ROLES_PERSONA_RUT_APLICResult
     */
    public cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult getROLES_PERSONA_RUT_APLICResult() {
        return ROLES_PERSONA_RUT_APLICResult;
    }


    /**
     * Sets the ROLES_PERSONA_RUT_APLICResult value for this ROLES_PERSONA_RUT_APLICResponse.
     * 
     * @param ROLES_PERSONA_RUT_APLICResult
     */
    public void setROLES_PERSONA_RUT_APLICResult(cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult ROLES_PERSONA_RUT_APLICResult) {
        this.ROLES_PERSONA_RUT_APLICResult = ROLES_PERSONA_RUT_APLICResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ROLES_PERSONA_RUT_APLICResponse)) return false;
        ROLES_PERSONA_RUT_APLICResponse other = (ROLES_PERSONA_RUT_APLICResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ROLES_PERSONA_RUT_APLICResult==null && other.getROLES_PERSONA_RUT_APLICResult()==null) || 
             (this.ROLES_PERSONA_RUT_APLICResult!=null &&
              this.ROLES_PERSONA_RUT_APLICResult.equals(other.getROLES_PERSONA_RUT_APLICResult())));
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
        if (getROLES_PERSONA_RUT_APLICResult() != null) {
            _hashCode += getROLES_PERSONA_RUT_APLICResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROLES_PERSONA_RUT_APLICResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ROLES_PERSONA_RUT_APLICResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROLES_PERSONA_RUT_APLICResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ROLES_PERSONA_RUT_APLICResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>ROLES_PERSONA_RUT_APLICResponse>ROLES_PERSONA_RUT_APLICResult"));
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
