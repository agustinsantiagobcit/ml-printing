/**
 * ROLES_PERSONA_RUT_APLIC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class ROLES_PERSONA_RUT_APLIC  implements java.io.Serializable {
    private String ID_Aplicacion;

    private String login;

    public ROLES_PERSONA_RUT_APLIC() {
    }

    public ROLES_PERSONA_RUT_APLIC(
           String ID_Aplicacion,
           String login) {
           this.ID_Aplicacion = ID_Aplicacion;
           this.login = login;
    }


    /**
     * Gets the ID_Aplicacion value for this ROLES_PERSONA_RUT_APLIC.
     * 
     * @return ID_Aplicacion
     */
    public String getID_Aplicacion() {
        return ID_Aplicacion;
    }


    /**
     * Sets the ID_Aplicacion value for this ROLES_PERSONA_RUT_APLIC.
     * 
     * @param ID_Aplicacion
     */
    public void setID_Aplicacion(String ID_Aplicacion) {
        this.ID_Aplicacion = ID_Aplicacion;
    }


    /**
     * Gets the login value for this ROLES_PERSONA_RUT_APLIC.
     * 
     * @return login
     */
    public String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this ROLES_PERSONA_RUT_APLIC.
     * 
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ROLES_PERSONA_RUT_APLIC)) return false;
        ROLES_PERSONA_RUT_APLIC other = (ROLES_PERSONA_RUT_APLIC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID_Aplicacion==null && other.getID_Aplicacion()==null) || 
             (this.ID_Aplicacion!=null &&
              this.ID_Aplicacion.equals(other.getID_Aplicacion()))) &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin())));
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
        if (getID_Aplicacion() != null) {
            _hashCode += getID_Aplicacion().hashCode();
        }
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROLES_PERSONA_RUT_APLIC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ROLES_PERSONA_RUT_APLIC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID_Aplicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ID_Aplicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Login"));
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
