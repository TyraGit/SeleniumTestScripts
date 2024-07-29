package athirahrahmat.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class Checkout extends BaseTest {
	
	@Test(dependsOnMethods = {"athirahrahmat.TestScripts.ViewCart.verifyProdInCart"})
	public void clickCheckout() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    
	    try {
	        System.out.println("Waiting for checkout button to be clickable...");
	        
	        // Scroll to the checkout button
	        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".totalRow button"))));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkoutButton);
	        System.out.println("Scrolled to checkout button.");

	        // Wait for any overlaying elements to disappear
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

	        // Wait until the checkout button is clickable and click it
	        //checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	        checkoutButton.click();
	        System.out.println("Clicked checkout button.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}
}
