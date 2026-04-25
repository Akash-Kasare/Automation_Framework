# Test Execution Guide

## Quick Start

### 1. Clone/Setup Project
```bash
cd C:\Users\akash\eclipse-workspace\API_BDD_CUCMBER
```

### 2. Verify Maven Installation
```bash
mvn -version
```

### 3. Install Dependencies
```bash
mvn clean install -DskipTests
```

### 4. Run All Tests
```bash
mvn clean test
```

## Detailed Test Execution

### Run Specific Feature File
```bash
mvn test -Dcucumber.features=src/test/resources/features/api.feature
```

### Run Tests with Specific Tags
```bash
# Run only Smoke tests
mvn test -Dcucumber.filter.tags="@Smoke"

# Run tests with specific tags AND condition
mvn test -Dcucumber.filter.tags="@Smoke and @API"
```

### Run Tests in Dry-Run Mode (Parse only, no execution)
```bash
mvn test -Dcucumber.options="--dry-run"
```

### Run with Specific Glue Code (Step Definitions)
```bash
mvn test -Dcucumber.options="--glue org.example.stepdefinitions --glue org.example.hooks"
```

## Test Output Structure

After running tests, you'll see output like:

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

## Report Locations

After test execution:

### 1. Cucumber HTML Report
```
target/cucumber-reports/cucumber.html
```
Open this file in a web browser to view detailed test results.

### 2. Cucumber JSON Report
```
target/cucumber-reports/cucumber.json
```
Used by CI/CD systems for integration.

### 3. Allure Report
```
target/allure-results/
```

**Generate and View Allure Report:**
```bash
mvn allure:report
mvn allure:serve
```

This will open an interactive Allure report in your default browser.

## Expected Test Results

All 4 tests should PASS:

### ✓ Test 1: Get all products list via GET request
- **Request:** GET https://automationexercise.com/api/productsList
- **Expected:** Status 200 + Valid JSON
- **Result:** PASS

### ✓ Test 2: POST to products list endpoint
- **Request:** POST https://automationexercise.com/api/productsList
- **Expected:** Status 405 + "This request method is not supported"
- **Result:** PASS

### ✓ Test 3: Get all brands list via GET request
- **Request:** GET https://automationexercise.com/api/brandsList
- **Expected:** Status 200 + Valid JSON
- **Result:** PASS

### ✓ Test 4: PUT to brands list endpoint
- **Request:** PUT https://automationexercise.com/api/brandsList
- **Expected:** Status 405 + "This request method is not supported"
- **Result:** PASS

## Troubleshooting Common Issues

### Issue: "Class not found" errors
**Solution:**
```bash
mvn clean compile test
```

### Issue: Feature files not found
**Solution:** Verify file location:
```
src/test/resources/features/api.feature
```

### Issue: Connection timeout errors
**Solution:** Update `config.properties`:
```properties
connection.timeout=10000  # Increase from 5000 to 10000
read.timeout=10000
```

### Issue: Tests fail with "Endpoint not found"
**Solution:** Verify the base URL is accessible:
```bash
# Test connectivity using PowerShell
Invoke-WebRequest -Uri "https://automationexercise.com/api/productsList" -Method GET
```

### Issue: Allure report not generating
**Solution:**
```bash
mvn clean test
mvn allure:report
```

## IDE Integration

### IntelliJ IDEA
1. Right-click on `TestRunner.java` → Run 'TestRunner'
2. Or right-click on `api.feature` → Run 'Feature: api'

### Eclipse
1. Right-click on project → Run As → Maven Test
2. Or create Run Configuration with goal: `clean test`

## CI/CD Integration

### GitHub Actions Example
```yaml
name: API Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '17'
      - run: mvn clean test
      - uses: actions/upload-artifact@v2
        if: always()
        with:
          name: allure-results
          path: target/allure-results
```

### Jenkins Scripted Pipeline Example
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                allure includeProperties: false, 
                       jdk: '', 
                       results: [[path: 'target/allure-results']]
            }
        }
    }
}
```

## Performance Tips

### Run Tests in Parallel (if supported)
```bash
mvn test -Dthreads=4
```

### Skip Report Generation for Faster Execution
```bash
mvn test -DargLine="-Dcucumber.plugin=pretty"
```

### Run Only Failed Tests
```bash
mvn test -Dsurefire.rerunFailingTestsCount=2
```

## Useful Maven Commands

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests only
mvn test

# Skip tests during build
mvn clean install -DskipTests

# Run single test class
mvn test -Dtest=TestRunner

# View dependency tree
mvn dependency:tree

# Check for outdated dependencies
mvn versions:display-dependency-updates

# Generate project documentation
mvn site
```

## Debug Mode

### Run with Debug Logging
Add to `config.properties`:
```properties
log.level=DEBUG
```

### Run with System Output
```bash
mvn test -X
```

### Debug in IDE
1. Set breakpoint in step definition
2. Run test in debug mode (Shift+F9 in IntelliJ)

---

**Framework:** Cucumber BDD + RestAssured + TestNG  
**Java:** 17+  
**Maven:** 3.6+

