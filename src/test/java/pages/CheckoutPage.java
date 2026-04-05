package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import utils.TestLogger;
import utils.WebElementActions;

/**
 * Page Object Model - Checkout Page
 * Encapsulates all checkout page elements and methods
 */
public class CheckoutPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By summarySubtotalLabel = By.className("summary_subtotal_label");
    private final By summaryTaxLabel = By.className("summary_tax_label");
    private final By summaryTotalLabel = By.className("summary_total_label");
    private final By completeHeader = By.className("complete-header");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // ==================== CONSTRUCTOR ====================
    public CheckoutPage() {
    }

    // ==================== PAGE METHODS ====================

    public void enterFirstName(String firstName) {
        TestLogger.stepStart("Enter First Name: " + firstName);
        WebElementActions.enterText(firstNameField, firstName, 10);
    }

    public void enterLastName(String lastName) {
        TestLogger.stepStart("Enter Last Name: " + lastName);
        WebElementActions.enterText(lastNameField, lastName, 10);
    }

    public void enterPostalCode(String postalCode) {
        TestLogger.stepStart("Enter Postal Code: " + postalCode);
        WebElementActions.enterText(postalCodeField, postalCode, 10);
    }

    public void clickContinue() {
        TestLogger.stepStart("Click Continue Button");
        WebElementActions.clickElement(continueButton, 10);
    }

    public void clickFinish() {
        TestLogger.stepStart("Click Finish Button");
        WebElementActions.clickElement(finishButton, 10);
    }

    public boolean isCheckoutOverviewDisplayed() {
        TestLogger.stepStart("Verify Checkout Overview Displayed");
        return WebElementActions.isElementVisible(finishButton, 10);
    }

    public String getSubtotal() {
        TestLogger.stepStart("Get Subtotal");
        return WebElementActions.getText(summarySubtotalLabel, 10);
    }

    public String getTax() {
        TestLogger.stepStart("Get Tax");
        return WebElementActions.getText(summaryTaxLabel, 10);
    }

    public String getTotal() {
        TestLogger.stepStart("Get Total");
        return WebElementActions.getText(summaryTotalLabel, 10);
    }

    public String getConfirmationMessage() {
        TestLogger.stepStart("Get Confirmation Message");
        return WebElementActions.getText(completeHeader, 10);
    }

    public boolean isOrderCompleted() {
        TestLogger.stepStart("Verify Order Completed");
        return WebElementActions.isElementVisible(completeHeader, 10);
    }

    public String getErrorMessage() {
        TestLogger.stepStart("Get Error Message");
        return WebElementActions.getText(errorMessage, 10);
    }

    public boolean isErrorMessageDisplayed() {
        TestLogger.stepStart("Verify Error Message Displayed");
        return WebElementActions.isElementVisible(errorMessage, 5);
    }
}
