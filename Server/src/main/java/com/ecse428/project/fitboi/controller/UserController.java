package com.ecse428.project.fitboi.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.project.fitboi.dto.*;
import com.ecse428.project.fitboi.model.FoodItem;
import com.ecse428.project.fitboi.model.MacroDistribution;
import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.fitboi.model.Sex;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.MealService;
import com.ecse428.project.fitboi.service.MetricService;
import com.ecse428.project.fitboi.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private MetricService metricService;

    @Autowired
    private MealService mealService;

    @GetMapping("{userEmail}/{password}")
    public ResponseEntity<?> loginUser(@PathVariable String userEmail, @PathVariable String password) {
        UserProfile user = userService.getUser(userEmail);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertToDto(user), HttpStatus.OK);
    }

    /**
     * GET
     * /users/ -> returns a list of all users
     */
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = new ArrayList<UserDto>();
        for (UserProfile user : userService.getAllUsers()) {
        	users.add(convertToDto(user));
        }
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }
    
    /**
     * GET
     * /users/{user_id}/ -> returns a specific user given their userEmail (email for now)
     * @param userEmail
     * @return
     */
    @GetMapping("{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable String userEmail) {
    	UserProfile user = userService.getUser(userEmail);
    	if (user == null || !userEmail.contains("@")) {
    		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(convertToDto(user), HttpStatus.OK);
    }
    
    /**
     * POST
     * /users/ -> adds a new user to the DB
     * @param userDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
    	if (userDto == null || !isValidUser(userDto)) {
    		return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
    	}
    	
        System.out.println("USER: " + userDto.toString());
       
    	
    	if (!userService.addNewUser(convertToDomainObject(userDto)))
    	{
    		return new ResponseEntity<String>("User already exists", HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }
    

    /**
     * UPDATE
     * /users/ -> update user in DB
     * @param userDto
     * @return
     */
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        if (userDto == null || !isValidUser(userDto)) {
            return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
        }
        
        System.out.println("USER: " + userDto.toString());
        
        if (!userService.updateUser(convertToDomainObject(userDto)))
        {
            return new ResponseEntity<String>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }


    /**
     * DELETE
     * /users/{user_id}/ -> deletes a user from the DB
     * @param userEmail
     * @return
     */
    @DeleteMapping("{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable String userEmail) {
    	
    	UserProfile deletedUser = userService.deleteUser(userEmail);
    	if (deletedUser == null || !userEmail.contains("@")) {
    		return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(convertToDto(deletedUser), HttpStatus.OK);
    }

    /**
     * POST
     * /users/{user_id}/metrics/{metric_id}/meals/{meal_id}/food -> adds a food to a meal
     * @param user_id
     * @param metric_id
     * @param meal_id
     * @return
     */
    @PostMapping("{user_id}/metrics/{metric_id}/meal/{meal_id}/food")
    public ResponseEntity<?> addFoodtoMeal(@PathVariable String user_id, @PathVariable int metric_id, @PathVariable int meal_id, @RequestBody ObjectNode food) 
    {
        
        Metric metric = userService.getUserMetric(user_id, metric_id);
        Meal meal = metricService.getUserMeal(metric, meal_id);

        String name = food.get("name").asText();
        int cal = food.get("cal").asInt();
        float portionSize = food.get("portionSize").asLong();
        float fats = food.get("fat").asLong();
        float carbs = food.get("carbs").asLong();
        float protein = food.get("protein").asLong();

        FoodItem foodItem = new FoodItem(name, cal, portionSize, new MacroDistribution(fats, carbs, protein));

        meal.addFoodItem(foodItem);
        
        mealService.updateMeal(meal);

    	return new ResponseEntity<FoodDto>(convertToDto(foodItem), HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics/{metric_id}/meals/{meal_id}/food -> adds a food to a meal
     * @param user_id
     * @param metric_id
     * @param meal_id
     * @return
     */
    @GetMapping("{user_id}/metrics/{metric_id}/meal/{meal_id}/food")
    public ResponseEntity<?> getFoodsFromMeal(@PathVariable String user_id, @PathVariable int metric_id, @PathVariable int meal_id) 
    {
        
        Metric metric = userService.getUserMetric(user_id, metric_id);
        Meal meal = metricService.getUserMeal(metric, meal_id);

        List<FoodItem> foods = meal.getFoodItems();
        
        List<FoodDto> foodsDto = new ArrayList<FoodDto>();
        for (FoodItem food : foods) {
            foodsDto.add(convertToDto(food));
        }
    	return new ResponseEntity<List<FoodDto>>(foodsDto, HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics/{metric_id}/meals/{meal_id}/food -> adds a food to a meal
     * @param user_id
     * @param metric_id
     * @param meal_id
     * @return
     */
    @DeleteMapping("{user_id}/metrics/{metric_id}/meal/{meal_id}/food/{food_id}")
    public ResponseEntity<?> deleteFoodFromMeal(@PathVariable String user_id, @PathVariable int metric_id, @PathVariable int meal_id, @PathVariable int food_id) 
    {
        
        Metric metric = userService.getUserMetric(user_id, metric_id);
        Meal meal = metricService.getUserMeal(metric, meal_id);
        FoodItem  food = mealService.deleteFood(meal, food_id);
        
        if (food == null) {
            return new ResponseEntity<String>("The food item does not exist in the given meal", HttpStatus.NOT_ACCEPTABLE);
        }
        
    	return new ResponseEntity<FoodDto>(convertToDto(food), HttpStatus.OK);
    }

    
    private UserDto convertToDto(UserProfile user) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 
    	return new UserDto(
    			user.getEmail(),
    			user.getName(),
    			user.getUserName(),
    			user.getPassword(),
    			df.format(user.getDOB()),
                user.getBiologicalSex().name(),
                user.getHeight()
    			);
    }
    
	private UserProfile convertToDomainObject(UserDto userDto) {
		
		UserProfile profile = new UserProfile(
				userDto.getEmail(),
				userDto.getName(),
				userDto.getUserName(),
				userDto.getPassword(),
				Date.valueOf(userDto.getDob()),
                userDto.getHeight(),
                Sex.valueOf(userDto.getBiologicalSex())
				);
		return profile;
    }

    private FoodDto convertToDto(FoodItem foodItem) {
        return new FoodDto(
            foodItem.getId(),
            foodItem.getName(),
            foodItem.getCalories(),
            foodItem.getPortionSize(),
            foodItem.getMacroDistribution().getFats(),
            foodItem.getMacroDistribution().getCarbs(),
            foodItem.getMacroDistribution().getProtein()
        );
    } 

    private boolean isValidUser(UserDto user){
        return user.getEmail().contains(("@")) && user.getHeight() > 0;
    }

}
