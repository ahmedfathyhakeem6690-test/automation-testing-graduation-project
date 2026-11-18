package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataProviders;
import utils.User;

import java.util.List;

public class LoginTest extends BaseTest {


    @Test(dataProvider = "validLogin", dataProviderClass = DataProviders.class)
    @Epic("Authentication")
    @Feature("Login")
    @Description("Verify that a user can log in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void validLogin (User user){

        LoginPage loginPage = new LoginPage();
        DashboardPage dashboard = new DashboardPage();
        Allure.step("Verify user could login with valid credentials", () ->
            loginPage.login(user.getUsername() ,user.getPassword()));

        Allure.step("Assert the URL contains /dashboard/index" , () ->
            Assert.assertTrue(dashboard.waitForUrlContains("/dashboard/index"),
                "Dashboard Page not appear"));

        Allure.step("Assert the Dashboard header \"Dashboard\" is displayed", () ->
            Assert.assertEquals(dashboard.getHeaderText(), user.getExpected(),
                "Header not equal Dashboard"));

    }

    @Test(dataProvider = "invalidLogin", dataProviderClass = DataProviders.class)
    @Epic("Authentication")
    @Feature("Login")
    @Description("Verify that login fails with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidLogin (User user){
        LoginPage loginPage = new LoginPage();
        Allure.step("Verify user could login with invalid credentials", () ->
                loginPage.login(user.getUsername() ,user.getPassword()));

        Allure.step("Assert the error message \"Invalid credentials\" is displayed" , () ->
                Assert.assertTrue(loginPage.getInvalidMsg().contains(user.getExpected()),
                        "Invalid credentials Message not displayed"));


    }
    @Test(dataProvider = "emptyLogin", dataProviderClass = DataProviders.class)
    @Epic("Authentication")
    @Feature("Login")
    @Description("Verify that login fails with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    public void emptyLoginTest (User user){
        LoginPage loginPage = new LoginPage();
        Allure.step("Verify user could login with empty fields", () ->
                loginPage.login(user.getUsername() ,user.getPassword()));

        List<String> errorMessages = loginPage.getRequiredMsg();

        Allure.step("Assert that the \"Required\" validation messages appear under both fields" , () ->
                Assert.assertEquals(errorMessages.size(),2,
                        "Expected a message under each field"));
        for (String errorMessage : errorMessages) {
            Assert.assertEquals(errorMessage, user.getExpected(),
                    "Validation message text mismatch");
        }


    }
}
