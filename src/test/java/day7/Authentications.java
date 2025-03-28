package day7;

import org.testng.annotations.Test;

import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

	@Test(priority = 1)
	void testBasicAuthentication() {
		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

	@Test(priority = 2)
	void testDigestAuthentication() {
		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

	@Test(priority = 3)

	void testPreemptiveAuthentication() {
		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}



	@Test(priority = 4)
	void testBearerTokenAuthentication() {
		//String bearerToken="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";
		given()
			//.headers("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.body("[0].name", equalTo("GitBasics"))
			.log().all();
	}
	
	//@Test(priority = 5)
	void testOAuth1Authentication() {
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken") // Oauth1.0
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 6)
	void testOAuth2Authentication() {
		given()
			//.auth().oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 7)
	void testAPIKeyAuthentication() {
		/* Method 1: Complete url passed as part of GET method parameter
		given()
			.queryParam("appid","b4d69ecbec87333538eefc7fb41f8695")
		.when()
			.get("https://api.openweathermap.org/data/2.5/weather?q=mumbai")
		.then()
			.statusCode(200)
			.log().all();
		*/
		
		//https://api.openweathermap.org/data/2.5/weather?q=mumbai
		//Method 2 - split the url to baseUri/basePath/query param
		given()
			.baseUri("https://api.openweathermap.org")
			.pathParam("myPath","data/2.5/weather")
			.queryParam("q", "mumbai")
			.queryParam("appid", "b4d69ecbec87333538eefc7fb41f8695")
		.when()
			.get("https://api.openweathermap.org/{myPath}")
		.then()
			.statusCode(200)
			.body("name",equalTo("Hyderabad"));
	}

}
