package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;

import java.util.List;

public class AdminTest extends BaseTest {
    @Test
    @Epic("Admin")
    @Feature("Add User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Add User form contains the required fields")
    public void addUserFormFields() {
        AdminPage admin = new AdminPage();

        Allure.step("Login as admin", this::loginAsAdmin);

        Allure.step("Open Admin > User Management > Users", admin::openAdminPage);

        Allure.step("Click Add", admin::clickAdd);

        Allure.step("Assert the form contains User Role, Employee Name, Username and Password",
                () -> {
                        for (String field : List.of("User Role", "Employee Name", "Username", "Password")) {
                                Assert.assertTrue(admin.isFieldDisplayed(field),
                                        field + " field is not displayed");
                            }
        });
    }



}
