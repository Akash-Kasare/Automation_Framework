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

            // Custom handler to redirect logs to System.out instead of System.err
            java.util.logging.StreamHandler stdoutHandler = new java.util.logging.StreamHandler(System.out, new CustomFormatter()) {
                @Override
                public synchronized void publish(LogRecord record) {
                    super.publish(record);
                    flush(); // Ensure log is printed immediately
                }
            };
            stdoutHandler.setLevel(Level.ALL);
            logger.addHandler(stdoutHandler);

            // Disable parent handlers to prevent duplicate logging
            logger.setUseParentHandlers(false);

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
            String message = record.getMessage();
            String status = "EXEC"; // Default status

            // Extract status if message starts with [STATUS]
            if (message.startsWith("[")) {
                int endIdx = message.indexOf("]");
                if (endIdx > 0) {
                    String possibleStatus = message.substring(1, endIdx);
                    // Check if it's a known status
                    if (isKnownStatus(possibleStatus)) {
                        status = possibleStatus;
                        message = message.substring(endIdx + 1).trim();
                    }
                }
            }

            return String.format(
                "[%-5s] [%-7s] [%s] %s%n",
                record.getLevel(),
                status,
                LocalDateTime.now().format(timeFormatter),
                message
            );
        }

        private boolean isKnownStatus(String status) {
            return status.equals("PASSED") || status.equals("FAILED") || 
                   status.equals("SKIPPED") || status.equals("PENDING") || 
                   status.equals("AMBIGUOUS") || status.equals("UNDEFINED") ||
                   status.equals("STARTED") || status.equals("COMPLETED") ||
                   status.equals("INFO") || status.equals("WARNING") ||
                   status.equals("ERROR");
        }
    }

    /**
     * Log step start with timing
     */
    public static void stepStart(String stepDescription) {
        stepStartTime = System.currentTimeMillis();
        currentStep = stepDescription;
        String message = String.format("[STARTED] Step: %s", stepDescription);
        info(message);
    }

    /**
     * Log step passed with execution time
     */
    public static void stepPassed(String message) {
        long duration = System.currentTimeMillis() - stepStartTime;
        String logMessage = String.format("[PASSED] Step: %s | Message: %s | Time: %d ms", 
            currentStep, message, duration);
        info(logMessage);
    }

    /**
     * Log step failed with error details
     */
    public static void stepFailed(String errorMessage, Throwable e) {
        long duration = System.currentTimeMillis() - stepStartTime;
        String logMessage = String.format("[FAILED] Step: %s | Error: %s | Time: %d ms", 
            currentStep, errorMessage, duration);
        error(logMessage);

        if (e != null) {
            error("[ERROR] Exception: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    /**
     * Log step skipped
     */
    public static void stepSkipped(String reason) {
        String message = String.format("[SKIPPED] Step: %s | Reason: %s", 
            currentStep, reason);
        warning(message);
    }

    /**
     * Log scenario start
     */
    public static void scenarioStart(String scenarioName) {
        info("[STARTED] SCENARIO: " + scenarioName);
    }

    /**
     * Log scenario completed with status
     */
    public static void scenarioCompleted(String scenarioName, String status, long duration) {
        String message = String.format("[%s] SCENARIO: %s | Time: %d ms", 
            status.toUpperCase(), scenarioName, duration);

        if (status.equalsIgnoreCase("PASSED")) {
            info(message);
        } else {
            error(message);
        }
    }

    /**
     * Log test suite start
     */
    public static void testSuiteStart(String suiteName) {
        info("[STARTED] SUITE: " + suiteName);
    }

    /**
     * Log test suite completion with summary
     */
    public static void testSuiteCompleted(String suiteName, int totalTests, int passed,
                                         int failed, int skipped, long duration) {
        String status = failed == 0 ? "PASSED" : "FAILED";
        String message = String.format("[%s] SUITE: %s | Total: %d | P: %d | F: %d | S: %d | Time: %d ms",
            status, suiteName, totalTests, passed, failed, skipped, duration);

        if (failed == 0) {
            info(message);
        } else {
            error(message);
        }
    }

    /**
     * Log info message
     */
    public static void info(String message) {
        if (!message.startsWith("[")) {
            message = "[INFO] " + message;
        }
        logger.info(message);
    }

    /**
     * Log warning message
     */
    public static void warning(String message) {
        if (!message.startsWith("[")) {
            message = "[WARNING] " + message;
        }
        logger.warning(message);
    }

    /**
     * Log error message
     */
    public static void error(String message) {
        if (!message.startsWith("[")) {
            message = "[ERROR] " + message;
        }
        logger.severe(message);
    }

    /**
     * Log debug message
     */
    public static void debug(String message) {
        if (!message.startsWith("[")) {
            message = "[DEBUG] " + message;
        }
        logger.fine(message);
    }

    /**
     * Log WebDriver action
     */
    public static void action(String element, String action, String details) {
        String message = String.format(
            "[ACTION] → %s | Element: %s | Details: %s",
            action,
            element,
            details
        );
        info(message);
    }

    /**
     * Log assertion
     */
    public static void assertion(String condition, boolean result, String message) {
        String status = result ? "PASSED" : "FAILED";
        String logMessage = String.format(
            "[%s] Assertion: %s | Expected: %s",
            status,
            condition,
            message
        );
        if (result) {
            info(logMessage);
        } else {
            error(logMessage);
        }
    }

    /**
     * Log with execution time
     */
    public static void timingLog(String operation, long durationMs) {
        info(String.format("[TIME] %s completed in %d ms", operation, durationMs));
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

