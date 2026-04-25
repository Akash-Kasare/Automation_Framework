package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager class handles reading properties from the config.properties file.
 * It provides an easy way to access global settings like base URLs and timeouts.
 */
public class ConfigManager {
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    // Static block runs once when the class is first loaded into memory.
    static {
        loadProperties();
    }

    /**
     * Loads the key-value pairs from the config.properties file into the memory.
     * If the file is not found or cannot be read, it logs an error message.
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Config file not found: " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
        }
    }

    /**
     * Retrieves a string value from the configuration file for a given key.
     * @param key The name of the property to find (e.g., "base.url").
     * @return The value of the property, or an empty string if the key doesn't exist.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    /**
     * Retrieves a string value from the configuration file, with a fallback default value.
     * @param key The name of the property to find.
     * @param defaultValue The value to return if the key is not found in the file.
     * @return The property value or the default value.
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Retrieves an integer value from the configuration file.
     * @param key The name of the property to find.
     * @param defaultValue The integer value to return if the key is not found or is not a valid number.
     * @return The parsed integer value or the default value.
     */
    public static int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue; // Return fallback if the value isn't a proper number
        }
    }
}
