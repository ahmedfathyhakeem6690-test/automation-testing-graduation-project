package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.ConfigReader;
import utils.JsonUtils;
import utils.User;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.recentDriver();
        DriverManager.getDriver().get(ConfigReader.get("base.url") + "auth/login");
    }

    protected void loginAsAdmin() {
        User admin = JsonUtils.readUsers("src/test/resources/testdata/valid-login.json").getFirst();
        new LoginPage().login(admin.getUsername(), admin.getPassword());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
