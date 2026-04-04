# 📊 EXCEL FILES STORAGE - COMPLETE GUIDE

## 📍 Excel File Locations

### Input File - Test Data
**File Name:** `test_data.xlsx`
**Full Path:** `src/test/resources/test_data.xlsx`
**Purpose:** Store test data for reading during test execution
**Usage:** Used by `TestDataManager.java` to load test parameters

### Output File - Test Results
**File Name:** `test_results.xlsx`
**Full Path:** `src/test/resources/test_results.xlsx`
**Purpose:** Store test execution results with pass/fail status
**Usage:** Used by `ExcelUtils.java` to write test results
**Auto-Created:** YES (created automatically on first write if doesn't exist)

---

## 🗂️ Directory Structure

```
SauceDemo_Automation_Cucmber/
│
├── src/
│   └── test/
│       └── resources/
│           ├── test_data.xlsx          ← INPUT FILE
│           ├── test_results.xlsx       ← OUTPUT FILE (auto-created)
│           ├── config.properties
│           ├── extent.properties
│           ├── extent-config.xml
│           ├── feature/
│           │   └── SauceDemo.feature
│           ├── screenshots/            ← Auto-created on failure
│           └── ...other resources...
│
├── logs/                               ← Auto-created test logs
│
├── target/
│   ├── extent-reports/                 ← Extent report output
│   │   └── extent-report.html
│   ├── cucumber-reports/               ← Cucumber report output
│   │   └── cucumber.html
│   └── ...build output...
│
└── ...other files...
```

---

## 📝 How to Create Test Data Excel File

### Step 1: Create test_data.xlsx

**Location:** `src/test/resources/test_data.xlsx`

**Create manually:**
1. Open Excel / Google Sheets
2. Create file: `test_data.xlsx`
3. Save to: `src/test/resources/`

### Step 2: Add Test Data Sheets

**Example Sheet: LoginTests**
```
┌─────────────────┬──────────────┬─────────────┬──────────────────┐
│ TestCase        │ Username     │ Password    │ Expected_Result  │
├─────────────────┼──────────────┼─────────────┼──────────────────┤
│ LoginTest_Valid │ standard_user│ secret_sauce│ Login Success    │
│ LoginTest_Invalid│ invalid_user│ wrong_pass  │ Login Failed     │
├─────────────────┼──────────────┼─────────────┼──────────────────┤
```

**Example Sheet: CartTests**
```
┌──────────────────────┬──────────────────┬──────────┐
│ TestCase             │ Product          │ Quantity │
├──────────────────────┼──────────────────┼──────────┤
│ AddToCart_OneProduct │ Sauce Labs Backpack│ 1       │
│ AddToCart_Multiple   │ Sauce Labs Backpack│ 2       │
├──────────────────────┼──────────────────┼──────────┤
```

---

## 🎯 Usage in Tests

### Load Test Data
```java
// In your test or @Before hook
TestDataManager.loadAllTestData();

// Get test data
String username = TestDataManager.getTestData(
    "LoginTests",
    "LoginTest_Valid",
    "Username"
);
// Returns: "standard_user"
```

### Write Test Results
```java
// After test execution
ExcelUtils.writeTestResult(
    "LoginTest_Valid",
    "Username",
    "standard_user",
    "PASS"  // Green cell
);

// Multiple results at once
Map<String, String> results = new HashMap<>();
results.put("Username_Used", "standard_user");
results.put("Password_Used", "secret_sauce");

ExcelUtils.writeMultipleResults(
    "LoginTest_Valid",
    results,
    "PASS"
);
```

---

## 📊 Test Results Excel Structure

### Auto-Generated Columns
```
┌──────────────┬───────────┬────────┬──────────────────┐
│ TestCase     │ Data Used │ Status │ Timestamp        │
├──────────────┼───────────┼────────┼──────────────────┤
│ LoginTest_V  │ standard  │ PASS ✓ │ 2026-04-04 10:30 │
│ LoginTest_I  │ invalid   │ FAIL ✗ │ 2026-04-04 10:35 │
└──────────────┴───────────┴────────┴──────────────────┘

✓ PASS = Green background
✗ FAIL = Red background
```

### Dynamic Columns
```
When you write:
ExcelUtils.writeTestResult("TestName", "NewColumn", "Value", "PASS");

It automatically:
1. Creates column if doesn't exist
2. Creates row if doesn't exist
3. Adds color formatting
4. Updates headers
```

---

## 💡 Key Points

✅ **test_data.xlsx** (INPUT)
- Created manually by you
- Store test data here
- Read-only during test execution
- Location: `src/test/resources/test_data.xlsx`

✅ **test_results.xlsx** (OUTPUT)
- Created automatically on first write
- Test results written here
- Auto-formatted with colors
- Location: `src/test/resources/test_results.xlsx`

✅ **Color Coding**
- PASS = Green (#92D050)
- FAIL = Red (#FF0000)
- Automatic formatting

✅ **Auto-Creation**
- If test_results.xlsx doesn't exist, it's created
- Columns auto-created as needed
- Headers formatted automatically

---

## 🚀 Quick Setup

### 1. Create Test Data File
```
Create: src/test/resources/test_data.xlsx
Add: Test data sheets with columns: TestCase, Column1, Column2, ...
```

### 2. Load in Test Setup
```java
@Before
public void setup() {
    TestDataManager.loadAllTestData();
}
```

### 3. Use in Tests
```java
String data = TestDataManager.getTestData("SheetName", "TestcaseName", "ColumnName");
```

### 4. Write Results
```java
ExcelUtils.writeTestResult("TestcaseName", "ColumnName", "Value", "PASS");
```

### 5. View Results
```
Open: src/test/resources/test_results.xlsx
See: Color-coded results (Green=PASS, Red=FAIL)
```

---

## 📋 File Paths Summary

```
INPUT:
  src/test/resources/test_data.xlsx        → Create manually

OUTPUT:
  src/test/resources/test_results.xlsx     → Auto-created

SCREENSHOTS:
  src/test/resources/screenshots/          → Auto-created on failure

LOGS:
  logs/test_execution_*.log                → Auto-created

REPORTS:
  target/extent-reports/extent-report.html
  target/cucumber-reports/cucumber.html
```

---

## ✅ Ready to Use!

**Excel Files Storage:**
- ✅ Input: `src/test/resources/test_data.xlsx`
- ✅ Output: `src/test/resources/test_results.xlsx`
- ✅ Both managed automatically
- ✅ Color coding automatic
- ✅ Easy to read and maintain

