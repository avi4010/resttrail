package com.studentapp.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestBase {
	
	@BeforeClass
	public static void init() {
		
		//java -jar rest.jar --server.port=8085
		RestAssured.baseURI = "http://localhost:8085/student";
		
	}

}
