package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    /**
     * Initialize driver based on configuration from config.properties file
     * Supports local and remote execution environments
     * Supports multiple browsers (Chrome, Edge) and OS (Windows, Mac)
     */
    public static void initializeDriver() {
        try {
            // Load configuration from properties file
            prop = loadProperties();

            // Get configuration values
            String executionEnv = prop.getProperty("execution_env").toLowerCase();
            String browser = prop.getProperty("browser").toLowerCase();
            String os = prop.getProperty("os").toLowerCase();

            if (executionEnv.equalsIgnoreCase("local")) {
                driver = initializeLocalDriver(browser, os);
            } else if (executionEnv.equalsIgnoreCase("remote")) {
                driver = initializeRemoteDriver(browser, os);
            } else {
                throw new IllegalArgumentException("Invalid execution environment: " + executionEnv);
            }

            // Set timeouts
            long implicitWait = Long.parseLong(prop.getProperty("implicit_wait", "10"));
            long pageLoadTimeout = Long.parseLong(prop.getProperty("page_load_timeout", "20"));

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

            // Maximize browser window
            driver.manage().window().maximize();

        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    /**
     * Load properties from config.properties file
     */
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        String configPath = "src/test/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        }
        return properties;
    }

    /**
     * Initialize local WebDriver based on browser and OS
     */
    private static WebDriver initializeLocalDriver(String browser, String os) {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            return new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    /**
     * Initialize remote WebDriver for cloud/remote execution
     */
    private static WebDriver initializeRemoteDriver(String browser, String os) throws MalformedURLException {
        String remoteUrl = prop.getProperty("remote_url", "http://localhost:4444");
        URL url = new URL(remoteUrl);

        org.openqa.selenium.Capabilities capabilities = null;

        if (browser.equalsIgnoreCase("chrome")) {
            capabilities = new org.openqa.selenium.chrome.ChromeOptions();
        } else if (browser.equalsIgnoreCase("edge")) {
            capabilities = new org.openqa.selenium.edge.EdgeOptions();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return new RemoteWebDriver(url, capabilities);
    }

    /**
     * Close the WebDriver
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}

