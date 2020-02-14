package com.ecse428.project.fitboi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;


@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserService userService;

	@Test
	public void addGetDeleteUser(){

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

	// @Test
	// public void addGetWrongUserDeleteUser(){

	// 	String aEmail = "testUser2@mail.mcgill.ca";
	// 	String aName = "testboi";
	// 	String aUserName = "testBoi";
	// 	String aPassword = "password";
	// 	int aAge = 15;
	// 	int aHeight = 193;
	// 	boolean aBiologicalSex = true;

	// 	String wrongEmail = "testUser3@mail.mcgill.ca";

	// 	UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);
	// 	boolean addStatus = userService.addUser(testUser);
	// 	assertTrue(addStatus, "Add user failure");
		
	// 	UserProfile dbUser =  userService.getUser(wrongEmail);
	// 	assertTrue(!dbUser.getEmail().equals(aEmail), "Test found user it should not have");

	// 	userService.deleteUser(aEmail);
	// 	if(userService.getUser(aEmail) != null){
	// 		fail("Delete user failure");
	// 	}
	// }

}
