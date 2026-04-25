package org.example.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.context.ScenarioContext;

/**
 * Hooks class for Cucumber test setup and teardown
 */
public class Hooks {
    private ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("================================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("================================================");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("================================================");
        System.out.println("Scenario Status: " + scenario.getStatus());
        System.out.println("Scenario: " + scenario.getName());
        System.out.println("================================================\n");
    }
}

