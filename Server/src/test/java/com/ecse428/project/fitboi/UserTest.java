package com.ecse428.project.fitboi;

import static org.junit.jupiter.api.Assertions.fail;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testPersonExists() {
        String email = "fitboi@mcgill.ca";
        
        UserProfile user = new UserProfile();
        user.setEmail(email);

        if(!service.addUser(user)) {
            fail("The user was not created");
        }
        
		if(!service.userExists(email)){
            fail("The user does not exist in the database");
        }

        if(!service.deleteUser(email).getEmail().equals(email)){
            fail("The user was not deleted succesfully");
        }
    }
    
    @Test
    public void testPersonDoesNotExist() {
        String email = "fitboi@mcgill.ca";
        String falseEmail = "fitboi1@mcgill.ca";
        
        UserProfile user = new UserProfile();
        user.setEmail(email);

        if(!service.addUser(user)) {
            fail("The user was not created");
        }
        
		if(service.userExists(falseEmail)){
            fail("The user exists in the database");
        }

        if(!service.deleteUser(email).getEmail().equals(email)){
            fail("The user was not deleted succesfully");
        }
    }

}