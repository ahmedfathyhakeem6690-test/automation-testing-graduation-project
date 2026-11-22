package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FooterPage extends BasePage {

    private final By footerTextLoc = By.xpath("//a[normalize-space()='OrangeHRM, Inc']/parent::p");
    private final By brandLinkLoc = By.linkText("OrangeHRM, Inc");

    public void scrollToFooter() {
        WebElement footer = waitToVisible(footerTextLoc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
    }

    public String getFooterText() {
        return getText(footerTextLoc);
    }

    public void clickBrandLinkAndSwitchToNewTab() {
        String original = driver.getWindowHandle();
        onClick(brandLinkLoc);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(original)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

}
