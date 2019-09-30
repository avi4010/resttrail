package com.studentapp.junit.studentInfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

//To run all the negative TCS
//clean verify -Dtags="studentfeature:NEGATIVE" serenity:aggregate

@RunWith (SerenityRunner.class)
public class TagsTest extends TestBase  {
	
	@WithTag ("studentfeature:NEGATIVE")
	@Title ("This test case checks If a wrong HTTP method gives a 405 error")
	@Test
	public void tagsTest001() {
		
		SerenityRest
		.rest()
		.given()
		.when()
		.post("/list")
		.then()
		.statusCode(405)
		.log()
		.all();
		
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:POSITVE"),
				@WithTag("studentfeature:SMOKE")
			})
	@Title ("This test case checks If a correct response is given for GET request")
	@Test
	public void tagsTest002() {
		
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200)
		.log()
		.all();
		
	}
	@WithTags(
			{
				@WithTag("studentfeature:NEGATIVE"),
				@WithTag("studentfeature:SMOKE")
			})
	@Title ("This test case checks If a correct response is given for GET request with wrong URI")
	@Test
	public void tagsTest003() {
		
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/list838")
		.then()
		.statusCode(400)
		.log()
		.all();
		
	}

}
