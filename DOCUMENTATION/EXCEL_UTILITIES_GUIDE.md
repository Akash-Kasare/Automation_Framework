# 📊 Excel Utilities - Complete Guide

## Overview

Two powerful Excel utilities have been created for managing test data and test results:

1. **ExcelUtils.java** - For writing test results with pass/fail color coding
2. **TestDataManager.java** - For reading and caching test data efficiently

---

## 🎯 ExcelUtils - Test Results Management

### Features

✅ **Write Test Results**
- Automatic file creation if not exists
- Supports multiple sheets
- Color coding (Green for PASS, Red for FAIL)
- Timestamp recording
- Multiple data points per testcase

✅ **Color Coding**
- **PASS** - Green background with dark green text
- **FAIL** - Red background with white text
- Automatic formatting

✅ **HashMap-Based Access**
- Structure: TestcaseName -> {ColumnName -> Value}
- Fast lookup
- Easy management

### Methods

#### 1. Write Single Test Result
```java
// Simple write
ExcelUtils.writeTestResult(
    "LoginTest_Valid_Credentials",  // Test case name
    "Username",                      // Column name
    "standard_user",                 // Data value
    "PASS"                          // Status (PASS/FAIL)
);
```

#### 2. Write Multiple Results at Once
```java
Map<String, String> testData = new HashMap<>();
testData.put("Username", "standard_user");
testData.put("Password", "secret_sauce");
testData.put("Expected_Result", "Login Success");

ExcelUtils.writeMultipleResults(
    "LoginTest_Valid_Credentials",
    testData,
    "PASS"
);
```

#### 3. Update Test Status
```java
ExcelUtils.updateTestStatus("LoginTest_Valid_Credentials", "PASS");
```

#### 4. Read Data from Excel
```java
String value = ExcelUtils.readCellValue(
    "src/test/resources/test_results.xlsx",
    "TestResults",
    "LoginTest_Valid_Credentials",
    "Username"
);
```

### Excel File Structure

The results Excel file is automatically created with this structure:

```
Column: TestCase | Data Used | Status | Timestamp | Username | Password | ...
Row 1:  Headers
Row 2:  LoginTest_Valid | standard_user | PASS | 2026-04-04 10:30:45 | standard_user | secret_sauce
Row 3:  LoginTest_Invalid | invalid_user | FAIL | 2026-04-04 10:32:15 | invalid_user | secret_sauce
```

---

## 🔍 TestDataManager - Test Data Management

### Features

✅ **HashMap Caching**
- Loads all test data into memory
- Fast access without file I/O
- Structure: SheetName -> TestcaseName -> {ColumnName -> Value}

✅ **Multiple Sheets**
- Support for multiple data sheets
- Different test data for different scenarios
- Easy organization

✅ **Smart Data Loading**
- Lazy loading (loads on first access)
- Caching for performance
- Memory efficient

### Methods

#### 1. Load All Test Data
```java
// Load all data into memory (HashMap)
TestDataManager.loadAllTestData();
```

#### 2. Get Test Data for Entire Testcase
```java
// Get all data for one testcase
Map<String, String> testData = TestDataManager.getTestData(
    "LoginTests",           // Sheet name
    "LoginTest_Valid"       // Testcase name
);

// Result: {Username -> standard_user, Password -> secret_sauce, ...}
```

#### 3. Get Single Value
```java
// Get specific column value
String username = TestDataManager.getTestData(
    "LoginTests",
    "LoginTest_Valid",
    "Username"
);
// Result: standard_user
```

#### 4. Get Value with Default
```java
String value = TestDataManager.getTestData(
    "LoginTests",
    "LoginTest_Valid",
    "ColumnName",
    "DefaultValue"
);
```

#### 5. Get All Testcases
```java
Set<String> testcaseNames = TestDataManager.getTestcaseNames("LoginTests");
// Result: {LoginTest_Valid, LoginTest_Invalid, LoginTest_Locked, ...}
```

### HashMap Structure

```
TestDataManager.cachedData = {
    "LoginTests" -> {
        "LoginTest_Valid" -> {
            "Username" -> "standard_user",
            "Password" -> "secret_sauce",
            "Expected_Result" -> "Login Success"
        },
        "LoginTest_Invalid" -> {
            "Username" -> "invalid_user",
            "Password" -> "wrong_password",
            "Expected_Result" -> "Login Failed"
        }
    },
    "CartTests" -> {
        "AddToCart_SingleProduct" -> {
            "Product" -> "Backpack",
            "Quantity" -> "1",
            "Expected_Price" -> "$29.99"
        }
    }
}
```

---

## 📋 File Locations

### Excel Files Location
```
src/test/resources/test_data.xlsx        (Input - Test Data)
src/test/resources/test_results.xlsx     (Output - Test Results)
```

### Java Files Location
```
src/test/java/utils/ExcelUtils.java            (Results Management)
src/test/java/utils/TestDataManager.java       (Test Data Management)
```

---

## 💡 Usage Example in Test Steps

### Example 1: Login Test with Data and Results

```java
@When("User logs in with test data from Excel")
public void userLogsInWithExcelData() {
    TestLogger.stepStart("Login with test data from Excel");
    try {
        // Load test data
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
        
        // Enter credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);
        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        
        // Log action
        TestLogger.action("LoginForm", "EnterCredentials", 
            "Username: " + username);
        
        // Click login
        driver.findElement(By.id("login-button")).click();
        
        // Write results to Excel
        Map<String, String> results = new HashMap<>();
        results.put("Username_Used", username);
        results.put("Password_Used", password);
        results.put("Action", "Login");
        
        ExcelUtils.writeMultipleResults(
            "LoginTest_Valid",
            results,
            "PASS"
        );
        
        TestLogger.stepPassed("Login successful with data from Excel");
        
    } catch (Exception e) {
        // Write failure to Excel
        ExcelUtils.writeTestResult(
            "LoginTest_Valid",
            "Status",
            "FAILED",
            "FAIL"
        );
        TestLogger.stepFailed("Login failed", e);
        throw e;
    }
}
```

### Example 2: Complete Test with Data Validation

```java
@When("User adds product to cart with validation")
public void userAddsProductWithValidation() {
    TestLogger.stepStart("Add product to cart");
    try {
        // Get test data
        Map<String, String> testData = TestDataManager.getTestData(
            "CartTests",
            "AddToCart_SingleProduct"
        );
        
        String product = testData.get("Product");
        String quantity = testData.get("Quantity");
        String expectedPrice = testData.get("Expected_Price");
        
        // Perform action
        findProduct(product).click();
        setQuantity(quantity);
        clickAddToCart();
        
        // Verify
        String actualPrice = getCartPrice();
        boolean priceMatches = actualPrice.equals(expectedPrice);
        
        // Write results
        Map<String, String> results = new HashMap<>();
        results.put("Product", product);
        results.put("Quantity_Used", quantity);
        results.put("Expected_Price", expectedPrice);
        results.put("Actual_Price", actualPrice);
        results.put("Price_Match", String.valueOf(priceMatches));
        
        String status = priceMatches ? "PASS" : "FAIL";
        ExcelUtils.writeMultipleResults(
            "AddToCart_SingleProduct",
            results,
            status
        );
        
        TestLogger.stepPassed("Product added successfully");
        
    } catch (Exception e) {
        ExcelUtils.updateTestStatus("AddToCart_SingleProduct", "FAIL");
        TestLogger.stepFailed("Failed to add product", e);
        throw e;
    }
}
```

---

## 🎯 Complete Workflow

### Step 1: Prepare Test Data Excel
Create `src/test/resources/test_data.xlsx` with sheets:
```
Sheet: LoginTests
  TestCase | Username | Password | Expected_Result
  LoginTest_Valid | standard_user | secret_sauce | Login Success
  LoginTest_Invalid | invalid_user | wrong_pass | Login Failed

Sheet: CartTests
  TestCase | Product | Quantity | Expected_Price
  AddToCart_SingleProduct | Backpack | 1 | $29.99
```

### Step 2: Load Test Data in @Before Hook
```java
@Before
public void setup() {
    TestDataManager.loadAllTestData();
    // All data now loaded in HashMap for fast access
}
```

### Step 3: Use Data in Tests
```java
// Get data from HashMap
String username = TestDataManager.getTestData(
    "LoginTests",
    "LoginTest_Valid",
    "Username"
);
```

### Step 4: Write Results to Excel
```java
// Write results with color coding
ExcelUtils.writeTestResult(
    "LoginTest_Valid",
    "Username",
    username,
    "PASS"  // Green cell
);
```

### Step 5: View Results
```
src/test/resources/test_results.xlsx
- Green cells for PASSED tests
- Red cells for FAILED tests
- Timestamp and data used recorded
```

---

## 🚀 Advanced Features

### Parameterized Tests with Multiple Data Sets

```java
Set<String> testcases = TestDataManager.getTestcaseNames("LoginTests");

for (String testcaseName : testcases) {
    Map<String, String> testData = TestDataManager.getTestData(
        "LoginTests",
        testcaseName
    );
    
    // Run test with data
    runLoginTest(testData);
    
    // Write result
    ExcelUtils.updateTestStatus(testcaseName, testPassed ? "PASS" : "FAIL");
}
```

### Dynamic Column Addition

```java
// Write to new column automatically
ExcelUtils.writeTestResult(
    "LoginTest_Valid",
    "New_Column_Name",  // Will be created if not exists
    "New Value",
    "PASS"
);
```

### Clear Results for Fresh Run

```java
// Clear all results before test run
ExcelUtils.clearResults();

// Or clear cache
TestDataManager.clearCache();
```

---

## 📊 Excel Output Example

### Test Results File (Automatic Color Coding)

```
╔════════════════════════════════════════════════════════════╗
║ TestCase              │ Data Used     │ Status │ Timestamp  ║
║─────────────────────────────────────────────────────────────║
║ LoginTest_Valid       │ standard_user │ PASS ▓ │ 2026-04-04 ║
║ LoginTest_Invalid     │ invalid_user  │ FAIL ▓ │ 2026-04-04 ║
║ AddToCart_Product     │ Backpack      │ PASS ▓ │ 2026-04-04 ║
║ Checkout_Complete     │ All Data      │ FAIL ▓ │ 2026-04-04 ║
╚════════════════════════════════════════════════════════════╝

Green ▓ = PASS (Green background with dark text)
Red ▓ = FAIL (Red background with white text)
```

---

## ✅ Key Benefits

✅ **Performance**
- HashMap caching eliminates repeated file I/O
- Fast data lookup
- Efficient memory usage

✅ **Flexibility**
- Easy to add new columns
- Support for multiple sheets
- Dynamic data handling

✅ **Visibility**
- Color-coded results
- Timestamp recording
- Data used is documented

✅ **Maintainability**
- Centralized test data
- Easy to update
- Reusable across tests

---

## 🔧 Configuration

### Add to Hooks for Setup
```java
@Before
public void setupTestData() {
    TestDataManager.loadAllTestData();
    ExcelUtils.clearResults(); // Optional: fresh start
    TestLogger.info("Test data loaded from Excel");
}
```

### Add to Hooks for Teardown
```java
@After
public void cleanupTestData() {
    TestDataManager.clearCache();
    TestLogger.info("Test data cache cleared");
}
```

---

**Everything is set up and ready to use!** 🎉

See individual file documentation for API details.

