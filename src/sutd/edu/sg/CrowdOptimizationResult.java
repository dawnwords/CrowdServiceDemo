/**
 * CrowdOptimizationResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sutd.edu.sg;

public class CrowdOptimizationResult  implements java.io.Serializable {
    private com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] 
    		crowdServiceSelection;

    private Double totalReliability;

    public CrowdOptimizationResult() {
    }

    public CrowdOptimizationResult(
           com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] crowdServiceSelection,
           Double totalReliability) {
           this.crowdServiceSelection = crowdServiceSelection;
           this.totalReliability = totalReliability;
    }


    /**
     * Gets the crowdServiceSelection value for this CrowdOptimizationResult.
     * 
     * @return crowdServiceSelection
     */
    public com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] getCrowdServiceSelection() {
        return crowdServiceSelection;
    }


    /**
     * Sets the crowdServiceSelection value for this CrowdOptimizationResult.
     * 
     * @param crowdServiceSelection
     */
    public void setCrowdServiceSelection(com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9[] crowdServiceSelection) {
        this.crowdServiceSelection = crowdServiceSelection;
    }


    /**
     * Gets the totalReliability value for this CrowdOptimizationResult.
     * 
     * @return totalReliability
     */
    public Double getTotalReliability() {
        return totalReliability;
    }


    /**
     * Sets the totalReliability value for this CrowdOptimizationResult.
     * 
     * @param totalReliability
     */
    public void setTotalReliability(Double totalReliability) {
        this.totalReliability = totalReliability;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CrowdOptimizationResult)) return false;
        CrowdOptimizationResult other = (CrowdOptimizationResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.crowdServiceSelection==null && other.getCrowdServiceSelection()==null) || 
             (this.crowdServiceSelection!=null &&
              java.util.Arrays.equals(this.crowdServiceSelection, other.getCrowdServiceSelection()))) &&
            ((this.totalReliability==null && other.getTotalReliability()==null) || 
             (this.totalReliability!=null &&
              this.totalReliability.equals(other.getTotalReliability())));
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
        if (getCrowdServiceSelection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCrowdServiceSelection());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCrowdServiceSelection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTotalReliability() != null) {
            _hashCode += getTotalReliability().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrowdOptimizationResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("sg.edu.sutd", "CrowdOptimizationResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crowdServiceSelection");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "crowdServiceSelection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringArrayOfCrowdWorker8Qgdyvm9>KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "KeyValueOfstringArrayOfCrowdWorker8Qgdyvm9"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalReliability");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "totalReliability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
