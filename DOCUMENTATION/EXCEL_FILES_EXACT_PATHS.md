# 📊 EXCEL FILES - EXACT LOCATIONS & PATHS

## 🎯 EXACT PATHS (Copy-Paste Ready)

### INPUT FILE - Test Data
```
Relative Path:    src/test/resources/test_data.xlsx
Absolute Path:    C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\test_data.xlsx
File Name:        test_data.xlsx
Folder:           src/test/resources/
Status:           Create Manually
```

### OUTPUT FILE - Test Results
```
Relative Path:    src/test/resources/test_results.xlsx
Absolute Path:    C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\test_results.xlsx
File Name:        test_results.xlsx
Folder:           src/test/resources/
Status:           Auto-Created on First Write
```

---

## 📁 PROJECT DIRECTORY STRUCTURE

```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
│
├── src\
│   ├── main\
│   │   └── ...
│   │
│   └── test\
│       ├── java\
│       │   ├── factory\
│       │   ├── steps\
│       │   ├── runner\
│       │   ├── listeners\
│       │   └── utils\
│       │       ├── ExcelUtils.java
│       │       └── TestDataManager.java
│       │
│       └── resources\                    ← EXCEL FILES HERE
│           ├── test_data.xlsx            ← CREATE THIS
│           ├── test_results.xlsx         ← AUTO-CREATED
│           ├── config.properties
│           ├── extent.properties
│           ├── extent-config.xml
│           ├── feature\
│           │   └── SauceDemo.feature
│           ├── screenshots\              ← Auto-created
│           └── other resources...
│
├── logs\                                 ← Auto-created
│
├── target\
│   ├── extent-reports\
│   │   └── extent-report.html
│   └── cucumber-reports\
│       └── cucumber.html
│
└── ...other files...
```

---

## 🔧 JAVA CLASS REFERENCES

### ExcelUtils.java
**Location:** `src/test/java/utils/ExcelUtils.java`
**Line 20:** `private static final String RESULTS_FILE = "src/test/resources/test_results.xlsx";`
**Purpose:** Writes test results to Excel

### TestDataManager.java
**Location:** `src/test/java/utils/TestDataManager.java`
**Line 18:** `private static final String TEST_DATA_FILE = "src/test/resources/test_data.xlsx";`
**Purpose:** Reads test data from Excel

---

## 📊 FILE INFORMATION

### test_data.xlsx
```
PURPOSE:     Input test data storage
LOCATION:    src/test/resources/test_data.xlsx
CREATED BY:  You (manually)
USED BY:     TestDataManager.java
OPERATION:   Read-only during tests
FORMAT:      Excel (.xlsx)
SHEETS:      LoginTests, CartTests, CheckoutTests, etc.
COLUMNS:     TestCase, Username, Password, Product, etc.
```

### test_results.xlsx
```
PURPOSE:     Output test results storage
LOCATION:    src/test/resources/test_results.xlsx
CREATED BY:  ExcelUtils.java (automatic)
USED BY:     Test framework, testers
OPERATION:   Write test results
FORMAT:      Excel (.xlsx)
AUTO-COLOR:  Green (PASS), Red (FAIL)
TIMESTAMP:   Added automatically
COLUMNS:     Auto-created as needed
```

---

## 🎯 HOW TO FIND THEM

### In IDE (Eclipse/IntelliJ)
```
1. Open Project Explorer
2. Navigate to: SauceDemo_Automation_Cucmber
3. Expand: src → test → resources
4. You'll see:
   - test_data.xlsx (if you created it)
   - test_results.xlsx (after first test run)
```

### In File Explorer
```
Windows File Explorer:
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\

Files:
- test_data.xlsx
- test_results.xlsx
- (other resources...)
```

### Command Line
```bash
# Check if files exist
dir "C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\*.xlsx"

# Open folder
explorer "C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\"

# Create test_data.xlsx (if needed)
# Use Excel or create manually
```

---

## ✅ QUICK CHECKLIST

- [ ] Know location: `src/test/resources/`
- [ ] Create: `test_data.xlsx` manually
- [ ] Place in: `src/test/resources/test_data.xlsx`
- [ ] Add test data sheets
- [ ] Run tests
- [ ] Find results: `src/test/resources/test_results.xlsx`
- [ ] View color-coded results (Green=PASS, Red=FAIL)

---

## 🚀 USAGE FLOW

```
1. CREATE MANUALLY
   └─ C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
      └─ src\test\resources\
         └─ test_data.xlsx ← Create here

2. ADD TEST DATA
   └─ Sheet: LoginTests
      └─ TestCase | Username | Password

3. RUN TESTS
   └─ Tests read from test_data.xlsx
   └─ Tests write to test_results.xlsx (auto-created)

4. VIEW RESULTS
   └─ C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
      └─ src\test\resources\
         └─ test_results.xlsx ← Open to see results
```

---

## 📍 SUMMARY

**Input Excel:**
```
File: test_data.xlsx
Path: src/test/resources/test_data.xlsx
Absolute: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\test_data.xlsx
Created: You (manually)
```

**Output Excel:**
```
File: test_results.xlsx
Path: src/test/resources/test_results.xlsx
Absolute: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\test_results.xlsx
Created: Automatically
```

**Both are in the same folder:** `src/test/resources/`

---

**You're all set!** 📊✅

