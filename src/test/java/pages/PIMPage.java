package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PIMPage extends BasePage{
    private final By EmployeeNameFieldLoc = By.xpath(
            "//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By SearchbtnLoc = By.cssSelector("button[type=\"submit\"]");
    private final By FirstNameLoc = By.cssSelector(".oxd-table-body .oxd-table-card .oxd-table-cell:nth-child(3)");
    private final By LastNameLoc = By.cssSelector(".oxd-table-body .oxd-table-card .oxd-table-cell:nth-child(4)");
    private final By noRecordFoundMsgLoc = By.xpath("//span[normalize-space()='No Records Found']");
    private final By addBtnLoc = By.cssSelector(" .oxd-button[type=\"button\"]");

    public void openEmployeeList() {
        SideMenuPage sideMenuPage = new SideMenuPage();
        sideMenuPage.openMenu("PIM");
        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));
    }
    public void searchByName (String name){
        typeText(EmployeeNameFieldLoc,name);
        onClick(SearchbtnLoc);
    }
    public boolean isNoRecordDisplayed (){
       return isDisplayed(noRecordFoundMsgLoc);
    }

    public String getFirstNameFirstRow (){
        return waitToVisible(FirstNameLoc).getText().toLowerCase();
    }

    public List<String> getResultsFirstNames() {
        waitToVisible(FirstNameLoc);
        return driver.findElements(FirstNameLoc).stream()
                .map(WebElement::getText)
                .toList();
    }
    public boolean waitForEmployeeInResults(String name , int countBeforeSearch) {
        waitToVisible (FirstNameLoc);
        List<WebElement> cells = driver.findElements(FirstNameLoc);
        if (cells.size() == countBeforeSearch) {
            return false;
        }
        for (WebElement cell : cells) {
            if (cell.getText().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public int getResultsCount() {
        waitToVisible(FirstNameLoc);
        return driver.findElements(FirstNameLoc).size();
    }

    public void openAddEmployeePage() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        onClick(addBtnLoc);
        addEmployeePage.waitForUrlContains("/pim/addEmployee");
    }

    public boolean waitForEmployeeRow (String firstName , String lastName) {
        waitToVisible (FirstNameLoc);
        waitToVisible(LastNameLoc);
        List<WebElement> firstCells = driver.findElements(FirstNameLoc);
        List<WebElement> lastCells = driver.findElements(LastNameLoc);

        for (int i = 0; i < Math.min(firstCells.size(), lastCells.size()); i++) {
            boolean firstMatches = firstCells.get(i).getText()
                    .toLowerCase().contains(firstName.toLowerCase());
            boolean lastMatches = lastCells.get(i).getText()
                    .equalsIgnoreCase(lastName);
            if (firstMatches && lastMatches) {
                return true;
            }
        }
        return false;
    }






}
