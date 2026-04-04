package runner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Utility class to open HTML reports after test execution
 */
public class ReportGenerator {

    /**
     * Open the HTML report in default browser
     */
    public static void openReport() {
        try {
            // Path to the HTML report
            String reportPath = "target/cucumber-reports/cucumber.html";
            File reportFile = new File(reportPath);

            if (reportFile.exists()) {
                System.out.println("\n" +
                    "╔════════════════════════════════════════════════════════════╗\n" +
                    "║  OPENING CUCUMBER REPORT                                  ║\n" +
                    "║  Location: " + reportPath + "\n" +
                    "╚════════════════════════════════════════════════════════════╝\n");

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(reportFile.toURI());
                    System.out.println("✅ Report opened in default browser");
                } else {
                    System.out.println("⚠️  Desktop is not supported. Please open report manually:");
                    System.out.println("   " + reportFile.getAbsolutePath());
                }
            } else {
                System.out.println("❌ Report file not found: " + reportPath);
            }
        } catch (IOException e) {
            System.err.println("❌ Error opening report: " + e.getMessage());
        }
    }

    /**
     * Open the Extent report (advanced report)
     */
    public static void openExtentReport() {
        try {
            // Path to the Extent report
            String reportPath = "target/extent-reports/extent.html";
            File reportFile = new File(reportPath);

            if (reportFile.exists()) {
                System.out.println("\n" +
                    "╔════════════════════════════════════════════════════════════╗\n" +
                    "║  OPENING EXTENT REPORT                                    ║\n" +
                    "║  Location: " + reportPath + "\n" +
                    "╚════════════════════════════════════════════════════════════╝\n");

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(reportFile.toURI());
                    System.out.println("✅ Extent report opened in default browser");
                } else {
                    System.out.println("⚠️  Desktop is not supported. Please open report manually:");
                    System.out.println("   " + reportFile.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error opening extent report: " + e.getMessage());
        }
    }

    /**
     * Main method to execute report opening
     */
    public static void main(String[] args) {
        // Wait a moment for report generation to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Try to open the report
        openReport();

        // Also try to open extent report if available
        openExtentReport();
    }
}


