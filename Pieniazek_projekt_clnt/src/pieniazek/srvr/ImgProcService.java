/**
 * ImgProcService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pieniazek.srvr;

public interface ImgProcService extends javax.xml.rpc.Service {
    public java.lang.String getImgProcAddress();

    public pieniazek.srvr.ImgProc getImgProc() throws javax.xml.rpc.ServiceException;

    public pieniazek.srvr.ImgProc getImgProc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
