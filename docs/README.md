# API BDD Cucumber Framework

## Project Overview
This project implements a comprehensive BDD (Behavior-Driven Development) framework for API testing using Cucumber, RestAssured, and TestNG following industry best practices.

## Project Structure

```
API_BDD_CUCMBER/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       └── Main.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── org/example/
│       │       ├── config/
│       │       │   └── CucumberConfiguration.java
│       │       ├── context/
│       │       │   └── ScenarioContext.java
│       │       ├── hooks/
│       │       │   └── Hooks.java
│       │       ├── runners/
│       │       │   └── TestRunner.java
│       │       ├── stepdefinitions/
│       │       │   └── APIStepDefinitions.java
│       │       └── utils/
│       │           ├── ConfigManager.java
│       │           └── RestClient.java
│       └── resources/
│           ├── features/
│           │   └── api.feature
│           └── config.properties
└── pom.xml
```

## Framework Components

### 1. **Feature Files** (`src/test/resources/features/`)
- `api.feature` - Contains Gherkin scenarios for all 4 API test cases

### 2. **Step Definitions** (`src/test/java/org/example/stepdefinitions/`)
- `APIStepDefinitions.java` - Implements step definitions for Gherkin scenarios
  - Given step: Set API endpoint
  - When steps: Send GET, POST, PUT, DELETE requests
  - Then steps: Verify status code, response message, valid JSON, response headers

### 3. **Utilities** (`src/test/java/org/example/utils/`)
- **ConfigManager.java** - Handles reading configuration from `config.properties`
- **RestClient.java** - Provides methods for HTTP requests (GET, POST, PUT, DELETE)

### 4. **Context** (`src/test/java/org/example/context/`)
- **ScenarioContext.java** - Holds test data and state across step definitions
- Implements dependency injection pattern

### 5. **Hooks** (`src/test/java/org/example/hooks/`)
- **Hooks.java** - Contains setup and teardown logic for each scenario
  - Before hook: Logs scenario start
  - After hook: Logs scenario status and end

### 6. **Configuration** (`src/test/java/org/example/config/`)
- **CucumberConfiguration.java** - Spring configuration for dependency injection

### 7. **Test Runner** (`src/test/java/org/example/runners/`)
- **TestRunner.java** - TestNG runner to execute Cucumber tests
  - Configured to run @Smoke tagged scenarios
  - Generates multiple reports (HTML, JSON, Allure)

## Test Scenarios

### API 1: Get All Products List
- **Endpoint:** `https://automationexercise.com/api/productsList`
- **Method:** GET
- **Expected Status Code:** 200
- **Expected Response:** Valid JSON

### API 2: POST To Products List
- **Endpoint:** `https://automationexercise.com/api/productsList`
- **Method:** POST
- **Expected Status Code:** 405
- **Expected Response:** "This request method is not supported"

### API 3: Get All Brands List
- **Endpoint:** `https://automationexercise.com/api/brandsList`
- **Method:** GET
- **Expected Status Code:** 200
- **Expected Response:** Valid JSON

### API 4: PUT To Brands List
- **Endpoint:** `https://automationexercise.com/api/brandsList`
- **Method:** PUT
- **Expected Status Code:** 405
- **Expected Response:** "This request method is not supported"

## Configuration Properties

File: `src/test/resources/config.properties`

```properties
## API Configuration
base.url=https://automationexercise.com/api
connection.timeout=5000      # Connection timeout in milliseconds
read.timeout=5000            # Read timeout in milliseconds

## Logging Configuration
log.level=INFO

## Report Configuration
report.path=target/allure-results
```

## Running Tests

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Run All Tests
```bash
mvn clean test
```

### Run Specific Tests with Tag
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### Run in Parallel
```bash
mvn clean test -Dthreads=4
```

### Generate Allure Report
```bash
mvn allure:report
mvn allure:serve
```

## Test Execution Flow

1. **Setup Phase**
   - Before Hook runs before each scenario
   - Logs scenario start information

2. **Execution Phase**
   - Given step: Set API endpoint
   - When step: Send HTTP request
   - Then steps: Assert response status code, message, and valid JSON

3. **Teardown Phase**
   - After Hook runs after each scenario
   - Logs scenario status (passed/failed)

## Reports Generated

After test execution, reports are generated in:
- **HTML Report:** `target/cucumber-reports/cucumber.html`
- **JSON Report:** `target/cucumber-reports/cucumber.json`
- **Allure Report:** `target/allure-results/`

## Best Practices Implemented

1. **BDD Approach** - Tests written in Gherkin language for readability
2. **Separation of Concerns** - Step definitions, utilities, and context are separate
3. **Reusability** - Generic step definitions that can be used for multiple scenarios
4. **Configuration Management** - Externalized configuration in properties file
5. **Dependency Injection** - Using PicoContainer for managing dependencies
6. **Logging** - Console logging for debugging and monitoring
7. **Error Handling** - Comprehensive assertions with meaningful error messages
8. **Test Reporting** - Multiple report formats (HTML, JSON, Allure)
9. **Maintainability** - Clear naming conventions and documentation

## Dependencies Used

- **Cucumber Java:** BDD framework
- **Cucumber TestNG:** TestNG integration for Cucumber
- **RestAssured:** API testing library
- **TestNG:** Testing framework
- **Jackson:** JSON processing
- **Allure:** Reporting and monitoring
- **Picocontainer:** Dependency injection
- **SLF4J:** Logging

## Troubleshooting

### Tests Not Running
- Ensure all Java files are compiled: `mvn clean compile`
- Check if feature files are in correct location: `src/test/resources/features/`

### Connection Timeout
- Increase timeout values in `config.properties`
- Check internet connectivity

### Report Generation Fails
- Run `mvn clean` before execution
- Ensure sufficient disk space
- Check report path permissions

## Future Enhancements

- Add data-driven testing with scenario outlines
- Implement request/response logging
- Add custom assertions library
- Implement API response schema validation
- Add performance testing (response time assertions)
- Implement test data management
- Add API authentication handling
- Implement retry logic for flaky tests

---

**Author:** QA Automation Team  
**Framework:** Cucumber BDD + RestAssured + TestNG  
**Java Version:** 17  
**Date:** 2026-04-25

