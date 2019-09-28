package com.studentapp.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Pending;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://localhost:8080/student";
		
	}
	
	@Test
	public void getAllStudents() {
		
		RestAssured.given()
		.when()
		.get("/1")
		.then()
		.log()
		.all()
		.statusCode(200);
		
	}
	
	@Pending
	@Test
	public void getSpecificStudent() {
		
		RestAssured.given()
		.when()
		.get("/1")
		.then()
		.log()
		.all()
		.statusCode(200);
		
	}
	
	@Test
	public void getAllStudentsfailing() {
		
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(300);
		
	}
	
	@Test
	public void compilationError() {
		
		System.out.println(5/0);
		
	}
	
	@Test
	public void comprimised() throws FileNotFoundException {
		
		File f = new File("D://test");
		FileReader fr = new FileReader(f);
		
	}
	
}
