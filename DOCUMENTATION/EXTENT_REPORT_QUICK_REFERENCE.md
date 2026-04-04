# 📊 EXTENT REPORT - QUICK REFERENCE

## Quick Start (2 Minutes)

### What Is Extent Report?
- Professional HTML test report
- Automatic screenshot capture
- Detailed test execution log
- Beautiful dashboard with statistics
- Mobile-responsive design

---

## 🎯 Most Used Methods

### Capture Screenshot
```java
// Capture on failure (automatic via Hooks)
ExtentReportUtils.captureScreenshot(driver, "TestName_Failed");

// Get as Base64
String base64 = ExtentReportUtils.captureScreenshotAsBase64(driver);
```

### Log Test Data
```java
Map<String, String> testData = new HashMap<>();
testData.put("Username", "standard_user");
testData.put("Password", "secret_sauce");

ExtentReportUtils.logTestDataUsed("LoginTest_Valid", testData);
```

### Log Browser Info
```java
ExtentReportUtils.logBrowserInfo("Chrome", "120", "Windows 11");
ExtentReportUtils.logEnvironmentInfo("QA", "https://www.saucedemo.com/");
```

### Log Assertion
```java
boolean result = loginSuccessful();
ExtentReportUtils.logAssertion(
    "Login successful",
    result,
    "Expected: Success",
    "Actual: " + result
);
```

### Log Test Details
```java
ExtentReportUtils.logTestDetails("Action", "User entered username");
ExtentReportUtils.logTestDetails("Verification", "Login successful");
```

---

## 📊 Report Files

### Configuration Files
```
extent.properties
  └─ Report location
  └─ Theme
  └─ Project name
  └─ Screenshot settings

extent-config.xml
  └─ Advanced settings
  └─ System information
  └─ Charts configuration
  └─ Dashboard settings
```

### Output Files
```
target/extent-reports/extent.html
  └─ Main HTML report
  └─ Opens automatically after tests
  └─ Contains all test details and screenshots
```

---

## 🚀 Complete Example

### Test with Extent Report
```java
@When("User performs login")
public void userPerformsLogin() {
    TestLogger.stepStart("Login with test data");
    try {
        // Get test data
        Map<String, String> testData = TestDataManager.getTestData(
            "LoginTests", "LoginTest_Valid"
        );
        
        // Log test data
        ExtentReportUtils.logTestDataUsed("LoginTest_Valid", testData);

        // Perform action
        ExtentReportUtils.logTestDetails("Action", "Entering credentials");
        loginForm.enterUsername(testData.get("Username"));
        loginForm.enterPassword(testData.get("Password"));

        // Click login
        ExtentReportUtils.logTestDetails("Action", "Clicking login button");
        loginForm.clickLogin();

        // Verify
        boolean success = isLoggedIn();
        
        // Log assertion
        ExtentReportUtils.logAssertion(
            "Login successful",
            success,
            "Success",
            success ? "Success" : "Failed"
        );

        // Write to Excel
        ExcelUtils.updateTestStatus("LoginTest_Valid", success ? "PASS" : "FAIL");

        // Capture screenshot
        if (success) {
            ExtentReportUtils.captureScreenshot(driver, "LoginSuccess");
        }

        TestLogger.stepPassed("Login successful");

    } catch (Exception e) {
        ExtentReportUtils.captureScreenshot(driver, "LoginFailed");
        ExcelUtils.updateTestStatus("LoginTest_Valid", "FAIL");
        TestLogger.stepFailed("Login failed", e);
        throw e;
    }
}
```

---

## 📋 API Testing Support

### Log API Request
```java
ExtentReportUtils.logApiRequest(
    "POST",
    "/api/login",
    "{\"username\":\"test\",\"password\":\"pass\"}"
);
```

### Log API Response
```java
ExtentReportUtils.logApiResponse(
    200,
    "{\"status\":\"success\",\"token\":\"xxx\"}"
);
```

---

## 🗄️ Database Testing Support

### Log Database Query
```java
ExtentReportUtils.logDatabaseQuery(
    "SELECT * FROM users WHERE username='test'",
    "Result: 1 row found"
);
```

---

## 📊 Report Structure

### Report Dashboard
```
┌─────────────────────────────────┐
│ Tests: 50 | Pass: 48 | Fail: 2 │
├─────────────────────────────────┤
│ Pie Chart (Pass/Fail/Skip)      │
├─────────────────────────────────┤
│ System Info                     │
│ Browser: Chrome 120             │
│ OS: Windows 11                  │
│ Environment: QA                 │
└─────────────────────────────────┘
```

### Test Details
```
Test: LoginTest_Valid
Status: PASS ✓
Duration: 2.5 seconds
Steps:
  1. Enter credentials ✓
  2. Click login ✓
  3. Verify login ✓
Screenshots:
  - LoginSuccess_2026-04-04_10-30-45.png
```

---

## 🎯 Workflow

```
Test Execution
    ↓
Extent Report Captures:
  - Test name
  - Status (PASS/FAIL/SKIP)
  - Duration
  - Steps
  - Screenshots
  - Logs
    ↓
HTML Report Generated:
  target/extent-reports/extent.html
    ↓
Report Opens Automatically:
  (via ReportGenerator.java)
    ↓
View in Browser:
  Dashboard + Test Details + Screenshots
```

---

## 🔍 Report Features

| Feature | Status |
|---------|--------|
| Dashboard | ✅ Yes |
| Statistics | ✅ Yes |
| Screenshots | ✅ Yes |
| Detailed Logs | ✅ Yes |
| Charts | ✅ Yes |
| Mobile Responsive | ✅ Yes |
| Custom Themes | ✅ Yes |
| Export | ✅ Yes |

---

## ⚡ Quick Commands

### Run Tests with Extent Report
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### View Report
```
File: target/extent-reports/extent.html
Opens: Automatically in browser
```

### Manual Report Open
```bash
start target/extent-reports/extent.html
```

---

## 📌 Key Points

✅ **Automatic Integration**
- No setup needed beyond configuration files
- Works automatically with tests
- Report generated on test completion

✅ **Screenshot Management**
- Automatic on FAIL
- Optional on PASS
- Embedded in report

✅ **Professional Appearance**
- Beautiful HTML design
- Easy to navigate
- Mobile-friendly

✅ **Comprehensive Information**
- All test details included
- Browser/OS information
- Test data recorded
- Execution timeline

---

## 🚀 Ready to Use!

**Everything is configured and working:**
1. Run tests
2. Report automatically generated
3. Report automatically opens
4. View complete test execution details

**No additional setup required!** ✅

