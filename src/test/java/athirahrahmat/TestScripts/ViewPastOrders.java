package athirahrahmat.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.HomePage;

public class ViewPastOrders extends BaseTest {
	
	@Test(dependsOnMethods={"athirahrahmat.TestScripts.Login.login"})
	public void clickOrdersTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased the wait time for better stability
		HomePage homePage = new HomePage(driver);

        try {
            // Navigate to Home page if not already there
            homePage.goToHomePage();

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // Waiting for toaster to appear
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); // Waiting for the loader to disappear

            // Wait for the Home page to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
            
			// Open Orders page
			driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/myorders']")).click();
			
			// Wait for the Orders page to load
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-star-inserted")));

			// Take a screenshot with a custom filename
            screenshots.takeScreenshot("ViewPastOrders_");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
