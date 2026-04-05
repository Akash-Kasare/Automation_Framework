Feature: SauceDemo Website Automation Test Scenarios

  Background:
    Given User navigates to the SauceDemo application
    When User waits for the page to load

  # ==================== LOGIN SCENARIOS (3-4 Tests) ====================

  @Login @Smoke
  Scenario: User successfully logs in with valid credentials
    When User enters username as "standard_user"
    And User enters password as "secret_sauce"
    And User clicks the login button
    # And User clicks OK button on alert
    Then User should be logged in successfully
    And User should see the products page

  @Login @Negative
  Scenario: User fails to login with invalid username
    When User enters username as "invalid_user"
    And User enters password as "secret_sauce"
    And User clicks the login button
    Then User should see an error message
    And Error message should display "Epic sadface: Username and password do not match any user in this service"

  @Login @Negative
  Scenario: User fails to login with invalid password
    When User enters username as "standard_user"
    And User enters password as "invalid_password"
    And User clicks the login button
    Then User should see an error message

  @Login @Negative
  Scenario: User fails to login with empty credentials
    When User clicks the login button
    Then User should see an error message
    And Error message should contain "required"

  # ==================== SHOPPING CART SCENARIOS (COMPLEX) ====================

  @ShoppingCart @Smoke @Complex
  Scenario: User adds single product to cart
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User clicks on product "Sauce Labs Backpack"
    And User clicks "Add to Cart" button for "Sauce Labs Backpack"
    Then Product "Sauce Labs Backpack" should be added to cart
    And Cart icon should show "1" item

  @ShoppingCart @Complex
  Scenario: User adds multiple products to cart
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds the following products to cart:
      | Product                       |
      | Sauce Labs Backpack           |
      | Sauce Labs Bike Light         |
      | Sauce Labs Bolt T-Shirt       |
      | Sauce Labs Fleece Jacket      |
    Then Cart should contain "4" items
    And Cart icon should show "4"

  @ShoppingCart @Complex
  Scenario: User removes product from cart
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds the following products to cart:
      | Product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
    And User clicks on cart icon
    And User removes "Sauce Labs Backpack" from cart
    Then Cart should contain "1" item
    And "Sauce Labs Backpack" should not be in cart
    And "Sauce Labs Bike Light" should be in cart

  @ShoppingCart @Complex
  Scenario: User verifies product prices in cart
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds the following products to cart:
      | Product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
    And User clicks on cart icon
    Then Cart subtotal should be calculated correctly
    And User should see product prices in cart

  # ==================== CHECKOUT SCENARIOS (COMPLEX) ====================

  @Checkout @Smoke @Complex
  Scenario: User completes purchase with valid information
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds product "Sauce Labs Backpack" to cart
    And User clicks on cart icon
    And User clicks checkout button
    And User enters checkout information:
      | FirstName | John              |
      | LastName  | Doe               |
      | PostalCode| 12345             |
    And User clicks continue on checkout info
    Then Checkout overview page should be displayed
    And Order total should be displayed

  @Checkout @Complex
  Scenario: User places order successfully and receives confirmation
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds the following products to cart:
      | Product                       |
      | Sauce Labs Backpack           |
      | Sauce Labs Fleece Jacket      |
    And User clicks on cart icon
    And User clicks checkout button
    And User enters checkout information:
      | FirstName  | Jane              |
      | LastName   | Smith             |
      | PostalCode | 54321             |
    And User clicks continue on checkout info
    And User verifies order summary
    And User clicks finish button
    Then Order confirmation page should be displayed
    And Confirmation message "Thank you for your order" should be visible
    And Order is completed successfully

  @Checkout @Complex
  Scenario: User cannot checkout without complete information
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds product "Sauce Labs Backpack" to cart
    And User clicks on cart icon
    And User clicks checkout button
    And User enters partial checkout information:
      | FirstName | Robert            |
    And User clicks continue on checkout info
    Then Error message should be displayed
    And Error message should contain "PostalCode is required"

  @Checkout @Complex
  Scenario: User can continue shopping after adding to cart
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds product "Sauce Labs Backpack" to cart
    And User clicks "Continue Shopping" button
    Then Products page should be displayed
    And Cart icon should show "1" item
    And User can continue adding more products

  @Checkout @Complex
  Scenario: User verifies tax calculation on checkout
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User adds the following products to cart:
      | Product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
    And User clicks on cart icon
    And User clicks checkout button
    And User enters checkout information:
      | FirstName | Alice             |
      | LastName  | Johnson           |
      | PostalCode| 99999             |
    And User clicks continue on checkout info
    Then Subtotal price should be visible
    And Tax amount should be calculated as 8% of subtotal
    And Total price should equal subtotal + tax
    And User should verify price breakdown

  # ==================== INVENTORY/SORTING SCENARIOS ====================

  @Inventory @Smoke
  Scenario: User sorts products by price - low to high
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User selects sort option "Price (low to high)"
    Then Products should be sorted by price in ascending order
    And First product should have lowest price
    And Last product should have highest price

  @Inventory
  Scenario: User filters and views product details
    When User logs in with username "standard_user" and password "secret_sauce"
    And User waits for products to load
    And User clicks on product "Sauce Labs Backpack"
    Then Product details page should be displayed
    And Product name should be "Sauce Labs Backpack"
    And Product price should be displayed
    And Product description should be visible
    And "Add to Cart" button should be available

