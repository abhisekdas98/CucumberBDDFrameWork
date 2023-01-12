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

import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDefination {
    RequestSpecification res;
    ResponseSpecification respspec;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() {
        // Write code here that turns the phrase above into concrete actions
        baseURI="https://rahulshettyacademy.com";
        Place p=new Place();
        Location location=new Location();
        List<String> ll=new LinkedList<>();
        ll.add("shoe park");
        ll.add("shop");
        location.setLat(-21.233);
        location.setLng(23.44);
        p.setAccuracy(54);
        p.setAddress("VIP prestige Appartment VIP Road puri");
        p.setLanguage("Odia");
        p.setLocation(location);
        p.setName("Abhisek Home");
        p.setPhone_number("+(91) 8328823423");
        p.setWebsite("www.abhisekdas98.com");
        p.setTypes(ll);
        RequestSpecification rq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

         res=given().spec(rq).body(p);
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
