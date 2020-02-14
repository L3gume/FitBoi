package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.*;

@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserService userService;
	@Autowired 
	private UserRepository userRepository;

	@BeforeEach
	public void setup(){
		userRepository.deleteAll();
	}

	@AfterEach
	public void teardown(){
		userRepository.deleteAll();
	}

	@Test
	public void testAddExistGetDeleteUpdateUserSuccess(){
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;

		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);
		boolean addStatus = userService.addUser(testUser);
		assertTrue(addStatus, "Add user failure");
		
		boolean existStatus = userService.checkUser(aEmail);
		assertTrue(existStatus, "Check user failure");

		aAge = 20;
		testUser.setAge(aAge);
		boolean updateStatus = userService.updateUser(testUser);
		assertTrue(updateStatus, "Update user failure");

		UserProfile dbUser =  userService.getUser(aEmail);
		assertTrue(dbUser.getEmail().equals(aEmail), "Get user failure");
		assertTrue(dbUser.getName().equals(aName), "Get user failure");
		assertTrue(dbUser.getUserName().equals(aUserName), "Get user failure");
		assertTrue(dbUser.getPassword().equals(aPassword), "Get user failure");
		assertTrue(dbUser.getAge() == aAge, "Get user failure");
		assertTrue(dbUser.getHeight() == aHeight, "Get user failure");
		assertTrue(dbUser.getBiologicalSex() == aBiologicalSex, "Get user failure");

		userService.deleteUser(aEmail);
		if(userService.getUser(aEmail) != null){
			fail("Delete user failure");
		}
	}

	@Test
	public void testAddExistGetDeleteUpdateUserFailure(){
		String aEmail = "testUser2@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;

		String wrongEmail = "testUser3@mail.mcgill.ca";

		// non existing
		boolean existStatus = userService.checkUser(wrongEmail);
		assertFalse(existStatus, "Check user failure");

		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);
		userService.addUser(testUser);
		boolean addStatus = userService.addUser(testUser);

		// duplicate
		assertFalse(addStatus, "Add user failure");

		userService.deleteUser(aEmail);
		if(userService.getUser(aEmail) != null){
			fail("Delete user failure");
		}
	}

}
