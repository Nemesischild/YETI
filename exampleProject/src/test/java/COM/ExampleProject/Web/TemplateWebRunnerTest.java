package COM.ExampleProject.Web;

//import COM.ExampleProject.Web.Suites.ExampleSiteLaunchTest;
import COM.ExampleProject.Web.Suites.AccessibilityTest1;
import com.runner.annotations.Setup;
import com.runner.runner.EnhancedSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

@RunWith(EnhancedSuite.class)
@Suite.SuiteClasses({
        AccessibilityTest1.class,
})
@Setup(
        application = "RP web Accessibility POC Audit",
        reportType = {
                Setup.ReportType.EXCEL,
                Setup.ReportType.EXTENT_REPORT
        },
        version = "1",
        attempt = 2
)
public class TemplateWebRunnerTest {
    public static String rootLocation = "src/main/resources/chromedriver";


    public static ChromeDriver driver;

    public void onTestStarted(String className, String methodName) throws IOException {

        if (driver == null) {
            StartWebRunner();
        }
    }

    public void onTestCompleted(String className, String methodName) {

    }

    public void onTestFinished(boolean result, String className, String methodName) {
        if (driver != null) {
            driver.quit();
        }
    }

    public void onTestFailure(String className, String methodName, String message, String stack) {

    }

    public void onTestPassed(String className, String methodName) {
    }

    public void onTestIgnored(String className, String methodName) {

    }

    public void afterSuite() {

    }

    public static void customWebRunnerStart() {

        StartWebRunner();

    }

    private static void StartWebRunner() {
        if (driver == null) {
            ChromeOptions cOpts = new ChromeOptions();
            // Activate line below to run Headless testing
            //cOpts.addArguments(("--headless"));

            System.setProperty("webdriver.chrome.driver", rootLocation);
            driver = new ChromeDriver(cOpts);

        }
    }
}

