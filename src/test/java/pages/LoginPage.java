package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import utils.TestLogger;
import utils.WebElementActions;

/**
 * Page Object Model - Login Page
 * Encapsulates all login page elements and methods
 * Uses WebElementActions for all UI interactions
 */
public class LoginPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // ==================== CONSTRUCTOR ====================
    public LoginPage() {
        // Constructor
    }

    // ==================== PAGE METHODS ====================

    /**
     * Enter username on login page
     * @param username - Username to enter
     */
    public void enterUsername(String username) {
        TestLogger.stepStart("Enter Username: " + username);
        WebElementActions.enterText(usernameField, username, 10);
        TestLogger.stepPassed("Username entered: " + username);
    }

    /**
     * Enter password on login page
     * @param password - Password to enter
     */
    public void enterPassword(String password) {
        TestLogger.stepStart("Enter Password");
        WebElementActions.enterText(passwordField, password, 10);
        TestLogger.stepPassed("Password entered successfully");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        TestLogger.stepStart("Click Login Button");
        WebElementActions.clickElement(loginButton, 10);
        TestLogger.stepPassed("Login button clicked");
    }

    /**
     * Perform login with credentials
     * @param username - Username
     * @param password - Password
     */
    public void login(String username, String password) {
        TestLogger.stepStart("Login with Username: " + username);
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
            TestLogger.stepPassed("Logged in successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Login failed", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        TestLogger.stepStart("Verify Error Message");
        return WebElementActions.isElementVisible(errorMessage, 5);
    }

    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        TestLogger.stepStart("Get Error Message");
        return WebElementActions.getText(errorMessage, 10);
    }

    /**
     * Check if login page is loaded
     * @return true if login button is visible
     */
    public boolean isLoginPageLoaded() {
        TestLogger.stepStart("Verify Login Page Loaded");
        return WebElementActions.isElementVisible(loginButton, 10);
    }
}

