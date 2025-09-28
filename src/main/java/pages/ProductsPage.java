package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.ActionsHelper;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.LogsUtils;
import utiles.config.LoadProperties;

import java.util.List;

public class ProductsPage {
    WebDriver driver;
    By productsBtn = By.xpath("//a[@href='/products']");
    By productsLocator = By.cssSelector(".features_items .col-sm-4");
    By productPrice = By.cssSelector(".productinfo h2");
    By productImg = By.cssSelector("img");
    By searchBox = By.xpath("//input[@id='search_product']");
    By searchIcon = By.xpath("//button[@id='submit_search']");
    By productDetailsBtn = By.xpath("//li/a[@href='/product_details/28']");
    By product2 = By.xpath("//img[@src='/get_product_picture/2']");
    By addToCartBtn = By.xpath("//div[@class='productinfo text-center']/a[@data-product-id='2']");
    By viewCartBtn = By.linkText("View Cart");

    public ProductsPage() {
        this.driver = DriverManager.getDriver();
    }

    public ProductsPage clickOnProductBtn() {
        ElementHelper.click(driver, productsBtn);
        return this;
    }

    public ProductsPage validateItemsLoaded() {
        ElementHelper.waitForVisibility(driver, productsLocator);
        List<WebElement> products = driver.findElements(productsLocator);
        LogsUtils.info("number of products in page :" + products.size());
        ActionsHelper.validateItemsDisplayed(products, productPrice, productImg);
        return this;
    }

    public ProductsPage searchForProduct() {
        ElementHelper.sendText(driver, searchBox, "T-shirt");
        ElementHelper.click(driver, searchIcon);
        return this;
    }

    public ProductsPage validateSearchResults() {
        AssertionHelper.assertUrl(driver, LoadProperties.getProperty("SearchedUrl"));
        ElementHelper.waitForVisibility(driver, productsLocator);
        List<WebElement> products = driver.findElements(productsLocator);
        LogsUtils.info("number of products After search :" + products.size());
        ActionsHelper.validateItemsDisplayed(products, productPrice, productImg);
        return this;
    }

    public ProductsPage clickOnProductDetails() {
        ElementHelper.scrollToElement(driver, productDetailsBtn);
        ElementHelper.click(driver, productDetailsBtn);

        return this;
    }


    public ProductsPage hoverOnProduct2() {
        ElementHelper.scrollToElement(driver, product2);
        ElementHelper.hoverOver(driver, product2);
        return this;
    }

    public ProductsPage clickOnAddToCartBtn() {
        ElementHelper.scrollToElement(driver, addToCartBtn);
        ElementHelper.click(driver, addToCartBtn);
        return this;
    }

    public ProductsPage ViewCartPage() {
        ElementHelper.click(driver, viewCartBtn);
        return this;
    }
}
