package utiles.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.FilesUtils;
import utiles.commonHelper.LogsUtils;

import java.io.File;
import java.util.Arrays;

public class ExtentReportListener implements ITestListener, IExecutionListener {
    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    File reports = new File("test-output/reports");
    File logs = new File("test-output/Logs");
    @Override
    public void onExecutionStart() {
        LogsUtils.info("Test Execution Started.");
        // loadProperties();
        FilesUtils.deleteFiles(reports);
        FilesUtils.cleanDirectory(logs);
        //FilesUtils.cleanDirectory(screenshots);
        FilesUtils.createDirectory(reports);
        FilesUtils.createDirectory(logs);
        //FilesUtils.createDirectory(screenshots);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        ExtentTest extentTest = extent.createTest(testName)
                .assignCategory(className);
        test.set(extentTest);
        test.get().log(Status.INFO, "🚀 Test Started: " + testName);
        if (result.getParameters().length > 0) {
            test.get().log(Status.INFO, "Test Data: " + Arrays.toString(result.getParameters()));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.PASS, "✅ Test Passed in " +
                    (result.getEndMillis() - result.getStartMillis()) + " ms");
            test.get().addScreenCaptureFromBase64String(captureScreenshot(), "Success Screenshot");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
            test.get().addScreenCaptureFromBase64String(captureScreenshot(), "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static String captureScreenshot() {
        try {
            TakesScreenshot scrShot = (TakesScreenshot) DriverManager.getDriver();
            return scrShot.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            return "Screenshot capture failed: " + e.getMessage();
        }
    }
}
