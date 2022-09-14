package COM.ExampleProject.Common;

import COM.ExampleProject.Android.ExampleAndroidRunner;
import COM.ExampleProject.Web.ExampleProjectWebRunner;
import com.automation.WebNavUtils;
import com.runner.accessibility.AccessibilityScanner;
import com.runner.accessibility.Result;
import com.runner.runner.EnhancedAssertion;
import com.runner.runner.EnhancedLogging;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static COM.ExampleProject.Android.ExampleAndroidRunner.elementPrepend;

/**
 * This is where we use abstraction of pre-built navigation methods
 * All of our tests are STATELESS. This means, every-time the @Test hook is called, the driver will be broken down and
 * re-supplied the capability set from the runner.
 * Below is an example of an abstracted function for re-use with other tests
 * This is the onboarding and log-on function for musicCaseAndroid as the example
 */

public class ExampleProjectNav {

    public static void loggingIntoAppWithOnboardingSkip() {


        try {
            Thread.sleep(5000);

            ExampleAndroidRunner.wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id(elementPrepend + ":id/onboarding_navigation_skip"))).click();
        } catch (Exception e) {

            Assert.fail("unable to skip onboarding" + e);
        }

        try {
            ExampleAndroidRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementPrepend + ":id/login_select_login")));

        } catch (Exception e) {

            Assert.fail("Unable to click on the initial login button" + e);


        }
        try {
            ExampleAndroidRunner.driver1.findElementById(elementPrepend + ":id/login_select_login").click();
            Thread.sleep(5000);
            ExampleAndroidRunner.driver1.findElementByXPath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText[1]").click();
            ExampleAndroidRunner.driver1.findElementByXPath(
                    " /hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText[1]").setValue("ConnorDevTest1");
            ExampleAndroidRunner.driver1.findElementByXPath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText[2]").click();
            ExampleAndroidRunner.driver1.findElementByXPath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText[2]").setValue("coz@12345");
            ExampleAndroidRunner.driver1.findElementByXPath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.Button").click();
            Thread.sleep(2000);

        } catch (Exception e) {
            Assert.fail("Unable to login" + e);
        }


        /**
         * This is where you will move pre-built navigation code for core requirements when restarting the application
         * Keeping in mind every-time @Test is called the entire cache will be cleared so things like logging in/Onboarding
         * or anything that requires a specific action to proceed.
         *It can sometimes be important to consider tear-down as-well. If you are directly adding anything to an account
         * consider if it needs to be removed in the same or next operation.
         */

    }


    public static void AcessibilityAuditAllLinksOnCurrentPage(RemoteWebDriver driver, String sRootURL, String sBoundryURL) throws Throwable {
        List<WebElement> elements;
        List<String> clickedLinks = new ArrayList<>();

        String sURL = "";
        boolean ignoreLink = false;

        // Find all a href links on the current page into a usable list
        elements = driver.findElements(By.tagName("a"));
        int sizeOfAllLinks = elements.size();


        //      Loop through the grabbed list
        for (int i = 0; i < sizeOfAllLinks; i++) {
            // record what URL is being opened
            sURL = elements.get(i).getAttribute("href");
            // Check if Link has already been clicked
            for (String thisURL : clickedLinks) {
                ignoreLink = thisURL.equals(sURL);
                // Set ignore flag if link found in  clicked URLs list
                if (ignoreLink) {
                    break;
                }
            }
                     // Check Ignore flag
            if (!ignoreLink) {

                // try to click the current link
                try {
                    //ignore mailto Links
                    if (sURL.contains(sBoundryURL) && !sURL.contains("mailto:") && !sURL.contains("tel:")) {
                        EnhancedLogging.debug("Element index: "+ i +" - Attempting to open: " + sURL);


                            ExampleProjectWebRunner.driver.navigate().to(sURL);


                        //elements.get(i).click();
                        // Add link URL to clicked list
                        clickedLinks.add(sURL);

                        AccessibilityScanner scanner = new AccessibilityScanner(ExampleProjectWebRunner.driver);
                        Map<String, Object> accessibilityReport = scanner.runAccessibilityAudit();

                        List<Result> errors = new ArrayList<Result>();
                        List<Result> warnings = new ArrayList<Result>();
                        errors = ((List<Result>) accessibilityReport.get("error"));
                        warnings = ((List<Result>) accessibilityReport.get("warning"));
                        String elementList = "";
                        if(errors.isEmpty() && warnings.isEmpty()){
                            EnhancedAssertion.softAssertCondition(true, "<p><strong>"+sURL + "</strong></p>\nNo Accessibility errors or warnings");
                        }
                        for (Result error : errors) {
                            for (String element : error.getElements()) { //violated elements
                                elementList = elementList + "<li style=\"text-align: left\" >" + element + "</li>\n";
                            }
                            EnhancedAssertion.softAssertCondition(false, "<p><strong>"+sURL + "</strong></p>"+ "\nERROR: Rule:" + error.getRule() + "\nURL: " + error.getUrl() + "\n ELEMENT <font color='black'> <ol type=\"1\">" + elementList + "</ol></font>");//e.g. AX_TEXT_01
                            elementList = "";

                        }
                        for (Result warning : warnings) {

                            for (String element : warning.getElements()) { //violated elements
                                elementList = elementList + "<li style=\"text-align: left\" type=\"I\">"+element+"</li>\n";

                            }
                            EnhancedAssertion.softAssertCondition(false, "<p><strong>"+sURL + "</strong></p>" + "\nWARNING: Rule:" + warning.getRule() + "\nURL: " + warning.getUrl() + "\n ELEMENT <font color='black'> <ol type=\"1\">"+ elementList +"</ol></font>");//e.g. AX_TEXT_01
                            elementList ="";

                        }


                    } else {
                        System.out.println("Element index: "+ i +" - IGNORING: " + sURL + " not in scope");
                    }
                } catch (Exception e) {
                    //ignore hidden element exceptions
                    if (!e.getMessage().contains("element not visible")) {
                        System.out.println("Element index: "+ i +" - Did not open URL:  " + sURL + "\n Recieved :" + e.getMessage() + "\n");
                    } else {
                        // report the error
                        System.out.println("Element index: " + i + " " + sURL + " - is not a visible element that can be clicked");
                    }
                }
                // Check window Tab titles
                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                // Report "Page not found" Error  discovered
                if (driver.switchTo().window(tabs.get(0)).getTitle().contains("Page Not Found")) {
                    System.out.println("\n ERROR: " + sURL + "returned '404 Page Not Found'");
                    EnhancedAssertion.softAssertCondition(false, "\n ERROR: " + sURL + "returned '404 Page Not Found'");
                }

                // Navigate back to the previous page
                driver.navigate().to(sRootURL);

                WebNavUtils.closeAllButRootBrowserTabs(driver);
            }
            Thread.sleep(1000);
            // Clear the list
            elements.clear();
            // grab all the links again (so the index count remains the same)
            elements = driver.findElements(By.tagName("a"));
            //System.out.println(sizeOfAllLinks);

        }
    }


}




