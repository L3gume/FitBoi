package com.example.fitboi.data;

import com.android.volley.Response;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.UserDto;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            final Result<LoggedInUser>[] res = new Result[]{new Result<>()};
            Consumer<UserDto> loginUser = new Consumer<UserDto>() {
                @Override
                public void accept(UserDto userDto) {
                    res[0] = new Result.Success<>(new LoggedInUser(userDto.getEmail(), username));
                }
            };
            UserAPI.loginUser(loginUser, username, password);
            return res[0];
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
