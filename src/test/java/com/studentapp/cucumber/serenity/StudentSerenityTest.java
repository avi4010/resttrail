package com.studentapp.cucumber.serenity;

import java.util.ArrayList;
import java.util.HashMap;

import com.serenityrestassured.model.StudentClass;
import com.studentapp.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenityTest {
	
	@Step ("This Step will create student with First Name:{0},Last Name:{1}, email:{2}, Program:{3}, Courses:{4}")
	public ValidatableResponse createStudent (String fName, String lName, String email, String programOpted,ArrayList<String> courses) {
		
		StudentClass student = new StudentClass();
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setEmail(email);
		student.setProgramme(programOpted);
		student.setCourse(courses);
		
		return SerenityRest.rest().given()
//		.contentType(ContentType.JSON)
		.spec(ReuseableSpecifications.getGenericRequest())
		.log()
		.all()
		.when()
		.body(student)
		.post()
		.then();
		
	}
	
	@Step ("This Step will get student Info by First Name:{0}")
	public HashMap<String,Object> getStudentInfoByFirstName (String fName){
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		return SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+fName+p2);
		
	}
	
	@Step ("This Step will UPDATE student with Student ID:{0}, First Name:{1},Last Name:{2}, email:{3}, Program:{4}, Courses:{5}")
	public ValidatableResponse updateStudent (int StudentId, String fName, String lName, String email, String programOpted,ArrayList<String> courses) {
		
		StudentClass student = new StudentClass();
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setEmail(email);
		student.setProgramme(programOpted);
		student.setCourse(courses);
		
		return SerenityRest.given()
//			.contentType(ContentType.JSON)
			.spec(ReuseableSpecifications.getGenericRequest())
			.log()
			.all()
			.when()
			.body(student)
			.put("/"+StudentId)
			.then();
		
	}
	
	@Step("This Test case deletes a Student for studentID: {0}")
	public void deleteStudent (int StudentId) {
		
			
		 SerenityRest.rest().given()
				.when()
				.delete("/"+StudentId);
		
	}
	
	@Step("This Test case gets a Student with studentID: {0}")
	public ValidatableResponse getStudentById (int StudentId) {
		
			
		 return SerenityRest.rest().given()
					.when()
					.get("/"+StudentId)
					.then();
		
	}

}
