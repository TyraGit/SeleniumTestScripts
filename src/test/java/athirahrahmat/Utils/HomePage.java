package athirahrahmat.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to check if the user is on the Home page
    public boolean isHomePage() {
        try {
            // Check for a unique element that only appears on the Home page
            return driver.findElement(By.cssSelector(".container")) != null;
        } catch (Exception e) {
            // Element not found, not on Home page
            return false;
        }
    }

    // Method to navigate to Home page if not already there
    public void goToHomePage() {
        if (!isHomePage()) {
            driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard']")).click();
        }
    }
}
