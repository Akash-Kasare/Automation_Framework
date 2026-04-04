# 🔍 Logger Quick Reference Guide

## Quick Start - 2 Minutes

### 1. Import Logger in Step Definition
```java
import utils.TestLogger;
```

### 2. Use in Your Step
```java
@When("User performs action")
public void userPerformsAction() {
    TestLogger.stepStart("User performs action");
    try {
        // Your step logic
        TestLogger.stepPassed("Action completed successfully");
    } catch (Exception e) {
        TestLogger.stepFailed("Action failed", e);
        throw e;
    }
}
```

### 3. Run Tests
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

### 4. Check Logs
```
logs/test_execution_YYYY-MM-DD_HH-mm-ss.log
```

---

## Common Logging Patterns

### Pattern 1: Simple Step
```java
@When("User clicks button")
public void userClicksButton() {
    TestLogger.stepStart("Click button");
    try {
        driver.findElement(By.id("btn")).click();
        TestLogger.stepPassed("Button clicked");
    } catch (Exception e) {
        TestLogger.stepFailed("Failed to click button", e);
        throw e;
    }
}
```

### Pattern 2: Step with Verification
```java
@Then("User should see message")
public void userShouldSeeMessage() {
    TestLogger.stepStart("Verify message visible");
    try {
        WebElement msg = driver.findElement(By.className("message"));
        boolean visible = msg.isDisplayed();
        TestLogger.assertion("Message visible", visible, "Message: " + msg.getText());
        Assert.assertTrue("Message not visible", visible);
        TestLogger.stepPassed("Message verified successfully");
    } catch (Exception e) {
        TestLogger.stepFailed("Failed to verify message", e);
        throw e;
    }
}
```

### Pattern 3: Step with Actions
```java
@When("User enters {string} in field")
public void userEntersInField(String value) {
    TestLogger.stepStart("Enter value: " + value);
    try {
        WebElement field = driver.findElement(By.id("input-field"));
        TestLogger.action("Input Field", "Clear", "Clearing previous value");
        field.clear();
        TestLogger.action("Input Field", "SendKeys", "Entering: " + value);
        field.sendKeys(value);
        TestLogger.stepPassed("Value entered successfully: " + value);
    } catch (Exception e) {
        TestLogger.stepFailed("Failed to enter value", e);
        throw e;
    }
}
```

---

## Log Levels & Symbols

| Symbol | Level | Method | Usage |
|--------|-------|--------|-------|
| ℹ️ | INFO | info() | General information |
| ⚠️ | WARNING | warning() | Warnings, skipped steps |
| ❌ | SEVERE | error() | Errors, failures |
| 🔍 | FINE | debug() | Debug details |
| ✅ | SUCCESS | stepPassed() | Step success |
| ❌ | FAIL | stepFailed() | Step failure |
| ⏭️ | SKIP | stepSkipped() | Step skipped |
| ✓ | PASS | assertion() | Assertion passed |
| ✗ | FAIL | assertion() | Assertion failed |

---

## Most Used Methods

```java
// START every step
TestLogger.stepStart("Description of what step does");

// On Success - wrap in try
TestLogger.stepPassed("What was accomplished");

// On Failure - catch exception
TestLogger.stepFailed("Error message", exception);

// Log actions - between logic steps
TestLogger.action("Element Name", "Action", "Details");

// Log assertions - after check
TestLogger.assertion("Condition Name", result, "Details");

// Log info - for context
TestLogger.info("Additional information");
```

---

## Finding Errors in Logs

### Search for Failures
```bash
# All failed steps
grep "❌ STEP FAILED" logs/*.log

# All failed scenarios
grep "❌ SCENARIO COMPLETED" logs/*.log

# Specific exception
grep "NoSuchElementException" logs/*.log

# Slow steps
grep "Execution Time: [5-9][0-9]{3,}" logs/*.log
```

### View Log Files
```bash
# Latest log file
tail -100 logs/test_execution_*.log

# Search with context
grep -A 5 "❌ STEP FAILED" logs/*.log

# Count failures
grep -c "❌ STEP FAILED" logs/*.log
```

---

## Example Output

### ✅ Passing Step
```
[INFO] 10:30:47
╔════════════════════════════════════════════════════════════╗
║ ✅ STEP PASSED
║ Step: Enter Username
║ Message: Username entered successfully
║ Execution Time: 1200 ms
║ Timestamp: 2026-04-04 10:30:47
╚════════════════════════════════════════════════════════════╝
```

### ❌ Failed Step
```
[SEVERE] 10:35:20
╔════════════════════════════════════════════════════════════╗
║ ❌ STEP FAILED
║ Step: Click Login Button
║ Error: Element not found
║ Exception: NoSuchElementException: no such element
║ Execution Time: 10000 ms
║ Timestamp: 2026-04-04 10:35:20
╚════════════════════════════════════════════════════════════╝
```

---

## Quick Copy-Paste Template

```java
package steps;

import utils.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class YourSteps {
    
    private WebDriver driver;
    
    @When("Your step description")
    public void yourStepMethod() {
        TestLogger.stepStart("Your step description");
        try {
            // Your action here
            TestLogger.action("Element", "Action", "Details");
            
            // Your assertion here
            boolean result = true;  // Your condition
            TestLogger.assertion("Condition Name", result, "Details");
            
            TestLogger.stepPassed("Step completed successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Error description", e);
            throw e;
        }
    }
}
```

---

## Do's and Don'ts

### ✅ DO
- ✅ Call `stepStart()` at beginning
- ✅ Call `stepPassed()` on success
- ✅ Call `stepFailed()` with exception on error
- ✅ Log important actions with `action()`
- ✅ Log assertions with `assertion()`
- ✅ Use try-catch for error handling
- ✅ Provide meaningful messages
- ✅ Include relevant details

### ❌ DON'T
- ❌ Forget to call `stepStart()`
- ❌ Skip error logging on failure
- ❌ Use generic error messages
- ❌ Log sensitive information (passwords)
- ❌ Call `stepPassed()` on assertion failure
- ❌ Catch exceptions without logging
- ❌ Skip timing information
- ❌ Log too much unnecessary detail

---

## Viewing Logs

### During Test Execution
```
Console output shows logs in real-time with:
- ✅ Timestamps
- ✅ Formatted output
- ✅ Colors (if terminal supports)
- ✅ Status symbols
```

### After Test Execution
```
Log file saved to: logs/test_execution_YYYY-MM-DD_HH-mm-ss.log
- All step details
- Complete stack traces
- Timing information
- Assertion results
```

---

## Integration Checklist

For each step definition:

- [ ] Import TestLogger
- [ ] Call `stepStart()` at the start
- [ ] Wrap logic in try-catch
- [ ] Call `stepPassed()` on success
- [ ] Call `stepFailed()` on exception
- [ ] Log important actions with `action()`
- [ ] Log assertions with `assertion()`
- [ ] Include meaningful messages
- [ ] Use proper error handling

---

## Example: Complete Step with Logger

```java
@Then("User should see {int} products")
public void userShouldSeeProducts(int expectedCount) {
    TestLogger.stepStart("Verify product count: " + expectedCount);
    try {
        // Action 1: Find products
        TestLogger.action("Inventory", "FindAll", "Finding all product elements");
        List<WebElement> products = driver.findElements(By.className("product"));
        
        // Log details
        TestLogger.info("Products found: " + products.size());
        
        // Assertion
        int actualCount = products.size();
        boolean countMatches = actualCount == expectedCount;
        TestLogger.assertion(
            "Product count matches", 
            countMatches, 
            "Expected: " + expectedCount + ", Actual: " + actualCount
        );
        
        // Assert and step passed
        Assert.assertEquals("Product count does not match", expectedCount, actualCount);
        TestLogger.stepPassed("Product count verified: " + actualCount);
        
    } catch (AssertionError e) {
        TestLogger.stepFailed("Product count assertion failed", e);
        throw e;
    } catch (Exception e) {
        TestLogger.stepFailed("Error verifying product count", e);
        throw e;
    }
}
```

---

## Troubleshooting Logger Issues

### Issue: No logs created
**Solution:** Check that `logs/` directory exists or wait for first test failure

### Issue: Logger not imported
**Solution:** Add import: `import utils.TestLogger;`

### Issue: Too verbose logs
**Solution:** Use `TestLogger.debug()` for verbose info instead of `info()`

### Issue: Can't find specific log entry
**Solution:** Search for the timestamp from console output

---

## Performance Tips

### Log Timing for Slow Steps
```java
long start = System.currentTimeMillis();
// Step logic
long duration = System.currentTimeMillis() - start;
TestLogger.timingLog("Step operation", duration);
```

### Check for Slow Steps in Logs
```bash
grep "Execution Time:.*[5-9][0-9]{3,}" logs/*.log
```

### Optimize Slow Steps
1. Find slow steps in logs (> 5000 ms)
2. Check for unnecessary waits
3. Use more specific locators
4. Reduce timeout values if possible

---

## Best Practices Summary

1. **Always wrap steps in try-catch**
   ```java
   try { /* logic */ } catch (Exception e) { /* log */ throw e; }
   ```

2. **Log the flow of execution**
   ```java
   TestLogger.stepStart(...);
   TestLogger.action(...);
   TestLogger.stepPassed(...);
   ```

3. **Include meaningful context**
   ```java
   TestLogger.action("Element", "Action", "Entering: " + value);
   ```

4. **Log assertions for clarity**
   ```java
   TestLogger.assertion("Condition", result, "Expected: X, Actual: Y");
   ```

5. **Always capture exceptions**
   ```java
   catch (Exception e) {
       TestLogger.stepFailed("Error message", e);
       throw e;
   }
   ```

---

## Log File Naming Convention

```
logs/test_execution_YYYY-MM-DD_HH-mm-ss.log

Example:
logs/test_execution_2026-04-04_10-30-47.log
```

Each test run creates a new log file with:
- Date execution started
- Time execution started
- All test details for that run

---

**Logger Quick Reference: ✅ READY TO USE**

Copy patterns above into your step definitions for instant logging support!

