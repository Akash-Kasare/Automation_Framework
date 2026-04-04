# SauceDemo Automation Test Framework - Step Definitions Documentation

## Overview
This document provides a comprehensive guide to all the reusable step definitions created for the SauceDemo automation testing framework.

## Project Structure
```
SauceDemo_Automation_Cucmber/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   ├── test/
│   │   ├── java/
│   │   │   ├── factory/
│   │   │   │   └── BaseClass.java          # WebDriver initialization
│   │   │   ├── steps/
│   │   │   │   ├── SauceDemoSteps.java     # All step definitions
│   │   │   │   └── Hooks.java              # Setup/Teardown hooks
│   │   │   └── runner/
│   │   │       └── TestRunner.java         # Test execution runner
│   │   └── resources/
│   │       ├── config.properties           # Configuration file
│   │       ├── feature/
│   │       │   └── SauceDemo.feature       # All test scenarios
│   │       └── screenshots/                # Failure screenshots
│   └── pom.xml
└── README.md
```

## Files Created

### 1. **BaseClass.java** (factory package)
Main class for WebDriver initialization with support for:
- **Local/Remote execution** (controlled via `execution_env` property)
- **Multiple browsers** (Chrome, Edge)
- **Multiple OS** (Windows, Mac)
- **Configuration management** from properties file
- **Timeout settings** (implicit wait, page load timeout)
- **Driver cleanup** (proper teardown)

**Methods:**
- `initializeDriver()` - Initialize WebDriver based on config
- `loadProperties()` - Load configuration from properties file
- `initializeLocalDriver()` - Setup local browser driver
- `initializeRemoteDriver()` - Setup remote browser driver
- `closeDriver()` - Cleanup driver instance

### 2. **SauceDemoSteps.java** (steps package)
Contains 70+ reusable step definitions organized in categories:

#### Login Steps (6 methods)
```
- userNavigatesToSauceDemoApplication()
- userWaitsForPageToLoad()
- userEntersUsername(String username)
- userEntersPassword(String password)
- userClicksLoginButton()
- userShouldBeLoggedInSuccessfully()
- userShouldSeeProductsPage()
- userShouldSeeInventoryList()
- userShouldSeeErrorMessage()
- errorMessageShouldDisplay(String expectedMessage)
```

#### Product Listing Steps (7 methods)
```
- userShouldSeeProductsDisplayed(int count)
- userClicksSortDropdown()
- userSelectsSortOption(String sortOption)
- productsShouldBeSortedAtoZ()
- productsShouldBeSortedZtoA()
- productsShouldBeSortedByPriceLowToHigh()
- productsShouldBeSortedByPriceHighToLow()
```

#### Add to Cart Steps (8 methods)
```
- userAddsProductToCart(String productName)
- userAddsAllProductsToCart()
- userRemovesProductFromCart(String productName)
- userShouldSeeButtonForProduct(String buttonText)
- cartBadgeShouldDisplayCount(int count)
- cartBadgeShouldNotBeVisible()
- buttonShouldDisplayAgain(String buttonText)
- userClicksOnProduct(String productName)
- userShouldSeeProductName(String productName)
- userShouldSeeProductDescription()
- userShouldSeeProductPrice()
- userShouldSeeProductImage()
```

#### Shopping Cart Steps (6 methods)
```
- userClicksShoppingCartIcon()
- userShouldSeeShoppingCartPage()
- userShouldSeeProductInCart(String productName)
- userShouldSeeQuantityAs(int quantity)
- userClicksButton(String buttonText)
- userShouldBeRedirectedToProductsPage()
- userIncreasesQuantity(String productName, int newQuantity)
- userDecreasesQuantity(String productName, int newQuantity)
- productShouldBeRemovedFromCart(String productName)
- userShouldSeeCheckoutStepOnePage()
```

#### Checkout Steps (10 methods)
```
- userEntersFirstName(String firstName)
- userEntersLastName(String lastName)
- userEntersPostalCode(String postalCode)
- userShouldSeeCheckoutStepTwoPage()
- userShouldSeeOrderSummary()
- userShouldSeeItemsInOrderSummary(int itemCount)
- userShouldSeeSubtotalAmount()
- userShouldSeeTaxAmount()
- userShouldSeeTotalAmount()
- userShouldSeeOrderConfirmationPage()
- userShouldSeeMessage(String message)
```

#### Logout Steps (5 methods)
```
- userClicksMenuButton()
- userClicksMenuOption(String optionText)
- userShouldBeLoggedOut()
- userShouldBeRedirectedToLoginPage()
- userShouldSeeOption(String optionText)
```

#### Menu Steps (4 methods)
```
- cartShouldBeEmpty()
- userShouldSeeAboutPage()
```

#### Footer Steps (7 methods)
```
- userShouldSeeFooterSection()
- userShouldSeeSocialMediaLinks()
- userShouldSeeCopyrightInformation()
- userClicksTwitterLink()
- userClicksFacebookLink()
- userClicksLinkedinLink()
- twitterPageShouldOpenInNewWindow()
- facebookPageShouldOpenInNewWindow()
- linkedinPageShouldOpenInNewWindow()
```

#### Performance Steps (1 method)
```
- pageShouldLoadWithinSeconds(int seconds)
```

#### Accessibility Steps (3 methods)
```
- userPressesTabToUsernameField()
- userPressesTabToPasswordField()
- userPressesEnterToLogin()
```

#### Filter & Edge Case Steps (6 methods)
```
- allProductsShouldBeDisplayed()
- addToCartButtonShouldNotBeVisible()
- userTriesAccessProductsPageDirectly()
- cartBadgeShouldStillDisplayCount(int count)
- findProductByName(String productName) [Helper]
```

### 3. **Hooks.java** (steps package)
Manages test lifecycle:
- **@Before** - Executes before each scenario
  - Logs scenario start
  - Initializes browser
- **@After** - Executes after each scenario
  - Logs scenario status
  - Takes screenshot on failure
  - Closes driver

**Methods:**
- `beforeScenario(Scenario scenario)`
- `afterScenario(Scenario scenario)`
- `takeScreenshot(String scenarioName)`

### 4. **TestRunner.java** (runner package)
Cucumber test runner configuration:
- Features location: `src/test/resources/feature`
- Step definitions location: `steps` package
- Report formats: HTML, JSON, JUnit XML
- Default execution: @Smoke tagged tests

### 5. **config.properties** (test resources)
Configuration file with properties:
```properties
# Browser Configuration
browser=chrome                          # Options: chrome, edge

# Operating System Configuration
os=windows                             # Options: windows, mac

# Execution Environment Configuration
execution_env=local                    # Options: local, remote

# Application URL
app_url=https://www.saucedemo.com/

# Remote Driver Configuration (for remote execution)
remote_url=http://localhost:4444

# Timeout Configuration (in seconds)
implicit_wait=10
explicit_wait=15
page_load_timeout=20
```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Only Smoke Tests
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

### Run Only Login Tests
```bash
mvn test -Dcucumber.filter.tags="@Login"
```

### Run Negative Tests Only
```bash
mvn test -Dcucumber.filter.tags="@Negative"
```

### Run Specific Feature
```bash
mvn test -Dcucumber.features="src/test/resources/feature/SauceDemo.feature"
```

## Feature File Tags

| Tag | Purpose | Count |
|-----|---------|-------|
| @Login | Login functionality tests | 7 |
| @ProductListing | Product display & sorting | 5 |
| @AddToCart | Add/Remove cart operations | 5 |
| @ShoppingCart | Cart management | 5 |
| @Checkout | Checkout process | 5 |
| @Logout | Logout functionality | 2 |
| @Menu | Menu navigation | 4 |
| @Footer | Footer links | 4 |
| @Performance | Performance testing | 1 |
| @UserTypes | Different user types | 2 |
| @Accessibility | Keyboard navigation | 1 |
| @Filter | Product filtering | 1 |
| @EdgeCase | Edge cases & security | 3 |
| @Smoke | Critical path tests | 30+ |
| @Negative | Negative/Error scenarios | 7+ |

## Key Features

✅ **Reusable Step Definitions** - All steps can be combined in any feature file scenario

✅ **Configuration Management** - Externalized configuration via properties file

✅ **Cross-Browser Support** - Chrome and Edge browsers supported

✅ **Local & Remote Execution** - Support for local and cloud-based testing

✅ **Automatic Screenshots** - Failure screenshots for debugging

✅ **Detailed Logging** - Console logs for each scenario

✅ **Proper Synchronization** - WebDriverWait for element visibility

✅ **Error Handling** - Comprehensive error messages

✅ **Helper Methods** - Reusable utility methods for finding elements

## Step Definition Naming Convention

All step definitions follow Gherkin syntax:
- **Given** - Setup/Precondition steps
- **When** - Action steps
- **Then** - Assertion/Verification steps
- **And** - Continue previous step type

## Example Usage in Feature File

```gherkin
Scenario: User successfully logs in with valid credentials
  Given User navigates to the SauceDemo application
  When User waits for the page to load
  When User enters username as "standard_user"
  And User enters password as "secret_sauce"
  And User clicks the login button
  Then User should be logged in successfully
  And User should see the products page
```

## Test Data

Valid test credentials from SauceDemo:
- **standard_user** / secret_sauce
- **locked_out_user** / secret_sauce
- **problem_user** / secret_sauce
- **performance_glitch_user** / secret_sauce

## Locators Used

All locators are based on SauceDemo's DOM structure:
- ID locators: `user-name`, `password`, `login-button`, etc.
- Class locators: `inventory_item`, `shopping_cart_link`, etc.
- XPath locators: For dynamic elements and complex selections

## Best Practices Implemented

1. **Single Responsibility** - Each step does one thing
2. **Reusability** - Steps parameterized for flexibility
3. **Maintainability** - Clear naming and organization
4. **Robustness** - Proper wait conditions
5. **Reporting** - HTML/JSON/XML reports
6. **Screenshots** - Automatic on failure
7. **Clean Code** - Following Java conventions

## Troubleshooting

### No elements found error
- Check element locators match current SauceDemo DOM
- Verify wait times are sufficient
- Update locators if SauceDemo HTML changes

### Driver initialization fails
- Verify WebDriver is installed
- Check browser version compatibility
- Ensure config.properties exists and is readable

### Tests timeout
- Increase timeout values in config.properties
- Check internet connection for remote tests
- Verify SauceDemo website is accessible

## Future Enhancements

- [ ] Page Object Model (POM) implementation
- [ ] Custom annotations for better organization
- [ ] Parallel test execution
- [ ] Docker support
- [ ] CI/CD integration (Jenkins, GitHub Actions)
- [ ] Database validation steps
- [ ] API testing integration

## Maintenance

Update step definitions when:
- SauceDemo website DOM changes
- New test scenarios are added
- Business requirements change
- New browser support is needed

## Contact & Support

For issues or improvements, refer to the step definitions documentation and comments in the code.

