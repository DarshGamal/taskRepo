import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SigninPage;
import utiles.ExtentReports.ExtentReportListener;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

@Listeners(ExtentReportListener.class)
public class SignInTest  extends  BaseTest{
    HomePage Home;
    SigninPage SigninPage;

    @BeforeTest
    public  void setUp(){
        Home =new HomePage();
        SigninPage=new SigninPage();
    }
    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/test-data/SignIn-data.json");
    }
    @Test(testName = "SignIn",dataProvider = "getData")
    public  void SignIn(String email,String pass){
        Home.clickOnSignInUpLink();
        SigninPage.EnterLoginEmail(email).enterLoginPassword(pass).clickOnLoginBtn();
    }
}
