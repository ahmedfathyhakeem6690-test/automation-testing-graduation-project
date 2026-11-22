package pages;

import org.openqa.selenium.By;

public class AddEmployeePage extends BasePage{

    private final By firstNameInputLoc = By.cssSelector("input[name=\"firstName\"]");
    private final By lastNameInputLoc = By.cssSelector("input[name=\"lastName\"]");
    private final By saveBtnLoc = By.cssSelector("button[type=\"submit\"]");
    private final By requiredMsgLoc = By.xpath("//span[normalize-space()=\"Required\"]");
    private final By savedFirstInputLoc = By.cssSelector("input[name=\"firstName\"]");
    private final By savedLastInputLoc = By.cssSelector("input[name=\"lastName\"]");


    public boolean NameFieldsIsDisplayed (){
        waitToVisible(firstNameInputLoc);
        waitToVisible(lastNameInputLoc);
        return isDisplayed(firstNameInputLoc) && isDisplayed(lastNameInputLoc);
    }

    public String FirstName(String firstName){
        waitToVisible(firstNameInputLoc);
        typeText(firstNameInputLoc, firstName);
        return  firstName;
    }
    public String LastName(String lastName){
        waitToVisible(lastNameInputLoc);
        typeText(lastNameInputLoc, lastName);
        return  lastName;
    }

    public void Save(){
        waitToVisible(saveBtnLoc);
        onClick(saveBtnLoc);
    }

    public void AddEmployee(String firstName, String lastName){
        waitToVisible(firstNameInputLoc);
        waitToVisible(lastNameInputLoc);
        waitToVisible(saveBtnLoc);
        typeText(firstNameInputLoc, firstName);
        typeText(lastNameInputLoc, lastName);
        onClick(saveBtnLoc);
    }

    public String errorMsg(String firstName, String lastName){
        AddEmployee(firstName, lastName);
        waitToVisible(requiredMsgLoc);
        return getText(requiredMsgLoc);
    }

    public String getSavedFirstName(){
        waitToVisible(savedFirstInputLoc);

        return   getValue(savedFirstInputLoc);
    }
    public String getSavedLastName(){
        waitToVisible(savedLastInputLoc);
        return  getValue(savedLastInputLoc);
    }





}
