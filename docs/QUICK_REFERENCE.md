# Quick Reference Guide - API BDD Cucumber Framework

## Project Summary

A production-ready BDD Cucumber framework for automated API testing following industry best practices.

**Technology Stack:**
- Cucumber 7.15.0 - BDD Framework
- RestAssured 5.4.0 - API Testing Library
- TestNG 7.9.0 - Test Framework
- Java 17 - Language
- Allure 2.25.0 - Reporting

## File Structure at a Glance

```
src/
├── test/
│   ├── java/org/example/
│   │   ├── config/          → CucumberConfiguration (Dependency Injection)
│   │   ├── context/         → ScenarioContext (Test Data Holder)
│   │   ├── hooks/           → Hooks (Setup/Teardown)
│   │   ├── runners/         → TestRunner (Test Execution)
│   │   ├── stepdefinitions/ → APIStepDefinitions (Gherkin Steps)
│   │   └── utils/           → ConfigManager, RestClient (Utilities)
│   └── resources/
│       ├── features/        → api.feature (Test Scenarios)
│       └── config.properties → Configuration
```

## 4 Test Scenarios

| # | Scenario | Endpoint | Method | Expected Status | Expected Response |
|---|----------|----------|--------|-----------------|-------------------|
| 1 | Get Products List | /productsList | GET | 200 | Valid JSON |
| 2 | POST Products List | /productsList | POST | 405 | Method Not Supported |
| 3 | Get Brands List | /brandsList | GET | 200 | Valid JSON |
| 4 | PUT Brands List | /brandsList | PUT | 405 | Method Not Supported |

## Key Classes & Their Responsibilities

### 1. **APIStepDefinitions**
Implements all Gherkin steps:
- `@Given` - Set API endpoint
- `@When` - Send HTTP requests (GET, POST, PUT, DELETE)
- `@Then` - Assert responses

### 2. **RestClient**
Provides HTTP methods:
- `get(endpoint)` - Sends GET request
- `post(endpoint)` - Sends POST request
- `put(endpoint)` - Sends PUT request
- `delete(endpoint)` - Sends DELETE request

### 3. **ScenarioContext**
Stores test state:
- `apiEndpoint` - Current endpoint under test
- `lastResponse` - Last HTTP response

### 4. **ConfigManager**
Reads configuration:
- Loads from `config.properties`
- Provides type-safe access (String, int)

### 5. **Hooks**
Setup/Teardown logic:
- `@Before` - Log scenario start
- `@After` - Log scenario end

## Running Tests

```bash
# Run all tests
mvn clean test

# Run with specific tag
mvn test -Dcucumber.filter.tags="@Smoke"

# View HTML report
# Open: target/cucumber-reports/cucumber.html

# Generate Allure report
mvn allure:report
mvn allure:serve
```

## Gherkin Syntax Used

```gherkin
Feature: Automation Exercise API Testing
  
  @Smoke @API
  Scenario: Get all products list via GET request
    Given I have the API endpoint "https://..."
    When I send a GET request
    Then the response status code should be 200
    And the response should contain a valid JSON
```

## Adding New Test Scenarios

### Step 1: Add Scenario to Feature File
```gherkin
@Smoke @API
Scenario: Test new endpoint
  Given I have the API endpoint "https://api.example.com/endpoint"
  When I send a GET request
  Then the response status code should be 200
```

### Step 2: Run Tests
```bash
mvn clean test
```

Step definitions are already implemented and reusable!

## Configuration Properties

```properties
# Timeout settings (milliseconds)
connection.timeout=5000
read.timeout=5000

# API Base URL (for future enhancement)
base.url=https://automationexercise.com/api

# Logging level
log.level=INFO

# Report output directory
report.path=target/allure-results
```

## Maven Commands Cheat Sheet

```bash
mvn clean                    # Clean build artifacts
mvn compile                  # Compile source code
mvn test                     # Run tests
mvn package                  # Create jar/war
mvn install                  # Install to local repository
mvn clean test               # Clean + Run tests
mvn clean install -DskipTests # Skip tests during install
mvn allure:report            # Generate Allure report
mvn allure:serve             # Serve Allure report
```

## Assertion Methods Available

```java
// Status Code
Then the response status code should be 200

// Response Message
Then the response message should contain "message text"

// JSON Validation
Then the response should contain a valid JSON

// Header Validation
Then the response header "Content-Type" should be "application/json"
```

## Console Output Example

```
================================================
Starting Scenario: Get all products list via GET request
================================================
API Endpoint set to: https://automationexercise.com/api/productsList
GET Request sent to: https://automationexercise.com/api/productsList
Response Status Code: 200
✓ Status Code verification passed: 200
✓ Response contains valid JSON
================================================
Scenario Status: PASSED
Scenario: Get all products list via GET request
================================================
```

## Reports Generated

1. **HTML Report** → `target/cucumber-reports/cucumber.html`
2. **JSON Report** → `target/cucumber-reports/cucumber.json`
3. **Allure Report** → `target/allure-results/`

## Key Features

✓ **BDD Approach** - Tests written in plain English (Gherkin)  
✓ **Reusable Steps** - Generic step definitions for multiple scenarios  
✓ **Clean Architecture** - Separation of concerns  
✓ **Dependency Injection** - Using PicoContainer  
✓ **Configuration Management** - Externalized properties  
✓ **Multiple Reporting** - HTML, JSON, Allure  
✓ **Error Handling** - Clear assertion messages  
✓ **Logging** - Console output for debugging  
✓ **CI/CD Ready** - Maven integration  
✓ **Maintainable** - Clear naming conventions  

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Tests not running | Run `mvn clean compile test` |
| Feature files not found | Check path: `src/test/resources/features/` |
| Connection timeout | Increase timeout in `config.properties` |
| Report not generating | Run `mvn clean test` first |
| Allure not opening | Run `mvn allure:serve` |

## Extending the Framework

### Add New HTTP Method
In `RestClient.java`:
```java
public Response patch(String endpoint) {
    return getRequestSpecification()
            .when()
            .patch(endpoint)
            .then()
            .extract()
            .response();
}
```

### Add New Assertion Step
In `APIStepDefinitions.java`:
```java
@Then("response time should be less than {int} ms")
public void verifyResponseTime(int maxTime) {
    // Implementation
}
```

### Add New Feature File
Create `src/test/resources/features/new_feature.feature`

## IDE Shortcuts

**IntelliJ IDEA:**
- `Ctrl+Shift+F10` - Run test file
- `Shift+F9` - Debug test
- `Ctrl+F` - Find in file
- `Ctrl+H` - Find and replace

**Eclipse:**
- `Alt+Shift+X, T` - Run as TestNG test
- `F11` - Debug
- `Ctrl+H` - Find and replace

## Performance Notes

- Each test makes 1 HTTP request
- Tests run sequentially by default
- Average execution time: ~1-2 seconds per test
- Total suite execution: ~5 seconds

## Useful Resources

- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [RestAssured Guide](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/)
- [Allure Report](https://docs.qameta.allure.io/)

---

**Quick Tips:**
- Always run `mvn clean` before `mvn test` to avoid cached issues
- Check the HTML report for detailed test execution logs
- Use Allure reports for better visualization
- Keep feature files in plain English for stakeholder readability

**Support:** For issues, check the EXECUTION_GUIDE.md or README.md files.

