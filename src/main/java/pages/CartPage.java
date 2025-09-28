package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.ActionsHelper;
import utiles.commonHelper.ElementHelper;
import java.util.List;

public class CartPage {
    WebDriver driver;
    By cartTable = By.cssSelector("table.table-condensed tbody");

    public CartPage() {
        this.driver = DriverManager.getDriver();
    }

    public CartPage verifyProductsOnCartPage() {
        WebElement table = ElementHelper.waitForVisibility(driver, cartTable);
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        ActionsHelper.validateProductsOnCart(rows);
        return this;
    }
}
