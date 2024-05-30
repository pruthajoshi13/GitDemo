package POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest_AddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPalce p = new AddPalce();
		p.setAccuracy(50);
		p.setAddress("Summer Walk Africa");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91)848 574 4654");
		p.setWesite("https://rahulshettyacademy.com");
		p.setName("Shravya");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		Response res = given().log().all().queryParam("key", "qaclick123").body(p).when().post("maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract().response();
	
		String responseString = res.asString();
		System.out.println(responseString);
		
		
	}

}
