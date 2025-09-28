package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

public class CheckOutPage {
    WebDriver driver;
    By textArea = By.xpath("//textarea");
    By placeOrderBtn = By.linkText("Place Order");

    public CheckOutPage() {
        this.driver = DriverManager.getDriver();
    }

    public CheckOutPage clickOnCheckoutBtn() {
        ElementHelper.findElementByText("Proceed To Checkout", driver).click();
        return this;
    }

    public CheckOutPage addOrderComment(String comment) {
        ElementHelper.scrollToElement(driver, textArea);
        ElementHelper.sendText(driver, textArea, comment);
        return this;
    }

    public CheckOutPage clickOnPlaceOrderBtn() {
        ElementHelper.scrollToElement(driver, placeOrderBtn);
        ElementHelper.click(driver, placeOrderBtn);
        return this;
    }

    public CheckOutPage reviewDeliveryAddress() {
        WebElement Address = ElementHelper.findElementByTextContains("delivery address", driver);
        WebElement country = ElementHelper.findElementByTextContains("India", driver);
        AssertionHelper.assertWebElementPresent(driver, Address);
        AssertionHelper.assertWebElementPresent(driver, country);
        return this;
    }
}
