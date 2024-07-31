package athirahrahmat.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.HomePage;

public class FilterByCategories extends BaseTest {
	
	  @Test(dependsOnMethods = {"athirahrahmat.TestScripts.Login.login"})
	    public void filterbycheckbox() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        HomePage homePage = new HomePage(driver);
	        
	        try {
	            // Navigate to Home page if not already there
	            homePage.goToHomePage();
	            
	            // Select the Electronics checkbox
	            WebElement electronicCheckbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[13]"));
	            electronicCheckbox.click();
	            System.out.println("Clicked on the Electronics checkbox.");
	            
	            // Verify the checkbox is selected
	            Assert.assertTrue(electronicCheckbox.isSelected(), "Electronics checkbox was not selected.");
	            
	            // Wait for the search results to load
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container")));
	            System.out.println("Waited for the search results to load.");
	            
	            // Locate the search results
	            /*List<WebElement> productResults = driver.findElements(By.cssSelector(".container .card-body"));
	            System.out.println("Located the search results.");
	            
	            // Print out the text of all search results for debugging
	            System.out.println("Filtered Results:");
	            for (WebElement product : productResults) {
	                String productText = product.findElement(By.cssSelector("h5")).getText();
	                System.out.println("Found product: " + productText);
	            }
	            
	            // Validate the filtered products (you might need to add more logic to check category)
	            boolean isFilteredCorrectly = productResults.stream()
	                    .allMatch(product -> product.getText().toLowerCase().contains("electronics"));
	            
	            Assert.assertTrue(isFilteredCorrectly, "Products are not filtered by the Electronics category correctly.");*/
	            
	            Thread.sleep(5000);
	            
	            // Uncheck the Electronics checkbox if necessary
	            electronicCheckbox.click();
	            System.out.println("Unchecked the Electronics checkbox.");
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    }

}
