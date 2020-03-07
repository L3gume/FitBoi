package com.example.fitboi.UnitTests;

import com.example.fitboi.api.GoalAPI;
import com.example.fitboi.dto.GoalDto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GoalAPI.class)
public class GoalAPIUnitTest {
    private static GoalDto goal;
    private static boolean isAdded;
    private static boolean isDeleted;
    private static boolean isUpdated;

    @BeforeClass
    public static void Setup() {
        goal = new GoalDto(1234,0,false,"2020-02-02", "2020-10-01", 76.8, "high", "lose", 10,20,30);
    }

    @Test
    public void addNewGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalDto goalDto = GoalAPI.createGoal("test@gmail.com", goal, null);
        isAdded = (goalDto.getStartDate().equals(goal.getStartDate()) && goalDto.isResult());
    }

    @Test
    public void deleteGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalDto goalDto = GoalAPI.deleteGoal("test@gmail.com", null);
        isDeleted = (goalDto.getStartDate().equals(goal.getStartDate()));
    }

    @Test
    public void getUserGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        goal = GoalAPI.getUserGoal("test@gmail.com", null);
    }

    @Test
    public void updateGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        goal.setCarbs(30);
        GoalDto newGoal = GoalAPI.updateGoal("test@gmail.com", goal, null);
        isUpdated = (newGoal.getCarbs() == 30);
    }

}
