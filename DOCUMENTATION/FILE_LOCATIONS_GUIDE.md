# 📋 COMPLETE PROJECT FILE LOCATIONS & STRUCTURE GUIDE

## Quick Navigation for All Project Files

This document shows the **exact location of EVERY file** in the project so you can easily find and modify anything.

---

## 🎯 PROJECT ROOT DIRECTORY

```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
```

---

## 📁 MAIN FOLDER STRUCTURE

```
SauceDemo_Automation_Cucmber/
│
├── 📚 DOCUMENTATION/ ← ALL .MD FILES HERE (21 files)
│   ├── INDEX.md
│   ├── README.md
│   ├── COMMAND_QUICK_REFERENCE.md
│   ├── TEST_EXECUTION_WITH_UI.md
│   └── ... (18 more documentation files)
│
├── 📦 src/ ← SOURCE CODE
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   ├── factory/
│       │   ├── steps/
│       │   ├── runner/
│       │   └── utils/
│       │
│       └── resources/
│           ├── feature/
│           ├── config.properties
│           └── screenshots/
│
├── 📊 logs/ ← TEST LOGS (Auto-created)
│   └── test_execution_YYYY-MM-DD_HH-mm-ss.log
│
├── 🎯 target/ ← BUILD OUTPUT (Auto-created)
│   ├── cucumber-reports/
│   │   ├── cucumber.html (REPORT)
│   │   ├── cucumber.json
│   │   └── cucumber.xml
│   │
│   └── classes/ (Compiled files)
│
├── 📄 pom.xml ← MAVEN CONFIGURATION
├── .gitignore
├── .idea/ ← IDE FILES
└── .mvn/ ← MAVEN WRAPPER

```

---

## 🔍 EXACT FILE LOCATIONS

### DOCUMENTATION FOLDER (All .md files)
```
📂 DOCUMENTATION/
   📄 Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\DOCUMENTATION\

   Contains 21 files:
   ├── INDEX.md
   ├── README.md
   ├── COMMAND_QUICK_REFERENCE.md
   ├── TEST_EXECUTION_WITH_UI.md
   ├── UI_EXECUTION_SETUP_COMPLETE.md
   ├── DOCUMENTATION_INDEX.md
   ├── FRAMEWORK_VISUAL_OVERVIEW.md
   ├── COMPLETE_IMPLEMENTATION_SUMMARY.md
   ├── STEP_DEFINITIONS_README.md
   ├── STEP_DEFINITIONS_QUICK_REFERENCE.md
   ├── TEST_EXECUTION_GUIDE.md
   ├── LOGGER_IMPLEMENTATION_GUIDE.md
   ├── LOGGER_QUICK_REFERENCE.md
   ├── LOGGER_IMPLEMENTATION_SUMMARY.md
   ├── LOGGER_FINAL_STATUS.md
   ├── LOGGER_COMPLETION_CHECKLIST.md
   ├── ERRORS_RESOLVED.md
   ├── PROJECT_COMPLETION_SUMMARY.md
   ├── COMPLETE_FILE_MANIFEST.md
   ├── DELIVERY_CHECKLIST.md
   └── IMPLEMENTATION_COMPLETE.md
```

---

### CONFIGURATION FILES

#### pom.xml (Maven Build File)
```
📄 pom.xml
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\pom.xml
   
   What it contains:
   ├── Project metadata (groupId, artifactId, version)
   ├── Dependencies (Selenium, Cucumber, Log4j, etc.)
   ├── Plugin configuration (maven-compiler, maven-surefire)
   ├── Report generation plugin (exec-maven-plugin)
   └── Build settings
   
   Modify when:
   - Adding new dependencies
   - Changing compiler version
   - Changing Java version
   - Adding new plugins
```

#### config.properties (Test Configuration)
```
📄 config.properties
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\config.properties
   
   What it contains:
   ├── browser=chrome           (Browser type)
   ├── os=windows              (Operating system)
   ├── execution_env=local     (Execution environment)
   ├── app_url=...             (Application URL)
   ├── implicit_wait=10        (Timeout values)
   ├── page_load_timeout=20
   └── explicit_wait=15
   
   Modify when:
   - Changing browser
   - Changing timeouts
   - Changing test URL
   - Changing execution environment
```

---

### JAVA SOURCE CODE FILES

#### Factory Package (WebDriver Setup)
```
📂 factory/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\factory\

   📄 BaseClass.java (115 lines)
      - WebDriver initialization
      - Configuration loading
      - Chrome driver setup
      - Timeout management
      - Driver cleanup
```

#### Steps Package (Test Steps)
```
📂 steps/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\steps\

   📄 SauceDemoSteps.java (560+ lines)
      - 70+ step definitions
      - Test actions and assertions
      - Browser interactions
      - With comprehensive logging
      
   📄 Hooks.java (88 lines)
      - @Before hook (setup)
      - @After hook (cleanup)
      - Screenshot capture
      - Test logging
```

#### Runner Package (Test Execution)
```
📂 runner/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\runner\

   📄 TestRunner.java (25 lines)
      - Cucumber configuration
      - Feature file path
      - Report plugin setup
      - Tag filtering
      
   📄 ReportGenerator.java (95 lines)
      - Auto-opens HTML reports
      - Cross-platform compatible
      - Report file handling
```

#### Utils Package (Utilities)
```
📂 utils/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\utils\

   📄 TestLogger.java (330+ lines)
      - Logging utility
      - Step logging (start, passed, failed)
      - Action logging
      - Scenario logging
      - File and console output
```

---

### FEATURE FILES (Test Scenarios)

#### Feature Files Directory
```
📂 feature/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\feature\

   📄 SauceDemo.feature (463 lines)
      - 50+ test scenarios
      - All feature tests
      - Login, Product, Cart, Checkout, etc.
      - Tagged for categorization
```

---

### RESOURCE FILES

```
📂 src/test/resources/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\

   📄 config.properties
      Location: src/test/resources/config.properties
      Contains: All test configuration
      
   📂 feature/
      Location: src/test/resources/feature/
      Contains: SauceDemo.feature (all test scenarios)
      
   📂 screenshots/
      Location: src/test/resources/screenshots/
      Contains: Failure screenshots (auto-created)
```

---

### BUILD OUTPUT FILES (Auto-created)

```
📂 target/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\target\

   📂 cucumber-reports/
      📄 cucumber.html - MAIN REPORT (Opens automatically)
      📄 cucumber.json - JSON format
      📄 cucumber.xml - JUnit format
      
   📂 extent-reports/
      📄 extent.html - Advanced report (if created)
      
   📂 classes/
      Contains: Compiled Java classes
```

---

### LOG FILES (Auto-created)

```
📂 logs/
   Full Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\logs\

   📄 test_execution_YYYY-MM-DD_HH-mm-ss.log
      - Created after each test run
      - Contains detailed execution logs
      - Step-by-step execution details
      - Timing information
      - Error details with stack traces
```

---

## 📊 FILE SUMMARY TABLE

| File/Folder | Location | Purpose | Modify? |
|-------------|----------|---------|---------|
| **pom.xml** | Root | Maven config | Yes (add dependencies) |
| **config.properties** | src/test/resources/ | Test config | Yes (browser, timeout) |
| **BaseClass.java** | src/test/java/factory/ | Driver setup | No (unless custom) |
| **SauceDemoSteps.java** | src/test/java/steps/ | Step definitions | Yes (add steps) |
| **Hooks.java** | src/test/java/steps/ | Test lifecycle | No (unless custom) |
| **TestRunner.java** | src/test/java/runner/ | Test execution config | No (unless custom) |
| **ReportGenerator.java** | src/test/java/runner/ | Report opening | No |
| **TestLogger.java** | src/test/java/utils/ | Logging | No |
| **SauceDemo.feature** | src/test/resources/feature/ | Test scenarios | Yes (add tests) |
| **DOCUMENTATION/** | Root | All .md files | No (reference only) |
| **target/** | Auto-created | Build output | Auto-created |
| **logs/** | Auto-created | Test logs | Auto-created |
| **screenshots/** | Auto-created | Failure images | Auto-created |

---

## 🎯 COMMON MODIFICATIONS

### To Change Browser
**File:** `config.properties`
**Location:** `src/test/resources/config.properties`
**Change:** `browser=chrome` → `browser=edge`

### To Change Timeout
**File:** `config.properties`
**Location:** `src/test/resources/config.properties`
**Change:** `implicit_wait=10` → `implicit_wait=20`

### To Change Application URL
**File:** `config.properties`
**Location:** `src/test/resources/config.properties`
**Change:** `app_url=https://www.saucedemo.com/` → your URL

### To Add New Dependency
**File:** `pom.xml`
**Location:** Root directory
**Add:** `<dependency>` section in `<dependencies>`

### To Add New Test Scenario
**File:** `SauceDemo.feature`
**Location:** `src/test/resources/feature/`
**Add:** New Scenario with steps

### To Add New Step Definition
**File:** `SauceDemoSteps.java`
**Location:** `src/test/java/steps/`
**Add:** New @When/@Then/@Given method

---

## 🚀 EXECUTION COMMANDS

### Commands with Correct Paths
```bash
# From project root directory
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Run tests
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Open report manually
start target\cucumber-reports\cucumber.html

# Open documentation
explorer DOCUMENTATION

# View logs
type logs\test_execution_*.log

# Open configuration file
code src\test\resources\config.properties

# View pom.xml
code pom.xml
```

---

## 📍 QUICK REFERENCE

### Most Important Files to Know

1. **pom.xml** (Root)
   - Maven configuration
   - All dependencies
   - Plugin setup

2. **config.properties** (src/test/resources/)
   - Browser settings
   - Timeout settings
   - URL configuration

3. **SauceDemoSteps.java** (src/test/java/steps/)
   - All step definitions
   - Test actions
   - Assertions

4. **SauceDemo.feature** (src/test/resources/feature/)
   - All test scenarios
   - Feature definitions
   - Test cases

5. **DOCUMENTATION/** (Root/DOCUMENTATION/)
   - All documentation
   - Navigation guides
   - Quick references

---

## ✅ FILE LOCATION VERIFICATION

### Check if file exists
```bash
# Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Check pom.xml
if exist pom.xml echo "✓ pom.xml found"

# Check config.properties
if exist src\test\resources\config.properties echo "✓ config.properties found"

# Check source files
if exist src\test\java\factory\BaseClass.java echo "✓ BaseClass.java found"

# Check feature files
if exist src\test\resources\feature\SauceDemo.feature echo "✓ SauceDemo.feature found"

# Check documentation
if exist DOCUMENTATION echo "✓ DOCUMENTATION folder found"
```

---

## 🎊 SUMMARY

```
Project Root: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\

Key Locations:
  ✓ Configuration:      src/test/resources/config.properties
  ✓ Java Code:          src/test/java/factory|steps|runner|utils/
  ✓ Test Scenarios:     src/test/resources/feature/SauceDemo.feature
  ✓ Maven Config:       pom.xml (Root)
  ✓ Documentation:      DOCUMENTATION/ (Root)
  ✓ Reports:            target/cucumber-reports/ (Auto-created)
  ✓ Logs:               logs/ (Auto-created)
  ✓ Screenshots:        src/test/resources/screenshots/ (Auto-created)
```

---

## 📞 FINDING FILES QUICKLY

| Need | Location |
|------|----------|
| Change browser | config.properties |
| Change timeout | config.properties |
| Add dependency | pom.xml |
| Add test scenario | SauceDemo.feature |
| Add step definition | SauceDemoSteps.java |
| View reports | target/cucumber-reports/cucumber.html |
| View logs | logs/test_execution_*.log |
| Read documentation | DOCUMENTATION/INDEX.md |

---

## 🚀 NEXT STEPS

1. **Open Project Root**
   ```bash
   explorer C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
   ```

2. **Browse Files Using This Guide**
   - Find file location from table above
   - Navigate to that location
   - Open and modify

3. **Run Tests**
   ```bash
   mvn clean test -Dcucumber.filter.tags="@Smoke"
   ```

4. **View Reports**
   ```bash
   start target\cucumber-reports\cucumber.html
   ```

---

**This document is your file location reference guide!** 📍
Use it to quickly find any file you need to modify or review.

