package Demo.pageObjects;

import Demo.commonComponents.BasicElements;
import Demo.commonComponents.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FrontPagePO extends BasicElements {
    private static By logo = By.cssSelector("[alt='']");

    public FrontPagePO(WebDriver driver) {
        super(driver);
    }

    public static FrontPagePO forThis(WebDriver driver) {
        return new FrontPagePO(driver);
    }

    public void waitForPageToLoad() {
        new WaitFor(driver).isElementFound(logo);
    }

    public void pressConnectToEngage() {
        click(By.xpath("//a[contains(@class,'et_pb_button_3 et_pb_module et_pb_bg_layout_light')]"));
    }

    public void filloutName(String arg0) {
        setText(By.cssSelector("[name='Contact-Name']"),arg0);
    }

    public void filloutEmail(String arg0) {
        setText(By.cssSelector("[type='email']"),arg0);

    }

    public void filloutCompany(String arg0) {
        setText(By.cssSelector("[name='Contact-Company']"),arg0);

    }

    public void filloutJobTitle(String arg0) {
        setText(By.cssSelector("[name='Contact-Job-Title']"),arg0);

    }

    public void filloutMessage(String arg0) {
        setText(By.cssSelector("[name='Contact-Message']"),arg0);

    }

    public void apply() {
        new WaitFor(driver).isElementFound(By.xpath("//a[@href='/internal_jobs/technical-recruiter/']"));
        click(By.xpath("//a[@href='/internal_jobs/technical-recruiter/']"));
        click(By.xpath("//div[@class='internal-apply']"));
    }

    public void filloutName1(String arg0) {
        setText(By.cssSelector("[name='Applicant-Name']"),arg0);
    }

    public void filloutEmail2(String arg0) {
        setText(By.cssSelector("[name='Applicant-Email']"),arg0);

    }

    public void filloutPhone2(String arg0) {
        setText(By.cssSelector("[name='Applicant-Phone']"),arg0);

    }

    public void filloutJobTitle2(String arg0) {
        setText(By.cssSelector("[name='Applicant-Job-Title']"),arg0);

    }

    public void pressClose() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("[class='close-reveal-modal']")));
    }

    public void pressGetInTouch() {
        click(By.cssSelector("[type='submit']"));
    }

    public void dontSeeSubmitBtn() {
        Assert.assertFalse(new WaitFor(driver).isElementFound(By.cssSelector("[type='submit']")),"Form was not Submitted.");
    }
}
