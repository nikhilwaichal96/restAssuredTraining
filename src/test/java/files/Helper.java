package files;

import io.restassured.path.json.JsonPath;

public class Helper {

	public static JsonPath textToJSON(String responseText) {
		return new JsonPath(responseText);  //for parsing json convert string json to json object
	}
}
