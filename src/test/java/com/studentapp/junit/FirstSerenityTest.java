package com.studentapp.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://localhost:8080/student";
		
	}
	
	@Test
	public void getAllStudentsPassing() {
		
		RestAssured.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
		
	}
	
	@Test
	public void getStudentfailing() {
		
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(300);
		
	}
	
	@Pending
	@Test
	public void getStudentPending() {
						
	}
	
	@Ignore
	@Test
	public void IgnoreTestCase() {
				
	}
	
	@Test
	public void compilationError() {
		
		System.out.println(5/0);
		
	}
	
	@Test
	public void fileDoesNotExist() throws FileNotFoundException {
		
		File f = new File("D://test");
		FileReader fr = new FileReader(f);
		
	}
	
	@Manual
	@Test
	public void thisIsaManualTest() {
				
	}
	
}
