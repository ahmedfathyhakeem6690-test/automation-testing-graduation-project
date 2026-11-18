package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import utils.ConfigReader;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void recentDriver () {
        String browser = ConfigReader.get("browser").toLowerCase();

        WebDriver webDriver ;

        switch (browser){
            case "firefox" :
                webDriver = ThreadGuard.protect(new FirefoxDriver());
                break;
            case "edge" :
                webDriver = ThreadGuard.protect(new EdgeDriver());
                break;
            default:
                webDriver = ThreadGuard.protect(new ChromeDriver());
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void quitDriver() {
        WebDriver webDriver = driver.get();

        if (webDriver!= null){
            webDriver.quit();
            driver.remove();
        }

    }
}
