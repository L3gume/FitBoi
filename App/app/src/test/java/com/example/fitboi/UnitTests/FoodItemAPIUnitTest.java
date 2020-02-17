package com.example.fitboi.UnitTests;

import com.example.fitboi.api.FoodItemAPI;
import com.example.fitboi.dto.FoodItemDto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
/*
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

<<<<<<< HEAD:App/app/src/test/java/com/example/fitboi/UnitTests/FoodItemUnitTest.java
/*@RunWith(PowerMockRunner.class)
@PrepareForTest(FoodItemAPI.class)*/
public class FoodItemUnitTest {
/*
    private static FoodItemDto foodItem;
    private static List<FoodItemDto> foodItemList = new ArrayList<>();
    private static Consumer<List<FoodItemDTO>> foodItemListFunction;
    private static Consumer<FoodItemDTO> foodItemFunction;
=======
@RunWith(PowerMockRunner.class)
@PrepareForTest(FoodItemAPI.class)
public class FoodItemAPIUnitTest {

    private static FoodItemDto foodItem;
    private static List<FoodItemDto> foodItemList = new ArrayList<>();
    private static Consumer<List<FoodItemDto>> foodItemListFunction;
    private static Consumer<FoodItemDto> foodItemFunction;
>>>>>>> dev:App/app/src/test/java/com/example/fitboi/UnitTests/FoodItemAPIUnitTest.java
    private static String desiredFood;

    @BeforeClass
    public static void Setup() {
        foodItemListFunction = new Consumer<List<FoodItemDto>>() {
            @Override
            public void accept(List<FoodItemDto> foodItemDTOS) {
                foodItemList.addAll(foodItemDTOS);
            }
        };

        foodItemFunction = new Consumer<FoodItemDto>() {
            @Override
            public void accept(FoodItemDto foodItemDTO) {
                foodItem = foodItemDTO;
            }
        };

        desiredFood = "Chicken";
    }

    @Test
    public void getAllOfTheItems() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        FoodItemAPI.getAllFoodItems(foodItemListFunction);
    }

    @Test
    public void getSpecificItem() {
        PowerMockito.mockStatic(FoodItemAPI.class);
        PowerMockito.doNothing().when(FoodItemAPI.class);
        FoodItemAPI.getFoodItem(foodItemFunction, desiredFood);
    }
    */
}
