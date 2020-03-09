package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;
import com.example.fitboi.api.GoalAPI;
import com.example.fitboi.dto.GoalDto;
import cucumber.api.java.en.*;

public class ID004_User_Edits_Current_Goal {

	private UserDto user;
	private GoalDto goal;
	private GoalDto updatedGoal;
    private GoalDto actualUpdatedGoal;

    private boolean isValidGoal(GoalDto goal){
    	if(goal.getBaseCalories() < 0){
    		return false;
    	}else if(goal.getWeightGoal() < 0){
    		return false;
    	}else if(goal.getCarbs() > 0 && goal.getCarbs() < 1){
    		return false;
    	}else if(goal.getFats() > 0 && goal.getFats() < 1){
    		return false;
    	}else if(goal.getProtein() > 0 && goal.getProtein() < 1){
    		return false;
    	}
    	return true;
    }


    // generic 

    @Given("the User goal exists")
    public void the_user_goal_exists() {
        // create user
        user = UserAPI.getUser("test@gmail.com",null);
        if (user == null) {
            user = new UserDto("test@gmail.com","Test", "test", "12345","1998-01-01","Male",180);
            user = UserAPI.addUser(user, null);
        }
        
        // create goal
        goal = new GoalDto(1337, 10, true,
                "2020-01-01", "2020-12-12", 12,
                "High", "Lose", 0.2, 0.4, 0.4);

        // persist goal
        GoalAPI.createGoal("boaty@mcboatface.ca", goal, null);
        assert true;
        
    }

    // Scenario 1

    @When("valid target goals are entered")
    public void valid_target_goals_are_entered() {
    	
    	// get goal id
        GoalDto g = GoalAPI.getUserGoal("test@gmail.com",null);
    	int goal_id = g != null ? g.getId() : 0;

    	// update goal
    	updatedGoal = new GoalDto(goal_id, 10, true,
                "2020-01-01", "2020-12-12", 12,
                "High", "Lose", 0.4, 0.2, 0.4);

    	// update valid goal
    	if(isValidGoal(updatedGoal)){
    		GoalAPI.updateGoal("test@gmail.com", updatedGoal, null);
    		assert true;
    	}else{
    		assert false;
    	}

    }

    @Then("the goal is updated and persisted to the database")
    public void the_goal_is_updated_and_persisted_to_the_database(){
    	
    	// get goal
		GoalDto g = GoalAPI.getUserGoal("test@gmail.com",null);
		int goal_id = g != null ? g.getId() : 0;

		// create copy of expected goal
    	actualUpdatedGoal = new GoalDto(goal_id, 10, true,
                "2020-01-01", "2020-12-12", 12,
                "High", "Lose", 0.4, 0.2, 0.4);

    	// compare goal
    	if(updatedGoal.equals(actualUpdatedGoal)){
    		assert true;
    	}else{
    		assert false;
    	}

    }


    // Scenario 2

    @When("invalid target goals are entered")
    public void invalid_target_goals_are_entered() {

    	// get goal id
		GoalDto g = GoalAPI.getUserGoal("test@gmail.com",null);
		int goal_id = g != null ? g.getId() : 0;

		// update goal
    	updatedGoal = new GoalDto(goal_id, 10, true,
                "2020-01-01", "2020-12-12", 12,
                "High", "Lose", 69420, 0.2, 0.4);

    	// dont update invalid goal
    	if(isValidGoal(updatedGoal)){
    		GoalAPI.updateGoal("test@gmail.com", updatedGoal, null);
    		assert false;
    	}else{
    		assert true;
    	}

    }

    @Then("the goal is not updated")
    public void the_goal_is_not_updated_and_persisted_to_the_database(){

    	// get goal
		GoalDto g = GoalAPI.getUserGoal("test@gmail.com",null);
		int goal_id = g != null ? g.getId() : 0;

		// create copy of unexpected goal
    	actualUpdatedGoal = new GoalDto(goal_id, 10, true,
                "2020-01-01", "2020-12-12", 12,
                "High", "Lose", 0.4, 0.2, 0.4);

    	// compare goal
    	if(updatedGoal.equals(actualUpdatedGoal)){
    		assert false;
    	}else{
    		assert true;
    	}

    }

}

