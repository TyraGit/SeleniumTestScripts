package athirahrahmat.TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class Checkout extends BaseTest {
	
	@Test(dependsOnMethods = {"athirahrahmat.TestCases.ViewCart.verifyProdInCart"})
	public void clickCheckout() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    try {
	        System.out.println("Waiting for checkout button to be clickable...");
	        // Wait until the checkout button is clickable
	        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	        System.out.println("Checkout button is clickable.");

	        // Scroll to the checkout button (even if it is already in view)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkoutButton);
	        System.out.println("Scrolled to checkout button.");

	        // Click the checkout button
	        checkoutButton.click();
	        System.out.println("Clicked checkout button.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}

	
}
