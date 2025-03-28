package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test
	public void verifyHeaderInfo()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()//optional
			.header("Content-Encoding","gzip")
			.and()//optional
			.header("Server","gws")
			.log().all();
	}
	
	@Test
	public void getHeaderInfo() {
		Response res = given()
				.when()
				.get("https://www.google.com/");
		System.out.println("Header value of Content-Encoding: " + res.getHeader("Content-Encoding"));
		System.out.println("Get All Headers Info");
		Headers allHeaders = res.getHeaders();
		for(Header head:allHeaders) {
			System.out.println("Header is : " + head.getName() + " value: " + head.getValue());
		}	
	}
}
