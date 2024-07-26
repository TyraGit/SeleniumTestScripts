package athirahrahmat.TestCases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        
        // Explicit wait for the logout icon to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-sign-out")));
        
        boolean loginSuccess = logoutIcon.isDisplayed();
        assertTrue(loginSuccess, "Login should be successful.");
        
        System.out.println("Login attempt successful");
    }
}
