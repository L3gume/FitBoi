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
    private static boolean isLoggedIn;
    private static boolean isEdited;

    @BeforeClass
    public static void Setup() {
    }

    @Test
    public void addNewUser() {
        user = new UserDto("test@gmail.com", "Test", "test123", "123", "1998-02-23", "Male", 180);
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        UserDto userDto = UserAPI.addUser(user, null);
        isAdded = (userDto != null);
    }

    @Test
    public void getUserByLoginInfo() {
        String username = "hello";
        String password = "password";
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        UserDto userDto = UserAPI.getUserByLogin(username, password, null);
        isLoggedIn = (userDto.getEmail().equals(user));
    }

    @Test
    public void getAllUsers() {
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        userList = UserAPI.getUsers(null);
    }

    @Test
    public void editUser() {
        PowerMockito.mockStatic(UserAPI.class);
        PowerMockito.doNothing().when(UserAPI.class);
        user.setHeight(22);
        UserDto newUser = UserAPI.updateUser(user, null);
        isEdited = (newUser.getHeight() == 22);
    }
}

