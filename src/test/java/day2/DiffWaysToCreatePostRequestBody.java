package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/*
Post request body Using HashMap
Post request body Using org.json
Post request body Using POJO(Plain Old Java Object) class
Post request body Using external json file

{
"id": "1",
"name": "John",
"location": "india",
"phone": "1234567890",
"courses": [
"Java",
"Selenium"
]
}
 */
public class DiffWaysToCreatePostRequestBody {

	//1.Post request body using HashMap
	
	//@Test(priority=1)
	void testPostUsingHashMap() {
		HashMap<Object,Object> data = new HashMap<Object,Object>();
		data.put("id","4");
		data.put("name","Harsha");
		data.put("location","Mumbai");
		data.put("phone","987654321");
		String courseArr[] = {"Python","Appium"};
		data.put("courses",courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Harsha"))
			.body("location", equalTo("Mumbai"))
			.body("phone",equalTo("987654321"))
			.body("courses[0]",equalTo("Python"))
			.body("courses[1]",equalTo("Appium"))
			.header("Content-Type", "application/json")
			.log().all();
		
	}
	
	@Test(priority=2)
	void deleteUser() {
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200);
	}
	
	//2.Post request body creation Using org.json
	
	//@Test(priority=1)
	void testPostUsingJsonLibrary() {
		JSONObject data = new JSONObject();
		data.put("id","4");
		data.put("name","Sangeeta");
		data.put("location","Hyderabad");
		data.put("phone","67747422345");
		String courseArr[] = {"Java","Selenium"};
		data.put("courses",courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Sangeeta"))
			.body("location", equalTo("Hyderabad"))
			.body("phone",equalTo("67747422345"))
			.body("courses[0]",equalTo("Java"))
			.body("courses[1]",equalTo("Selenium"))
			.header("Content-Type", "application/json")
			.log().all();
		
	}
	
	//3.Post request body Using POJO(Plain Old Java Object) class
	//@Test(priority=1)
	void testPostUsingPOJOClass() {
		Student_POJO data = new Student_POJO();
		data.setId("4");
		data.setName("Praveena");
		data.setLocation("Bangalore");
		data.setPhone("1234567890");
		String courseArr[] = {"GenAI","Excel"};
		data.setCourses(courseArr);
				
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Praveena"))
			.body("location", equalTo("Bangalore"))
			.body("phone",equalTo("1234567890"))
			.body("courses[0]",equalTo("GenAI"))
			.body("courses[1]",equalTo("Excel"))
			.header("Content-Type", "application/json")
			.log().all();
		
	}
	
	//4.Post request body Using external json file
		@Test(priority=1)
		void testPostUsingExtenalJSONFile() throws FileNotFoundException {
			/*FileInputStream fis = null;
			try {
				fis = new FileInputStream("./src/test/resources/day2/body.json");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
			File f = new File("./src/test/resources/day2/body.json");
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			JSONObject fis = new JSONObject(jt);
			given()
				.contentType("application/json")
				.body(fis.toString())
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name",equalTo("Andrew"))
				.body("location", equalTo("USA"))
				.body("phone",equalTo("170456123890"))
				.body("courses[0]",equalTo("Jmeter"))
				.body("courses[1]",equalTo("C"))
				.header("Content-Type", "application/json")
				.log().all();
			
		}
}
