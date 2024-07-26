package athirahrahmat.TestCases;

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

    @Test(dependsOnMethods = {"athirahrahmat.TestCases.AddToCart.selectProduct"})
    public void verifyProdInCart() {
        String productName = "ADIDAS ORIGINAL";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //waiting for toaster to appear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); //waiting for the loader to disappear
            
            //open Cart page
            driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
            
            //wait for the cart page to load and the items to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));
            List<WebElement> carts = driver.findElements(By.cssSelector(".cartSection h3"));

            //validate that the product name is found in the cart
            boolean match = carts.stream()
                                 .map(WebElement::getText)
                                 .anyMatch(productName::equalsIgnoreCase);

            Assert.assertTrue(match, "Product " + productName + " should be in the cart.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
