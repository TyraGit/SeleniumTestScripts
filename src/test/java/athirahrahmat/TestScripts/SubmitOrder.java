package athirahrahmat.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class SubmitOrder extends BaseTest {

    @Test(dependsOnMethods={"athirahrahmat.TestScripts.Checkout.clickCheckout"})
    public void selectCountry() {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased the wait time for better stability
        Actions action = new Actions(driver);

        // Enter the country name into the input field
        action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "Mala").perform();

        // Wait for the dropdown to appear and then select the third item
        buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(3)")).click();
    }

    @Test(dependsOnMethods={"selectCountry"}) // Ensure this step runs after the country is selected
    public void submitOrder() {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adding wait for stability

        // Wait until the submit button is clickable
        WebElement submitButton = buffer.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

        // Scroll the submit button into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Click the submit button
        submitButton.click();

        // Verify the order confirmation message
        String orderConfirmedMessage = buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
        Assert.assertTrue(orderConfirmedMessage.equalsIgnoreCase("Thankyou for the order."));
    }
}
