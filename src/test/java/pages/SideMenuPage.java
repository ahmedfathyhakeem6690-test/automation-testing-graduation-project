package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SideMenuPage extends BasePage{
    private static final String MENU_ITEM_XPATH =
            "//span[contains(@class,'oxd-main-menu-item--name') and normalize-space()='%s']";

    private final By allMenuItemsLoc = By.cssSelector(".oxd-main-menu-item--name");

    private By menuItem(String name) {
        return By.xpath(String.format(MENU_ITEM_XPATH, name));
    }

    public void openMenu(String name) {
        onClick(menuItem(name));
    }

    public boolean isMenuDisplayed(String name) {
        return isDisplayed(menuItem(name));
    }

    public List<String> getMenuNames() {
        waitToVisible(allMenuItemsLoc);
        return driver.findElements(allMenuItemsLoc).stream()
                .map(WebElement::getText)
                .toList();
    }
}
