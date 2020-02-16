package com.ecse428.project.fitboi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ecse428.project.fitboi.service.UserService;

@SpringBootApplication
@EnableJpaRepositories("com.ecse428.project.repository")
public class FitboiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FitboiApplication.class, args);
	}
	
}
