package day5;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class ParsingXMLResponse_Approach1 {

	@Test
	public void testXMLResponse() {
		given()
		.when()
			.get("https://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=52a69caf0754d13344257e73e0f65666")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("current.city.country", equalTo("GB"))
			.body("current.city.@name", equalTo("London"))
			.body("current.city.coord.@lon",equalTo("-0.1257"));
	}
}
