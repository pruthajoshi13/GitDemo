package Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Body_Files.payloads;

public class AddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//validating AddPalce API is working as per expectations
		// given :- all input details
		// when:- submit API- resource,http method
		// then:- validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payloads.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		// Add place -> Update Place with new Address -> Get Place to validate if new
		// address is present in response
		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing JSON
		String placeID = js.getString("place_id");
		System.out.println(placeID);

		// Update Place
		String newAddress = "Summer Walk Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\":\"" + placeID + "\",\r\n" + "    \"address\":\"" + newAddress
						+ "\",\r\n" + "    \"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		/*
		 * // Get Place String getPlaceResponse = given().log().all().queryParam("key",
		 * "qaclick123").queryParam("place_id", placeID)
		 * .when().get("maps/api/place/get/json").then().assertThat().log().all().
		 * statusCode(200).extract() .response().asString(); JsonPath js1 =
		 * ReUsableMethods.rawToJson(getPlaceResponse); String actualAddress =
		 * js1.getString("address"); Assert.assertEquals(actualAddress,newAddress)
		 */
	}
}

/*
 * given().log().all().queryParam("key",
 * "qaclick123").header("Content-Type","application/json") .body("{\r\n" +
 * "\"location\": {\r\n" + "\"lat\": -38.383494,\r\n" + "\"lng\": 33.427362\r\n"
 * + "},\r\n" + "\"accuracy\": 50,\r\n" +
 * "\"name\": \"Rahul Shetty Academy\",\r\n" +
 * "\"phone_number\": \"(+91) 983 893 3937\",\r\n" +
 * "\"address\": \"29, side layout, cohen 09\",\r\n" + "\"types\": [\r\n" +
 * "\"shoe park\",\r\n" + "\"shop\"\r\n" + "],\r\n" +
 * "\"website\": \"http://rahulshettyacademy.com\",\r\n" +
 * "\"language\": \"French-IN\"\r\n" +
 * "}").when().post("maps/api/place/add/json")
 * .then().log().all().assertThat().statusCode(200).body("scope",
 * equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)");
 */

/*
 * given().log().all().queryParam("key", "qaclick123").header("Content-Type",
 * "application/json") .body("{\r\n" + "\"location\": {\r\n" +
 * "\"lat\": -38.383494,\r\n" + "\"lng\": 33.427362\r\n" + "},\r\n" +
 * "\"accuracy\": 50,\r\n" + "\"name\": \"Rahul Shetty Academy\",\r\n" +
 * "\"phone_number\": \"(+91) 983 893 3937\",\r\n" +
 * "\"address\": \"29, side layout, cohen 09\",\r\n" + "\"types\": [\r\n" +
 * "\"shoe park\",\r\n" + "\"shop\"\r\n" + "],\r\n" +
 * "\"website\": \"http://rahulshettyacademy.com\",\r\n" +
 * "\"language\": \"French-IN\"\r\n" + "}").when()
 * .post("maps/api/place/add/json").then().log().all().assertThat().statusCode(
 * 200).extract().response().asString();
 */