package Demo.stepdefs.UCCStepdefs.channelStepdefs;

import Demo.commonComponents.WaitFor;
import Demo.pageObjects.FrontPagePO;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static main.DemoSolutionTestSuite.driver;

public class HomePageStepdefs implements En {
    public HomePageStepdefs() {
        Then("^I see Home Page$", () -> {
            FrontPagePO.forThis(driver).waitForPageToLoad();
        });
        And("^I can Select \"([^\"]*)\" item from the menu$", (String arg0) -> {
            if (arg0.equals("Design & Build")){
                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.xpath("//a[@href='/design-and-build/']"));
                new WaitFor(driver).isElementFound(By.xpath("//nav[@id='top-menu-nav']//li[@id='menu-item-23696']//ul[@class='sub-menu']//li[1]"));
                action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//nav[@id='top-menu-nav']//li[@id='menu-item-23696']//ul[@class='sub-menu']//li[1]"))).click().build().perform();
            }
            else if (arg0.equals("Who We Are")){
                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.xpath("//ul[@id='top-menu']//li[@id='menu-item-22519']//a[@href='#'][contains(text(),'Who we are')]"));
                new WaitFor(driver).isElementFound(By.xpath("//nav[@id='top-menu-nav']//li[@id='menu-item-22519']//ul[@class='sub-menu']//li[3]"));
                action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//nav[@id='top-menu-nav']//li[@id='menu-item-22519']//ul[@class='sub-menu']//li[3]"))).click().build().perform();
            }
             });
        When("^I press \"([^\"]*)\"$", (String arg0) -> {
            if (arg0.equals("Connect To Engage")){
                FrontPagePO.forThis(driver).pressConnectToEngage();
            } else if (arg0.equals("Get in touch")){
                FrontPagePO.forThis(driver).pressGetInTouch();
            }
        });
        Then("^I fill out the form with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$", (String arg0, String arg1, String arg2, String arg3, String arg4) -> {
            FrontPagePO.forThis(driver).filloutName(arg0);
            FrontPagePO.forThis(driver).filloutEmail(arg1);
            FrontPagePO.forThis(driver).filloutCompany(arg2);
            FrontPagePO.forThis(driver).filloutJobTitle(arg3);
            FrontPagePO.forThis(driver).filloutMessage(arg4);

        });
        Then("^I apply to one of the positions$", () -> {
            FrontPagePO.forThis(driver).apply();
        });
        Then("^I fill out the form with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$", (String arg0, String arg1, String arg2, String arg3) -> {
            FrontPagePO.forThis(driver).filloutName1(arg0);
            FrontPagePO.forThis(driver).filloutEmail2(arg1);
            FrontPagePO.forThis(driver).filloutPhone2(arg2);
            FrontPagePO.forThis(driver).filloutJobTitle2(arg3);
            FrontPagePO.forThis(driver).pressClose();
        });
        And("^I expect form to be submitted$", () -> {
            FrontPagePO.forThis(driver).dontSeeSubmitBtn();
        });
    }
}
