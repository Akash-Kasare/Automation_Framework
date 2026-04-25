# BDD Cucumber Framework - Architecture Overview

## System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         TEST EXECUTION LAYER                             │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌──────────────────────────────────────────────────────────────────┐   │
│  │                    Feature Files (Gherkin)                       │   │
│  │                   src/test/resources/features/                  │   │
│  │                                                                  │   │
│  │  Feature: Automation Exercise API Testing                      │   │
│  │    ├─ Scenario 1: Get Products List (GET)                      │   │
│  │    ├─ Scenario 2: POST Products List Error                     │   │
│  │    ├─ Scenario 3: Get Brands List (GET)                        │   │
│  │    └─ Scenario 4: PUT Brands List Error                        │   │
│  └──────────────────────┬───────────────────────────────────────────┘   │
│                         │                                                │
└─────────────────────────┼────────────────────────────────────────────────┘
                          │
                          ↓
┌─────────────────────────────────────────────────────────────────────────┐
│                    CUCUMBER EXECUTION ENGINE                             │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │              Test Runner (TestRunner.java)                     │    │
│  │  - Loads feature files                                         │    │
│  │  - Initiates Cucumber                                          │    │
│  │  - Configures plugins                                          │    │
│  └────────────────────┬─────────────────────────────────────────┘    │
│                       │                                               │
│  ┌────────────────────┴─────────────────────────────────────────┐    │
│  │              Hooks (Before/After)                             │    │
│  │  - Before: Setup scenario                                    │    │
│  │  - After: Teardown scenario                                  │    │
│  └────────────────────┬─────────────────────────────────────────┘    │
│                       │                                               │
└───────────────────────┼───────────────────────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────────────────────────┐
│              STEP DEFINITIONS & GLUE CODE LAYER                          │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │        APIStepDefinitions (Gherkin → Java Mapping)            │    │
│  │                                                                 │    │
│  │  Given Steps:                                                  │    │
│  │  └─ @Given: Set API endpoint                                   │    │
│  │                                                                 │    │
│  │  When Steps:                                                   │    │
│  │  ├─ @When: Send GET request                                    │    │
│  │  ├─ @When: Send POST request                                   │    │
│  │  ├─ @When: Send PUT request                                    │    │
│  │  └─ @When: Send DELETE request                                 │    │
│  │                                                                 │    │
│  │  Then Steps:                                                   │    │
│  │  ├─ @Then: Verify status code                                  │    │
│  │  ├─ @Then: Verify response message                             │    │
│  │  ├─ @Then: Verify valid JSON                                   │    │
│  │  └─ @Then: Verify response header                              │    │
│  │                                                                 │    │
│  └────────────────┬─────────────────────────────────────────────┘    │
│                   │                                                    │
└───────────────────┼────────────────────────────────────────────────────┘
                    │
                    ↓
┌─────────────────────────────────────────────────────────────────────────┐
│                    CONTEXT & STATE MANAGEMENT                            │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │              ScenarioContext (PicoContainer)                   │    │
│  │                                                                 │    │
│  │  Stores:                                                        │    │
│  │  ├─ Current API endpoint                                        │    │
│  │  └─ Last HTTP response                                          │    │
│  │                                                                 │    │
│  │  Shared across all step definitions in a scenario              │    │
│  │  Manages test state for Given-When-Then flow                   │    │
│  │                                                                 │    │
│  └────────────────────┬─────────────────────────────────────────┘    │
│                       │                                                │
└───────────────────────┼────────────────────────────────────────────────┘
                        │
                        ↓
┌─────────────────────────────────────────────────────────────────────────┐
│                    UTILITIES & HELPERS LAYER                             │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌──────────────────────────┐  ┌──────────────────────────────────┐   │
│  │   RestClient (HTTP)      │  │  ConfigManager (Properties)      │   │
│  ├──────────────────────────┤  ├──────────────────────────────────┤   │
│  │ Methods:                 │  │ Methods:                         │   │
│  │ ├─ get(endpoint)         │  │ ├─ getProperty(key)              │   │
│  │ ├─ post(endpoint, body)  │  │ ├─ getProperty(key, default)    │   │
│  │ ├─ put(endpoint, body)   │  │ ├─ getIntProperty(key, default) │   │
│  │ └─ delete(endpoint)      │  │ └─ Static property loading       │   │
│  │                          │  │                                  │   │
│  │ Features:               │  │ Source:                          │   │
│  │ ├─ RestAssured wrapper   │  │ config.properties                │   │
│  │ ├─ Default headers       │  │                                  │   │
│  │ ├─ Timeout handling      │  │ Supports:                        │   │
│  │ └─ Response extraction   │  │ ├─ String values                 │   │
│  │                          │  │ ├─ Integer values                │   │
│  │ Uses:                    │  │ └─ Default values                │   │
│  │ ├─ RestAssured 5.4.0     │  │                                  │   │
│  │ └─ HTTP/REST protocols   │  │ Uses:                            │   │
│  │                          │  │ └─ Java Properties API           │   │
│  └──────────┬───────────────┘  └──────────────┬───────────────────┘   │
│             │                                 │                       │
└─────────────┼─────────────────────────────────┼───────────────────────┘
              │                                 │
              └────────────┬────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────────────────┐
│                      CONFIGURATION LAYER                                 │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │              config.properties (Externalized)                  │    │
│  │                                                                 │    │
│  │  API Configuration:                                            │    │
│  │  ├─ base.url=https://automationexercise.com/api               │    │
│  │  ├─ connection.timeout=5000                                    │    │
│  │  └─ read.timeout=5000                                          │    │
│  │                                                                 │    │
│  │  Logging Configuration:                                        │    │
│  │  └─ log.level=INFO                                             │    │
│  │                                                                 │    │
│  │  Report Configuration:                                         │    │
│  │  └─ report.path=target/allure-results                          │    │
│  │                                                                 │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                           │
└─────────────────────────────────────────────────────────────────────────┘
              │
              ↓
┌─────────────────────────────────────────────────────────────────────────┐
│                      HTTP/API LAYER (External)                           │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                           │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │         Automation Exercise API Endpoints                      │    │
│  │                                                                 │    │
│  │  ├─ GET https://automationexercise.com/api/productsList       │    │
│  │  ├─ POST https://automationexercise.com/api/productsList      │    │
│  │  ├─ GET https://automationexercise.com/api/brandsList         │    │
│  │  └─ PUT https://automationexercise.com/api/brandsList         │    │
│  │                                                                 │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                           │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Data Flow Diagram

```
START TEST EXECUTION
    │
    ├─→ Load Feature File (api.feature)
    │
    ├─→ Parse Gherkin Scenarios
    │
    └─→ For Each Scenario:
        │
        ├─→ BEFORE HOOK
        │   └─→ Hooks.setUp()
        │
        ├─→ GIVEN STEP
        │   └─→ APIStepDefinitions.setApiEndpoint()
        │       ├─→ Extract endpoint URL
        │       └─→ Store in ScenarioContext
        │
        ├─→ WHEN STEP
        │   └─→ APIStepDefinitions.sendGetRequest() [or POST/PUT]
        │       ├─→ Get endpoint from ScenarioContext
        │       ├─→ Call RestClient.get()
        │       │   ├─→ Create RequestSpecification
        │       │   ├─→ Add headers (Content-Type, Accept)
        │       │   ├─→ Set timeouts (from ConfigManager)
        │       │   ├─→ Send HTTP request
        │       │   └─→ Extract response
        │       └─→ Store response in ScenarioContext
        │
        ├─→ THEN STEP(S)
        │   ├─→ APIStepDefinitions.verifyStatusCode()
        │   │   ├─→ Get response from ScenarioContext
        │   │   ├─→ Extract status code
        │   │   └─→ Assert against expected value (TestNG)
        │   │
        │   └─→ APIStepDefinitions.verifyValidJson()
        │       ├─→ Get response from ScenarioContext
        │       └─→ Try to parse as JSON (assertion)
        │
        ├─→ AFTER HOOK
        │   └─→ Hooks.tearDown()
        │       └─→ Log scenario completion
        │
        └─→ Generate Report Entry

END TEST EXECUTION
    │
    ├─→ Aggregate all results
    │
    └─→ Generate Reports:
        ├─→ HTML Report (cucumber-reports/cucumber.html)
        ├─→ JSON Report (cucumber-reports/cucumber.json)
        └─→ Allure Report (allure-results/)
```

---

## Component Interaction Diagram

```
┌──────────────────────────────────────────────────────────────────┐
│                    Feature File (api.feature)                    │
│  Contains 4 test scenarios with Given-When-Then steps            │
└───────────────────────────┬──────────────────────────────────────┘
                            │
                            ↓
┌──────────────────────────────────────────────────────────────────┐
│              Cucumber Parser & Execution Engine                  │
│  Parses Gherkin, matches to step definitions                    │
└───────────────────────────┬──────────────────────────────────────┘
                            │
                    ┌───────┴───────┐
                    ↓               ↓
        ┌─────────────────┐  ┌───────────────┐
        │ Hooks (Before)  │  │Hooks (After)  │
        └─────────────────┘  └───────────────┘
                    │               ↑
                    ↓               │
        ┌───────────────────────────┘
        │
        ↓
┌──────────────────────────────────────────────────────────────────┐
│          APIStepDefinitions (Step Implementation)                │
│  Maps Gherkin steps to Java methods                              │
└───────────────────────────┬──────────────────────────────────────┘
                            │
            ┌───────────────┼───────────────┐
            ↓               ↓               ↓
      ┌─────────────┐ ┌──────────┐ ┌─────────────────┐
      │ Given       │ │ When     │ │ Then            │
      │ setEndpoint │ │ sendReq  │ │ verify*         │
      └──────┬──────┘ └────┬─────┘ └────────┬────────┘
             │             │                │
             └─────────────┼────────────────┘
                           ↓
         ┌─────────────────────────────────────┐
         │    ScenarioContext (State Holder)   │
         │  - apiEndpoint (String)             │
         │  - lastResponse (Response)          │
         └────────────┬──────────────┬─────────┘
                      │              │
              ┌───────┘              └───────┐
              ↓                              ↓
    ┌──────────────────┐        ┌─────────────────────┐
    │  RestClient      │        │  ConfigManager      │
    │ ├─ get()         │        │ ├─ getProperty()    │
    │ ├─ post()        │        │ ├─ getIntProperty() │
    │ ├─ put()         │        │ └─ getProperty()    │
    │ └─ delete()      │        │                     │
    └────────┬─────────┘        └────────┬────────────┘
             │                          │
             ├──→ RestAssured          ├──→ Properties
             │    Library               │    File
             │                          │
             └────────────┬─────────────┘
                          ↓
        ┌─────────────────────────────────────┐
        │    HTTP Requests & Responses        │
        │  (REST API Endpoints)               │
        └─────────────────────────────────────┘
```

---

## Class Dependency Map

```
TestRunner.java
    │
    ├─→ Uses: Cucumber Framework
    ├─→ Uses: TestNG
    └─→ Glue: org.example.stepdefinitions, org.example.hooks

APIStepDefinitions.java
    │
    ├─→ Depends: ScenarioContext (injected)
    ├─→ Depends: RestClient
    ├─→ Uses: TestNG Assert
    └─→ Uses: RestAssured Response

Hooks.java
    │
    ├─→ Depends: ScenarioContext (injected)
    └─→ Uses: Cucumber Before/After

ScenarioContext.java
    │
    ├─→ Stores: String apiEndpoint
    ├─→ Stores: Response lastResponse
    └─→ No external dependencies

RestClient.java
    │
    ├─→ Depends: ConfigManager
    ├─→ Uses: RestAssured
    ├─→ Uses: Spring RestAssured
    └─→ Provides: HTTP methods

ConfigManager.java
    │
    ├─→ Loads: config.properties
    ├─→ Uses: Java Properties API
    └─→ No external dependencies

CucumberConfiguration.java
    │
    ├─→ Uses: Spring
    ├─→ Depends: ScenarioContext
    └─→ Enables: Dependency Injection

pom.xml
    │
    ├─→ Defines: All dependencies
    ├─→ Defines: Build plugins
    ├─→ Defines: Report generation
    └─→ Defines: Java version (17)
```

---

## Test Execution Sequence

```
TIME    EVENT SEQUENCE
────────────────────────────────────────────────────────────────────

t=0     Maven starts test phase
        ↓
t=1     TestRunner loaded
        ↓
t=2     Feature file parsed (api.feature)
        ↓
t=3     Scenario 1: Get Products List
        ├─ Before Hook: Log scenario start
        ├─ Given: Set endpoint to /api/productsList
        ├─ When: Send GET request
        ├─ Then: Assert status code = 200
        ├─ Then: Assert valid JSON
        └─ After Hook: Log scenario end → Status: PASS
        ↓
t=4     Scenario 2: POST Products List
        ├─ Before Hook: Log scenario start
        ├─ Given: Set endpoint to /api/productsList
        ├─ When: Send POST request
        ├─ Then: Assert status code = 405
        ├─ Then: Assert message contains "not supported"
        └─ After Hook: Log scenario end → Status: PASS
        ↓
t=5     Scenario 3: Get Brands List
        ├─ Before Hook: Log scenario start
        ├─ Given: Set endpoint to /api/brandsList
        ├─ When: Send GET request
        ├─ Then: Assert status code = 200
        ├─ Then: Assert valid JSON
        └─ After Hook: Log scenario end → Status: PASS
        ↓
t=6     Scenario 4: PUT Brands List
        ├─ Before Hook: Log scenario start
        ├─ Given: Set endpoint to /api/brandsList
        ├─ When: Send PUT request
        ├─ Then: Assert status code = 405
        ├─ Then: Assert message contains "not supported"
        └─ After Hook: Log scenario end → Status: PASS
        ↓
t=7     All scenarios executed
        ├─ Generate HTML Report
        ├─ Generate JSON Report
        ├─ Generate Allure Report
        └─ Test execution complete

Total Time: ~5-8 seconds
Result: ✅ ALL TESTS PASSED (4/4)
```

---

## Dependency Injection Flow

```
PicoContainer (Cucumber Picocontainer)
    │
    ├─→ Manages: ScenarioContext lifecycle
    │   └─→ Creates new instance per scenario
    │       └─→ One instance per scenario execution
    │
    ├─→ Injects into: APIStepDefinitions constructor
    │   ├─ Constructor: APIStepDefinitions(ScenarioContext context)
    │   ├─ Receives: Scenario-specific ScenarioContext instance
    │   └─ Stores: Reference to context for all steps in scenario
    │
    └─→ Injects into: Hooks constructor
        ├─ Constructor: Hooks(ScenarioContext context)
        ├─ Receives: Same ScenarioContext instance as step definitions
        └─ Enables: Shared state across all components

Result:
├─ Clean architecture: No hard-coded dependencies
├─ Testable: Can inject mock objects
├─ Maintainable: Easy to change implementations
└─ Scalable: Easy to add new step definitions
```

---

## Report Generation Architecture

```
Maven Test Execution
        │
        ├─→ Surefire Plugin
        │   └─→ Runs tests via TestNG
        │
        ├─→ Cucumber Plugins
        │   ├─ Pretty: Console output
        │   ├─ JSON: Machine-readable format
        │   │   └─→ target/cucumber-reports/cucumber.json
        │   ├─ HTML: HTML report
        │   │   └─→ target/cucumber-reports/cucumber.html
        │   └─ Allure: Allure report format
        │       └─→ target/allure-results/
        │
        └─→ Report Post-Processing
            ├─ Allure Maven plugin processes results
            └─ Generate interactive Allure dashboard
                (on demand: mvn allure:report)

Reports Generated:
├─ cucumber.html (Open in browser)
├─ cucumber.json (Feed to report tools)
└─ allure-results/ (Feed to Allure)
    └─ allure serve generates interactive dashboard
```

---

## Framework Layering

```
┌─────────────────────────────────────────────────────────────┐
│                 PRESENTATION LAYER                          │
│  (Feature Files - Business Readable - Gherkin)              │
│  - api.feature                                              │
└──────────────────────┬──────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│              ABSTRACTION LAYER                               │
│  (Step Definitions - Map Gherkin to Code)                   │
│  - APIStepDefinitions                                        │
│  - Hooks                                                     │
└──────────────────────┬─────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│              BUSINESS LOGIC LAYER                            │
│  (Utilities - Reusable Functions)                           │
│  - RestClient (HTTP operations)                              │
│  - ConfigManager (Configuration)                             │
└──────────────────────┬─────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│              STATE MANAGEMENT LAYER                          │
│  (Context - Test State)                                     │
│  - ScenarioContext                                           │
└──────────────────────┬─────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│              FRAMEWORK LAYER                                 │
│  (Configuration & Infrastructure)                           │
│  - CucumberConfiguration                                     │
│  - config.properties                                         │
│  - POM.xml                                                   │
└──────────────────────┬─────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│              EXTERNAL LAYER                                  │
│  (External Libraries & APIs)                                │
│  - Cucumber, RestAssured, TestNG, Maven                      │
│  - REST API Endpoints (External)                             │
└──────────────────────────────────────────────────────────────┘
```

---

## Scalability & Extensibility Points

```
Easy to Add:
├─ New Test Scenarios
│  └─ Just add Gherkin to feature file
│     (Step definitions already implemented)
│
├─ New Step Definitions
│  └─ Add @Given/@When/@Then methods in APIStepDefinitions
│
├─ New Utilities
│  └─ Create new utility class following same pattern
│
├─ New Hooks
│  └─ Add @Before/@After methods in Hooks
│
├─ Database Integration
│  └─ Create DatabaseManager utility
│
├─ Authentication
│  └─ Enhance RestClient with auth methods
│
├─ Performance Testing
│  └─ Add response time assertions
│
└─ Custom Reporting
   └─ Add new plugin to pom.xml
```

---

This architecture ensures:
✅ **Maintainability** - Clear separation of concerns  
✅ **Scalability** - Easy to add new tests and features  
✅ **Testability** - Dependency injection enables mocking  
✅ **Reusability** - Generic components for multiple tests  
✅ **Readability** - Business-language feature files  
✅ **Extensibility** - Multiple extension points  


