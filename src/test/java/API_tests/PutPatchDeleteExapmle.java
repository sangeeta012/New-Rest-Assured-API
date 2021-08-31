package API_tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDeleteExapmle {
	
	@BeforeTest
	public void SetUp() {
		
		baseURI = "https://reqres.in/";
	}
	
	@Test
	public void testPUT() {
		
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
			put("api/users/2").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	
	@Test
	public void testPATCH() {
		
		JSONObject request = new JSONObject();
		
		//request.put("name", "Anjali");
		request.put("job", "Actor");
		
		System.out.println(request.toJSONString());
		
		given().
			header("Conten-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("api/users/2").
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
			delete("api/users/2").
		then().
			statusCode(204).
			log().all();
		
		
	}
	

}
