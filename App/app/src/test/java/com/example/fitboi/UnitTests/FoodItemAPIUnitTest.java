package com.example.fitboi.UnitTests;

import com.example.fitboi.api.FoodItemAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodItemDto;
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
@PrepareForTest(FoodItemAPI.class)
public class FoodItemAPIUnitTest {

    private static UserDto user;
    private static FoodItemDto foodItem;
    private static List<FoodItemDto> foodItemList = new ArrayList<>();
    private static Consumer<List<FoodItemDto>> foodItemListFunction;
    private static Consumer<FoodItemDto> foodItemFunction;
    private static String desiredFood;
    private static boolean isFound;
    private static boolean isLogged;
    private static boolean isUnlogged;

    @BeforeClass
    public static void Setup() {

        desiredFood = "Chicken";

        user = new UserDto("test@gmail.com", "Test", "test123", "123", 21, 21, true);
        UserAPI.addUser(user, null);
    }

    @Test
    public void getAllOfTheItems() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        foodItemList = FoodItemAPI.getFoodItems(null);
    }

    @Test
    public void getSpecificItem() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        foodItem = FoodItemAPI.getFoodItemsByPrefix(desiredFood, null).get(0);
        isFound = (foodItem != null);
    }

    @Test
    public void logFoodItem() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        FoodItemDto foodDto = FoodItemAPI.addFoodItemToUser(foodItem, user.getEmail(), null);
        isLogged = (foodDto != null);
    }

    @Test
    public void deleteLoggedFoodItem() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        FoodItemDto foodDto = FoodItemAPI.deleteFoodItemFromUser(foodItem.getId(), user.getEmail(), null);
        isUnlogged = (foodDto != null);
    }
}
