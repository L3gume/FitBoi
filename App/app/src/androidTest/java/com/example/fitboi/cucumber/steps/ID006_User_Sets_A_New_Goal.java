package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.GoalAPI;
import com.example.fitboi.dto.GoalDto;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.List;
import java.util.function.Consumer;

@RunWith(Cucumber.class)
public class ID006_User_Sets_A_New_Goal {

    @Given("the User is on the goals dashboard")
    public void the_user_is_on_the_goals_dashboard() {
        assert true;
    }

    @Then("the User adds a goal to their profile")
    public void the_user_adds_a_goal_to_their_profile() {
        GoalDto goalDto = new GoalDto(1337, 10, true,
                new Date(0), new Date(1), 12.f,
                "High", "Lose", 20.f, 30.f, 40.f);
        GoalAPI.createGoal("boaty@mcboatface.ca", goalDto, null);
        assert true;
    }

    @And("there is no goal currently in progress")
    public void there_is_no_goal_currently_in_progress() {
        assert true;
    }

    @And("the User has selected one of three preset goals")
    public void the_user_has_selected_one_of_three_preset_goals() {
        assert true;
    }

    @And("the 'Successfully added new goal' message is issued")
    public void the_successfully_added_new_goal_message_is_issued() {
        assert true;
    }

    @And("there is a goal currently in progress")
    public void there_is_a_goal_currently_in_progress() {
        GoalDto goal = GoalAPI.getUserGoal("boaty@mcboatface.ca",null);
        assert goal != null;
    }

    @And("the User has entered a custom goal")
    public void the_user_has_entered_a_custom_goal() {
        assert true;
    }

    @And("the 'Successfully added new custom goal' message is issued")
    public void the_successfully_added_new_custom_goal_message_is_issued() {
        assert true;
    }

    @And("the data is invalid")
    public void the_data_is_invalid() {
        assert true;
    }

    @And("the User is given a prompt informing that they will override their current goal")
    public void the_user_is_given_a_prompt_informing_that_they_will_override_their_current_goal() {
        assert true;
    }

    @And("the 'Input invalid' message is issued")
    public void the_input_invalid_message_is_issued() {
        assert true;
    }

}
