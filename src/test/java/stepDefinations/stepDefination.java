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
import resources.APIResources;
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
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res=given().spec(requestSpecification()).body(data.AddPlacePayLoad(name,language,address));
        respspec=new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

    }
    @When("User Calls {string} with {string} HTTP Request")
    public void userCallsWithHTTPRequest(String resource, String method) {
        APIResources api=APIResources.valueOf(resource);

        if(method.equalsIgnoreCase("POST"))
        {
            response=res.when().post(api.getResource()).then().
                    spec(respspec).extract().response();
        }
        if(method.equalsIgnoreCase("GET"))
        {
            response=res.when().get(api.getResource()).then().
                    spec(respspec).extract().response();
        }

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
