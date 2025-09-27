import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.PaymentPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)
public class CheckOutTest extends BaseTest {
    CheckOutPage checkOutPage;
    PaymentPage paymentPage;
    @BeforeTest
    public void setUp() {
        checkOutPage = new CheckOutPage();
        paymentPage=new PaymentPage();
    }

    @Test(priority = 1)
    public void ProceedToCheckout() {
        checkOutPage.clickOnCheckoutBtn()
                .addOrderComment("Please leave the package with the security guard.")
                .clickOnPlaceOrderBtn();
    }
    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/test-data/Payment-data.json");
    }
    @Test(priority = 2,dataProvider = "getData")
    public void fillPaymentInfo(String NameOnCard,String CardNumber,String CVC,String ExpirationMonth,String ExpirationYear) {
        paymentPage.fillPaymentData(NameOnCard,CardNumber,CVC,ExpirationMonth,ExpirationYear)
                .clickOnPayBtn()
                .assertOnSuccessfulMessage();
    }


}
