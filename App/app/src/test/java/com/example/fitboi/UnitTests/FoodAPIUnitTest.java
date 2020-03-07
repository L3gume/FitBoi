package com.example.fitboi.UnitTests;

import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.UserDto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
/*
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FoodAPI.class)
public class FoodAPIUnitTest {

    private static UserDto user;
    private static FoodDto foodItem;
    private static List<FoodDto> foodItemList = new ArrayList<>();
    private static Consumer<List<FoodDto>> foodItemListFunction;
    private static Consumer<FoodDto> foodItemFunction;
    private static String desiredFood;
    private static boolean isFound;
    private static boolean isLogged;
    private static boolean isUnlogged;

    @BeforeClass
    public static void Setup() {

        desiredFood = "Chicken";

        user = new UserDto("test@gmail.com", "Test", "test123", "123", "1998-02-23" , "Male", 180);
        UserAPI.addUser(user, null);
    }

    @Test
    public void getAllOfTheItems() {
        PowerMockito.mockStatic(FoodAPI.class);
        PowerMockito.doNothing().when(FoodAPI.class);
        foodItemList = FoodAPI.getFoodsByPrefix("", 10, null);
    }

    @Test
    public void getSpecificItem() {
        PowerMockito.mockStatic(FoodAPI.class);
        PowerMockito.doNothing().when(FoodAPI.class);
        foodItem = FoodAPI.getFoodsByPrefix(desiredFood, 1,null).get(0);
        isFound = (foodItem != null);
    }

    @Test
    public void logFoodItem() {
        PowerMockito.mockStatic(FoodAPI.class);
        PowerMockito.doNothing().when(FoodAPI.class);
        FoodDto foodDto = FoodAPI.addFoodToMeal(foodItem, user.getEmail(), 1,1,null);
        isLogged = (foodDto != null);
    }

    @Test
    public void deleteLoggedFoodItem() {
        PowerMockito.mockStatic(FoodAPI.class);
        PowerMockito.doNothing().when(FoodAPI.class);
        FoodDto foodDto = FoodAPI.deleteFoodFromMeal(user.getEmail(), 1,1,foodItem.getId(), null);
        isUnlogged = (foodDto != null);
    }
}
