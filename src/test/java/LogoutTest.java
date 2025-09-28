import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LogoutPage;
import utiles.ExtentReports.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class LogoutTest extends BaseTest{
    LogoutPage logoutPage;

    @BeforeTest
    public  void setUp(){
       logoutPage=new LogoutPage();
    }
    @Test
    public void clickOnLogoutBtn(){
        logoutPage.clickOnLogoutBtn()
                .verifyRedirectionAfterLogout();
    }
}
