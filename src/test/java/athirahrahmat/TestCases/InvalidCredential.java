package athirahrahmat.TestCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.CredentialPropertiesReader;

public class InvalidCredential extends BaseTest {
	
	@Test
	public void wrongUsername()
	{
		CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String wrongUsername = reader.getProperty("wrongUsername1");
        String password = reader.getProperty("password1");

        driver.findElement(By.id("userEmail")).sendKeys(wrongUsername);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
        boolean errorMessage = driver.findElement(By.cssSelector(".toast-container")).isDisplayed();
        System.out.println(errorMessage);
	}
	
	@Test
	public void wrongPassword()
	{
		CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String wrongUsername = reader.getProperty("username1");
        String password = reader.getProperty("wrongPassword");

        driver.findElement(By.id("userEmail")).sendKeys(wrongUsername);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
        boolean errorMessage = driver.findElement(By.cssSelector(".toast-container")).isDisplayed();
        System.out.println(errorMessage);
	}

}
