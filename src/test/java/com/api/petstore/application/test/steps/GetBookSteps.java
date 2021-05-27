package com.api.petstore.application.test.steps;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.petstore.application.test.util.CommonRest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class GetBookSteps {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetBookSteps.class);

	RequestSpecification request;
	Response response;

	@Steps
	CommonRest commonRest;

	@Given("^User create a request for geting book details for sevice (.*)$")
	public void createRequestForGettingBookDetailsByAuthor(String service) {
		request = commonRest.createRequestWithContentType(service);
	}

	@And("^User pass value as (.*) for QueryParametr as (.*)$")
	public void createRequestWithInvalidQueryParameter(String Value, String queryparameter) {
		request = commonRest.createRequestWithInvalidQueryParameter(Value, queryparameter);
	}

	@When("^User sends a request for getting book details to (.*)$")
	public void sendRequestForGettingBookDetails(String endpoint) {
		response = request.log().all().get(commonRest.properties.get(endpoint)).andReturn();
	}

	@Then("Verify author not found message in respponse")
	public void authorNotFoundInResponse() {
		JsonPath jsonpath = new JsonPath(response.asString());
		LOGGER.info(response.asPrettyString());
		Assert.assertEquals("Wrong Status Code in response", 404, response.getStatusCode());

		Assert.assertEquals("Success Message is wrong in response",
				"The book by requested bookid / author name does not exists!", jsonpath.getString("msg"));

	}

	@Then("Verify book details in respponse")
	public void verifyBookDetails() {
		JsonPath jsonpath = new JsonPath(response.asString());
		LOGGER.info(response.asPrettyString());
		Assert.assertEquals("Wrong Status Code in response", 200, response.getStatusCode());

		Assert.assertEquals("Book Name is wrong in response", "[Learn Appium Automation with Java]",
				jsonpath.getString("book_name"));
		Assert.assertEquals("Isbn is wrong in response", "[Test_123]", jsonpath.getString("isbn"));
		Assert.assertEquals("aisle is wrong in response", "[123]", jsonpath.getString("aisle"));

	}

}
