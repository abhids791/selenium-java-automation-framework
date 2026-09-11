package brightlyTestQa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.nio.file.Paths;

public class Util {

    public static ExtentReports getReporterObject(){
        String reportPath = Paths.get(
                System.getProperty("user.dir"),
                "reports",
                "index.html"
        ).toString();
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Abhijit Das");
        return extent;
    }

}
