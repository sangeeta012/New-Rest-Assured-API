package API_tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JSONSchemaValidator {

	@BeforeTest
	public void SetUp() {
		
		baseURI = "https://reqres.in/";
	}
	
	@Test
	public void testGET() {
		
		given().
			get("api/users?page=2").
		then().
		    assertThat().body(matchesJsonSchemaInClasspath("jsonschema.txt")).
			statusCode(200);
//			body("data[2].first_name", equalTo("Tobias")).
//			body("data.first_name", hasItems("Tobias","Rachel"));
		
	}
	
}
