package utiles.DriverMange;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utiles.config.LoadProperties;

import java.time.Duration;

public class DriverManager {

    private  static  WebDriver driver;
    // Get Browser url and name fom Load properties file
    static String BrowserName = LoadProperties.BROWSER;
    static String URLEnvironment = LoadProperties.URL;
    private DriverManager() {}
     static public void driverSetup() {
         if (driver==null) {
        switch (BrowserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //implicitWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URLEnvironment);
    }}
     static public WebDriver getDriver() {
        if (driver == null) {
            driverSetup();
        }
        return driver;
    }
     static public void closeDriver() {
        /* if (driver != null) {
             System.out.println("Closing WebDriver...");
             driver.quit();
             driver = null;  // Set driver to null after quitting to indicate it's no longer available
         }*/
    }
}
