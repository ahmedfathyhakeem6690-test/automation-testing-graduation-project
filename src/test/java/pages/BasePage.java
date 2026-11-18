package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));

    }

    protected WebElement waitToVisible (By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected WebElement waitClikable (By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void onClick (By locator){

        waitClikable(locator).click();
    }

    protected void typeText (By locator , String text) {
        WebElement element = waitToVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText (By locator){
        return waitToVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator){
        try {
            return waitToVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean itContains (By locator , String text){
        try {
            return waitToVisible(locator).getText().contains(text);
        }catch (TimeoutException e){
            return false;
        }
    }

}
