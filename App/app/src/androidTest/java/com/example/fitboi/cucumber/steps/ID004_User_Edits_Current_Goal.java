package com.example.fitboi.cucumber.steps;

import com.example.fitboi.Fitboi;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.mockito.Mockito.mock;


public class ID004_User_Edits_Current_Goal {

	private UserDto user;
	private GoalDto goal;
	private GoalDto updatedGoal;
    private GoalDto actualUpdatedGoal;


    // Scenario 1

    @Given("the User goal exists")
    public void the_user_goal_exists() {
        
    }

    @When("valid target goals are entered")
    public void valid_target_goals_are_entered() {

    }

    @Then("the goal is updated and persisted to the database")
    public void the_goal_is_updated_and_persisted_to_the_database(){

    }


    // Scenario 2

    @Given("the User goal exists")
    public void the_user_goal_exists() {
        
    }

    @When("invalid target goals are entered")
    public void invalid_target_goals_are_entered() {

    }

    @Then("the goal is updated and persisted to the database")
    public void the_goal_is_not_updated_and_persisted_to_the_database(){

    }

}

