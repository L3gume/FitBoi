package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.api.MealAPI;
import com.example.fitboi.api.MetricsAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.MealDto;
import com.example.fitboi.dto.MetricDto;
import com.example.fitboi.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class ID003_User_Logs_A_Food_Item {

    private UserDto user;
    private MetricDto metric;
    private MealDto meal;

    private String desiredFood = "chocolate";
    private FoodDto food;
    private List<FoodDto> foods;

    @Given("the User is logged in")
    public void the_user_is_logged_in(){
        // create user if not already
        user = UserAPI.getUser("test@gmail.com",null);
        if (user == null) {
            user = new UserDto("test@gmail.com","Test", "test", "12345","1998-01-01","Male",180);
            user = UserAPI.addUser(user, null);
        }
        // create metric for user if not already
        List<MetricDto> metrics = MetricsAPI.getAllUserMetrics(user.getEmail(), null);
        if (metrics == null || metrics.isEmpty()) {
            metric = new MetricDto(0,"2020-01-01",0);
            metric = MetricsAPI.addMetric(user.getEmail(),metric,null);
        } else {
            metric = metrics.get(0);
        }
        // create meal for metric if not already
        List<MealDto> meals = MealAPI.getUserMeals(user.getEmail(), metric.getId(), null);
        if (meals == null || meals.isEmpty()) {
            meal = new MealDto(0,"Lunch");
            meal = MealAPI.createMeal(user.getEmail(),metric.getId(),meal,null);
        } else {
            meal = meals.get(0);
        }
    }

    @When("a request to log the food item is made")
    public void a_request_to_log_the_food_item_is_made(){
        food = FoodAPI.addFoodToMeal(food,user.getEmail(),metric.getId(),meal.getId(),null);
    }

    @Then("the User's calorie count is updated using the desired food item")
    public void the_users_calorie_count_is_updated_using_the_desired_food_item(){
        int current_cal = MetricsAPI.getUserMetric(user.getEmail(),metric.getId(),null).getExerciseSpending();
        assert (current_cal == food.getCal());
    }

    @Then("the User will be unable to log the food item")
    public void the_user_will_be_unable_to_log_the_food_item(){
        assert (food == null);
    }

    @And("the database contains the desired food item")
    public void the_database_contains_the_desired_food_item(){
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
