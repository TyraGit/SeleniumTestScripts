package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class AddMoreThan1Product extends BaseTest {
	//this test script is for MORE THAN 1 product to add to cart

    @Test(dependsOnMethods = {"athirahrahmat.TestScripts.Login.login"})
    public void selectProducts() {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            System.out.println("Products are visible on the page.");

            // Define the product names to add to the cart
            String[] productNames = {"ZARA COAT 3", "IPHONE 13 PRO", "ADIDAS ORIGINAL"};

            // Loop through the product names and add each to the cart
            for (String productName : productNames) {
                addProductToCart(productName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //this method is a utility method so no need Test annotation
    public void addProductToCart(String productName) {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);

        if (prod != null) {
            try {
                buffer.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
                prod.findElement(By.cssSelector(".btn.w-10.rounded:last-of-type")).click();
                System.out.println("Added '" + productName + "' to the cart.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred while adding " + productName + " to the cart: " + e.getMessage());
            }
        } else {
            System.out.println("Product '" + productName + "' not found.");
        }
    }
}


