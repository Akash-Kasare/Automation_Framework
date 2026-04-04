# Test Execution Guide

## Prerequisites
1. Java JDK 11 or higher installed
2. Maven installed and configured
3. Chrome/Edge browser installed
4. WebDriver downloaded (managed by Selenium)

## Project Setup

### Step 1: Clone/Extract Project
```bash
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Update Configuration (if needed)
Edit `src/test/resources/config.properties`:
```properties
browser=chrome
os=windows
execution_env=local
app_url=https://www.saucedemo.com/
```

## Running Tests

### Option 1: Run All Tests
```bash
mvn test
```

### Option 2: Run Only Smoke Tests (Recommended for Quick Validation)
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

### Option 3: Run Specific Test Category

#### Login Tests Only
```bash
mvn test -Dcucumber.filter.tags="@Login"
```

#### Product Tests Only
```bash
mvn test -Dcucumber.filter.tags="@ProductListing"
```

#### Shopping Cart Tests Only
```bash
mvn test -Dcucumber.filter.tags="@ShoppingCart"
```

#### Checkout Tests Only
```bash
mvn test -Dcucumber.filter.tags="@Checkout"
```

#### Negative/Error Tests
```bash
mvn test -Dcucumber.filter.tags="@Negative"
```

#### Edge Case Tests
```bash
mvn test -Dcucumber.filter.tags="@EdgeCase"
```

### Option 4: Run Multiple Tags
```bash
# Run both Login and Checkout tests
mvn test -Dcucumber.filter.tags="@Login or @Checkout"

# Run only Smoke tests that are also Login tests
mvn test -Dcucumber.filter.tags="@Smoke and @Login"
```

### Option 5: Run with Different Browser
```bash
# Use Edge browser
mvn test -Dbrowser=edge

# Use Chrome browser (default)
mvn test -Dbrowser=chrome
```

### Option 6: Run with Different Environment
```bash
# Run on local machine (default)
mvn test -Dexecution_env=local

# Run on remote/cloud machine
mvn test -Dexecution_env=remote
```

### Option 7: Run Specific Feature Scenario
```bash
# Run specific scenario by line number
mvn test -Dcucumber.features="src/test/resources/feature/SauceDemo.feature:15"

# Run entire feature file
mvn test -Dcucumber.features="src/test/resources/feature/SauceDemo.feature"
```

## Test Execution Scenarios

### Scenario 1: Quick Smoke Test (2-3 minutes)
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```
**Expected Output:** ~30 passing tests

### Scenario 2: Complete Test Suite (10-15 minutes)
```bash
mvn clean test
```
**Expected Output:** ~50 passing tests

### Scenario 3: Negative Tests Only (5 minutes)
```bash
mvn clean test -Dcucumber.filter.tags="@Negative"
```
**Expected Output:** ~7 passing tests

### Scenario 4: User Journey Testing
```bash
mvn clean test -Dcucumber.filter.tags="@Login and @AddToCart and @Checkout"
```
**Simulates:** User login → Browse products → Add items → Checkout → Logout

### Scenario 5: Cross-Browser Testing
```bash
# Test on Chrome
mvn clean test -Dbrowser=chrome

# Test on Edge
mvn clean test -Dbrowser=edge
```

## Test Reports

After test execution, reports are generated:

### HTML Report
```
Location: target/cucumber-reports/cucumber.html
Access: Open in any web browser for visual report
```

### JSON Report
```
Location: target/cucumber-reports/cucumber.json
Use With: Cucumber reporting tools
```

### JUnit Report
```
Location: target/cucumber-reports/cucumber.xml
Use With: Jenkins, CI/CD pipelines
```

### Screenshots (On Failure)
```
Location: src/test/resources/screenshots/
File Format: PNG with timestamp
Naming: ScenarioName_YYYY-MM-DD_HH-mm-ss.png
```

## Troubleshooting

### Issue 1: Maven not found
```bash
# Solution: Add Maven to system PATH
# Windows: Set MAVEN_HOME and add %MAVEN_HOME%\bin to PATH
# Verify: mvn -version
```

### Issue 2: WebDriver not found
```bash
# Solution 1: Run clean install to download drivers
mvn clean install

# Solution 2: Install WebDriver separately
# Drivers are auto-downloaded by Selenium 4+
```

### Issue 3: Tests hang or timeout
```bash
# Solution: Increase timeout in config.properties
implicit_wait=20
page_load_timeout=30

# Or run with additional timeout:
mvn test -DimplicitWait=20
```

### Issue 4: "Step not defined" error
```bash
# Solution 1: Verify step text matches exactly in feature file
# Solution 2: Check that steps package is in glue path (runner/TestRunner.java)
# Solution 3: Rebuild project: mvn clean install
```

### Issue 5: Element not found errors
```bash
# Solutions:
# 1. Verify SauceDemo website is accessible
# 2. Update locators in SauceDemoSteps.java if DOM changed
# 3. Increase wait time for slow networks
# 4. Check if popup/modal is blocking element
```

## Debug Mode

### Enable Detailed Console Output
```bash
mvn test -X -Dcucumber.filter.tags="@Smoke"
```

### Run Single Scenario with Debug
```bash
# Add this to SauceDemoSteps.java for debugging:
System.out.println("DEBUG: Current URL = " + BaseClass.driver.getCurrentUrl());

mvn test -Dcucumber.features="src/test/resources/feature/SauceDemo.feature:15"
```

## Performance Testing

### Measure Test Execution Time
```bash
# Show execution times
mvn clean test -q

# With detailed times
mvn clean test -Dcucumber.filter.tags="@Performance"
```

### Expected Performance
- Quick Smoke Test: 2-3 minutes
- Full Suite: 10-15 minutes
- Individual scenario: 5-30 seconds
- Page load: < 5 seconds

## Continuous Integration Setup

### For Jenkins
```groovy
pipeline {
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
}
```

### For GitHub Actions
```yaml
name: Run Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
      - uses: actions/upload-artifact@v2
        if: always()
        with:
          name: cucumber-reports
          path: target/cucumber-reports
```

## Test Data Credentials

Use these test accounts for SauceDemo:

| Username | Password | Access | Notes |
|----------|----------|--------|-------|
| standard_user | secret_sauce | ✓ | Normal user |
| locked_out_user | secret_sauce | ✗ | Account locked |
| problem_user | secret_sauce | ✓ | Buggy UI |
| performance_glitch_user | secret_sauce | ✓ | Slow page load |

## Best Practices

1. **Always run clean before full suite:**
   ```bash
   mvn clean test
   ```

2. **Use smoke tests for quick validation:**
   ```bash
   mvn test -Dcucumber.filter.tags="@Smoke"
   ```

3. **Run specific categories during development:**
   ```bash
   mvn test -Dcucumber.filter.tags="@Login"
   ```

4. **Check reports after execution:**
   - Review HTML report for details
   - Check screenshots for failures
   - Analyze JUnit XML for CI/CD integration

5. **Keep config.properties updated:**
   - Change browser for cross-browser testing
   - Adjust timeouts for slow networks
   - Update URL for different environments

## Parallel Execution

To run tests in parallel (faster execution):

### Update pom.xml:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```

### Run:
```bash
mvn test
```

## Test Report Interpretation

### All Tests Passed ✓
```
[INFO] Tests run: 50, Failures: 0, Skipped: 0, Errors: 0
```

### Some Tests Failed ✗
```
[ERROR] Failed tests:
[ERROR]   TestRunner - Scenario: User fails to login with invalid credentials
```

### Check HTML Report for:
- Step execution status (Passed/Failed)
- Error messages and stack traces
- Screenshots of failures
- Execution time per step

## Cleanup After Tests

Clear old test data:
```bash
# Remove old screenshots
rm -rf src/test/resources/screenshots/*

# Clear target directory
mvn clean

# Full cleanup and rebuild
mvn clean install
```

## Support & Troubleshooting

For additional help:
1. Check STEP_DEFINITIONS_README.md for step details
2. Review SauceDemoSteps.java for locators and implementation
3. Check Hooks.java for setup/teardown logic
4. Verify config.properties for correct configuration
5. Review feature file for exact step wording

## Quick Command Reference

```bash
# Run all tests
mvn test

# Smoke tests only
mvn test -Dcucumber.filter.tags="@Smoke"

# Login tests
mvn test -Dcucumber.filter.tags="@Login"

# Checkout flow
mvn test -Dcucumber.filter.tags="@Checkout"

# With Chrome browser
mvn test -Dbrowser=chrome

# With Edge browser
mvn test -Dbrowser=edge

# Generate reports
mvn clean test

# Run specific scenario
mvn test -Dcucumber.features="src/test/resources/feature/SauceDemo.feature:15"

# Debug mode
mvn test -X

# Parallel execution
mvn test -DparallelCount=4
```

Happy Testing! 🎉

