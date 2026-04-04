# 📊 EXCEL UTILITIES - QUICK REFERENCE

## Quick Start (5 Minutes)

### File Locations
```
Test Data Input:        src/test/resources/test_data.xlsx
Test Results Output:    src/test/resources/test_results.xlsx
Excel Utils:            src/test/java/utils/ExcelUtils.java
Data Manager:           src/test/java/utils/TestDataManager.java
```

---

## 🎯 Most Used Methods

### Write Test Result (Color Coded)
```java
// Green cell for PASS
ExcelUtils.writeTestResult(
    "LoginTest_Valid",      // Test case name
    "Username",             // Column name
    "standard_user",        // Data value
    "PASS"                  // Status (Green)
);

// Red cell for FAIL
ExcelUtils.writeTestResult(
    "LoginTest_Invalid",
    "Username",
    "invalid_user",
    "FAIL"                  // Status (Red)
);
```

### Write Multiple Results at Once
```java
Map<String, String> data = new HashMap<>();
data.put("Username", "standard_user");
data.put("Password", "secret_sauce");
data.put("Browser", "Chrome");

ExcelUtils.writeMultipleResults(
    "LoginTest_Valid",
    data,
    "PASS"
);
```

### Read Test Data from Excel
```java
// Load all data into memory
TestDataManager.loadAllTestData();

// Get specific value
String username = TestDataManager.getTestData(
    "LoginTests",           // Sheet name
    "LoginTest_Valid",      // Test case name
    "Username"              // Column name
);

// Get all data for testcase
Map<String, String> testData = TestDataManager.getTestData(
    "LoginTests",
    "LoginTest_Valid"
);
```

### Update Test Status
```java
// Simple status update
ExcelUtils.updateTestStatus(
    "LoginTest_Valid",
    "PASS"
);
```

---

## 📋 Step-by-Step Example

### 1. Create Test Data Excel File
```
Create: src/test/resources/test_data.xlsx

Sheet: LoginTests
┌─────────────────────────────────────┐
│ TestCase        │ Username │ Password │
├─────────────────────────────────────┤
│ LoginTest_Valid │ standard_user │ secret_sauce │
│ LoginTest_Invalid │ invalid │ wrong │
└─────────────────────────────────────┘
```

### 2. Load Data in Test Setup
```java
@Before
public void setup() {
    TestDataManager.loadAllTestData();
}
```

### 3. Use Data in Test
```java
@When("User logs in")
public void userLogsIn() {
    String username = TestDataManager.getTestData(
        "LoginTests",
        "LoginTest_Valid",
        "Username"
    );
    
    String password = TestDataManager.getTestData(
        "LoginTests",
        "LoginTest_Valid",
        "Password"
    );
    
    // Use data...
    enterUsername(username);
    enterPassword(password);
}
```

### 4. Write Results to Excel
```java
@Then("Login should succeed")
public void loginShouldSucceed() {
    if (isLoggedIn()) {
        // Green cell
        ExcelUtils.writeTestResult(
            "LoginTest_Valid",
            "Result",
            "Login Successful",
            "PASS"
        );
    } else {
        // Red cell
        ExcelUtils.writeTestResult(
            "LoginTest_Valid",
            "Result",
            "Login Failed",
            "FAIL"
        );
    }
}
```

### 5. View Results
```
Open: src/test/resources/test_results.xlsx
- Green cells = PASS
- Red cells = FAIL
- Timestamp = When test ran
- Data = What was used
```

---

## 🎨 Color Coding

### PASS (Green Cell)
```
Background: Light Green (#92D050)
Text: Dark Green
Font: Normal
```

### FAIL (Red Cell)
```
Background: Red (#FF0000)
Text: White
Font: Bold
```

---

## 📊 Excel Output Structure

### Test Results File
```
┌──────────────┬───────────┬────────┬──────────────────┬──────────┐
│ TestCase     │ Data Used │ Status │ Timestamp        │ Username │
├──────────────┼───────────┼────────┼──────────────────┼──────────┤
│ LoginTest_V  │ standard  │ PASS ✓ │ 2026-04-04 10:30 │ standard │
│ LoginTest_I  │ invalid   │ FAIL ✗ │ 2026-04-04 10:35 │ invalid  │
└──────────────┴───────────┴────────┴──────────────────┴──────────┘

✓ Green = PASS
✗ Red = FAIL
```

---

## 💡 Common Patterns

### Pattern 1: Test with Assertions
```java
@When("User performs action")
public void userPerformsAction() {
    // Get test data
    String expectedResult = TestDataManager.getTestData(
        "Tests", "TestCase", "Expected"
    );
    
    // Perform action
    String actualResult = performAction();
    
    // Write result
    boolean passed = actualResult.equals(expectedResult);
    ExcelUtils.writeTestResult(
        "TestCase",
        "Result",
        actualResult,
        passed ? "PASS" : "FAIL"
    );
}
```

### Pattern 2: Bulk Test Execution
```java
@When("Run all tests from data file")
public void runAllTests() {
    Set<String> testcases = TestDataManager.getTestcaseNames("Tests");
    
    for (String testcase : testcases) {
        Map<String, String> data = TestDataManager.getTestData(
            "Tests", testcase
        );
        
        boolean result = runTest(data);
        
        ExcelUtils.updateTestStatus(
            testcase,
            result ? "PASS" : "FAIL"
        );
    }
}
```

### Pattern 3: Data-Driven Test
```java
@When("Login with multiple credentials")
public void loginWithMultipleCredentials() {
    Set<String> testcases = TestDataManager.getTestcaseNames("LoginTests");
    
    for (String testcaseName : testcases) {
        String username = TestDataManager.getTestData(
            "LoginTests", testcaseName, "Username"
        );
        String password = TestDataManager.getTestData(
            "LoginTests", testcaseName, "Password"
        );
        
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        
        boolean passed = isLoggedIn();
        ExcelUtils.updateTestStatus(testcaseName, passed ? "PASS" : "FAIL");
    }
}
```

---

## ⚡ Quick Commands

### Create Test Data Excel
```
1. Create file: src/test/resources/test_data.xlsx
2. Add sheets: LoginTests, CartTests, etc.
3. Add headers: TestCase, Column1, Column2, ...
4. Add test data rows
```

### Load Data
```java
TestDataManager.loadAllTestData();
```

### Get Data
```java
TestDataManager.getTestData("SheetName", "TestcaseName", "ColumnName");
```

### Write Result
```java
ExcelUtils.writeTestResult("TestcaseName", "ColumnName", "Value", "PASS");
```

### View Results
```
File: src/test/resources/test_results.xlsx
Open in Excel to see color-coded results
```

---

## 🔄 Workflow

```
1. Prepare Test Data
   ↓
2. Load Data (HashMap)
   ↓
3. Run Tests with Data
   ↓
4. Write Results (Color Coded)
   ↓
5. View Excel Results
```

---

## 📋 Method Reference

### ExcelUtils Methods
| Method | Purpose |
|--------|---------|
| `writeTestResult()` | Write single result with color |
| `writeMultipleResults()` | Write multiple results at once |
| `updateTestStatus()` | Update status at end |
| `readCellValue()` | Read data from Excel |
| `readExcelData()` | Read all data from sheet |

### TestDataManager Methods
| Method | Purpose |
|--------|---------|
| `loadAllTestData()` | Load all data into HashMap |
| `getTestData(sheet, testcase)` | Get all data for testcase |
| `getTestData(sheet, testcase, column)` | Get specific value |
| `getTestcaseNames(sheet)` | Get all testcase names |
| `addTestData()` | Add data to HashMap |
| `clearCache()` | Clear cached data |

---

## ✅ Checklist

Before using:
- [ ] Create test_data.xlsx
- [ ] Add test data sheets
- [ ] Add headers (TestCase, columns)
- [ ] Add test data rows
- [ ] Import ExcelUtils
- [ ] Import TestDataManager
- [ ] Load data in @Before hook
- [ ] Use data in tests
- [ ] Write results after tests

---

## 🎯 Real-World Example

### Test Case: Login Functionality

**Test Data (test_data.xlsx):**
```
Sheet: LoginTests
TestCase           │ Username      │ Password     │ Expected
LoginTest_Valid    │ standard_user │ secret_sauce │ Success
LoginTest_Invalid  │ invalid_user  │ wrong_pass   │ Failure
```

**Test Step:**
```java
@When("User logs in with Excel data")
public void userLogsInWithExcelData() {
    // Get data
    String username = TestDataManager.getTestData(
        "LoginTests", "LoginTest_Valid", "Username"
    );
    String password = TestDataManager.getTestData(
        "LoginTests", "LoginTest_Valid", "Password"
    );
    
    // Perform action
    loginForm.enterUsername(username);
    loginForm.enterPassword(password);
    loginForm.clickLogin();
    
    // Verify and write result
    boolean loginSuccess = isLoggedIn();
    ExcelUtils.writeMultipleResults(
        "LoginTest_Valid",
        Map.of(
            "Username_Used", username,
            "Password_Used", password,
            "Result", loginSuccess ? "Success" : "Failure"
        ),
        loginSuccess ? "PASS" : "FAIL"
    );
}
```

**Result in test_results.xlsx:**
```
TestCase           │ Username_Used │ Password_Used │ Result  │ Status
LoginTest_Valid    │ standard_user │ secret_sauce  │ Success │ PASS ✓

✓ = Green background (PASS)
```

---

**Ready to use! Start with ExcelUtils.writeTestResult() method.** 🚀

