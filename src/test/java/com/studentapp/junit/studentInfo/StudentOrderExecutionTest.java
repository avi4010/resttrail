package com.studentapp.junit.studentInfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.serenityrestassured.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentOrderExecutionTest extends TestBase{
	
	static String fName = "Avinash" + TestUtils.getRandomValue();
	static String lName = "Seelam" + TestUtils.getRandomValue();
	static String email = TestUtils.getRandomValue()+"avinash123@mindtree.com";
	static String programOpted = "Computer Science";
	static int StudentId;
	
	@Title ("This TC adds a new student to the database")
	@Test
	public void test001() {
		
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
		
		SerenityRest.rest().given()
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
	
	@Title ("This TC prints all the students present in the DB and compares with the newly added")
	@Test
	public void test002() {
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		HashMap<String,Object> value = SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+fName+p2);
		
		System.out.println("The value is: " + value);
		
		String firstName  = (String) value.get("firstName");
		StudentId = (Integer) value.get("id");
//		System.out.println("Value displayed is "+firstName);
		
		assertEquals(fName, firstName);
		
//		assertThat(value,hasValue(fName));
		
	}
	
	@Title("This testcase performes PUT operation on a user")
	@Test
	public void test003() {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("java");
		courses.add("python");
		courses.add("DBMS");
		
		fName += "_Updated";
		
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
			.put("/"+StudentId)
			.then()
			.log()
			.all()
			.statusCode(200);
			
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		HashMap<String,Object> value = SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+fName+p2);
		
		System.out.println("The value is: " + value);
		
		String firstName  = (String) value.get("firstName");
		StudentId = (Integer) value.get("id");
//		System.out.println("Value displayed is "+firstName);
		
		assertEquals(fName, firstName);
	}

	@Title ("This test case performs DELETE operation on the student app")
	@Test
	public void test004() {
		
		SerenityRest.rest().given()
		.when()
		.delete("/"+StudentId);
		
		SerenityRest.rest().given()
		.when()
		.get("/"+StudentId)
		.then()
		.log()
		.all()
		.statusCode(404);
		
	}

}
