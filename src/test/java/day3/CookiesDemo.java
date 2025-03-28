package day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
public class CookiesDemo {

	@Test(priority=1)
	public void testCookies() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC")
			.log().all();
	}
	
	@Test
	public void getCookies() {
		Response res=given()
		.when()
			.get("https://www.google.com/");
		
		//get single cookie value
		System.out.println("Cookie value of AEC");
		String aecCookie = res.getCookie("AEC");
		System.out.println(aecCookie);
		
		//get all cookie values
		Map<String, String> allCookies = res.getCookies();
		//System.out.println("All Cookies: " + allCookies);
		
		//System.out.println("All cookie keys: " + allCookies.keySet());
		//System.out.println("All cookie values: " +allCookies.values());
		
		for(String key: allCookies.keySet()) {
			System.out.println("Cookie key is: " + key + " Corresponding cookie value : " + res.getCookie(key));
		}
	}
}
