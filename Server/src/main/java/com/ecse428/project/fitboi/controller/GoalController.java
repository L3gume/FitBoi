package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

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

import com.ecse428.project.fitboi.dto.*;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.Goal;
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
    @GetMapping("{userEmail}/goals")
    public ResponseEntity<List<GoalDto>> getUserGoals(@PathVariable String userEmail)
    {
        List<GoalDto> goalDtos = new ArrayList<GoalDto>();
        List<Goal> goals = goalService.getUserGoals(userEmail);

        for(Goal goal : goals)
        {
            goalDtos.add(convertToDto(goal));
        }

    	return new ResponseEntity<List<GoalDto>>(goalDtos, HttpStatus.OK);
    }

        /**
     * POST
     * create a new Goal for a User
     * @param userEmail
     * @return
     */
    @PostMapping("{userEmail}/goals")
    public ResponseEntity<?> createGoal(@PathVariable("userEmail") String userEmail, @RequestBody ObjectNode objectNode) {
    	if (objectNode == null) {
    		return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
        }
        
        UserProfile user = userService.getUser(userEmail);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        ActivityLevel activityLevel;
        
        float fats, carbs, proteins;
        try {
            activityLevel = ActivityLevel.valueOf(objectNode.get("activityLevel").asText());

            fats = (float) objectNode.get("fats").asLong();
            carbs = (float) objectNode.get("carbs").asLong();
            proteins = (float) objectNode.get("proteins").asLong();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Activity Level invalid ('Low', 'Medium', 'High')", HttpStatus.NOT_FOUND);
        } catch(NullPointerException e) {
            return new ResponseEntity<>("The macro distribution is missing a field: ('fats', 'carbs', 'proteins')", HttpStatus.NOT_FOUND);
        }
        MacroDistribution macroDistribution  = new MacroDistribution(fats, carbs, proteins);

        int cal = objectNode.get("baseCalories").asInt();
        boolean result =  objectNode.get("result").asBoolean();
      
        float weight = objectNode.get("weight").asLong();

        GoalDto goal = new GoalDto(cal, result, Date.valueOf(objectNode.get("startDate").asText()), weight, activityLevel, macroDistribution);
       
        //TODO This method is not working
    	if (!goalService.addGoaltoUser(convertToDomainObject(goal), user))
    	{
    		return new ResponseEntity<String>("Goal already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    	}

    	return new ResponseEntity<GoalDto>(goal, HttpStatus.CREATED);
    }

        /**
     * DELETE
     * /users/{user_id}/goals/{goalId} -> deletes a goal from the DB
     * @param userEmail 
     * @param goalId
     * @return
     */
    @DeleteMapping("{userEmail}/goals/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable String userEmail, @PathVariable int goalId) {
    	
    	Goal deletedGoal = goalService.deleteGoal(userEmail, goalId);

    	if (deletedGoal == null) {
    		return new ResponseEntity<String>("Goal does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<GoalDto>(convertToDto(deletedGoal), HttpStatus.OK);
    }



   // TODO: Add PATCH call

    private GoalDto convertToDto(Goal goal) {
    	return new GoalDto(
            goal.getBaseCalories(),
            goal.getResult(),
            goal.getStartDate(),
            goal.getWeight(),
            goal.getActivityLevel(),
            goal.getMacroDistribution()   			
    			);
    }

    private Goal convertToDomainObject(GoalDto goalDto) {
		
		Goal goal = new Goal(
            goalDto.getBaseCalories(),
            goalDto.getResult(),
            goalDto.getStartDate(),
            goalDto.getWeight(),
            goalDto.getActivityLevel(),
            goalDto.getMacroDistribution()
                );
                
		return goal;
    }
}