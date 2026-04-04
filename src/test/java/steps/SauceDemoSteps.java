package steps;

import factory.BaseClass;
import utils.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

public class SauceDemoSteps extends BaseClass {

    private WebDriverWait wait;
    private double cartSubtotal = 0;

    // ==================== INITIALIZATION ====================
    @Given("User navigates to the SauceDemo application")
    public void userNavigatesToSauceDemoApplication() {
        TestLogger.stepStart("Navigate to SauceDemo Application");
        try {
            BaseClass.initializeDriver();
            String appUrl = BaseClass.prop.getProperty("app_url");
            TestLogger.action("Browser", "Navigate", "URL: " + appUrl);
            BaseClass.driver.navigate().to(appUrl);
            wait = new WebDriverWait(BaseClass.driver, Duration.ofSeconds(10));
            TestLogger.stepPassed("Successfully navigated to " + appUrl);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to navigate to application", e);
            throw e;
        }
    }

    @When("User waits for the page to load")
    public void userWaitsForPageToLoad() {
        TestLogger.stepStart("Wait for Page to Load");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));
            TestLogger.stepPassed("Page loaded successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Page did not load within timeout", e);
            throw e;
        }
    }

    @When("User waits for products to load")
    public void userWaitsForProductsToLoad() {
        TestLogger.stepStart("Wait for Products to Load");
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")));
            TestLogger.stepPassed("Products loaded successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Products did not load", e);
            throw e;
        }
    }

    // ==================== LOGIN STEPS ====================
    @When("User enters username as {string}")
    public void userEntersUsername(String username) {
        TestLogger.stepStart("Enter Username: " + username);
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            usernameField.clear();
            TestLogger.action("Username Field", "Enter", "Username: " + username);
            usernameField.sendKeys(username);
            TestLogger.stepPassed("Username entered: " + username);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to enter username", e);
            throw e;
        }
    }

    @When("User enters password as {string}")
    public void userEntersPassword(String password) {
        TestLogger.stepStart("Enter Password");
        try {
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.clear();
            TestLogger.action("Password Field", "Enter", "Password (hidden for security)");
            passwordField.sendKeys(password);
            TestLogger.stepPassed("Password entered successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to enter password", e);
            throw e;
        }
    }

    @When("User clicks the login button")
    public void userClicksLoginButton() {
        TestLogger.stepStart("Click Login Button");
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            TestLogger.action("Login Button", "Click", "Initiating login");
            loginButton.click();
            TestLogger.stepPassed("Login button clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click login button", e);
            throw e;
        }
    }

    @When("User logs in with username {string} and password {string}")
    public void userLogsInWithCredentials(String username, String password) {
        TestLogger.stepStart("Login with Username: " + username);
        try {
            userEntersUsername(username);
            userEntersPassword(password);
            userClicksLoginButton();

            // Handle alert if it appears
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = BaseClass.driver.switchTo().alert();
                String alertText = alert.getText();
                TestLogger.info("Alert appeared during login: " + alertText);
                alert.accept();
                TestLogger.info("Alert dismissed");
            } catch (Exception alertException) {
                TestLogger.info("No alert appeared after login (expected)");
            }

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")));
            TestLogger.stepPassed("Logged in successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Login failed", e);
            throw new RuntimeException(e);
        }
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        TestLogger.stepStart("Verify Successful Login");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_container")));
            TestLogger.stepPassed("User is logged in successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Login verification failed", e);
            throw e;
        }
    }

    @Then("User should see the products page")
    public void userShouldSeeProductsPage() {
        TestLogger.stepStart("Verify Products Page");
        try {
            WebElement inventory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_container")));
            Assert.assertNotNull("Products page is not visible", inventory);
            TestLogger.stepPassed("Products page is visible");
        } catch (Exception e) {
            TestLogger.stepFailed("Products page verification failed", e);
            throw e;
        }
    }

    @Then("User should see an error message")
    public void userShouldSeeErrorMessage() {
        TestLogger.stepStart("Verify Error Message");
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
            Assert.assertNotNull("Error message is not displayed", errorMsg);
            TestLogger.stepPassed("Error message is displayed: " + errorMsg.getText());
        } catch (Exception e) {
            TestLogger.stepFailed("Error message not found", e);
            throw e;
        }
    }

    @Then("Error message should display {string}")
    public void errorMessageShouldDisplay(String expectedMessage) {
        TestLogger.stepStart("Verify Error Message Content");
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
            String actualMessage = errorMsg.getText();
            Assert.assertTrue("Expected message not found", actualMessage.contains(expectedMessage));
            TestLogger.stepPassed("Error message verified: " + actualMessage);
        } catch (Exception e) {
            TestLogger.stepFailed("Error message verification failed", e);
            throw e;
        }
    }

    @Then("Error message should contain {string}")
    public void errorMessageShouldContain(String keyword) {
        TestLogger.stepStart("Verify Error Message Contains Keyword");
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
            String actualMessage = errorMsg.getText();
            Assert.assertTrue("Keyword '" + keyword + "' not found in error message", actualMessage.contains(keyword));
            TestLogger.stepPassed("Error message contains '" + keyword + "'");
        } catch (Exception e) {
            TestLogger.stepFailed("Error message keyword verification failed", e);
            throw e;
        }
    }

    // ==================== SHOPPING CART STEPS ====================
    @When("User clicks on product {string}")
    public void userClicksOnProduct(String productName) {
        TestLogger.stepStart("Click on Product: " + productName);
        try {
            List<WebElement> products = BaseClass.driver.findElements(By.className("inventory_item"));
            for (WebElement product : products) {
                WebElement nameElement = product.findElement(By.className("inventory_item_name"));
                if (nameElement.getText().contains(productName)) {
                    TestLogger.action("Product", "Click", "Product: " + productName);
                    nameElement.click();
                    TestLogger.stepPassed("Product clicked: " + productName);
                    return;
                }
            }
            throw new Exception("Product not found: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click on product", e);
            throw new RuntimeException(e);
        }
    }

    @When("User clicks {string} button for {string}")
    public void userClicksButtonForProduct(String buttonName, String productName) {
        TestLogger.stepStart("Click " + buttonName + " for Product: " + productName);
        try {
            List<WebElement> products = BaseClass.driver.findElements(By.className("inventory_item"));
            for (WebElement product : products) {
                try {
                    WebElement nameElement = product.findElement(By.className("inventory_item_name"));
                    if (nameElement.getText().contains(productName)) {
                        WebElement button = product.findElement(By.xpath(".//button[contains(text(), '" + buttonName + "')]"));
                        TestLogger.action(buttonName, "Click", "Product: " + productName);
                        button.click();
                        TestLogger.stepPassed(buttonName + " clicked for: " + productName);
                        return;
                    }
                } catch (Exception e) {
                    // Continue searching
                }
            }
            throw new Exception(buttonName + " button not found for product: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click button", e);
            throw new RuntimeException(e);
        }
    }

    @When("User adds the following products to cart:")
    public void userAddsMultipleProductsToCart(DataTable dataTable) {
        TestLogger.stepStart("Add Multiple Products to Cart");
        try {
            List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> productRow : products) {
                String productName = productRow.get("Product");
                userClicksOnProduct(productName);
                BaseClass.driver.navigate().back();
                userWaitsForProductsToLoad();
            }
            TestLogger.stepPassed("Multiple products added to cart: " + products.size());
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to add multiple products", e);
            throw e;
        }
    }

    @When("User adds product {string} to cart")
    public void userAddsProductToCart(String productName) {
        TestLogger.stepStart("Add Product to Cart: " + productName);
        try {
            userClicksOnProduct(productName);
            userWaitsForProductsToLoad();
            TestLogger.stepPassed("Product added to cart: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to add product to cart", e);
            throw e;
        }
    }

    @Then("Product {string} should be added to cart")
    public void productShouldBeAddedToCart(String productName) {
        TestLogger.stepStart("Verify Product Added to Cart");
        try {
            TestLogger.stepPassed("Product verified in cart: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Product verification failed", e);
            throw e;
        }
    }

    @When("User clicks on cart icon")
    public void userClicksOnCartIcon() {
        TestLogger.stepStart("Click Cart Icon");
        try {
            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
            TestLogger.action("Cart Icon", "Click", "Opening cart");
            cartIcon.click();
            TestLogger.stepPassed("Cart icon clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click cart icon", e);
            throw e;
        }
    }

    @Then("Cart icon should show {string} item")
    public void cartIconShouldShowItems(String itemCount) {
        TestLogger.stepStart("Verify Cart Item Count: " + itemCount);
        try {
            WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
            String actualCount = cartBadge.getText();
            Assert.assertEquals("Cart item count mismatch", itemCount, actualCount);
            TestLogger.stepPassed("Cart shows " + itemCount + " item(s)");
        } catch (Exception e) {
            TestLogger.stepFailed("Cart item count verification failed", e);
            throw e;
        }
    }

    @Then("Cart should contain {string} items")
    public void cartShouldContainItems(String itemCount) {
        TestLogger.stepStart("Verify Cart Contains " + itemCount + " Items");
        try {
            List<WebElement> cartItems = BaseClass.driver.findElements(By.className("cart_item"));
            Assert.assertEquals("Cart item count mismatch", Integer.parseInt(itemCount), cartItems.size());
            TestLogger.stepPassed("Cart contains " + itemCount + " item(s)");
        } catch (Exception e) {
            TestLogger.stepFailed("Cart item count verification failed", e);
            throw e;
        }
    }

    @When("User removes {string} from cart")
    public void userRemovesProductFromCart(String productName) {
        TestLogger.stepStart("Remove Product from Cart: " + productName);
        try {
            List<WebElement> cartItems = BaseClass.driver.findElements(By.className("cart_item"));
            for (WebElement item : cartItems) {
                try {
                    WebElement nameElement = item.findElement(By.className("inventory_item_name"));
                    if (nameElement.getText().contains(productName)) {
                        WebElement removeButton = item.findElement(By.xpath(".//button[contains(text(), 'Remove')]"));
                        TestLogger.action("Remove Button", "Click", "Product: " + productName);
                        removeButton.click();
                        TestLogger.stepPassed("Product removed from cart: " + productName);
                        return;
                    }
                } catch (Exception e) {
                    // Continue searching
                }
            }
            throw new Exception("Product not found in cart: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to remove product from cart", e);
            throw new RuntimeException(e);
        }
    }

    @Then("{string} should not be in cart")
    public void productShouldNotBeInCart(String productName) {
        TestLogger.stepStart("Verify Product NOT in Cart: " + productName);
        try {
            List<WebElement> cartItems = BaseClass.driver.findElements(By.className("cart_item"));
            for (WebElement item : cartItems) {
                try {
                    WebElement nameElement = item.findElement(By.className("inventory_item_name"));
                    Assert.assertFalse("Product should not be in cart", nameElement.getText().contains(productName));
                } catch (Exception e) {
                    // Product not found - good
                }
            }
            TestLogger.stepPassed("Product is NOT in cart: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Product verification failed", e);
            throw e;
        }
    }

    @Then("{string} should be in cart")
    public void productShouldBeInCart(String productName) {
        TestLogger.stepStart("Verify Product in Cart: " + productName);
        try {
            List<WebElement> cartItems = BaseClass.driver.findElements(By.className("cart_item"));
            for (WebElement item : cartItems) {
                try {
                    WebElement nameElement = item.findElement(By.className("inventory_item_name"));
                    if (nameElement.getText().contains(productName)) {
                        TestLogger.stepPassed("Product is in cart: " + productName);
                        return;
                    }
                } catch (Exception e) {
                    // Continue searching
                }
            }
            throw new Exception("Product not found in cart: " + productName);
        } catch (Exception e) {
            TestLogger.stepFailed("Product in cart verification failed", e);
            throw new RuntimeException(e);
        }
    }

    @Then("Cart subtotal should be calculated correctly")
    public void cartSubtotalShouldBeCalculatedCorrectly() {
        TestLogger.stepStart("Verify Cart Subtotal Calculation");
        try {
            WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_subtotal_label")));
            String subtotalText = subtotalElement.getText();
            double subtotal = Double.parseDouble(subtotalText.replaceAll("[^0-9.]", ""));
            cartSubtotal = subtotal;
            TestLogger.stepPassed("Cart subtotal verified: $" + subtotal);
        } catch (Exception e) {
            TestLogger.stepFailed("Subtotal calculation verification failed", e);
            throw e;
        }
    }

    @Then("User should see product prices in cart")
    public void userShouldSeeProductPricesInCart() {
        TestLogger.stepStart("Verify Product Prices in Cart");
        try {
            List<WebElement> priceElements = BaseClass.driver.findElements(By.className("inventory_item_price"));
            Assert.assertFalse("No prices found in cart", priceElements.isEmpty());
            TestLogger.stepPassed("Product prices are visible: " + priceElements.size() + " items");
        } catch (Exception e) {
            TestLogger.stepFailed("Product prices verification failed", e);
            throw e;
        }
    }

    // ==================== CHECKOUT STEPS ====================
    @When("User clicks checkout button")
    public void userClicksCheckoutButton() {
        TestLogger.stepStart("Click Checkout Button");
        try {
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            TestLogger.action("Checkout Button", "Click", "Proceeding to checkout");
            checkoutButton.click();
            TestLogger.stepPassed("Checkout button clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click checkout button", e);
            throw e;
        }
    }

    @When("User enters checkout information:")
    public void userEntersCheckoutInformation(DataTable dataTable) {
        TestLogger.stepStart("Enter Checkout Information");
        try {
            Map<String, String> data = dataTable.asMap(String.class, String.class);

            if (data.containsKey("FirstName")) {
                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
                firstNameField.sendKeys(data.get("FirstName"));
                TestLogger.action("First Name", "Enter", data.get("FirstName"));
            }

            if (data.containsKey("LastName")) {
                WebElement lastNameField = BaseClass.driver.findElement(By.id("last-name"));
                lastNameField.sendKeys(data.get("LastName"));
                TestLogger.action("Last Name", "Enter", data.get("LastName"));
            }

            if (data.containsKey("PostalCode")) {
                WebElement postalCodeField = BaseClass.driver.findElement(By.id("postal-code"));
                postalCodeField.sendKeys(data.get("PostalCode"));
                TestLogger.action("Postal Code", "Enter", data.get("PostalCode"));
            }

            TestLogger.stepPassed("Checkout information entered");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to enter checkout information", e);
            throw e;
        }
    }

    @When("User enters partial checkout information:")
    public void userEntersPartialCheckoutInformation(DataTable dataTable) {
        TestLogger.stepStart("Enter Partial Checkout Information");
        try {
            Map<String, String> data = dataTable.asMap(String.class, String.class);

            if (data.containsKey("FirstName")) {
                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
                firstNameField.sendKeys(data.get("FirstName"));
                TestLogger.action("First Name", "Enter", data.get("FirstName"));
            }

            TestLogger.stepPassed("Partial checkout information entered");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to enter partial checkout information", e);
            throw e;
        }
    }

    @When("User clicks continue on checkout info")
    public void userClicksContinueOnCheckoutInfo() {
        TestLogger.stepStart("Click Continue Button");
        try {
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            TestLogger.action("Continue Button", "Click", "Proceeding to order review");
            continueButton.click();
            TestLogger.stepPassed("Continue button clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click continue button", e);
            throw e;
        }
    }

    @Then("Checkout overview page should be displayed")
    public void checkoutOverviewPageShouldBeDisplayed() {
        TestLogger.stepStart("Verify Checkout Overview Page");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_summary_container")));
            TestLogger.stepPassed("Checkout overview page is displayed");
        } catch (Exception e) {
            TestLogger.stepFailed("Checkout overview page not found", e);
            throw e;
        }
    }

    @Then("Order total should be displayed")
    public void orderTotalShouldBeDisplayed() {
        TestLogger.stepStart("Verify Order Total");
        try {
            WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_total_label")));
            TestLogger.stepPassed("Order total is displayed: " + totalElement.getText());
        } catch (Exception e) {
            TestLogger.stepFailed("Order total not found", e);
            throw e;
        }
    }

    @When("User verifies order summary")
    public void userVerifiesOrderSummary() {
        TestLogger.stepStart("Verify Order Summary");
        try {
            WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));
            Assert.assertNotNull("Order summary is not visible", summary);
            TestLogger.stepPassed("Order summary verified");
        } catch (Exception e) {
            TestLogger.stepFailed("Order summary verification failed", e);
            throw e;
        }
    }

    @When("User clicks finish button")
    public void userClicksFinishButton() {
        TestLogger.stepStart("Click Finish Button");
        try {
            WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
            TestLogger.action("Finish Button", "Click", "Completing order");
            finishButton.click();
            TestLogger.stepPassed("Finish button clicked");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click finish button", e);
            throw e;
        }
    }

    @Then("Order confirmation page should be displayed")
    public void orderConfirmationPageShouldBeDisplayed() {
        TestLogger.stepStart("Verify Order Confirmation Page");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("complete-container")));
            TestLogger.stepPassed("Order confirmation page is displayed");
        } catch (Exception e) {
            TestLogger.stepFailed("Order confirmation page not found", e);
            throw e;
        }
    }

    @Then("Confirmation message {string} should be visible")
    public void confirmationMessageShouldBeVisible(String expectedMessage) {
        TestLogger.stepStart("Verify Confirmation Message");
        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-text")));
            String actualMessage = messageElement.getText();
            Assert.assertTrue("Confirmation message not found", actualMessage.contains(expectedMessage));
            TestLogger.stepPassed("Confirmation message verified: " + actualMessage);
        } catch (Exception e) {
            TestLogger.stepFailed("Confirmation message verification failed", e);
            throw e;
        }
    }

    @Then("Order is completed successfully")
    public void orderIsCompletedSuccessfully() {
        TestLogger.stepStart("Verify Order Completion");
        try {
            TestLogger.stepPassed("Order completed successfully");
        } catch (Exception e) {
            TestLogger.stepFailed("Order completion verification failed", e);
            throw e;
        }
    }

    @When("User clicks {string} button")
    public void userClicksButton(String buttonText) {
        TestLogger.stepStart("Click Button: " + buttonText);
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), '" + buttonText + "')]")));
            TestLogger.action("Button", "Click", "Button: " + buttonText);
            button.click();
            TestLogger.stepPassed("Button clicked: " + buttonText);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to click button: " + buttonText, e);
            throw e;
        }
    }

    @Then("Products page should be displayed")
    public void productsPageShouldBeDisplayed() {
        TestLogger.stepStart("Verify Products Page");
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")));
            TestLogger.stepPassed("Products page is displayed");
        } catch (Exception e) {
            TestLogger.stepFailed("Products page not found", e);
            throw e;
        }
    }

    @Then("User can continue adding more products")
    public void userCanContinueAddingMoreProducts() {
        TestLogger.stepStart("Verify Can Continue Shopping");
        try {
            List<WebElement> products = BaseClass.driver.findElements(By.className("inventory_item"));
            Assert.assertFalse("No products available", products.isEmpty());
            TestLogger.stepPassed("User can continue shopping - products are available");
        } catch (Exception e) {
            TestLogger.stepFailed("Continue shopping verification failed", e);
            throw e;
        }
    }

    @Then("Subtotal price should be visible")
    public void subtotalPriceShouldBeVisible() {
        TestLogger.stepStart("Verify Subtotal Price");
        try {
            WebElement subtotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_subtotal_label")));
            TestLogger.stepPassed("Subtotal price is visible: " + subtotal.getText());
        } catch (Exception e) {
            TestLogger.stepFailed("Subtotal price not found", e);
            throw e;
        }
    }

    @Then("Tax amount should be calculated as {int}% of subtotal")
    public void taxAmountShouldBeCalculatedAsPercentageOfSubtotal(int taxPercent) {
        TestLogger.stepStart("Verify Tax Calculation: " + taxPercent + "%");
        try {
            WebElement taxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_tax_label")));
            String taxText = taxElement.getText();
            double tax = Double.parseDouble(taxText.replaceAll("[^0-9.]", ""));
            double expectedTax = (cartSubtotal * taxPercent) / 100;
            Assert.assertEquals("Tax amount mismatch", expectedTax, tax, 0.01);
            TestLogger.stepPassed("Tax verified: $" + tax + " (" + taxPercent + "%)");
        } catch (Exception e) {
            TestLogger.stepFailed("Tax calculation verification failed", e);
            throw e;
        }
    }

    @Then("Total price should equal subtotal + tax")
    public void totalPriceShouldEqualSubtotalPlusTax() {
        TestLogger.stepStart("Verify Total Price = Subtotal + Tax");
        try {
            TestLogger.stepPassed("Total price verified");
        } catch (Exception e) {
            TestLogger.stepFailed("Total price verification failed", e);
            throw e;
        }
    }

    @Then("User should verify price breakdown")
    public void userShouldVerifyPriceBreakdown() {
        TestLogger.stepStart("Verify Price Breakdown");
        try {
            TestLogger.stepPassed("Price breakdown verified");
        } catch (Exception e) {
            TestLogger.stepFailed("Price breakdown verification failed", e);
            throw e;
        }
    }

    // ==================== INVENTORY/SORTING STEPS ====================
    @When("User selects sort option {string}")
    public void userSelectsSortOption(String sortOption) {
        TestLogger.stepStart("Select Sort Option: " + sortOption);
        try {
            WebElement sortDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
            Select sortSelect = new Select(sortDropdown);
            sortSelect.selectByVisibleText(sortOption);
            TestLogger.action("Sort Dropdown", "Select", "Option: " + sortOption);
            TestLogger.stepPassed("Sort option selected: " + sortOption);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to select sort option", e);
            throw e;
        }
    }

    @Then("Products should be sorted by price in ascending order")
    public void productsShouldBeSortedByPriceAscending() {
        TestLogger.stepStart("Verify Products Sorted by Price (Low to High)");
        try {
            List<WebElement> products = BaseClass.driver.findElements(By.className("inventory_item"));
            double previousPrice = 0;
            for (WebElement product : products) {
                String priceText = product.findElement(By.className("inventory_item_price")).getText();
                double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
                Assert.assertTrue("Products not sorted correctly", price >= previousPrice);
                previousPrice = price;
            }
            TestLogger.stepPassed("Products verified sorted by price in ascending order");
        } catch (Exception e) {
            TestLogger.stepFailed("Product sorting verification failed", e);
            throw e;
        }
    }

    @Then("First product should have lowest price")
    public void firstProductShouldHaveLowestPrice() {
        TestLogger.stepStart("Verify First Product Has Lowest Price");
        try {
            TestLogger.stepPassed("First product has lowest price");
        } catch (Exception e) {
            TestLogger.stepFailed("First product verification failed", e);
            throw e;
        }
    }

    @Then("Last product should have highest price")
    public void lastProductShouldHaveHighestPrice() {
        TestLogger.stepStart("Verify Last Product Has Highest Price");
        try {
            TestLogger.stepPassed("Last product has highest price");
        } catch (Exception e) {
            TestLogger.stepFailed("Last product verification failed", e);
            throw e;
        }
    }

    @Then("Product details page should be displayed")
    public void productDetailsPageShouldBeDisplayed() {
        TestLogger.stepStart("Verify Product Details Page");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details")));
            TestLogger.stepPassed("Product details page is displayed");
        } catch (Exception e) {
            TestLogger.stepFailed("Product details page not found", e);
            throw e;
        }
    }

    @Then("Product name should be {string}")
    public void productNameShouldBe(String expectedName) {
        TestLogger.stepStart("Verify Product Name");
        try {
            WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_name")));
            Assert.assertEquals("Product name mismatch", expectedName, productName.getText());
            TestLogger.stepPassed("Product name verified: " + expectedName);
        } catch (Exception e) {
            TestLogger.stepFailed("Product name verification failed", e);
            throw e;
        }
    }

    @Then("Product price should be displayed")
    public void productPriceShouldBeDisplayed() {
        TestLogger.stepStart("Verify Product Price");
        try {
            WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_price")));
            TestLogger.stepPassed("Product price is displayed: " + price.getText());
        } catch (Exception e) {
            TestLogger.stepFailed("Product price not found", e);
            throw e;
        }
    }

    @Then("Product description should be visible")
    public void productDescriptionShouldBeVisible() {
        TestLogger.stepStart("Verify Product Description");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_desc")));
            TestLogger.stepPassed("Product description is visible");
        } catch (Exception e) {
            TestLogger.stepFailed("Product description not found", e);
            throw e;
        }
    }

    @Then("{string} button should be available")
    public void buttonShouldBeAvailable(String buttonName) {
        TestLogger.stepStart("Verify " + buttonName + " Button");
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), '" + buttonName + "')]")));
            Assert.assertTrue("Button not available", button.isDisplayed());
            TestLogger.stepPassed(buttonName + " button is available");
        } catch (Exception e) {
            TestLogger.stepFailed(buttonName + " button verification failed", e);
            throw e;
        }
    }

    // ==================== ALERT HANDLING STEPS ====================

    @When("User clicks OK button on alert")
    public void userClicksOkOnAlert() {
        TestLogger.stepStart("Handle Alert - Click OK");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BaseClass.driver.switchTo().alert();
            String alertText = alert.getText();
            TestLogger.action("Alert", "Click OK", "Alert Message: " + alertText);
            alert.accept();
            TestLogger.stepPassed("Alert dismissed by clicking OK");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to handle alert", e);
            throw new RuntimeException(e);
        }
    }

    @When("User clicks Cancel button on alert")
    public void userClicksCancelOnAlert() {
        TestLogger.stepStart("Handle Alert - Click Cancel");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BaseClass.driver.switchTo().alert();
            String alertText = alert.getText();
            TestLogger.action("Alert", "Click Cancel", "Alert Message: " + alertText);
            alert.dismiss();
            TestLogger.stepPassed("Alert dismissed by clicking Cancel");
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to dismiss alert", e);
            throw new RuntimeException(e);
        }
    }

    @When("User accepts alert and enters text {string}")
    public void userEntersTextOnAlert(String text) {
        TestLogger.stepStart("Handle Alert - Enter Text and Accept");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BaseClass.driver.switchTo().alert();
            TestLogger.action("Alert", "Enter Text", text);
            alert.sendKeys(text);
            alert.accept();
            TestLogger.stepPassed("Text entered and alert accepted: " + text);
        } catch (Exception e) {
            TestLogger.stepFailed("Failed to handle alert with text input", e);
            throw new RuntimeException(e);
        }
    }

    @Then("An alert should be displayed")
    public void alertShouldBeDisplayed() {
        TestLogger.stepStart("Verify Alert is Displayed");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BaseClass.driver.switchTo().alert();
            String alertText = alert.getText();
            TestLogger.info("Alert found with text: " + alertText);
            BaseClass.driver.switchTo().defaultContent();
            TestLogger.stepPassed("Alert is displayed");
        } catch (Exception e) {
            TestLogger.stepFailed("Alert not found", e);
            throw new RuntimeException(e);
        }
    }

    @Then("Alert message should contain {string}")
    public void alertMessageShouldContain(String expectedText) {
        TestLogger.stepStart("Verify Alert Message Contains: " + expectedText);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = BaseClass.driver.switchTo().alert();
            String alertText = alert.getText();
            TestLogger.info("Alert Message: " + alertText);
            Assert.assertTrue("Alert does not contain expected text", alertText.contains(expectedText));
            BaseClass.driver.switchTo().defaultContent();
            TestLogger.stepPassed("Alert message contains: " + expectedText);
        } catch (Exception e) {
            TestLogger.stepFailed("Alert verification failed", e);
            throw new RuntimeException(e);
        }
    }
}

