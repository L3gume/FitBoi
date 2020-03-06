package com.ecse428.project.fitboi.controller;

import java.sql.Date;

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
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.GoalType;
import com.ecse428.project.fitboi.model.MacroDistribution;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.GoalService;
import com.ecse428.project.fitboi.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/users/")
public class GoalController {
    	
	@Autowired
    private UserService userService;
    
    @Autowired
    private GoalService goalService;

    /**
     * GET
     * /users/{userEmail}/goals -> returns all the goals for a user
     * @param userEmail
     * @return
     */
    @GetMapping("{userEmail}/goal")
    public ResponseEntity<GoalDto> getUserGoal(@PathVariable String userEmail)
    {
        GoalDto goalDto = new GoalDto();
        Goal goal = goalService.getUserGoal(userEmail);

        goalDto = convertToDto(goal);
        

    	return new ResponseEntity<GoalDto>(goalDto, HttpStatus.OK);
    }

        /**
     * POST
     * create a new Goal for a User
     * @param userEmail
     * @return
     */
    @PostMapping("{userEmail}/goal")
    public ResponseEntity<?> createGoal(@PathVariable("userEmail") String userEmail, @RequestBody ObjectNode objectNode) {
    	if (objectNode == null) {
    		return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Goal: " + objectNode);
        
        UserProfile user = userService.getUser(userEmail);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        ActivityLevel activityLevel;
        GoalType goalType;
        
        float fats, carbs, proteins;
        try {
            activityLevel = ActivityLevel.valueOf(objectNode.get("activityLevel").asText());
            goalType = GoalType.valueOf(objectNode.get("goalType").asText());

            fats = (float) objectNode.get("fats").asDouble();
            carbs = (float) objectNode.get("carbs").asDouble();
            proteins = (float) objectNode.get("proteins").asDouble();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid enum value", HttpStatus.NOT_FOUND);
        } catch(NullPointerException e) {
            return new ResponseEntity<>("The macro distribution is missing a field: ('fats', 'carbs', 'proteins')", HttpStatus.NOT_FOUND);
        }
        MacroDistribution macroDistribution  = new MacroDistribution(fats, carbs, proteins);
        System.out.println("fats: " + macroDistribution.getFats() + "  carbs: " + macroDistribution.getCarbs() + "  protiens: " + macroDistribution.getProtein());

        int cal = objectNode.get("baseCalories").asInt();
        boolean result =  objectNode.get("result").asBoolean();
      
        float weightGoal = objectNode.get("weightGoal").asLong();

        Goal goal = new Goal(cal, result, Date.valueOf(objectNode.get("startDate").asText()),
                Date.valueOf(objectNode.get("endDate").asText()), weightGoal, activityLevel,
                goalType, macroDistribution);

    	if (!goalService.setUserGoal(goal, user))
    	{
    		return new ResponseEntity<>("Unable to set goal", HttpStatus.UNPROCESSABLE_ENTITY);
    	}

    	return new ResponseEntity<>(convertToDto(goal), HttpStatus.CREATED);
    }

    /**
     * POST
     * updates goal in db given 
     * @param userEmail 
     * @param goalDto
     * @return
     */

    @PutMapping("{userEmail}/goal")
    public ResponseEntity<?> updateGoal(@PathVariable("userEmail") String userEmail, @RequestBody GoalDto goalDto) {
        if (goalDto == null) {
            return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
        }
        
        Goal goal = userService.getUserGoal(userEmail);
        goal = cloneDto(goal, goalDto);

        if (!goalService.updateGoal(userEmail, goal))
        {
            return new ResponseEntity<String>("Goal does not exist", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<GoalDto>(goalDto, HttpStatus.OK);
    }

    /**
     * DELETE
     * /users/{user_id}/goals/{goalId} -> deletes a goal from the DB
     * @param userEmail 
     * @return
     */
    @DeleteMapping("{userEmail}/goal")
    public ResponseEntity<?> deleteGoal(@PathVariable String userEmail) {
    	
    	Goal deletedGoal = goalService.deleteGoal(userEmail);

    	if (deletedGoal == null) {
    		return new ResponseEntity<String>("Goal does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<GoalDto>(convertToDto(deletedGoal), HttpStatus.OK);
    }

    private GoalDto convertToDto(Goal goal) {
        MacroDistribution dist = goal.getMacroDistribution();
    	return new GoalDto(
    	        goal.getId(),
                goal.getBaseCalories(),
                goal.getResult(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getWeightGoal(),
                goal.getActivityLevel().name(),
                goal.getGoalType().name(),
                dist.getFats(),
                dist.getCarbs(),
                dist.getProtein()
    			);
    }

    // account for auto-gen issue
    private Goal cloneDto(Goal goal, GoalDto goalDto){
        goal.setBaseCalories(goalDto.getBaseCalories());
        goal.setResult(goalDto.isResult());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setWeightGoal(goalDto.getWeightGoal());
        goal.setActivityLevel(ActivityLevel.valueOf(goalDto.getActivityLevel()));
        goal.setGoalType(GoalType.valueOf(goalDto.getGoalType()));
        goal.getMacroDistribution().setFats(goalDto.getFats());
        goal.getMacroDistribution().setCarbs(goalDto.getCarbs());
        goal.getMacroDistribution().setProtein(goalDto.getProtein());
        return goal;
    }

    private Goal convertToDomainObject(GoalDto goalDto) {
		
		Goal goal = new Goal(
            goalDto.getBaseCalories(),
            goalDto.isResult(),
            goalDto.getStartDate(),
            goalDto.getEndDate(),
            goalDto.getWeightGoal(),
            ActivityLevel.valueOf(goalDto.getActivityLevel()),
            GoalType.valueOf(goalDto.getGoalType()),
            new MacroDistribution(goalDto.getFats(), goalDto.getCarbs(), goalDto.getProtein())
                );
                
		return goal;
    }
}
