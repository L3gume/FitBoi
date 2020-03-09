package com.example.fitboi.cucumber.steps;


import com.example.fitboi.api.MealAPI;
import com.example.fitboi.api.MetricsAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.MealDto;
import com.example.fitboi.dto.MetricDto;
import com.example.fitboi.dto.UserDto;

import java.util.List;

import cucumber.api.java.en.*;

class StaticTestData {
    static UserDto user = null;
    static UserDto updatedUser = null;
    static UserDto actualUpdatedUser = null;
    static MetricDto metric = null;
    static MealDto meal = null;
    static FoodDto food1 = new FoodDto(0,"Food1", 10,1,20,30,40);
    static FoodDto food2 = new FoodDto(1,"Food2", 50,2,60,70,80);
    static boolean isConnectionGood = true;
}

public class Stepdefs {

    @Given("the User is logged into FitBoi")
    public void the_user_is_logged_into_fitboi() {
        // reset data
        StaticTestData.metric = null;
        StaticTestData.meal = null;
        StaticTestData.food1 = new FoodDto(0,"Food1", 10,1,20,30,40);
        StaticTestData.food2 = new FoodDto(1,"Food2", 50,2,60,70,80);
        StaticTestData.isConnectionGood = true;

        StaticTestData.updatedUser = null;
        StaticTestData.actualUpdatedUser = null;
        StaticTestData.user = UserAPI.getUser("test@gmail.com",null);
        if (StaticTestData.user == null) {
            StaticTestData.user = new UserDto("test@gmail.com","Test", "test",
                    "12345","1998-01-01","Male",180);
            StaticTestData.user = UserAPI.addUser(StaticTestData.user, null);
        }
        StaticTestData.updatedUser = new UserDto(StaticTestData.user.getEmail(), StaticTestData.user.getName(), StaticTestData.user.getUserName(),
                StaticTestData.user.getPassword(), StaticTestData.user.getDob(), StaticTestData.user.getBiologicalSex(), StaticTestData.user.getHeight());
        // create metric for user if not already
        List<MetricDto> metrics = MetricsAPI.getAllUserMetrics(StaticTestData.user.getEmail(), null);
        if (metrics == null || metrics.isEmpty()) {
            StaticTestData.metric = new MetricDto(0,"2020-01-01",0);
            StaticTestData.metric = MetricsAPI.addMetric(StaticTestData.user.getEmail(), StaticTestData.metric,null);
        } else {
            StaticTestData.metric = metrics.get(0);
        }
        // create meal for metric if not already
        List<MealDto> meals = MealAPI.getUserMeals(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), null);
        if (meals == null || meals.isEmpty()) {
            StaticTestData.meal = new MealDto(0,"Lunch");
            StaticTestData.meal = MealAPI.createMeal(StaticTestData.user.getEmail(), StaticTestData.metric.getId(), StaticTestData.meal,null);
        } else {
            StaticTestData.meal = meals.get(0);
        }
        StaticTestData.isConnectionGood = true;
    }
}
