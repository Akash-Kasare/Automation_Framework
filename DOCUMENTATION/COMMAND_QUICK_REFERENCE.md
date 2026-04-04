# 🚀 COMMAND QUICK REFERENCE

## Execution Commands

### RUN TESTS - COPY & PASTE READY

#### 1. QUICK TEST (Smoke Tests - 2-3 minutes)
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```
**What it does:**
- Runs 30+ quick smoke tests
- Tests on Chrome with visible UI
- Shows each step with timing
- Opens report automatically

---

#### 2. ALL TESTS (Full Suite - 10-15 minutes)
```bash
mvn clean test
```
**What it does:**
- Runs all 50+ test scenarios
- Complete test coverage
- All features tested
- Comprehensive report

---

#### 3. LOGIN TESTS ONLY
```bash
mvn clean test -Dcucumber.filter.tags="@Login"
```

---

#### 4. SHOPPING TESTS ONLY
```bash
mvn clean test -Dcucumber.filter.tags="@ShoppingCart"
```

---

#### 5. CHECKOUT TESTS ONLY
```bash
mvn clean test -Dcucumber.filter.tags="@Checkout"
```

---

#### 6. WITH DETAILED OUTPUT
```bash
mvn clean test -X -Dcucumber.filter.tags="@Smoke"
```

---

#### 7. QUIET MODE (Less Output)
```bash
mvn clean test -q -Dcucumber.filter.tags="@Smoke"
```

---

## Post-Execution Commands

### OPEN REPORTS MANUALLY

#### Open HTML Report (Windows)
```bash
start target\cucumber-reports\cucumber.html
```

#### Open HTML Report (Mac)
```bash
open target/cucumber-reports/cucumber.html
```

#### Open HTML Report (Linux)
```bash
xdg-open target/cucumber-reports/cucumber.html
```

---

### VIEW LOGS

#### View Latest Log File
```bash
type logs\test_execution_*.log
```

#### Search for Failures in Logs
```bash
findstr "FAILED" logs\test_execution_*.log
```

#### View Last 50 Lines of Log
```bash
powershell "Get-Content logs\test_execution_*.log | Select-Object -Last 50"
```

---

### OPEN DIRECTORIES

#### Open Reports Directory
```bash
explorer target\cucumber-reports
```

#### Open Logs Directory
```bash
explorer logs
```

#### Open Screenshots Directory
```bash
explorer src\test\resources\screenshots
```

---

## Useful Options

### RUN WITH DIFFERENT BROWSER

#### Edge Browser
```bash
mvn clean test -Dbrowser=edge
```

#### Chrome Browser (Explicit)
```bash
mvn clean test -Dbrowser=chrome
```

---

### RUN WITH DIFFERENT TIMEOUTS

#### Increase Timeout (for slow network)
```bash
mvn clean test -Dpage_load_timeout=30 -Dcucumber.filter.tags="@Smoke"
```

---

### MULTIPLE TAG COMBINATIONS

#### Run Smoke + Login Tests
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke and @Login"
```

#### Run Login OR Checkout Tests
```bash
mvn clean test -Dcucumber.filter.tags="@Login or @Checkout"
```

#### Skip Negative Tests
```bash
mvn clean test -Dcucumber.filter.tags="not @Negative"
```

---

## Build Commands

### CLEAN BUILD
```bash
mvn clean
```

### BUILD WITHOUT TESTS
```bash
mvn clean install -DskipTests
```

### FULL CLEAN AND BUILD
```bash
mvn clean install
```

### CHECK DEPENDENCIES
```bash
mvn dependency:tree
```

---

## Verification Commands

### CHECK JAVA VERSION
```bash
java -version
```

### CHECK MAVEN VERSION
```bash
mvn -version
```

### VERIFY PROJECT STRUCTURE
```bash
mvn help:describe
```

---

## Troubleshooting Commands

### REBUILD PROJECT
```bash
mvn clean install
```

### RUN TESTS IN DEBUG MODE
```bash
mvn clean test -X
```

### VALIDATE POM FILE
```bash
mvn validate
```

### CHECK TEST DISCOVERY
```bash
mvn test --dry-run
```

---

## MOST IMPORTANT COMMAND

### START HERE - RUN SMOKE TESTS
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

Watch the Chrome browser open and execute tests live!
Report opens automatically when done.

---

## QUICK COPY-PASTE REFERENCE

```
# Quick test (use this first)
mvn clean test -Dcucumber.filter.tags="@Smoke"

# All tests
mvn clean test

# Login tests
mvn clean test -Dcucumber.filter.tags="@Login"

# Open report
start target\cucumber-reports\cucumber.html

# View logs
type logs\test_execution_*.log

# Check version
java -version && mvn -version
```

---

## WHAT HAPPENS AFTER YOU RUN THE COMMAND

### Step 1: Build Phase (30 seconds)
```
Maven builds the project
Downloads any missing dependencies
Compiles Java classes
```

### Step 2: Test Execution Phase (2-15 minutes)
```
Chrome browser opens automatically
Tests execute with visible UI
Console shows each step
Detailed logs in logs/ directory
Screenshots taken on failure
```

### Step 3: Report Generation (10 seconds)
```
Generates HTML report
Generates JSON report
Generates JUnit XML report
```

### Step 4: Report Opening (5 seconds)
```
ReportGenerator automatically opens report
HTML opens in your default browser
You can view detailed test results
```

---

## EXPECTED OUTPUT

### Success Example
```
[INFO] BUILD SUCCESS
[INFO] Tests run: 30, Failures: 0, Skipped: 0, Errors: 0

╔════════════════════════════════════════════════════════════╗
║  OPENING CUCUMBER REPORT                                  ║
║  Location: target/cucumber-reports/cucumber.html          ║
╚════════════════════════════════════════════════════════════╝

✅ Report opened in default browser
```

### With Failures Example
```
[ERROR] Tests run: 50, Failures: 2, Skipped: 0, Errors: 0

[ERROR] Failed tests:
[ERROR]   - Scenario 1 at line 35
[ERROR]   - Scenario 2 at line 78

Report still opens with failure details and screenshots
```

---

## COMPLETE WORKFLOW

```bash
# 1. Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# 2. Run tests (copy-paste this)
mvn clean test -Dcucumber.filter.tags="@Smoke"

# 3. Watch Chrome browser open and execute tests live
# 4. View console output showing each step
# 5. Report opens automatically when done
# 6. View results in beautiful HTML format

# If report doesn't open:
start target\cucumber-reports\cucumber.html

# If you want to see logs:
type logs\test_execution_*.log

# If you want to see reports directory:
explorer target\cucumber-reports
```

---

## 🎯 FINAL SUMMARY

**Just copy and run this:**
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

**That's it!**
- Tests run on Chrome with visible UI
- You watch them execute live
- Report opens automatically
- View detailed results

Everything else is automated! 🚀

