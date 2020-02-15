package com.example.fitboi.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class ID000_Common_Steps {

    public static String returnString;

    @Then("the {string} message is issued")
    public void the_message_is_issued(String string) {
        // Assert that the returnString is the same as the string
        assertEquals(returnString, string);
    }

    
    @Then("the goal is not updated")
    public void the_goal_is_not_updated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    
    @Given("no goals are available")
    public void no_goals_are_available() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    
    @Given("the goal is selected")
    public void the_goal_is_selected() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a User in on the goal page")
    public void a_User_in_on_the_goal_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    
    @Then("no information is accessible")
    public void no_information_is_accessible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the User goal is created")
    public void the_User_goal_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the User is on the main dashboard")
    public void the_User_is_on_the_main_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}