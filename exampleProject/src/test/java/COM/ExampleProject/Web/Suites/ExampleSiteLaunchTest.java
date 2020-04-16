package COM.ExampleProject.Web.Suites;
import COM.ExampleProject.Web.ExampleProjectWebRunner;
import com.runner.annotations.SuiteInformation;
import com.runner.annotations.TestInformation;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


@SuiteInformation(
        suiteName = "Launch site",
        suiteDescription = "Able to launch and check the successful launch of a website",
        priority = SuiteInformation.SuitePriority.LOW,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "Correct site is loaded" +
                        "And validated"
        }
)


public class ExampleSiteLaunchTest {


    @TestInformation(

            testName = "Test ID-(Jira US Reference EG. QTMS-1234) + Test name",
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
            type = TestInformation.TestType.AUTOMATIC)


    @Test
    public void QTMS_T1234() throws InterruptedException {

        try {

            ExampleProjectWebRunner.driver.get("https://google.com");

        } catch (Exception E) {
            Assert.fail("Unable to load google");

        }
        try {

            ExampleProjectWebRunner.driver.findElementByCssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input").click();
            WebElement element = ExampleProjectWebRunner.driver.findElement(By.cssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input"));
            element.sendKeys("Connor is the best HAHAHAHAHAHA - Kidding");
            ExampleProjectWebRunner.driver.findElementByCssSelector("#tsf > div:nth-child(2) > div > div.FPdoLc.VlcLAe > center > input.gNO89b").click();

        } catch (Exception e) {
            Assert.fail("Unable to enter the required text");
        }

        try {
            ExampleProjectWebRunner.driver.findElementByPartialLinkText("'Haha, Just Kidding ... Unless?").click();

        } catch (Exception e) {
            Assert.fail("Unable to find the requested page");
        }

        try {
            ExampleProjectWebRunner.driver.findElementByCssSelector("#mega-menu-item-485868 > a > img");

        } catch (Exception e) {
            Assert.fail("Unable to find the requested element");
        }
    }
}










