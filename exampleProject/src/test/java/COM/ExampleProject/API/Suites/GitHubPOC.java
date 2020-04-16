package COM.ExampleProject.API.Suites;


import COM.ExampleProject.API.APIRunner;
import COM.ExampleProject.Common.GitHubUserClass;
import com.automation.APIUtils;
import com.runner.annotations.SuiteInformation;
import com.runner.annotations.TestInformation;
import com.runner.runner.EnhancedAssertion;
import com.runner.runner.EnhancedLogging;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

@SuiteInformation(

        suiteName = "GIT HUB API POC 1",
        suiteDescription = "Ability to interact with the various elements of the App once Launched",
        priority = SuiteInformation.SuitePriority.HIGH,
        suiteAcceptanceCriteria = {
                "Splash screen, Notifications, Welocme Screen all appear as expected"
        }
)


public class GitHubPOC {
    @TestInformation(
            testName = "RAPI-001 : GitHub User does not exist",
            testDescription = "As a user/systyem\n" +
                    "I want to attempt to access GITHUB API with a user that does not exist\n" +
                    "So that I can validate the response error",
            expectedBehaviour = "Given user does not exist\n" +
                    "When user info is retrieved\n" +
                    "Then 404 error is received",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 1
    )

    @Test
    public void API_GITHUBv3_001() throws ClientProtocolException, IOException {

        // Generate random 8 character String (should create a non-existent user name)
        String name = RandomStringUtils.randomAlphabetic(8);

        EnhancedLogging.testlog("Using End Point: " + APIRunner.APIUri);

        // Build the request (HTTP Get  - URL)
        HttpUriRequest request = new HttpGet(APIRunner.APIUri + "users/" + name);

        // Send the Request to the GitHub endpoint (Retrieved from the Runner
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Report and Asert on the Response Status Code - Expect 404 - User not found
        EnhancedLogging.debug(httpResponse.getStatusLine().toString());
        EnhancedAssertion.hardAssertCondition(
                httpResponse.getStatusLine().toString().contains("404"), "Expected: 404 Response \n Got : "+ httpResponse.getStatusLine().toString());

    }

    @TestInformation(
            testName = "API-003 : Validate mime Type",
            testDescription = "As a ruserr\n" +
                    "I want to attempt to access GITHUb API\n" +
                    "So that I can validate the Mime type in the response",
            expectedBehaviour = "Given request is sent to GITHUB\n" +
                    "When response iscrecieved\n" +
                    "Then the mime type is correct",
            priority = TestInformation.TestPriority.MEDIUM,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 2
    )

    @Test
    public void API_GITHUBv3_002() throws ClientProtocolException, IOException {

        // Set the Expected mime Type
        EnhancedLogging.testlog("Using End Point: " + APIRunner.APIUri);
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet(APIRunner.APIUri + "eugenp");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        EnhancedAssertion.hardAssertCondition(mimeType.equals(jsonMimeType), "Mime Type:\n Expected: " + jsonMimeType + "\n Got: " + mimeType);

    }

    @TestInformation(
            testName = "API-002 : Validate response data",
            testDescription = "As a ruserr\n" +
                    "I want to attempt to access GITHUb API\n" +
                    "So that I can validate the Mime type in the response",
            expectedBehaviour = "Given request is sent to GITHUB\n" +
                    "When uresponse iscrecieved\n" +
                    "Then the mime type is correct",
            priority = TestInformation.TestPriority.CRITICAL,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 2
    )

    @Test
    public void API_GITHUBv3_003() throws ClientProtocolException, IOException, InterruptedException {

        // Hit Git HUB API
        HttpUriRequest request = new HttpGet(APIRunner.APIUri + "users/MubalooQA");
        EnhancedLogging.testlog("Using End Point: " + APIRunner.APIUri);
        // Execute the API request
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Populate GithUbUserClass woth Data from response
        GitHubUserClass resource = APIUtils.retrieveResourceFromResponse(response, GitHubUserClass.class);

        //Query the GitHubUserClass for various bits of information
        EnhancedLogging.testlog("Login Name: " + resource.getLogin());
        EnhancedLogging.testlog("ID: " + resource.getId().toString());
        EnhancedLogging.testlog("Type: " + resource.getType());
        EnhancedLogging.testlog("Node_ID: " + resource.getNodeId());
        EnhancedLogging.testlog("No. of Repos: " + resource.getPublicRepos().toString());
        EnhancedLogging.testlog("No. of Followers: " + resource.getFollowers().toString());

        // Create a simple Assertion to show that the info returned is correct
        EnhancedAssertion.hardAssertCondition(resource.getLogin().equals("MubalooQA"), "Login details in response\nExpected 'MubalooQA' \n Got: " + resource.getLogin());




    }
}



