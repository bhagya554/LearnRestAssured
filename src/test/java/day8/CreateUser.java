package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.IAttributes;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
public class CreateUser {
	/*
	 * {
   
    "name": "Ghanshyam r",
    "email": "ghanshyam_pa@thompson-okon.test",
    "gender": "female",
    "status": "inactive"
	}
	 */

	@Test
	void createUser(ITestContext context) {
		
		Faker fake = new Faker();
		
		JSONObject jo = new JSONObject();
		jo.put("name", fake.name().fullName());
		jo.put("email", fake.internet().emailAddress());
		jo.put("gender","male");
		jo.put("status","inactive");
		String bearerToken="6ab40340708103e96926628a757d95a7ea09689cbeeed8e794da466d7834cedf";
		
		Response res=given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(jo.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users");
		
		int userId=res.jsonPath().getInt("id");
		
		System.out.println("User Id is:" + userId);
		//context.setAttribute("user_Id", userId);//Test Level
		context.getSuite().setAttribute("user_Id", userId);//Suite level
		
	}
}
