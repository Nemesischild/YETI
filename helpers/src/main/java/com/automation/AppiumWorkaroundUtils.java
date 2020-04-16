package com.automation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.Optional;

public class AppiumWorkaroundUtils {

    /**]
     *
     * -- returnAppFocus --
     *
     * @param driver
     * @throws InterruptedException
     */
    public static void returnAppFocus(AndroidDriver<MobileElement> driver) throws InterruptedException {

        try {
            driver.runAppInBackground(Duration.ofMillis(10));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);

    }

    /**
     *
     * -- refocusApp --
     *
     * @param driver
     * @param PackageName
     * @throws InterruptedException
     */
   public static void refocusApp(AndroidDriver<MobileElement> driver, String PackageName) throws InterruptedException{

      driver.activateApp(PackageName);

      Thread.sleep(1000);
   }


    /**
     *
     * getLCaseString
     *
     * @param driver
     * @param ElementID
     * @return
     * @throws InterruptedException
     */
    public static String getLCaseString(AndroidDriver<MobileElement> driver, String ElementID) throws InterruptedException{

      return driver.findElementById(ElementID).getText().toLowerCase();

    }

    /**
     *
     * getUCaseString
     *
     * @param driver
     * @param ElementID
     * @return
     * @throws InterruptedException
     */
    public static String getUCaseString(AndroidDriver<MobileElement> driver, String ElementID) throws InterruptedException{

        return driver.findElementById(ElementID).getText().toUpperCase();

    }

}
