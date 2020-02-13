package com.ecse428.project.fitboi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecse428.project.fitboi.dto.UserDto;

/**
 * Provides an API to manipulate user information in the database.
 * TODO: We should probably throw exceptions for each of these methods instead of using booleans.
 * 		 Currently, booleans are used to signal either "OK" or "something went wrong".
 * 		 If multiple things can go wrong, "something went wrong" isn't descriptive.
 */
public class UserService {
	
	
	// /* @TODO: Create a JDBC layer for this controller.
	//  * For now, I'm going to use this Map to mock the database.
	//  */
	// private static Map<String, UserDto> mockUsers = new HashMap<String, UserDto> (Map.ofEntries(
	// 		Map.entry("test1@gmail.com", new UserDto("test1@gmail.com", 25, true, 10, 10)),
	// 		Map.entry("test2@gmail.com", new UserDto("test2@gmail.com", 20, true, 39, 49))
	// ));
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets all users from the database
	 * @return List of all users
	 */
	public List<UserDto> getAllUsers() {
		List<UserDto> users;
		// TODO: Add call into JDBC to fetch all users //////
        users = new ArrayList<UserDto>(mockUsers.values());
        //////////////////////////////////////////////////////
		return users;
	}
	
	/**
	 * Gets a specific users from the database
	 * @return If the userId exists in the database, the a user dto is returned. Else, null is returned.
	 */
	public UserDto getUser(String userId) {
		UserDto user;
    	// TODO: Add call into JDBC to fetch a user //////
    	user = mockUsers.get(userId);
        //////////////////////////////////////////////////////
		return user;
	}
	
	
	/**
	 * Adds a new user to the database
	 * @param user
	 * @return True if the user has been inserted, False otherwise
	 */
	public boolean addUser(UserDto user) {
		// TODO: Add call into JDBC to create a user ////////
    	if (mockUsers.containsKey(user.getEmail())) {
    		return false;
    	}
    	 // Note: The actual operation may add extra data into the UserDto. To make this clear, I have the newUser object.
    	mockUsers.put(user.getEmail(), user);
        //////////////////////////////////////////////////////
		return true;
	}
	
	// /**
	//  * Deletes a user from the database 
	//  * @param userId
	//  * @return The deleted user dto if the deletion was successful. null if the user could not be removed / did not exist in the db.
	//  */
	// public UserDto deleteUser(String userId) {
	// 	UserDto deletedUser;
	// 	// TODO: Add call into JDBC to delete a user ////////
 //    	if (!mockUsers.containsKey(userId)) {
 //    		return null;
 //    	}
 //    	deletedUser = mockUsers.remove(userId);
 //        //////////////////////////////////////////////////////	
	// 	return deletedUser;
	// }
	

}
