package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.api.MetricAPI;
import com.example.fitboi.dto.FoodDto;

import java.util.List;

import cucumber.api.java.en.*;

public class ID003_User_Logs_A_Food_Item {
    private FoodDto food;
    private List<FoodDto> foods;

    @When("a request to log the food item is made")
    public void a_request_to_log_the_food_item_is_made(){
        food = FoodAPI.addFoodToMeal(food,StaticTestData.user.getEmail(),StaticTestData.metric.getId(),StaticTestData.meal.getId(),null);
    }

    @Then("the User's calorie count is updated using the desired food item")
    public void the_users_calorie_count_is_updated_using_the_desired_food_item(){
        int current_cal = MetricAPI.getUserMetric(StaticTestData.user.getEmail(),StaticTestData.metric.getId(),null).getExerciseSpending();
        assert (current_cal == food.getCal());
    }

    @Then("the User will be unable to log the food item")
    public void the_user_will_be_unable_to_log_the_food_item(){
        assert (food == null);
    }

    @And("the database contains the desired food item")
    public void the_database_contains_the_desired_food_item(){
        String desiredFood = "chocolate";
        foods = FoodAPI.getFoodsByPrefix(desiredFood,1,null);
    }

    @And("the User selects the desired food item from the search results")
    public void the_user_selects_the_desired_food_item_from_the_search_results(){
        food = foods.get(0);
    }

    @And("the database does not contain the desired food item")
    public void the_database_does_not_contain_the_desired_food_item(){
        foods = null;
        food = null;
    }

}
