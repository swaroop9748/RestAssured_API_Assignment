import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_VALIDATE_INVALID_APIKEY {
	@Test
	void Validate_InvalidAPIKEY()
	{
	//Specify the base URI
		System.out.println("*********************TC003 VALIDATE INVALID APIKEY*************************************");

		RestAssured.baseURI="https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/";
		
	//Request Object	
		RequestSpecification httpRequest=RestAssured.given();
		
	//Response Object
		Response response=httpRequest.request(Method.GET,"promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyp");
		
	//Printing Response in the Console
		System.out.println("Response is:"+response.getBody().asString());
		
	// Status Code Validation
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is:"+statusCode);
		Assert.assertEquals(statusCode, 403);
		
	// RequestId Verification
		JsonPath jsonpath=response.jsonPath();
		String requestId=jsonpath.get("error.requestId");
		Assert.assertEquals(requestId.isEmpty(), false);
	// Code validation
		String code=jsonpath.get("error.code");
		Assert.assertEquals(code, "8001");
		
		
	}
}
