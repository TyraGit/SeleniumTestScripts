package athirahrahmat.TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class AddToCart extends BaseTest {
    
    @Test(dependsOnMethods = {"athirahrahmat.TestCases.Login.login"})
    public void selectProduct() {
        WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            System.out.println("Products are visible on the page.");

            // Go through a list of elements and filter the product by "ADIDAS ORIGINAL"
            List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
            WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);

            if (prod != null) {
                prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
                
            } else {
                System.out.println("Product 'ADIDAS ORIGINAL' not found.");
            }
            
            buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //waiting for toaster to appear
            buffer.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); //waiting for the loader to disappear
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
