package restassuretesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Helper;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="BookData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String addBookPayload="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Playwright\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Nikhil Waichal\"\r\n"
				+ "}\r\n"
				+ "";
		
		String addBookTextResponse = given().header("Content-Type","application/json").body(addBookPayload)
		.when().post("Library/Addbook.php")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().response().asString();
		JsonPath addbookJsonResponse = Helper.textToJSON(addBookTextResponse);
		String ID =addbookJsonResponse.get("ID");
		System.out.println("Book id is"+ID);
		System.out.println("Response is "+addbookJsonResponse);
		
		
	}
	
	@Test(dataProvider="BookData")
	public void deleteBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String bookId = isbn+aisle;
		String deleteBookPayload = "{\r\n"
				+ "\r\n"
				+ "\"ID\" : \""+bookId+"\"\r\n"
				+ "\r\n"
				+ "} \r\n"
				+ "";
		String deleteBookResponseText = given().
				header("Content-Type","application/json")
				.body(deleteBookPayload)
				.when().post("/Library/DeleteBook.php")
				.then()
				.assertThat()
				.statusCode(200)
				.extract().response().asString();
		JsonPath deletebookJsonResponse = Helper.textToJSON(deleteBookResponseText);
		String actualMsg =deletebookJsonResponse.get("msg");
		Assert.assertEquals(actualMsg, "book is successfully deleted");
	}
	@DataProvider(name="BookData")
	public Object[][] getData() {
		return new Object[][] {{"abc","123476"},{"pqr","123476"},{"xyz","93246"}};
	}

}
