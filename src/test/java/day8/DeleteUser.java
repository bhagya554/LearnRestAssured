package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	void deleteUser(ITestContext context) {
		//int userId = (int) context.getAttribute("user_Id");//this should come from CreateUserclass >createUser method
		int userId = (int) context.getSuite().getAttribute("user_Id");
		String bearerToken="6ab40340708103e96926628a757d95a7ea09689cbeeed8e794da466d7834cedf";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("uid", userId)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{uid}")
		.then()
			.statusCode(204)
			.log().all();
}
}
