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

        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            TestLogger.error("Scenario failed. Taking screenshot...");
            takeScreenshot(scenario.getName());
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
     * Method to take screenshot on failure
     */
    private void takeScreenshot(String scenarioName) {
        try {
            // Create screenshots folder if it doesn't exist
            File screenshotsDir = new File("src/test/resources/screenshots");
            if (!screenshotsDir.exists()) {
                boolean created = screenshotsDir.mkdirs();
                if (!created) {
                    TestLogger.warning("Failed to create screenshots directory");
                }
            }

            // Generate timestamp for unique filename
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = scenarioName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            String filePath = "src/test/resources/screenshots/" + fileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) BaseClass.driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);

            TestLogger.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            TestLogger.error("Failed to take screenshot: " + e.getMessage());
        }
    }
}


