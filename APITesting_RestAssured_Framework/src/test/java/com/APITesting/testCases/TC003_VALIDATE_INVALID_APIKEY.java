package com.APITesting.testCases;
import java.io.IOException;

import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.APITesting.Utilities.XLUtils;
import com.APITesting.baseClass.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_VALIDATE_INVALID_APIKEY extends Testbase {
	
	
	@Test(dataProvider="APIDATA")
	void Validate_InvalidAPIKEY(String TestcaseID,String Keyword,String APIKey,String responseStatuscode,String errorCode,String errorMessage)
	{
		logger.info("*********************TC003_VALIDATE_INVALID_APIKEY*************************************");
	
		//logger.info("*********************"+TestcaseID+" STARTED EXECUTION*************************************");
		//Specify the base URI
		RestAssured.baseURI="https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/";
		
	//Request Object	
		httpRequest=RestAssured.given();
		
	//Response Object
		response=httpRequest.request(Method.GET,"promotions?apikey="+APIKey+"");
		
	//Printing Response in the Console
		System.out.println("Response is:"+response.getBody().asString());
		JsonPath jsonpath=response.jsonPath();
		System.out.println(Keyword);
		
	// Status Code Validation
		if(Keyword.equalsIgnoreCase("STATUSCODE"))
		{
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is:"+statusCode);
		String value=""+statusCode+"";
		Assert.assertEquals(value, responseStatuscode);
		
		}
	// RequestId Verification
		if(Keyword.equalsIgnoreCase("REQUESTID"))
		{
		//String status=response.asString().contains(errorMessage)
		String requestId=jsonpath.get("error.requestId");
		System.out.println(requestId);
		Assert.assertEquals(requestId.isEmpty(), false);
		}
	// Code validation
		if(Keyword.contentEquals("ERRORCODE"))
		{
		String code=jsonpath.get("error.code");
		System.out.println(code);
		Assert.assertEquals(code, errorCode);
		}
	//Error Message Validation
		if(Keyword.equalsIgnoreCase("ERRORMESSAGE"))
		{
		String responseerrorMessage=jsonpath.get("error.message");
		System.out.println(responseerrorMessage);
		Assert.assertEquals(responseerrorMessage,errorMessage );
		}
		
		
		
	}
	
	@DataProvider(name="APIDATA")
	String [][] getapiData() throws IOException
	{
	
		
		// Read Data from Excels using UTILITY files
		String path=System.getProperty("user.dir")+"/src/test/Testdata/RestAPI_TestData.xlsx";
		int rowNum=XLUtils.getRowCount(path, "Scenario3");
		int colNum=XLUtils.getCellCount(path, "Scenario3",1);
		
		String APIData[][]=new String[rowNum][colNum];
		for(int i=1;i<=rowNum;i++)
		{
			for(int j=0;j<colNum;j++)
			{
				APIData[i-1][j]=XLUtils.getCellData(path, "Scenario3", i, j);
			}
		}
		
		return APIData;
		
		
	}
}