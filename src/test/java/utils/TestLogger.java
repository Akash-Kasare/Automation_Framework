package utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Custom Logger utility for test execution
 * Provides step-by-step logging with status, timing, and details
 */
public class TestLogger {

    private static final Logger logger = Logger.getLogger(TestLogger.class.getName());
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static long stepStartTime;
    private static String currentStep;

    static {
        try {
            // Create logs directory if it doesn't exist
            java.io.File logsDir = new java.io.File("logs");
            if (!logsDir.exists()) {
                if (!logsDir.mkdir()) {
                    System.err.println("Warning: Could not create logs directory");
                }
            }

            // File handler for logging to file
            String logFileName = "logs/test_execution_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".log";
            FileHandler fileHandler = new FileHandler(logFileName, true);
            fileHandler.setFormatter(new CustomFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            // Console handler for console output
            java.util.logging.ConsoleHandler consoleHandler = new java.util.logging.ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);

            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    /**
     * Custom formatter for better log readability
     */
    private static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return String.format(
                "[%s] [%s] %s%n",
                record.getLevel(),
                LocalDateTime.now().format(timeFormatter),
                record.getMessage()
            );
        }
    }

    /**
     * Log step start with timing
     */
    public static void stepStart(String stepDescription) {
        stepStartTime = System.currentTimeMillis();
        currentStep = stepDescription;
        String message = String.format(
            "\n╔════════════════════════════════════════════════════════════╗\n" +
            "║ STEP STARTED: %s\n" +
            "║ Timestamp: %s\n" +
            "╚════════════════════════════════════════════════════════════╝",
            padRight(stepDescription, 57),
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.info(message);
    }

    /**
     * Log step passed with execution time
     */
    public static void stepPassed(String message) {
        long duration = System.currentTimeMillis() - stepStartTime;
        String logMessage = String.format(
            "╔════════════════════════════════════════════════════════════╗\n" +
            "║ ✅ STEP PASSED\n" +
            "║ Step: %s\n" +
            "║ Message: %s\n" +
            "║ Execution Time: %s ms\n" +
            "║ Timestamp: %s\n" +
            "╚════════════════════════════════════════════════════════════╝",
            padRight(currentStep, 57),
            padRight(message, 57),
            duration,
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.info(logMessage);
    }

    /**
     * Log step failed with error details
     */
    public static void stepFailed(String errorMessage, Throwable e) {
        long duration = System.currentTimeMillis() - stepStartTime;
        String logMessage = String.format(
            "╔════════════════════════════════════════════════════════════╗\n" +
            "║ ❌ STEP FAILED\n" +
            "║ Step: %s\n" +
            "║ Error: %s\n" +
            "║ Exception: %s\n" +
            "║ Execution Time: %s ms\n" +
            "║ Timestamp: %s\n" +
            "╚════════════════════════════════════════════════════════════╝",
            padRight(currentStep, 57),
            padRight(errorMessage, 57),
            e != null ? padRight(e.getClass().getSimpleName() + ": " + e.getMessage(), 57) : "N/A",
            duration,
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.severe(logMessage);

        if (e != null) {
            logger.severe("Stack Trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                logger.severe("\t" + element.toString());
            }
        }
    }

    /**
     * Log step skipped
     */
    public static void stepSkipped(String reason) {
        String message = String.format(
            "╔════════════════════════════════════════════════════════════╗\n" +
            "║ ⏭️  STEP SKIPPED\n" +
            "║ Step: %s\n" +
            "║ Reason: %s\n" +
            "║ Timestamp: %s\n" +
            "╚════════════════════════════════════════════════════════════╝",
            padRight(currentStep, 57),
            padRight(reason, 57),
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.warning(message);
    }

    /**
     * Log scenario start
     */
    public static void scenarioStart(String scenarioName) {
        String message = String.format(
            "\n" +
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
            "┃ SCENARIO STARTED: %s\n" +
            "┃ Start Time: %s\n" +
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛",
            padRight(scenarioName, 57),
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.info(message);
    }

    /**
     * Log scenario completed with status
     */
    public static void scenarioCompleted(String scenarioName, String status, long duration) {
        String statusSymbol = status.equalsIgnoreCase("PASSED") ? "✅" : "❌";
        String message = String.format(
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
            "┃ %s SCENARIO COMPLETED: %s\n" +
            "┃ Status: %s\n" +
            "┃ Total Duration: %s ms\n" +
            "┃ End Time: %s\n" +
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛",
            statusSymbol,
            padRight(scenarioName, 57),
            status,
            duration,
            LocalDateTime.now().format(dateTimeFormatter)
        );

        if (status.equalsIgnoreCase("PASSED")) {
            logger.info(message);
        } else {
            logger.severe(message);
        }
    }

    /**
     * Log test suite start
     */
    public static void testSuiteStart(String suiteName) {
        String message = String.format(
            "\n\n" +
            "╔════════════════════════════════════════════════════════════╗\n" +
            "║        TEST SUITE STARTED\n" +
            "║        Suite: %s\n" +
            "║        Start Time: %s\n" +
            "╚════════════════════════════════════════════════════════════╝",
            padRight(suiteName, 57),
            LocalDateTime.now().format(dateTimeFormatter)
        );
        logger.info(message);
    }

    /**
     * Log test suite completion with summary
     */
    public static void testSuiteCompleted(String suiteName, int totalTests, int passed,
                                         int failed, int skipped, long duration) {
        double passPercentage = totalTests > 0 ? (passed * 100.0) / totalTests : 0;
        String message = String.format(
            "╔════════════════════════════════════════════════════════════╗\n" +
            "║        TEST SUITE COMPLETED\n" +
            "║        Suite: %s\n" +
            "║        Total Tests: %d\n" +
            "║        ✅ Passed: %d (%.2f%%)\n" +
            "║        ❌ Failed: %d\n" +
            "║        ⏭️  Skipped: %d\n" +
            "║        ⏱️  Total Duration: %s ms\n" +
            "║        End Time: %s\n" +
            "╚════════════════════════════════════════════════════════════╝\n",
            padRight(suiteName, 57),
            totalTests,
            passed,
            passPercentage,
            failed,
            skipped,
            duration,
            LocalDateTime.now().format(dateTimeFormatter)
        );

        if (failed == 0) {
            logger.info(message);
        } else {
            logger.severe(message);
        }
    }

    /**
     * Log info message
     */
    public static void info(String message) {
        logger.info("[INFO] " + message);
    }

    /**
     * Log warning message
     */
    public static void warning(String message) {
        logger.warning("[WARNING] " + message);
    }

    /**
     * Log error message
     */
    public static void error(String message) {
        logger.severe("[ERROR] " + message);
    }

    /**
     * Log debug message
     */
    public static void debug(String message) {
        logger.fine("[DEBUG] " + message);
    }

    /**
     * Log WebDriver action
     */
    public static void action(String element, String action, String details) {
        String message = String.format(
            "→ Action: %s | Element: %s | Details: %s",
            action,
            element,
            details
        );
        logger.info(message);
    }

    /**
     * Log assertion
     */
    public static void assertion(String condition, boolean result, String message) {
        String symbol = result ? "✓" : "✗";
        String logMessage = String.format(
            "%s Assertion: %s | Expected: %s | Result: %s",
            symbol,
            condition,
            message,
            result ? "PASSED" : "FAILED"
        );
        if (result) {
            logger.info(logMessage);
        } else {
            logger.severe(logMessage);
        }
    }

    /**
     * Log with execution time
     */
    public static void timingLog(String operation, long durationMs) {
        logger.info(String.format("⏱️  %s completed in %d ms", operation, durationMs));
    }

    /**
     * Helper method to pad strings for alignment
     */
    private static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Get logger instance for direct access if needed
     */
    public static Logger getLogger() {
        return logger;
    }
}

