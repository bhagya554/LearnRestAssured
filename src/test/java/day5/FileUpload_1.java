package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUpload_1 {

	@Test
	void singleFileUpload() {
		
		File myFile = new File("./src/test/resources/day5/sample.txt");
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
		.when()
			.post("https://v2.convertapi.com/upload")
		.then()
			.statusCode(200)
			.body("FileName", equalTo("sample.txt"))
			.log().all();
	}
}
