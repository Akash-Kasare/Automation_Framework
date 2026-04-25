# Advanced Examples & Extensions

## Table of Contents
1. [Adding Request Body to Scenarios](#adding-request-body-to-scenarios)
2. [Adding Response Validation](#adding-response-validation)
3. [Data-Driven Testing](#data-driven-testing)
4. [Authentication & Headers](#authentication--headers)
5. [Custom Assertions](#custom-assertions)
6. [Error Handling](#error-handling)
7. [Performance Testing](#performance-testing)
8. [Database Integration](#database-integration)
9. [Logging & Debugging](#logging--debugging)
10. [CI/CD Integration](#cicd-integration)

---

## Adding Request Body to Scenarios

### Feature File Example
```gherkin
Feature: API with Request Body

  @Smoke
  Scenario: Create new product with POST request
    Given I have the API endpoint "https://automationexercise.com/api/products"
    And I have the following request body:
      """
      {
        "name": "Test Product",
        "price": 99.99,
        "category": "Electronics"
      }
      """
    When I send a POST request
    Then the response status code should be 201
```

### Step Definition Update
```java
@Given("I have the following request body:")
public void setRequestBody(String body) {
    context.setRequestBody(body);
}

@When("I send a POST request")
public void sendPostRequestWithBody() {
    String endpoint = context.getApiEndpoint();
    String body = context.getRequestBody();
    
    Response response = restClient.post(endpoint, body);
    context.setLastResponse(response);
}
```

---

## Adding Response Validation

### Advanced Response Assertions

```java
// Validate specific JSON field
@Then("the response should contain product name {string}")
public void verifyProductName(String expectedName) {
    Response response = context.getLastResponse();
    String productName = response.jsonPath().getString("name");
    Assert.assertEquals(productName, expectedName);
}

// Validate JSON array size
@Then("the response should contain {int} products")
public void verifyProductCount(int expectedCount) {
    Response response = context.getLastResponse();
    int actualCount = response.jsonPath().getList("data").size();
    Assert.assertEquals(actualCount, expectedCount);
}

// Validate nested JSON fields
@Then("the product {string} should have price {double}")
public void verifyProductPrice(String productName, double expectedPrice) {
    Response response = context.getLastResponse();
    double actualPrice = response.jsonPath()
        .getDouble("data.find { it.name == '" + productName + "' }.price");
    Assert.assertEquals(actualPrice, expectedPrice, 0.01);
}

// Validate response time
@Then("the response should be received within {int} milliseconds")
public void verifyResponseTime(int maxTime) {
    Response response = context.getLastResponse();
    long responseTime = response.getTime();
    Assert.assertLessThan(responseTime, maxTime,
        "Response took " + responseTime + "ms, expected less than " + maxTime + "ms");
}
```

---

## Data-Driven Testing

### Feature File with Scenario Outline
```gherkin
Feature: Data-Driven API Testing

  @DataDriven
  Scenario Outline: Validate multiple products
    Given I have the API endpoint "https://automationexercise.com/api/productsList"
    When I search for product "<productName>"
    Then the response status code should be <statusCode>
    And the product price should be <price>

    Examples:
      | productName | statusCode | price  |
      | iPhone      | 200        | 999.99 |
      | Samsung     | 200        | 799.99 |
      | OnePlus     | 200        | 699.99 |
      | InvalidProd | 404        | 0.00   |
```

### Step Definition for Data-Driven
```java
@When("I search for product {string}")
public void searchProduct(String productName) {
    String endpoint = context.getApiEndpoint() + "?product=" + productName;
    Response response = restClient.get(endpoint);
    context.setLastResponse(response);
}

@Then("the product price should be {double}")
public void verifyPrice(double expectedPrice) {
    Response response = context.getLastResponse();
    double actualPrice = response.jsonPath().getDouble("price");
    Assert.assertEquals(actualPrice, expectedPrice);
}
```

---

## Authentication & Headers

### Enhanced RestClient with Authentication
```java
public class RestClient {
    // ... existing code ...
    
    private String authToken;
    
    public RestClient() {
        this.connectionTimeout = ConfigManager.getIntProperty("connection.timeout", 5000);
        this.readTimeout = ConfigManager.getIntProperty("read.timeout", 5000);
        this.authToken = ConfigManager.getProperty("auth.token", "");
    }
    
    public void setAuthToken(String token) {
        this.authToken = token;
    }
    
    public void authenticate(String username, String password) {
        // Example: Call login endpoint and store token
        String loginEndpoint = ConfigManager.getProperty("base.url") + "/login";
        String credentials = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        
        Response response = post(loginEndpoint, credentials);
        if (response.getStatusCode() == 200) {
            authToken = response.jsonPath().getString("token");
        }
    }
    
    private RequestSpecification getRequestSpecification() {
        RequestSpecification spec = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .connectTimeout(connectionTimeout)
                .readTimeout(readTimeout);
        
        if (authToken != null && !authToken.isEmpty()) {
            spec.header("Authorization", "Bearer " + authToken);
        }
        
        return spec;
    }
}
```

### Feature File with Authentication
```gherkin
Feature: API Testing with Authentication

  @Auth
  Scenario: Get protected resource with authentication
    Given I authenticate with username "user@example.com" and password "password123"
    And I have the API endpoint "https://api.example.com/protected/resource"
    When I send a GET request
    Then the response status code should be 200
```

### Step Definition for Authentication
```java
@Given("I authenticate with username {string} and password {string}")
public void authenticateUser(String username, String password) {
    restClient.authenticate(username, password);
}
```

---

## Custom Assertions

### Create Custom Assertion Class
```java
package org.example.assertions;

import org.testng.Assert;

public class APIAssertions {
    
    public static void assertStatusCodeSuccess(int statusCode) {
        Assert.assertTrue(statusCode >= 200 && statusCode < 300,
            "Expected successful status code (2xx), but got: " + statusCode);
    }
    
    public static void assertStatusCodeError(int statusCode) {
        Assert.assertTrue(statusCode >= 400 && statusCode < 600,
            "Expected error status code (4xx/5xx), but got: " + statusCode);
    }
    
    public static void assertResponseTime(long responseTime, long maxTime) {
        Assert.assertLessThanOrEqual(responseTime, maxTime,
            "Response time " + responseTime + "ms exceeded maximum " + maxTime + "ms");
    }
    
    public static void assertJsonFieldExists(String json, String jsonPath) {
        try {
            Object value = io.restassured.path.json.JsonPath.from(json).get(jsonPath);
            Assert.assertNotNull(value, "JSON field '" + jsonPath + "' not found");
        } catch (Exception e) {
            Assert.fail("JSON field '" + jsonPath + "' validation failed: " + e.getMessage());
        }
    }
}
```

### Usage in Step Definitions
```java
@Then("the response should be successful")
public void verifySuccessfulResponse() {
    Response response = context.getLastResponse();
    APIAssertions.assertStatusCodeSuccess(response.getStatusCode());
}

@Then("the response time should be acceptable")
public void verifyResponseTime() {
    Response response = context.getLastResponse();
    APIAssertions.assertResponseTime(response.getTime(), 5000);
}
```

---

## Error Handling

### Advanced Error Handling in RestClient
```java
public class RestClient {
    
    public Response getWithRetry(String endpoint, int maxRetries) {
        int retryCount = 0;
        Response response = null;
        
        while (retryCount < maxRetries) {
            try {
                response = get(endpoint);
                if (response.getStatusCode() < 500) {
                    return response;
                }
            } catch (Exception e) {
                System.out.println("Attempt " + (retryCount + 1) + " failed: " + e.getMessage());
            }
            
            retryCount++;
            if (retryCount < maxRetries) {
                try {
                    Thread.sleep(1000 * retryCount); // Exponential backoff
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        return response;
    }
    
    public Response getWithFallback(String primaryEndpoint, String fallbackEndpoint) {
        try {
            Response response = get(primaryEndpoint);
            if (response.getStatusCode() == 200) {
                return response;
            }
        } catch (Exception e) {
            System.out.println("Primary endpoint failed, trying fallback");
        }
        
        return get(fallbackEndpoint);
    }
}
```

### Feature File with Retry
```gherkin
@Retry
Scenario: Get product with retry on failure
  Given I have the API endpoint "https://api.example.com/products"
  When I send a GET request with retry attempts 3
  Then the response status code should be 200
```

---

## Performance Testing

### Performance Test Scenario
```gherkin
Feature: API Performance Testing

  @Performance
  Scenario: Validate API response time
    Given I have the API endpoint "https://automationexercise.com/api/productsList"
    When I send a GET request
    Then the response status code should be 200
    And the response time should be less than 5000 milliseconds
    And the response should contain a valid JSON
```

### Performance Metrics Collection
```java
public class PerformanceMetrics {
    private long startTime;
    private long endTime;
    private Response response;
    
    public void captureMetrics(Response response) {
        this.response = response;
        this.endTime = System.currentTimeMillis();
    }
    
    public long getResponseTime() {
        return endTime - startTime;
    }
    
    public long getContentLength() {
        return response.getBody().asString().length();
    }
    
    public double getResponseTimePerKB() {
        return (double) getResponseTime() / (getContentLength() / 1024);
    }
    
    public void printMetrics() {
        System.out.println("Response Time: " + getResponseTime() + "ms");
        System.out.println("Content Length: " + getContentLength() + " bytes");
        System.out.println("Time per KB: " + getResponseTimePerKB() + "ms/KB");
    }
}
```

---

## Database Integration

### Database Utility Class
```java
public class DatabaseManager {
    private Connection connection;
    
    public DatabaseManager(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
    
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Query execution failed: " + e.getMessage());
            return null;
        }
    }
    
    public int executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Update execution failed: " + e.getMessage());
            return 0;
        }
    }
    
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Database close failed: " + e.getMessage());
        }
    }
}
```

### Feature File with Database Validation
```gherkin
@Database
Scenario: Verify API response matches database
  Given I connect to database
  And I have the API endpoint "https://api.example.com/products/1"
  When I send a GET request
  Then the response status code should be 200
  And the response product name should match database value
  And cleanup database records
```

---

## Logging & Debugging

### Enhanced Logging Configuration
```properties
# config.properties
log.level=DEBUG
log.file=target/logs/test-execution.log
log.pattern=%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
log.max.file.size=10MB
log.max.backup.index=5
```

### Request/Response Logger
```java
public class RequestResponseLogger {
    
    public static void logRequest(String method, String endpoint, String body) {
        System.out.println("========== REQUEST ==========");
        System.out.println("Method: " + method);
        System.out.println("Endpoint: " + endpoint);
        if (body != null && !body.isEmpty()) {
            System.out.println("Body: " + body);
        }
        System.out.println("=============================");
    }
    
    public static void logResponse(Response response) {
        System.out.println("========== RESPONSE ==========");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("==============================");
    }
}
```

### Usage in RestClient
```java
public Response get(String endpoint) {
    RequestResponseLogger.logRequest("GET", endpoint, null);
    Response response = getRequestSpecification()
            .when()
            .get(endpoint)
            .then()
            .extract()
            .response();
    RequestResponseLogger.logResponse(response);
    return response;
}
```

---

## CI/CD Integration

### GitHub Actions Workflow
```yaml
name: API BDD Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Set up Java
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Run tests
        run: mvn clean test
      
      - name: Generate Allure Report
        if: always()
        run: mvn allure:report
      
      - name: Upload test results
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: test-results
          path: target/allure-results
      
      - name: Publish Allure Report
        if: always()
        uses: simple-elf/allure-report-action@master
        with:
          allure_results: target/allure-results
          gh_pages: gh-pages
```

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Report') {
            steps {
                sh 'mvn allure:report'
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/**/*.xml'
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber Report'
            ])
        }
    }
}
```

---

## Summary

These advanced examples demonstrate how to extend the framework for:
- ✓ Request body handling
- ✓ Advanced response validation
- ✓ Data-driven testing
- ✓ Authentication & security
- ✓ Custom assertions
- ✓ Error handling & retries
- ✓ Performance monitoring
- ✓ Database integration
- ✓ Enhanced logging
- ✓ CI/CD pipelines

Pick and choose what you need for your specific testing requirements!

---

**Remember:** Start simple, extend gradually as your testing needs grow.

