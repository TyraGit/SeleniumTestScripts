package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class AddToCart extends BaseTest {
    //this test script is for only 1 product to add to cart
	
    @Test(dependsOnMethods = {"athirahrahmat.TestScripts.Login.login"})
    public void selectProduct() {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            System.out.println("Products are visible on the page.");

            // Go through a list of elements and filter the product by "ADIDAS ORIGINAL"
            List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
            WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);

            if (prod != null) {
                prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
            } else {
                System.out.println("Product 'ZARA COAT 3' not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
