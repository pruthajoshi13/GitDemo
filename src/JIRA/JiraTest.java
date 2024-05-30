package JIRA;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost/8080";

		SessionFilter session = new SessionFilter();

		String response = given().header("content-Type", "application/json")
				.body("{ \"username\": \"rahulonlinetutor\", \"password\": \"Jira12345\" }").log().all().filter(session)
				.when().post("/rest/auth/1/session").then().extract().response().asString();

		given().pathParam("id", "10101").log().all().header("content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"This is my First comment\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).when().post("/rest/api/2/issue/{issueId/{key}/comment").then().log().all().assertThat()
				.statusCode(201);

	}

}
