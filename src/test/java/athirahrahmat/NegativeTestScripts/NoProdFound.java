package athirahrahmat.NegativeTestScripts;

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

public class NoProdFound extends BaseTest {

	@Test(dependsOnMethods={"athirahrahmat.TestScripts.Login.login"})
    public void filterBySearchbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePage homePage = new HomePage(driver);
        String randomName = "NIKE";
        
        try {
            // Navigate to Home page if not already there
            homePage.goToHomePage();
            
            // Enter the product name in the search box and press Enter
            WebElement searchBox = driver.findElement(By.xpath("(//input[@placeholder='search'])[2]"));
            
            searchBox.sendKeys(randomName);
            searchBox.sendKeys(Keys.RETURN); // Simulate pressing Enter
            
            // Wait for the search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container")));
            
            // Locate the search results
            List<WebElement> productResults = driver.findElements(By.cssSelector(".card-body"));
                        
            // Validate that any product containing the search term is found
            boolean isProductFound = productResults.stream()
                    .map(product -> product.findElement(By.cssSelector("h5")).getText())
                    .anyMatch(name -> name.toLowerCase().contains(randomName.toLowerCase()));
            
            // Assert that the product name is found in the search results
            Assert.assertFalse(isProductFound, "Product " + randomName + " was not found in the search results.");
            
            WebElement errorToaster = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-error")));
            String errorMessage = errorToaster.getText();
            System.out.println("Toaster message: " + errorMessage);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
	
}
