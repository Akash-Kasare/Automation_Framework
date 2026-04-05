package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import utils.TestLogger;
import utils.WebElementActions;

/**
 * Page Object Model - Product Details Page
 * Encapsulates all product details page elements and methods
 */
public class ProductDetailsPage extends BaseClass {

    // ==================== LOCATORS ====================
    private final By productName = By.className("inventory_details_name");
    private final By productPrice = By.className("inventory_details_price");
    private final By productDescription = By.className("inventory_details_desc");
    private final By addToCartButton = By.cssSelector(".btn_inventory");
    private final By backToProductsButton = By.id("back-to-products");

    // ==================== CONSTRUCTOR ====================
    public ProductDetailsPage() {
    }

    // ==================== PAGE METHODS ====================

    public String getProductName() {
        TestLogger.stepStart("Get Product Name from Details Page");
        return WebElementActions.getText(productName, 10);
    }

    public boolean isProductPriceDisplayed() {
        TestLogger.stepStart("Verify Product Price Displayed");
        return WebElementActions.isElementVisible(productPrice, 10);
    }

    public boolean isProductDescriptionDisplayed() {
        TestLogger.stepStart("Verify Product Description Displayed");
        return WebElementActions.isElementVisible(productDescription, 10);
    }

    public boolean isAddToCartButtonAvailable() {
        TestLogger.stepStart("Verify Add to Cart Button Available");
        return WebElementActions.isElementVisible(addToCartButton, 10);
    }

    public void clickAddToCart() {
        TestLogger.stepStart("Click Add to Cart Button in Details Page");
        WebElementActions.clickElement(addToCartButton, 10);
    }

    public void clickBackToProducts() {
        TestLogger.stepStart("Click Back to Products Button");
        WebElementActions.clickElement(backToProductsButton, 10);
    }
}
