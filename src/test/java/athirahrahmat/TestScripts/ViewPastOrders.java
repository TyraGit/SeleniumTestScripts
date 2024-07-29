package athirahrahmat.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.Screenshots;

public class ViewPastOrders extends BaseTest {
	
	@Test(dependsOnMethods={"athirahrahmat.TestScripts.Login.login"})
	public void clickOrdersTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased the wait time for better stability
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // Waiting for toaster to appear
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); // Waiting for the loader to disappear

			// Open Cart page
			driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/myorders']")).click();

			// calling and initialize Screenshots class
            Screenshots screenshotUtility = new Screenshots();
            
            // Take a screenshot
            screenshotUtility.takeScreenshots();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
