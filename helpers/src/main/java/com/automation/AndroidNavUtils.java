package com.automation;

import com.runner.runner.EnhancedLogging;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class AndroidNavUtils {

    public static ChromeDriver driver;
    public static WebDriverWait wait;


    /**
     * --clickOnElementByID--
     *
     * @param driver    driverObject
     * @param elementID
     * @return boolean: True is the pass condition. False is the failure condition.
     * @throws InterruptedException
     */

    public static boolean clickOnElementByID(AndroidDriver<MobileElement> driver, String elementID) throws InterruptedException {
        try {
           getElementByIDUsingWaitAndroid(driver, elementID, 3).click();
        return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click on the specified element" + e.getMessage());
            return false;
        }
    }

    /**
     * -- clickOnElementByxPath --
     *
     * @param driver driverObject
     * @param xPath
     * @return boolean: True is the pass condition. False is the failure condition.
     */

    public static boolean clickOnElementByxPath(AndroidDriver<MobileElement> driver, String xPath) {
        try {
            getElementByXPATHUsingWaitAndroid(driver, xPath, 3).click();
            //AndroidNavUtils.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click on the specified element" + e.getMessage());
            return false;
        }
    }

    /**
     * -- checkForElementByID --
     *
     * @param driver    driverObject
     * @param elementID
     * @return boolean: True is the pass condition. False is the failure condition
     */

    public static MobileElement checkForElementByID(AndroidDriver<MobileElement> driver, String elementID) {
        driver.findElement(By.id(elementID));
        return driver.findElement(By.id(elementID));
    }


    /**
     *
     * -- checkForElementByxPath --
     *
     * @param driver
     * @param xPath
     * @return
     */
    public static MobileElement checkForElementByxPath(AndroidDriver<MobileElement> driver, String xPath) {
        driver.findElement(By.xpath(xPath));
        return driver.findElement(By.xpath(xPath));
    }


    /**
     *
     * -- androidSwipe --
     *
     * @param driver
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return
     * @throws InterruptedException
     */
    public static boolean androidSwipe(AndroidDriver<MobileElement> driver, int fromX, int fromY, int toX, int toY) throws InterruptedException {
        AndroidTouchAction swipe = new
                AndroidTouchAction(driver);
        try {
            swipe.press(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release();
            return true;

        } catch (Exception e) {
            EnhancedLogging.debug("Swipe Action failed" + e.getMessage());
            return false;
        }
    }

    //Get size of screen and swipes, in a direction from the exact centre to a Point of testers choosing


    /**
     * -- screenSizeSwipe --
     *
     * @param driver      driverObject
     * @param swipeType
     * @param swipeLength
     * @return
     */

    public static boolean screenSizeSwipe(AndroidDriver<MobileElement> driver, String swipeType, int swipeLength) {

        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;
        int endY;
        int endX;

        switch (swipeType.toLowerCase()) {
            case "up":
                endX = startX;
                endY = startY - swipeLength;
                break;
            case "down":
                endX = startX;
                endY = startY + swipeLength;
                break;
            case "left":
                endX = startX - swipeLength;
                endY = startY;
                break;
            case "right":
                endX = startX + swipeLength;
                endY = startY;
                break;
            default:
                return false;

        }

        TouchAction action = new TouchAction(driver);

        try {
            action.press(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
            return true;

        } catch (Exception e) {
            EnhancedLogging.debug("Scroll action failed : \n" + e.getMessage());
            return false;
        }

    }





    /**
     * -- actionScrollToFindElementByText --
     *
     * @param driver
     * @param sText
     * @param iAttempts
     * @return
     */
    public static boolean actionScrollToFindElementByText(AndroidDriver<MobileElement> driver, String sText, int iAttempts) {


        boolean elementVisible = false;
        int attemptCounter = 0;

        while (!elementVisible && attemptCounter <= iAttempts) {

            try {
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + sText + "\")");
                elementVisible = true;

            } catch (Exception e) {
                elementVisible = false;
                attemptCounter = attemptCounter + 1;
            }

            AndroidNavUtils.screenSizeSwipe(driver, "Up", 75);

        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible && attemptCounter <= iAttempts) {

                try {
                    driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + sText + "\")");
                    elementVisible = true;

                } catch (Exception e) {
                    elementVisible = false;
                    attemptCounter = attemptCounter + 1;
                }

                AndroidNavUtils.screenSizeSwipe(driver, "Down", 75);

            }

        }

        return elementVisible;

    }

    /**
     * -- actionScrollToFindElementById --
     *
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     */
    public static boolean actionScrollToFindElementById(AndroidDriver<MobileElement> driver, String sElementID, int iAttempts) {


        boolean elementVisible = false;
        int attemptCounter = 0;

        while (!elementVisible && attemptCounter <= iAttempts) {

            try {
                driver.findElementById(sElementID);
                elementVisible = true;

            } catch (Exception e) {
                elementVisible = false;
                attemptCounter = attemptCounter + 1;
            }

            AndroidNavUtils.screenSizeSwipe(driver, "Up", 75);

        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible && attemptCounter <= iAttempts) {

                try {
                    driver.findElementById(sElementID);
                    elementVisible = true;

                } catch (Exception e) {
                    elementVisible = false;
                    attemptCounter = attemptCounter + 1;
                }

                AndroidNavUtils.screenSizeSwipe(driver, "Down", 75);

            }

        }

        return elementVisible;

    }

    /**
     * -- actionScrollToFindElementByAccId --
     *
     * @param driver
     * @param sElementAccID
     * @param iAttempts
     * @return
     */
    public static boolean actionScrollToFindElementByAccId(AndroidDriver<MobileElement> driver, String sElementAccID, int iAttempts) {


        boolean elementVisible = false;
        int attemptCounter = 0;

        while (!elementVisible && attemptCounter <= iAttempts) {

            try {
                driver.findElementByAccessibilityId(sElementAccID);
                elementVisible = true;

            } catch (Exception e) {
                elementVisible = false;
                attemptCounter = attemptCounter + 1;
            }

            AndroidNavUtils.screenSizeSwipe(driver, "Up", 75);

        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible && attemptCounter <= iAttempts) {

                try {
                    driver.findElementByAccessibilityId(sElementAccID);
                    elementVisible = true;

                } catch (Exception e) {
                    elementVisible = false;
                    attemptCounter = attemptCounter + 1;
                }

                AndroidNavUtils.screenSizeSwipe(driver, "Down", 75);

            }

        }

        return elementVisible;

    }




    /**
     * -- getElementByTextUsingScreenScroll --
     *
     * @param driver    - AndroidDriver
     * @param sText     - String  - Text To find
     * @param iAttempts - Integer - Number of attempts to scroll
     * @return - Web element object when found , otherwise null
     * @throws InterruptedException
     */
    public static WebElement getElementByTextUsingScreenScroll(AndroidDriver<MobileElement> driver, String sText, int iAttempts) throws InterruptedException {


        boolean elementVisible = false;
        int attemptCounter = 0;

        while (!elementVisible && attemptCounter <= iAttempts) {

            try {
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + sText + "\")");
                elementVisible = true;

            } catch (Exception e) {
                elementVisible = false;
                attemptCounter = attemptCounter + 1;
            }

            AndroidNavUtils.screenSizeSwipe(driver, "Up", 75);

        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible && attemptCounter <= iAttempts) {

                try {
                    driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + sText + "\")");
                    elementVisible = true;

                } catch (Exception e) {
                    elementVisible = false;
                    attemptCounter = attemptCounter + 1;
                }

                AndroidNavUtils.screenSizeSwipe(driver, "Down", 75);

            }

        }

        if (elementVisible) {
            try {
                return driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + sText + "\")");
            } catch (Exception e) {
                EnhancedLogging.debug("Element with Text value " + sText + " not found on current screen");
                return null;
            }
        }
        else {
            return null;
        }

    }


    /**
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     * @throws InterruptedException
     */
    public static WebElement getElementByIdUsingScreenScroll(AndroidDriver<MobileElement> driver, String sElementID, int iAttempts) throws InterruptedException {


        boolean elementVisible = false;
        int attemptCounter = 0;


        while (!elementVisible && attemptCounter <= iAttempts) {

            try {
                driver.findElementById(sElementID);
                elementVisible = true;

            } catch (Exception e) {
                elementVisible = false;
                attemptCounter = attemptCounter + 1;
            }

            AndroidNavUtils.screenSizeSwipe(driver, "Down", 75);

        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible && attemptCounter <= iAttempts) {

                try {
                    driver.findElementById(sElementID);
                    elementVisible = true;

                } catch (Exception e) {
                    elementVisible = false;
                    attemptCounter = attemptCounter + 1;
                }

                AndroidNavUtils.screenSizeSwipe(driver, "Up", 75);

            }

        }

        if (elementVisible) {
            try {
                return driver.findElementById(sElementID);
            } catch (Exception e) {
                EnhancedLogging.debug("Element with ID: " + sElementID + ", not found on current screen");
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     *
     *  -- clickAreaOfElementAndroid --
     *
     * @param driver
     * @param element
     * @return
     * @throws InterruptedException
     */
    public static boolean clickAreaOfElementAndroid(AndroidDriver<MobileElement> driver, WebElement element) throws InterruptedException {

        Point elementloc = element.getLocation();

        int X = elementloc.getX();
        int Y = elementloc.getY();

        TouchAction action = new TouchAction(driver);
        EnhancedLogging.debug("Clicking Element with Text " + element.getText());
        try {

           action.tap(PointOption.point(X, Y)).perform();

        } catch (Exception e) {
            EnhancedLogging.debug("Failed to click area of the element using Text " + element.getText() + "at location :"+  "\n" + X + " - " + Y + "\n" + e +"\n" + e.getMessage());
            return false;
        }

        return true;

    }

    /**
     *
     * -- captureScreenshotAndroid --
     *
     * @param driver
     * @param sFileIdentifier
     * @throws InterruptedException
     */
    public static void captureScreenshotAndroid(AndroidDriver<MobileElement> driver, String sFileIdentifier) throws InterruptedException {


        try {
            String connectedDeviceName = driver.getCapabilities().getCapability("deviceName").toString();//getSessionDetail("deviceName").toString();


            String filename = "ScreenShot" + connectedDeviceName + "-" + sFileIdentifier.replace("@", "-") +
                    "-" + driver.getDeviceTime("hh:mm:ss").replace(":", "") + ".jpg";

            File file = driver.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(file, new File(filename));
            //EnhancedLogging.debug("captured screen shot " + filename + " and added it to project root");
            EnhancedLogging.testlog("captured screen shot " + filename + " and added it to project root");
        } catch (Exception e) {

            EnhancedLogging.debug("Failed to capture screen shot with error :\n" + e.getMessage());

        }
    }

    /**
     *
     *  --getAndroidversionIDs --
     *
     * Method allows us to keep generic code across multiple versions of Android where the the
     * OS level apps return their interactable element with varying ID's
     *
     * @param driver
     * @return Hash Map of Android apps <keyvalued> and <elementPrependValue>
     */
    public static HashMap<String, String> getAndroidversionIDs(AndroidDriver<MobileElement> driver) {

        HashMap<String, String> AndroidIDs = new HashMap<String, String>();
        String sAndroidcapVersion = driver.getCapabilities().getCapability("platformVersion").toString();

        switch (sAndroidcapVersion) {
            case "10":
                AndroidIDs.put("permsdialog", "com.android.permissioncontroller");
                AndroidIDs.put("Browser", "com.android.chrome");
                break;
            default:
                AndroidIDs.put("permsdialog", "com.android.packageinstaller");
                AndroidIDs.put("Browser", "com.sec.android.app.sbrowser");
                break;
        }


        return AndroidIDs;


    }

    /**
     *
     *  -- WaitElementDisplayedIDAndroid --
     *
     * @param driver
     * @param sID
     * @param pollTimeSeconds
     * @return
     * @throws InterruptedException
     */
    public static boolean WaitElementDisplayedIDAndroid(AndroidDriver<MobileElement> driver, String sID, int pollTimeSeconds) throws InterruptedException {
        boolean bError = false;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);
        Integer i = 0;
        while (then.getTime().after(new Date())) {
            try {
                i++;
                bError = driver.findElementById(sID).isDisplayed();//MyRACAppiOSRunner.driver.findElementByAccessibilityId("Could not login with the given email and password.").isDisplayed();
                if (bError) {
                    break;
                }
            } catch (Exception e) {
                EnhancedLogging.debug("waiting for Element with ID: " + sID + "\n Attempt :" + i + "\n" + e.getMessage());
            }
        }
        return bError;
    }

    /**
     * --  getElementByDisplayedIDUsingWaitAndroid --
     *
     * @param driver          - Accepts AndroidDriver
     * @param sID             - ID of expected Element to get
     * @param pollTimeSeconds - Second to wait for element to be displayed
     * @return Returns web element object when displayed , otherwise returns Null
     * @throws InterruptedException
     */
    public static WebElement getElementByIDUsingWaitAndroid(AndroidDriver<MobileElement> driver, String sID, int pollTimeSeconds) throws InterruptedException {

        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);
        WebElement element = null;
        while (then.getTime().after(new Date())) {
            try {
                element = driver.findElementById(sID);
                if (element.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return element;
    }


    /**
     * --  getElementByXPATHUsingWaitAndroid --
     *
     * @param driver          - Accepts AndroidDriver
     * @param XPATH           - XPATH Query value of expected Element to get
     * @param pollTimeSeconds - Second to wait for element to be displayed
     * @return Returns web element object when displayed , otherwise returns Null
     * @throws InterruptedException
     */
    public static WebElement getElementByXPATHUsingWaitAndroid(AndroidDriver<MobileElement> driver, String XPATH, int pollTimeSeconds) throws InterruptedException {

        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);
        WebElement element = null;
        while (then.getTime().after(new Date())) {
            try {
                element = driver.findElementByXPath(XPATH);
                if (element.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return element;
    }


    /**
     * -- WaitElementDisplayedXpathAndroid --
     *
     * @param driver          - Accepts AndroidDriver
     * @param sXpath          - XPATH Query value of expected Element to get
     * @param pollTimeSeconds - Wait time in seconds
     * @return - True when element to be discovered and time limit not reached, Otherwise returns false
     * @throws InterruptedException
     */
    public static boolean WaitElementDisplayedXpathAndroid(AndroidDriver<MobileElement> driver, String sXpath, int pollTimeSeconds) throws InterruptedException {
        boolean bError = false;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);

        while (then.getTime().after(new Date())) {
            try {
                bError = driver.findElementById(sXpath).isDisplayed();//MyRACAppiOSRunner.driver.findElementByAccessibilityId("Could not login with the given email and password.").isDisplayed();
                if (bError) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return bError;
    }


    /**
     * --verifyElementAbsentUsingID--
     *
     * @param driver  Android driverObject
     * @param sID
     * @return boolean: Returns true if an element is found, If not false is thrown
     * @throws InterruptedException
     */

    public static boolean verifyElementAbsentUsingID(AndroidDriver driver, String sID) throws InterruptedException {
        try {
            driver.findElement(By.id(sID));
            //System.out.println("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            //System.out.println("Element absent");
            return true;
        }
    }
}