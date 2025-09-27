import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utiles.ExtentReports.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class SearchProductsTest extends BaseTest {
    ProductsPage productsPage;


    @BeforeTest
    public void setUp() {
        productsPage = new ProductsPage();
    }

    @Test(priority = 1)
    public void searchProducts() {
        productsPage.clickOnProductBtn()
                .searchForProduct();
    }
    @Test(priority = 2)
    public void verifyProductDetails(){
        productsPage.validateSearchResults()
                .clickOnProductDetails()
                .validateProductDetails();

    }
}
