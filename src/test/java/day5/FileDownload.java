package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileDownload {

	@Test
	void downloadFile() {
		given()
		
		.when()
			.get("https://upcdn.io/W142itN/raw/uploads/2024/11/30/4kAJFwd4FY-file")
		.then()
			.statusCode(200)
			.log().body();
	}
}
