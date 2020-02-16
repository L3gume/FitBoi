package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.MacroDistribution;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.GoalService;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.*;

import static org.mockito.Mockito.*;
import java.sql.Date;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GoalServiceTests {

  @Autowired
  private GoalService goalService;
  
	@MockBean
  private GoalRepository goalRepository;
  
  @MockBean
  private UserRepository userRepository;

  @MockBean
  private UserService userService;

  @Test
  public void testAddGoalToUserSuccess(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    when(goalRepository.existsById(testGoal.getId())).thenReturn(false);
    when(goalRepository.save(any())).thenReturn(testGoal);
    when(userService.updateUser(any())).thenReturn(true);
    assertTrue(goalService.addGoaltoUser(testGoal, testUser));
  }
  @Test
  public void testAddGoalToUserFail(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    when(goalRepository.existsById(testGoal.getId())).thenReturn(false);
    when(goalRepository.save(any())).thenReturn(testGoal);
    when(userService.updateUser(any())).thenReturn(true);
    assertTrue(goalService.addGoaltoUser(testGoal, testUser));
  }

  @Test
  public void testGetUserGoalsSuccess(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    testUser.addGoal(testGoal);
    when(userService.getUser(testUser.getEmail())).thenReturn(testUser);
    assertTrue(!goalService.getUserGoals(testUser.getEmail()).isEmpty());
  }

  @Test
  public void testGetUserGoalsInRangeSuccess(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    testUser.addGoal(testGoal);
    when(userService.getUser(anyString())).thenReturn(testUser);
    assertTrue(!goalService.getUserGoalsInRange(testUser.getEmail(), testGoal.getStartDate(), testGoal.getStartDate()).isEmpty());
  }

  @Test
  public void testDeleteUserGoal(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    testUser.addGoal(testGoal);
    when(goalRepository.existsById(anyInt())).thenReturn(true);
    when(userRepository.findUserByEmail(anyString())).thenReturn(testUser);
    when(goalRepository.findGoalById(anyInt())).thenReturn(testGoal);
    assertTrue(goalService.deleteGoal(testUser.getEmail(), 50) != null);
  }

  @Test
  public void testGetUserGoalsInRangeFail(){
    UserProfile testUser = createUser();
    Goal testGoal = createGoal();
    testUser.addGoal(testGoal);
    when(userService.getUser(anyString())).thenReturn(testUser);
    Date startDate = Date.valueOf("2020-01-15");
    assertTrue(goalService.getUserGoalsInRange(testUser.getEmail(), startDate, startDate).isEmpty());
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

  private Goal createGoal(){
    int calories = 2500;
    boolean result = false;
    Date startDate = Date.valueOf("2020-02-15");
    float weight = 75;
    return new Goal(calories, result, startDate, weight, ActivityLevel.Medium, new MacroDistribution(100, 150, 200));
  }


}