package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.DataProviders;
import utils.User;


import java.util.List;
public class PIMTest extends BaseTest {
    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("PIM")
    @Feature("Employee Search")
    @Description("Verify that an existing employee can be found by name")
    @Severity(SeverityLevel.CRITICAL)
    public void searchExistingEmployee (User user) {

        LoginPage loginPage = new LoginPage();
        DashboardPage dashboard = new DashboardPage();
        Allure.step("Verify user could login with valid credentials", () ->
                loginPage.login(user.getUsername(), user.getPassword()));

        PIMPage pim = new PIMPage();
        Allure.step("Open the Employee List", pim::openEmployeeList);

        String name = pim.getFirstNameFirstRow();
        int countBefore = pim.getResultsCount();

        Allure.step("Search for the employee: " + name, () ->
                pim.searchByName(name));

        Allure.step("Assert the results table contains the searched employee's name", () ->
                Assert.assertTrue(pim.waitForEmployeeInResults(name,countBefore),
                        "Employee not found in results: " + name));
        }
    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("PIM")
    @Feature("Employee Search")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that searching for a non-existing employee shows No Records Found")
    public void searchNonExistingEmployee(User user) {
        LoginPage loginPage = new LoginPage();
        PIMPage pim = new PIMPage();

        Allure.step("Login with valid credentials", () ->
                loginPage.login(user.getUsername(), user.getPassword()));

        Allure.step("Open the Employee List", pim::openEmployeeList);

        String randomName = "Zzxq";
        Allure.step("Search for a random name: " + randomName, () ->
                pim.searchByName(randomName));

        Allure.step("Assert No Records Found is displayed", () ->
                Assert.assertTrue(pim.isNoRecordDisplayed(),
                        "No Records Found message not displayed"));
    }

}

