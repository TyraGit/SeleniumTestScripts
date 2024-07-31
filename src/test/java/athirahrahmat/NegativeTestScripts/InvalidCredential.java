package athirahrahmat.NegativeTestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;
import athirahrahmat.Utils.CredentialPropertiesReader;

public class InvalidCredential extends BaseTest {

    @Test
    public void wrongUsername() {
        CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String wrongUsername = reader.getProperty("wrongUsername1");
        String password = reader.getProperty("password1");

        driver.findElement(By.id("userEmail")).sendKeys(wrongUsername);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        
        // Add explicit wait for the error message to be displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorToaster = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-error")));
        String errorMessage = errorToaster.getText();
        System.out.println("Toaster message: " + errorMessage);
        
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userPassword")).clear();
    }

    @Test
    public void wrongPassword() {
        CredentialPropertiesReader reader = new CredentialPropertiesReader("credentials.properties");
        String wrongUsername = reader.getProperty("username1");
        String wrongPassword = reader.getProperty("wrongPassword");

        driver.findElement(By.id("userEmail")).sendKeys(wrongUsername);
        driver.findElement(By.id("userPassword")).sendKeys(wrongPassword);
        driver.findElement(By.id("login")).click();
        
        // Add explicit wait for the error message to be displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorToaster = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-error")));
        String errorMessage = errorToaster.getText();
        System.out.println("Toaster message: " + errorMessage);
        
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userPassword")).clear();
    }
}
