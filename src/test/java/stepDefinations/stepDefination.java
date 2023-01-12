package stepDefinations;

import POJO.Location;
import POJO.Place;
import static io.restassured.RestAssured.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDefination extends Utils {
    RequestSpecification res;
    ResponseSpecification respspec;
    Response response;
    TestDataBuild data=new TestDataBuild();
    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
         res=given().spec(requestSpecification()).body(data.AddPlacePayLoad());
         respspec=new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();


    }
    @When("User Calls {string} with Post HTTP Request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
       response=res.when().post("/maps/api/place/add/json").then().
                spec(respspec).extract().response();


    }
    @Then("the API call got Success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {

       Assert.assertEquals(response.statusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String Expectedvalue) {
        String resp=response.asString();
        JsonPath js=new JsonPath(resp);

        Assert.assertEquals(js.getString(keyValue),Expectedvalue);

    }



}
