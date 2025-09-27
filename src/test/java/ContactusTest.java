import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactusPage;
import pages.HomePage;
import pages.SigninPage;
import utiles.datareaders.DataProviderUtils;

import java.io.IOException;
import java.util.Iterator;

public class ContactusTest extends BaseTest {
    ContactusPage contactusPage;

    @BeforeTest
    public void setUp() {
        contactusPage = new ContactusPage();
    }
    @DataProvider
    public Iterator<Object[]> getData() throws IOException {
        return DataProviderUtils.getData("src/test/resources/test-data/Contactus-data.json");
    }
    @Test(priority = 1,dataProvider = "getData")
    public void fillContactusInfo(String name,String email,String subject,String message){
        contactusPage.clickOnContactusBtn()
                .fillContactUsData(name,email,subject,message)
                .clickOnSubmitBtn();
    }
    @Test(priority = 2)
    public void VerifySuccessConfirmationMessage(){
        contactusPage.assertOnSuccessfulMessage();
    }

}
