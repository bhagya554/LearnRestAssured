package day1;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HTTPRequests {

	int id;
	//Get List of Users
	@Test(priority=1)
	void getListOfUsers() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();//displays entire response in console window.
		}
	//@Test
	void createUser_ValidateStatusCode() {
		HashMap<String,Object> hm = new HashMap<String, Object>();
		hm.put("name", "Bhagya");
		hm.put("job","Learner");
		
		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	//Post Request and Get the user id of new user
	@Test(priority = 2)
	void createUser_GetUserId() {
		HashMap<String,Object> hm = new HashMap<String, Object>();
		hm.put("name", "Bhagya");
		hm.put("job","Learner");
		
		Response response=given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("https://reqres.in/api/users");
		/*.then()
			.statusCode(201)
			.log().all();
		*/
		id=response.jsonPath().getInt("id");
		System.out.println(id);
		
		/*
		 * Need created user id to use it as part of put and Delete request
		 * How to capture user id from response:
		 * Response = given()
						.contentType("application/json")
						.body(hm)
					.when()
						.post("https://reqres.in/api/users")
			id=response.jsonPath().getInt("id");
		 */
	}
	
	//Put Request
	@Test(priority=3,dependsOnMethods = {"createUser_GetUserId"})
	void updateUser() {
		HashMap<String,Object> hm = new HashMap<String, Object>();
		hm.put("name", "Sahitya");
		hm.put("job","Trainer");
		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//Delete User
	@Test(priority=4)
	void deleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();//displays entire response in console window.
		}
	
}
