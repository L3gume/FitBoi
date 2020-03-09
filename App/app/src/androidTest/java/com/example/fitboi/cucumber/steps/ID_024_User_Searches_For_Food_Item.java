package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.dto.FoodDto;

import java.util.ArrayList;

import cucumber.api.java.en.*;

public class ID_024_User_Searches_For_Food_Item {
    private String searchQuery;
    private ArrayList<FoodDto> searchResult;

    @And("they entered some data to search for")
    public void they_entered_some_data_to_search_for() {
        searchQuery = "Chicken";
    }

    @And("they did not enter any data to search for")
    public void they_did_not_enter_any_data_to_search_for() {
        searchQuery = null;
    }

    @When("searching for the item")
    public void searching_for_the_item() {
        searchResult =
                (ArrayList<FoodDto>)FoodAPI.getFoodsByPrefix(searchQuery, 420, null);
    }

    @Then("the search query is sent")
    public void the_search_query_is_sent() {
        assert searchResult != null;
    }

    @Then("the search query is not sent")
    public void the_search_query_is_not_sent() {
        assert searchResult == null;
    }

    @And("a response is received")
    public void a_response_is_received() {
        if (searchResult != null) {
            for (int i = 0; i < searchResult.size(); ++i) {
                assert searchResult.get(i).getName().contains(searchQuery);
            }
        }
    }

    @And("no response is received")
    public void no_response_is_received() {
        assert searchResult == null;
    }

}
