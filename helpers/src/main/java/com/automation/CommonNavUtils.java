package com.automation;

import com.runner.runner.EnhancedLogging;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class CommonNavUtils {


    /**
     *
     *  -- clickAreaOfElementByLocation --
     *
     * @param driver
     * @param element
     * @return
     * @throws InterruptedException
     */
    public static boolean clickAreaOfElementByLocation(AppiumDriver driver, WebElement element) throws InterruptedException{

        Point elementloc =  element.getLocation();

        int X = elementloc.getX();
        int Y  = elementloc.getY();

        TouchAction action= new TouchAction(driver);
        try {
            EnhancedLogging.debug("Clicking Element with Text " + element.getText());
            action.tap(PointOption.point(X, Y)).perform();

        }catch(Exception e){
            EnhancedLogging.debug("Failed to click area of the passed element at location  for vehicle with Reg:"+  element.getText()+  "\n" + X + " - " + Y + "\n" + e);
            return false;
        }

        return true;

    }
}
