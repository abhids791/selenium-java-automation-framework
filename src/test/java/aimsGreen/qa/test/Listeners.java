package aimsGreen.qa.test;

import AimsGreen.QA.utils.util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = util.getReporterObject();
    ExtentTest test;


    @Override
    public void onTestStart(org.testng.ITestResult result) {
        // Code to execute when a test starts
        test= extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        // Code to execute when a test succeeds
        test.log(com.aventstack.extentreports.Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(org.testng.ITestResult result) {
        // Code to execute when a test fails
        test.fail(result.getThrowable());
        WebDriver driver = null;
        String filePath =null;
        try {
            driver =(WebDriver) result.getTestClass().getRealClass().getField(("driver")).get(result.getInstance());
            System.out.println("Driver in Listener: "+driver);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            filePath= takeScreenShot(result.getMethod().getMethodName(), driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result) {
        // Code to execute when a test is skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) {
        // Code to execute when a test fails but is within success percentage
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Code to execute before any test starts
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Code to execute after all tests have finished
        extent.flush();
    }
}
