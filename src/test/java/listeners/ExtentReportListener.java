package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import utils.TestLogger;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cucumber EventListener for Extent Report
 * Captures:
 * - testRunStarted: Test execution initialization
 * - testCaseStarted: Test case started
 * - testCaseFinished: Test case finished with status
 * - testRunFinished: Report generation
 */
public class ExtentReportListener implements EventListener {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    private static final String REPORT_PATH = "target/extent-reports";
    private static String REPORT_FILE;

    static {
        // Initialize report file name with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        REPORT_FILE = REPORT_PATH + "/SauceDemo_Report_" + timestamp + ".html";
    }

    /**
     * Initialize Extent Report when test run starts
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, event -> onTestRunStarted());
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    /**
     * Called when test run starts
     */
    private void onTestRunStarted() {
        // Refresh the timestamp and file name at the start of the run
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        REPORT_FILE = REPORT_PATH + "/SauceDemo_Report_" + timestamp + ".html";

        TestLogger.info("========== Extent Report Initialization Started ==========");

        // Create report directory
        File reportDir = new File(REPORT_PATH);
        if (!reportDir.exists()) {
            if (reportDir.mkdirs()) {
                TestLogger.info("Created report directory: " + REPORT_PATH);
            }
        }

        // Initialize Extent Reports with the timestamped file
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_FILE);

        // Configure reporter
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("SauceDemo Automation Test Report");
        sparkReporter.config().setReportName("SauceDemo Test Execution Report");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        // Initialize ExtentReports
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Set system information
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Operating System", "Windows 11");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Application URL", "https://www.saucedemo.com/");
        extentReports.setSystemInfo("Framework", "Cucumber + Selenium + Extent Reports");
        extentReports.setSystemInfo("Execution Type", "Automated");

        TestLogger.info("Extent Report initialized: " + REPORT_FILE);
        TestLogger.info("========== Extent Report Initialization Completed ==========");
    }

    /**
     * Called when test case starts
     */
    private void onTestCaseStarted(TestCaseStarted event) {
        String testName = event.getTestCase().getName();
        TestLogger.info("========== Test Case Started: " + testName + " ==========");

        // Create test node in report
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, "Test execution started: " + testName);

        // Log start time
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        extentTest.log(Status.INFO, "Start Time: " + timestamp);
    }

    /**
     * Called when test step starts
     */
    private void onTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepText = step.getStep().getText();
            TestLogger.info("Step: " + stepText);
        }
    }

    /**
     * Called when test step finishes
     */
    private void onTestStepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepText = step.getStep().getText();

            Result result = event.getResult();
            switch (result.getStatus()) {
                case PASSED:
                    TestLogger.info("[PASSED] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.PASS, "✓ " + stepText);
                    }
                    break;
                case FAILED:
                    TestLogger.error("[FAILED] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.FAIL, "✗ " + stepText);
                        if (result.getError() != null) {
                            extentTest.log(Status.FAIL, "Error: " + result.getError().getMessage());
                        }
                        
                        // Use ExtentReportUtils.onStepFailure to capture and attach screenshot
                        utils.ExtentReportUtils.onStepFailure(factory.BaseClass.getDriver(), stepText, extentTest);
                    }
                    break;
                case SKIPPED:
                    TestLogger.warning("[SKIPPED] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.SKIP, "⊙ " + stepText);
                    }
                    break;
                case PENDING:
                    TestLogger.warning("[PENDING] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.WARNING, "⚠ " + stepText);
                    }
                    break;
                case AMBIGUOUS:
                    TestLogger.error("[AMBIGUOUS] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.WARNING, "⚠ " + stepText);
                    }
                    break;
                case UNDEFINED:
                    TestLogger.error("[UNDEFINED] " + stepText);
                    if (extentTest != null) {
                        extentTest.log(Status.FAIL, "✗ " + stepText);
                    }
                    break;
            }
        }
    }

    /**
     * Called when test case finishes
     */
    private void onTestCaseFinished(TestCaseFinished event) {
        Result result = event.getResult();
        String testName = event.getTestCase().getName();
        long duration = result.getDuration().toMillis();
        String status = result.getStatus().name();
        
        // Retrieve test data from Hooks context
        java.util.Map<String, String> dataUsed = null;
        if (steps.Hooks.getTestContext() != null) {
            dataUsed = steps.Hooks.getTestContext().getAllTestData();
        }

        // Write to Excel Execution Report
        utils.ExcelUtils.writeExecutionReport(testName, dataUsed, status, duration);

        switch (result.getStatus()) {
            case PASSED:
                TestLogger.info("========== Test Case PASSED: " + testName + " ==========");
                if (extentTest != null) {
                    extentTest.log(Status.PASS, "Test Case Passed: " + testName);
                    extentTest.log(Status.INFO, "Execution Time: " + duration + " ms");
                }
                break;
            case FAILED:
                TestLogger.error("========== Test Case FAILED: " + testName + " ==========");
                if (extentTest != null) {
                    extentTest.log(Status.FAIL, "Test Case Failed: " + testName);
                    if (result.getError() != null) {
                        extentTest.log(Status.FAIL, "Exception: " + result.getError().getMessage());
                        extentTest.log(Status.FAIL, "Stack Trace:\n" + getStackTrace(result.getError()));
                    }
                    extentTest.log(Status.INFO, "Execution Time: " + duration + " ms");
                }
                break;
            case SKIPPED:
                TestLogger.warning("========== Test Case SKIPPED: " + testName + " ==========");
                if (extentTest != null) {
                    extentTest.log(Status.SKIP, "Test Case Skipped: " + testName);
                }
                break;
        }
    }

    /**
     * Called when test run finishes
     */
    private void onTestRunFinished(TestRunFinished event) {
        TestLogger.info("========== Test Run Finished ==========");

        // Flush report
        if (extentReports != null) {
            extentReports.flush();
            TestLogger.info("Extent Report generated: " + REPORT_FILE);
        }

        TestLogger.info("========== Test Execution Completed ==========");
    }

    /**
     * Get stack trace as string
     */
    private String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Get report file path
     */
    public static String getReportFilePath() {
        return REPORT_FILE;
    }

    /**
     * Get report directory
     */
    public static String getReportDirectory() {
        return REPORT_PATH;
    }
}

