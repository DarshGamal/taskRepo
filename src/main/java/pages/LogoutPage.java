package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;

public class LogoutPage {
    WebDriver driver;
    By logoutBtn = By.linkText("Logout");

    public LogoutPage() {
        this.driver = DriverManager.getDriver();
    }

    public LogoutPage clickOnLogoutBtn() {
        ElementHelper.click(driver, logoutBtn);
        return this;
    }

    public LogoutPage verifyRedirectionAfterLogout() {
        AssertionHelper.assertUrl(driver, "https://www.automationexercise.com/login");
        return this;
    }
}
