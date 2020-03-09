package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.UserDto;

import java.util.List;

import cucumber.api.java.en.*;

public class ID015_User_Deletes_Logged_Food_Item {

    @And("the User has more than one logged food items for the meal")
    public void the_user_has_more_than_one_logged_food_items_for_the_meal(){
        FoodAPI.addFoodToMeal(StaticTestData.food1, StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(),null);
        FoodAPI.addFoodToMeal(StaticTestData.food2, StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(),null);
    }

    @When("deleting a food item from the meal")
    public void deleting_a_food_item_from_the_meal(){
        if (StaticTestData.isConnectionGood) {
            FoodAPI.deleteFoodFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), StaticTestData.food1.getId(), null);
        }
    }

    @When("deleting multiple food items from the meal")
    public void deleting_multiple_food_items_from_the_meal(){
        if (StaticTestData.isConnectionGood) {
            FoodAPI.deleteFoodFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), StaticTestData.food1.getId(), null);
            FoodAPI.deleteFoodFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), StaticTestData.food2.getId(), null);
        }
    }

    @When("the User exits the current window")
    public void the_user_exits_the_current_window(){
        // do nothing
    }

    @Then("the food item is dissociated from the meal")
    public void the_food_item_is_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), null);
        assert (!foods.contains(StaticTestData.food1));
    }

    @Then("the food items are dissociated from the meal")
    public void the_food_items_are_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), null);
        assert (!foods.contains(StaticTestData.food1) && !foods.contains(StaticTestData.food2));
    }

    @Then("the food item is not dissociated from the meal")
    public void the_food_item_is_not_dissociated_from_the_meal(){
        List<FoodDto> foods = FoodAPI.getFoodsFromMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(), null);
        assert (foods.contains(StaticTestData.food1));
    }

    @And("the User has at least one logged food item for the meal")
    public void the_user_has_at_least_one_logged_food_item_for_the_meal(){
        FoodAPI.addFoodToMeal(StaticTestData.food1, StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal.getId(),null);
    }

    @And("they are indicating a food item deletion")
    public void they_are_indicating_a_food_item_deletion(){
        // do nothing
    }

    @And("there is a lack of connection between the server and client")
    public void there_is_a_lack_of_connection_between_the_server_and_client(){
        StaticTestData.isConnectionGood = false;
    }

    @And("the Connectivity issues prevented saving message is issued")
    public void the_error_message_is_issued(){
        // do nothing
    }
}
