package Demo.stepdefs;

import Demo.commonComponents.SolutionException;
import cucumber.api.java8.En;
import main.DemoSolutionTestSuite;

import static main.DemoSolutionTestSuite.driver;

public class CommonStepdefs implements En {
    public CommonStepdefs() {
        When("^I get to the resource$", () -> {
            DemoSolutionTestSuite.setupSuite();
        });
        And("^I make a screen shot of current page$", () -> {
            new SolutionException(driver).fullPageScreenShot("Screen_shot");
        });
    }
}
