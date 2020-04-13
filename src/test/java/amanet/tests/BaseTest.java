package amanet.tests;

import amanet.page.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static ExtentTest extentLogger;
    protected static ExtentReports extent;
    protected static ExtentHtmlReporter reporter;


    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            extentLogger.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentLogger.skip(result.getThrowable());
        }
        driver.quit();
        extent.flush();
    }

    @BeforeClass
    public void startReport() {
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        extent.setSystemInfo("Host Name", "Amanet");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Yigal Ulger");
    }

    public ExtentHtmlReporter getHtmlReporter() {
        reporter= new ExtentHtmlReporter(System.getProperty("user.dir") +"/AmanetReport.html");
        reporter.config().setChartVisibilityOnOpen(true);
        reporter.config().setDocumentTitle("QA Automation Report");
        reporter.config().setReportName("Regression Testing");
        reporter.config().setTheme(Theme.STANDARD);
        return reporter;
    }

}
