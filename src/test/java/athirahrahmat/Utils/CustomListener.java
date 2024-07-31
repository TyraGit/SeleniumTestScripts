package athirahrahmat.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomListener implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Format current timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String suiteName = context.getSuite().getName();
        String reportFileName = suiteName + "_Test Report_" + timeStamp + ".html";

        // Specify the location of the report
        String reportFilePath = "test-output/test reports/" + reportFileName;
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Optional: Delete old reports
        //deleteOldReports("test-output/test reports/");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped test
        test.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Write the report to the file
        extent.flush();
    }
    
    /*// Method to delete old reports
    private void deleteOldReports(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".html"));
        
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }*/
}
