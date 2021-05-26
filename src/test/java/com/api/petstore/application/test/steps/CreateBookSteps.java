package com.api.petstore.application.test.steps;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.petstore.application.test.pojo.CreateBookRequest;
import com.api.petstore.application.test.util.CommonRest;
import com.api.petstore.application.test.util.CreateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class CreateBookSteps {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateBookSteps.class);
	ObjectMapper objectMapper = new ObjectMapper();
	Response response;

	@Steps
	CreateBookRequest createBookRequest;

	@Steps
	CreateData createData;

	@Steps
	CommonRest commonRest;

	@Given("User create a request for creating book details")
	public void createBookRequest() {
		createBookRequest.setName(createData.generateFirstName());
		createBookRequest.setIsbn(createData.generateISBNNumber());
		createBookRequest.setAisle(createData.generateAisle());
		createBookRequest.setAuthor(createData.generateAuthor());

		try {
			LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createBookRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Given("User create a request for existing book")
	public void createExistingBookRequest() {
		createBookRequest.setName("Learn Appium Automation with Java");
		createBookRequest.setIsbn(123);
		createBookRequest.setAisle("123");
		createBookRequest.setAuthor("Test_123");

		try {
			LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createBookRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("^User sends a request for creating book details to (.*) with (.*)$")
	public void sendRequestForCreateBook(String service, String endpoint) {

		RequestSpecification request = commonRest.createRequestWithContentType(service);

		response = request.log().all().body(createBookRequest).when()
				.post(commonRest.properties.get("Create_Book_Endpoint")).andReturn();
	}

	@Then("Verify books details are created successfully")
	public void verifyResponse() {
		LOGGER.info(response.asPrettyString());
		Assert.assertEquals(200, response.getStatusCode());
	}

	@And("Verify Success Message in Response")
	public void verifySuccessMessageInResponse() {
		JsonPath jsonpath = new JsonPath(response.asString());
		Assert.assertEquals("Success Message is wrong in response", "successfully added", jsonpath.getString("Msg"));
	}

	@Then("Verify already created book message in response")
	public void alreadyCreatedBookMessage() {
		LOGGER.info(response.asPrettyString());
		JsonPath jsonpath = new JsonPath(response.asString());
		Assert.assertEquals("Response code is wrong in response", 404, response.getStatusCode());
		Assert.assertEquals("Message is wrong in Response",
				"Add Book operation failed, looks like the book already exists", jsonpath.getString("msg"));

	}

}
