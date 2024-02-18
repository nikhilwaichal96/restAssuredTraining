package restassuretesting;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class JsonOperation {
	@Test //testng annotation
	public void sumOfCourses() {
		String jsonResponse ="{\r\n"
				+ "    \"dashboard\": {\r\n"
				+ "        \"purchaseAmount\": 910,\r\n"
				+ "        \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "    },\r\n"
				+ "    \"courses\": [\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Selenium Python\",\r\n"
				+ "            \"price\": 50,\r\n"
				+ "            \"copies\": 6\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Cypress\",\r\n"
				+ "            \"price\": 40,\r\n"
				+ "            \"copies\": 4\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"RPA\",\r\n"
				+ "            \"price\": 45,\r\n"
				+ "            \"copies\": 10\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";

		      JsonPath js = new JsonPath(jsonResponse); //convert string jsonResponse to Json object
		      int count= js.getInt("courses.size()");   //Get total count of courses as courses is array we used size() method
		      System.out.println("Total number of courses are "+count);
		      int purchaseAmount = js.getInt("dashboard.purchaseAmount"); //access subobjects using parent.subobject
		      System.out.println("Total purchase amount is "+purchaseAmount);
		      int totalPrice = 0;
		      //Print total price
		      //Get all courses and print price and no of copies
		      for(int i=0;i<count;i++) {
		    	  int price = js.getInt("courses["+String.valueOf(i)+"].price"); 
		    	  String courseTitle = js.get("courses["+String.valueOf(i)+"].title");
		    	  int copies = js.get("courses["+String.valueOf(i)+"].copies");
		    	  System.out.println(courseTitle+ " has price "+ price + "No of copies "+copies);
		    	  totalPrice =totalPrice+(price*copies);
		      }
		      System.out.println("Total price is "+totalPrice);
		      
		      //Fetch value of course named RPA
		      for(int i=0;i<count;i++) {
		    	  String courseTitle = js.get("courses["+String.valueOf(i)+"].title");
		    	  int copies = js.get("courses["+String.valueOf(i)+"].copies");
		    	  if(courseTitle.equalsIgnoreCase("RPA")) {
		    		  System.out.println(courseTitle +" has sold total "+copies);
		    		  break;
		    	  }
		      }
		      //Validate that total price of courses is equals to purchaseAmount field in json
		      Assert.assertEquals(totalPrice, purchaseAmount);
	}

}
