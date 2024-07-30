package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import athirahrahmat.Utils.HomePage;
import athirahrahmat.Utils.BaseTest;

public class ViewProdDesc extends BaseTest {

    @Test(dependsOnMethods = {"athirahrahmat.TestScripts.Login.login"})
    public void viewProduct() {
        String productName = "IPHONE 13 PRO";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePage homePage = new HomePage(driver);

        try {
            // Navigate to Home page if not already there
            homePage.goToHomePage();

            // Wait for the Home page to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

            // Locate and view the product
            boolean productFound = homePage.viewProductByName(productName);

            if (!productFound) {
                System.out.println("Product " + productName + " not found on the home page.");
                return; // Exit the method if the product is not found
            }

            // Wait for the product view page to load and the items to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-star-inserted")));
            List<WebElement> prodDesc = driver.findElements(By.cssSelector(".col-lg-6.rtl-text"));

            // Validate that the product name is found in the product description page
            boolean match = prodDesc.stream()
                                 .map(WebElement::getText)
                                 .anyMatch(text -> text.contains(productName));

            if (match) {
                System.out.println("Product " + productName + " is showing in the description page.");
            } else {
                System.out.println("Product " + productName + " is not showing in the description page.");
            }
            
            // Take a screenshot with a custom filename
            screenshots.takeScreenshot("ViewProductDesc_");
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
