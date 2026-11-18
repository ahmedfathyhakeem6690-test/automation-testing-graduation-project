package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage{

    private final By UsernameLoc =By.cssSelector("input[name=\"username\"]");
    private final By PasswordLoc =By.cssSelector("input[name=\"password\"]");
    private final By LoginBtnLoc =By.cssSelector("button[type=\"submit\"]");
    private final By Invalid_credentialsLoc =By.cssSelector(".oxd-alert-content-text");
    private final By RequiredLoc =By.cssSelector(".oxd-input-field-error-message");

    public void login(String username , String password){
        typeText(UsernameLoc,username);
        typeText(PasswordLoc,password);
        onClick(LoginBtnLoc);
    }

    public String getInvalidMsg(){
        return getText(Invalid_credentialsLoc);
    }

    public List<String> getRequiredMsg (){
        waitToVisible(RequiredLoc);
        return driver.findElements(RequiredLoc).stream().map(WebElement::getText).toList();
    }



}
