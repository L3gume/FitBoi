package com.ecse428.project.fitboi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
	UserService userService;
	
	
	
	public boolean addGoaltoUser(Goal goal, UserProfile user) {
		user.addGoal(goal);
		userService.updateUser(user);
		return true;
	}
	
	public List<Goal> getUserGoals(String userEmail)
	{
		UserProfile user = userService.getUser(userEmail);
		return user.getGoals();
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