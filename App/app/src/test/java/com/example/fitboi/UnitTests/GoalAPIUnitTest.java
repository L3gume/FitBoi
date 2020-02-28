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
    private static List<GoalDto> goalList = new ArrayList<>();
    private static Consumer<List<GoalDto>> goalGetListFunction;
    private static Consumer<GoalDto> goalAddFunction;
    private static Consumer<GoalDto> goalDeleteFunction;
    private static boolean isAdded;
    private static boolean isDeleted;
    private static boolean isUpdated;

    @BeforeClass
    public static void Setup() {
        goal = new GoalDto(2000, false, "02-02-2020", 76.8, "high", 10, 20, 30);
    }

    @Test
    public void addNewGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalDto goalDto = GoalAPI.addUserGoal("test@gmail.com", goal, null);
        isAdded = (goalDto.getStartDate().equals(goal.getStartDate()) && goalDto.getResult());
    }

    @Test
    public void deleteGoal() {
        String goalID = "12345";
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalDto goalDto = GoalAPI.deleteUserGoal(goalID, null);
        isDeleted = (goalDto.getStartDate().equals(goal.getStartDate()));
    }

    @Test
    public void getListOfUserGoals() {
        String goalID = "12345";
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        goalList = GoalAPI.getUserGoals(goalID, null);
    }

    @Test
    public void updateGoal() {
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        goal.setCarbs(30);
        GoalDto newGoal = GoalAPI.updateUserGoal("test@gmail.com", goal.getId(), goal, null);
        isUpdated = (newGoal.getCarbs() == 30);
    }

}
