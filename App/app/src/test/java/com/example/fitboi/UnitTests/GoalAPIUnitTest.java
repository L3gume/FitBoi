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

    @BeforeClass
    public static void Setup() {
        goalGetListFunction = new Consumer<List<GoalDto>>() {
            @Override
            public void accept(List<GoalDto> goalDtos) {
                goalList.addAll(goalDtos);
            }
        };

        goalAddFunction = new Consumer<GoalDto>() {
            @Override
            public void accept(GoalDto goalDto) {
                if(goalDto.getStartDate().equals(goal.getStartDate()) && goalDto.getResult()) {
                    isAdded = true;
                }
            }
        };

        goalDeleteFunction = new Consumer<GoalDto>() {
            @Override
            public void accept(GoalDto goalDto) {
                if(goalDto.getStartDate().equals(goal.getStartDate())) {
                    isAdded = false;
                }
            }
        };
    }

    @Test
    public void addNewGoal() {
        goal = new GoalDto(2000, false, "02-02-2020", 76.8, "high", 10, 20, 30);
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalAPI.addNewGoal("test@gmail.com", goal, goalAddFunction);
    }

    @Test
    public void deleteGoal() {
        String goalID = "12345";
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalAPI.deleteGoal(goalID, goalDeleteFunction);
    }

    @Test
    public void getListOfUserGoals() {
        String goalID = "12345";
        PowerMockito.mockStatic(GoalAPI.class);
        PowerMockito.doNothing().when(GoalAPI.class);
        GoalAPI.getUserGoals(goalID, goalGetListFunction);
    }


}
