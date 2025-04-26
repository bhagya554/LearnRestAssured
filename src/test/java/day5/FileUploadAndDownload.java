package day5;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class FileUploadAndDownload {
	@Test(priority=1)
	public void singleFileUpload() {
		File myFile = new File("./src/test/resources/day5/Test 1.txt");
		given()
			.multiPart("file", myFile)
			.contentType("multipart/formdata")
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("Test 1.txt"));
	}
	
	@Test
	public void multipleFileUpload() {
		File myFile1 = new File("./src/test/resources/day5/Test 1.txt");
		File myFile2 = new File("./src/test/resources/day5/Test 2.txt");
		File[] fileArr= {myFile1,myFile2};
		
		given()
				/*
				 * .multiPart("files", myFile1) .multiPart("files",myFile2)
				 */
			.multiPart("files",fileArr)
			.contentType("multipart/formdata")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test 1.txt"))
			.body("[1].fileName", equalTo("Test 2.txt"))
			.log().all();
	}
	
	@Test(priority=2)
	public void fileDownload() {
		given()
		.when()
			.get("http://localhost:8080/downloadFile/Test 1.txt")
		.then()
			.statusCode(200)
			.log().body();
	}

}
