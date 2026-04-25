package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner class to run Cucumber tests with TestNG
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.example.stepdefinitions", "org.example.hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@Smoke",
        monochrome = false,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

