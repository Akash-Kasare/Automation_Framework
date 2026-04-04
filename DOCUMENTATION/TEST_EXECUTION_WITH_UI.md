# 🚀 TEST EXECUTION GUIDE - WITH UI & AUTO REPORT OPENING

## Complete Setup for Chrome Browser UI & Automatic Report Opening

---

## 🎯 What You'll Get

✅ **Tests run on Chrome browser with visible UI**
✅ **Live view of test execution in browser**
✅ **Automatic report opening after execution**
✅ **Extent Report + Cucumber Report**
✅ **Console logs with test details**
✅ **Screenshots on failure**

---

## 📋 Prerequisites

### Required Software
- Java 11+ (Already installed)
- Maven (Already configured)
- Chrome browser (installed on your machine)
- WebDriver auto-downloaded by Selenium 4

### Configuration Already Done
- ✅ BaseClass.java - Chrome driver initialization
- ✅ TestRunner.java - Updated with report plugins
- ✅ ReportGenerator.java - Created for auto-opening reports
- ✅ pom.xml - exec-maven-plugin added

---

## 🚀 Running Tests

### Method 1: Run Smoke Tests (Recommended - Quick)
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```
**Time:** 2-3 minutes | **Tests:** 30+

### Method 2: Run All Tests
```bash
mvn clean test
```
**Time:** 10-15 minutes | **Tests:** 50+

### Method 3: Run Specific Category
```bash
# Login tests only
mvn clean test -Dcucumber.filter.tags="@Login"

# Checkout tests
mvn clean test -Dcucumber.filter.tags="@Checkout"

# Shopping cart tests
mvn clean test -Dcucumber.filter.tags="@ShoppingCart"
```

### Method 4: Run with Chrome Browser (Explicit)
```bash
mvn clean test -Dbrowser=chrome
```

### Method 5: Run with Different Browser (Edge)
```bash
mvn clean test -Dbrowser=edge
```

---

## 👀 What You'll See During Execution

### Console Output
```
[INFO] Scanning for projects...
[INFO] -------< org.example:SauceDemo_Automation_Cucmber >-------
[INFO] Building SauceDemo_Automation_Cucmber 1.0-SNAPSHOT
[INFO] 

╔════════════════════════════════════════════════════════════╗
║ SCENARIO STARTED: User successfully logs in with valid credentials
║ Start Time: 2026-04-04 10:30:45
╚════════════════════════════════════════════════════════════╝

✓ User navigates to the SauceDemo application
✓ User waits for the page to load
✓ User enters username as "standard_user"
✓ User enters password as "secret_sauce"
✓ User clicks the login button
✓ User should be logged in successfully

[INFO] 
╔════════════════════════════════════════════════════════════╗
║ ✅ SCENARIO COMPLETED: User successfully logs in
║ Status: PASSED
║ Total Duration: 15000 ms
║ End Time: 2026-04-04 10:30:48
╚════════════════════════════════════════════════════════════╝

[INFO] BUILD SUCCESS
```

### Chrome Browser Window
```
The Chrome browser window will open and you'll see:
1. Navigation to https://www.saucedemo.com/
2. Entering username and password (visible on screen)
3. Clicking buttons
4. Page interactions
5. All actions performed live

The browser will close when scenario ends
```

### After Test Execution
```
╔════════════════════════════════════════════════════════════╗
║  OPENING CUCUMBER REPORT                                  ║
║  Location: target/cucumber-reports/cucumber.html          ║
╚════════════════════════════════════════════════════════════╝

✅ Report opened in default browser
```

---

## 📊 Reports Generated

### 1. **Cucumber HTML Report** (Opens Automatically)
```
Location: target/cucumber-reports/cucumber.html
Contains:
  - All scenario results
  - Pass/Fail status
  - Step execution details
  - Duration information
  - Error messages on failure
```

### 2. **Extent Report** (Advanced - Opens if Available)
```
Location: target/extent-reports/extent.html
Contains:
  - Beautiful HTML report
  - Screenshots on failure
  - Test statistics
  - Timeline view
  - System information
```

### 3. **JSON Report** (For Integration)
```
Location: target/cucumber-reports/cucumber.json
Format: JSON
Use: CI/CD integration, analytics tools
```

### 4. **JUnit XML Report** (For CI/CD)
```
Location: target/cucumber-reports/cucumber.xml
Format: XML
Use: Jenkins, Azure DevOps, GitLab CI
```

### 5. **Test Logs**
```
Location: logs/test_execution_YYYY-MM-DD_HH-mm-ss.log
Contains:
  - Step-by-step execution logs
  - Timing information
  - Error details with stack traces
  - Action logs
  - Assertion results
```

### 6. **Screenshots** (On Failure)
```
Location: src/test/resources/screenshots/
Contains:
  - Screenshots of failed steps
  - Timestamp in filename
  - Useful for debugging
```

---

## 🎯 Complete Execution Example

### Step-by-Step Execution
```bash
# 1. Navigate to project directory
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# 2. Clean and run tests (Chrome browser will open)
mvn clean test -Dcucumber.filter.tags="@Smoke"

# 3. During execution:
#    - Console shows step-by-step execution
#    - Chrome browser opens and performs actions
#    - You can watch test execution live
#    - Tests complete automatically

# 4. After execution:
#    - Report automatically opens in default browser
#    - View detailed test results
#    - See failures (if any) with screenshots
#    - Check timing information
```

---

## 📋 File Structure After Execution

```
SauceDemo_Automation_Cucmber/
├── target/
│   └── cucumber-reports/
│       ├── cucumber.html          ← Main report (opens automatically)
│       ├── cucumber.json
│       └── cucumber.xml
│
├── logs/
│   └── test_execution_2026-04-04_10-30-47.log
│
├── src/test/resources/screenshots/
│   ├── Failed_Step_2026-04-04_10-35-20.png
│   └── Another_Failed_Step_2026-04-04_10-40-15.png
│
└── ...existing files...
```

---

## 🔍 Viewing Reports Manually

### If Report Doesn't Open Automatically

**Chrome/Firefox/Edge:**
```bash
# Windows (PowerShell)
Start-Process "target/cucumber-reports/cucumber.html"

# Or open directly
explorer target\cucumber-reports\cucumber.html
```

**Mac:**
```bash
open target/cucumber-reports/cucumber.html
```

**Linux:**
```bash
xdg-open target/cucumber-reports/cucumber.html
```

---

## 📊 Report Contents

### Cucumber Report Features
```
✓ Feature overview
✓ Scenario details
✓ Step-by-step execution
✓ Pass/Fail status with color coding
✓ Duration for each step and scenario
✓ Error messages and stack traces
✓ Test summary statistics
✓ Execution timeline
```

### Information Shown
```
- Total Scenarios: 50
- Passed: 48 (96%)
- Failed: 2 (4%)
- Duration: 600 seconds

For Each Scenario:
  - Name
  - Tags (@Smoke, @Login, etc.)
  - Steps executed
  - Duration
  - Status (✓ or ✗)

For Each Step:
  - Step text
  - Status
  - Duration
  - Error (if failed)
```

---

## 🚨 Troubleshooting

### Issue 1: Report doesn't open automatically
**Solution:** Open manually
```bash
start target\cucumber-reports\cucumber.html
```

### Issue 2: Chrome browser doesn't appear
**Solution:** Check configuration
```bash
# Verify config.properties
cat src/test/resources/config.properties

# Should show: browser=chrome
```

### Issue 3: Tests run but no UI visible
**Solution:** Check log files
```bash
# View latest log
cat logs/test_execution_*.log | tail -100

# Search for errors
grep "Error\|Exception" logs/test_execution_*.log
```

### Issue 4: Report generation fails
**Solution:** Clean and rebuild
```bash
mvn clean install
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

---

## 💡 Pro Tips

### 1. Run Tests in Quiet Mode (Less Console Spam)
```bash
mvn clean test -q -Dcucumber.filter.tags="@Smoke"
```

### 2. Run Tests with Debug Info
```bash
mvn clean test -X -Dcucumber.filter.tags="@Smoke"
```

### 3. Skip Report Opening (Just Run Tests)
```bash
mvn clean test -DskipTests=false
```

### 4. Use Different Timeout for Slow Network
```bash
mvn clean test -Dpage_load_timeout=30
```

### 5. Run Tests in Parallel (Faster)
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

---

## 📝 Expected Output Summary

### Successful Execution
```
[INFO] BUILD SUCCESS
[INFO] Tests run: 30, Failures: 0, Skipped: 0, Errors: 0
[INFO] 
╔════════════════════════════════════════════════════════════╗
║  OPENING CUCUMBER REPORT                                  ║
║  Location: target/cucumber-reports/cucumber.html          ║
╚════════════════════════════════════════════════════════════╝
✅ Report opened in default browser
```

### With Failures
```
[ERROR] Tests run: 50, Failures: 2, Skipped: 0, Errors: 0
[ERROR] 
[ERROR] Failed tests:
[ERROR]   TestRunner - Scenario: User fails to login (at line 35)
[ERROR]   TestRunner - Scenario: Invalid checkout (at line 78)
[ERROR]
Report will still open showing failures with details and screenshots
```

---

## 🎯 Quick Command Reference

```bash
# Full execution with report
mvn clean test

# Smoke tests (fast)
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Login tests
mvn clean test -Dcucumber.filter.tags="@Login"

# With Chrome explicitly
mvn clean test -Dbrowser=chrome

# With detailed output
mvn clean test -X

# Quiet mode (less output)
mvn clean test -q

# Check test count
mvn test -Dcucumber.filter.tags="@Smoke" --dry-run

# Open report manually (after tests)
start target\cucumber-reports\cucumber.html

# View logs
type logs\test_execution_*.log
```

---

## ✅ Verification Checklist

Before running tests, verify:

- [x] Java is installed: `java -version`
- [x] Maven is installed: `mvn -version`
- [x] Chrome browser is installed
- [x] config.properties has `browser=chrome`
- [x] pom.xml has Extent report plugin
- [x] TestRunner.java is configured
- [x] ReportGenerator.java exists
- [x] BaseClass.java has WebDriver init
- [x] Hooks.java is configured
- [x] Step definitions exist

---

## 📞 Support

### Common Commands Quick Access
```bash
# Run tests with UI visible
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Open report directory
explorer target\cucumber-reports

# View test logs
type logs\test_execution_*.log

# Check configuration
type src\test\resources\config.properties

# View screenshots
explorer src\test\resources\screenshots
```

---

**Ready to Execute Tests with UI and Auto-Report Opening! 🚀**

Run: `mvn clean test -Dcucumber.filter.tags="@Smoke"`

Watch the Chrome browser perform automated tests live, and the report will automatically open when done!

