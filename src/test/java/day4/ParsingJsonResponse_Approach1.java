package day4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ParsingJsonResponse_Approach1 {

	@Test
	public void parseJson_Approach1() {
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type","application/json")
			.log().headers()
			.body("book[3].title", equalTo("The Lord of the Rings"));
	}
	
}
