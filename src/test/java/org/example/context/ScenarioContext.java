package org.example.context;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * ScenarioContext holds test data and state across step definitions.
 * It acts as a temporary memory for a single test scenario, allowing different
 * steps to share information (like the endpoint to call, parameters to send, or the response received).
 */
public class ScenarioContext {
    private String apiEndpoint;
    private Response lastResponse;
    private Map<String, String> requestParams = new HashMap<>();

    /**
     * Gets the currently stored API endpoint.
     * @return The API endpoint URL string.
     */
    public String getApiEndpoint() {
        return apiEndpoint;
    }

    /**
     * Stores the API endpoint to be used by subsequent request steps.
     * @param apiEndpoint The API endpoint to set (e.g., "/productsList").
     */
    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    /**
     * Gets the HTTP response from the most recently executed API request.
     * @return The RestAssured Response object containing status code, body, headers, etc.
     */
    public Response getLastResponse() {
        return lastResponse;
    }

    /**
     * Stores the HTTP response after an API request is made, so later steps can verify it.
     * @param lastResponse The RestAssured Response to store.
     */
    public void setLastResponse(Response lastResponse) {
        this.lastResponse = lastResponse;
    }

    /**
     * Gets all the request parameters (form data or query parameters) stored for the next request.
     * @return A map of key-value pairs representing the parameters.
     */
    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    /**
     * Replaces the current request parameters with a new set of parameters.
     * @param requestParams The new map of parameters to store.
     */
    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    /**
     * Adds a single key-value parameter to the current map of request parameters.
     * @param key The parameter name.
     * @param value The parameter value.
     */
    public void addRequestParam(String key, String value) {
        this.requestParams.put(key, value);
    }

    /**
     * Clears all stored request parameters.
     * This is usually called after a request is sent to ensure the next request starts fresh.
     */
    public void clearRequestParams() {
        this.requestParams.clear();
    }
}
