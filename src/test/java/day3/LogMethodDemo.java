package day3;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class LogMethodDemo {

	@Test
	void Looging() {
	given()
	
	.when()
		.get("https://reqres.in/api/users?page=2")
	.then()
		//.log().all();
		//.log().body();
		//.log().headers();
		.log().cookies();
	}
}
