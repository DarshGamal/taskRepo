package utiles.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public  final class ExtentReportManager {
    private static ExtentReports extent;

    private ExtentReportManager() {
        // private constructor to prevent instantiation
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Functional Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // System info
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Rofida");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
