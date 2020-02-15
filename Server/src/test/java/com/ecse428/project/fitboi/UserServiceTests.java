package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.ArrayList;

@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserService userService;

	@MockBean
    private UserRepository mockRepository;

	@Test
	public void testAddUserSuccess(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(false);
		when(mockRepository.save(testUser)).thenReturn(testUser);
		boolean addStatus = userService.addUser(testUser);
		assertTrue(addStatus);
	}

	@Test
	public void testAddUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(true);
		boolean addStatus = userService.addUser(testUser);
		assertTrue(!addStatus);
	}

	@Test
	public void testGetUserSuccess(){
		UserProfile testUser = createUser();
		when(mockRepository.findUserByEmail(testUser.getEmail())).thenReturn(testUser);
		UserProfile found = userService.getUser(testUser.getEmail());
		assertTrue(found.getEmail().equals(testUser.getEmail()));
	}
	
	@Test
	public void testGetUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.findUserByEmail(testUser.getEmail())).thenReturn(null);
		UserProfile found = userService.getUser(testUser.getEmail());
		assertTrue(found == null);
	}

	@Test
	public void testGetAllUserSuccess(){
		List<UserProfile> users = new ArrayList<>();
		UserProfile testUser = createUser();
		users.add(testUser);
		when(mockRepository.findAll()).thenReturn(users);
		List<UserProfile> results = new ArrayList<>();
		for(UserProfile user : userService.getAllUsers()){
			results.add(user);
		}
		assertTrue(!results.isEmpty());
	}

	@Test
	public void tesUpdateUserSuccess(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(true);
		when(mockRepository.save(testUser)).thenReturn(testUser);
		boolean addStatus = userService.updateUser(testUser);
		assertTrue(addStatus);
	}

	@Test
	public void testUpdateUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(false);
		boolean addStatus = userService.updateUser(testUser);
		assertTrue(!addStatus);
	}

	@Test
	public void testCheckUserSuccess(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(true);
		boolean found = userService.checkUser(testUser.getEmail());
		assertTrue(found == true);
	}
	
	@Test
	public void testCheckUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(false);
		boolean found = userService.checkUser(testUser.getEmail());
		assertTrue(found == false);
	}

	@Test
	public void testDeleteUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(false);
		UserProfile deleted = userService.deleteUser(testUser.getEmail());
		assertTrue(deleted == null);
	}
	
	@Test
	public void testDeleteUserSuccess(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(true);
		when(mockRepository.findUserByEmail(testUser.getEmail())).thenReturn(testUser);

		UserProfile deleted = userService.deleteUser(testUser.getEmail());
		assertTrue(deleted.getEmail().equals(testUser.getEmail()));
	}

	private UserProfile createUser(){
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		return new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);
	}
}
