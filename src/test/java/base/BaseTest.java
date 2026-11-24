package base;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.ConfigReader;
import utils.JsonUtils;
import utils.User;

import static listeners.CustomListeners.log;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        long t0 = System.currentTimeMillis();
        DriverManager.recentDriver();
        long t1 = System.currentTimeMillis();
        DriverManager.getDriver().get(ConfigReader.get("base.url") + "auth/login");
        long t2 = System.currentTimeMillis();
        log.info("SETUP: browser launch {} ms, login page {} ms", t1 - t0, t2 - t1);
    }
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected void step(String name, Runnable body) {
        log.info("STEP: {}", name);
        Allure.step(name, body::run);
    }

    protected void loginAsAdmin() {
        User admin = JsonUtils.readUsers("src/test/resources/testdata/valid-login.json").getFirst();
        new LoginPage().login(admin.getUsername(), admin.getPassword());
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        long start = System.currentTimeMillis();
        DriverManager.quitDriver();
        log.info("TEARDOWN took {} ms", System.currentTimeMillis() - start);
        DriverManager.quitDriver();


    }

}
