package SpecBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;



public class SpecBuilder_SerializeTest_AddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPalce p = new AddPalce();
		p.setAccuracy(50);
		p.setAddress("Summer Walk Africa");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91)848 574 4654");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Shravya");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick").setContentType(ContentType.JSON).build();
		
		ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req).body(p);
				
		Response response =res.when().post("maps/api/place/add/json").then()
				.spec(resspec).extract().response();
	
		String responseString = response.asString();
		System.out.println(responseString);
		
		
	}

}
