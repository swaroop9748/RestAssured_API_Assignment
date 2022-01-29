import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_VALIDATE_ASSIGNMENT {
	@Test
	void validateAssignmentDetails() {
		//Specify the base URI
		
		System.out.println("*********************TC002 VALIDATE ASSIGNMENT DETAILS*************************************");

				RestAssured.baseURI="https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/";
				
			//Request Object	
				RequestSpecification httpRequest=RestAssured.given();
				
			//Response Object
				Response response=httpRequest.request(Method.GET,"promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ");
				
			//Printing Response in the Console
				System.out.println("Response is:"+response.getBody().asString());
				
			// Validate if the promotionID values are Strings
				JsonPath jsonpath=response.jsonPath();
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
				
				// Validate in Program Type is EPISODE or MOVIE or SERIES or SEASON 
				int programTypevalue=0;
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
