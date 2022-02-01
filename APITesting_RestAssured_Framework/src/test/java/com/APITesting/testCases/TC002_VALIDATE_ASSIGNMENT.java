package com.APITesting.testCases;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.APITesting.baseClass.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_VALIDATE_ASSIGNMENT extends Testbase {
	@BeforeClass
	void validateAssignmentDetails() {
		//Specify the base URI
		
		logger.info("*********************TC002 VALIDATE ASSIGNMENT DETAILS*************************************");

				RestAssured.baseURI="https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/";
				
			//Request Object	
				httpRequest=RestAssured.given();
				
			//Response Object
				response=httpRequest.request(Method.GET,"promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ");
				
			//Printing Response in the Console
				System.out.println("Response is:"+response.getBody().asString());
				
				jsonpath=response.jsonPath();
				
				
	}
			// Validate if the promotionID values are Strings
				@Test
				void validatePromotionIDObject()
				{
					logger.info("*********************Validate Promotion ID*************************************");
				List<String> promotionIds=jsonpath.get("promotions.promotionId");
				int promotionIdparameter=0;
				for(String str:promotionIds)
				{
					if(str.isEmpty())
					{
						promotionIdparameter=promotionIdparameter+1;
						
					}
					else
					{
						System.out.println(str);;
					}
				}
				Assert.assertEquals(promotionIdparameter, 0);
				System.out.println("Promotion Id's are string values");
				}
				@Test
				void validateProgramTypeObject()
				{
					logger.info("*********************VALIDATE Program Type Object*************************************");
				// Validate in Program Type is EPISODE or MOVIE or SERIES or SEASON 
				int programTypevalue=0;
				List<String> promotionIds=jsonpath.get("promotions.promotionId");
				for (int i=0;i<promotionIds.size();i++)
				{
					
				
				List<String> programType=jsonpath.get("promotions["+i+"].properties.programType");
				String programTypeparam=programType.toString().toUpperCase();
				System.out.println(programTypeparam);
				if(programTypeparam.contains("NULL"))
				{
					System.out.println("No Program type for this promotion");
				}
				else if(programTypeparam.contains("EPISODE")||programTypeparam.contains("MOVIE")||programTypeparam.contains("SERIES")||programTypeparam.contains("SEASON"))
						{
					System.out.println("Valid Program Type");
						}
				else
				{
					System.out.println("Invalid Program Type");
					programTypevalue=programTypevalue+1;
				}
				
				}
				Assert.assertEquals(programTypevalue, 0);
				System.out.println("Program Type is either in EPISODE or MOVIE or SERIES or SEASON ");
				
				}
				
				
				

				
				
			
	
}
