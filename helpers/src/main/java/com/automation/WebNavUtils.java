package com.automation;

import com.runner.runner.EnhancedAssertion;
import com.runner.runner.EnhancedLogging;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WebNavUtils {


    /**
     * --setWebDriver--
     *
     * @param options Options are added as part of driver initiation - (chromeOptions)
     * @param locale
     */

    public static void setWebDriverLanguage(ChromeOptions options, String locale) {
        options.addArguments("--lang=" + locale.substring(0, 1));
//        options.addArguments("--Title=MG TEST");
    }


    /**
     * --launchSiteURL--
     *
     * @param driver driverObject
     * @param inURL  This is the URL you want to swap to.
     */

    public static void launchSiteURL(RemoteWebDriver driver, String inURL) {
        String currentWindow = driver.getWindowHandle();
        driver.get(inURL);
        driver.switchTo().window(currentWindow);
    }

    /**
     * --scrollToElementAndClickbyCSS--
     * This method is for use when you believe the element in question will be visable to the DOM but not to the current context of the sceeen.
     *
     * @param driver driverObject
     * @param cssID
     * @return boolean: True is the success of the method, False is failure.
     */

    public static boolean scrollToElementAndClickbyCSS(RemoteWebDriver driver, String cssID) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(cssID));

        try {
            action.moveToElement(element).click().perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click on selected element..." + e.getMessage());
            return false;
        }
    }


    /**
     * --clickElementByOffsetUsingCSS--
     * This method uses a CSSID to return it's area by X and Y co-ordinates and then clicks on the
     *
     * @param driver - The driver object
     * @param cssID
     * @return booleans: true is the success of the method, false is a failure.
     * @throws InterruptedException
     */

    public static boolean clickElementByOffsetUsingCSS(RemoteWebDriver driver, String cssID) throws InterruptedException {
        try {
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.cssSelector(cssID));
            int x = element.getLocation().x;
            int y = element.getLocation().y;
            act.moveByOffset(x + 3, y + 3).build().perform();
            Thread.sleep(500);
            act.click();

        } catch (Exception e) {
            System.out.println("moveTo/actClick unsuccessful: " + e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * --clickElementByOffset--
     *
     * @param driver
     * @param element
     * @return booleans: True is the method success and false is the failure.
     * @throws InterruptedException
     */

    public static boolean clickElementByOffset(RemoteWebDriver driver, WebElement element) throws InterruptedException {
        try {
            Actions act = new Actions(driver);

            int x = element.getLocation().x;
            int y = element.getLocation().y;
            act.moveByOffset(x + 3, y + 3).build().perform();
            Thread.sleep(500);
            act.click();

        } catch (Exception e) {
            System.out.println("moveTo/actClick unsuccessful: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * --getElementWithIndex--
     *
     * @param driver  driverObject
     * @param element elementObject
     * @param index   the value is returned
     * @return webElementObject - The returning of the object is the success criteria
     */

    public static WebElement getElementWithIndex(RemoteWebDriver driver, WebElement element, int index) {
        //WebElement element = driver.findElement(By.id(Value));
        List<WebElement> elements = element.findElements(By.tagName("a"));
        return elements.get(index);
    }

    /**
     * --enterTexttoElementXPATH--
     *
     * @param driver driverObject
     * @param xPath
     * @param data
     * @return boolean: return true is the method success and false is the failure.
     */

    public static boolean enterTexttoElementXPATH(RemoteWebDriver driver, String xPath, String data) {
        By elementSearch = By.xpath(xPath);
        WebElement element = driver.findElement(elementSearch);
        try {
            element.sendKeys(data);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * --enterTextByCSS--
     *
     * @param driver driverObject
     * @param cssID
     * @param data   The value you want to input
     * @return boolean: true is the pass condition and false is failure
     */

    public static boolean enterTextByCSS(RemoteWebDriver driver, String cssID, String data) {
        By elementSearch = By.cssSelector(cssID);
        WebElement element = driver.findElement(elementSearch);
        try {
            element.sendKeys(data);
            return true;

        } catch (Exception e) {
            EnhancedLogging.debug("Unable to enter specified text..." + e.getMessage());
            return false;
        }
    }

    /**
     * --clickOnElementXPATH--
     *
     * @param driver  driverObject
     * @param xpathID
     * @return boolean: true is the pass condition and false is failure
     */

    public static boolean clickOnElementXPATH(RemoteWebDriver driver, String xpathID) {
        WebElement element = driver.findElement(By.xpath(xpathID));
        try {
            element.click();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click the specified element..." + e.getMessage());
            return false;
        }
    }

    /**
     * @param driver driverObject
     * @param cssID
     * @return boolean: true is the pass condition, false is failure.
     */

    public static boolean clickOnElementByCSS(RemoteWebDriver driver, String cssID) {
        WebElement element = driver.findElement(By.cssSelector(cssID));
        try {
            element.click();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click on the specified element..." + e.getMessage());
            return false;
        }

    }

    /**
     * --checkForElementXPATH--
     *
     * @param driver  driverObject
     * @param xpathid
     * @return boolean: true is the pass condition, false is the failure.
     */


    public static boolean checkForElementXPATH(RemoteWebDriver driver, String xpathid) {
        try {
            WebElement checkForElement = driver.findElement(By.xpath(xpathid));
            return checkForElement.isDisplayed();
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to validate the requested element..." + e.getMessage());
        }
        return false;
    }


    /**
     * --getBgImagefromDivCSS--
     *
     * @param driver driverObject
     * @param CSS
     * @return String: If it is returned the method will pass.
     */

    public static String getBgImagefromDivCSS(RemoteWebDriver driver, String CSS) {

        WebElement MyImg = driver.findElement(By.cssSelector(CSS));
        String WelImg = MyImg.getCssValue("background-image");
        return WelImg;
        //Assert.assertTrue(WelImg.contains("imageurl"));
    }

    /**
     * --checkForElementbyCSS--
     *
     * @param driver driverObject
     * @param cssID
     * @return boolean: true is the pass condition, false is a failure.
     */

    public static boolean checkForElementbyCSS(RemoteWebDriver driver, String cssID) {
        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector(cssID));
            return element.isDisplayed();
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to find requested element..." + e.getMessage());
            return false;

        }

    }

    /**
     * --getTextfromElementXPATH--
     *
     * @param driver driverObject
     * @param xpath
     * @return String: If a string is returned, the method will pass.
     */

    public static String getTextfromElementXPATH(RemoteWebDriver driver, String xpath) {
        WebElement MyElement = driver.findElement(By.xpath(xpath));
        return MyElement.getText();
    }

    /**
     * --getTextfromElementCSS--
     *
     * @param driver driverObject
     * @param CSS
     * @return String: If a string is returned, the method will pass.
     */

    public static String getTextfromElementCSS(RemoteWebDriver driver, String CSS) {
        WebElement MyElement = driver.findElement(By.cssSelector(CSS));
        return MyElement.getText();
    }

    /**
     * --inputTextToElementByCSS--
     *
     * @param driver driverObject
     * @param cssID
     * @param data   Text that you want to be inputted
     * @return booleans: True is the pass condition, False is the failure condition
     */


    public static boolean inputTextToElementByCSS(RemoteWebDriver driver, String cssID, String data) {
        try {
            driver.findElement(By.cssSelector(cssID)).sendKeys(data);
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to input requested text");
            return false;
        }
    }

    /**
     * --pressEnterXPATH--
     *
     * @param driver driverObject
     * @param xpath
     * @return boolean: True is the pass condition, false is the failure condition
     */

    public static boolean pressEnterXPATH(RemoteWebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to press enter key..." + e.getMessage());
            return false;
        }
    }


    /**
     * --pressEnterCSS--
     *
     * @param driver driverObject
     * @param cssID
     * @return boolean: True is the pass condition, false is the failure condition
     */

    public static boolean pressEnterCSS(RemoteWebDriver driver, String cssID) {
        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector(cssID));
            element.sendKeys(Keys.ENTER);
            return true;

        } catch (Exception e) {
            EnhancedLogging.debug("Unable to press enter key..." + e.getMessage());
            return false;

        }
    }


    /**
     * --findElementTextbyXPATH--
     *
     * @param driver driverObject
     * @param text
     * @return Webelement: If an element is returned with a value it will pass. If the element is null
     */

    public static WebElement findElementTextbyXPATH(RemoteWebDriver driver, String text) {
        String query = String.format("//*[contains(text(), '%s')]", text);
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(query));

        } catch (Exception e) {
            EnhancedLogging.debug("Element Not found with supplied Id : " + query);
        }
        return element;
    }

    /**
     * @param driver driverObject
     * @param ID
     * @return webElement: If the element is returned and not null, the method will pass.
     */

    public static WebElement findElementbyID(RemoteWebDriver driver, String ID) {
//        String query = String.format("//*[contains(text(), '%s')]", text);
        WebElement element = null;
        try {
            element = driver.findElement(By.id(ID));

        } catch (Exception e) {
            EnhancedLogging.debug("Element Not found with supplied Id : " + ID + "\n" + e.getMessage());
        }
        return element;
    }

    /**
     * --findElementbyCLASSNAME--
     *
     * @param driver    driverObject
     * @param ClassName
     * @return Webelement: If the element is returned and is not null, the method will pass.
     */

    public static WebElement findElementbyCLASSNAME(RemoteWebDriver driver, String ClassName) {
        WebElement element = null;
        try {
            element = driver.findElement(By.className(ClassName));
        } catch (Exception e) {
            System.out.println("Element Not found with supplied Classname value : " + ClassName);
        }
        return element;
    }

    /**
     * --findElementbyCSS--
     *
     * @param driver driverObject
     * @param Css
     * @return Webelement: If the element is returned and is not null, the method will pass.
     */

    public static WebElement findElementbyCSS(RemoteWebDriver driver, String Css) {
        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector(Css));
        } catch (Exception e) {
            EnhancedLogging.debug("Element Not found with supplied CSS value : " + Css + "\n" + e.getMessage());
        }
        return element;

    }

    /**
     * --hoverOverElementXPATH--
     *
     * @param driver driverObject
     * @param xpath
     * @return boolean: true is the pass condition. False is the failure condition
     * @throws InterruptedException It is thrown here as a thread sleep is used to let the driver populate the actions it needs to chain together
     */

    public static boolean hoverOverElementXPATH(RemoteWebDriver driver, String xpath) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
            Thread.sleep(2000);
            WebElement clickElement = waitForElementWithTimeoutXPATH(driver, xpath, 3000);
            action.moveToElement(clickElement).click().build().perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to move to and click the requested element..." + e.getMessage());
            return false;
        }
    }

    /**
     * @param driver          driverObject
     * @param xPath1
     * @param timeoutInMillis The max timeout before failure
     * @return Webelement: If the element is returned and is not null, the method will pass.
     * @throws InterruptedException ties in with the use of a timeout.
     */

    public static WebElement waitForElementWithTimeoutXPATH(RemoteWebDriver driver, String xPath1, int timeoutInMillis) throws InterruptedException {
        Calendar future = Calendar.getInstance();
        future.setTime(new Date());
        future.add(Calendar.MILLISECOND, timeoutInMillis);
        long futureEpoch = future.getTimeInMillis();
        while (futureEpoch >= Calendar.getInstance().getTimeInMillis()) {
            try {
                WebElement element = driver.findElement(By.xpath(xPath1));

                if (element != null) {
                    return element;
                }

            } catch (Exception e) {
                Thread.sleep(500);
            }
        }
        return null;
    }

    /**
     * @param driver          driverObject
     * @param sID
     * @param timeoutInMillis The max timeout before failure
     * @return Webelement: If the element is returned and is not null, the method will pass.
     * @throws InterruptedException ties in with the use of a timeout.
     */

    public static WebElement waitForElementWithTimeoutID(RemoteWebDriver driver, String sID, int timeoutInMillis) throws InterruptedException {
        Calendar future = Calendar.getInstance();
        future.setTime(new Date());
        future.add(Calendar.MILLISECOND, timeoutInMillis);
        long futureEpoch = future.getTimeInMillis();
        while (futureEpoch >= Calendar.getInstance().getTimeInMillis()) {
            try {
                WebElement element = driver.findElement(By.id(sID));

                if (element != null) {
                    return element;
                }

            } catch (Exception e) {
                Thread.sleep(500);
            }
        }
        return null;
    }


    /**
     * --waitForElementWithTimeoutCSS--
     *
     * @param driver          driverObject
     * @param cssName
     * @param timeoutInMillis
     * @return webElement: If the element is returned and is not null, the method will pass.
     * @throws InterruptedException
     */
    public static WebElement waitForElementWithTimeoutCSS(RemoteWebDriver driver, String cssName, int timeoutInMillis) throws InterruptedException { //TODO: Change the name to get element with timeout
        Calendar future = Calendar.getInstance();
        future.setTime(new Date());
        future.add(Calendar.MILLISECOND, timeoutInMillis);
        long futureEpoch = future.getTimeInMillis();
        while (futureEpoch >= Calendar.getInstance().getTimeInMillis()) {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssName));
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
     * --hoverOverElementCSS--
     *
     * @param driver driverObject
     * @param CSS
     * @return boolean: True is the pass condition. False is the failure condition.
     * @throws InterruptedException
     */

    public static boolean hoverOverElementCSS(RemoteWebDriver driver, String CSS) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(CSS));
        Actions action = new Actions(driver);
        Thread.sleep(2000);
        WebElement clickElement = waitForElementWithTimeoutCSS(driver, CSS, 3000);
        try {
            action.moveToElement(element).perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to hover over element" + e.getMessage());
            return false;
        }
    }

    /**
     * --hoverOverElementAndClickByCss--
     *
     * @param driver driverObject
     * @param cssID
     * @return boolean: True is the pass condition. False is the failure condition.
     * @throws InterruptedException
     */

    public static boolean hoverOverElementAndClickByCss(RemoteWebDriver driver, String cssID) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(cssID));
        Actions action = new Actions(driver);
        Thread.sleep(2000);
        WebElement clickElement = waitForElementWithTimeoutCSS(driver, cssID, 3000);
        try {
            action.moveToElement(element).click().perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to hover and click element" + e.getMessage());
            return false;
        }
    }

    /**
     * --hoverOverElementAndClickByPartialText--
     *
     * @param driver      driverObject
     * @param partialText
     * @return boolean: True is the pass condition. False is the failure condition
     * @throws InterruptedException
     */

    public static boolean hoverOverElementAndClickByPartialText(RemoteWebDriver driver, String partialText) throws InterruptedException {
        WebElement element = driver.findElement(By.partialLinkText(partialText));
        Actions action = new Actions(driver);
        Thread.sleep(2000);
        try {
            action.moveToElement(element).click().perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to hover and click the selected element" + e.getMessage());
            return false;
        }
    }

    /**
     * --validateCurrentURL--
     *
     * @param driver driverObject
     * @param sURL
     * @return boolean: True is the pass condition. False is the failure condition
     */

    public static boolean validateCurrentURL(RemoteWebDriver driver, String sURL) {

        boolean b;
        String stringCurrent = driver.getCurrentUrl();

        URL nURL = null;
        URL cURL = null;

        try {
            nURL = new URL(sURL);
        } catch (MalformedURLException exURL) {
            System.out.println(exURL);
            return false;
        }


        try {
            cURL = new URL(stringCurrent);
        } catch (MalformedURLException exURL) {
            System.out.println(exURL);
            return false;
        }

        return nURL.equals(cURL);
    }


    /**
     * --checkURLonPage--
     *
     * @param driver driverObject
     * @param expURL
     * @return True is the pass condition. Failure is the failure condition.
     */

    public static boolean checkURLonPage(RemoteWebDriver driver, String expURL) {

        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

        //System.out.println(expURL);

        for (int i = 0; i <= links.size() - 1; i = i + 1) {
            //System.out.println( links.get(i).getText() + " at positon " + i );

            if (links.get(i).getAttribute("href") != null) {
                //System.out.println(links.get(i).getText() + " " + links.get(i).getAttribute("href") + " at positon " + i );

                if (links.get(i).getAttribute("href").equals(expURL)) {
                    EnhancedLogging.debug(" Found URL : " + links.get(i).getAttribute("href") + " at positon " + i);
                    return true;
                }
            }
        }
        EnhancedLogging.debug(" URL : " + expURL + " not found");

        return false;
    }

    /**
     * --findAndClickLink--
     *
     * @param driver driverObject
     * @param expURL
     */

    public static void findAndClickLink(RemoteWebDriver driver, String expURL) {

        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println(expURL);

        for (int i = 0; i <= links.size() - 1; i = i + 1) {
            //System.out.println( links.get(i).getText() + " at positon " + i );

            if (links.get(i).getAttribute("href") != null) {
                //System.out.println(links.get(i).getText() + " " + links.get(i).getAttribute("href") + " at positon " + i );

                if (links.get(i).getAttribute("href").equals(expURL)) {
                    //System.out.println(" Found URL : " + links.get(i).getAttribute("href") + " at positon " + i);
                    try {
                        clickElementByOffset(driver, links.get(i));
                        return;
                    } catch (Exception e) {
                        EnhancedAssertion.softAssertCondition(false, " Click on URL " + links.get(i).getAttribute("href") + "failed : " + e);
                    }
                }
            }

        }
    }

    /**
     * --clickOnElementWithDelayXPATH--
     *
     * @param driver        driverObject
     * @param xpath
     * @param delayInMillis
     * @return boolean: True is the pass condition. False is the failure condition.
     * @throws InterruptedException
     */

    public static boolean clickOnElementWithDelayXPATH(RemoteWebDriver driver, String xpath, int delayInMillis) throws InterruptedException {
        Actions act = new Actions(driver);
        WebElement element = waitForElementWithTimeoutXPATH(driver, xpath, delayInMillis);
        act.moveToElement(element).perform();
        Thread.sleep(delayInMillis);
        try {
            element.click();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to click the specified element..." + e.getMessage());
            return false;
        }

    }

    /**
     * --HoverOverElementWithTimeoutPollingXPATH--
     *
     * @param driver driverObject
     * @param xPath
     * @return boolean: True is the pass condition. False is the failure condition.
     * @throws InterruptedException
     */

    public static boolean HoverOverElementWithTimeoutPollingXPATH(RemoteWebDriver driver, String xPath) throws InterruptedException {
        WebElement element = waitForElementWithTimeoutXPATH(driver, xPath, 5000);
        Actions act = new Actions(driver);
        try {
            act.moveToElement(element).perform();
            return true;
        } catch (Exception e) {
            EnhancedLogging.debug("Unable to hover over the specified element..." + e.getMessage());
            return false;
        }
    }

    /**
     * --checkPopUpAppears--
     * This method checks for a pop-up to appear, as it user the .switchTo it is important to remember if the popup doesn't break into a new window. This one won't work.
     * Here we simply pass it a driver. The context of the driver will handle the rest of requirements for the method.
     *
     * @param driver
     * @return boolean: Returns true on detection of alert dialogue. Defaults to false.
     */

    public static boolean checkPopUpAppears(RemoteWebDriver driver) {
        Boolean b = false;
        try {
            driver.switchTo().alert();
            // If it reaches here, it found a popup
            b = true;

        } catch (Exception e) {
            Assert.fail("No Pop Up detected");
        }

        return b;

    }


    //TODO - Validate this method works.

    /**
     * --allowChromeMicAccess--
     * Make sure this method is called on driver initiation as once it is established as an object the chromeOptions cannot be changed.
     *
     * @param driver  Webdriver
     * @param Enable  String (Accepts: Enable, Disable, ForceAsk - Not case sensitive, defaults to allow mic)
     * @param siteURL String (Accepts: Any valid site url including Http://)
     * @return driverObject
     */

    public static WebDriver allowChromeMicAccess(RemoteWebDriver driver, String Enable, String siteURL) {

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();

        switch (Enable.toLowerCase()) {
            case "allow":
                prefs.put("profile.content_settings.exceptions.media_stream_mic.'" + siteURL + ":443,'.setting", "1");
                break;
            case "block":
                prefs.put("profile.content_settings.exceptions.media_stream_mic.'" + siteURL + ":443,'.setting", "2");
                break;
            case "forceask":
                prefs.put("profile.content_settings.exceptions.media_stream_mic.'" + siteURL + ":443,'.setting", "2");
            default:
                prefs.put("profile.content_settings.exceptions.media_stream_mic.'" + siteURL + ":443,'.setting", "1");
                break;
        }

        options.setExperimentalOption("prefs", prefs);

        try {
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            EnhancedLogging.debug("Failed to set chromeDriver Options" + e.getMessage());
        }


        return driver;


    }

    /**
     * --verifyElementAbsentUsingCSS--
     *
     * @param driver  driverObject
     * @param locator cssLocator
     * @return boolean: Returns true if an element is found, i=If not false is thrown
     * @throws InterruptedException
     */

    public static boolean verifyElementAbsentUsingCSS(RemoteWebDriver driver, String locator) throws InterruptedException {
        try {
            driver.findElement(By.cssSelector(locator));
            //System.out.println("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            //System.out.println("Element absent");
            return true;
        }
    }

    // Todo: refactor this method to use the correct Identifier

    /**
     * --verifyElementAbsent--
     * This method validates that an element is not present on screen.
     *
     * @param driver  driverObject
     * @param locator
     * @return boolean: True is the pass condition. False is the failure condition
     * @throws InterruptedException
     */


    public static boolean verifyElementAbsent(RemoteWebDriver driver, String locator) throws InterruptedException {
        try {
            driver.findElement(By.cssSelector(locator));
            EnhancedLogging.debug("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            EnhancedLogging.debug("Element absent");
            return true;
        }
    }

    /**
     * --allowChromeMicAccessV2--
     */

    public static void allowChromeMicAccessV2() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        RemoteWebDriver driver = new ChromeDriver(options);
    }

    /**
     * --hoverOverElementAndClickByName--
     *
     * @param driver driverObject
     * @param name
     * @throws InterruptedException
     */

    public static void hoverOverElementAndClickByName(RemoteWebDriver driver, String name) throws InterruptedException {
        WebElement element = driver.findElement(By.name(name));
        Actions action = new Actions(driver);
        Thread.sleep(2000);
        action.moveToElement(element).click().perform();
    }

    /**
     * --getPageConsoleErrors--
     *
     * @param driver driverObject
     */

    public static void getPageConsoleErrors(RemoteWebDriver driver) {

        // Grab any browser errors from the DEV Console and report them
        LogEntries rootentries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : rootentries) {
            EnhancedAssertion.softAssertCondition(false, "Discovered console Error: \n" + entry.getMessage());
            EnhancedLogging.debug("\n Discovered console Error: " + entry.getMessage() + "\n ************* ~~~~~~~~~~~~~~ ************** \n");
        }
    }

    /**
     * --validateSiteLoadWithinMaxLimit--
     * Validate the full load of a URL against a known maximum allowed time limit in Seconds
     *
     * @param driver           WebDriver: Accepts - Current runner WebDriver object
     * @param sURL             String: Accepts - Standard http: / https: URL
     * @param lMaxLimitSeconds long - Accepts any number of seconds greater than 0
     * @return boolean : true when timer less than @lMaxLimitSeconds
     */
    public static boolean validateSiteLoadWithinMaxLimit(RemoteWebDriver driver, String sURL, long lMaxLimitSeconds) {

        long totalTime = 0;

        //// get the load time of a site
        totalTime = getTimerOnSiteLoad(driver, sURL);

        //write timer result to log file
        EnhancedLogging.testlog("Loading: <a href " + sURL + "/a> " + sURL + " : " + totalTime + " seconds");
        EnhancedLogging.debug("Loading: <a href " + sURL + "/a> " + sURL + " : " + totalTime + " seconds");

        // return minor error when unrealistic limite sent

        if (lMaxLimitSeconds <= 0) {
            EnhancedLogging.testlog("Unable to validate URL (\"<a href" + sURL + "/a>" + sURL + ") is able to load within the maximum limit (" + lMaxLimitSeconds + " seconds) as it cannot be 0 or less\n Actual load took :" + totalTime + " seconds");
            return true;
        }

        // Run check that load of site is not above the required maximum load time
        if (totalTime > lMaxLimitSeconds) {
            EnhancedLogging.testlog("Unable to load URL (\"<a href" + sURL + "/a>  Mubaloo Staging Website <a>\") failed to load within the maximum limit (" + lMaxLimitSeconds + " seconds) took :" + totalTime + " seconds");
            return false;
        }

        return true;

    }

    /**
     * --getTimerOnSiteLoad--
     * Calls a URl via the WebDriver and records the time it takes from the call to the completion of the DOM load
     *
     * @param driver  - WebDriver - Accepts Current WebDriver Object
     * @param siteURL - String : Accepts - Standard http: / https: USRL String
     * @return long : calculated seconds of load ((end time - Startime )/ 1000 )
     */

    public static long getTimerOnSiteLoad(RemoteWebDriver driver, String siteURL) {

        long startTime = 0;
        long endTime = 0;
        long totalTime = 0;

        try {
            //Start a Timer
            startTime = System.currentTimeMillis();
            // Kick off the browser - which waits for DOM load to be complete
            driver.get(siteURL);
            // End the timer
            endTime = System.currentTimeMillis();
            //calculate in seconds the full lod time
        } catch (Exception E) {
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            EnhancedLogging.testlog("Unable to load URL (\"<a href" + siteURL + "/a>  Mubaloo Staging Website <a>\" -  failed TO load after: " + totalTime + " seconds");
        }
        //Calculate & return load time in seconds
        return (endTime - startTime) / 1000;
    }

    /**
     * --cycleThroughAllLinksOnCurrentPage--
     * Method designed to take in any URL and walk through all identified href links on the page and validate that they are clickable and load
     * another page
     *
     * @param driver   - Webdriver Expected
     * @param sRootURL -  valid http / https URL (e.g. https://bbc.co.uk
     * @throws Throwable - Catch any Exception
     */
    public static void cycleThroughAllLinksOnCurrentPage(RemoteWebDriver driver, String sRootURL) throws Throwable {
        List<WebElement> elements;
        List<String> clickedLinks = new ArrayList<>();

        String sURL = "";
        boolean ignoreLink = false;

        // Find all a href links on the current page into a useable list
        elements = driver.findElements(By.tagName("a"));
        int sizeOfAllLinks = elements.size();


        //      Loop through the grabbed list
        for (int i = 0; i < sizeOfAllLinks; i++) {
            // record what URL is being clicked
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
            if (ignoreLink) {

                // try to click the current link
                try {
                    //ignore mailto Links
                    if (sURL.contains("http")) {
                        EnhancedLogging.debug("Attempting to Click: " + sURL);
                        //Click the link
                        findAndClickLink(driver, sURL);
                        //elements.get(i).click();
                        // Add link URL to clicked list
                        clickedLinks.add(sURL);
                    } else {
                        System.out.println("IGNORING: " + sURL + " non http link");
                    }
                } catch (Exception e) {
                    //ignore hidden element exceptions
                    if (!e.getMessage().contains("element not visible")) {
                        System.out.println("Did not click Element:  " + sURL + "\n Recieved :" + e.getMessage() + "\n");
                    } else {
                        // report the error
                        System.out.println(sURL + "is not a visible element that can be clicked");
                    }
                }
                // Check window Tab titles
                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                // Report "Page not found" Error  discovered
                if (driver.switchTo().window(tabs.get(0)).getTitle().equals("Page Not Found")) {
                    System.out.println("\n ERROR: " + sURL + "returned 'Page Not Found'");
                }

                // Navigate back to the previous page
                driver.navigate().to(sRootURL);

                closeAllButRootBrowserTabs(driver);
            }
            Thread.sleep(1000);
            // Clear the list
            elements.clear();
            // grab all the links again (so the index count remains the same)
            elements = driver.findElements(By.tagName("a"));
            //System.out.println(sizeOfAllLinks);

        }
    }


    /**
     * --closeAllButRootBrowserTabs--
     * Method to look at the active browser window and close all tabs but the first one (tab(0))
     *
     * @param driver - Takes We Dbrivert
     * @throws Throwable - Throws any supported Exception: Try catches need to catch on Throwable rather than Exception
     */

    /**
     * --closeAllButRootBrowserTabs--
     *
     * @param driver driverObject
     * @throws Throwable
     */

    public static void closeAllButRootBrowserTabs(RemoteWebDriver driver) throws Throwable {

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //System.out.println("Detect tab count of: " + tabs.size());
        if (tabs.size() >= 2) {
            for (int n = tabs.size() - 1; n > 0; n--) {
                driver.switchTo().window(tabs.get(n));
                Thread.sleep(500);
                //perform whatever actions you want in new tab then close it
                driver.close();
            }
            //Switch back to your original tab
            driver.switchTo().window(tabs.get(0));
        }
    }

    public static void pageLoadPolling (int ms)  throws InterruptedException {

        Thread.sleep(ms);

        // This method is for when your tests make you wanna:
        //                                     (╯°□°）╯︵ ┻━┻
        //Let's be honest no one likes calling a Thread.Sleep in visible code, so just do it here via this method ;)
    }
}


