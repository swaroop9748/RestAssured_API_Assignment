import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.groovy.json.internal.JsonFastParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_ASSIGNMENT {
	
	@Test
	void getpromotiondetails()
	{
		System.out.println("*********************TC001 ASSIGNMENT DETAILS*************************************");
	//Specify the base URI
		RestAssured.baseURI="https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/";
		
	//Request Object	
		RequestSpecification httpRequest=RestAssured.given();
		
	//Response Object
		Response response=httpRequest.request(Method.GET,"promotions?apikey=webB2BGDMSTGExy0sVDlZMzNDdUyZ");
		
	//Printing Response in the Console
		System.out.println("Response is:"+response.getBody().asString());
		
	// Status Code Validation
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is:"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	// Status Line Verification
		String statusLine=response.getStatusLine();
		System.out.println("Status line is:"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	//Json Body properties existence verification
		
		Assert.assertEquals(response.getBody().asString().contains("promotions"), true);
		Assert.assertEquals(response.getBody().asString().contains("promotionId"), true);
		Assert.assertEquals(response.getBody().asString().contains("orderId"), true);
		Assert.assertEquals(response.getBody().asString().contains("promoArea"), true);
		Assert.assertEquals(response.getBody().asString().contains("promoType"), true);
		
		
	//JSON body node value showprice Validation
		
		JsonPath jsonpath=response.jsonPath();
		List<Boolean> showPrice=jsonpath.get("promotions.showPrice");
		int showPriceParameter=0;
		for(Boolean str:showPrice)
		{
			if(str.equals(true)||str.equals(false))
			{
				System.out.println(str);
			}
			else
			{
				showPriceParameter=showPriceParameter+1;
			}
		}
		Assert.assertEquals(showPriceParameter, 0);
		System.out.println("Show Price value is either True or False");
		
	//JSON body node value showtext Validation
		
		List<Boolean> showText=jsonpath.get("promotions.showText");
		int showTextparameter=0;
				
		for(Boolean str:showText)
		{
			if(str.equals(true)||str.equals(false))
			{
				System.out.println(str);
			}
			else
			{
				showTextparameter=showTextparameter+1;
			}
		}
		Assert.assertEquals(showTextparameter, 0);
		System.out.println("Show text value is either True or False");
		
	//localizedTexts Should Exist with “ar” and “en” json objects
		
		List<String> localizedTexts=jsonpath.get("promotions.localizedTexts");
		System.out.println(localizedTexts.size());
		System.out.println(localizedTexts);
		int localizedTextsparam=0;
		
		Object[] array=localizedTexts.toArray();
		
		for (Object obj:array)
		{
			if(obj.toString().contains("ar")&&obj.toString().contains("en"))
			{
				System.out.println("Localized Texts cantains ar and en Json Objects");
			}
			else 
			{
				localizedTextsparam=localizedTextsparam+1;
			}
		}
		Assert.assertEquals(localizedTextsparam, 0);
		
	}

}
