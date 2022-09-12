package COM.ExampleProject.Android.Suites;
import com.runner.annotations.SuiteInformation;
import com.runner.annotations.TestInformation;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuiteInformation(
        suiteName = "ExampleOnBoarding",
        suiteDescription = "The ability to bypass onboarding screens and log into the application",
        priority = SuiteInformation.SuitePriority.HIGH,
        suiteAcceptanceCriteria = {
                "That onboarding is bypassed and a succesful login occurs"
        }
)

/**
 * @Suiteinformation is always declared BEFORE the class is declared, and AFTER imports.
 * This just contains basic information regarding the test such as, the name of the suite it will bind it's report to
 * A description of the suite's tests
 * Priority of the suite
 * And the acceptance criteria of the suite itself. Examples have been left in above
 */


public class ExampleOnboarding {


    @TestInformation(

            testName = "- Example Onboarding - Validation",
            testDescription = "As a user\n" +
                    "I want to be able to see example code for my project",
            expectedBehaviour = "Given I am on the Example project\n" +
                    "When I look at a class for information\n" +
                    "Then I see well commented javadocs by Connor",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 1
        
    )

    /**
     * @TestInformation is now specific information relating to the tests that will run, Any test that runs will
     * uses the @Test annotation. And any test that runs using the @Test annotation also needs to include an @TestInformation
     * declaration prior to the start of an @Test annotated Test method.
     */

   
    

    @Test
    public void logInWithOnBoarding() {

        try {
            Thread.sleep(5000);

            COM.ExampleProject.Android.ExampleAndroidRunner.wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id(COM.ExampleProject.Android.ExampleAndroidRunner.elementPrepend + ":id/onboarding_navigation_skip"))).click();
        } catch (Exception e) {

            Assert.fail("unable to skip onboarding" + e);
        }

        try {
            COM.ExampleProject.Android.ExampleAndroidRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(COM.ExampleProject.Android.ExampleAndroidRunner.elementPrepend + ":id/login_select_login")));

        } catch (Exception e) {

            Assert.fail("Unable to click on the initial login button" + e);
        }

        /**
         * Here we have a basic test method including 2 Try catches.
         * Try catches are just used so if a specific part of the method fails, we can see where and what failed
         * as appose to a generic failure relating to the method itself
         * This is because we cannot separate each separate method call with the @Test annotation,
         * often times the requirements of a given test are more than 1 action and validation for the driver to perform.
         * Thus creating the need for try catches relevant to a singular test step
         */

    }
}


