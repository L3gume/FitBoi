package com.ecse428.project.fitboi.service;

import java.util.List;

import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Iterable<UserProfile> getAllUsers() {
		repository.findAll();
		return repository.findAll();
	}
	
	/**
	 * Gets a specific users from the database
	 * @return If the userEmail exists in the database, the a user dto is returned. Else, null is returned.
	 */
	@Transactional
	public UserProfile getUser(String userEmail) {
		return repository.findUserByEmail(userEmail);
	}

	/**
	 * Checks whether or not a user exists
	 * @param userId: the user's email address
	 * @return true if the user exists in the database, false otherwise
	 */
	public boolean userExists(String userId) {
		return getUser(userId) != null;
	}

	/**
	 * Adds a new user to the database
	 * @param user
	 * @return True if the user has been inserted, False otherwise
	 */

	@Transactional
	public boolean addNewUser(UserProfile user) {
		if (repository.existsById(user.getEmail())) {
			return false;
		}
		
		repository.save(user);
		return true;
	}

	/**
	 * Update a user in the database
	 * @param user
	 * @return True if the user has update
	 */
	@Transactional
	public boolean updateUser(UserProfile user){
		if (repository.existsById(user.getEmail())) {
			repository.save(user);
			return true;
		}
		return false;
	}


	/**
	 * Adds a new user to the database
	 * @param userEmail
	 * @return True if the user exists, false if they dont
	 */
	@Transactional
	public boolean checkUser(String userEmail){
		return repository.existsById(userEmail);
	}

	/**
	 * Deletes a user from the database 
	 * @param userEmail
	 * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	 */
	@Transactional
	public UserProfile deleteUser(String userEmail) {
    	if (!repository.existsById(userEmail)) {
    		return null;
    	}
    	UserProfile deletedUser = repository.findUserByEmail(userEmail);
    	repository.deleteById(userEmail);
		return deletedUser;
	}

	public Goal getUserGoal(String userEmail)
	{
		UserProfile user = getUser(userEmail);
		return user.getGoal();
	}

	public Metrics getUserMetric(String userEmail, int metric_id) {
		UserProfile user = getUser(userEmail);
		for(Metrics metrics : user.getMetrics()) {
			if(metrics.getId() == metric_id) {
				return metrics;
			}
		}
		return null;
	}

	public List<Metrics> getAllUserMetrics(String userEmail) {
		UserProfile user = getUser(userEmail);

		return user == null ? null : user.getMetrics();
	}

	public Metrics deleteMetrics(String userEmail, int metric_id) {
		UserProfile user = getUser(userEmail);
		for(Metrics metric : user.getMetrics()) {
			if(metric.getId() == metric_id) {
				if (user.removeMetric(metric)) {
					repository.save(user);
					return metric;
				}
			}
		}

		return null;
	}
}
