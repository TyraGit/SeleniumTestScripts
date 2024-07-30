package athirahrahmat.Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            return driver.findElement(By.cssSelector(".card-body")) != null;
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

    // Method to find a specific product by name and click the view button
    public boolean viewProductByName(String productName) {
        try {
            List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
            for (WebElement product : products) {
                WebElement productNameElement = product.findElement(By.cssSelector("h5"));
                if (productNameElement.getText().equalsIgnoreCase(productName)) {
                    product.findElement(By.cssSelector("button:first-of-type")).click();
                    return true; // Product found and view button clicked
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Product not found
    }
}
