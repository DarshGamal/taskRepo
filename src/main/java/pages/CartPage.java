package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
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
        double totalFromRows = 0.0;
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td.cart_description h4 a")).getText().trim();
            System.out.println("name : " + name);
            String qtyStr = row.findElement(By.cssSelector("td.cart_quantity button")).getText().trim();
            int quantity = Integer.parseInt(qtyStr);
            System.out.println("Quantity : " + quantity);
            String unitPriceText = row.findElement(By.cssSelector("td.cart_price p")).getText().trim();
            double unitPrice = ElementHelper.parsePrice(unitPriceText);
            System.out.println("price : " + unitPrice);

            String subTotalText = row.findElement(By.cssSelector("td.cart_total p")).getText().trim();
            double subTotal = ElementHelper.parsePrice(subTotalText);
            System.out.println("totalPrice : " + subTotal);
            // --- TestNG assertions ---
            AssertionHelper.assertFalse(name.isEmpty(), "Product name should not be empty");
            AssertionHelper.assertTrue(quantity > 0, "Quantity must be > 0");
            AssertionHelper.assertTrue(unitPrice > 0, "Unit price must be > 0");
            Assert.assertEquals(unitPrice * quantity, subTotal, 0.01,
                    "Row subtotal should equal unitPrice × quantity");

            totalFromRows += subTotal;
        }
        return this;
    }
}
