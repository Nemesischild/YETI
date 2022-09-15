package COM.ExampleProject.Web;


import COM.ExampleProject.Web.Suites.GoogleResponsivenessPOC;
import com.runner.annotations.Setup;
import com.runner.interfaces.EnhancedTestInterface;
import com.runner.runner.EnhancedSuite;
import com.automation.RunnerUtils;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.Locale;
import java.net.MalformedURLException;


@RunWith(EnhancedSuite.class)

/**
 * We use the @RunWith in order to implement our enchancedSuite and EnchancedInterface
 */

@Suite.SuiteClasses({
       GoogleResponsivenessPOC.class,
 })

/**
 * Here we declare what classes the EnhancedSuite Runner will look for to run - This can be more than one just
 * add a class in the same fashion as above, separated with a comma.
 */

@Setup(
        application = "RP Accessibility audit POC",
        reportType = {
                Setup.ReportType.EXTENT_REPORT
        },
        version = "1",
        attempt = 2
)

///**
// * The @Setup is just for our reporter, the attempt number is the times the suite has been ran,
// * Version is the version number of the text#
// * Report type is always Excel as we use reflection of the results from J-Unit and
// * Enhanced test runner/Interface in order to output our completed report.
// */


public class ExampleProjectWebRunner implements EnhancedTestInterface {

    /**
     * When the above requirements are met we can now declare our class. Keeping in mind it HAS to
     * implement the EnchancedTestInterface or our runner won't be able to bind to anything.
     */

//    public static String rootLocation = "src/main/resources/chromedriver.exe";
    public static String rootLocation = "src/main/resources/chromedriver";
    public static ChromeDriver driver;

    /**
     * Static declarations - These are mainly for use when adding a parameter as there are elements of this runner that are
     * subject to frequent change - This just allows us to deal changes in one place instead of changing things all over the
     * class, simply alter the constants when required
     */


    public void onTestStarted(String className, String methodName) throws MalformedURLException {

        /**
         * Here we are using the onTeststarted method in order to initialize our driver and pass it
         * any required capabilities or pre defined settings before the test execution starts
         */


        if (driver == null) {

            System.setProperty("webdriver.chrome.driver", rootLocation);

            ChromeOptions chromeOptions = new ChromeOptions();
            LoggingPreferences logPrefs = new LoggingPreferences();
            chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
            driver = new ChromeDriver(chromeOptions);

            /**
             * We use a conditional statement here to determine if the driver needs to be created.
             * we are saying if, the driver is equal to null (Non existent - ==)
             * then create a new driver using the System.setProperty call followed by rootLocation
             * rootLocation is the static we declared before the method in order to keep system level changes
             * in 1 place, thus making the class easier to alter on a per device set-up
             */
        }
    }

    public void onTestFinished(boolean result, String className, String methodName) {

        if (driver != null) {
            driver.quit();
            driver=null;

            /**
             * Here we are using another conditional statement but this time we are saying if
             * the driver is greater than or equal to (!=) null then we call driver.quit
             * this is to ensure there are no instances of a driver when we call the next @Test method
             * Which will in turn call the onTestStarted method.
             */
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

    /**
     * These are just are test position method calls, currently only
     * onTestFinished has any logic applied to it, which is the driver teardown. However,
     * Feel free to add any methods or surrounding logic in order to aid your test suites.
     */

}

/**
 * Javadoc authored by
 * Connor Lovegrove
 */