package athirahrahmat.Utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import athirahrahmat.Utils.RSAWebDrivers.BrowserType;

public class BaseTest {
	//drivers are static so they can be shared across all instances of the test classes.
    public static RSAWebDrivers rsaWebDrivers;
    public static WebDriver driver;
    public String url = "https://rahulshettyacademy.com/client";

    @BeforeSuite
    public void invokeBrowser() {
        rsaWebDrivers = new RSAWebDrivers(BrowserType.CHROME);
        driver = rsaWebDrivers.getDriver();
        driver.manage().window().maximize();
        
        System.out.println("Browser opened.");
        
        System.out.println("Navigating to URL: " + url);
        driver.get(url);
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

