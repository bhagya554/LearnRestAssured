package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse_Approach3 {

	@Test
	public void testXMLResponse() {
		Response res = given().when().get("https://www.w3schools.com/xml/books.xml");

		// Validation 1 - Validating status code
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Validation 2 - Validating header content type
		Assert.assertEquals(res.header("Content-Type"), "text/xml");
		
		//Validation 3 - Using XmlPath class - Find total no. of books
		XmlPath xmlObj = new XmlPath(res.asString());
		List<String> allBooks = xmlObj.getList("bookstore.book");
		System.out.println("No. of books are: " + allBooks.size());
		Assert.assertEquals(allBooks.size(), 4);
		
		//Validation 4 - Get all book titles and Validate specific book title is present or not
		List<String> allBookTitles = xmlObj.getList("bookstore.book.title");
		boolean status = false;
		for(String bookTitle:allBookTitles) {
			//System.out.println(bookTitle);
			if(bookTitle.equals("XQuery Kick Start")) {
				status = true;
				break;
			}
			
		}
		
		Assert.assertEquals(status, true,"Book Title not present");
		
	}
}
