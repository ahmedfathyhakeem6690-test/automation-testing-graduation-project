package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.User;

public class AddEmployeeTest extends BaseTest {
    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("Add Employee")
    @Feature("Add Employee")
    @Description("Verify that can add employee")
    @Severity(SeverityLevel.CRITICAL)
    public void addEmployee (User user) {

        LoginPage loginPage = new LoginPage();
        Allure.step("Verify user could login with valid credentials", () ->
                loginPage.login(user.getUsername(), user.getPassword()));

        PIMPage pim = new PIMPage();
        Allure.step("Open the Employee List", pim::openEmployeeList);

        AddEmployeePage addEmployee = new AddEmployeePage();
        Allure.step("Open Add Employee Page" , pim::openAddEmployeePage );


        Allure.step("Assert the URL contains /pim/addEmployee" , () ->
                Assert.assertTrue(addEmployee.waitForUrlContains("pim/addEmployee")));

        Allure.step("Assert the \"First Name\" and \"Last Name\" fields are displayed" , addEmployee::NameFieldsIsDisplayed);

    }
    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("Add Employee")
    @Feature("Add Employee")
    @Description("Verify that saving without a first name shows a Required error")
    @Severity(SeverityLevel.CRITICAL)
    public void addEmployeeEmpty (User user) {

        LoginPage loginPage = new LoginPage();
        Allure.step("Verify user could login with valid credentials", () ->
                loginPage.login(user.getUsername(), user.getPassword()));

        PIMPage pim = new PIMPage();
        Allure.step("Open the Employee List", pim::openEmployeeList);

        AddEmployeePage addEmployee = new AddEmployeePage();
        Allure.step("Open Add Employee Page", pim::openAddEmployeePage);

        Allure.step("Enter last name only and click Save", () ->
                addEmployee.enterNamesAndSave("", "Fathy"));



        Allure.step("Assert Required is displayed under First Name", () ->
                Assert.assertEquals(addEmployee.getRequiredMessage(), "Required",
                        "Validation message mismatch"));
    }
    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("Add Employee")
    @Feature("Add Employee")
    @Description("Verify that a new employee can be added and then found in the employee list")
    @Severity(SeverityLevel.CRITICAL)
    public void addEmployeeFromConfig (User user) {

        LoginPage loginPage = new LoginPage();
        Allure.step("Verify user could login with valid credentials", () ->
                loginPage.login(user.getUsername(), user.getPassword()));

        PIMPage pim = new PIMPage();
        Allure.step("Open the Employee List", pim::openEmployeeList);

        AddEmployeePage addEmployee = new AddEmployeePage();
        Allure.step("Open Add Employee Page", pim::openAddEmployeePage);

        String first = ConfigReader.get("firstName");
        String last = ConfigReader.get("lastName");
        step("Enter first and last name", () -> {
            addEmployee.FirstName(first);
            addEmployee.LastName(last);
        });

        Allure.step("Click Save", addEmployee::Save);

        Allure.step("Assert the Personal Details page opens for the newly created employee" ,
                () -> {   Assert.assertTrue(addEmployee.waitForUrlContains("/pim/viewPersonalDetails"),
                        "Personal Details page did not open");
                        Assert.assertEquals(addEmployee.getSavedFirstName(), first , "First name mismatch");
                        Assert.assertEquals(addEmployee.getSavedLastName() ,last , "Last name mismatch");
                        });

        Allure.step("Open the Employee List", pim::openEmployeeList);

        Allure.step("Search for the new employee", () ->
                pim.searchByName(first + " " + last));

        Allure.step("Assert the new employee appears in the results", () ->
                Assert.assertTrue(pim.waitForEmployeeRow(first, last),
                        "New employee not found: " + first + " " + last));





    }
}
