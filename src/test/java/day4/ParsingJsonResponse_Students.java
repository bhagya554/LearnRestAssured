package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
public class ParsingJsonResponse_Students {
	@Test
	void approach1() {
		given()
			.baseUri("http://localhost:3000")
			.basePath("/students")
		.when()
			.get()
		.then()
			.statusCode(200)
			.body("[2].courses[1]",equalTo("RestAPI"));
	}
	
	@Test
	void approach2() {
		Response response=given()
			.baseUri("http://localhost:3000")
			.basePath("/students")
		.when()
			.get();
		String courseName=response.jsonPath().get("[2].courses[0]").toString();
		Assert.assertEquals(courseName, "C#");
		
	}
	
	@Test
	void approach3() {
		Response response=given()
				.baseUri("http://localhost:3000")
				.basePath("/students")
			.when()
				.get();
		
		JSONArray ja = new JSONArray(response.asString());
		for(int i=0;i<ja.length();i++) {
			JSONArray js = ja.getJSONObject(i).getJSONArray("courses");
			System.out.println("Student " + (i+1) + " course details");
			for(int j=0;j<js.length();j++) {
				String courseName=js.getString(j);
				System.out.println(courseName);
			}
		}
		System.out.println();
	}
}
