# SauceDemo Automation Testing (Cucumber BDD)

This repository contains a robust Behavior-Driven Development (BDD) automation testing framework for the [SauceDemo](https://www.saucedemo.com/) web application. It is designed using Java, Selenium WebDriver, and Cucumber.

## 🚀 Technologies & Tools

* **Programming Language:** Java 21
* **Web Automation:** Selenium WebDriver 4.15.0
* **BDD Framework:** Cucumber 7.15.0
* **Test Runner:** JUnit
* **Build Tool:** Maven
* **Reporting:** ExtentReports 5.1.1, Cucumber HTML/JSON Reports
* **Logging:** Log4j2
* **Data-Driven Testing:** Apache POI (Excel)

## 📁 Project Structure

The framework is structured using the **Page Object Model (POM)** design pattern for better maintainability and code reusability:

* `src/test/resources/feature/`: Contains Cucumber feature files with Gherkin scenarios (Login, Cart, Checkout, Inventory).
* `src/test/java/steps/`: Contains step definitions corresponding to the feature file steps, mapping business requirements to code.
* `src/test/java/pages/`: Contains Page Object classes defining web elements and page-specific actions.
* `src/test/java/factory/`: Contains `BaseClass` for WebDriver initialization and `TestContext` for sharing state between steps (Dependency Injection via PicoContainer).
* `src/test/java/utils/`: Contains utility classes for Excel data management, ExtentReports, standardizing web element actions, and logging.
* `src/test/java/runner/`: Contains `TestRunner` to configure and execute tests, and `ReportArchiver`/`ReportGenerator` for post-execution report handling.
* `src/test/resources/testdata/`: Contains `Execution_Report.xlsx` used for data-driven testing.

## ⚙️ Prerequisites

* **Java Development Kit (JDK) 21** installed and configured in your system environment.
* **Apache Maven** installed and configured.
* **Google Chrome** browser installed (tests are configured to run on Chrome).
* An IDE such as IntelliJ IDEA or Eclipse.

## 🛠️ Setup & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Akash-Kasare/Automation_Framework.git
   cd SauceDemo_Automation_Cucmber
   ```

2. **Run tests via Maven:**
   To clean the project and execute all Cucumber tests defined in the `TestRunner`, run the following command from the root directory:
   ```bash
   mvn clean test
   ```

## 📊 Reporting & Archiving

The framework generates comprehensive reports after execution:
* **Cucumber Reports:** Generated in `target/cucumber-reports/` (HTML, JSON, XML formats).
* **ExtentReports:** A rich, interactive HTML report generated using the ExtentReports Cucumber adapter, providing detailed step-level logs and screenshots.
* **Logs:** Application execution logs are generated in the `logs/` directory.

**Report Archiving Feature:**
The framework includes an automated `ReportArchiver` configured within the `TestRunner`. After test execution, reports and logs are automatically archived with timestamps (e.g., `_DDMMYYYY_hhmmss`) into archive folders, preventing subsequent test runs from overwriting previous results.

## 📝 Test Scenarios Covered
* Valid and Invalid Login Scenarios
* Adding/Removing Single and Multiple Products to the Shopping Cart
* Verifying Product Prices and Tax Calculations
* Complete Checkout Workflow with varied User Data
* Inventory Sorting and Product Details Verification
