package factory;

import pages.*;
import utils.*;
import java.util.HashMap;
import java.util.Map;

/**
 * TestContext - Central context holder for all test data and page objects
 *
 * Provides:
 * - Single instance of all page objects
 * - Shared test data storage
 * - Easy access to utilities
 * - Thread-safe operations
 *
 * Usage:
 * TestContext context = new TestContext();
 * context.getLoginPage().login("user", "pass");
 */
public class TestContext {

    // ==================== PAGE OBJECTS ====================
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private NavigationPage navigationPage;
    private ProductDetailsPage productDetailsPage;

    // ==================== UTILITIES ====================
    private WebElementActions webElementActions;
    private ExcelUtils excelUtils;
    private TestLogger testLogger;
    private ExtentReportUtils extentReportUtils;

    // ==================== TEST DATA STORAGE ====================
    private Map<String, String> testData;
    private Map<String, Object> runtimeData;

    // ==================== CONSTRUCTOR ====================
    public TestContext() {
        initializePageObjects();
        initializeUtilities();
        initializeDataMaps();
    }

    // ==================== INITIALIZATION METHODS ====================

    /**
     * Initialize all page objects
     */
    private void initializePageObjects() {
        TestLogger.info("Initializing page objects");
        this.loginPage = new LoginPage();
        this.productsPage = new ProductsPage();
        this.cartPage = new CartPage();
        this.checkoutPage = new CheckoutPage();
        this.navigationPage = new NavigationPage();
        this.productDetailsPage = new ProductDetailsPage();
        TestLogger.info("Page objects initialized successfully");
    }

    /**
     * Initialize all utilities
     */
    private void initializeUtilities() {
        TestLogger.info("Initializing utilities");
        // Note: WebElementActions, ExcelUtils, TestLogger are static,
        // so no need to create instances
        this.extentReportUtils = new ExtentReportUtils();
        TestLogger.info("Utilities initialized successfully");
    }

    /**
     * Initialize data storage maps
     */
    private void initializeDataMaps() {
        TestLogger.info("Initializing test data maps");
        this.testData = new HashMap<>();
        this.runtimeData = new HashMap<>();
        TestLogger.info("Test data maps initialized");
    }

    // ==================== PAGE OBJECT GETTERS ====================

    /**
     * Get LoginPage instance
     * @return LoginPage object
     */
    public LoginPage getLoginPage() {
        return loginPage;
    }

    /**
     * Get ProductsPage instance
     * @return ProductsPage object
     */
    public ProductsPage getProductsPage() {
        return productsPage;
    }

    /**
     * Get CartPage instance
     * @return CartPage object
     */
    public CartPage getCartPage() {
        return cartPage;
    }

    /**
     * Get CheckoutPage instance
     * @return CheckoutPage object
     */
    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    /**
     * Get NavigationPage instance
     * @return NavigationPage object
     */
    public NavigationPage getNavigationPage() {
        return navigationPage;
    }

    /**
     * Get ProductDetailsPage instance
     * @return ProductDetailsPage object
     */
    public ProductDetailsPage getProductDetailsPage() {
        return productDetailsPage;
    }

    // ==================== UTILITY ACCESS METHODS ====================

    /**
     * Get WebElementActions for reusable UI interactions
     * @return WebElementActions class (static methods)
     */
    public Class<?> getWebElementActions() {
        return WebElementActions.class;
    }

    /**
     * Get ExcelUtils for Excel operations
     * @return ExcelUtils class (static methods)
     */
    public Class<?> getExcelUtils() {
        return ExcelUtils.class;
    }

    /**
     * Get ExtentReportUtils instance
     * @return ExtentReportUtils object
     */
    public ExtentReportUtils getExtentReportUtils() {
        return extentReportUtils;
    }

    // ==================== TEST DATA STORAGE METHODS ====================

    /**
     * Store test data
     * @param key - Data key
     * @param value - Data value
     */
    public void setTestData(String key, String value) {
        testData.put(key, value);
        TestLogger.info("Test data stored: " + key + " = " + value);
    }

    /**
     * Get test data
     * @param key - Data key
     * @return Data value
     */
    public String getTestData(String key) {
        String value = testData.get(key);
        TestLogger.info("Test data retrieved: " + key + " = " + value);
        return value;
    }

    /**
     * Check if test data exists
     * @param key - Data key
     * @return true if key exists
     */
    public boolean hasTestData(String key) {
        return testData.containsKey(key);
    }

    /**
     * Remove test data
     * @param key - Data key
     */
    public void removeTestData(String key) {
        testData.remove(key);
        TestLogger.info("Test data removed: " + key);
    }

    /**
     * Clear all test data
     */
    public void clearTestData() {
        testData.clear();
        TestLogger.info("All test data cleared");
    }

    /**
     * Get all test data
     * @return Map of test data
     */
    public Map<String, String> getAllTestData() {
        return new HashMap<>(testData);
    }

    // ==================== RUNTIME DATA STORAGE METHODS ====================

    /**
     * Store runtime data (for any object type)
     * @param key - Data key
     * @param value - Data value (any object)
     */
    public void setRuntimeData(String key, Object value) {
        runtimeData.put(key, value);
        TestLogger.info("Runtime data stored: " + key);
    }

    /**
     * Get runtime data
     * @param key - Data key
     * @return Data value
     */
    public Object getRuntimeData(String key) {
        Object value = runtimeData.get(key);
        TestLogger.info("Runtime data retrieved: " + key);
        return value;
    }

    /**
     * Get runtime data as specific type
     * @param key - Data key
     * @param type - Type to cast to
     * @return Data value cast to type
     */
    public <T> T getRuntimeData(String key, Class<T> type) {
        Object value = runtimeData.get(key);
        if (value != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    /**
     * Check if runtime data exists
     * @param key - Data key
     * @return true if key exists
     */
    public boolean hasRuntimeData(String key) {
        return runtimeData.containsKey(key);
    }

    /**
     * Remove runtime data
     * @param key - Data key
     */
    public void removeRuntimeData(String key) {
        runtimeData.remove(key);
        TestLogger.info("Runtime data removed: " + key);
    }

    /**
     * Clear all runtime data
     */
    public void clearRuntimeData() {
        runtimeData.clear();
        TestLogger.info("All runtime data cleared");
    }

    /**
     * Get all runtime data
     * @return Map of runtime data
     */
    public Map<String, Object> getAllRuntimeData() {
        return new HashMap<>(runtimeData);
    }

    // ==================== CONTEXT RESET METHODS ====================

    /**
     * Reset entire context (reinitialize everything)
     */
    public void resetContext() {
        TestLogger.info("Resetting test context");
        clearTestData();
        clearRuntimeData();
        initializePageObjects();
        TestLogger.info("Test context reset complete");
    }

    /**
     * Clear all data without reinitializing pages
     */
    public void clearAllData() {
        TestLogger.info("Clearing all test and runtime data");
        clearTestData();
        clearRuntimeData();
        TestLogger.info("All data cleared");
    }

    /**
     * Get context summary
     * @return String representation of context
     */
    @Override
    public String toString() {
        return "TestContext{" +
                "testData=" + testData.size() + " items" +
                ", runtimeData=" + runtimeData.size() + " items" +
                '}';
    }
}

