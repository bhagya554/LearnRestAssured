package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse_Approach2 {

	@Test
	public void testXMLResponse() {
		Response res = given().when().get(
				"https://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=52a69caf0754d13344257e73e0f65666");

		// Validation 1 - Validating status code
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Validation 2 - Validating header content type
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		//Validation 3 - Validate the country and city name
		String country=res.xmlPath().get("current.city.country").toString();
		Assert.assertEquals(country, "GB");
		String cityName= res.xmlPath().get("current.city.@name").toString();
		Assert.assertEquals(cityName, "London");
		
	}
}
