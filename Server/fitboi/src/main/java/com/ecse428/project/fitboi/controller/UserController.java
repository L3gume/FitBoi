package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.project.fitboi.dto.*;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	
	/* @TODO: Create a JDBC layer for this controller.
	 * For now, I'm going to use this Map to mock the database.
	 */
	private static Map<String, UserDto> mockUsers = new HashMap<String, UserDto> (Map.ofEntries(
			Map.entry("test1@gmail.com", new UserDto("test1@gmail.com", 25, true, 10, 10)),
			Map.entry("test2@gmail.com", new UserDto("test2@gmail.com", 20, true, 39, 49))
	));
	///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * GET
     * /users/ -> returns a list of all users
     */
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = new ArrayList<UserDto>();
        // TODO: Add call into JDBC to fetch all users //////
        users = new ArrayList<UserDto>(mockUsers.values());
        //////////////////////////////////////////////////////
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }
    
    /**
     * GET
     * /users/{user_id}/ -> returns a specific user given their userId (email for now)
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
    	UserDto user;
    	// TODO: Add call into JDBC to fetch a user //////
    	user = mockUsers.get(userId);
        //////////////////////////////////////////////////////
    	if (user == null) {
    		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
    
    /**
     * POST
     * /users/ -> adds a new user to the DB
     * @param userId
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
    	if (user == null) {
    		return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	UserDto newUser;
    	// TODO: Add call into JDBC to create a user ////////
    	if (mockUsers.containsKey(user.getEmail())) {
    		return new ResponseEntity<String>("User already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	 // Note: The actual operation may add extra data into the UserDto. To make this clear, I have the newUser object.
    	newUser = mockUsers.put(user.getEmail(), user);
        //////////////////////////////////////////////////////
    	return new ResponseEntity<UserDto>(newUser, HttpStatus.CREATED);
    }
    
    /**
     * DELETE
     * /users/{user_id}/ -> deletes a user from the DB
     * @param userId
     * @return
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
    	
    	UserDto deletedUser;

    	// TODO: Add call into JDBC to delete a user ////////
    	if (!mockUsers.containsKey(userId)) {
    		return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
    	}
    	deletedUser = mockUsers.remove(userId);
        //////////////////////////////////////////////////////	
    	return new ResponseEntity<UserDto>(deletedUser, HttpStatus.OK);
    }

    // TODO: Add PATCH call

}
