package athirahrahmat.TestScripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class DownloadInvoice extends BaseTest {
    
    @Test(dependsOnMethods={"athirahrahmat.TestScripts.SubmitOrder.submitOrder"})
    public void DownloadCSV() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Find all buttons with the same selector
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mt-3:nth-child(1)")));
        
        // Iterate through the list and click the button with the specific label
        for (WebElement button : buttons) {
            if (button.getText().equals("Click To Download Order Details in CSV")) {
                // Scroll the button into view
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", button);
                
                button.click();
                System.out.println("Download is successful");
                break; // Exit the loop after clicking the correct button
            }
        }

        // Wait for the download to complete
        Thread.sleep(5000); // Adjust the sleep time as necessary

        // Verify the file download dynamically
        String downloadDirPath = Paths.get(System.getProperty("user.dir"), "Downloads").toString();
        File downloadDir = new File(downloadDirPath);
        File[] csvFiles = downloadDir.listFiles((dir, name) -> name.endsWith(".csv"));

        // Ensure there are CSV files in the download directory
        Assert.assertNotNull(csvFiles, "No files found in the download directory.");
        Assert.assertTrue(csvFiles.length > 0, "No CSV files found in the download directory.");

        // Find the most recently modified CSV file
        File downloadedFile = Arrays.stream(csvFiles)
                .max(Comparator.comparingLong(File::lastModified))
                .orElseThrow(() -> new AssertionError("No CSV files found in the download directory."));

        // Assert file existence and non-emptiness
        Assert.assertTrue(downloadedFile.exists(), "The file was not downloaded successfully.");
        Assert.assertTrue(downloadedFile.length() > 0, "The downloaded file is empty.");
        
        // Clean up downloaded file after verification
        Files.delete(downloadedFile.toPath());
    }
}
