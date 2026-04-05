package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
    @CucumberOptions(
    features = "src/test/resources/feature",
    glue = {"steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml",
        "listeners.ExtentReportListener"
    }

    // Tags (@Login, @ShoppingCart, @Checkout, @Inventory,
)
public class TestRunner {

    // Static block to archive reports after test execution
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n========== Archiving Reports ==========");
            ReportArchiver.archiveReports();
            ReportArchiver.listArchivedReports();
            System.out.println("========== Archive Complete ==========\n");
        }));
    }

    // TestRunner configuration:
    // - Runs on Chrome browser (configured in BaseClass.java)
    // - Generates HTML, JSON, and JUnit reports
    // - Generates Extent Report for detailed test execution
    // - Shows UI during execution
    // - Logs are automatically created in logs/ directory
    // - Reports archived with format: TestcaseName_DDMMYYYY_hhmmss
    // - Archive locations:
    //   - Extent Reports: target/extent-reports-archive/
    //   - Logs: logs/archive/
}




