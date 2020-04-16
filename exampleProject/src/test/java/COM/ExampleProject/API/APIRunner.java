package COM.ExampleProject.API;


import COM.ExampleProject.API.Suites.GitHubPOC;
import com.automation.APIUtils;
import com.runner.annotations.Setup;
import com.runner.interfaces.EnhancedTestInterface;
import com.runner.runner.EnhancedSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(EnhancedSuite.class)



@Suite.SuiteClasses({

        GitHubPOC.class,

})



@Setup(
        application = "API Example GitHUB Endpoint",
        reportType = {
                Setup.ReportType.EXCEL,
                Setup.ReportType.EXTENT_REPORT,
        },
        version = "1",
        attempt = 2
)


public class APIRunner implements EnhancedTestInterface {

    public static String rootLocation = "src/main/resources/chromedriver";
    public static ChromeDriver driver;

    private static String pomAPIUri = System.getProperty("APIEndPoint");
    public static String APIUri;
    private static void setAPIEndPoint(){

        if (pomAPIUri.contains("https://")){
            APIUri = pomAPIUri;
        }
        else {
            APIUri = pomAPIUri.replace("https:/", "https://");
        }
    }

   public void onTestStarted(String className, String methodName) {
       setAPIEndPoint();
    }

    public void onTestCompleted(String className, String methodName) {

    }
    public void onTestFinished(boolean result, String className, String methodName) {

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

            System.setProperty("webdriver.chrome.driver", rootLocation);
            driver = new ChromeDriver();

        }
    }
}

