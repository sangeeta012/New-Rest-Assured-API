package API_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TestsExample {

	@BeforeTest
	public void Setup() {

		baseURI = "https://reqres.in/";
	}

	@Test
	public void testGet_1() {

		//First Example
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");

		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void testGET_2() {

		given().
			get("api/users?page=2").
		then().
			statusCode(200).
			body("data[0].id", equalTo(7)).
			log().all();

	}

}
