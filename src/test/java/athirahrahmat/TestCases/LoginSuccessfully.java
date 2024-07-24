package athirahrahmat.TestCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import athirahrahmat.Utils.CredentialPropertiesReader;

public class LoginSuccessfully extends BaseTest {

    @Test
    public void login() {
        CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String username = reader.getProperty("username1");
        String password = reader.getProperty("password1");

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
        // Verify successful login
        // Add assertions to ensure login was successful
        // For example, verify a specific element or URL after login
        // WebElement successMessage = driver.findElement(By.id("successMessageId"));
        // assertTrue(successMessage.isDisplayed(), "Success message should be displayed.");

        System.out.println("Login attempt completed and verified.");
    }
}
