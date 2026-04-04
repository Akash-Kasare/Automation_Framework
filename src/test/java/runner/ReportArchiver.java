package runner;

import utils.TestLogger;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Report Archiver
 * Archives and renames test reports with format: TestcaseName_DDMMYYYY_hhmmss
 * Stores logs and extent reports in organized folders
 */
public class ReportArchiver {

    private static final String EXTENT_REPORT_SOURCE = "target/extent-reports/extent-report.html";
    private static final String EXTENT_REPORT_ARCHIVE = "target/extent-reports-archive";
    private static final String LOGS_ARCHIVE = "logs/archive";

    /**
     * Archive all reports after test execution
     * Creates timestamped folders and renames files
     */
    public static void archiveReports() {
        TestLogger.info("========== Starting Report Archive Process ==========");

        try {
            // Create archive directories
            createArchiveDirectories();

            // Get testcase name from latest execution
            String testcaseName = extractTestcaseNameFromReport();

            // Format: TestcaseName_DDMMYYYY_hhmmss
            String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
            String archivedFileName = testcaseName + "_" + timestamp;

            // Archive extent report
            archiveExtentReport(archivedFileName);

            // Archive logs
            archiveLogs(archivedFileName);

            TestLogger.info("========== Report Archive Completed ==========");
            TestLogger.info("Archived with name: " + archivedFileName);

        } catch (Exception e) {
            TestLogger.error("Error archiving reports: " + e.getMessage());
        }
    }

    /**
     * Create archive directories if they don't exist
     */
    private static void createArchiveDirectories() {
        try {
            File extentArchiveDir = new File(EXTENT_REPORT_ARCHIVE);
            File logsArchiveDir = new File(LOGS_ARCHIVE);

            if (!extentArchiveDir.exists()) {
                if (extentArchiveDir.mkdirs()) {
                    TestLogger.info("Created extent report archive directory: " + EXTENT_REPORT_ARCHIVE);
                }
            }

            if (!logsArchiveDir.exists()) {
                if (logsArchiveDir.mkdirs()) {
                    TestLogger.info("Created logs archive directory: " + LOGS_ARCHIVE);
                }
            }
        } catch (Exception e) {
            TestLogger.error("Error creating archive directories: " + e.getMessage());
        }
    }

    /**
     * Archive extent report HTML
     */
    private static void archiveExtentReport(String archivedFileName) {
        try {
            File sourceFile = new File(EXTENT_REPORT_SOURCE);

            if (sourceFile.exists()) {
                String archivedPath = EXTENT_REPORT_ARCHIVE + File.separator + archivedFileName + ".html";
                File archivedFile = new File(archivedPath);

                // Copy file
                Files.copy(sourceFile.toPath(), archivedFile.toPath());

                TestLogger.info("Extent report archived: " + archivedPath);
                TestLogger.info("Archive file name: " + archivedFileName + ".html");

            } else {
                TestLogger.warning("Extent report not found at: " + EXTENT_REPORT_SOURCE);
            }
        } catch (Exception e) {
            TestLogger.error("Error archiving extent report: " + e.getMessage());
        }
    }

    /**
     * Archive log files
     */
    private static void archiveLogs(String archivedFileName) {
        try {
            File logsDir = new File("logs");

            if (logsDir.exists() && logsDir.isDirectory()) {
                File[] logFiles = logsDir.listFiles((dir, name) -> name.endsWith(".log"));

                if (logFiles != null && logFiles.length > 0) {
                    for (File logFile : logFiles) {
                        String archivedPath = LOGS_ARCHIVE + File.separator + archivedFileName + ".log";
                        Files.copy(logFile.toPath(), Paths.get(archivedPath));

                        TestLogger.info("Log file archived: " + archivedPath);
                    }
                } else {
                    TestLogger.warning("No log files found in logs directory");
                }
            } else {
                TestLogger.warning("Logs directory not found");
            }
        } catch (Exception e) {
            TestLogger.error("Error archiving logs: " + e.getMessage());
        }
    }

    /**
     * Extract testcase name from extent report
     * Default: "TestExecution" if cannot extract
     */
    private static String extractTestcaseNameFromReport() {
        try {
            File reportFile = new File(EXTENT_REPORT_SOURCE);

            if (reportFile.exists()) {
                // Read report and extract first testcase name
                String content = new String(Files.readAllBytes(reportFile.toPath()));

                // Try to extract testcase name from report
                int testStart = content.indexOf("<div class=\"test\"");
                if (testStart > 0) {
                    int nameStart = content.indexOf("data-test-name=\"", testStart);
                    if (nameStart > 0) {
                        nameStart += 16;
                        int nameEnd = content.indexOf("\"", nameStart);
                        if (nameEnd > nameStart) {
                            String testname = content.substring(nameStart, nameEnd);
                            TestLogger.info("Extracted testcase name: " + testname);
                            return testname.replaceAll("[^a-zA-Z0-9_]", "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            TestLogger.warning("Error extracting testcase name: " + e.getMessage());
        }

        // Default if cannot extract
        TestLogger.info("Using default testcase name: TestExecution");
        return "TestExecution";
    }

    /**
     * Get archived extent report path
     */
    public static String getArchivedExtentReportPath() {
        return EXTENT_REPORT_ARCHIVE;
    }

    /**
     * Get archived logs path
     */
    public static String getArchivedLogsPath() {
        return LOGS_ARCHIVE;
    }

    /**
     * List all archived reports
     */
    public static void listArchivedReports() {
        TestLogger.info("========== Archived Reports ==========");

        try {
            File extentArchive = new File(EXTENT_REPORT_ARCHIVE);
            File logsArchive = new File(LOGS_ARCHIVE);

            TestLogger.info("Extent Reports Archive (" + EXTENT_REPORT_ARCHIVE + "):");
            if (extentArchive.exists() && extentArchive.isDirectory()) {
                File[] reports = extentArchive.listFiles();
                if (reports != null && reports.length > 0) {
                    for (File report : reports) {
                        TestLogger.info("  - " + report.getName());
                    }
                } else {
                    TestLogger.info("  (No archived reports)");
                }
            }

            TestLogger.info("\nLogs Archive (" + LOGS_ARCHIVE + "):");
            if (logsArchive.exists() && logsArchive.isDirectory()) {
                File[] logs = logsArchive.listFiles();
                if (logs != null && logs.length > 0) {
                    for (File log : logs) {
                        TestLogger.info("  - " + log.getName());
                    }
                } else {
                    TestLogger.info("  (No archived logs)");
                }
            }

        } catch (Exception e) {
            TestLogger.error("Error listing archived reports: " + e.getMessage());
        }
    }
}


