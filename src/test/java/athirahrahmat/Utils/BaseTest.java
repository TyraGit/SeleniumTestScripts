package athirahrahmat.Utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import athirahrahmat.Utils.RSAWebDrivers.BrowserType;

public abstract class BaseTest {
    public RSAWebDrivers rsaWebDrivers;
    public WebDriver driver;
    public String url = "https://rahulshettyacademy.com/client";

    @BeforeSuite
    public void initializeBrowser() {
        rsaWebDrivers = new RSAWebDrivers(BrowserType.CHROME);
        driver = rsaWebDrivers.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Browser initialized.");
    }
    
    @BeforeMethod
    public void navigateToURL() {
    	System.out.println("Navigating to URL: " + url);
        driver.get(url);
        System.out.println("Navigation completed.");
    }
    

    @AfterSuite
    public void closeBrowser() {
    	System.out.println("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed.");
    }
}

