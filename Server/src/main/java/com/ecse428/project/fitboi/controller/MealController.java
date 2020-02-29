package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.dto.MealDto;
import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.service.MealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meal/")
public class MealController {
    	
	@Autowired
    private MealService mealService;

    /**
     * GET
     * /meal/{mealId} -> returns all the goals for a user
     * @param mealId
     * @return
     */
    @GetMapping("{mealId}")
    public ResponseEntity<MealDto> getMeal(@PathVariable int mealId)
    {
        Meal meal = mealService.getMeal(mealId);

        MealDto mealDto = convertToDto(meal);

    	return new ResponseEntity<MealDto>(mealDto, HttpStatus.OK);
    }

    /**
     * GET
     * /meal/ -> returns all the meals
     * @param mealId
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<MealDto>> getMeals()
    {
        Iterable<Meal> meals = mealService.getAllMeals();

        List<MealDto> mealsDto = new ArrayList<MealDto>();
        for (Meal meal : meals) {
            mealsDto.add(convertToDto(meal));
        }

    	return new ResponseEntity<List<MealDto>>(mealsDto, HttpStatus.OK);
    }

    /**
     * POST
     * create a new Meal for a Metric
     * @return
     */
    @PostMapping("{mealType}")
    public ResponseEntity<?> createMeal(@PathVariable MealType mealType) {    

        Meal meal = new Meal(mealType);
        mealService.addNewMeal(meal);
        MealDto mealDto = convertToDto(meal);
       
    	return new ResponseEntity<MealDto>(mealDto, HttpStatus.CREATED);
    }

    /**
     * DELETE
     * /meal/{mealId}-> deletes a Meal from the DB
     * @param mealId 
     * @return
     */
    @DeleteMapping("{mealId}")
    public ResponseEntity<?> deleteGoal(@PathVariable int mealId) {
    	
    	Meal deletedMeal = mealService.deleteMeal(mealId);

    	if (deletedMeal == null) {
    		return new ResponseEntity<String>("Meal does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<MealDto>(convertToDto(deletedMeal), HttpStatus.OK);
    }



   // TODO: Add PATCH call

    private MealDto convertToDto(Meal meal) {
    	return new MealDto(
            meal.getId(),
            meal.getMealType(),
            meal.getFoodItems()	
    		);
    }
}
