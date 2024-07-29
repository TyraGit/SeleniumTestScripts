package athirahrahmat.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshots extends BaseTest {
	
	private static final int MAX_SCREENSHOTS = 20; // Maximum number of screenshots to keep
	
	public void takeScreenshots() throws InterruptedException, IOException {
		
		// Pause for 5 seconds
		Thread.sleep(5000); 

		// Take a screenshot
		//this screenshot only takes the visible view, not the whole page
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		// Generate a unique filename with a timestamp instead of deleting the screenshot file manually
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        
		File destinationFile = new File("target/screenshots/screenshot" + timestamp + ".png");
		Files.copy(screenshot.toPath(), destinationFile.toPath());
		
		// Clean up old screenshots
        cleanUpOldScreenshots(new File("screenshots"));
	
	}
	
	//method to cleanup file if there are more than 20 files in the screenshots folder
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
