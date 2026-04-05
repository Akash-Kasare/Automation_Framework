package steps;

import factory.BaseClass;
import utils.TestLogger;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends BaseClass {

    private long scenarioStartTime;

    /**
     * Hook to execute before each scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        BaseClass.getDriver(); // Initialize driver
        scenarioStartTime = System.currentTimeMillis();
        TestLogger.scenarioStart(scenario.getName());
        TestLogger.info("Scenario Tags: " + scenario.getSourceTagNames());
    }

    /**
     * Hook to execute after each scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        long scenarioDuration = System.currentTimeMillis() - scenarioStartTime;
        String status = scenario.isFailed() ? "FAILED" : "PASSED";

        TestLogger.scenarioCompleted(scenario.getName(), status, scenarioDuration);

        // Take screenshot if scenario failed (for local storage/history)
        // Note: ExtentReportListener also handles screenshot for the report
        if (scenario.isFailed()) {
            TestLogger.error("Scenario failed. Saving screenshot for local logs...");
            utils.ExtentReportUtils.captureScreenshot(BaseClass.getDriver(), scenario.getName().replaceAll(" ", "_"));
        }

        // Close the driver
        try {
            BaseClass.closeDriver();
            TestLogger.info("WebDriver closed successfully");
        } catch (Exception e) {
            TestLogger.error("Error closing driver: " + e.getMessage());
        }
    }

    /**
     * Method to take screenshot on failure (Deprecated: use ExtentReportUtils instead)
     */
    private void takeScreenshot(String scenarioName) {
        // Method kept for internal consistency but logic moved to ExtentReportUtils
        utils.ExtentReportUtils.captureScreenshot(BaseClass.getDriver(), scenarioName.replaceAll(" ", "_"));
    }
}


