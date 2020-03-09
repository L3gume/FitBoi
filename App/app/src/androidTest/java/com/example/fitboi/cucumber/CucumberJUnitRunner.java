package com.example.fitboi.cucumber;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        //plugin = "json:target/cucumber-report.json",
        features = {"features"},
        glue = {"com.example.fitboi.cucumber.steps"})
public class CucumberJUnitRunner {
}