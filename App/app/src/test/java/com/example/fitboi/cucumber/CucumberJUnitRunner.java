package com.example.fitboi.cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/cucumber-report.json",
        features = "src/test/java/com/example/fitboi/cucumber/assets",
        glue = { "com.example.fitboi.cucumber.steps" })
public class CucumberJUnitRunner {
}