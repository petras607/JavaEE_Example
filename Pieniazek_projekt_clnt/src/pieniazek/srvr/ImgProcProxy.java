package pieniazek.srvr;

public class ImgProcProxy implements pieniazek.srvr.ImgProc {
  private String _endpoint = null;
  private pieniazek.srvr.ImgProc imgProc = null;
  
  public ImgProcProxy() {
    _initImgProcProxy();
  }
  
  public ImgProcProxy(String endpoint) {
    _endpoint = endpoint;
    _initImgProcProxy();
  }
  
  private void _initImgProcProxy() {
    try {
      imgProc = (new pieniazek.srvr.ImgProcServiceLocator()).getImgProc();
      if (imgProc != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)imgProc)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)imgProc)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (imgProc != null)
      ((javax.xml.rpc.Stub)imgProc)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pieniazek.srvr.ImgProc getImgProc() {
    if (imgProc == null)
      _initImgProcProxy();
    return imgProc;
  }
  
  public java.lang.String startProc(java.lang.String path, int mode) throws java.rmi.RemoteException{
    if (imgProc == null)
      _initImgProcProxy();
    return imgProc.startProc(path, mode);
  }
  
  
}