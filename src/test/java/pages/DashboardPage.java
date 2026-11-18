package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{

    private final By dashboardHeaderLoc = By.cssSelector(".oxd-text--h6");

    public boolean waitForUrlContains(String text){
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        }catch (TimeoutException e){
            return false;
        }
    }

    public String getHeaderText (){
        return getText(dashboardHeaderLoc);
    }

}
