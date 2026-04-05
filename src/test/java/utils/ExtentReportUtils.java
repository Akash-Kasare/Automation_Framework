package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;

/**
 * Extent Report Utility
 * Provides additional functionality for Extent Reports
 * - Screenshot capture and attachment
 * - Test execution details logging
 * - Status color coding
 */
public class ExtentReportUtils {

    /**
     * Capture screenshot and return as Base64 string
     * Useful for embedding in reports
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String base64Screenshot = screenshot.getScreenshotAs(OutputType.BASE64);
            TestLogger.info("Screenshot captured and converted to Base64");
            return base64Screenshot;
        } catch (Exception e) {
            TestLogger.error("Failed to capture screenshot as Base64: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot and save to file
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots directory
            String screenshotsDir = "src/test/resources/screenshots";
            File directory = new File(screenshotsDir);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    TestLogger.warning("Could not create screenshots directory");
                }
            }

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String filePath = screenshotsDir + File.separator + fileName;

            // Capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);

            // Copy file
            Files.copy(source.toPath(), destination.toPath());

            TestLogger.info("Screenshot saved: " + filePath);
            return filePath;

        } catch (IOException e) {
            TestLogger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get screenshot as Base64 from file
     */
    public static String getScreenshotAsBase64(String filePath) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            TestLogger.error("Failed to convert screenshot to Base64: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handle step failure by capturing and attaching screenshot
     * @param driver - WebDriver instance
     * @param stepName - Name of the failed step
     * @param extentTest - ExtentTest object for the current scenario
     */
    public static void onStepFailure(WebDriver driver, String stepName, com.aventstack.extentreports.ExtentTest extentTest) {
        try {
            TestLogger.error("[FAILED] Step: " + stepName);
            
            // Capture screenshot as Base64
            String base64Screenshot = captureScreenshotAsBase64(driver);
            
            if (base64Screenshot != null && extentTest != null) {
                // Attach to extent report (raw base64 string, prefix is added by library)
                extentTest.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot: " + stepName);
                TestLogger.info("Screenshot attached to report for failed step: " + stepName);
                
                // Also save a physical file for local logs
                captureScreenshot(driver, stepName.replaceAll(" ", "_"));
            }
        } catch (Exception e) {
            TestLogger.error("Error during onStepFailure: " + e.getMessage());
        }
    }

    /**
     * Log test execution details to Extent Report
     */
    public static void logTestDetails(String category, String details) {
        TestLogger.info("[" + category + "] " + details);
    }

    /**
     * Log browser information
     */
    public static void logBrowserInfo(String browserName, String browserVersion, String osName) {
        TestLogger.info("Browser: " + browserName + " v" + browserVersion);
        TestLogger.info("OS: " + osName);
    }

    /**
     * Log environment information
     */
    public static void logEnvironmentInfo(String environment, String appUrl) {
        TestLogger.info("Environment: " + environment);
        TestLogger.info("Application URL: " + appUrl);
    }

    /**
     * Log test data used
     */
    public static void logTestDataUsed(String testcaseName, java.util.Map<String, String> testData) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTest Data Used for [").append(testcaseName).append("]:\n");

        for (java.util.Map.Entry<String, String> entry : testData.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        TestLogger.info(sb.toString());
    }

    /**
     * Log assertion result
     */
    public static void logAssertion(String condition, boolean result, String expectedValue, String actualValue) {
        String status = result ? "✓ PASS" : "✗ FAIL";
        String message = String.format(
            "%s | Condition: %s | Expected: %s | Actual: %s",
            status, condition, expectedValue, actualValue
        );

        if (result) {
            TestLogger.info(message);
        } else {
            TestLogger.error(message);
        }
    }

    /**
     * Log API request details (for API tests)
     */
    public static void logApiRequest(String method, String endpoint, String requestBody) {
        TestLogger.info("API Request:");
        TestLogger.info("  Method: " + method);
        TestLogger.info("  Endpoint: " + endpoint);
        TestLogger.info("  Body: " + requestBody);
    }

    /**
     * Log API response details (for API tests)
     */
    public static void logApiResponse(int statusCode, String responseBody) {
        TestLogger.info("API Response:");
        TestLogger.info("  Status Code: " + statusCode);
        TestLogger.info("  Body: " + responseBody);
    }

    /**
     * Log database query
     */
    public static void logDatabaseQuery(String query, String result) {
        TestLogger.info("Database Query:");
        TestLogger.info("  Query: " + query);
        TestLogger.info("  Result: " + result);
    }

    /**
     * Format status message for report
     */
    public static String formatStatusMessage(String testName, String status) {
        return String.format("[%s] %s", testName, status);
    }

    /**
     * Get report file path
     */
    public static String getReportPath() {
        return "target/extent-reports/extent.html";
    }

    /**
     * Check if report exists
     */
    public static boolean reportExists() {
        File reportFile = new File(getReportPath());
        return reportFile.exists();
    }

    /**
     * Get report generation time
     */
    public static String getReportGenerationTime() {
        File reportFile = new File(getReportPath());
        if (reportFile.exists()) {
            long lastModified = reportFile.lastModified();
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastModified));
        }
        return "N/A";
    }
}

