package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestLogger;
import utils.WebElementActions;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object Model - Cart Page
 * Encapsulates all cart page elements and methods
 */
public class CartPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By cartItems = By.className("cart_item");
    private final By cartItemNames = By.className("inventory_item_name");
    private final By cartItemPrices = By.className("inventory_item_price");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    
    private String removeButtonXPath = "//div[text()='%s']/ancestor::div[@class='cart_item']//button[text()='Remove']";

    // ==================== CONSTRUCTOR ====================
    public CartPage() {
    }

    // ==================== PAGE METHODS ====================

    public boolean isCartPageLoaded() {
        TestLogger.stepStart("Verify Cart Page Loaded");
        return WebElementActions.isElementVisible(checkoutButton, 10);
    }

    public List<String> getCartItemNames() {
        TestLogger.stepStart("Get All Cart Item Names");
        List<WebElement> elements = WebElementActions.getElements(cartItemNames, 10);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void removeProductFromCart(String productName) {
        TestLogger.stepStart("Remove Product from Cart: " + productName);
        By locator = By.xpath(String.format(removeButtonXPath, productName));
        WebElementActions.clickElement(locator, 10);
        TestLogger.stepPassed("Product removed from cart: " + productName);
    }

    public void clickCheckout() {
        TestLogger.stepStart("Click Checkout Button");
        WebElementActions.clickElement(checkoutButton, 10);
        TestLogger.stepPassed("Checkout button clicked");
    }

    public void clickContinueShopping() {
        TestLogger.stepStart("Click Continue Shopping Button");
        WebElementActions.clickElement(continueShoppingButton, 10);
        TestLogger.stepPassed("Continue shopping button clicked");
    }

    public double getCartSubtotal() {
        TestLogger.stepStart("Get Cart Subtotal");
        List<WebElement> elements = WebElementActions.getElements(cartItemPrices, 10);
        return elements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    
    public boolean isProductInCart(String productName) {
        TestLogger.stepStart("Check if Product is in Cart: " + productName);
        return getCartItemNames().contains(productName);
    }
}
