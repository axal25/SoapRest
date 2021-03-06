package soap.api.wsconsume.autogen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.6
 * 2019-06-10T17:26:15.569+02:00
 * Generated source version: 3.2.6
 *
 */
@WebServiceClient(name = "WebServiceEJB_ServiceName",
                  wsdlLocation = "http://localhost:8080/soap-api-ejb/SingletonSessionBeanWebService?wsdl",
                  targetNamespace = "http://api.soap/")
public class WebServiceEJBServiceName extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://api.soap/", "WebServiceEJB_ServiceName");
    public final static QName WebServiceEJBPortName = new QName("http://api.soap/", "WebServiceEJB_PortName");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/soap-api-ejb/SingletonSessionBeanWebService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WebServiceEJBServiceName.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/soap-api-ejb/SingletonSessionBeanWebService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WebServiceEJBServiceName(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WebServiceEJBServiceName(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceEJBServiceName() {
        super(WSDL_LOCATION, SERVICE);
    }





    /**
     *
     * @return
     *     returns WebServiceEJBName
     */
    @WebEndpoint(name = "WebServiceEJB_PortName")
    public WebServiceEJBName getWebServiceEJBPortName() {
        return super.getPort(WebServiceEJBPortName, WebServiceEJBName.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceEJBName
     */
    @WebEndpoint(name = "WebServiceEJB_PortName")
    public WebServiceEJBName getWebServiceEJBPortName(WebServiceFeature... features) {
        return super.getPort(WebServiceEJBPortName, WebServiceEJBName.class, features);
    }

}
