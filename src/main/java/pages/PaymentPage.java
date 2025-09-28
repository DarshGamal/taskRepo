package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

public class PaymentPage {
    WebDriver driver;
    By nameOnCard = By.xpath("//input[@data-qa='name-on-card']");
    By cardNumber = By.xpath("//input[@data-qa='card-number']");
    By cvc = By.xpath("//input[@data-qa='cvc']");
    By expirationMonth = By.xpath("//input[@data-qa='expiry-month']");
    By expirationYear = By.xpath("//input[@data-qa='expiry-year']");
    By payBtn = By.xpath("//button[@data-qa='pay-button']");

    public PaymentPage() {
        this.driver = DriverManager.getDriver();
    }

    public PaymentPage fillPaymentData(String NameOnCard, String CardNumber, String CVC, String ExpirationMonth, String ExpirationYear) {
        ElementHelper.sendText(driver, nameOnCard, NameOnCard);
        ElementHelper.sendText(driver, cardNumber, CardNumber);
        ElementHelper.sendText(driver, cvc, CVC);
        ElementHelper.sendText(driver, expirationMonth, ExpirationMonth);
        ElementHelper.sendText(driver, expirationYear, ExpirationYear);
        return this;
    }

    public PaymentPage clickOnPayBtn() {
        ElementHelper.scrollToElement(driver,payBtn);
        ElementHelper.click(driver, payBtn);
        return this;
    }

    public PaymentPage assertOnSuccessfulMessage() {
        WebElement actualMsg = ElementHelper.findElementByText("Order Placed!", driver);
        String expectedMsg = "ORDER PLACED!";
        AssertionHelper.assertEqual(actualMsg.getText(), expectedMsg);
        return this;
    }
}
