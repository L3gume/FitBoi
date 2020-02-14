package com.ecse428.project.fitboi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	 * @return If the userEmail exists in the database, the a user dto is returned. Else, null is returned.
	 */
	public UserProfile getUser(String userEmail) {
		return repository.findUserByEmail(userEmail);
	}
	
	
	/**
	 * Adds a new user to the database
	 * @param user
	 * @return True if the user has been inserted, False otherwise
	 */
	public boolean addUser(UserProfile user) {
		if (repository.existsById(user.getEmail())) {
			return false;
		}
		
		repository.save(user);
		return true;
	}
	
	/**
	 * Deletes a user from the database 
	 * @param userEmail
	 * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	 */
	public UserProfile deleteUser(String userEmail) {
    	if (!repository.existsById(userEmail)) {
    		return null;
    	}
    	UserProfile deletedUser = repository.findUserByEmail(userEmail);
    	repository.deleteById(userEmail);
		return deletedUser;
	}
	

}
