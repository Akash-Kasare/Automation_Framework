package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.example.context.ScenarioContext;
import org.example.utils.RestClient;
import org.testng.Assert;
import java.util.Map;
import java.util.HashMap;

/**
 * Step definitions for API testing scenarios.
 * This class links the plain English Gherkin steps in the feature files to actual Java code execution.
 */
public class APIStepDefinitions {
    private RestClient restClient;
    private ScenarioContext context;

    /**
     * Constructor using PicoContainer for dependency injection.
     * It automatically receives the ScenarioContext so data can be shared across steps.
     * @param context The shared context for the current scenario.
     */
    public APIStepDefinitions(ScenarioContext context) {
        this.context = context;
        this.restClient = new RestClient();
    }

    /**
     * Stores the given API endpoint path into the shared context.
     * This prepares the target URL for the next HTTP request.
     * @param endpoint The API path (e.g., "/productsList") from the feature file.
     */
    @Given("I have the API endpoint {string}")
    public void setApiEndpoint(String endpoint) {
        context.setApiEndpoint(endpoint);
        System.out.println("API Endpoint set to: " + endpoint);
    }

    /**
     * Reads a data table from the feature file and stores it as request parameters.
     * These parameters will be sent in the body or query string of the next HTTP request.
     * @param dataTable The table of key-value pairs provided in the feature file step.
     */
    @Given("I have the following request parameters:")
    public void setRequestParameters(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> params = dataTable.asMap(String.class, String.class);
        context.setRequestParams(new HashMap<>(params));
        System.out.println("Request parameters set: " + params);
    }

    /**
     * Sends an HTTP GET request to the previously stored endpoint.
     * It automatically includes any stored request parameters as query string parameters,
     * and saves the server's response into the context for validation later.
     */
    @When("I send a GET request")
    public void sendGetRequest() {
        String endpoint = context.getApiEndpoint();
        Assert.assertNotNull(endpoint, "API Endpoint should be set before making request");
        
        Response response;
        if (context.getRequestParams().isEmpty()) {
            response = restClient.get(endpoint);
        } else {
            response = restClient.get(endpoint, context.getRequestParams());
        }
        context.setLastResponse(response);
        context.clearRequestParams(); // Clean up for the next request
        
        System.out.println("GET Request sent to: " + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    /**
     * Sends an HTTP POST request to the previously stored endpoint.
     * It includes any stored request parameters as form data in the body,
     * and saves the server's response into the context.
     */
    @When("I send a POST request")
    public void sendPostRequest() {
        String endpoint = context.getApiEndpoint();
        Assert.assertNotNull(endpoint, "API Endpoint should be set before making request");
        
        Response response;
        if (context.getRequestParams().isEmpty()) {
            response = restClient.post(endpoint);
        } else {
            response = restClient.post(endpoint, context.getRequestParams());
        }
        context.setLastResponse(response);
        context.clearRequestParams();
        
        System.out.println("POST Request sent to: " + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    /**
     * Sends an HTTP PUT request (typically used for updates) to the previously stored endpoint.
     * Any stored request parameters are sent as form data in the request body.
     */
    @When("I send a PUT request")
    public void sendPutRequest() {
        String endpoint = context.getApiEndpoint();
        Assert.assertNotNull(endpoint, "API Endpoint should be set before making request");
        
        Response response;
        if (context.getRequestParams().isEmpty()) {
            response = restClient.put(endpoint);
        } else {
            response = restClient.put(endpoint, context.getRequestParams());
        }
        context.setLastResponse(response);
        context.clearRequestParams();
        
        System.out.println("PUT Request sent to: " + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    /**
     * Sends an HTTP DELETE request to the previously stored endpoint.
     * Can optionally include stored request parameters, and saves the response.
     */
    @When("I send a DELETE request")
    public void sendDeleteRequest() {
        String endpoint = context.getApiEndpoint();
        Assert.assertNotNull(endpoint, "API Endpoint should be set before making request");
        
        Response response;
        if (context.getRequestParams().isEmpty()) {
            response = restClient.delete(endpoint);
        } else {
            response = restClient.delete(endpoint, context.getRequestParams());
        }
        context.setLastResponse(response);
        context.clearRequestParams();
        
        System.out.println("DELETE Request sent to: " + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    /**
     * Verifies that the HTTP status code from the last response matches the expected value.
     * @param expectedStatusCode The status code expected (e.g., 200 for OK).
     */
    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Response response = context.getLastResponse();
        Assert.assertNotNull(response, "Response should not be null");
        
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
        System.out.println("✓ Status Code verification passed: " + actualStatusCode);
    }

    /**
     * Verifies that the response body text contains a specific expected message.
     * @param expectedMessage The exact string of text that should appear somewhere in the response.
     */
    @Then("the response message should contain {string}")
    public void verifyResponseMessage(String expectedMessage) {
        Response response = context.getLastResponse();
        Assert.assertNotNull(response, "Response should not be null");
        
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(expectedMessage),
                "Response body should contain: '" + expectedMessage + "'\nActual body: " + responseBody);
        System.out.println("✓ Response message verification passed");
    }

    /**
     * Validates that the response body is formatted as valid JSON data.
     * It does this by attempting to parse the response using jsonPath.
     */
    @Then("the response should contain a valid JSON")
    public void verifyValidJson() {
        Response response = context.getLastResponse();
        Assert.assertNotNull(response, "Response should not be null");
        
        try {
            response.jsonPath();
            System.out.println("✓ Response contains valid JSON");
        } catch (Exception e) {
            Assert.fail("Response body is not valid JSON: " + e.getMessage());
        }
    }

    /**
     * Verifies that a specific HTTP header in the response has the expected value.
     * @param headerName The name of the header to check (e.g., "Content-Type").
     * @param expectedValue The value it should be equal to (e.g., "application/json").
     */
    @Then("the response header {string} should be {string}")
    public void verifyResponseHeader(String headerName, String expectedValue) {
        Response response = context.getLastResponse();
        Assert.assertNotNull(response, "Response should not be null");
        
        String headerValue = response.getHeader(headerName);
        Assert.assertNotNull(headerValue, "Header '" + headerName + "' should exist in response");
        Assert.assertEquals(headerValue, expectedValue,
                "Header '" + headerName + "' should be '" + expectedValue + "' but got '" + headerValue + "'");
        System.out.println("✓ Response header verification passed: " + headerName + " = " + headerValue);
    }
}
