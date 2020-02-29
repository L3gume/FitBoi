package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ecse428.project.fitboi.dto.DBFoodDto;
import com.ecse428.project.fitboi.dto.MealDto;
import com.ecse428.project.fitboi.model.DBFood;
import com.ecse428.project.fitboi.model.FoodItem;
import com.ecse428.project.fitboi.model.MacroDistribution;
import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.service.FoodService;
import com.ecse428.project.fitboi.service.MealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/food/")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private MealService mealService;

    /**
     * GET
     * /food/{foodName}/ -> returns a list of food in the food DB that have that exact name
     * @param foodName
     * @param numElements
     * @return
     */
    @GetMapping("{foodName}/{numElements}")
    public ResponseEntity<?> getFoodItem(@PathVariable String foodName, @PathVariable int numElements) {
        List<DBFood> foodList = foodService.getDBFoodFromName(foodName);
        List<DBFood> smallList = foodList.stream().limit(numElements).collect(Collectors.toList());
        List<DBFoodDto> foodDtoList = new ArrayList<DBFoodDto>();
        for(DBFood food : smallList)
        {
            foodDtoList.add(converToDto(food));
        }

    	return new ResponseEntity<List<DBFoodDto>>(foodDtoList, HttpStatus.OK);
    }

    /**
     * POST
     * /food/{mealId}/ -> adds a food to a meal
     * @param 
     * @return
     */
    @PostMapping("{mealId}/{portionSize}")
    public ResponseEntity<?> addFoodtoMeal(@PathVariable int mealId, @PathVariable float portionSize, @RequestBody DBFoodDto foodDto) {
        
        Meal meal = mealService.getMeal(mealId);

        FoodItem foodItem = new FoodItem(foodDto.getName(), (int)foodDto.getCal(), portionSize, new MacroDistribution((float)foodDto.getFat(), (float)foodDto.getCarbs(), (float)foodDto.getProtein()));

        meal.addFoodItem(foodItem);
        
        mealService.updateMeal(meal);

    	return new ResponseEntity<MealDto>(convertToDto(meal), HttpStatus.OK);
    }


    public DBFoodDto converToDto(DBFood food)
    {
        return new DBFoodDto(food.getName(), food.getCal(), food.getProtein(), food.getFat(),
         food.getCarbs());
    }
    
    private MealDto convertToDto(Meal meal) {
    	return new MealDto(
            meal.getId(),
            meal.getMealType(),
            meal.getFoodItems()	
    		);
    }
}
