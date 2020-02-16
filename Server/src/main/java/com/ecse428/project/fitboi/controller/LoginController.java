package com.ecse428.project.fitboi.controller;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecse428.project.fitboi.dto.*;

@RestController
@RequestMapping("/users/")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    /*
    @RequestMapping("/")
    public String mainPage(){
        return "Index page, use Android App instead";
    }
    */
    
    @GetMapping("{userId}/{password}")
    //@GetMapping("{password}")
    public ResponseEntity<?> loginUser(@PathVariable String userId, @PathVariable String password) {
        UserProfile user = userService.getUser(userId);
        if (user == null) {
    		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
        }
        
        return new ResponseEntity<>(convertToDto(user), HttpStatus.OK);
    }
    
    private UserDto convertToDto(UserProfile user) {
    	return new UserDto(
    			user.getEmail(),
    			user.getName(),
    			user.getUserName(),
    			user.getPassword(),
    			user.getAge(),
    			user.getBiologicalSex(),
				user.getHeight()
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
