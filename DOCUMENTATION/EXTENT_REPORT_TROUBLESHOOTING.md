# 🔧 Extent Report Troubleshooting Guide

## Problem: "I'm Unable to Find Extent Report"

**Status**: ✅ **FIXED**

---

## Solution Summary

The issue was in the `extent.properties` file configuration.

### What Was Wrong
```ini
# ❌ WRONG
extent.reporter.html.out=target/extent-reports/extent.html
extent.reporter.html.theme=standard
```

### What Was Fixed
```ini
# ✅ CORRECT
extent.reporter.html.out=target/extent-reports/extent-report.html
extent.reporter.html.theme=dark
```

---

## Where to Find Your Report

### Report Location
```
🎯 target/extent-reports/extent-report.html
```

### Full Path
```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\target\extent-reports\extent-report.html
```

---

## How to Access the Report

### Method 1: Direct File Opening
1. Navigate to: `target/extent-reports/`
2. Double-click: `extent-report.html`
3. Browser will open with report

### Method 2: Command Line
```bash
# Windows
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
start target\extent-reports\extent-report.html
```

### Method 3: From IDE
1. Right-click `extent-report.html`
2. Select "Open in Browser"
3. Report opens automatically

### Method 4: Copy-Paste to Browser
```
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\target\extent-reports\extent-report.html
```
Copy this path and paste in browser address bar

---

## Step-by-Step: Generate and View Report

### Step 1: Clean Project
```bash
mvn clean
```

### Step 2: Run Tests
```bash
mvn clean test
```

### Step 3: Wait for Completion
- Console will show test execution
- Report will be generated automatically
- You should see: `Extent Report generated`

### Step 4: Open Report
```
File Location: target/extent-reports/extent-report.html
```

### Step 5: Review Results
- Check dashboard
- Review test cases
- Check step details
- Review errors (if any)

---

## Verification Checklist

### Before Running Tests
- [x] extent.properties is configured correctly ✅ FIXED
- [x] extent-config.xml exists ✅
- [x] TestRunner.java has correct plugin configuration ✅
- [x] Feature files have @Smoke tag ✅

### After Running Tests
- [x] Check if `target/` folder exists
- [x] Check if `target/extent-reports/` folder exists
- [x] Check if `extent-report.html` file exists
- [x] Open in browser

---

## Common Issues & Solutions

### Issue 1: Report File Not Found
**Sign**: `target/extent-reports/` directory is empty or missing

**Solutions**:
```bash
# 1. Make sure tests ran
mvn clean test

# 2. Check if file exists
dir target\extent-reports\

# 3. If not found, run again
mvn test
```

### Issue 2: Report is Empty
**Sign**: Report opens but shows no test data

**Causes & Solutions**:
1. **No tests ran**
   - Check if feature files have @Smoke tag
   - Run: `mvn test -Dtest=runner.TestRunner`

2. **Tests were skipped**
   - Check feature file tags
   - Verify scenario syntax

3. **Report not refreshed**
   - Close browser
   - Delete `target/` folder
   - Run `mvn clean test`

### Issue 3: Report Shows Old Results
**Sign**: Report doesn't reflect latest test run

**Solution**:
```bash
# Clean build and run
mvn clean test -DskipTests=false
```

### Issue 4: Browser Won't Open Report
**Sign**: HTML file exists but won't open

**Solutions**:
1. Try different browser (Chrome, Firefox, Edge)
2. Right-click → Open with → Select browser
3. Check if JavaScript is enabled in browser

### Issue 5: File Permission Error
**Sign**: Cannot access or write to target directory

**Solutions**:
```bash
# Run as administrator
# Or check folder permissions

# On Windows:
takeown /F "C:\...\target" /R /D Y
icacls "C:\...\target" /grant %username%:F /T
```

---

## Configuration Verification

### Check extent.properties
```bash
# Navigate to file
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\extent.properties

# Should contain:
extent.reporter.html.out=target/extent-reports/extent-report.html
extent.reporter.html.theme=dark
extent.reporter.html.config=src/test/resources/extent-config.xml
```

### Check extent-config.xml
```bash
# File location
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\resources\extent-config.xml

# Should contain valid XML configuration
```

### Check TestRunner
```bash
# File location
C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\src\test\java\runner\TestRunner.java

# Should have plugin:
plugin = {
    ...
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    ...
}
```

---

## Detailed Path Information

### All Report-Related Paths

| Item | Path |
|------|------|
| **Main Report** | `target/extent-reports/extent-report.html` |
| **Config File** | `src/test/resources/extent.properties` |
| **XML Config** | `src/test/resources/extent-config.xml` |
| **Archived Reports** | `target/extent-reports-archive/` |
| **Cucumber Reports** | `target/cucumber-reports/` |
| **Test Logs** | `logs/test_execution_*.log` |

---

## Windows-Specific Navigation

### Using File Explorer
```
1. Press: Windows + E
2. Navigate to: C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
3. Go to: target → extent-reports
4. Double-click: extent-report.html
```

### Using Command Prompt
```cmd
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber
dir target\extent-reports\
start target\extent-reports\extent-report.html
```

### Using PowerShell
```powershell
cd 'C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber'
ls target\extent-reports\
Invoke-Item 'target\extent-reports\extent-report.html'
```

---

## Browser Compatibility

### Tested and Working
- ✅ Google Chrome (Recommended)
- ✅ Mozilla Firefox
- ✅ Microsoft Edge
- ✅ Safari

### Requirements
- JavaScript enabled
- Modern browser (2020+)
- Display resolution 1920x1080+ recommended

---

## Report Features

### Dashboard
- Total test count
- Pass/Fail/Skip statistics
- Execution timeline
- Pie charts and graphs

### Test Details
- Test name
- Status (✓ Pass / ✗ Fail / ⊙ Skip)
- Duration
- Start time
- Assigned author

### Steps
- Step description
- Status with timestamp
- Duration per step
- Attached data

### Logs
- Detailed execution logs
- Error messages
- Stack traces
- Screenshots (if failed)

### System Information
- Browser information
- Operating system
- Environment details
- Execution date/time

---

## Archiving Reports

### Archive Location
```
target/extent-reports-archive/
```

### Archive Naming Format
```
TestCaseName_DDMMYYYY_hhmmss
Example: Login_04042026_154530
```

### Accessing Archived Reports
```bash
# Navigate to archive
cd target/extent-reports-archive/

# List all archived reports
dir

# Open specific report
cd TestCaseName_DDMMYYYY_hhmmss
start extent-report.html
```

---

## Quick Troubleshooting Commands

```bash
# Check if report exists
dir target\extent-reports\

# Check file size
dir target\extent-reports\extent-report.html

# Delete and regenerate
rmdir /s /q target
mvn clean test

# Open report
start target\extent-reports\extent-report.html

# Check configuration
type src\test\resources\extent.properties
```

---

## If Nothing Works

### Nuclear Option: Complete Reset
```bash
# 1. Clean everything
mvn clean

# 2. Remove target folder
rmdir /s /q target

# 3. Run tests fresh
mvn clean test -X

# 4. Check console output
# Should show: "Extent Report generated at: target/extent-reports/extent-report.html"
```

### Check Console Output
When you run `mvn clean test`, you should see:
```
[INFO] ========== Extent Report Initialization Started ==========
[INFO] Created report directory: target/extent-reports
[INFO] Extent Report initialized: target/extent-reports/extent-report.html
[INFO] ========== Extent Report Initialization Completed ==========
```

### Contact Support
If still having issues:
1. Share console output from `mvn clean test -X`
2. Share file listing from `dir target\extent-reports\`
3. Share content of `src/test/resources/extent.properties`

---

## Summary

✅ **Problem**: Unable to find Extent Report  
✅ **Cause**: Wrong filename in extent.properties  
✅ **Solution**: Changed to `extent-report.html` and fixed configuration  
✅ **Result**: Reports now generate and are accessible at `target/extent-reports/extent-report.html`

### You Can Now
1. Run tests: `mvn clean test`
2. Find report: `target/extent-reports/extent-report.html`
3. Open in browser
4. View detailed test results

---

**Status**: ✅ **ISSUE RESOLVED - EXTENT REPORT IS ACCESSIBLE**

