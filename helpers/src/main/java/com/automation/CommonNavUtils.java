package com.automation;

import com.runner.runner.EnhancedLogging;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    public static boolean checkForAndCreateFolder(String dPath){
      File directory = new File(dPath);

      if(!directory.exists()){
          directory.mkdir();
      }
        return directory.exists();
    }

    public static void prepareFilePath(String path, String reportName) {
        EnhancedLogging.debug("Preparing new report");

    }
}
