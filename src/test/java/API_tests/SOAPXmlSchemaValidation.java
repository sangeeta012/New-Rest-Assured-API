package API_tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SOAPXmlSchemaValidation {
	
	@BeforeTest
	public void SetUp() {
		
		baseURI = "http://www.dneonline.com/";
	}
	
	@Test 
	public void testPOST() throws IOException {
		
		File file1 = new File("C:\\Users\\Sangeeta\\eclipse-workspace\\NewRestAssuredAPI\\target\\classes\\SoapPostRequest.txt");
		
		if(file1.exists())
			System.out.println(">> File Exist");
		
		FileInputStream fileInputStream = new FileInputStream(file1);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("calculator.asmx").
		then().
			statusCode(200).
			body("//*:AddResult.text()", equalTo("21")).and().
			assertThat().body(matchesXsdInClasspath("SoapScheam.txt")).
			log().all();
		
		
	}


}
