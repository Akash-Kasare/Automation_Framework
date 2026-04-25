package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

/**
 * RestClient class provides methods for making HTTP requests
 */
public class RestClient {
    private int connectionTimeout;
    private int readTimeout;
    private String baseUrl;

    public RestClient() {
        this.connectionTimeout = ConfigManager.getIntProperty("connection.timeout", 5000);
        this.readTimeout = ConfigManager.getIntProperty("read.timeout", 5000);
        this.baseUrl = ConfigManager.getProperty("base.url", "https://automationexercise.com/api");
    }

    /**
     * Sends a GET request to the specified endpoint
     */
    public Response get(String endpoint) {
        return getRequestSpecification()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a GET request with query params
     */
    public Response get(String endpoint, Map<String, String> queryParams) {
        return getRequestSpecification()
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a POST request to the specified endpoint
     */
    public Response post(String endpoint) {
        return getRequestSpecification()
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a POST request with form parameters
     */
    public Response post(String endpoint, Map<String, String> formParams) {
        return getRequestSpecification()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a POST request with JSON body to the specified endpoint
     */
    public Response postWithBody(String endpoint, String body) {
        return getRequestSpecification()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request to the specified endpoint
     */
    public Response put(String endpoint) {
        return getRequestSpecification()
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request with form parameters
     */
    public Response put(String endpoint, Map<String, String> formParams) {
        return getRequestSpecification()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request with JSON body to the specified endpoint
     */
    public Response putWithBody(String endpoint, String body) {
        return getRequestSpecification()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a DELETE request to the specified endpoint
     */
    public Response delete(String endpoint) {
        return getRequestSpecification()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a DELETE request with form parameters
     */
    public Response delete(String endpoint, Map<String, String> formParams) {
        return getRequestSpecification()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Returns a configured RequestSpecification with default headers and timeout settings
     */
    private RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .config(io.restassured.config.RestAssuredConfig.config()
                    .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", connectionTimeout)
                        .setParam("http.socket.timeout", readTimeout)))
                .header("Accept", "application/json");
    }
}
