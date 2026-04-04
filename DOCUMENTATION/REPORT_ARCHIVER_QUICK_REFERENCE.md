# 📊 REPORT ARCHIVER - QUICK REFERENCE

## 🎯 What It Does

Automatically archives logs and extent reports after test execution with standardized naming format.

---

## 📋 Naming Format

```
TestcaseName_DDMMYYYY_hhmmss

Example:
LoginTest_Valid_04042026_103045.html
└──────┬──────┘ └──────┬──────┘ └────┬────┘
    Testcase Name   Date       Time

Date Format: DD = Day, MM = Month, YYYY = Year
Time Format: hh = Hour, mm = Minute, ss = Second
```

---

## 📁 Archive Locations

### Extent Reports
```
target/extent-reports-archive/
├── LoginTest_Valid_04042026_103045.html
├── AddToCart_Multiple_04042026_105230.html
└── (more reports...)
```

### Log Files
```
logs/archive/
├── LoginTest_Valid_04042026_103045.log
├── AddToCart_Multiple_04042026_105230.log
└── (more logs...)
```

---

## 🚀 How to Use

### 1. Run Tests (Normal Command)
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### 2. Automatic Archiving Happens
```
✓ Tests complete
✓ Reports generated
✓ ReportArchiver triggers
✓ Files renamed with timestamp
✓ Stored in archive folders
✓ List displayed on console
```

### 3. Access Archived Reports
```
Extent Reports:  target/extent-reports-archive/TestcaseName_DDMMYYYY_hhmmss.html
Log Files:       logs/archive/TestcaseName_DDMMYYYY_hhmmss.log
```

---

## 📊 File Organization

### Before Archiving
```
After Test Run:
├── target/extent-reports/extent-report.html (latest)
├── logs/test_execution_2026-04-04_10-30-45.log (latest)
```

### After Archiving
```
Archived:
├── target/extent-reports-archive/
│   └── LoginTest_Valid_04042026_103045.html ← Renamed & archived
├── logs/archive/
│   └── LoginTest_Valid_04042026_103045.log ← Renamed & archived
```

---

## 💡 Key Features

✅ **Automatic**
- No manual archiving needed
- Triggers after tests complete

✅ **Timestamped**
- Format: DDMMYYYY_hhmmss
- Easy to identify when run

✅ **Organized**
- Separate folders for each type
- Multiple runs stored safely

✅ **Complete**
- Both logs and reports archived
- Same timestamp for both

---

## 📝 Console Output Example

```
========== Archiving Reports ==========
[INFO] Extracted testcase name: LoginTest_Valid
[INFO] Created extent report archive directory: target/extent-reports-archive
[INFO] Extent report archived: target/extent-reports-archive/LoginTest_Valid_04042026_103045.html
[INFO] Created logs archive directory: logs/archive
[INFO] Log file archived: logs/archive/LoginTest_Valid_04042026_103045.log

========== Archived Reports ==========
Extent Reports Archive (target/extent-reports-archive/):
  - LoginTest_Valid_04042026_103045.html

Logs Archive (logs/archive/):
  - LoginTest_Valid_04042026_103045.log

========== Archive Complete ==========
```

---

## 🎯 Common Tasks

### View All Archived Reports
```
Manual:
1. Open folder: target/extent-reports-archive/
2. Open folder: logs/archive/
3. All reports with timestamps listed
```

### Find Specific Test Report
```
Format: TestcaseName_DDMMYYYY_hhmmss.html
Search: Look for your testcase name + date/time
```

### Retrieve Old Test Report
```
Go to: target/extent-reports-archive/
Find: TestcaseName_DDMMYYYY_hhmmss.html
Open: In browser to view results
```

---

## 🔍 File Naming Examples

### Different Testcases, Same Day
```
LoginTest_Valid_04042026_090000.html     (9:00 AM)
LoginTest_Valid_04042026_093045.html     (9:30 AM)
AddToCart_Multiple_04042026_100200.html  (10:02 AM)
Checkout_Complete_04042026_110815.html   (11:08 AM)
```

### Multiple Days
```
LoginTest_Valid_04042026_103045.html     (April 4, 2026)
LoginTest_Valid_05042026_093030.html     (April 5, 2026)
LoginTest_Valid_06042026_101515.html     (April 6, 2026)
```

---

## ⚙️ How It Works Behind the Scenes

```
1. Test Execution Complete
   ↓
2. JVM Shutdown Hook Triggers
   ↓
3. ReportArchiver.archiveReports() Called
   ├─ Create archive directories
   ├─ Extract testcase name from report
   ├─ Get current timestamp (DDMMYYYY_hhmmss)
   ├─ Copy extent report → archive folder
   ├─ Rename: TestcaseName_DDMMYYYY_hhmmss.html
   ├─ Copy log files → archive folder
   ├─ Rename: TestcaseName_DDMMYYYY_hhmmss.log
   └─ List archived reports
```

---

## 📍 Files Involved

```
ReportArchiver.java       src/test/java/runner/ReportArchiver.java
TestRunner.java           src/test/java/runner/TestRunner.java (updated)
```

---

## ✅ Checklist

- [x] ReportArchiver.java created
- [x] TestRunner.java updated with shutdown hook
- [x] Archive folders auto-created
- [x] Files renamed with timestamp
- [x] Both logs and reports archived
- [x] Original files preserved
- [x] Ready to use

---

## 🎊 Ready to Use!

Just run tests normally:
```bash
mvn clean test
```

Archives created automatically with:
- **Format:** TestcaseName_DDMMYYYY_hhmmss
- **Location:** 
  - Extent Reports: target/extent-reports-archive/
  - Logs: logs/archive/
- **No Manual Action Needed!**

---

**Everything automated and ready!** 🚀

