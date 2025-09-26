import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class Test {
    WebDriver driver;
    @BeforeTest
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicitWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.automationexercise.com/");
    }
    @org.testng.annotations.Test()
    public void SignUp() {
        driver.findElement(By.linkText("Signup / Login")).click();
        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Rofida");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("rofida11@gmail.com");
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        driver.findElement(By.id("uniform-id_gender2")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("P@ssw0rd");

        WebElement days = driver.findElement(By.id("days"));
        Select Days = new Select(days);
        Days.selectByIndex(2);

        WebElement Month = driver.findElement(By.id("months"));
        Select Months = new Select(Month);
        Months.selectByVisibleText("January");

        WebElement Year = driver.findElement(By.id("years"));
        Select Years = new Select(Year);
        Years.selectByValue("2019");

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("first_name")).sendKeys("rofida");

        driver.findElement(By.id("last_name")).sendKeys("araby");
        driver.findElement(By.xpath("//input[@data-qa='address']")).sendKeys("address");

        WebElement Country = driver.findElement(By.id("country"));
        Select Countries = new Select(Country);
        Countries.selectByIndex(3);

        driver.findElement(By.id("state")).sendKeys("Cairo");
        driver.findElement(By.id("city")).sendKeys("cairo");
        driver.findElement(By.id("zipcode")).sendKeys("020");
        driver.findElement(By.id("mobile_number")).sendKeys("010234567");
        // Explicit
//         WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
//         WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create Account']")));
//         Button.click();

        //Fluent Wait
//        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(30))
//                .pollingEvery(Duration.ofSeconds(3))
//                .ignoring(NoSuchElementException.class);
//        WebElement Button=fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create Account']")));
//        Button.click();

        /* Hard assertion on End point  */
        String ActualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.automationexercise.com/account_created";
        Assert.assertEquals(ActualURL, expectedURL, "Your is not Correct!");
        /* soft assertion on ui */
        SoftAssert soft = new SoftAssert();
        String ActualSuccessfulMessage=driver.findElement(By.xpath("//b")).getText();
        soft.assertEquals(ActualSuccessfulMessage,"ACCOUNT CREATED!");
        soft.assertAll();
    }
     @AfterTest
     public void teardown(){
        driver.quit();
     }
}
