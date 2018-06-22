/**
 * LOGIN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class LOGIN  implements java.io.Serializable {
    private String vl_login;

    private String clave;

    public LOGIN() {
    }

    public LOGIN(
           String vl_login,
           String clave) {
           this.vl_login = vl_login;
           this.clave = clave;
    }


    /**
     * Gets the vl_login value for this LOGIN.
     * 
     * @return vl_login
     */
    public String getVl_login() {
        return vl_login;
    }


    /**
     * Sets the vl_login value for this LOGIN.
     * 
     * @param vl_login
     */
    public void setVl_login(String vl_login) {
        this.vl_login = vl_login;
    }


    /**
     * Gets the clave value for this LOGIN.
     * 
     * @return clave
     */
    public String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LOGIN.
     * 
     * @param clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LOGIN)) return false;
        LOGIN other = (LOGIN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.vl_login==null && other.getVl_login()==null) || 
             (this.vl_login!=null &&
              this.vl_login.equals(other.getVl_login()))) &&
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave())));
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
        if (getVl_login() != null) {
            _hashCode += getVl_login().hashCode();
        }
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LOGIN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">LOGIN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vl_login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "vl_login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
