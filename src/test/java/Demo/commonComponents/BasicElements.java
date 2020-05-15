package Demo.commonComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 * This is Base Page Object implementing common for all pages behavior.
 */
public class BasicElements {
    private WaitFor wait;
    protected WebDriver driver;
    public Logger LOGGER = LogManager.getLogger();


    public BasicElements(WebDriver driver) {
        this.driver = driver;
        wait = new WaitFor(driver);
    }

    public <T> T click(T ele, boolean... expectRefresh) {
        final By body = By.cssSelector("body");
        wait.ForElementBePresent(ele);
        if (ele.getClass().equals(WebElement.class)) {
            ((WebElement) ele).click();
        } else {
            driver.findElement(((By) ele)).click();
        }
        if (expectRefresh.length > 0 && expectRefresh[0]) {
            wait.ForElementNotPresent(body);
        }
        return ele;
    }

    protected <T> T rightClick(T ele) {
        wait.ForElementBePresent(ele);
        wait.ForRefresh(ele);
        Actions action = new Actions(driver);
        if (ele.getClass().equals(WebElement.class)) {
            action.contextClick(((WebElement) ele)).build().perform();
        } else {
            action.contextClick(driver.findElement(((By) ele))).build().perform();
        }
        return ele;
    }

    protected <T> void setText(T ele, String symbols) {
        wait.ForElementBePresent(ele);
        driver.findElement((By) ele).sendKeys(symbols);
    }

    protected <T> String getText(T ele, boolean... isValue) {
        String textFromLabel = "";
        if (isValue.length > 0 && isValue[0]) {
            wait.ForElementBePresent(ele);
            if (ele.getClass().equals(RemoteWebElement.class)) {
                textFromLabel = ((WebElement) ele).getAttribute("value");
            } else {
                textFromLabel = driver.findElement((By) ele).getAttribute("value");
            }
        } else {
            wait.ForElementBePresent(ele);
            if (ele.getClass().equals(RemoteWebElement.class)) {
                textFromLabel = ((WebElement) ele).getText();
            } else {
                textFromLabel = driver.findElement((By) ele).getText();
            }
        }
        return textFromLabel;
    }
}