package lambda.odoorelated;


import java.net.URL;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class Xmlrputils {

    public Vector<String> getDatabaseList(String host, int port) {
        XmlRpcClient xmlrpcDb = new XmlRpcClient();
        Vector<String> res = new Vector<String>();

        XmlRpcClientConfigImpl xmlrpcConfigDb = new XmlRpcClientConfigImpl();
        xmlrpcConfigDb.setEnabledForExtensions(true);
        xmlrpcConfigDb.setServerURL(new URL("http", host, port, "/xmlrpc/db"));

        xmlrpcDb.setConfig(xmlrpcConfigDb);

        try {
            //Retrieve databases
            Vector<Object> params = new Vector<Object>();
            Object result = xmlrpcDb.execute("list", params);
            Object[] a = (Object[]) result;

            for (int i = 0; i < a.length; i++) {
                if (a[i] instanceof String) {
                    res.addElement((String) a[i]);
                }
            }
        } catch (XmlRpcException e) {
            logger.warn("XmlException Error while retrieving OpenERP Databases: ", e);
        } catch (Exception e) {
            logger.warn("Error while retrieving OpenERP Databases: ", e);
        }
        return res;
    }

}
