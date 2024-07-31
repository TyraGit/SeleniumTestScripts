package athirahrahmat.TestScripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class Logout extends BaseTest {
	
	@Test(dependsOnMethods= {"athirahrahmat.TestScripts.Login.login"})
	public void signout() throws InterruptedException {
		 
		driver.findElement(By.xpath("//button[normalize-space()='Sign Out']")).click();
		
		boolean loginPage = driver.findElement(By.cssSelector(".login-title")).isDisplayed();
        Assert.assertTrue(loginPage);
        
        System.out.println("Logout is successful");
        
        Thread.sleep(5000);
	}

}