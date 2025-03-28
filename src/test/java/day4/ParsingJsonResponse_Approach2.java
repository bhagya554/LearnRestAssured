package day4;
import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponse_Approach2 {

	@Test
	public void parseJson_Approach2() {
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		//Validation 1 - Validating status code
		Assert.assertEquals(res.getStatusCode(),200);
		
		//Validation 2 - Validating header content type
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		//Validation 3 - Get the book title using jsonpath and validate using TestNG Assert
		String bookTitle = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookTitle,"The Lord of the Rings");	
	
		//Validation 4 - Get all titles of books and validate "The Lord of the Rings" book title is present
		/*In case order of book details is changed , we can't validate the title using jsonpath properly. 
		 * Hence we use JSON object class
		 */
		
		//Converting response to JSONObject type
		JSONObject jo = new JSONObject(res.asString()); 
		JSONArray ja = jo.getJSONArray("book");
		boolean status=false;
		// Entire response is one json object > json object has book JSON Array >book array has multiple JSON Objects
		for(int i=0;i<ja.length();i++) {
			String bookTitle1=ja.getJSONObject(i).get("title").toString();
			//System.out.println("Book Title is : " + bookTitle1);
			if(bookTitle1.equals("The Lord of the Rings")) {
				status = true;
				break;
			}
			
		}
		
		Assert.assertEquals(status, true,"Book Title is not present");
		
		//Validation 5: Find Total Price and Validate Total Price of books
		double totalPrice=0;
		
		for(int i=0;i<ja.length();i++) {
			String priceText = ja.getJSONObject(i).get("price").toString();
			totalPrice = totalPrice+ Double.parseDouble(priceText);
		}
		System.out.println(totalPrice);
		Assert.assertEquals(totalPrice,53.92,"Total price is wrong");
	}
	
}
