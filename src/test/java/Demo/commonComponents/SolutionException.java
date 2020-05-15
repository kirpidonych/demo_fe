package Demo.commonComponents;

import cucumber.runtime.model.CucumberTagStatement;
import gherkin.formatter.model.Step;
import org.apache.commons.httpclient.HttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static main.CommonRunner.cucumberFeatureG;

public class SolutionException extends Exception {
    private WebDriver driver;
    private Logger LOGGER = LogManager.getLogger();

    public SolutionException(WebDriver driver) {
        this.driver = driver;
    }

    public static SolutionException forThis(WebDriver driver) {
        return new SolutionException(driver);
    }

    public void fullPageScreenShot(String methodName) {
        final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
                .takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = methodName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            File testTempImage = new File(testTempDir, newImageFileName);
            FileOutputStream osf;

            osf = new FileOutputStream(testTempImage);
            osf.write(imageInByte);
            fileABug(imageInByte);
            osf.flush();
            try {
                osf.close();
            } finally {
                LOGGER.info("Not able to close File Output Stream");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createATempDirectoryForScreenshots() {
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%sscreenshots", s, s);

        File testTempDir = new File(ourTestTempPathName);
        if (testTempDir.exists()) {
            if (!testTempDir.isDirectory()) {
                Assert.fail("Test path exists but is not a directory");
            }
        }
        return testTempDir;
    }

    public static void fileABug(byte[] imageInByte) {
        HttpClient httpClient = new HttpClient();
        XmlRpcClient rpcClient = new XmlRpcClient();
        XmlRpcCommonsTransportFactory factory = new XmlRpcCommonsTransportFactory(rpcClient);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

        factory.setHttpClient(httpClient);
        rpcClient.setTransportFactory(factory);
        try {
            config.setServerURL(new URL("/xmlrpc.cgi"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        rpcClient.setConfig(config);
        String testName = "";
        for (CucumberTagStatement element : cucumberFeatureG.getFeatureElements()){
            testName = element.getVisualName();
        }
        List<CucumberTagStatement> list = cucumberFeatureG.getFeatureElements();
        List<String> listS = new ArrayList<>();
       for (CucumberTagStatement item: list){
           List<Step> steps = item.getSteps();
           for (Step ignored : steps){
               listS.add(ignored.getName()+"\n");
           }
       }
        // map of the bug data
        Map bugMap = new HashMap();
        bugMap.put("api_key", "2zlzQrueNVGoPWqnp3XBFaZe1sKugrXrSsth3SYg");
        bugMap.put("product", "Demo");
        bugMap.put("component", "GUI");
        bugMap.put("summary", testName);
        bugMap.put("version", "1.0");
        bugMap.put("description", "This is text ");
        bugMap.put("op_sys", "Windows");
        bugMap.put("platform", "PC");
        bugMap.put("severity", "Normal");
        bugMap.put("comment", "\n"+Arrays.toString(listS.toArray()).replace("[","")
                .replace("]","").replace(",","")+"\t"+"<--- Failure on this step" );



        // create bug
        Object createResult = null;
        try {
            createResult = rpcClient.execute("Bug.create", new Object[]{bugMap});
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
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
        try {
            configAuto.setServerURL(new URL("/xmlrpc.cgi"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        rpcClientAuto.setConfig(configAuto);

        // map of the bug data
        Map bugMapAuto = new HashMap();
        bugMapAuto.put("api_key", "2zlzQrueNVGoPWqnp3XBFaZe1sKugrXrSsth3SYg");

        bugMapAuto.put("ids", value);
        bugMapAuto.put("summary", "Auto Test Attachment");
        bugMapAuto.put("content_type", "image/png");
        bugMapAuto.put("data", imageInByte);
        bugMapAuto.put("file_name", "screenshot.png");

        // create bug
        Object createResultAuto = null;
        try {
            createResultAuto = rpcClientAuto.execute("Bug.add_attachment", new Object[]{bugMapAuto});
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        System.err.println("createAttachmentResult = " + createResultAuto.toString());
    }
}