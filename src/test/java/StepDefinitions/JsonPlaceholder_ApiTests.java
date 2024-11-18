package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static ApiController.JsonPlaceholder_Controller.GetSinglePostByID;
import static org.hamcrest.Matchers.*;
import static ApiController.JsonPlaceholder_Controller.ListAllPosts;

public class JsonPlaceholder_ApiTests {
    private Response response;

    @Given("Authenticate to Api Service")
    public void AuthenticateToApiService() {
    }

    @When("Get All {string}")
    public void getAll(String EndPoint) {
        // Make the API call and store the response in the class-level variable
        response = ListAllPosts(EndPoint);
        response.then().log().body();  // Log the response body
    }

    @Then("verify the status code is {int}")
    public void verifyTheStatusCodeIs(int Code) {
        response.then().assertThat().statusCode(Code);

    }

    @And("Verify that every post contain {string} and {string} and {string}, {string}")
    public void verifyThatEveryPostContainAndAnd(String Assertion1, String Assertion2, String Assertion3, String Assertion4) {
        // Verify that every post contains the specified fields using JSONPath assertions
        response.then()
                .assertThat()
                // Ensure each post has the required keys
                .body("$", everyItem(hasKey(Assertion1)))  // Ensure every post has the 'userId' key
                .body("$", everyItem(hasKey(Assertion2)))     // Ensure every post has the 'id' key
                .body("$", everyItem(hasKey(Assertion3)))  // Ensure every post has the 'title' key
                .body("$", everyItem(hasKey(Assertion4)))  // Ensure every post has the 'body' key
                // Ensure each field is non-null
                .body(Assertion1, everyItem(notNullValue()))  // Ensure every post has a non-null 'userId'
                .body(Assertion2, everyItem(notNullValue()))  // Ensure every post has a non-null 'id'
                .body(Assertion3, everyItem(notNullValue()))  // Ensure every post has a non-null 'title'
                .body(Assertion4, everyItem(notNullValue()))  // Ensure every post has a non-null 'body'

                // Ensure each field is not an empty string
                .body(Assertion1, everyItem(not(isEmptyString())))  // Ensure 'userId' is not empty
                .body(Assertion2, everyItem(not(isEmptyString())))  // Ensure 'id' is not empty
                .body(Assertion3, everyItem(not(isEmptyString())))  // Ensure 'title' is not empty
                .body(Assertion4, everyItem(not(isEmptyString()))); // Ensure 'body' is not empty
    }

    @And("Verify Content-Type header is {string}")
    public void verifyContentTypeHeaderIs(String expectedContentType) {
        response.then().assertThat().header("Content-Type", expectedContentType);
    }

    @When("Get Single Post by endpoint is {string} and ID is {string}")
    public void getSinglePostByEndpointIsAndIDIs(String Endpoint, String ID) {
        response = GetSinglePostByID (Endpoint,ID);
        response.then().log().body();  // Log the response body

    }

    @And("verify the Response contains  {string} and {string} and {string}, {string}")
    public void verifyTheResponseContainsAndAnd(String Assertion1, String Assertion2, String Assertion3, String Assertion4) {
        response.then()
                .assertThat()
                .body("$", hasKey(Assertion1))
                .body("$", hasKey(Assertion2))
                .body("$", hasKey(Assertion3))
                .body("$", hasKey(Assertion4));
    }

    @And("Verify the Response id is {int}")
    public void verifyTheResponseIdIs(int ExpectedIDInResponse) {
        response.then()
                .assertThat()
                .body("id", equalTo((ExpectedIDInResponse)));  // Check the 'id' of the first post
    }


}