package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUpload_2 {

	@Test
	void singleFileUpload() {
		
		File myFile = new File("./src/test/resources/day5/sample.txt");
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
			.auth().oauth2("public_W142itNE64Fd6KKMF41Lvp6tcd1a")
		.when()
			.post("https://api.bytescale.com/v2/accounts/W142itN/uploads/binary")
		.then()
			.statusCode(200)
			//.body("FileName", equalTo("sample.txt"))
			.log().all();
	}
	
	@Test
	void multiFileUpload_1() {
		
		File myFile1 = new File("./src/test/resources/day5/Test 1.txt");
		File myFile2 = new File("./src/test/resources/day5/Test 2.txt");
		
		given()
			.multiPart("files",myFile1)
			.multiPart("files",myFile2)
			.contentType("multipart/form-data")
			.auth().oauth2("public_W142itNE64Fd6KKMF41Lvp6tcd1a")
		.when()
			.post("https://api.bytescale.com/v2/accounts/W142itN/uploads/binary")
		.then()
			.statusCode(200)
			//.body("FileName", equalTo("sample.txt"))
			.log().all();
	}
	
	@Test
	void multiFileUpload_2() {
		
		File myFile1 = new File("./src/test/resources/day5/Test 1.txt");
		File myFile2 = new File("./src/test/resources/day5/Test 2.txt");
		File fileArr[] = {myFile1,myFile2};
		given()
			.multiPart("files",fileArr)
			.contentType("multipart/form-data")
			.auth().oauth2("public_W142itNE64Fd6KKMF41Lvp6tcd1a")
		.when()
			.post("https://api.bytescale.com/v2/accounts/W142itN/uploads/binary")
		.then()
			.statusCode(200)
			//.body("FileName", equalTo("sample.txt"))
			.log().all();
	}
}
