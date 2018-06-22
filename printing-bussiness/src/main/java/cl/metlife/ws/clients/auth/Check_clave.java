/**
 * Check_clave.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.metlife.ws.clients.auth;

public class Check_clave  implements java.io.Serializable {
    private String inrut;

    private String icclave;

    private String icsession;

    private String icidioma;

    public Check_clave() {
    }

    public Check_clave(
           String inrut,
           String icclave,
           String icsession,
           String icidioma) {
           this.inrut = inrut;
           this.icclave = icclave;
           this.icsession = icsession;
           this.icidioma = icidioma;
    }


    /**
     * Gets the inrut value for this Check_clave.
     * 
     * @return inrut
     */
    public String getInrut() {
        return inrut;
    }


    /**
     * Sets the inrut value for this Check_clave.
     * 
     * @param inrut
     */
    public void setInrut(String inrut) {
        this.inrut = inrut;
    }


    /**
     * Gets the icclave value for this Check_clave.
     * 
     * @return icclave
     */
    public String getIcclave() {
        return icclave;
    }


    /**
     * Sets the icclave value for this Check_clave.
     * 
     * @param icclave
     */
    public void setIcclave(String icclave) {
        this.icclave = icclave;
    }


    /**
     * Gets the icsession value for this Check_clave.
     * 
     * @return icsession
     */
    public String getIcsession() {
        return icsession;
    }


    /**
     * Sets the icsession value for this Check_clave.
     * 
     * @param icsession
     */
    public void setIcsession(String icsession) {
        this.icsession = icsession;
    }


    /**
     * Gets the icidioma value for this Check_clave.
     * 
     * @return icidioma
     */
    public String getIcidioma() {
        return icidioma;
    }


    /**
     * Sets the icidioma value for this Check_clave.
     * 
     * @param icidioma
     */
    public void setIcidioma(String icidioma) {
        this.icidioma = icidioma;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Check_clave)) return false;
        Check_clave other = (Check_clave) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inrut==null && other.getInrut()==null) || 
             (this.inrut!=null &&
              this.inrut.equals(other.getInrut()))) &&
            ((this.icclave==null && other.getIcclave()==null) || 
             (this.icclave!=null &&
              this.icclave.equals(other.getIcclave()))) &&
            ((this.icsession==null && other.getIcsession()==null) || 
             (this.icsession!=null &&
              this.icsession.equals(other.getIcsession()))) &&
            ((this.icidioma==null && other.getIcidioma()==null) || 
             (this.icidioma!=null &&
              this.icidioma.equals(other.getIcidioma())));
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
        if (getInrut() != null) {
            _hashCode += getInrut().hashCode();
        }
        if (getIcclave() != null) {
            _hashCode += getIcclave().hashCode();
        }
        if (getIcsession() != null) {
            _hashCode += getIcsession().hashCode();
        }
        if (getIcidioma() != null) {
            _hashCode += getIcidioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_clave.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">check_clave"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inrut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "inrut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icclave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icclave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icsession");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icsession"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icidioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "icidioma"));
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
