package restassuretesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Helper;
import files.payload;
public class Basic2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//POST
		String response =given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then()
		.assertThat()
		.statusCode(200)
		.body("scope",equalTo("APP"))//equalTo method comes from hamcrest package
		.header("server", "Apache/2.4.52 (Ubuntu)")  
        .extract().response().asString();
		System.out.println(response);
		JsonPath js = Helper.textToJSON(response);  
		String placeID=js.getString("place_id");
		System.out.println("Place id is "+placeID);
		
		
		//PUT
		String newAddress = "Madhukunj Bunglow";
		 given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all()
		.statusCode(200)
		.body("msg",equalTo("Address successfully updated"));
		 
		 //GET
		 String getPlaceResponse = given().log().all()
		 .queryParam("key", "qaclick123")
		 .queryParam("place_id", placeID)
		 .when().get("/maps/api/place/get/json")
		 .then().assertThat().log().all()
		 .statusCode(200).extract().response().asString();
		 System.out.println("Response of getAPI "+getPlaceResponse);
		 JsonPath getPlaceResponseJson = Helper.textToJSON(getPlaceResponse);
		 String actualAddress = getPlaceResponseJson.getString("address");
		 System.out.println("Actual address is "+actualAddress);
		 
		 Assert.assertEquals(newAddress, actualAddress);
	}
}
