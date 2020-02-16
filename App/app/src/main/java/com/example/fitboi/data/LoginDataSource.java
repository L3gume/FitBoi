package com.example.fitboi.data;

import com.android.volley.Response;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.UserDto;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, final String password) {

        try {
            // TODO: handle loggedInUser authentication
            final ArrayList<LoggedInUser> res = new ArrayList<>();
            Consumer<UserDto> loginUser = new Consumer<UserDto>() {
                @Override
                public void accept(UserDto userDto) {
                    res.add(userDto != null ? new LoggedInUser(userDto.getEmail(), username) : null);
                }
            };
            // Authentication happens on the server side
            UserDto ret = UserAPI.loginUser(username, password);
            //while (res.size() < 1);
            if (ret != null)
                return new Result.Success<>(ret);
            else
                return new Result.Error(new IOException("Error logging in"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
