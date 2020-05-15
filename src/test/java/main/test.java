package main;

import org.apache.commons.httpclient.HttpClient;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class test {
    public static void main(String[] args) throws XmlRpcException, MalformedURLException {
        HttpClient httpClient = new HttpClient();
        XmlRpcClient rpcClient = new XmlRpcClient();
        XmlRpcCommonsTransportFactory factory = new XmlRpcCommonsTransportFactory(rpcClient);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

        factory.setHttpClient(httpClient);
        rpcClient.setTransportFactory(factory);
        config.setServerURL(new URL("/xmlrpc.cgi"));
        rpcClient.setConfig(config);

        // map of the bug data
        Map bugMap = new HashMap();
        bugMap.put("api_key", "");

        bugMap.put("product", "TestProduct");
        bugMap.put("component", "TestComponent");
        bugMap.put("summary", "Bug created for test");
        bugMap.put("description", "This is text ");
        bugMap.put("version", "unspecified");
        bugMap.put("op_sys", "Windows");
        bugMap.put("platform", "PC");
        bugMap.put("severity", "Normal");


        // create bug
        Object createResult = rpcClient.execute("Bug.create", new Object[]{bugMap});
        System.err.println("createResult = " + createResult.toString());

        Map<String, Integer> result;
        result = (HashMap)createResult;
        int value = result.get("id");


        HttpClient httpClientAuto = new HttpClient();
        XmlRpcClient rpcClientAuto = new XmlRpcClient();
        XmlRpcCommonsTransportFactory factoryAuto = new XmlRpcCommonsTransportFactory(rpcClientAuto);
        XmlRpcClientConfigImpl configAuto = new XmlRpcClientConfigImpl();

        factoryAuto.setHttpClient(httpClientAuto);
        rpcClientAuto.setTransportFactory(factoryAuto);
        configAuto.setServerURL(new URL("xmlrpc.cgi"));
        rpcClientAuto.setConfig(configAuto);

        // map of the bug data
        Map bugMapAuto = new HashMap();
        bugMapAuto.put("api_key", "2zlzQrueNVGoPWqnp3XBFaZe1sKugrXrSsth3SYg");

        bugMapAuto.put("ids", value);
        bugMapAuto.put("summary", "Auto Test Attachment");
        bugMapAuto.put("content_type", "image/png");
        bugMapAuto.put("data", "ghjk");
        bugMapAuto.put("file_name", "screenshot.png");

        // create bug
        Object createResultAuto = rpcClientAuto.execute("Bug.add_attachment", new Object[]{bugMapAuto});
        System.err.println("createAttachmentResult = " + createResultAuto.toString());
    }
}