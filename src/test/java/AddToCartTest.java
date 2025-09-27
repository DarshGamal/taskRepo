import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsDetailsPage;
import pages.ProductsPage;
import utiles.ExtentReports.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class AddToCartTest extends BaseTest {
    ProductsPage productsPage;
    ProductsDetailsPage productsDetailsPage;
    CartPage cartPage;

    @BeforeTest
    public void setUp() {
        productsPage = new ProductsPage();
        productsDetailsPage = new ProductsDetailsPage();
        cartPage=new CartPage();
    }

    @Test(priority = 1)
    public void addProduct1ToCart() {
        productsDetailsPage.chooseQuantity("2")
                .clickOnAddToCartBtn()
                .confirmSuccessMsg();

    }
    @Test(priority = 2)
    public void continueShopping(){
        productsDetailsPage .clickOnContinueShoppingBtn();
        productsPage.clickOnProductBtn();
    }

    @Test(priority = 3)
    public void addProduct2ToCart() {
        productsPage.hoverOnProduct2()
                .clickOnAddToCartBtn()
                .ViewCartPage();
        cartPage.verifyProductsOnCartPage();
    }

}
