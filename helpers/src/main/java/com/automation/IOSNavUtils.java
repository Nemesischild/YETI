package com.automation;

import com.runner.runner.EnhancedLogging;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class IOSNavUtils {

    /**
     * -- screenSizeSwipeiOS --
     *
     * Get size of screen and swipes, in a direction from the exact centre to a Point of testers choosing
     *
     * @param driver
     * @param swipeType
     * @return
     */
    public static boolean screenSizeSwipeiOS(IOSDriver<MobileElement> driver, String swipeType) {

        Map<String, Object> args = new HashMap<>();
        args.put("direction", swipeType.toLowerCase());

        try {
           driver.executeScript("mobile: scroll", args);
            return true;

        } catch (Exception e) {
            EnhancedLogging.debug("Scroll action failed : \n" + e.getMessage());

        }
        return false;
    }

    /**
     *
     * -- clickAreaOfElement--
     *
     * @param driver
     * @param element
     * @return
     * @throws InterruptedException
     */
    public static boolean clickAreaOfElement(IOSDriver<MobileElement> driver, WebElement element) throws InterruptedException {

        Point elementloc = element.getLocation();

        int X = elementloc.getX();
        int Y = elementloc.getY();

        TouchAction action = new TouchAction(driver);
        try {
            EnhancedLogging.debug("Clicking Element with Text " + element.getText());
            action.tap(PointOption.point(X + 3, Y + 1)).perform();

        } catch (Exception e) {
            EnhancedLogging.debug("Failed to click area of the passed element at location:" + X + " - " + Y + "\n" + e);
            return false;
        }

        return true;

    }


    /**
     *
     * -- captureScreenshotiOS --
     *
     * @param driver
     * @param sFileIdentifier
     * @throws InterruptedException
     */
    public static boolean captureScreenshotiOS(IOSDriver<MobileElement> driver, String sFileIdentifier) throws InterruptedException {


        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            String connectedDeviceName = driver.getCapabilities().getCapability("deviceName").toString();//getSessionDetail("deviceName").toString();
            String filename = "ScreenShot - " + connectedDeviceName + "-" + sFileIdentifier + "-" + driver.getDeviceTime("hh:mm:ss").replace(":", "") + ".jpg";

            File file = driver.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(file, new File(filename));

            EnhancedLogging.debug("captured screen shot " + filename + " and added it to project root");
            EnhancedLogging.testlog("captured screen shot " + filename + " and added it to project root");
            return true;
        } catch (Exception e) {

            EnhancedLogging.debug("\n Failed to capture screen shot with error :\n" + e.getMessage() + "\n\n");
            return false;
        }
    }

    /**
     *
     * -- getElementByAccIdUsingScreenScrollIOS --
     *
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     * @throws InterruptedException
     */
    public static WebElement getElementByAccIdUsingScreenScrollIOS(IOSDriver<MobileElement> driver, String sElementID, int iAttempts) throws InterruptedException {


        boolean elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);
        ;
        int attemptCounter = 1;
        EnhancedLogging.debug("\nLooking for Element " + sElementID);


        while (!elementVisible || attemptCounter <= iAttempts) {

             elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);
            attemptCounter = attemptCounter + 1;


            if (!elementVisible) {
                 IOSNavUtils.screenSizeSwipeiOS(driver, "Down");
            } else {
                break;
            }
        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible || attemptCounter <= iAttempts) {

                elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);
                 attemptCounter = attemptCounter + 1;

                if (!elementVisible) {
                    IOSNavUtils.screenSizeSwipeiOS(driver, "Up");
                } else {
                    break;
                }
            }

        }

        if (elementVisible) {
            try {
                return driver.findElementByAccessibilityId(sElementID);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     *
     *  -- getElementByNameUsingScreenScrollIOS --
     *
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     * @throws InterruptedException
     */
    public static WebElement getElementByNameUsingScreenScrollIOS(IOSDriver<MobileElement> driver, String sElementID, int iAttempts) throws InterruptedException {


        boolean elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);
        ;
        int attemptCounter = 1;
        System.out.println("\nLooking for Element " + sElementID);


        while (!elementVisible || attemptCounter <= iAttempts) {

            elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);
            attemptCounter = attemptCounter + 1;


            if (!elementVisible) {
                IOSNavUtils.screenSizeSwipeiOS(driver, "Down");
            } else {
                break;
            }
        }

        if (!elementVisible) {
            attemptCounter = 0;

            while (!elementVisible || attemptCounter <= iAttempts) {

                 elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);
                 attemptCounter = attemptCounter + 1;

                if (!elementVisible) {
                     IOSNavUtils.screenSizeSwipeiOS(driver, "Up");
                } else {
                    break;
                }
            }

        }

        if (elementVisible) {
            try {
                return driver.findElementByName(sElementID);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     *
     * -- findElementByAccessibilityIdUsingScreenScrollIOS --
     *
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     * @throws InterruptedException
     */
    public static boolean findElementByAccessibilityIdUsingScreenScrollIOS(IOSDriver<MobileElement> driver, String sElementID, int iAttempts) throws InterruptedException {

        EnhancedLogging.debug("\nLooking for Element " + sElementID);

        boolean elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);

        int attemptCounter = 1;


        // Scroll the screen down while checking for the element
        while (!elementVisible && attemptCounter <= iAttempts) {

            elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);
            attemptCounter = attemptCounter + 1;


            if (elementVisible) {
                break;
            } else {
                IOSNavUtils.screenSizeSwipeiOS(driver, "Down");

            }
        }

        // Not found the element on the down scroll / or in current position so scroll back up
        if (!elementVisible) {
            attemptCounter = 1;

            while (!elementVisible && attemptCounter <= iAttempts) {

                elementVisible = WaitElementDispalyedbyAccessibilityIDIOS(driver, sElementID, 1);
                attemptCounter = attemptCounter + 1;

                if (elementVisible) {
                    break;
                } else {
                    IOSNavUtils.screenSizeSwipeiOS(driver, "Up");
                }
            }

        }
        System.out.println("Returning " + sElementID + " Found = " + elementVisible);
        return elementVisible;
    }


    /**
     *
     *  -- findElementByNameUsingScreenScrollIOS --
     *
     * @param driver
     * @param sElementID
     * @param iAttempts
     * @return
     * @throws InterruptedException
     */
    public static boolean findElementByNameUsingScreenScrollIOS(IOSDriver<MobileElement> driver, String sElementID, int iAttempts) throws InterruptedException {

        EnhancedLogging.debug("\nLooking for Element " + sElementID);

        boolean elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);

        int attemptCounter = 1;


        while (!elementVisible && attemptCounter <= iAttempts) {

            elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);
            attemptCounter = attemptCounter + 1;


            if (elementVisible) {
                 break;
            } else {
                IOSNavUtils.screenSizeSwipeiOS(driver, "Down");

            }
        }

        // Not found the element on the down scroll / or in current position so scroll back up
        if (!elementVisible) {
            attemptCounter = 1;

            while (!elementVisible && attemptCounter <= iAttempts) {

                 elementVisible = WaitElementDispalyedbyNameIOS(driver, sElementID, 1);
                attemptCounter = attemptCounter + 1;

                if (elementVisible) {
                    break;
                } else {
                    IOSNavUtils.screenSizeSwipeiOS(driver, "Up");
                }
            }

        }
        System.out.println("Returning " + sElementID + " Found = " + elementVisible);
        return elementVisible;
    }

    /**
     *
     *  -- waitForElementWithTimeoutUsingAccId --
     *
     * @param driver
     * @param sACCID
     * @param timeoutInMillis
     * @return
     * @throws InterruptedException
     */
    public static WebElement waitForElementWithTimeoutUsingAccId(IOSDriver driver, String sACCID, int timeoutInMillis) throws InterruptedException {
        Calendar future = Calendar.getInstance();
        future.setTime(new Date());
        future.add(Calendar.MILLISECOND, timeoutInMillis);
        long futureEpoch = future.getTimeInMillis();
        WebElement element = null;
        while (futureEpoch >= Calendar.getInstance().getTimeInMillis()) {
            try {
                element = driver.findElementByAccessibilityId(sACCID);
                if (element != null) {
                    return element;
                }

            } catch (Exception e) {
                Thread.sleep(2000);
            }
        }

        return null;
    }


    public static WebElement waitForElementWithTimeoutUsingXPATH(IOSDriver driver, String sXPATH, int timeoutInMillis) throws InterruptedException {
        Calendar future = Calendar.getInstance();
        future.setTime(new Date());
        future.add(Calendar.MILLISECOND, timeoutInMillis);
        long futureEpoch = future.getTimeInMillis();
        WebElement element = null;
        while (futureEpoch >= Calendar.getInstance().getTimeInMillis()) {
            try {
                element = driver.findElementByXPath(sXPATH);
                if (element != null) {
                    return element;
                }

            } catch (Exception e) {
                Thread.sleep(2000);
            }
        }

        return null;
    }

    /**
     *
     * -- WaitElementDisplayedXpathIOS --
     *
     * @param driver
     * @param sXpath
     * @param pollTimeSeconds
     * @return
     * @throws InterruptedException
     */

    public static boolean WaitElementDisplayedXpathIOS(IOSDriver<MobileElement> driver, String sXpath, int pollTimeSeconds) throws InterruptedException {
        boolean bError = false;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);

        while (then.getTime().after(new Date())) {
            try {
                bError = driver.findElementById(sXpath).isDisplayed();
                if (bError) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return bError;
    }

    /**
     *
     * -- WaitElementDispalyedbyAccessibilityIDIOS --
     *
     * @param driver
     * @param sAccID
     * @param pollTimeSeconds
     * @return
     * @throws InterruptedException
     */
    public static boolean WaitElementDispalyedbyAccessibilityIDIOS(IOSDriver<MobileElement> driver, String sAccID, int pollTimeSeconds) throws InterruptedException {
        boolean bError = false;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);

        while (then.getTime().after(new Date())) {
            try {
                bError = driver.findElementByAccessibilityId(sAccID).isDisplayed();
                if (bError) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return bError;
    }


    /**
     *
     *  -- WaitElementDispalyedbyNameIOS --
     *
     * @param driver
     * @param sName
     * @param pollTimeSeconds
     * @return
     * @throws InterruptedException
     */
    public static boolean WaitElementDispalyedbyNameIOS(IOSDriver<MobileElement> driver, String sName, int pollTimeSeconds) throws InterruptedException {
        boolean bError = false;
        Calendar then = Calendar.getInstance();
        then.add(Calendar.SECOND, pollTimeSeconds);

        while (then.getTime().after(new Date())) {
            try {
                bError = driver.findElementByAccessibilityId(sName).isDisplayed();//MyRACAppiOSRunner.driver.findElementByAccessibilityId("Could not login with the given email and password.").isDisplayed();
                if (bError) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return bError;
    }
}
