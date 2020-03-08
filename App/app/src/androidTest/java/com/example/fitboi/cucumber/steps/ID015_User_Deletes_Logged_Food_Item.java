package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.api.MealAPI;
import com.example.fitboi.api.MetricsAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.MealDto;
import com.example.fitboi.dto.MetricDto;
import com.example.fitboi.dto.UserDto;

import java.util.List;

import cucumber.api.java.en.*;

public class ID015_User_Deletes_Logged_Food_Item {

    UserDto user;
    MetricDto metric;
    MealDto meal;
    FoodDto food1 = new FoodDto(0,"Food1", 10,1,20,30,40);
    FoodDto food2 = new FoodDto(1,"Food2", 50,2,60,70,80);
    boolean isConnectionGood;

    @Given("the User is logged into FitBoi")
    public void the_user_is_logged_into_fitboi(){
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
        isConnectionGood = true;
    }

    @Given("the User has more than one logged food items for the meal")
    public void the_user_has_more_than_one_logged_food_items_for_the_meal(){
        FoodAPI.addFoodToMeal(food1,user.getEmail(),metric.getId(),meal.getId(),null);
        FoodAPI.addFoodToMeal(food2,user.getEmail(),metric.getId(),meal.getId(),null);
    }

    @When("deleting a food item from the meal")
    public void deleting_a_food_item_from_the_meal(){
        if (isConnectionGood) {
            FoodAPI.deleteFoodFromMeal(user.getEmail(),metric.getId(),meal.getId(),food1.getId(), null);
        }
    }

    @When("deleting multiple food items from the meal")
    public void deleting_multiple_food_items_from_the_meal(){
        if (isConnectionGood) {
            FoodAPI.deleteFoodFromMeal(user.getEmail(),metric.getId(),meal.getId(),food1.getId(), null);
            FoodAPI.deleteFoodFromMeal(user.getEmail(),metric.getId(),meal.getId(),food2.getId(), null);
        }
    }

    @When("the User exits the current window")
    public void the_user_exits_the_current_window(){
        // do nothing
    }

    @Then("the food item is dissociated from the meal")
    public void the_food_item_is_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(user.getEmail(),metric.getId(),meal.getId(), null);
        assert (!foods.contains(food1));
    }

    @Then("the food items are dissociated from the meal")
    public void the_food_items_are_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(user.getEmail(),metric.getId(),meal.getId(), null);
        assert (!foods.contains(food1) && !foods.contains(food2));
    }

    @Then("the food item is not dissociated from the meal")
    public void the_food_item_is_not_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(user.getEmail(),metric.getId(),meal.getId(), null);
        assert (foods.contains(food1));
    }

    @And("the User has at least one logged food item for the meal")
    public void the_user_has_at_least_one_logged_food_item_for_the_meal(){
        FoodAPI.addFoodToMeal(food1,user.getEmail(),metric.getId(),meal.getId(),null);
    }

    @And("they are indicating a food item deletion")
    public void they_are_indicating_a_food_item_deletion(){
        // do nothing
    }

    @And("there is a lack of connection between the server and client")
    public void there_is_a_lack_of_connection_between_the_server_and_client(){
        isConnectionGood = false;
    }

    @And("the Connectivity issues prevented saving message is issued")
    public void the_error_message_is_issued(){
        // do nothing
    }
}
