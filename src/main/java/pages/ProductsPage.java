package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

public class ProductsPage {
    WebDriver driver;
    By productsBtn = By.xpath("//a[@href='/products']");
    By Products = By.xpath("//div[@class='col-sm-9 padding-right']");
    By searchBox = By.xpath("//input[@id='search_product']");
    By searchIcon = By.xpath("//button[@id='submit_search']");
    By searchResults = By.xpath("//div[@class='features_items']");
    By productDetailsBtn = By.xpath("//li/a[@href='/product_details/28']");
    By product2=By.xpath("//img[@src='/get_product_picture/2']");
    By addToCartBtn=By.xpath("//div[@class='productinfo text-center']/a[@data-product-id='2']");
    By viewCartBtn=By.linkText("View Cart");
    public ProductsPage() {
        this.driver = DriverManager.getDriver();
    }

    public ProductsPage clickOnProductBtn() {
        ElementHelper.click(driver, productsBtn);
        return this;
    }

    public ProductsPage searchForProduct() {
        //    System.out.println(ElementHelper.getElements(driver,Products).size());
        ElementHelper.sendText(driver, searchBox, "T-shirt");
        ElementHelper.click(driver, searchIcon);
        return this;
    }

    public ProductsPage validateSearchResults() {
        AssertionHelper.assertUrl(driver, "https://www.automationexercise.com/products?search=T-shirt");
        AssertionHelper.assertElementPresent(driver, searchResults);
        return this;
    }

    public ProductsPage clickOnProductDetails() {
       // ElementHelper.click(driver, productDetailsBtn);
        ElementHelper.scrollToElement(driver,productDetailsBtn);
        ElementHelper.click(driver,productDetailsBtn);

        return this;
    }

    public ProductsPage validateProductDetails() {
        WebElement productCategory = ElementHelper.findElementByTextContains("Category: Men", driver);
        WebElement productPrice = ElementHelper.findElementByTextContains("1299", driver);
        WebElement productDesc = ElementHelper.findElementByTextContains("Stock", driver);
        Assert.assertTrue(productDesc.isDisplayed(), "❌ Element not displayed:");
        Assert.assertTrue(productCategory.isDisplayed(), "❌ Element not displayed:");
        Assert.assertTrue(productPrice.isDisplayed(), "❌ Element not displayed:");
        return this;
    }
    public ProductsPage hoverOnProduct2(){
        ElementHelper.scrollToElement(driver,product2);
        ElementHelper.hoverOver(driver,product2);
        return this;
    }
    public ProductsPage clickOnAddToCartBtn(){
        ElementHelper.scrollToElement(driver,addToCartBtn);
        ElementHelper.click(driver,addToCartBtn);
        return this;
    }
    public ProductsPage ViewCartPage(){
        ElementHelper.click(driver,viewCartBtn);
        return this;
    }
}
