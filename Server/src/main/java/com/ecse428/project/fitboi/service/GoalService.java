package com.ecse428.project.fitboi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.GoalRepository;

/**
 * Provides an API to manipulate user information in the database.
 * TODO: We should probably throw exceptions for each of these methods instead of using booleans.
 * 		 Currently, booleans are used to signal either "OK" or "something went wrong".
 * 		 If multiple things can go wrong, "something went wrong" isn't descriptive.
 */
@Service
public class GoalService {
	
	
	@Autowired
	GoalRepository repository;
	
	
	
	/**
	 * Adds a new user to the database
	 * @param goal
	 * @return True if the user has been inserted, False otherwise
	 */
	public boolean addGoaltoUser(Goal goal, UserProfile user) {
		if (repository.existsById(goal.getId())) {
			return false;
		}
		
		repository.save(user);
		return true;
	}
	
	

}