/**
 * CrowdWorker.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sutd.edu.sg;

public class CrowdWorker  implements java.io.Serializable {
    private Double cost;

    private Integer index;

    private Double reliability;

    private Long responseTime;

    private Boolean selected;

    public CrowdWorker(
           Double cost,
           Integer index,
           Double reliability,
           Long responseTime,
           Boolean selected) {
           this.cost = cost;
           this.index = index;
           this.reliability = reliability;
           this.responseTime = responseTime;
           this.selected = selected;
    }


    /**
     * Gets the cost value for this CrowdWorker.
     * 
     * @return cost
     */
    public Double getCost() {
        return cost;
    }


    /**
     * Sets the cost value for this CrowdWorker.
     * 
     * @param cost
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }


    /**
     * Gets the index value for this CrowdWorker.
     * 
     * @return index
     */
    public Integer getIndex() {
        return index;
    }


    /**
     * Sets the index value for this CrowdWorker.
     * 
     * @param index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }


    /**
     * Gets the reliability value for this CrowdWorker.
     * 
     * @return reliability
     */
    public Double getReliability() {
        return reliability;
    }


    /**
     * Sets the reliability value for this CrowdWorker.
     * 
     * @param reliability
     */
    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }


    /**
     * Gets the responseTime value for this CrowdWorker.
     * 
     * @return responseTime
     */
    public Long getResponseTime() {
        return responseTime;
    }


    /**
     * Sets the responseTime value for this CrowdWorker.
     * 
     * @param responseTime
     */
    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }


    /**
     * Gets the selected value for this CrowdWorker.
     * 
     * @return selected
     */
    public Boolean getSelected() {
        return selected;
    }


    /**
     * Sets the selected value for this CrowdWorker.
     * 
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CrowdWorker)) return false;
        CrowdWorker other = (CrowdWorker) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cost==null && other.getCost()==null) || 
             (this.cost!=null &&
              this.cost.equals(other.getCost()))) &&
            ((this.index==null && other.getIndex()==null) || 
             (this.index!=null &&
              this.index.equals(other.getIndex()))) &&
            ((this.reliability==null && other.getReliability()==null) || 
             (this.reliability!=null &&
              this.reliability.equals(other.getReliability()))) &&
            ((this.responseTime==null && other.getResponseTime()==null) || 
             (this.responseTime!=null &&
              this.responseTime.equals(other.getResponseTime()))) &&
            ((this.selected==null && other.getSelected()==null) || 
             (this.selected!=null &&
              this.selected.equals(other.getSelected())));
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
        if (getCost() != null) {
            _hashCode += getCost().hashCode();
        }
        if (getIndex() != null) {
            _hashCode += getIndex().hashCode();
        }
        if (getReliability() != null) {
            _hashCode += getReliability().hashCode();
        }
        if (getResponseTime() != null) {
            _hashCode += getResponseTime().hashCode();
        }
        if (getSelected() != null) {
            _hashCode += getSelected().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrowdWorker.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("sg.edu.sutd", "CrowdWorker"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cost");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("index");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "index"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reliability");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "reliability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "responseTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selected");
        elemField.setXmlName(new javax.xml.namespace.QName("sg.edu.sutd", "selected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
