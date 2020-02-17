package com.example.fitboi.UnitTests;

import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

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
@PrepareForTest(UserAPI.class)
public class UserAPIUnitTest {
    private static UserDto user;
    private static List<UserDto> userList = new ArrayList<>();
    private static Consumer<List<UserDto>> userGetListFunction;
    private static Consumer<UserDto> userAddFunction;
    private static Consumer<UserDto> userGetFunction;
    private static boolean isAdded;

    @BeforeClass
    public static void Setup() {
        userGetListFunction = new Consumer<List<UserDto>>() {
            @Override
            public void accept(List<UserDto> userDtos) {
                userList.addAll(userDtos);
            }
        };

        userGetFunction = new Consumer<UserDto>() {
            @Override
            public void accept(UserDto userDto) {
                if(userDto.getEmail().equals(user)) {
                    user = userDto;
                }
            }
        };

        userAddFunction = new Consumer<UserDto>() {
            @Override
            public void accept(UserDto userDto) {
                if(userDto.getEmail().equals(userDto.getEmail())) {
                    isAdded = true;
                }
            }
        };
    }

    @Test
    public void addNewGoal() {
        user = new UserDto("test@gmail.com", "Test", "test123", "123", 21, 21, true);
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        UserAPI.addNewUser(user, userAddFunction);
    }

    @Test
    public void getUserByLoginInfo() {
        String username = "hello";
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        UserAPI.getUserByLoginInfo(userGetFunction, username);
    }

    @Test
    public void getAllUsers() {
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        UserAPI.getAllUsers(userGetListFunction);
    }
}

