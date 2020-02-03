package com.ecse428.project.fitboi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecse428.project.fitboi.service.UserService;

@SpringBootApplication
public class FitboiApplication {

	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(FitboiApplication.class, args);
	}
	
	
	@Bean
	public UserService userService() {
		userService = new UserService();
		return userService;
	}
}
