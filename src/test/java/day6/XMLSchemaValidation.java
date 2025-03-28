package day6;

import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;
import static io.restassured.RestAssured.*;

public class XMLSchemaValidation {

	@Test
	public void validateXMLSchema() {
		given()
		.when()
			.get("https://www.w3schools.com/xml/books.xml")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("day6/w3xmlSchema.xsd"));
	}
}
