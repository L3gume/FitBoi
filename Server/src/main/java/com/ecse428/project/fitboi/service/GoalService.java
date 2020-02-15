package com.ecse428.project.fitboi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.MacroDistribution;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.GoalRepository;
import com.ecse428.project.repository.UserRepository;

/**
 * Provides an API to manipulate user information in the database.
 * TODO: We should probably throw exceptions for each of these methods instead of using booleans.
 * 		 Currently, booleans are used to signal either "OK" or "something went wrong".
 * 		 If multiple things can go wrong, "something went wrong" isn't descriptive.
 */
@Service
public class GoalService {
	
	
	@Autowired
	GoalRepository goalRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	

	public boolean addGoaltoUser(Goal goal, UserProfile user) {
		if (goalRepository.existsById(goal.getId())) {
			return false;
		}
		user.addGoal(goal);
		goalRepository.save(goal);
		userService.updateUser(user);
		return true;
	}
	
	public List<Goal> getUserGoals(String userEmail)
	{
		UserProfile user = userService.getUser(userEmail);
		return user.getGoals();
	}

		/**
	 * Deletes a goal from the database 
	 * @param userId
	 * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	 */
	public Goal deleteGoal(String userId, int goalId) {
    	if (!goalRepository.existsById(goalId)) {
    		return null;
		}
		UserProfile user = userRepository.findUserByEmail(userId);

		Goal deletedGoal = goalRepository.findGoalById(goalId);
		user.removeGoal(deletedGoal);
		userRepository.save(user);
		goalRepository.deleteById(goalId);
		return deletedGoal;
	}

	public List<Goal> getUserGoalsInRange(String userEmail, Date start,
		Date end)
	{
		List<Goal> goals = getUserGoals(userEmail);
		List<Goal> valid_goals = new ArrayList<Goal>();

		for(Goal goal : goals)
		{
			if((goal.getStartDate().after(start) && goal.getStartDate().before(end)) ||
				goal.getStartDate().equals(start) || goal.getStartDate().equals(end))
			{
				valid_goals.add(goal);
			}
		}

		return valid_goals;
	}

}