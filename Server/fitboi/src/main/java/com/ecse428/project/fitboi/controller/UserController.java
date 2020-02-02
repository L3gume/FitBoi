package com.ecse428.project.fitboi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.project.fitboi.dto.*;
import com.ecse428.project.fitboi.service.UserService;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    /**
     * GET
     * /users/ -> returns a list of all users
     */
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
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
    	user = userService.getUser(userId);
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
    	
    	if (!userService.addUser(user))
    	{
    		return new ResponseEntity<String>("User already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	
    	return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
    }
    
    /**
     * DELETE
     * /users/{user_id}/ -> deletes a user from the DB
     * @param userId
     * @return
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
    	
    	UserDto deletedUser = userService.deleteUser(userId);

    	if (deletedUser == null) {
    		return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(deletedUser, HttpStatus.OK);
    }

    // TODO: Add PATCH call

}
