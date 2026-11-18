package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver ;

    @BeforeMethod
    public void setUp() {
        DriverManager.recentDriver();
        DriverManager.getDriver().get(ConfigReader.get("base.url") + "auth/login");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
