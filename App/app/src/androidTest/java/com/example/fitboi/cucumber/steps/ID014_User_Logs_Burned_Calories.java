package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.MetricsAPI;
import com.example.fitboi.dto.MetricDto;

import cucumber.api.java.en.*;
public class ID014_User_Logs_Burned_Calories {

    private Integer caloriesToAdd;
    private Integer caloriesBeforeAdd;
    private Integer caloriesToSet;

    @Then("the calories are added to the total burnt calorie count for that day")
    public void the_calories_are_added_to_the_total_burnt_calorie_count_for_that_day(){
        MetricDto updatedMetric = MetricsAPI.getCurrentUserMetric(StaticTestData.user.getEmail(),null);
        assert (updatedMetric.getExerciseSpending() == caloriesBeforeAdd+caloriesToAdd);
    }

    @Then("the calories are set as the total burnt calorie count for that day")
    public void the_calories_are_set_as_the_total_burnt_calorie_count_for_that_day(){
        MetricDto updatedMetric = MetricsAPI.getCurrentUserMetric(StaticTestData.user.getEmail(),null);
        assert (updatedMetric.getExerciseSpending() == caloriesToSet);
    }

    @Then("the calories are not added to the total burnt calorie count for that day")
    public void the_calories_are_not_added_to_the_total_burnt_calorie_count_for_that_day(){
        MetricDto updatedMetric = MetricsAPI.getCurrentUserMetric(StaticTestData.user.getEmail(),null);
        assert (updatedMetric.getExerciseSpending() != caloriesBeforeAdd+caloriesToAdd);
    }

    @Then("the calories are not set as the total burnt calorie count for that day")
    public void the_calories_are_not_set_as_the_total_burnt_calorie_count_for_that_day(){
        MetricDto updatedMetric = MetricsAPI.getCurrentUserMetric(StaticTestData.user.getEmail(),null);
        assert (updatedMetric.getExerciseSpending() != caloriesToSet);
    }

    @And("the User has entered burned calories to add to their day")
    public void the_user_has_entered_burned_calories_to_add_to_their_day(){
        caloriesToAdd = 10;
        caloriesBeforeAdd = StaticTestData.metric.getExerciseSpending();
    }

    @And("the User decides to add the entered calories to their day")
    public void the_user_decides_to_add_the_entered_calories_to_their_day(){
        MetricDto newMetric = StaticTestData.metric;
        newMetric.setExerciseSpending(newMetric.getExerciseSpending()+caloriesToAdd);
        MetricsAPI.updateMetric(newMetric.getId(),newMetric,null);
    }

    @And("the User has entered burned calories to set as their day total")
    public void the_user_has_entered_burned_calories_to_set_as_their_day_total(){
        caloriesToSet = 100;
    }

    @And("the User decides to set the entered calories to their day")
    public void the_user_decides_to_set_the_entered_calories_to_their_day(){
        MetricDto newMetric = StaticTestData.metric;
        newMetric.setExerciseSpending(caloriesToSet);
        MetricsAPI.updateMetric(newMetric.getId(),newMetric,null);
    }

    @And("the User has entered invalid data in the burned calories to add to their day")
    public void the_user_has_entered_invalid_data_in_the_burned_calories_to_add_to_their_day(){
        caloriesToAdd = -1;
    }

    @And("the User has entered invalid data in the burned calories to set to their day")
    public void the_user_has_entered_invalid_data_in_the_burned_calories_to_set_to_their_day(){
        caloriesToSet = -1;
    }
}
