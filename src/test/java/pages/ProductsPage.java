package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestLogger;
import utils.WebElementActions;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object Model - Products Page
 * Encapsulates all products page elements and methods
 */
public class ProductsPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By inventoryContainer = By.id("inventory_container");
    private final By productSortDropdown = By.className("product_sort_container");
    private final By inventoryItems = By.className("inventory_item");
    private final By inventoryItemNames = By.className("inventory_item_name");
    private final By inventoryItemPrices = By.className("inventory_item_price");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartButton = By.className("shopping_cart_link");

    private String addToCartButtonXPath = "//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button";

    // ==================== CONSTRUCTOR ====================
    public ProductsPage() {
    }

    // ==================== PAGE METHODS ====================

    public boolean isProductsPageLoaded() {
        TestLogger.stepStart("Verify Products Page Loaded");
        return WebElementActions.isElementVisible(inventoryContainer, 10);
    }

    public void selectSortOption(String option) {
        TestLogger.stepStart("Select Sort Option: " + option);
        WebElementActions.selectByVisibleText(productSortDropdown, option, 10);
        TestLogger.stepPassed("Sort option selected: " + option);
    }

    public void clickOnProduct(String productName) {
        TestLogger.stepStart("Click on Product: " + productName);
        By productLocator = By.xpath("//div[text()='" + productName + "']");
        WebElementActions.clickElement(productLocator, 10);
        TestLogger.stepPassed("Product clicked: " + productName);
    }

    public void addProductToCart(String productName) {
        TestLogger.stepStart("Add Product to Cart: " + productName);
        By locator = By.xpath(String.format(addToCartButtonXPath, productName));
        WebElementActions.clickElement(locator, 10);
        TestLogger.stepPassed("Product added to cart: " + productName);
    }

    public String getCartBadgeCount() {
        TestLogger.stepStart("Get Cart Badge Count");
        if (WebElementActions.isElementVisible(cartBadge, 5)) {
            return WebElementActions.getText(cartBadge);
        }
        return "0";
    }

    public void clickCartIcon() {
        TestLogger.stepStart("Click Cart Icon");
        WebElementActions.clickElement(cartButton, 10);
        TestLogger.stepPassed("Cart icon clicked");
    }

    public List<String> getProductNames() {
        TestLogger.stepStart("Get All Product Names");
        List<WebElement> elements = WebElementActions.getElements(inventoryItemNames, 10);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        TestLogger.stepStart("Get All Product Prices");
        List<WebElement> elements = WebElementActions.getElements(inventoryItemPrices, 10);
        return elements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clickContinueShopping() {
        TestLogger.stepStart("Click Continue Shopping");
        WebElementActions.clickElement(By.id("continue-shopping"), 10);
        TestLogger.stepPassed("Continue shopping clicked");
    }
}
