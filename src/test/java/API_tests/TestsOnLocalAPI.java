package API_tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestsOnLocalAPI {
	
	@BeforeTest
	public void SetUp() {
		
		baseURI = "http://localhost:3000/";
	}
	
	@Test
	public void testGET() {
		
		given().
			get("users?id=3").
		then().
			statusCode(200).
			body("[0].firstName", equalTo("Twinkal")).
			//body("data.first_name", hasItems("Tobias","Rachel"));
			log().all();
		
	}
	
	@Test 
	public void testPOST() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Rakesh");
		request.put("lastName", "Actor");
		request.put("subjectId", 3);
		
		
		//System.out.println(request.toJSONString());
		
		given().
			header("Conten-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("users").
		then().
			statusCode(201).
			log().all();
		
		
	}
	
	@Test 
	public void testPUT() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Rakesh");
		request.put("lastName", "Doctor");
		//request.put("subjectId", 2);
		
		
		//System.out.println(request.toJSONString());
		
		given().
			header("Conten-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("users/4").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	@Test 
	public void testPATCH() {
		
		JSONObject request = new JSONObject();
		
		request.put("subjectId", 9);
		
		
		//System.out.println(request.toJSONString());
		
		given().
			header("Conten-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("users/4").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	@Test
	public void testDELETE() {
		
//		
//		given().//we'll remove given, although we can use given
//			header("Conten-Type","application/json").
//			contentType(ContentType.JSON).
//			accept(ContentType.JSON).
		when().
			delete("users/4").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	

}
