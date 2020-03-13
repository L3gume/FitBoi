package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.dto.MealDto;
import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.fitboi.service.MealService;
import com.ecse428.project.fitboi.service.MetricService;
import com.ecse428.project.fitboi.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class MealController {
    	
	@Autowired
    private MealService mealService;

    @Autowired
    private MetricService metricService;

    @Autowired
    private UserService userService;


    @GetMapping("meals")
    public ResponseEntity<?> getAllMeals()
    {
        // Find all the meals for the metric
        Iterable<Meal> meals = mealService.getAllMeals();

        // Convert the meals to DTOs
        List<MealDto> mealsDto = new ArrayList<MealDto>();
        for (Meal meal : meals) {
            mealsDto.add(convertToDto(meal));
        }

    	return new ResponseEntity<List<MealDto>>(mealsDto, HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics/{metric_id}/meal -> returns all the meals for a users metric
     * @param user_id
     * @param metric_id
     * @return
     */
    @GetMapping("{user_id}/metrics/{metric_id}/meal")
    public ResponseEntity<?> getUserMeals(@PathVariable String user_id, @PathVariable int metric_id)
    {
        // Make sure the metric is for the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user has no metrics for the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Find all the meals for the metric
        List<Meal> meals = metricService.getAllMeals(metric);
        if (meals == null) {
            return new ResponseEntity<String>("The user has no meals for the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Convert the meals to DTOs
        List<MealDto> mealsDto = new ArrayList<MealDto>();
        for (Meal meal : meals) {
            mealsDto.add(convertToDto(meal));
        }

    	return new ResponseEntity<List<MealDto>>(mealsDto, HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics/{metric_id}/meal/{meal_id} -> returns the a specific meal from the user
     * @param user_id
     * @param metric_id
     * @param meal_id
     * @return
     */
    @GetMapping("{user_id}/metrics/{metric_id}/meal/{meal_id}")
    public ResponseEntity<?> getMeal(@PathVariable String user_id, @PathVariable int metric_id, @PathVariable int meal_id)
    {
        // Make sure the metric is for the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user has no metrics for the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }
        
        // Get the meal given the meal_id
        Meal meal = metricService.getUserMeal(metric, meal_id);
        if (meal == null) {
            return new ResponseEntity<String>("The user has no meals for the given meal_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Convert to DTO
        MealDto mealDto = convertToDto(meal);

    	return new ResponseEntity<MealDto>(mealDto, HttpStatus.OK);
    }

    /**
     * POST
     * /meal/{user_id}/metrics/{metric_id}/meal/ -> create a new Meal for a Metric
     * @param user_id
     * @param metric_id
     * @return
     */
    @PostMapping("{user_id}/metrics/{metric_id}/meal")
    public ResponseEntity<?> createMeal(@PathVariable String user_id, @PathVariable int metric_id, @RequestBody ObjectNode objectNode) {    

        // Make sure the metric is for the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user has no metric for the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Create the meal and add it to the metric
        Meal meal = new Meal(MealType.valueOf(objectNode.get("mealType").asText()));
        metric.addMeal(meal);

        // Persist the metric
        metricService.updateMetrics(metric);

        // Convert to DTO
        MealDto mealDto = convertToDto(meal);
       
    	return new ResponseEntity<MealDto>(mealDto, HttpStatus.CREATED);
    }

    /**
     * DELETE
     * /meal/{user_id}/metrics/{metric_id}/meal/{mealId}-> deletes a Meal from the users metrics
     * @param user_id
     * @param metric_id
     * @param meal_id
     * @return
     */
    @DeleteMapping("{user_id}/metrics/{metric_id}/meal/{meal_id}")
    public ResponseEntity<?> deleteGoal(@PathVariable String user_id, @PathVariable int metric_id, @PathVariable int meal_id) {
    	
    	// Make sure the metric is for the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user has no metrics for the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Delete the meal from the DB
        Meal deletedMeal = metricService.deleteMeal(metric, meal_id);

    	return new ResponseEntity<MealDto>(convertToDto(deletedMeal), HttpStatus.OK);
    }

    private MealDto convertToDto(Meal meal) {
    	return new MealDto(
            meal.getId(),
            meal.getMealType().toString()
    		);
    }
}
