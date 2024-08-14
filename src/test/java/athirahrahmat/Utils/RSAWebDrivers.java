package athirahrahmat.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class RSAWebDrivers {

    public WebDriver driver;

    // Enum to define browser types
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE
    }

    // Constructor to initialize WebDriver based on the browser type
    public RSAWebDrivers(ChromeOptions options) {
        // Retrieve the browser type from the system property, defaulting to CHROME
        String browser = System.getProperty("browser", "CHROME").toUpperCase();
        BrowserType browserType = BrowserType.valueOf(browser);

        // Set the path to the WebDriver executable based on the browser type
        switch (browserType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/Users/athirah/chromedriver");
                this.driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "/Users/athirah/geckodriver");
                this.driver = new FirefoxDriver();
                break;

            case EDGE:
                System.setProperty("webdriver.edge.driver", "/Users/athirah/msedgedriver");
                this.driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid browser type");
        }
    }

    // Getter for the WebDriver
    public WebDriver getDriver() {
        return this.driver;
    }
}
