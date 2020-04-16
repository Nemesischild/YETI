package COM.ExampleProject.Web.Suites;

import COM.ExampleProject.Web.ExampleProjectWebRunner;
import com.runner.annotations.SuiteInformation;
import com.runner.annotations.TestInformation;
import com.runner.runner.EnhancedAssertion;
import com.runner.runner.EnhancedLogging;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

@SuiteInformation(
        suiteName = "SEO POC",
        suiteDescription = "Able to launch Google and run through a number of Search criteria and ensure the wanted resukt appears where it should be",
        priority = SuiteInformation.SuitePriority.LOW,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "Search criteria entered" +
                        "Wnated resut in the first x number of results"
        }
)



public class SEOExample {
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
        public void QSEO_T001_POC() throws InterruptedException {


                try {

                        ExampleProjectWebRunner.driver.get("https://google.com");

                } catch (Exception E) {
                        Assert.fail("Unable to load google");

                }

                try {

                        ExampleProjectWebRunner.driver.findElementByCssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input").click();
                        WebElement element = ExampleProjectWebRunner.driver.findElement(By.cssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input"));
                        element.sendKeys("Digital Agency Bristol");
                        element.sendKeys(Keys.ENTER);
                        //ExampleProjectWebRunner.driver.findElementByCssSelector("#tsf > div:nth-child(2) > div > div.FPdoLc.VlcLAe > center > input.gNO89b").click();

                } catch (Exception e) {
                        EnhancedAssertion.hardAssertCondition(false, "Unable to enter the required text");
                }

                List<WebElement> googleResults;

                googleResults = ExampleProjectWebRunner.driver.findElements(By.cssSelector(".r"));

                for (int i =0; i<googleResults.size(); i++){

                        if (googleResults.get(i).getText().contains("Mubaloo")){
                                EnhancedLogging.debug("Mubaloo appeared in result list in Position: " + (i+1) );

                        }
                }


        }


}
