package com.ecse428.project.fitboi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.UserRepository;

/**
 * Provides an API to manipulate user information in the database.
 * TODO: We should probably throw exceptions for each of these methods instead of using booleans.
 * 		 Currently, booleans are used to signal either "OK" or "something went wrong".
 * 		 If multiple things can go wrong, "something went wrong" isn't descriptive.
 */
@Service
public class UserService {
	
	
	@Autowired
	UserRepository repository;
	
	
	/**
	 * Gets all users from the database
	 * @return List of all users
	 */
	public Iterable<UserProfile> getAllUsers() {
		repository.findAll();
		return repository.findAll();
	}
	
	/**
	 * Gets a specific users from the database
	 * @return If the userId exists in the database, the a user dto is returned. Else, null is returned.
	 */
	public UserProfile getUser(String userId) {
		return repository.findUserByEmail(userId);
	}
	
	
	/**
	 * Adds a new user to the database
	 * @param user
	 * @return True if the user has been inserted, False otherwise
	 */
	public boolean addNewUser(UserProfile user) {
		if (repository.existsById(user.getEmail())) {
			return false;
		}
		
		repository.save(user);
		return true;
	}

	/**
	 * Updates an existing user
	 * @param user
	 * @return True if the user has been inserted, False otherwise
	 */
	public boolean updateUser(UserProfile user) {
		if (!repository.existsById(user.getEmail())) {
			return false;
		}
		
		repository.save(user);
		return true;
	}
	
	/**
	 * Deletes a user from the database 
	 * @param userId
	 * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	 */
	public UserProfile deleteUser(String userId) {
    	if (!repository.existsById(userId)) {
    		return null;
    	}
    	UserProfile deletedUser = repository.findUserByEmail(userId);
    	repository.deleteById(userId);
		return deletedUser;
	}
	
	public List<Goal> getUserGoals(String userEmail)
	{
		UserProfile user = getUser(userEmail);
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
