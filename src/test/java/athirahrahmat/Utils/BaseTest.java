package athirahrahmat.Utils;

import java.time.Duration;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
            // Configure download directory
            String downloadFilePath = Paths.get(System.getProperty("user.dir"), "downloads").toString();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", downloadFilePath);
            prefs.put("profile.default_content_settings.popups", 0);
            
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            
            rsaWebDrivers = new RSAWebDrivers(BrowserType.CHROME, options);
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
