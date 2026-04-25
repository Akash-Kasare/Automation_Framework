Feature: Automation Exercise API Testing
  As a QA Automation Engineer
  I want to verify the API endpoints functionality
  So that I can ensure proper API behavior and response codes

  @Smoke @API
  Scenario: Get all products list via GET request
    Given I have the API endpoint "/productsList"
    When I send a GET request
    Then the response status code should be 200
    And the response should contain a valid JSON

  @Smoke @API
  Scenario: POST to products list endpoint should return method not allowed
    Given I have the API endpoint "/productsList"
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "This request method is not supported"

  @Smoke @API
  Scenario: Get all brands list via GET request
    Given I have the API endpoint "/brandsList"
    When I send a GET request
    Then the response status code should be 200
    And the response should contain a valid JSON

  @Smoke @API
  Scenario: PUT to brands list endpoint should return method not allowed
    Given I have the API endpoint "/brandsList"
    When I send a PUT request
    Then the response status code should be 200
    And the response message should contain "This request method is not supported"

  @Smoke @API
  Scenario: API 5 - POST To Search Product
    Given I have the API endpoint "/searchProduct"
    And I have the following request parameters:
      | search_product | tshirt |
    When I send a POST request
    Then the response status code should be 200
    And the response should contain a valid JSON

  @Smoke @API
  Scenario: API 6 - POST To Search Product without search_product parameter
    Given I have the API endpoint "/searchProduct"
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "Bad request, search_product parameter is missing in POST request."

  @Smoke @API
  Scenario: API 8 - POST To Verify Login without email parameter
    Given I have the API endpoint "/verifyLogin"
    And I have the following request parameters:
      | password | password123 |
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "Bad request, email or password parameter is missing in POST request."

  @Smoke @API
  Scenario: API 9 - DELETE To Verify Login
    Given I have the API endpoint "/verifyLogin"
    When I send a DELETE request
    Then the response status code should be 200
    And the response message should contain "This request method is not supported."

  @Smoke @API
  Scenario: API 10 - POST To Verify Login with invalid details
    Given I have the API endpoint "/verifyLogin"
    And I have the following request parameters:
      | email    | invalid_email@example.com |
      | password | invalidpass |
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "User not found!"

  @Smoke @API
  Scenario: API 11 - POST To Create/Register User Account
    Given I have the API endpoint "/createAccount"
    And I have the following request parameters:
      | name         | testuser123 |
      | email        | testautomation12345@example.com |
      | password     | password123 |
      | title        | Mr |
      | birth_date   | 01 |
      | birth_month  | 01 |
      | birth_year   | 1990 |
      | firstname    | Test |
      | lastname     | User |
      | company      | TestCo |
      | address1     | 123 Test St |
      | address2     | Apt 4 |
      | country      | United States |
      | zipcode      | 12345 |
      | state        | NY |
      | city         | New York |
      | mobile_number| 1234567890 |
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "User created!"

  @Smoke @API
  Scenario: API 7 - POST To Verify Login with valid details
    Given I have the API endpoint "/verifyLogin"
    And I have the following request parameters:
      | email    | testautomation12345@example.com |
      | password | password123 |
    When I send a POST request
    Then the response status code should be 200
    And the response message should contain "User exists!"

  @Smoke @API
  Scenario: API 13 - PUT METHOD To Update User Account
    Given I have the API endpoint "/updateAccount"
    And I have the following request parameters:
      | name         | testuser123updated |
      | email        | testautomation12345@example.com |
      | password     | password123 |
      | title        | Mr |
      | birth_date   | 01 |
      | birth_month  | 01 |
      | birth_year   | 1990 |
      | firstname    | TestUpdated |
      | lastname     | User |
      | company      | TestCo |
      | address1     | 123 Test St |
      | address2     | Apt 4 |
      | country      | United States |
      | zipcode      | 12345 |
      | state        | NY |
      | city         | New York |
      | mobile_number| 1234567890 |
    When I send a PUT request
    Then the response status code should be 200
    And the response message should contain "User updated!"

  @Smoke @API
  Scenario: API 14 - GET user account detail by email
    Given I have the API endpoint "/getUserDetailByEmail"
    And I have the following request parameters:
      | email | testautomation12345@example.com |
    When I send a GET request
    Then the response status code should be 200
    And the response should contain a valid JSON

  @Smoke @API
  Scenario: API 12 - DELETE METHOD To Delete User Account
    Given I have the API endpoint "/deleteAccount"
    And I have the following request parameters:
      | email    | testautomation12345@example.com |
      | password | password123 |
    When I send a DELETE request
    Then the response status code should be 200
    And the response message should contain "Account deleted!"
