package athirahrahmat.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("tyralalola96@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tyr@1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait buffer = new WebDriverWait(driver, Duration.ofSeconds(5)); //to wait until the page load and finds the element
		
		buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); //waiting and scanning the page
		
		//go through a list of elements and filter the product by "ADIDAS ORIGINAL"
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //waiting and scanning the page
		buffer.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); //waiting for the element to disappear
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		List <WebElement> carts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = carts.stream().anyMatch(cart-> cart.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match); //to validate that the product name is found in the cart
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "Mala").build().perform();
		
		buffer.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(3)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String orderConfirmedMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();

	}

}
