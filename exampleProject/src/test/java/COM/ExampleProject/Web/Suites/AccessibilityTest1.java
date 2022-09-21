package COM.ExampleProject.Web.Suites;

import COM.ExampleProject.Web.ExampleProjectWebRunner;

import com.automation.WebNavUtils;
import com.runner.accessibility.AccessibilityScanner;
import com.runner.accessibility.Result;
import COM.ExampleProject.Common.ExampleProjectNav;
import com.runner.annotations.SuiteInformation;
import com.runner.annotations.TestInformation;
import com.runner.runner.EnhancedAssertion;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SuiteInformation(
        suiteName = "Site responsiveness & Acessibility",
        suiteDescription = "Validate responsiveness of site at verious resolutions and aidit accessibility",
        priority = SuiteInformation.SuitePriority.LOW,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "is displayed as expected in differing resolutions" +
                        "And meets the basic Accessibiltiy guidelines"
        }
)

public class AccessibilityTest1 {

//    @TestInformation(
//
//            testName = "Responsiveness analysis",
//            testDescription = "<TEST OBJECTIVE JIRA/ADAPTAVIST>" +
//                    "As a ... " +
//                    "I want ..." +
//                    "So that ...",
//            /**
//             * expected behaviour is just the BDD test script
//            */
//
//            expectedBehaviour = "Given I am on the Example project\n" +
//                    "When I look at a class for information\n" +
//                    "Then I see well commented javadocs by Connor",
//            priority = TestInformation.TestPriority.HIGH,
//            type = TestInformation.TestType.AUTOMATIC,
//            testRunOrder = 1)
//
//
//    @Test
//    public void RSP_POC_001() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InstantiationException {
//
//        List<String> dimensions = new ArrayList<>();
//        dimensions.add("Audit/Expected/1200x800");
//        dimensions.add("Audit/Expected/768x1024");
//        dimensions.add("Audit/Expected/375x812");
//        dimensions.add("Audit/Expected/320x480");
//        dimensions.add("800x1280");
//        dimensions.add("Audit/Expected/360x640");
//
//        int width;
//        int height;
//        WebNavUtils.validateSiteLoadWithinMaxLimit(ExampleProjectWebRunner.driver, "https://google.co.uk/", 5);
//
//
//        MubalooHomePage home = new MubalooHomePage(ExampleProjectWebRunner.driver);
//        for (int i = 0; i < dimensions.size(); i++) {
//
//            Path snapShotPath = Paths.get("./src/main/resources/Audit/Expected/", dimensions.get(i));
//            Ocular.config()
//                    .snapshotPath(snapShotPath)
//                    .resultPath(Paths.get("./src/main/resources/Audit/Actual/", dimensions.get(i)));
//
//            width = Integer.parseInt(dimensions.get(i).split("x")[0]);
//            height = Integer.parseInt(dimensions.get(i).split("x")[1]);
//
//            ExampleProjectWebRunner.driver.manage().window().setSize(new Dimension(width, height));
//            EnhancedAssertion.softAssertCondition(home.compare().isEqualsImages(), "Google:  Test " + (i + 1) + ": " + dimensions.get(i));
////            EnhancedAssertion.softAssertCondition(
////                    Ocular.snapshot()
////                            .sample()
////                            .using(ExampleProjectWebRunner.driver)
////                            .compare()
////                            .isEqualsImages(),
////                    "Mubaloo Home Page:  Test " + (i + 1) + ": " + dimensions.get(i));
//        }
//
//
//        WebNavUtils.scrollToElementAndClickbyCSS(ExampleProjectWebRunner.driver, "#et-boc > div > div.et_pb_section.et_pb_section_1.et_section_regular.et_pb_section_sticky.et_pb_section_sticky_mobile > div.et_pb_row.et_pb_row_0 > div > div.et_pb_button_module_wrapper.et_pb_button_0_wrapper.et_pb_button_alignment_center.et_pb_module > a");
//        MubalooApproachPage approach = new MubalooApproachPage(ExampleProjectWebRunner.driver);
//
//
//        for (int i = 0; i < dimensions.size(); i++) {
//
//            Path snapShotPath = Paths.get("./src/main/resources/Audit/Expected/", dimensions.get(i));
//            Ocular.config()
//                    .snapshotPath(snapShotPath)
//                    .resultPath(Paths.get("./src/main/resources/Audit/Actual/", dimensions.get(i)));
//
//            width = Integer.parseInt(dimensions.get(i).split("x")[0]);
//            height = Integer.parseInt(dimensions.get(i).split("x")[1]);
//
//            ExampleProjectWebRunner.driver.manage().window().setSize(new Dimension(width, height));
//            EnhancedAssertion.softAssertCondition(approach.compare(WebNavUtils.findElementbyCSS(ExampleProjectWebRunner.driver, "#et_mobile_nav_menu > div > span.mobile_menu_bar.mobile_menu_bar_toggle")).isEqualsImages(), "Mubaloo Approach Page:  Test " + (i + 1) + ": " + dimensions.get(i));
//        }
//        // ExampleProjectWebRunner.accessibilityRunner.execute("APPOACHPAGE");
//
//
//        //ExampleProjectWebRunner.accessibilityRunner.generateHtmlReport();
//    }

    @TestInformation(

            testName = "Site Accessibility Audit - Multi page",
            testDescription = "<TEST OBJECTIVE JIRA/ADAPTAVIST>" +
                    "As a ... " +
                    "I want ..." +
                    "So that ...",
            /**
             * expected behaviour is just the BDD test script
            */

            expectedBehaviour = "Given I am on the Example project\n" +
                    "When I look at a class for information\n" +
                    "Then I see well commented javadocs by Connor",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC
    )


    @Test
    public void RSP_ACC_001() throws Throwable {
        String sRootURL = "https://test1.racingpost.com/news/";
        String boundryURL = "test1.racingpost.com/news";
        ArrayList<String> usableLinks = new ArrayList<>();
        Integer i = 1;

        EnhancedAssertion.hardAssertCondition(
                WebNavUtils.validateSiteLoadWithinMaxLimit(ExampleProjectWebRunner.driver, sRootURL, 25),
                "URL:" + sRootURL + "- Expected load time less than 25s");

        usableLinks = ExampleProjectNav.getWebLinksForAudit(ExampleProjectWebRunner.driver, sRootURL, boundryURL);

        if (ExampleProjectWebRunner.driver.findElementById("truste-consent-button").isDisplayed()) {
            ExampleProjectWebRunner.driver.findElementById("truste-consent-button").click();
        }

        File directory = new File("src/main/resources/reports/audit/images");
        if (directory.exists()) {
            FileUtils.cleanDirectory(directory);
        } else {
            FileUtils.forceMkdir(directory);
        }

        for (String link : usableLinks) {
            //Navigate to the given URl but handle and report any errors
            try {
                ExampleProjectWebRunner.driver.navigate().to(link);
            } catch (Exception e) {
                EnhancedAssertion.addlogInfo(true, link + "\n<font color='black'>" + e + "</font>", false, true);
            }
            WebNavUtils.pageLoadPolling(1000);

            System.out.println("SCANNING Link " + i + "/" + usableLinks.size() + ":" + link);
            AccessibilityScanner scanner = new AccessibilityScanner(ExampleProjectWebRunner.driver);
            Map<String, Object> accessibilityReport = scanner.runAccessibilityAudit();

            List<Result> errors = new ArrayList<Result>();
            List<Result> warnings = new ArrayList<Result>();
//            byte[] screenshot;
//            screenshot = accessibilityReport.get("screenshot")
//            errors = ((List<Result>) accessibilityReport.get("error"));
            warnings = ((List<Result>) accessibilityReport.get("warning"));
            String elementList = "";

            if (errors.isEmpty() && warnings.isEmpty()) {
                EnhancedAssertion.softAssertCondition(true, link + "\nNo Accessibility errors or warnings noted");//e.g. AX_TEXT_01
            } else {
                byte[] reportScreenShot = ((byte[]) accessibilityReport.get("screenshot"));
                ByteArrayInputStream inStreamj = new ByteArrayInputStream(reportScreenShot);
                BufferedImage newImage = ImageIO.read(inStreamj);
                try {

                    ImageIO.write(newImage, "png", new File(directory.getPath() + File.separator + "URL.Ref" + i + ".png"));
                } catch (IOException e) {
                    System.out.println(e);
                }

                for (Result error : errors) {
                    for (String element : error.getElements()) { //violated elements
                        elementList = elementList + "<li style=\"text-align: left\" >" + element + "</li>\n";
                    }
                    EnhancedAssertion.softAssertCondition(false, "URL Ref " + i + ":" + link + "\nERROR: Rule:" + error.getRule() + "\nURL: " + error.getUrl() + "\n ELEMENT \n<font color='black'> <ol type=\"1\">" + elementList + "</ol></font>\n");//e.g. AX_TEXT_01
                    elementList = "";
                }
                for (Result warning : warnings) {

                    for (String element : warning.getElements()) { //violated elements
                        elementList = elementList + "<li style=\"text-align: left\" type=\"I\">" + element + "</li>\n";

                    }
                    EnhancedAssertion.softAssertCondition(false, "URL Ref " + i + ":" + link + "\nWARNING: Rule:" + warning.getRule() + "\nURL: " + warning.getUrl() + "\n ELEMENT \n<font color='black'> <ol type=\"1\">" + elementList + "</ol></font>");//e.g. AX_TEXT_01
                    elementList = "";
                }
                i++;
            }
        }
    }
}

