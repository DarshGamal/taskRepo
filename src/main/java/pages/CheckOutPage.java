package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
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
        ElementHelper.scrollToElement(driver,textArea);
        ElementHelper.sendText(driver, textArea, comment);
        return this;
    }

    public CheckOutPage clickOnPlaceOrderBtn() {
        ElementHelper.click(driver, placeOrderBtn);
        return this;
    }
}
