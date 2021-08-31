package API_tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class GETandPOSTExample {
	
	@BeforeTest
	public void SetUp() {
		
		baseURI = "https://reqres.in/";
	}
	
	@Test
	public void testGET() {
		
		given().
			get("api/users?page=2").
		then().
			statusCode(200).
			body("data[2].first_name", equalTo("Tobias")).
			body("data.first_name", hasItems("Tobias","Rachel"));
		
	}
	
	@Test 
	public void testPOST() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Anjali");
		request.put("job", "Engineer");
		
		System.out.println(request.toJSONString());
		
		given().
			header("Conten-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("api/users").
		then().
			statusCode(201).
			log().all();
		
		
	}

}
