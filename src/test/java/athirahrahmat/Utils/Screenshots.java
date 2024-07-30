package athirahrahmat.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

    private static final int MAX_SCREENSHOTS = 20; // Maximum number of screenshots to keep
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    // Method to take a screenshot with a custom filename
    public void takeScreenshot(String baseFileName) throws InterruptedException, IOException {
        // Pause for 5 seconds
        Thread.sleep(5000);

        // Take a screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Generate a unique filename with a timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = baseFileName + timestamp + ".png";

        File destFile = new File("target/screenshots/" + fileName);
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot saved as " + fileName);

        // Clean up old screenshots
        cleanUpOldScreenshots(new File("target/screenshots"));
    }

    // Method to clean up old screenshots
    private void cleanUpOldScreenshots(File folder) {
        File[] files = folder.listFiles();
        if (files != null && files.length > MAX_SCREENSHOTS) {
            // Sort files by last modified date
            Arrays.sort(files, (f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
            // Delete oldest files
            for (int i = 0; i < files.length - MAX_SCREENSHOTS; i++) {
                files[i].delete();
            }
        }
    }
}
