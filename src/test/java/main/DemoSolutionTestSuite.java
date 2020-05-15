package main;

import Demo.utils.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Map;

import static Demo.frameworkConfiguration.FrameworkParameter.*;
import static Demo.frameworkConfiguration.SetSuite.getFrameworkSettings;


public class DemoSolutionTestSuite {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static WebDriver driver;
    public static String remoteTestEnvIPAddress;
    private static String appURI;

    @BeforeSuite(alwaysRun = true)
    public static void setupSuite() {
        getFrameworkSettings();
        LOGGER.info("Reading build parameters from Jenkins");
        Map<String, String> env = System.getenv();
        boolean isURI = false, isIP = false;

        for (String envName : env.keySet()) {
            switch (envName) {
                case "RemoteTestEnvIPAddress":
                    LOGGER.info("Getting IP address from system variables");
                    remoteTestEnvIPAddress = env.get(envName);
                    isIP = true;
                    break;
                case "AppURI":
                    LOGGER.info("Getting URI from system variables");
                    appURI = env.get(envName);
                    isURI = true;
                    break;
                default:
                    break;
            }
        }
        if (!isURI || !isIP) {
            LOGGER.info("Getting IP address from settings");
            remoteTestEnvIPAddress = getRemoteTestEnvIpAddress();
            LOGGER.info("Getting URI from settings");
            appURI = getTestEnvIpAddress();
        }
        driver = Driver.create(getBrowser(), appURI, true);
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriver() {
        Driver.cleanup();
    }

//    @AfterMethod(alwaysRun = true)
//    public void catchException(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            SolutionException.forThis(driver).fullPageScreenShot((result.getMethod().getMethodName()));
//        }
//    }
}
