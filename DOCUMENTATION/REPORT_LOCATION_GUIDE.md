# 📁 Project Directory Structure & Report Location

## Where to Find Everything

```
SauceDemo_Automation_Cucmber/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   ├── factory/
│       │   │   └── BaseClass.java
│       │   ├── listeners/
│       │   │   └── ExtentReportListener.java ✅ (Fixed)
│       │   ├── runner/
│       │   │   ├── TestRunner.java
│       │   │   ├── ReportGenerator.java
│       │   │   └── ReportArchiver.java
│       │   ├── steps/
│       │   │   ├── Hooks.java
│       │   │   └── SauceDemoSteps.java ✅ (Fixed)
│       │   └── utils/
│       │       ├── TestLogger.java
│       │       ├── ExcelUtils.java
│       │       └── TestDataManager.java
│       │
│       └── resources/
│           ├── config.properties
│           ├── extent.properties ✅ (FIXED)
│           ├── extent-config.xml
│           ├── feature/
│           │   └── SauceDemo.feature
│           └── screenshots/
│
├── target/ ⬅️ **WHERE REPORTS ARE GENERATED**
│   ├── extent-reports/ ⬅️ **EXTENT REPORTS HERE**
│   │   ├── extent-report.html ⬅️ **🎯 MAIN REPORT FILE**
│   │   └── (other report assets)
│   │
│   ├── extent-reports-archive/ ⬅️ **ARCHIVED REPORTS**
│   │   ├── SauceDemo_Login_04042026_154530/
│   │   ├── SauceDemo_Cart_04042026_160245/
│   │   └── ...
│   │
│   ├── cucumber-reports/
│   │   ├── cucumber.html
│   │   ├── cucumber.json
│   │   └── cucumber.xml
│   │
│   └── test-classes/
│       ├── config.properties
│       ├── extent-config.xml
│       ├── extent.properties
│       └── (compiled classes)
│
├── logs/ ⬅️ **TEST EXECUTION LOGS**
│   └── test_execution_*.log
│
├── DOCUMENTATION/
│   ├── EXECUTIVE_SUMMARY.md
│   ├── EXTENT_REPORT_GUIDE.md ⬅️ **YOU'RE HERE**
│   ├── SAUCEDEMO_STEPS_ISSUES_RESOLVED.md
│   ├── ISSUE_RESOLUTION_COMPLETE.md
│   └── (other documentation files)
│
├── pom.xml
├── PROJECT_RESOLUTION_COMPLETE.md
├── QUICK_RESOLUTION_SUMMARY.md
├── README_ISSUE_RESOLVED.md
└── EXTENT_REPORT_GUIDE.md

```

---

## 🎯 EXTENT REPORT LOCATIONS

### 1. Main Report (After Running Tests)
```
📍 target/extent-reports/extent-report.html
```
**This is the file you need to open!**

### 2. Archived Reports (With Timestamps)
```
📍 target/extent-reports-archive/
    └── TestName_DDMMYYYY_hhmmss/
```

### 3. Cucumber Reports (Built-in)
```
📍 target/cucumber-reports/
    ├── cucumber.html
    ├── cucumber.json
    └── cucumber.xml
```

### 4. Test Logs
```
📍 logs/test_execution_*.log
```

---

## 📋 Configuration Files Location

### Extent Configuration
```
📍 src/test/resources/extent.properties ✅ (FIXED)
   ├── Report output path
   ├── Theme settings
   ├── Screenshot configuration
   └── System information
```

### Extent XML Configuration
```
📍 src/test/resources/extent-config.xml
   ├── Report formatting
   ├── Document title
   ├── Theme selection
   └── Advanced settings
```

---

## 🚀 Quick Navigation

### To Access Extent Report
```bash
# Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# Run tests
mvn clean test

# The report will be at:
# target/extent-reports/extent-report.html
```

### To Open Report Directly
```bash
# Windows
start target\extent-reports\extent-report.html

# Or just double-click the file:
# C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber\target\extent-reports\extent-report.html
```

---

## 📊 Report Generation Flow

```
1. Run: mvn clean test
              ↓
2. Cucumber TestRunner starts
              ↓
3. ExtentCucumberAdapter initialized
              ↓
4. extent.properties loaded
   └─ Report path: target/extent-reports/
   └─ Theme: dark
   └─ Config: extent-config.xml
              ↓
5. Tests execute
   └─ Steps logged
   └─ Results captured
   └─ Screenshots attached (on failure)
              ↓
6. Report generated
   └─ HTML file created
   └─ Assets compiled
   └─ Report ready
              ↓
7. Access Report
   └─ Open: target/extent-reports/extent-report.html
              ↓
8. Archive Report
   └─ Saved with timestamp
   └─ Location: target/extent-reports-archive/
```

---

## ✅ What Was Fixed

### extent.properties
| Setting | Before | After |
|---------|--------|-------|
| Report path | `extent.html` | `extent-report.html` ✅ |
| Report directory | `target/extent-reports/` | `target/extent-reports/` ✅ |
| Theme | `standard` | `dark` ✅ |
| Config file | `extent-config.xml` | `src/test/resources/extent-config.xml` ✅ |
| Screenshots on fail | `true` | `true` ✅ |

---

## 📂 Directory Reference

### Source Code
```
src/test/java/                 - Step definitions
src/test/resources/            - Configuration files
src/test/resources/feature/    - Feature files
```

### Generated Reports
```
target/extent-reports/         - Main Extent Report ⬅️ YOU ARE HERE
target/cucumber-reports/       - Cucumber built-in report
target/extent-reports-archive/ - Archived reports
logs/                          - Execution logs
```

### Documentation
```
DOCUMENTATION/                 - All guides and references
README_ISSUE_RESOLVED.md       - Quick summary
PROJECT_RESOLUTION_COMPLETE.md - Complete overview
EXTENT_REPORT_GUIDE.md         - This guide
```

---

## 🔍 Finding Your Report

### Windows Explorer
1. Open: `C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber`
2. Navigate to: `target/extent-reports/`
3. Open: `extent-report.html` in browser

### Command Line
```bash
# Navigate to project
cd C:\Users\akash\eclipse-workspace\SauceDemo_Automation_Cucmber

# List report files
dir target\extent-reports\

# Open report
start target\extent-reports\extent-report.html
```

### IDE (IntelliJ/Eclipse)
1. Open Project Explorer
2. Navigate to: `target/extent-reports/`
3. Right-click `extent-report.html`
4. Select: "Open in Browser"

---

## 📝 Report Details

### File Information
| Property | Value |
|----------|-------|
| File Name | `extent-report.html` |
| Format | HTML5 |
| Size | ~1-5 MB (varies) |
| Theme | Dark |
| Generated After | Each test run |
| Auto-opens | Yes (if configured) |
| Browser Compatible | All modern browsers |

### Report Contents
- Dashboard with statistics
- Test cases and results
- Step-by-step execution
- Screenshots (on failure)
- System information
- Execution logs
- Error stack traces

---

## ✨ Summary

✅ **Extent Report Location Fixed**
- Path: `target/extent-reports/extent-report.html`
- Theme: Dark
- Configuration: Updated and working

✅ **You Can Now**
1. Run tests: `mvn clean test`
2. Find report: `target/extent-reports/extent-report.html`
3. Open in browser
4. Review all test details

✅ **Report will include**
- All test results
- Step-by-step execution
- Error details (if any)
- Screenshots (on failure)
- System information
- Complete logs

---

**Next Action**: Run `mvn clean test` to generate your Extent Report! 🎉

