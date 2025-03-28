package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UpdateUser {
	@Test
	void updateUser(ITestContext context) {
		//int userId=(int) context.getAttribute("user_Id");
		int userId = (int) context.getSuite().getAttribute("user_Id");
		Faker fake = new Faker();
		
		JSONObject jo = new JSONObject();
		jo.put("name", fake.name().fullName());
		jo.put("email", fake.internet().emailAddress());
		jo.put("gender","female");
		jo.put("status","active");
		String bearerToken="6ab40340708103e96926628a757d95a7ea09689cbeeed8e794da466d7834cedf";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("uid", userId)
			.body(jo.toString())
		.when()
			.put("https://gorest.co.in/public/v2/users/{uid}")
		.then()
			.statusCode(200)
			.log().all();
		
		//int res_id=res.jsonPath().getInt("id");
		
		
		
		
	}
}
