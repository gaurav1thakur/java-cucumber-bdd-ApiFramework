package StepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinations extends Utils {
    RequestSpecification res;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();

    static String place_id;

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {
        TestDataBuild testDataBuild = new TestDataBuild();
        res = given().spec(requestSpecification())
                .body(testDataBuild.add_place_payload());
    }

    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(testDataBuild.add_place_payload(name, language, address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) throws FileNotFoundException {
        //Constructor will be called with valueOf resource which is passed as parameter
        ApiResources resourceAPI = ApiResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
                            //.then().spec(responseSpecification()).extract().response();
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
           // .then().spec(responseSpecification()).extract().response();
        }
        System.out.println(" http method get post called ::"+response.asString());
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int int1) {
        assertEquals(response.getStatusCode(), int1);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_Id created to {string} using {string}")
    public void verify_place_id_created_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        System.out.print("place_id :: " + place_id);

        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");

       // System.out.println(response.asString());
        String actualName = getJsonPath(response, "name");
        System.out.println("actualName ::" + actualName);

        assertEquals(actualName, expectedName);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
    }

}
