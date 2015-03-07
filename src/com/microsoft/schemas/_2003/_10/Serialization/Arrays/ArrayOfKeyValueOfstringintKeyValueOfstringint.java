/**
 * ArrayOfKeyValueOfstringintKeyValueOfstringint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas._2003._10.Serialization.Arrays;

public class ArrayOfKeyValueOfstringintKeyValueOfstringint  implements java.io.Serializable {
    private String key;

    private int value;

    public ArrayOfKeyValueOfstringintKeyValueOfstringint() {
    }

    public ArrayOfKeyValueOfstringintKeyValueOfstringint(
           String key,
           int value) {
           this.key = key;
           this.value = value;
    }


    /**
     * Gets the key value for this ArrayOfKeyValueOfstringintKeyValueOfstringint.
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }


    /**
     * Sets the key value for this ArrayOfKeyValueOfstringintKeyValueOfstringint.
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Gets the value value for this ArrayOfKeyValueOfstringintKeyValueOfstringint.
     * 
     * @return value
     */
    public int getValue() {
        return value;
    }


    /**
     * Sets the value value for this ArrayOfKeyValueOfstringintKeyValueOfstringint.
     * 
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ArrayOfKeyValueOfstringintKeyValueOfstringint)) return false;
        ArrayOfKeyValueOfstringintKeyValueOfstringint other = (ArrayOfKeyValueOfstringintKeyValueOfstringint) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            this.value == other.getValue();
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        _hashCode += getValue();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArrayOfKeyValueOfstringintKeyValueOfstringint.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringint>KeyValueOfstringint"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
