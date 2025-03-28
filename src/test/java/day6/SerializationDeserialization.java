package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




//POJO(Java Object) > JSON - Serialization
//JSON> POJO  - Deserialization
public class SerializationDeserialization {

	private String string;

	//convert java object to json
	@Test
	void serialization_converttoJson() throws JsonProcessingException {
		//create java object using POJO class
		Student_POJO pojoObj = new Student_POJO();
		pojoObj.setId("4");
		pojoObj.setName("Praveena");
		pojoObj.setLocation("Bangalore");
		pojoObj.setPhone("1234567890");
		String courseArr[] = {"GenAI","Excel"};
		pojoObj.setCourses(courseArr);
		
		//convert java object to JSON -- serialization
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData= objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoObj);
	System.out.println(jsonData);
	}
	
	//convert json to java object
	@Test
	void deSerialization_convertFromJSONToPOJO() throws JsonMappingException, JsonProcessingException {
		
		String jsonData="{\r\n"
				+ "  \"id\" : \"4\",\r\n"
				+ "  \"name\" : \"Praveena\",\r\n"
				+ "  \"location\" : \"Bangalore\",\r\n"
				+ "  \"phone\" : \"1234567890\",\r\n"
				+ "  \"courses\" : [ \"GenAI\", \"Excel\" ]\r\n"
				+ "}";
		
		//convert from json string to POJO(Java Object)
		ObjectMapper objMapper = new ObjectMapper();
		Student_POJO stuObj=objMapper.readValue(jsonData, Student_POJO.class);
		System.out.println("Student ID: " +stuObj.getId());
		System.out.println("First Course: " +stuObj.getCourses()[0]);
		System.out.println("Second Course: " +stuObj.getCourses()[1]);
		System.out.println("Student name: " +stuObj.getName());
		System.out.println("Student location: " +stuObj.getLocation());
		System.out.println("Student Phone No: " +stuObj.getPhone());
	}
}
