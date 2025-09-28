import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsDetailsPage;
import pages.ProductsPage;
import utiles.ExtentReports.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class SearchProductsTest extends BaseTest {
    ProductsPage productsPage;
    ProductsDetailsPage productsDetailsPage;

    @BeforeTest
    public void setUp() {

        productsPage = new ProductsPage();
        productsDetailsPage = new ProductsDetailsPage();
    }

    @Test(priority = 1)
    public void searchProducts() {
        productsPage.clickOnProductBtn()
                .validateItemsLoaded()
                .searchForProduct();
    }

    @Test(priority = 2)
    public void verifyProductDetails() {
        productsPage.validateSearchResults()
                .clickOnProductDetails();
        productsDetailsPage.validateProductDetails();

    }
}
