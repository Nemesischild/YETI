package COM.ExampleProject.Android;
import COM.ExampleProject.Android.Suites.ExampleOnboarding;
import com.runner.annotations.Setup;
import com.runner.interfaces.EnhancedTestInterface;
import com.runner.runner.EnhancedSuite;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;


@RunWith(EnhancedSuite.class)

/**
 * We use the @RunWith in order to implement our enchancedSuite and EnchancedInterface
 */


@Suite.SuiteClasses({
        ExampleOnboarding.class,

/**
 * Here we declare what classes the EnhancedSuite Runner will look for to run - This can be more than one just
 * add a class in the same fashion as above, separated with a comma.
 */

})
@Setup(
        application = "ExampleProject (Android)",
        reportType = {
                Setup.ReportType.EXCEL,
                Setup.ReportType.EXTENT_REPORT
        },
        version = "1",
        attempt = 4

        /**
         * The @Setup is just for our reporter, the attempt number is the times the suite has been ran,
         * Version is the version number of the text#
         * Report type is always Excel as we use reflection of the results from J-Unit and
         * Enhanced test runner/Interface in order to output our completed report.
        */

)
public class ExampleAndroidRunner implements EnhancedTestInterface {

    /**4
     * When the above requirements are met we can now declare our class. Keeping in mind it HAS to
     * implement the EnchancedTestInterface or our runner won't be able to bind to anything.
     */

    public static WebDriverWait wait;
    public static AndroidDriver<MobileElement> driver1;
    public static String port1 = "4723";
    public static String adbTcpPort = "5555";
    public static String elementPrepend;

    /**
     * Static declarations - These are mainly for use when adding a parameter as there are elements of this runner that are
     * subject to frequent change - This just allows us to deal with changes in one place instead of changing things all over the
     * class, simply alter the constants when required
     */

    public static void destroyDriver() {
        driver1.quit();
    }

    /**
     * Here we are defining out method to destroy the driver. This is used in our OnTestFinished Method and just creates a
     * teardown of the driver between every test
     */

    public void onTestStarted(String className, String methodName) throws MalformedURLException {

        DesiredCapabilities caps1 = new DesiredCapabilities();
        caps1.setCapability("deviceName", "Galaxy S6");
        caps1.setCapability("udid", "10.72.33.79:" + adbTcpPort);
        caps1.setCapability("platformName", "Android");
        caps1.setCapability("platformVersion", "8.0");
        caps1.setCapability("skipUnlock", true);
        caps1.setCapability("appPackage", "org.abrsm.music.case.qa");
        caps1.setCapability("appActivity", "com.abrsm.musiccase.ui.splash.SplashActivity");
        caps1.setCapability("noReset", false);
        caps1.setCapability("clearSystemFiles", true);
        caps1.setCapability("automationName", "uiautomator2");
        setupDriver(caps1);
        System.out.println("Driver Created");

        /**
         * This is our method to pass the driver it's required capabilities. As above we have defined that
         * new capabilities to this driver will be called as caps1, and within this call set we are passing it all
         * required parameters for uiautomator2 to start our tests once a connection to the appium server has been made
         */


        driver1 = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port1 + "/wd/hub"), caps1);
    }

    public static void setupDriver(DesiredCapabilities capabilities) throws MalformedURLException {

        URL url1 = new URL("http://127.0.0.1:" + port1 + "/wd/hub");

        try {
            driver1 = new AndroidDriver<MobileElement>(url1, capabilities);
            wait = new WebDriverWait(driver1, 10);
            elementPrepend = driver1.getCapabilities().getCapability("appPackage").toString();
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println("\n\n" + e.getMessage() + " \n\n\n " + e + "\n\n");
        }

        /**
         * This is our method to create the driver, here we are passing it The following parameters
         * A URL Defined as url1 in this instance, that passes The servers address, using the port1 parameter instead of
         * hard-coding it. As mentioned above this is so changes to any of the statics can be
         * We wrap the creation in a try catch, this is because if there is an error creating the driver we get
         * more information in the stack-trace for debugging
         */


    }

    public void onTestFinished(boolean result, String className, String methodName) {
        destroyDriver();
    }

    public void onTestFailure(String className, String methodName, String message, String stack) {

    }

    public void onTestPassed(String className, String methodName) {
    }

    public void onTestIgnored(String className, String methodName) {

    }

    public void afterSuite() {

        /**
         * These are just are test position method calls, currently only
         * onTestFinishes has any logic applied to it, which is the driver teardown. However,
         * Feel free to add any methods or surrounding logic in order to aid your test suites.
         */

    }
}


/**
 * Javadoc Authored by Connor Lovegrove
 */
