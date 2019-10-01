package com.studentapp.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import com.studentapp.cucumber.serenity.StudentSerenityTest;
import com.studentapp.utils.TestUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class StudentStep {
	
	private static String email;
	
	@Steps
	StudentSerenityTest step;
	
	@When("^user sends a GET request to the list endpoint, they must get a valid status code 200$")
	public void verify_studentStatus_endpoint_200() {
		
//		RestAssured.given()
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
		
	}
	
	@When ("^I create a new student with the following Information firstName(.*) lastname(.*) email(.*) programme(.*) course(.*)$")
	public void createNewStu(String firstName, String lastname, String _email,String programme,String course) {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add(course);
		
		email = TestUtils.getRandomValue() + _email;
		
		step.createStudent(firstName, lastname, email, programme, courses)
		.statusCode(201);
		
	}
	
	@Then ("^I verify that the student with which email is created$")
	public void getStudentByEmail() {
		
		HashMap<String,Object> value  = step.getStudentInfoByEmail(email);
		System.out.println("The value is: " + value);
		
		String ActualEmail  = (String) value.get("email");
		
		assertEquals(email, ActualEmail);
		
	}
}
