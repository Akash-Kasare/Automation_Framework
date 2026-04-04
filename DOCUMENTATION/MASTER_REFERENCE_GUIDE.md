# 🎯 MASTER REFERENCE GUIDE - START HERE!

## Complete Guide to Find Everything in the Project

---

## 📍 PROJECT LOCATION

```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
```

---

## 🚀 QUICK NAVIGATION

### For Different Needs

#### 1️⃣ I want to MODIFY CONFIGURATION
**Files to Edit:**
- **config.properties** → `src/test/resources/config.properties`
  - Change browser (chrome/edge)
  - Change timeout values
  - Change application URL

#### 2️⃣ I want to RUN TESTS
**What You Need:**
- **Command:** `mvn clean test -Dcucumber.filter.tags="@Smoke"`
- **Guide:** `DOCUMENTATION/COMMAND_QUICK_REFERENCE.md`
- **Reports:** `target/cucumber-reports/cucumber.html` (auto-opens)

#### 3️⃣ I want to ADD NEW TEST SCENARIO
**Files to Edit:**
- **SauceDemo.feature** → `src/test/resources/feature/SauceDemo.feature`
- **Guide:** `DOCUMENTATION/STEP_DEFINITIONS_README.md`

#### 4️⃣ I want to ADD NEW STEP DEFINITION
**Files to Edit:**
- **SauceDemoSteps.java** → `src/test/java/steps/SauceDemoSteps.java`
- **Guide:** `DOCUMENTATION/STEP_DEFINITIONS_README.md`

#### 5️⃣ I want to ADD NEW DEPENDENCY
**Files to Edit:**
- **pom.xml** → `C:\...SauceDemo_Automation_Cucmber\pom.xml`
- **Guide:** `DOCUMENTATION/TEST_EXECUTION_GUIDE.md`

#### 6️⃣ I want to UNDERSTAND THE FRAMEWORK
**Documents to Read:**
- **DOCUMENTATION/FRAMEWORK_VISUAL_OVERVIEW.md** - Architecture
- **DOCUMENTATION/COMPLETE_IMPLEMENTATION_SUMMARY.md** - What's implemented
- **DOCUMENTATION/PROJECT_STRUCTURE_MAP.md** - File organization

#### 7️⃣ I want to UNDERSTAND LOGGING
**Documents to Read:**
- **DOCUMENTATION/LOGGER_IMPLEMENTATION_GUIDE.md** - Complete guide
- **DOCUMENTATION/LOGGER_QUICK_REFERENCE.md** - Quick patterns
- **logs/test_execution_*.log** - Actual logs after running tests

#### 8️⃣ I want to VIEW TEST REPORTS
**Files to Open:**
- **cucumber.html** → `target/cucumber-reports/cucumber.html`
- (Auto-opens after test execution)

#### 9️⃣ I want to FIND ALL DOCUMENTATION
**Start Here:**
- **DOCUMENTATION/INDEX.md** - All docs organized
- **DOCUMENTATION/FILE_LOCATIONS_GUIDE.md** - File locations
- **DOCUMENTATION/PROJECT_STRUCTURE_MAP.md** - Visual structure

---

## 📂 COMPLETE FILE DIRECTORY

### Root Directory Files
```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\

├── pom.xml                          ← Maven configuration
├── .gitignore                       ← Git ignore
├── .idea/                           ← IDE settings
├── .mvn/                            ← Maven wrapper
│
├── DOCUMENTATION/                   ← ALL .MD FILES (23 files)
│   ├── INDEX.md                     ← Start here for navigation
│   ├── README.md                    ← Quick start
│   ├── FILE_LOCATIONS_GUIDE.md     ← Where files are located
│   ├── PROJECT_STRUCTURE_MAP.md     ← Visual file structure
│   ├── COMMAND_QUICK_REFERENCE.md
│   ├── TEST_EXECUTION_WITH_UI.md
│   ├── UI_EXECUTION_SETUP_COMPLETE.md
│   ├── And 16+ more documentation files...
│
├── src/                             ← Source code
│   ├── main/
│   └── test/
│       ├── java/
│       │   ├── factory/BaseClass.java
│       │   ├── steps/(SauceDemoSteps.java, Hooks.java)
│       │   ├── runner/(TestRunner.java, ReportGenerator.java)
│       │   └── utils/TestLogger.java
│       └── resources/
│           ├── config.properties
│           ├── feature/SauceDemo.feature
│           └── screenshots/
│
├── logs/                            ← Test logs (auto-created)
│   └── test_execution_*.log
│
└── target/                          ← Build output (auto-created)
    ├── cucumber-reports/
    │   ├── cucumber.html            ← MAIN REPORT
    │   ├── cucumber.json
    │   └── cucumber.xml
    └── classes/
```

---

## 🔍 EXACT FILE LOCATIONS

### MOST IMPORTANT FILES

| # | File | Path | Purpose | Modify? |
|---|------|------|---------|---------|
| 1 | **pom.xml** | Root | Maven config, dependencies | ✅ Yes |
| 2 | **config.properties** | src/test/resources/ | Test configuration | ✅ Yes |
| 3 | **SauceDemo.feature** | src/test/resources/feature/ | Test scenarios | ✅ Yes |
| 4 | **SauceDemoSteps.java** | src/test/java/steps/ | Step definitions | ✅ Yes |
| 5 | **BaseClass.java** | src/test/java/factory/ | WebDriver setup | ❌ No |
| 6 | **Hooks.java** | src/test/java/steps/ | Test lifecycle | ❌ No |
| 7 | **TestRunner.java** | src/test/java/runner/ | Test execution config | ❌ No |
| 8 | **TestLogger.java** | src/test/java/utils/ | Logging system | ❌ No |
| 9 | **DOCUMENTATION/** | Root | All .md files | ❌ Reference |

---

## 📍 QUICK LOOKUP TABLE

### If you need to...

```
FIND pom.xml
  ↳ Location: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\pom.xml
  ↳ Use: Add dependencies, change Java version

FIND config.properties
  ↳ Location: src\test\resources\config.properties
  ↳ Use: Change browser, timeout, URL

FIND test scenarios
  ↳ Location: src\test\resources\feature\SauceDemo.feature
  ↳ Use: Add new test scenarios

FIND step definitions
  ↳ Location: src\test\java\steps\SauceDemoSteps.java
  ↳ Use: Add new step definitions

FIND WebDriver setup
  ↳ Location: src\test\java\factory\BaseClass.java
  ↳ Use: Driver initialization code

FIND test lifecycle
  ↳ Location: src\test\java\steps\Hooks.java
  ↳ Use: Setup and teardown code

FIND logging system
  ↳ Location: src\test\java\utils\TestLogger.java
  ↳ Use: Logging utilities

FIND test runner config
  ↳ Location: src\test\java\runner\TestRunner.java
  ↳ Use: Cucumber configuration

FIND reports
  ↳ Location: target\cucumber-reports\cucumber.html
  ↳ Use: View test results

FIND test logs
  ↳ Location: logs\test_execution_*.log
  ↳ Use: Detailed test execution info

FIND documentation
  ↳ Location: DOCUMENTATION\
  ↳ Use: Read guides and references
```

---

## 🎯 COMMON TASKS & SOLUTIONS

### Task 1: Change Browser
```
File: config.properties
Path: src/test/resources/config.properties
Change: browser=chrome → browser=edge
Or run: mvn clean test -Dbrowser=edge
```

### Task 2: Change Timeout
```
File: config.properties
Path: src/test/resources/config.properties
Change: implicit_wait=10 → implicit_wait=20
```

### Task 3: Change Application URL
```
File: config.properties
Path: src/test/resources/config.properties
Change: app_url=https://www.saucedemo.com/ → your URL
```

### Task 4: Add New Test Scenario
```
File: SauceDemo.feature
Path: src/test/resources/feature/SauceDemo.feature
Action: Add new Scenario with steps
```

### Task 5: Add New Step Definition
```
File: SauceDemoSteps.java
Path: src/test/java/steps/SauceDemoSteps.java
Action: Add new @When/@Then/@Given method
Follow: Pattern from existing steps
```

### Task 6: Add New Dependency
```
File: pom.xml
Path: Root directory (pom.xml)
Action: Add <dependency> inside <dependencies> section
Run: mvn clean install
```

### Task 7: View Test Results
```
File: cucumber.html
Path: target/cucumber-reports/cucumber.html
Action: Auto-opens after tests
Manual: start target\cucumber-reports\cucumber.html
```

### Task 8: Check Test Logs
```
File: test_execution_*.log
Path: logs/test_execution_YYYY-MM-DD_HH-mm-ss.log
Action: type logs\test_execution_*.log
```

### Task 9: Customize WebDriver
```
File: BaseClass.java
Path: src/test/java/factory/BaseClass.java
Action: Modify initializeDriver() method
Note: Be careful with changes
```

### Task 10: Add Setup/Teardown Logic
```
File: Hooks.java
Path: src/test/java/steps/Hooks.java
Action: Modify @Before and @After methods
Note: Be careful with changes
```

---

## 📊 DOCUMENTATION GUIDE

### Start with these files (in order)

**For Getting Started:**
1. `DOCUMENTATION/README.md` (5 min)
2. `DOCUMENTATION/INDEX.md` (5 min)
3. `DOCUMENTATION/FILE_LOCATIONS_GUIDE.md` (10 min)

**For Running Tests:**
1. `DOCUMENTATION/COMMAND_QUICK_REFERENCE.md` (5 min)
2. `DOCUMENTATION/UI_EXECUTION_SETUP_COMPLETE.md` (5 min)

**For Framework Understanding:**
1. `DOCUMENTATION/FRAMEWORK_VISUAL_OVERVIEW.md` (15 min)
2. `DOCUMENTATION/COMPLETE_IMPLEMENTATION_SUMMARY.md` (15 min)

**For Step Definitions:**
1. `DOCUMENTATION/STEP_DEFINITIONS_README.md` (20 min)
2. `DOCUMENTATION/STEP_DEFINITIONS_QUICK_REFERENCE.md` (15 min)

**For Logging:**
1. `DOCUMENTATION/LOGGER_IMPLEMENTATION_GUIDE.md` (20 min)
2. `DOCUMENTATION/LOGGER_QUICK_REFERENCE.md` (10 min)

---

## ✅ VERIFICATION CHECKLIST

Before you start, verify all files exist:

```bash
# Navigate to project root
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Check key files exist
if exist pom.xml echo "✓ pom.xml found"
if exist src\test\resources\config.properties echo "✓ config.properties found"
if exist src\test\resources\feature\SauceDemo.feature echo "✓ SauceDemo.feature found"
if exist src\test\java\factory\BaseClass.java echo "✓ BaseClass.java found"
if exist src\test\java\steps\SauceDemoSteps.java echo "✓ SauceDemoSteps.java found"
if exist DOCUMENTATION echo "✓ DOCUMENTATION folder found"
```

---

## 🚀 NEXT STEPS

### Step 1: Orient Yourself
- [ ] Read this document (FILE_LOCATIONS_GUIDE.md)
- [ ] Read DOCUMENTATION/INDEX.md

### Step 2: Choose Your Path
- [ ] If running tests → Read COMMAND_QUICK_REFERENCE.md
- [ ] If modifying code → Read PROJECT_STRUCTURE_MAP.md
- [ ] If understanding framework → Read FRAMEWORK_VISUAL_OVERVIEW.md

### Step 3: Take Action
- [ ] Modify files as needed
- [ ] Run tests: `mvn clean test -Dcucumber.filter.tags="@Smoke"`
- [ ] View reports: `start target\cucumber-reports\cucumber.html`

---

## 📞 QUICK REFERENCE

### Open Project
```bash
explorer C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
```

### Run Tests
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### View Report
```bash
start target\cucumber-reports\cucumber.html
```

### Open Documentation
```bash
explorer DOCUMENTATION
```

### Check Logs
```bash
type logs\test_execution_*.log
```

---

## 🎊 FINAL SUMMARY

```
Project Root: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\

Most Important Locations:
  📍 Configuration:     src/test/resources/config.properties
  📍 Test Scenarios:    src/test/resources/feature/SauceDemo.feature
  📍 Step Definitions:  src/test/java/steps/SauceDemoSteps.java
  📍 Maven Config:      pom.xml
  📍 Documentation:     DOCUMENTATION/ (23 files)
  📍 Reports:           target/cucumber-reports/cucumber.html
  📍 Logs:              logs/test_execution_*.log

All Documentation in: DOCUMENTATION/
Start with: DOCUMENTATION/INDEX.md
This file: FILE_LOCATIONS_GUIDE.md
```

---

**Use this guide to quickly find any file or understand the project structure!** 🎯

Happy coding! 🚀

