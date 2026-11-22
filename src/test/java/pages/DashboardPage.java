package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{

    private final By dashboardHeaderLoc = By.cssSelector(".oxd-text--h6");





    public String getHeaderText (){
        return getText(dashboardHeaderLoc);
    }

}
