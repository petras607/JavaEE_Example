/**
 * ImgProcServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pieniazek.srvr;

public class ImgProcServiceLocator extends org.apache.axis.client.Service implements pieniazek.srvr.ImgProcService {

    public ImgProcServiceLocator() {
    }


    public ImgProcServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ImgProcServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ImgProc
    private java.lang.String ImgProc_address = "http://localhost:8080/Pieniazek_projekt_srvr/services/ImgProc";

    public java.lang.String getImgProcAddress() {
        return ImgProc_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ImgProcWSDDServiceName = "ImgProc";

    public java.lang.String getImgProcWSDDServiceName() {
        return ImgProcWSDDServiceName;
    }

    public void setImgProcWSDDServiceName(java.lang.String name) {
        ImgProcWSDDServiceName = name;
    }

    public pieniazek.srvr.ImgProc getImgProc() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ImgProc_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getImgProc(endpoint);
    }

    public pieniazek.srvr.ImgProc getImgProc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            pieniazek.srvr.ImgProcSoapBindingStub _stub = new pieniazek.srvr.ImgProcSoapBindingStub(portAddress, this);
            _stub.setPortName(getImgProcWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setImgProcEndpointAddress(java.lang.String address) {
        ImgProc_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pieniazek.srvr.ImgProc.class.isAssignableFrom(serviceEndpointInterface)) {
                pieniazek.srvr.ImgProcSoapBindingStub _stub = new pieniazek.srvr.ImgProcSoapBindingStub(new java.net.URL(ImgProc_address), this);
                _stub.setPortName(getImgProcWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ImgProc".equals(inputPortName)) {
            return getImgProc();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://srvr.pieniazek", "ImgProcService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://srvr.pieniazek", "ImgProc"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ImgProc".equals(portName)) {
            setImgProcEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
