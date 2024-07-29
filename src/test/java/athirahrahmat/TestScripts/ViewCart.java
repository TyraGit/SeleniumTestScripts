package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class ViewCart extends BaseTest {

    @Test(dependsOnMethods = {"athirahrahmat.TestScripts.AddMoreThan1Product.selectProducts"})
    public void verifyProdInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // waiting for toaster to appear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); // waiting for the loader to disappear

            // Open Cart page
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@routerlink='/dashboard/cart'])[1]")));
            cartButton.click();

            // Wait for any overlaying elements to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

            // Wait for the cart page to load and the items to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));
            List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

            // Define the product names to verify
            String[] expectedProductNames = {"ZARA COAT 3", "IPHONE 13 PRO", "ADIDAS ORIGINAL"};

            // Verify that each expected product is in the cart
            for (String productName : expectedProductNames) {
                boolean match = cartItems.stream()
                                         .map(WebElement::getText)
                                         .anyMatch(productName::equalsIgnoreCase);

                Assert.assertTrue(match, "Product " + productName + " should be in the cart.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
