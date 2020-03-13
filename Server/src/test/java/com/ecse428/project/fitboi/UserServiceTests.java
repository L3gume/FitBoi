package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.fitboi.model.Sex;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
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
		boolean addStatus = userService.addNewUser(testUser);
		assertTrue(addStatus);
	}

	@Test
	public void testAddUserFailure(){
		UserProfile testUser = createUser();
		when(mockRepository.existsById(testUser.getEmail())).thenReturn(true);
		boolean addStatus = userService.addNewUser(testUser);
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

	@Test
	public void addMetricsSuccess(){
		UserProfile testUser = createUser();
		Metric metric = new Metric(new Date(0), 3);
		testUser.addMetric(metric);
		when(userService.getUser(anyString())).thenReturn(testUser);
		Metric result = userService.getUserMetric(testUser.getEmail(), metric.getId());
		assertTrue(result.getDate().equals(metric.getDate()));
	}

	private UserProfile createUser(){
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		Date aDOB = Date.valueOf("2010-11-11");
		int aHeight = 193;
		Sex aBiologicalSex = Sex.Male;
		return new UserProfile(aEmail, aName, aUserName, aPassword, aDOB, aHeight, aBiologicalSex);
	}
}
