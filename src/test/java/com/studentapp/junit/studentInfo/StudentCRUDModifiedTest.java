package com.studentapp.junit.studentInfo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenityTest;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentCRUDModifiedTest extends TestBase{
	
	static String fName = "Avinash" + TestUtils.getRandomValue();
	static String lName = "Seelam" + TestUtils.getRandomValue();
	static String email = TestUtils.getRandomValue()+"avinash123@mindtree.com";
	static String programOpted = "Computer Science";
	static int StudentId;
	
	@Steps
	StudentSerenityTest steps;
	
	
	@Title ("This Test case will create a new Student")
	@Test
	public void test001() {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("java");
		courses.add("python");
		courses.add("DBMS");
		
		steps.createStudent(fName, lName, email, programOpted, courses)
		.statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponse());
		
	}
	
	@Title ("This Test case will verify If student is added succesfully searching with First Name")
	@Test
	public void test002() {
		
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(fName);
		
		System.out.println("The value is: " + value);
		
		String firstName  = (String) value.get("firstName");
		StudentId = (Integer) value.get("id");
//		System.out.println("Value displayed is "+firstName);
		
		assertEquals(fName, firstName);
		
//		assertThat(value,hasValue(fName));
		
	}
	
	@Title ("This Test case will update the First Name of the Student")
	@Test
	public void test003() {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("java");
		courses.add("python");
		courses.add("DBMS");
		
		fName += "_Updated";
		
		steps.updateStudent(StudentId, fName, lName, email, programOpted, courses)
			.log()
			.all()
			.statusCode(200)
			.spec(ReuseableSpecifications.getGenericResponse());
			
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(fName);
		
		System.out.println("The value is: " + value);
		String firstName  = (String) value.get("firstName");
		StudentId = (Integer) value.get("id");
//		System.out.println("Value displayed is "+firstName);
		
		assertEquals(fName, firstName);
	}
	
	@Title ("This test case performs DELETE operation on the student app")
	@Test
	public void test004() {
		
		steps.deleteStudent(StudentId);
		
		steps.getStudentById(StudentId)
		.log()
		.all()
		.statusCode(404)
		.spec(ReuseableSpecifications.getGenericResponse());
		
	}

}
