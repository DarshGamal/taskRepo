package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

public class ProductsDetailsPage {
    WebDriver driver;
    By Quantity = By.id("quantity");
    By addToCartButton = By.xpath("//button[@type='button']");
    By continueShoppingBtn=By.xpath("//button[@data-dismiss='modal']");
    public ProductsDetailsPage() {
        this.driver = DriverManager.getDriver();
    }

    public ProductsDetailsPage chooseQuantity(String quantity) {
        ElementHelper.sendText(driver, Quantity, quantity);
        return this;
    }

    public ProductsDetailsPage clickOnAddToCartBtn() {
        ElementHelper.click(driver, addToCartButton);
        return this;
    }

    public ProductsDetailsPage confirmSuccessMsg() {
        WebElement successMsg = ElementHelper.findElementByTextContains("Your product has been added to cart", driver);
        Assert.assertTrue(successMsg.isDisplayed(), "❌ Element not displayed:");
        return this;
    }
    public ProductsDetailsPage clickOnContinueShoppingBtn() {
        ElementHelper.click(driver, continueShoppingBtn);
        return this;
    }
}
