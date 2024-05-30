package Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Body_Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//validating AddPalce API is working as per expectations
		// given :- all input details
		// when:- submit API- resource,http method
		// then:- validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\ACCOUNT\\Desktop\\Udemy_API\\udemy_api.json"))))
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		// Add place -> Update Place with new Address -> Get Place to validate if new
		// address is present in response
		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing JSON
		String placeID = js.getString("place_id");
		System.out.println(placeID);

	}
}
