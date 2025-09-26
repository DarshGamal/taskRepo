package utiles.commonHelper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ElementHelper {
    private static final int waitingTime = 30;

    /**
     * -----------------------------
     * Can be extracted into a separate class: WaitHelper
     * -----------------------------
     **/

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForInvisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilTextPresent(WebDriver driver, By locator, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }

    public static WebElement fluentWait(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver1 -> driver1.findElement(locator));
    }

    public static void waitForLoaderToDisappear(WebDriver driver, By loaderLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
    }

    /**
     * -----------------------------
     * Can be extracted into a separate class: BaseElementHelper
     * -----------------------------
     **/

    public static WebElement findElementBy(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator);
    }

    public static WebElement findElementByText(String text, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='" + text + "']")));

    }
    public static WebElement findElementByValue(String value, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@value='" + value + "']")));
    }


    public static WebElement findElementByTextContains(String text, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));
    }

    public static void click(WebDriver driver, By locator) {
        waitForClickable(driver, locator).click();
    }

    public static void click(WebElement webElement) {
        webElement.click();
    }

    public static void sendText( WebDriver driver, By locator,String text) {
        WebElement element = waitForVisibility(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void clearInput(WebDriver driver, By locator) {
        waitForVisibility(driver, locator).clear();
    }

    public static String getText(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator).getText();
    }

    public static  String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();}
    public static List<WebElement> getElements(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingTime));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            return waitForVisibility(driver, locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementEnabled(WebDriver driver, By locator) {
        try {
            return waitForVisibility(driver, locator).isEnabled();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * -----------------------------
     * Can be extracted into a separate class: DropdownHelper
     * -----------------------------
     **/

    public static void selectFromDropDownByText( WebDriver driver, By locator ,String text) {
        Select select = new Select(waitForClickable(driver, locator));
        select.selectByVisibleText(text);
    }

    public static void selectFromDropdownByIndex(int index, WebDriver driver, By locator) {
        Select select = new Select(waitForClickable(driver, locator));
        select.selectByIndex(index);
    }

    public static void selectFromDropdownByValue(String value, WebDriver driver, By locator) {
        Select select = new Select(waitForClickable(driver, locator));
        select.selectByValue(value);
    }

    /**
     * -----------------------------
     * Can be extracted into a separate class: JavaScriptHelper
     * -----------------------------
     **/
    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = waitForVisibility(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void jsClick(WebDriver driver, By locator) {
        WebElement element = waitForClickable(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * -----------------------------
     * Can be extracted into a separate class: ActionsHelper
     * -----------------------------
     **/

    public static void hoverOver(WebDriver driver, By locator) {
        Actions actions = new Actions(driver);
        WebElement element = waitForVisibility(driver, locator);
        actions.moveToElement(element).perform();
    }
    }

