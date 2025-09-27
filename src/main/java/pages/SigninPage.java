package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.Global;
import utiles.commonHelper.LogsUtils;

// WE use fluent pattern (Method chainning )
public class SigninPage {
    WebDriver driver;
    Global global;
    By LoginEmail =By.xpath("//input[@data-qa='login-email']");
    By  LoginPassword=By.xpath("//input[@data-qa='login-password']");
    By LoginButton =By.xpath("//button[@data-qa='login-button']");
    public  SigninPage( ){
        this.driver= DriverManager.getDriver();
        global= Global.getInstance();
    }
    public  SigninPage EnterLoginEmail(String email){
        ElementHelper.sendText(driver,LoginEmail,email);
        return this ;
    }
    public SigninPage enterLoginPassword(String pass){
        ElementHelper.sendText(driver,LoginPassword,pass);
        return this ;
    }
    public SigninPage  clickOnLoginBtn() {
        ElementHelper.click(driver,LoginButton);
        return this ;
    }
}
