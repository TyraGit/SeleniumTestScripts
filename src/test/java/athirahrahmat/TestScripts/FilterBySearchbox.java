package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.HomePage;

public class FilterBySearchbox extends BaseTest {
	
    @Test(dependsOnMethods= {"athirahrahmat.TestScripts.Login.login"})
    public void filterBySearchbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePage homePage = new HomePage(driver);
        String prodName = "ZARA";
        
        try {
            // Navigate to Home page if not already there
            homePage.goToHomePage();
            
            // Enter the product name in the search box and press Enter
            WebElement searchBox = driver.findElement(By.xpath("(//input[@placeholder='search'])[2]"));
            
            searchBox.sendKeys(prodName);
            searchBox.sendKeys(Keys.RETURN); // Simulate pressing Enter
            
            // Wait for the search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container")));
            
            // Locate the search results
            List<WebElement> productResults = driver.findElements(By.cssSelector(".card-body"));
            
            // Print out the text of all search results for debugging
            /*System.out.println("Search Results:");
            for (WebElement product : productResults) {
                String productText = product.findElement(By.cssSelector("h5")).getText();
                System.out.println("Found product: " + productText);
            }*/
            
            // Validate that any product containing the search term is found
            boolean isProductFound = productResults.stream()
                    .map(product -> product.findElement(By.cssSelector("h5")).getText())
                    .anyMatch(name -> name.toLowerCase().contains(prodName.toLowerCase()));
            
            // Assert that the product name is found in the search results
            Assert.assertTrue(isProductFound, "Product " + prodName + " was not found in the search results.");
            
            System.out.println("Product " + prodName + " is displayed in the search results.");
            
            // clear the product name from the search box and press Enter
            searchBox.clear();
            searchBox.sendKeys(Keys.RETURN);
            
            Thread.sleep(5000);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
