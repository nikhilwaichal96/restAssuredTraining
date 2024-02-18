package restassuretesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Kamdhenu\",\r\n"
				+ "  \"phone_number\": \"(+91) 123411038\",\r\n"
				+ "  \"address\": \"Pune\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"House\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"Marathi\"\r\n"
				+ "}").when().post("/maps/api/place/add/json")
		.then().log().all()
		.statusCode(200)
		.body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)");  //equalTo method comes from hamcrest package

	}

}
