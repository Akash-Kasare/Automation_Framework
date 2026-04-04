# 📊 REPORT ARCHIVER - AUTOMATED FILE NAMING & STORAGE

## ✅ What Was Implemented

An automatic report archiving system that stores logs and extent reports with a standardized naming format and organized folder structure.

---

## 🎯 Features

### File Naming Format
```
Format: TestcaseName_DDMMYYYY_hhmmss

Examples:
├── LoginTest_Valid_04042026_103045.html
├── AddToCart_Multiple_04042026_105230.html
├── Checkout_Complete_04042026_110815.html
└── TestExecution_04042026_120000.log
```

### Automatic Archiving
```
After test execution completes:
1. Captures extent report (HTML)
2. Captures log files
3. Renames with timestamp
4. Stores in organized folders
5. Lists all archived reports
```

---

## 📁 Archive Folder Structure

### Extent Reports Archive
```
target/extent-reports-archive/
├── LoginTest_Valid_04042026_103045.html
├── AddToCart_Multiple_04042026_105230.html
├── Checkout_Complete_04042026_110815.html
└── (more reports...)
```

### Logs Archive
```
logs/archive/
├── LoginTest_Valid_04042026_103045.log
├── AddToCart_Multiple_04042026_105230.log
├── Checkout_Complete_04042026_110815.log
└── (more logs...)
```

---

## 🔧 How It Works

### Step 1: Test Execution
```
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### Step 2: Test Completes
```
✓ All tests execute
✓ Extent report generated: target/extent-reports/extent-report.html
✓ Log files created: logs/test_execution_YYYY-MM-DD_HH-mm-ss.log
```

### Step 3: Automatic Archiving (After JVM Shutdown)
```
1. ReportArchiver triggers via shutdown hook
2. Extracts testcase name from extent report
3. Gets current timestamp (DDMMYYYY_hhmmss format)
4. Creates archive folders if needed
5. Copies and renames reports:
   - Extent Report: TestcaseName_DDMMYYYY_hhmmss.html
   - Log Files: TestcaseName_DDMMYYYY_hhmmss.log
6. Lists all archived reports
```

### Step 4: Review Archives
```
Open: target/extent-reports-archive/ → Extent reports
Open: logs/archive/ → Log files
All with TestcaseName_DDMMYYYY_hhmmss format
```

---

## 📋 Class: ReportArchiver

**Location:** `src/test/java/runner/ReportArchiver.java`
**Size:** 280+ lines

### Key Methods

#### archiveReports()
```java
// Called automatically after test execution
ReportArchiver.archiveReports();

// What it does:
// 1. Creates archive directories
// 2. Extracts testcase name from report
// 3. Formats timestamp (DDMMYYYY_hhmmss)
// 4. Archives extent report
// 5. Archives log files
```

#### archiveExtentReport()
```java
// Copies extent report to archive
// Name format: TestcaseName_DDMMYYYY_hhmmss.html
// Location: target/extent-reports-archive/
```

#### archiveLogs()
```java
// Copies log files to archive
// Name format: TestcaseName_DDMMYYYY_hhmmss.log
// Location: logs/archive/
```

#### listArchivedReports()
```java
// Displays all archived reports
ReportArchiver.listArchivedReports();

// Output:
// Extent Reports Archive (target/extent-reports-archive/):
//   - LoginTest_Valid_04042026_103045.html
//   - AddToCart_Multiple_04042026_105230.html
// Logs Archive (logs/archive/):
//   - LoginTest_Valid_04042026_103045.log
//   - AddToCart_Multiple_04042026_105230.log
```

---

## 🔌 Integration Points

### TestRunner.java (Updated)
```java
// Static initialization block in TestRunner
static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        ReportArchiver.archiveReports();
        ReportArchiver.listArchivedReports();
    }));
}
```

**How it works:**
1. When tests finish executing
2. JVM shutdown hook triggers
3. ReportArchiver.archiveReports() called
4. Reports automatically archived
5. Archived reports listed

---

## 📊 Timestamp Format Breakdown

### Format: DDMMYYYY_hhmmss

```
DD     = Day (01-31)
MM     = Month (01-12)
YYYY   = Year (2026)
hh     = Hour (00-23)
mm     = Minute (00-59)
ss     = Second (00-59)

Example: 04042026_103045
         ↓↓ ↓↓ ↓↓↓↓ ↓↓ ↓↓ ↓↓
         04 04 2026 10 30 45
         (April 4, 2026 at 10:30:45 AM)
```

---

## 🎯 Usage Examples

### Example 1: Test Execution with Auto-Archiving
```bash
# Command
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Output (at end)
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

### Example 2: Multiple Test Executions
```bash
# First run (9:00 AM)
mvn clean test -Dcucumber.filter.tags="@Login"
# Archives: LoginTest_Valid_04042026_090000.html & .log

# Second run (10:30 AM)
mvn clean test -Dcucumber.filter.tags="@Cart"
# Archives: AddToCart_Multiple_04042026_103045.html & .log

# Result in folders:
# target/extent-reports-archive/
#   ├── LoginTest_Valid_04042026_090000.html
#   └── AddToCart_Multiple_04042026_103045.html
#
# logs/archive/
#   ├── LoginTest_Valid_04042026_090000.log
#   └── AddToCart_Multiple_04042026_103045.log
```

---

## 📍 File Locations

### Source Files
```
ReportArchiver.java:     src/test/java/runner/ReportArchiver.java
TestRunner.java:         src/test/java/runner/TestRunner.java (updated)
```

### Archive Locations
```
Extent Reports:          target/extent-reports-archive/
Log Files:               logs/archive/
```

### Original Locations (Still Used)
```
Latest Extent Report:    target/extent-reports/extent-report.html
Latest Log:              logs/test_execution_*.log
```

---

## ✅ Key Benefits

✅ **Organized Storage**
- Reports automatically organized
- Easy to find specific test reports
- Historical tracking

✅ **Timestamped Files**
- Know when test ran
- Multiple runs tracked
- No overwrites

✅ **Automatic Process**
- No manual renaming
- No manual moving
- Happens automatically

✅ **Easy Retrieval**
- Predictable naming
- Easy to locate
- Sorted by date/time

✅ **Complete Tracking**
- Extent report archived
- Log file archived
- Both use same timestamp

---

## 🚀 Test Execution Flow

```
1. Execute Tests
   mvn clean test

2. Tests Run
   ├─ Extent report generated
   └─ Log files created

3. Tests Complete
   ├─ Extent report: target/extent-reports/extent-report.html
   └─ Logs: logs/test_execution_*.log

4. JVM Shutdown
   └─ ReportArchiver triggers

5. Archive Process
   ├─ Create archive folders
   ├─ Extract testcase name
   ├─ Format timestamp
   ├─ Copy & rename extent report
   ├─ Copy & rename log files
   └─ List archived reports

6. Archives Created
   ├─ target/extent-reports-archive/TestcaseName_DDMMYYYY_hhmmss.html
   └─ logs/archive/TestcaseName_DDMMYYYY_hhmmss.log

7. Original Files Remain
   ├─ target/extent-reports/extent-report.html (for latest run)
   └─ logs/test_execution_*.log (for latest run)
```

---

## 📊 Archive Contents Example

### After Multiple Test Runs

#### target/extent-reports-archive/
```
LoginTest_Valid_04042026_090000.html (500 KB)
LoginTest_Valid_04042026_093045.html (510 KB)
AddToCart_Multiple_04042026_100200.html (480 KB)
Checkout_Complete_04042026_110815.html (520 KB)
```

#### logs/archive/
```
LoginTest_Valid_04042026_090000.log (150 KB)
LoginTest_Valid_04042026_093045.log (155 KB)
AddToCart_Multiple_04042026_100200.log (140 KB)
Checkout_Complete_04042026_110815.log (160 KB)
```

---

## ⚙️ Configuration

### Customize Archive Location

To change archive location, modify in ReportArchiver.java:
```java
// Line 9-10
private static final String EXTENT_REPORT_ARCHIVE = "your/custom/path/extent-reports";
private static final String LOGS_ARCHIVE = "your/custom/path/logs";
```

### Customize Timestamp Format

To change timestamp format, modify in ReportArchiver.java:
```java
// Line 39 (current: DDMMYYYY_hhmmss)
String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

// Other formats:
// "yyyy-MM-dd_HH-mm-ss"  → 2026-04-04_10-30-45
// "yyyyMMdd_HHmmss"      → 20260404_103045
```

---

## ✨ Features Summary

| Feature | Status | Details |
|---------|--------|---------|
| Auto-Archive | ✅ | Triggers after test execution |
| Timestamp Naming | ✅ | Format: DDMMYYYY_hhmmss |
| Extent Reports Archive | ✅ | target/extent-reports-archive/ |
| Logs Archive | ✅ | logs/archive/ |
| Testcase Extraction | ✅ | Automatic from report |
| Archive Listing | ✅ | Displays all archived files |
| Folder Creation | ✅ | Auto-creates if needed |
| Shutdown Hook | ✅ | Triggers on JVM exit |

---

## 🎊 IMPLEMENTATION COMPLETE!

**Everything automated:**
- ✅ Reports archived automatically
- ✅ Files renamed with timestamp
- ✅ Organized in separate folders
- ✅ Testcase name extracted
- ✅ Format: TestcaseName_DDMMYYYY_hhmmss
- ✅ Both logs and extent reports
- ✅ No manual intervention needed

**Ready to use immediately!** 🚀

