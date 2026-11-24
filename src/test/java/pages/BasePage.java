package pages;

import base.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
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
        log.info("Click on {}", locator);
        waitClikable(locator).click();
    }

    protected void typeText (By locator , String text) {
        String shown = locator.toString().toLowerCase().contains("password") ? "****" : text;
        log.info("Type '{}' into {}", shown, locator);
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

    public boolean waitForUrlContains(String text){
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        }catch (TimeoutException e){
            return false;
        }
    }
    public String getReqMsg(By locator){
        return getText(locator);
    }
    public String getValue(By locator) {
        return wait.ignoring(StaleElementReferenceException.class).until(d -> {
            String value = d.findElement(locator).getAttribute("value");
            return (value == null || value.isEmpty()) ? null : value;
        });
    }

    protected final Logger log = LogManager.getLogger(getClass());


}
