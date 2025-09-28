package utiles.commonHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ActionsHelper {
    public static double parsePrice(String text) {
        // Remove everything except digits and decimal point
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public static void validateProductsOnCart(List<WebElement> rows) {
        double totalFromRows = 0.0;
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td.cart_description h4 a")).getText().trim();
            LogsUtils.info("name of item in cart : " + name);
            String qtyStr = row.findElement(By.cssSelector("td.cart_quantity button")).getText().trim();
            int quantity = Integer.parseInt(qtyStr);
            LogsUtils.info("Quantity of item in cart : " + quantity);
            String unitPriceText = row.findElement(By.cssSelector("td.cart_price p")).getText().trim();
            double unitPrice = parsePrice(unitPriceText);
            LogsUtils.info("price of item in cart : " + unitPrice);
            String subTotalText = row.findElement(By.cssSelector("td.cart_total p")).getText().trim();
            double subTotal = parsePrice(subTotalText);
            LogsUtils.info("Total price of item in cart : " + subTotal);
            // --- TestNG assertions ---
            AssertionHelper.assertFalse(name.isEmpty(), "Product name should not be empty");
            AssertionHelper.assertTrue(quantity > 0, "Quantity must be > 0");
            AssertionHelper.assertTrue(unitPrice > 0, "Unit price must be > 0");
            Assert.assertEquals(unitPrice * quantity, subTotal, 0.01,
                    "Row subtotal should equal unitPrice × quantity");

            totalFromRows += subTotal;
        }
    }

    public static void validateItemsDisplayed(List<WebElement> products, By productPrice, By productImg ) {
        AssertionHelper.assertFalse(products.isEmpty(), "No products were loaded/displayed on the page.");
        for (WebElement product : products) {
            AssertionHelper.assertTrue(product.isDisplayed(), "Product card not visible: " + product);
            //Price should be visible
            WebElement priceElem = product.findElement(productPrice);
            AssertionHelper.assertTrue(priceElem.isDisplayed(), "Product price is not visible in product: " + product);
            // Image should be visible
            WebElement img = product.findElement(productImg);
            AssertionHelper.assertTrue(img.isDisplayed(), "Product image is not visible in product: " + product);
        }

    }

    public static void checkProductDetailsInfo(WebDriver driver, By name, By category, By price,By Desc) {
        AssertionHelper.assertElementPresent(driver, name);
        String nameText = driver.findElement(name).getText().trim();
        AssertionHelper.assertFalse(nameText.isEmpty(), "Product name should not be empty");
        LogsUtils.info("Product details Name : ", nameText);

        String categoryText = driver.findElement(category).getText().trim();
        AssertionHelper.assertTrue(categoryText.toLowerCase().startsWith("category"), "Category text should start with 'Category': " + categoryText);
        LogsUtils.info("Product details category : ", categoryText);

        String priceTxt = driver.findElement(price).getText().trim();
        AssertionHelper.assertFalse(priceTxt.isEmpty(), "Price should not be empty");
        //check that price contains digits
        Assert.assertTrue(priceTxt.matches(".*\\d+.*"), "Price should contain digits: " + priceTxt);
        LogsUtils.info("Product details Name : ", priceTxt);

        List<WebElement> detailElems = driver.findElements(Desc);
        StringBuilder descriptionBuilder = new StringBuilder();
        for (WebElement e : detailElems) {
            String txt = e.getText().trim();
            if (!txt.isEmpty()) {
                descriptionBuilder.append(txt).append("\n");
            }
        }
        String descriptionText = descriptionBuilder.toString().trim();
        AssertionHelper.assertFalse(descriptionText.isEmpty(), "Description/Details should not be empty");
        LogsUtils.info("Product details Description : ", descriptionText);

    }
}
