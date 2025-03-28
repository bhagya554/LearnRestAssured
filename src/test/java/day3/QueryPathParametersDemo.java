package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class QueryPathParametersDemo {
//https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testPathAndQueryParam() {
		given()
			.pathParam("pathParamUsers", "users")
			.queryParam("page",2)
			.queryParam("id",5)
		.when()
			.get("https://reqres.in/api/{pathParamUsers}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
