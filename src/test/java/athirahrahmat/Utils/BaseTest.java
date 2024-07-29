package athirahrahmat.Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import athirahrahmat.Utils.RSAWebDrivers.BrowserType;

public class BaseTest {
    public static RSAWebDrivers rsaWebDrivers;
    public static WebDriver driver;
    public String url = "https://rahulshettyacademy.com/client";

    @BeforeSuite
    public void invokeBrowser() {
        if (driver == null) {
            rsaWebDrivers = new RSAWebDrivers(BrowserType.CHROME);
            driver = rsaWebDrivers.getDriver();
            driver.manage().window().maximize();
            
            System.out.println("Browser opened.");
            
            System.out.println("Navigating to URL: " + url);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }
    
    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing browser...");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("Browser closed.");
    }
}
