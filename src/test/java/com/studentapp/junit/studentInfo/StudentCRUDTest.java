package com.studentapp.junit.studentInfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.serenityrestassured.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

@RunWith(SerenityRunner.class)
public class StudentCRUDTest extends TestBase{
	
	static String fName = "Avinash";
	static String lName = "Seelam";
	static String email = TestUtils.getRandomValue()+"avinash123@mindtree.com";
	static String programOpted = "Computer Science";
	
	@Test
	public void createStudent() {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("java");
		courses.add("python");
		courses.add("DBMS");
		
		StudentClass student = new StudentClass();
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setEmail(email);
		student.setProgramme(programOpted);
		student.setCourse(courses);
		
		SerenityRest.given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.post()
		.then()
		.log()
		.all()
		.statusCode(201);
		
	}

}
