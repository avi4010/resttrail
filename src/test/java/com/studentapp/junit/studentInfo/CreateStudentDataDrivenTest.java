package com.studentapp.junit.studentInfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenityTest;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="4x")
@UseTestDataFrom("testdata\\StudentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {
	
	private String fName;
	private String lName;
	private String email;
	private String programOpted;
	private String course;
	
	@Steps
	StudentSerenityTest step;
	
	@Title("Data Driven Test to add multiple students to student app")
	@Test
	public void createMultipleStudents() {
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add(course);
		
		step.createStudent(fName, lName, email, programOpted, courses)
		.statusCode(201);
		
	}

}
