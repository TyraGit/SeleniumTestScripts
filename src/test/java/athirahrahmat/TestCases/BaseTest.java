package athirahrahmat.TestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import athirahrahmat.Utils.RSAWebDrivers;
import athirahrahmat.Utils.RSAWebDrivers.BrowserType;

public abstract class BaseTest {
    public RSAWebDrivers rsaWebDrivers;
    public WebDriver driver;
    public static String url = "https://rahulshettyacademy.com/client";

    @BeforeSuite
    public void initializeBrowser() {
        rsaWebDrivers = new RSAWebDrivers(BrowserType.CHROME);
        driver = rsaWebDrivers.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait here
    }
    
    @BeforeMethod
    public void navigateToURL() {
        driver.get(url);
    }

    /*@AfterSuite
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}

