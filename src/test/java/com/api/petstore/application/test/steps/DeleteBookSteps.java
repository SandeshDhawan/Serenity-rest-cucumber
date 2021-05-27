package com.api.petstore.application.test.steps;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.petstore.application.test.pojo.CreateBookRequest;
import com.api.petstore.application.test.pojo.DeleteBookRequest;
import com.api.petstore.application.test.util.CommonRest;
import com.api.petstore.application.test.util.CreateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;

public class DeleteBookSteps {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateBookSteps.class);
	ObjectMapper objectMapper = new ObjectMapper();
	Response response;

	@Steps
	CommonRest commonRest;

	@Steps
	DeleteBookRequest DeleteBookRequest;

	@Given("User create a request for deleting book record")
	public void deleteBookRequest() {
		DeleteBookRequest.setID("Test_12313");
		try {
			LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(DeleteBookRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("^User sends a request for deleting book details to (.*) with (.*)$")
	public void sendRequestForDeletingBook(String service, String endpoint) {

		RequestSpecification request = commonRest.createRequestWithContentType(service);

		response = request.log().all().body(DeleteBookRequest).when()
				.post(commonRest.properties.get(endpoint)).andReturn();
	}

	@Then("Verify message book not found for invalid ID")
	public void bookNotFoundMessage() {
		JsonPath jsonpath = new JsonPath(response.asString());
		LOGGER.info(response.asPrettyString());
		Assert.assertEquals(404, response.getStatusCode());

		Assert.assertEquals("Message is wrong in response",
				"Delete Book operation failed, looks like the book doesnt exists", jsonpath.getString("Msg"));

	}

}
