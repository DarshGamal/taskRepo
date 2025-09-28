package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.ActionsHelper;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

import java.util.List;

public class CartPage {
    WebDriver driver;
    By cartTable = By.cssSelector("table.table-condensed tbody");
    By tableRows = By.cssSelector("#cart_info_table tbody tr");

    public CartPage() {
        this.driver = DriverManager.getDriver();
    }

    public CartPage verifyProductsOnCartPage() {
        WebElement table = ElementHelper.waitForVisibility(driver, cartTable);
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        ActionsHelper.validateProductsOnCart(rows);
        return this;
    }

    public CartPage CheckCartBadgeUpdates() {
        int items = getItemsCount();
        AssertionHelper.assertTrue(items >= 2, "Cart does not contain two items");
        return this;
    }

    public int getItemsCount() {
        List<WebElement> rows = ElementHelper.getElements(driver, tableRows);
        return rows.size();
    }
}
