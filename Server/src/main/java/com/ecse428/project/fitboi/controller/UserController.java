package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.project.fitboi.dto.*;
import com.ecse428.project.fitboi.model.UserProfile;
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
        List<UserDto> users = new ArrayList<UserDto>();
        for (UserProfile user : userService.getAllUsers()) {
        	users.add(convertToDto(user));
        }
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }
    
    /**
     * GET
     * /users/{user_id}/ -> returns a specific user given their userEmail (email for now)
     * @param userEmail
     * @return
     */
    @GetMapping("{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable String userEmail) {
    	UserProfile user = userService.getUser(userEmail);
    	if (user == null) {
    		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(convertToDto(user), HttpStatus.OK);
    }
    
    /**
     * POST
     * /users/ -> adds a new user to the DB
     * @param userEmail
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
    	if (user == null) {
    		return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	System.out.println("USER: " + user.toString());
    	
    	if (!userService.addUser(convertToDomainObject(user)))
    	{
    		return new ResponseEntity<String>("User already exists", HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
    }
    

    /**
     * UPDATE
     * /users/ -> update user in DB
     * @param userEmail
     * @return
     */
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        if (user == null) {
            return new ResponseEntity<String>("Request body invalid", HttpStatus.NOT_ACCEPTABLE);
        }
        
        System.out.println("USER: " + user.toString());
        
        if (!userService.updateUser(convertToDomainObject(user)))
        {
            return new ResponseEntity<String>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }


    /**
     * DELETE
     * /users/{user_id}/ -> deletes a user from the DB
     * @param userEmail
     * @return
     */
    @DeleteMapping("{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable String userEmail) {
    	
    	UserProfile deletedUser = userService.deleteUser(userEmail);

    	if (deletedUser == null) {
    		return new ResponseEntity<String>("User does not exist", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<UserDto>(convertToDto(deletedUser), HttpStatus.OK);
    }

   // TODO: Add PATCH call
    
    private UserDto convertToDto(UserProfile user) {
    	return new UserDto(
    			user.getEmail(),
    			user.getName(),
    			user.getUserName(),
    			user.getPassword(),
    			user.getAge(),
    			user.getHeight(),
    			user.getBiologicalSex()
    			);
    }
    
	private UserProfile convertToDomainObject(UserDto userDto) {
		
		UserProfile profile = new UserProfile(
				userDto.getEmail(),
				userDto.getName(),
				userDto.getUserName(),
				userDto.getPassword(),
				userDto.getAge(),
				userDto.getHeight(),
				userDto.getSex()
				);
		return profile;
    }

}
