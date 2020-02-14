package com.ecse428.project.fitboi.controller;

import javax.servlet.http.HttpUtils;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("{userId}")
    @GetMapping("{password}")
    public ResponseEntity<?> loginUser(@PathVariable String userId, @PathVariable String password) {
        UserProfile user = userService.getUser(userId);
        if (user == null) {
    		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        if (user.getPassword() != password) {
            return new ResponseEntity<String>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
        }
        
        return new ResponseEntity<UserDto>(convertToDto(user), HttpUtils.OK);
    }
    
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
