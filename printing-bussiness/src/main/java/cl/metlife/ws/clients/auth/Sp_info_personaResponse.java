/**
 * Sp_info_personaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class Sp_info_personaResponse  implements java.io.Serializable {
    private cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult sp_info_personaResult;

    public Sp_info_personaResponse() {
    }

    public Sp_info_personaResponse(
           cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult sp_info_personaResult) {
           this.sp_info_personaResult = sp_info_personaResult;
    }


    /**
     * Gets the sp_info_personaResult value for this Sp_info_personaResponse.
     * 
     * @return sp_info_personaResult
     */
    public cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult getSp_info_personaResult() {
        return sp_info_personaResult;
    }


    /**
     * Sets the sp_info_personaResult value for this Sp_info_personaResponse.
     * 
     * @param sp_info_personaResult
     */
    public void setSp_info_personaResult(cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult sp_info_personaResult) {
        this.sp_info_personaResult = sp_info_personaResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Sp_info_personaResponse)) return false;
        Sp_info_personaResponse other = (Sp_info_personaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sp_info_personaResult==null && other.getSp_info_personaResult()==null) || 
             (this.sp_info_personaResult!=null &&
              this.sp_info_personaResult.equals(other.getSp_info_personaResult())));
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
        if (getSp_info_personaResult() != null) {
            _hashCode += getSp_info_personaResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Sp_info_personaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">sp_info_personaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sp_info_personaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "sp_info_personaResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>sp_info_personaResponse>sp_info_personaResult"));
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
