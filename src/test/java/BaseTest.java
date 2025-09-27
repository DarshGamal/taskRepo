
import org.testng.annotations.*;
import utiles.DriverMange.DriverManager;

public class BaseTest {

    @BeforeTest
    public void Init() {
        DriverManager.driverSetup();
    }

    @AfterSuite
    public void closeDriver(){
       DriverManager.closeDriver();
    }
}
