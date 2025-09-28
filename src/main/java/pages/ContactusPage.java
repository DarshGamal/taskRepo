package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.LogsUtils;
import utiles.config.LoadProperties;

public class ContactusPage {
    WebDriver driver;
    By contactusBtn = By.xpath("//a[@href='/contact_us']");
    By name = By.xpath("//input[@data-qa='name']");
    By email = By.xpath("//input[@data-qa='email']");
    By subject = By.xpath("//input[@data-qa='subject']");
    By message = By.xpath("//textarea[@data-qa='message']");
    By submitBtn = By.xpath("//input[@data-qa='submit-button']");
    By actualMessage = By.xpath("//div[@class='status alert alert-success']");

    public ContactusPage() {
        this.driver = DriverManager.getDriver();
    }

    public ContactusPage clickOnContactusBtn() {
        ElementHelper.click(driver, contactusBtn);
        return this;
    }

    public ContactusPage fillContactUsData(String Name, String Email, String Subject, String MSG) {
        ElementHelper.sendText(driver, name, Name);
        ElementHelper.sendText(driver, email, Email);
        ElementHelper.sendText(driver, subject, Subject);
        ElementHelper.sendText(driver, message, MSG);
        return this;
    }

    public ContactusPage clickOnSubmitBtn() {
        ElementHelper.click(driver, submitBtn);
        Alert alert = this.driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public ContactusPage assertOnSuccessfulMessage() {
        String actual = ElementHelper.findElementBy(driver, actualMessage).getText();
        LogsUtils.info("Actual message: ",actual);
        String expected = LoadProperties.getProperty("ContactusSuccessfulMsg");
        LogsUtils.info("Expected message: ",expected);
        AssertionHelper.assertEqual(actual, expected);
        return this;
    }

}
