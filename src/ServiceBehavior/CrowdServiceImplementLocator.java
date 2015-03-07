/**
 * CrowdServiceImplementLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ServiceBehavior;

public class CrowdServiceImplementLocator extends org.apache.axis.client.Service implements ServiceBehavior.CrowdServiceImplement {

    public CrowdServiceImplementLocator() {
    }


    public CrowdServiceImplementLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CrowdServiceImplementLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_CrowdService
    private String BasicHttpBinding_CrowdService_address = "http://10.131.252.225:8732/WebServiceLibrary.CrowdService.svc";

    public String getBasicHttpBinding_CrowdServiceAddress() {
        return BasicHttpBinding_CrowdService_address;
    }

    // The WSDD service name defaults to the port name.
    private String BasicHttpBinding_CrowdServiceWSDDServiceName = "BasicHttpBinding_CrowdService";

    public String getBasicHttpBinding_CrowdServiceWSDDServiceName() {
        return BasicHttpBinding_CrowdServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_CrowdServiceWSDDServiceName(String name) {
        BasicHttpBinding_CrowdServiceWSDDServiceName = name;
    }

    public sutd.edu.sg.CrowdService getBasicHttpBinding_CrowdService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_CrowdService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_CrowdService(endpoint);
    }

    public sutd.edu.sg.CrowdService getBasicHttpBinding_CrowdService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_CrowdServiceStub _stub = new org.tempuri.BasicHttpBinding_CrowdServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_CrowdServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_CrowdServiceEndpointAddress(String address) {
        BasicHttpBinding_CrowdService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sutd.edu.sg.CrowdService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_CrowdServiceStub _stub = new org.tempuri.BasicHttpBinding_CrowdServiceStub(new java.net.URL(BasicHttpBinding_CrowdService_address), this);
                _stub.setPortName(getBasicHttpBinding_CrowdServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_CrowdService".equals(inputPortName)) {
            return getBasicHttpBinding_CrowdService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("ServiceBehavior", "CrowdServiceImplement");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ServiceBehavior", "BasicHttpBinding_CrowdService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_CrowdService".equals(portName)) {
            setBasicHttpBinding_CrowdServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
