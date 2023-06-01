package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.testng.Assert;

import POJO.AddPlace;
import POJO.addPlaceResponsePOJO;
import POJO.location;
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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDef_AddPlaceAPI extends Utils{
	 RequestSpecification addPlaceRequest;
	 ResponseSpecification res;
	 Response response;
		Response addPlaceresponse;
		static String place_Id;
	 TestDataBuild data=new TestDataBuild(); 
	 
	 @Given("^Add place payload \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	    public void add_place_payload_something_something_something(String name, String language, String address) throws Throwable {
	     	
       addPlaceRequest = given()
			.spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
	    
    }
	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resource, String method) {
    	res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
    	
    	APIResources resourceAPI=APIResources.valueOf(resource);
		
    
		if(method.equalsIgnoreCase("Post"))
    	{
    		addPlaceresponse=addPlaceRequest
    				.when()
    				     .post(resourceAPI.getResource());
         }else if(method.equalsIgnoreCase("Get"))
         {
        	 addPlaceresponse=addPlaceRequest
        				.when()
        				     .get(resourceAPI.getResource());
         }
	 }

    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() throws Throwable {
    	assertEquals(addPlaceresponse.getStatusCode(),200);
    	
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String keyValue, String expValue) throws Throwable {

    
       Assert.assertEquals(getJsonPath(addPlaceresponse,keyValue),expValue);
    	
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
      
    	place_Id=getJsonPath(addPlaceresponse,"place_id");
		addPlaceRequest= given()
        .spec(requestSpecification())
        .queryParam("place_id",place_Id);
        user_calls_with_http_request(resource,"GET");
        String  actualName=getJsonPath(addPlaceresponse,"name");
        assertEquals(actualName,expName);
                    
    }
    @Given("DeletePlace payload")
	 public void delete_place_payload() throws IOException {
	     
	     given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	 }

}