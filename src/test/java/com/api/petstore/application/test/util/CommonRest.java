package com.api.petstore.application.test.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class CommonRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonRest.class);
	public static HashMap<String, String> properties = new HashMap<String, String>();
	public static HashMap<String, String> query_parameter = new HashMap<String, String>();
	CreateData createdata = new CreateData();
	RequestSpecification request;

	public void readProperties() {
		Properties prop = new Properties();
		FileInputStream file;

		try {
			file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");

			prop.load(file);

			for (String key : prop.stringPropertyNames()) {
				LOGGER.info(key + ":" + prop.get(key));
				properties.put(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RequestSpecification createRequestWithContentType(String service) {
		readProperties();
		request = SerenityRest.given().baseUri(properties.get(service)).contentType("application/json");
		return request;
	}

	public RequestSpecification createRequestWithoutContentType(String service) {
		readProperties();
		request = SerenityRest.given().baseUri(properties.get(service));
		return request;
	}

	public RequestSpecification createRequestWithInvalidQueryParameter(String Value, String queryParameter) {
		readProperties();
		request = request.given().queryParam(queryParameter, Value);
		return request;
	}

	public RequestSpecification createRequestWithValidQueryParameter(String queryParameter) {
		readProperties();
		request = request.given().queryParam(queryParameter, "Test_123");
		return request;
	}

}
