# 📊 PROJECT STRUCTURE VISUAL MAP

## Complete Directory Tree with File Locations

```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
│
├── 📚 DOCUMENTATION/                          ← ALL .MD FILES (21 total)
│   ├── INDEX.md                               ← START HERE - Navigation Guide
│   ├── README.md                              ← Quick Start
│   ├── FILE_LOCATIONS_GUIDE.md               ← YOU ARE HERE
│   ├── COMMAND_QUICK_REFERENCE.md
│   ├── TEST_EXECUTION_WITH_UI.md
│   ├── UI_EXECUTION_SETUP_COMPLETE.md
│   ├── DOCUMENTATION_INDEX.md
│   ├── FRAMEWORK_VISUAL_OVERVIEW.md
│   ├── COMPLETE_IMPLEMENTATION_SUMMARY.md
│   ├── STEP_DEFINITIONS_README.md
│   ├── STEP_DEFINITIONS_QUICK_REFERENCE.md
│   ├── TEST_EXECUTION_GUIDE.md
│   ├── LOGGER_IMPLEMENTATION_GUIDE.md
│   ├── LOGGER_QUICK_REFERENCE.md
│   ├── LOGGER_IMPLEMENTATION_SUMMARY.md
│   ├── LOGGER_FINAL_STATUS.md
│   ├── LOGGER_COMPLETION_CHECKLIST.md
│   ├── ERRORS_RESOLVED.md
│   ├── PROJECT_COMPLETION_SUMMARY.md
│   ├── COMPLETE_FILE_MANIFEST.md
│   ├── DELIVERY_CHECKLIST.md
│   └── IMPLEMENTATION_COMPLETE.md
│
├── 📦 src/                                    ← SOURCE CODE
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   ├── factory/
│       │   │   └── BaseClass.java             (115 lines - WebDriver init)
│       │   │
│       │   ├── steps/
│       │   │   ├── SauceDemoSteps.java        (560+ lines - 70+ step definitions)
│       │   │   └── Hooks.java                 (88 lines - Test lifecycle)
│       │   │
│       │   ├── runner/
│       │   │   ├── TestRunner.java            (25 lines - Test execution config)
│       │   │   └── ReportGenerator.java       (95 lines - Auto-opens reports)
│       │   │
│       │   └── utils/
│       │       └── TestLogger.java            (330+ lines - Logging utility)
│       │
│       └── resources/
│           ├── feature/
│           │   └── SauceDemo.feature          (463 lines - 50+ test scenarios)
│           │
│           ├── config.properties              (15 lines - Test configuration)
│           │
│           └── screenshots/                   (Auto-created on failure)
│               └── FailedTest_timestamp.png
│
├── 📂 logs/                                   (Auto-created after test execution)
│   └── test_execution_2026-04-04_10-30-47.log
│
├── 🎯 target/                                (Auto-created by Maven)
│   ├── cucumber-reports/                     (Reports)
│   │   ├── cucumber.html                     ← MAIN REPORT (Opens automatically)
│   │   ├── cucumber.json
│   │   └── cucumber.xml
│   │
│   ├── extent-reports/                       (Advanced reports)
│   │   └── extent.html
│   │
│   └── classes/                              (Compiled Java files)
│
├── 📄 pom.xml                                ← MAVEN CONFIGURATION
│   ├── Project metadata (groupId, artifactId)
│   ├── Dependencies (Selenium, Cucumber, Log4j)
│   ├── Plugin configuration
│   └── Build settings
│
├── 📄 .gitignore
├── 📂 .idea/                                 (IntelliJ IDEA settings)
├── 📂 .mvn/                                  (Maven wrapper)
└── 📄 README.md (Root level)

```

---

## 🔍 IMPORTANT FILE LOCATIONS - QUICK REFERENCE

### Configuration Files You Might Modify

```
📄 pom.xml
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\pom.xml
   ↳ Add new dependencies here
   ↳ Configure plugins
   ↳ Change Java version

📄 config.properties
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\config.properties
   ↳ Change browser (chrome/edge)
   ↳ Change timeout values
   ↳ Change application URL
   ↳ Change execution environment (local/remote)
```

---

### Java Source Code Files

```
📄 BaseClass.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\factory\BaseClass.java
   ↳ WebDriver initialization
   ↳ Configuration loading
   ↳ Driver cleanup

📄 SauceDemoSteps.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\steps\SauceDemoSteps.java
   ↳ 70+ step definitions
   ↳ Test actions and assertions
   ↳ Already has logging integrated

📄 Hooks.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\steps\Hooks.java
   ↳ Setup and teardown
   ↳ Screenshot capture
   ↳ Test logging

📄 TestRunner.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\runner\TestRunner.java
   ↳ Cucumber configuration
   ↳ Report setup
   ↳ Tag filtering

📄 ReportGenerator.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\runner\ReportGenerator.java
   ↳ Auto-opens reports after tests
   ↳ Cross-platform compatible

📄 TestLogger.java
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\utils\TestLogger.java
   ↳ Comprehensive logging system
   ↳ Step-level logging
   ↳ File and console output
```

---

### Test Definition Files

```
📄 SauceDemo.feature
   Path: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\feature\SauceDemo.feature
   ↳ 50+ test scenarios
   ↳ Gherkin syntax
   ↳ All feature tests
```

---

## 📊 FILE ORGANIZATION BY PURPOSE

### WHEN YOU NEED TO...

```
1️⃣ CHANGE TEST CONFIGURATION (Browser, Timeout, URL)
   → Edit: config.properties
   Location: src/test/resources/config.properties

2️⃣ ADD NEW DEPENDENCY (Library)
   → Edit: pom.xml
   Location: C:\...SauceDemo_Automation_Cucmber\pom.xml

3️⃣ ADD NEW TEST SCENARIO
   → Edit: SauceDemo.feature
   Location: src/test/resources/feature/SauceDemo.feature

4️⃣ ADD NEW STEP DEFINITION
   → Edit: SauceDemoSteps.java
   Location: src/test/java/steps/SauceDemoSteps.java

5️⃣ CUSTOMIZE BROWSER INITIALIZATION
   → Edit: BaseClass.java
   Location: src/test/java/factory/BaseClass.java

6️⃣ CUSTOMIZE TEST SETUP/TEARDOWN
   → Edit: Hooks.java
   Location: src/test/java/steps/Hooks.java

7️⃣ VIEW TEST REPORTS
   → Open: cucumber.html
   Location: target/cucumber-reports/cucumber.html
   (Auto-opens after test execution)

8️⃣ VIEW TEST LOGS
   → Open: test_execution_*.log
   Location: logs/test_execution_YYYY-MM-DD_HH-mm-ss.log

9️⃣ VIEW DOCUMENTATION
   → Open: INDEX.md
   Location: DOCUMENTATION/INDEX.md

🔟 VIEW FAILURE SCREENSHOTS
   → Open: Screenshots folder
   Location: src/test/resources/screenshots/
```

---

## 🎯 MOST ACCESSED FILES

### Daily Usage Files

```
Rank 1: config.properties
   Path: src/test/resources/config.properties
   Use: Configure tests (browser, timeout, URL)

Rank 2: SauceDemo.feature
   Path: src/test/resources/feature/SauceDemo.feature
   Use: Add new test scenarios

Rank 3: SauceDemoSteps.java
   Path: src/test/java/steps/SauceDemoSteps.java
   Use: Add new step definitions

Rank 4: pom.xml
   Path: C:\...SauceDemo_Automation_Cucmber\pom.xml
   Use: Add new dependencies

Rank 5: DOCUMENTATION/INDEX.md
   Path: DOCUMENTATION/INDEX.md
   Use: Find documentation and guides
```

---

## 🚀 COMMON FILE OPERATIONS

### Open Files in IDE

```bash
# Open VS Code with project
code C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Open specific file in VS Code
code src\test\resources\config.properties
code pom.xml
code src\test\java\steps\SauceDemoSteps.java

# Open file in Windows Explorer
explorer src\test\resources\
explorer DOCUMENTATION\
```

### View Files in Terminal

```bash
# View pom.xml
type pom.xml

# View config.properties
type src\test\resources\config.properties

# View feature file
type src\test\resources\feature\SauceDemo.feature

# View logs
type logs\test_execution_*.log

# List all Java files
dir /s /b src\test\java\*.java
```

---

## 📍 DIRECTORY ALIASES

For easy access, remember these shortcuts:

```
PROJECT ROOT: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\

Aliases:
  [ROOT] = C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
  [SRC] = [ROOT]src\
  [TEST_JAVA] = [SRC]test\java\
  [TEST_RES] = [SRC]test\resources\
  [FACTORY] = [TEST_JAVA]factory\
  [STEPS] = [TEST_JAVA]steps\
  [RUNNER] = [TEST_JAVA]runner\
  [UTILS] = [TEST_JAVA]utils\
  [FEATURE] = [TEST_RES]feature\
  [CONFIG] = [TEST_RES]
  [DOCS] = [ROOT]DOCUMENTATION\
  [LOGS] = [ROOT]logs\
  [REPORTS] = [ROOT]target\cucumber-reports\
```

---

## ✅ VERIFICATION CHECKLIST

```
✓ pom.xml exists at root
✓ config.properties exists in src/test/resources/
✓ BaseClass.java exists in src/test/java/factory/
✓ SauceDemoSteps.java exists in src/test/java/steps/
✓ Hooks.java exists in src/test/java/steps/
✓ TestRunner.java exists in src/test/java/runner/
✓ ReportGenerator.java exists in src/test/java/runner/
✓ TestLogger.java exists in src/test/java/utils/
✓ SauceDemo.feature exists in src/test/resources/feature/
✓ DOCUMENTATION folder exists at root with 21+ files
```

---

## 🎊 KEY TAKEAWAY

**Project Root:** 
```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\
```

**All Documentation:** 
```
DOCUMENTATION/
```

**Configuration:** 
```
src/test/resources/config.properties
pom.xml
```

**Java Code:** 
```
src/test/java/factory/
src/test/java/steps/
src/test/java/runner/
src/test/java/utils/
```

**Test Scenarios:** 
```
src/test/resources/feature/SauceDemo.feature
```

**Reports (After Execution):** 
```
target/cucumber-reports/cucumber.html
```

---

**Use this guide to quickly locate any file in the project!** 🎯

