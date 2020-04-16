package COM.ExampleProject.LoadBalanceWebTest;

import com.automation.WebNavUtils;
import com.runner.annotations.Setup;
import com.runner.interfaces.EnhancedTestInterface;
import com.runner.runner.EnhancedSuite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(EnhancedSuite.class)

@Suite.SuiteClasses({
        LoadTestWebRunner.class
})

@Setup(
        application = "ExampleProject (Web)",
        reportType = {
                Setup.ReportType.EXCEL,
                Setup.ReportType.EXTENT_REPORT
        },
        version = "1",
        attempt = 2
)


public class LoadTestWebRunner implements EnhancedTestInterface {
    private final String VIDEO_URL = null;
    private static RemoteWebDriver driver;


    public void onTestStarted(String className, String methodName) throws MalformedURLException {

        // these 3 environment variables in each request.
        String platform_name = "win7";
        String browser_name = "chrome";
        String browser_version = "latest";

        // optional video recording
        String record_video = "False";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setPlatform(Platform.WIN10);
        capabilities.setCapability("platformName", "windows"); //required from selenium version 3.9.1 when testing with firefox or IE
        capabilities.setBrowserName(browser_name);
        capabilities.setVersion(browser_version);

        //Chrome specifics
        if (browser_name.equalsIgnoreCase("chrome")) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
            prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
            prefs.put("profile.default_content_setting_values.media_stream_geolocation", 1);
            prefs.put("profile.default_content_setting_values.media_stream_notifications",1);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--use-fake-device-for-media-stream");
            options.addArguments("--use-fake-ui-for-media-stream");
            options.addArguments("disable-infobars");
            System.out.println("RemoteWebDriver Fake Media Stream has been added");




            // starting from Chrome 57 the info bar displays with "Chrome is being controlled by automated test software."
            // On Linux start-maximized does not expand browser window to max screen size. Always set a window size and position.
            if (platform_name.equalsIgnoreCase("linux")) {
                options.addArguments(Arrays.asList("--window-position=0,0"));
                options.addArguments(Arrays.asList("--window-size=1920,1080"));
            } else

            {
                options.addArguments(Arrays.asList("--start-maximized"));
            }
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        }

        //replace USERNAME:ACCESS_KEY@SUBDOMAIN with your credentials found in the Gridlastic dashboard
        driver = new RemoteWebDriver(new URL("http://4aQnXhWQvn5L8GvwetLMeEcZL09yPjdK:kUX71KNnXJYWgjjlDUXHpZx1QwpwGt6R@VBRNP9TN.gridlastic.com:80/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));

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
        driver.quit();
    }


    public static void loginToDashBoard() throws InterruptedException {
        Thread.sleep(3000);
        driver.get("https://uat.journeysguitar.com");
        try {
            Thread.sleep(5000);
            WebNavUtils.scrollToElementAndClickbyCSS(driver,
                    "#cookie-warning > div > button");
            WebNavUtils.clickOnElementByCSS(driver,
                    "#LINK_TO_SIGN_IN_PAGE");
            WebNavUtils.clickOnElementByCSS(driver,
                    "#logonIdentifier");
            WebNavUtils.enterTextByCSS(driver, "#logonIdentifier",
                    "ConnorUATtest7");
            WebNavUtils.enterTextByCSS(driver, "#password",
                    "coz@12345");
            WebNavUtils.clickOnElementByCSS(driver,
                    "#next");
            WebNavUtils.checkForElementbyCSS(driver,
                    "#LINK_TO_DASHBOARD");
            System.out.println("Sign in successful");
        } catch (Exception e) {
        }
    }

    public static void micSelect() throws InterruptedException {

//        WebNavUtils.scrollToElementAndClickbyCSS(driver,
        //"#root > div > div.container > div > a");

        Thread.sleep(5000); //Letting the page load
        WebElement element = driver.findElementByCssSelector(
                "#root > div > div.media-wrapper > div > div.inputs-and-image-container.media-row.row > div.inputs-wrapper > div > div.select-wrapper > select");
        Select select = new Select(element);
        Thread.sleep(5000);  //This is just to give it time to find the element
        select.selectByIndex(1);
        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.media-wrapper > div > div.two-button-wrapper > div:nth-child(2) > button");
        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#top > div.container > div > div:nth-child(3) > div > button.grey-outline-cta-button");
        WebNavUtils.clickOnElementByCSS(driver,
                "body > div.ReactModalPortal > div > div > div > div > button.secondary-solid-cta-button");

        System.out.println("Sucessfully clicked on the microphone");

    }



    public static void loginToDashboardAndOpenPathWay() throws InterruptedException {
        loginToDashBoard();
        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.pathway-teaser-wrapper > div.container > div > button");

        WebNavUtils.clickOnElementByCSS(driver,
                "body > div.ReactModalPortal > div > div > div > div > button.secondary-solid-cta-button");

        System.out.println("Pathway opened successfully");


    }

    @Test
    public void QTMS_02_loginAndCreateLearningPathway() throws InterruptedException {


        loginToDashboardAndOpenPathWay();

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(1)");

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(2)");

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.container > div > div > button");

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(1)");

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(2)");

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.container > div > div > button.secondary-solid-cta-button.white-icon-right");

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(1)");

        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.song-catalogue-wrapper.pathway-wrapper > div.song-catalogue-container.container > div > div:nth-child(2)");

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.container > div > div > button.secondary-solid-cta-button.white-icon-right");

        System.out.println("Pathway Has been created successfully");
    }

    @Test
    public void QTMS_03_logInAndGoToRecordingStudioFirstSong() throws InterruptedException {
        loginToDashBoard();

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.pathway-teaser-wrapper > div.container > div > div > div.pathway-card-text-wrapper > div.pathway-card-box.first > a");

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#root > div > div.container > div > a");


        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.button-group-wrapper > div > button.second-button");

        Thread.sleep(10000);
        WebElement element = driver.findElementByCssSelector(
                "#root > div > div.media-wrapper > div > div.inputs-and-image-container.media-row.row > div.inputs-wrapper > div > div.select-wrapper > select");
        Select select = new Select(element);
        Thread.sleep(10000);  //This is just to give it time to find the element
        select.selectByIndex(1);
        WebNavUtils.clickOnElementByCSS(driver,
                "#root > div > div.media-wrapper > div > div.two-button-wrapper > div:nth-child(2) > button");
        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#top > div.container > div > div:nth-child(3) > div > button.grey-outline-cta-button");
        WebNavUtils.clickOnElementByCSS(driver,
                "body > div.ReactModalPortal > div > div > div > div > button.secondary-solid-cta-button");

        System.out.println("Sucessfully clicked on the microphone");

        Thread.sleep(10000); //Lets the page load

        WebNavUtils.scrollToElementAndClickbyCSS(driver,
                "#record-button");

        System.out.println("Now recording the video");

        int msWait = 800000;

        WebElement element1 =
                WebNavUtils.waitForElementWithTimeoutCSS(driver,
                        "button.secondary-solid-cta-button.white-icon-right", msWait);

        if (element1 == null) {
            Assert.fail("Unable to find the requested element");
        }

        System.out.println("Found element");

        WebElement element2 =  WebNavUtils.findElementbyCSS(driver,
                "button.secondary-solid-cta-button.white-icon-right");

        element2.sendKeys(Keys.TAB);
        element2.sendKeys(Keys.TAB);
        element2.sendKeys(Keys.ENTER);

        Thread.sleep(70000); //Giving it 1:10 min to process the video before the implicit wait kicks in

        WebNavUtils.hoverOverElementAndClickByCss(driver,
                "button.secondary-solid-cta-button.white-tick-icon-left.finish-btn");

        System.out.println("Success! Video has been merged!");

    }




}
