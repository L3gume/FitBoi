package com.ecse428.project.fitboi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.ecse428.project.repository")
public class FitboiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FitboiApplication.class, args);
	}
	
}
