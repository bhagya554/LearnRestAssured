package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void generateFakeDataTest() {
		Faker faker = new Faker();
		String fullName=faker.name().fullName();
		String firstName=faker.name().firstName();
		String lastName=faker.name().lastName();
		String userName=faker.name().username();
		String password=faker.internet().password(4, 10);
		String mobileNum=faker.phoneNumber().cellPhone();
		String emailAddress = faker.internet().safeEmailAddress();
		faker.business().creditCardNumber();
		faker.color().name();
		faker.animal().name();
		System.out.println("Full Name: "+fullName);
		System.out.println("User Name: "+userName);
		System.out.println("Password: "+password);
		System.out.println("Mobile number: "+mobileNum);
		System.out.println("First Name: "+firstName);
		System.out.println("Last Name: "+lastName);
		System.out.println("Email: "+emailAddress);
	}
}
