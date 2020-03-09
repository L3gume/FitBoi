package com.ecse428.project.fitboi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

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

	@Transactional
	public boolean setUserGoal(Goal goal, UserProfile user) {
		
		user.setGoal(goal);
		userService.updateUser(user);
		return true;
	}
	
	@Transactional
	public Goal getUserGoal(String userEmail)
	{
		UserProfile user = userService.getUser(userEmail);
		return user.getGoal();
	}


	/**
	 * Update a goal from the database with new goal
	 * @param userEmail 
	 * @param goal
	 * @return boolean dependant on if goal exists or has been successfully updated
	 */
	@Transactional
	public boolean updateGoal(String userEmail, Goal goal){
		UserProfile user = userService.getUser(userEmail);
		if(user.getGoal().getId() != goal.getId()){
			return false;
		}else{
			user.setGoal(goal);
			userService.updateUser(user);
			return true;
		}
	}

	/**
	 * Deletes a goal from the database 
	 * @param userId
	 * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	 */
	public Goal deleteGoal(String userId) {
    	
		UserProfile user = userRepository.findUserByEmail(userId);
		Goal deletedGoal = user.getGoal();
		user.removeGoal();
		userRepository.save(user);
		return deletedGoal;
	}

}
