package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse_Approach2_BooksURL {

	@Test
	public void testXMLResponse() {
		Response res = given().when().get(
				"https://www.w3schools.com/xml/books.xml");

		// Validation 1 - Validating status code
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Validation 2 - Validating header content type
		Assert.assertEquals(res.header("Content-Type"), "text/xml");
		
		//Validation 3 - Validate the title of 3rd book
		String country=res.xmlPath().get("bookstore.book[2].title").toString();
		Assert.assertEquals(country, "XQuery Kick Start");
		
		
	}
}
