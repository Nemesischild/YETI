package COM.ExampleProject.iOS;


import COM.ExampleProject.iOS.Suites.ExampleLogin;
import com.runner.annotations.Setup;
import com.runner.interfaces.EnhancedTestInterface;
import com.runner.runner.EnhancedSuite;
import io.appium.java_client.ios.IOSDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(EnhancedSuite.class)
@Suite.SuiteClasses({
        ExampleLogin.class
})
@Setup(
        application = "RAC Consumer",
        reportType = {
                Setup.ReportType.EXCEL,
                Setup.ReportType.EXTENT_REPORT
        },
        version = "1.1(Parallel Runner)",
        attempt = 4
)

public class ExampleProjectiOSRunner implements EnhancedTestInterface {


    static SoftAssert softassert = new SoftAssert();
    public static IOSDriver driver;
    public static WebDriverWait wait;
    public static URL url;


    public static void setupDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new IOSDriver(url, capabilities);
        wait = new WebDriverWait(driver, 10);



    }

    public static void destroyDriver() {
        driver.quit();
    }

    public void onTestStarted(String className, String methodName) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        System.out.println("Creating Driver");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "10.3.1");
        caps.setCapability("deviceName", "MOO-00191");
        caps.setCapability("xcodeSigningId", "iPhone Developer");
        caps.setCapability("xcodeOrgId", "67CWSQ36P2");
        caps.setCapability("app", "/Users/admim/Desktop/IPA_files/Mc_UAT_70.ipa");
        caps.setCapability("bundleId", "com.abrsm.music-notebooks");
        caps.setCapability("udid", "f82060d16e7498d5f58dd7a5acd066d94e31cd32");
        caps.setCapability("fullReset", true);
//        caps.setCapability("permissions", "{\"uk.co.o-net.ractravel\": {\"location\":\"unset\"}}");
        //caps.setCapability("autoGrantPermissions", true);
        try {
            setupDriver(caps);

        } catch (Exception e) {
            System.out.println("\nERROR:" + e.getMessage() + "\n");
        }
    }

    public void onTestFinished(boolean result, String className, String methodName) {
        softassert.assertAll();
        destroyDriver();
    }


    public void onTestFailure(String className, String methodName, String message, String stack) {

    }


    public void onTestPassed(String className, String methodName) {

    }


    public void onTestIgnored(String className, String methodName) {

    }

    @AfterSuite
    public void afterSuite() {

    }
}


