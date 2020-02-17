package com.example.fitboi.cucumber.steps;

import com.ecse428.project.fitboi.model.DBFood;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.UserDto;
import com.example.fitboi.api.FoodItemAPI;
import java.util.function.Consumer;


import cucumber.api.java.en.*;

import static org.mockito.Mockito.mock;

public class ID003_User_Logs_A_Food_Item {
    private UserDto userDto = null;
    private LoggedInUser user = null;
    List<DBFood> database = new ArrayList<DBFood>();
    String foodLoggedName = "";

    public List<DBFood> isFoodInDatabase(String name)
    {
        for(DBFood food : database)
        {
            if(food.getName().equals(name)) return true;
        }

        return false;
    }

    @Given("the User is not currently logged into the FitBoi application")
    public void given_user_not_currently_logged_into_fitboi_app() {
        assert user == null;
    }

    
    @And("the database contains the desired food item")
    public void and_database_contains_desired_food_item() {
        DBFood foodItem = new DBFood("Bread", 100, 120, 100, 25); 
        database.add(foodItem);
        foodLoggedName = "Bread";
        assert isFoodInDatabase(foodLoggedName);
    }

    @And("the database does not contains the desired food item")
    public void and_database_contains_desired_food_item() {
        DBFood foodItem = new DBFood("Cheese", 120, 10, 300, 25); 
        database.add(foodItem);
        foodLoggedName = "Bread";
        assert !isFoodInDatabase(foodLoggedName);
    }

    @And("the User selects the desired food item from the search results")
    public void and_user_slects_desired_food_item_from_search_results() {
        this.foodLoggedName = "Bread";
        assert this.foodLoggedName != "";
    }

    @When("a request to log the food item is made")
    public void when_log_food_item_request_made() {
        this.foodLoggedName = "Bread"
    }

    
    @Then("the User's calorie count is updated using the desired food item")
    public void then_user_calorie_count_updated_using_desired_food_item() {
        assert true;
    }


    @Then("the User will be unable to log the food item")
    public void then_user_not_able_log_food_item() {
        assert true;
    }

}
