package restassuretesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class StaticJSONFile {

	public static void main(String[] args) {
		String glocationBody="";
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		try {
			glocationBody = new String(Files.readAllBytes(Paths.get("N:\\Learning\\API\\restassuredJava\\RestAssuredTraining\\src\\test\\java\\files\\glocation.json")));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type","application/json")
			.body(glocationBody)
			.when().post("/maps/api/place/add/json")
			.then().log().all()
			.statusCode(200)
			.body("scope",equalTo("APP"))//equalTo method comes from hamcrest package
			.header("server", "Apache/2.4.52 (Ubuntu)");
		}   
	}

