package com.example.fitboi;

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

/*@RunWith(PowerMockRunner.class)
@PrepareForTest(FoodItemAPI.class)*/
public class FoodItemUnitTest {
/*
    private static FoodItemDto foodItem;
    private static List<FoodItemDto> foodItemList = new ArrayList<>();
    private static Consumer<List<FoodItemDTO>> foodItemListFunction;
    private static Consumer<FoodItemDTO> foodItemFunction;
    private static String desiredFood;

    @BeforeClass
    public static void Setup() {
        foodItemListFunction = new Consumer<List<FoodItemDTO>>() {
            @Override
            public void accept(List<FoodItemDTO> foodItemDTOS) {
                foodItemList.addAll(foodItemDTOS);
            }
        };

        foodItemFunction = new Consumer<FoodItemDTO>() {
            @Override
            public void accept(FoodItemDTO foodItemDTO) {
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
