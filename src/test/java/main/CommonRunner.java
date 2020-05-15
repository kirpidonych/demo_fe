package main;

import Demo.commonComponents.SolutionException;
import Demo.utils.Driver;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.model.CucumberFeature;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Arrays;

import static main.DemoSolutionTestSuite.driver;

public class CommonRunner {
    public static TestNGCucumberRunner testNGCucumberRunner;
    public static CucumberFeature cucumberFeatureG;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Runs Cucumber Feature", dataProvider = "features")
    public void testRunner(CucumberFeatureWrapper cucumberFeature) {
        cucumberFeatureG = cucumberFeature.getCucumberFeature();
        testNGCucumberRunner.runCucumber(cucumberFeatureG);
    }

    @AfterMethod(alwaysRun = true)
    public void catchException(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            SolutionException.forThis(driver).fullPageScreenShot(Arrays.toString(result.getParameters()));
        }
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
