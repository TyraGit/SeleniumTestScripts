package athirahrahmat.Utils;

import java.time.Duration;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static RSAWebDrivers rsaWebDrivers;
    public static WebDriver driver;
    public String url = "https://rahulshettyacademy.com/client";
    protected Screenshots screenshots;

    @BeforeSuite
    public void invokeBrowser() {
        if (driver == null) {
            ChromeOptions options = null;
            String browser = System.getProperty("browser", "CHROME").toUpperCase();

            if (browser.equals("CHROME")) {
                // Configure download directory for Chrome
                String downloadFilePath = Paths.get(System.getProperty("user.dir"), "downloads").toString();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", downloadFilePath);
                prefs.put("profile.default_content_settings.popups", 0);

                options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
            }

            rsaWebDrivers = new RSAWebDrivers(options);
            driver = rsaWebDrivers.getDriver();
            driver.manage().window().maximize();

            System.out.println("Browser opened.");

            System.out.println("Navigating to URL: " + url);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    @BeforeClass
    public void setUp() {
        screenshots = new Screenshots(driver);
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
