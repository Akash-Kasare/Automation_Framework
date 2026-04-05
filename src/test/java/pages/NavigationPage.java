package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import utils.TestLogger;
import utils.WebElementActions;

/**
 * Page Object Model - Navigation Page (Side Menu, Header, Footer)
 * Encapsulates all navigation related elements and methods
 */
public class NavigationPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By allItemsLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");
    private final By cartButton = By.className("shopping_cart_link");

    // ==================== CONSTRUCTOR ====================
    public NavigationPage() {
    }

    // ==================== PAGE METHODS ====================

    public void openMenu() {
        TestLogger.stepStart("Open Side Menu");
        WebElementActions.clickElement(menuButton, 10);
        TestLogger.stepPassed("Menu opened");
    }

    public void clickLogout() {
        TestLogger.stepStart("Click Logout");
        WebElementActions.clickElement(logoutLink, 10);
        TestLogger.stepPassed("Logout clicked");
    }

    public void clickAllItems() {
        TestLogger.stepStart("Click All Items");
        WebElementActions.clickElement(allItemsLink, 10);
        TestLogger.stepPassed("All items link clicked");
    }

    public void clickResetAppState() {
        TestLogger.stepStart("Click Reset App State");
        WebElementActions.clickElement(resetAppStateLink, 10);
        TestLogger.stepPassed("Reset app state clicked");
    }

    public void clickCart() {
        TestLogger.stepStart("Click Cart Button");
        WebElementActions.clickElement(cartButton, 10);
        TestLogger.stepPassed("Cart button clicked");
    }
}
