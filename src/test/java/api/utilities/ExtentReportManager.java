package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    String reportName;


        public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timestamp + ".html";

        sparkReporter=new ExtentSparkReporter(".\\Reports\\" + reportName);//specify location of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
        sparkReporter.config().setReportName("Sample API"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Pest Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environemnt","QE");
        extentReports.setSystemInfo("user","Chinmay");
    }

        public void onTestSuccess(ITestResult result) {
            extentTest=extentReports.createTest(result.getName());
            extentTest.assignCategory(result.getMethod().getGroups());
            extentTest.createNode(result.getName());
            extentTest.log(Status.PASS, "Test Passed");
        }    
        
        public void onTestFailure(ITestResult result) {
            extentTest=extentReports.createTest(result.getName());
            extentTest.createNode(result.getName());
            extentTest.assignCategory(result.getMethod().getGroups());
            extentTest.log(Status.FAIL, "Test Failed");
            extentTest.log(Status.FAIL, result.getThrowable().getMessage());
        }
        
    
        public void onTestSkipped(ITestResult result) {
            extentTest=extentReports.createTest(result.getName());
            extentTest.createNode(result.getName());
            extentTest.assignCategory(result.getMethod().getGroups());
            extentTest.log(Status.SKIP, "Test Skipped");
            extentTest.log(Status.SKIP, result.getThrowable().getMessage());
        }
        
        public void onFinish(ITestContext context) {
        	extentReports.flush();
        }
}