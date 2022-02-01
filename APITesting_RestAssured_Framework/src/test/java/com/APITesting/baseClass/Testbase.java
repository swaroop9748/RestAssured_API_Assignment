package com.APITesting.baseClass;





import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testbase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public static JsonPath jsonpath;
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		logger=Logger.getLogger("RestAssuredAPITesting");
		String log4jConfiPath=System.getProperty("user.dir")+"/log4j.properties";
		PropertyConfigurator.configure(log4jConfiPath);
		logger.setLevel(Level.DEBUG);
	}
	

}
