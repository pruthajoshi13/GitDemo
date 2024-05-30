package GraphQL;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int characterId = 5121;
		int episodeId =1;
		String response = given().log().all()
				.header("Content-Type", "application/json")
				.body("{\"query\":\"query ($characterId : Int!, $episodeId : Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    type\\n    id\\n  }\\n  location(locationId: 7075) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: \\\"hulu\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":"+episodeId+"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "Shishir");
		
		//Mutations
	String newcharacterName = "Baskin Robin";
		String Mutationresponse = given().log().all()
				.header("Content-Type", "application/json")
				.body("{\"query\":\"mutation($locationName :String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location : {name: $locationName, type:\\\"Southzone\\\",dimension\\n  :\\\"2146\\\"})\\n  {\\n    id\\n  }\\n createCharacter(character: {name:$characterName , type:\\\"Macho\\\", status:\\\"dead\\\",species:\\\"fantacy\\\", gender:\\\"Male\\\", image:\\\"png\\\",originId:7074, locationId: 7074}) \\n  {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName,air_date:\\\"2024-06-13\\\",episode:\\\"132\\\"})\\n  {\\n    id\\n  }\\n  deleteLocations(locationIds:[7085,7088])\\n  {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"Newzeland\",\"characterName\":\""+newcharacterName+"\",\"episodeName\":\"Manifest\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
		System.out.println(Mutationresponse);
		//JsonPath js1 = new JsonPath(Mutationresponse);
		//String createLocation= js1.getString("data.createLocation.id");
		//Assert.assertEquals(createLocation, "7097");
	}

}
