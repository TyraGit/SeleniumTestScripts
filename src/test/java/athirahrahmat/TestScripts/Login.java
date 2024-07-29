package athirahrahmat.TestScripts;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.CredentialPropertiesReader;

public class Login extends BaseTest {

    @Test
    public void login() {
        CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String username = reader.getProperty("username2");
        String password = reader.getProperty("password2");

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
        boolean loginSuccess = driver.findElement(By.className("fa-sign-out")).isDisplayed();
        assertTrue(loginSuccess, "Login should be successful.");
        
        System.out.println("Login attempt completed and verified.");
    }
}
