package com.example.fitboi.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class UserAPI {

    /********** PUBLIC METHODS **********/

    /**
     * Add new user to backend
     * path:    /users/
     * type:    POST
     *
     * Example of how to use asynchronously:
     * Consumer addUserConfirmation = new Consumer<UserDto>() {
     *   @Override
     *   public void accept(UserDto user) {
     *     if (user.email == userDto.email) {
     *         String message = "User Added!";
     *     }
     *   }
     * };
     * UserAPI.addNewUser(userDto, addUserConfirmation);
     *
     * Example of how to use synchronously:
     * UserDto userToAdd = new UserDto()
     * UserDto userAdded = UserAPI.addNewUser(userToAdd, null);
     *
     * @param userToAdd UserDto of user to be added
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static UserDto addUser(UserDto userToAdd, Consumer<UserDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = userCallSuccessListener(fn);
            errorListener = userCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl+MyVolley.userPostfix,
                userDtoToJson(userToAdd),
                successListener,
                errorListener
        );
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToUserDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get list of all users in system
     * path:    /users/
     * type:    GET
     *
     * Example of how to use asynchronously:
     * Consumer addAllUsersToList = new Consumer<List<UserDto>>() {
     *   @Override
     *   public void accept(List<UserDto> users) {
     *     ListObject.add(users);
     *   }
     * };
     * UserAPI.getAllUsers(addAllUsersToList);
     *
     * Example of how to use synchronously:
     * List<UserDto> users = UserAPI.getAllUsers(null);
     *
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static List<UserDto> getUsers(Consumer<List<UserDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = userListCallSuccessListener(fn);
            errorListener = userListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+MyVolley.userPostfix,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<UserDto> users = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    users.add(jsonToUserDto(result.optJSONObject(i)));
                }

                return users;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get user object from email and password
     * path:    /users/{userEmail}/{userPassword}/
     * type:    GET
     *
     * Example of how to use asynchronously:
     * Consumer loginUser = new Consumer<UserDto>() {
     *   @Override
     *   public void accept(UserDto user) {
     *       String message = "user logged in";
     *   }
     * };
     * UserAPI.getUserByLoginInfo("test@gmail.com", "123", loginUser);
     *
     * Example of how to use synchronously:
     * UserDto user = UserAPI.getUserByLoginInfo("test@gmail.com", "123", null);
     *
     * @param email String: email of user to be logged in
     * @param password: String: password of user to be logged in
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static UserDto getUserByLogin(String email, String password, Consumer<UserDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = userCallSuccessListener(fn);
            errorListener = userCallErrorListener(fn);
        }


        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl + MyVolley.userPostfix + email + "/" + password + "/",
                null,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToUserDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Update user object
     * path:    /users/{userEmail}/{userPassword}/
     * type:    GET
     *
     * Example of how to use asynchronously:
     * UserDto userWithChange;
     * Consumer modifyUserSettings = new Consumer<UserDto>() {
     *   @Override
     *   public void accept(UserDto user) {
     *       String message = "user settings modified";
     *   }
     * };
     * UserAPI.updateUser("test@gmail.com", userWithChange, modifyUserSettings);
     *
     * Example of how to use synchronously:
     * UserDto userWithChange;
     * UserDto user = UserAPI.updateUser("test@gmail.com", userWithChange, null);
     *
     * @param email String: email of user to be logged in
     * @param newUserDto new information with which to update user
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static UserDto updateUser(String email, UserDto newUserDto, Consumer<UserDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = userCallSuccessListener(fn);
            errorListener = userCallErrorListener(fn);
        }


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                MyVolley.serverUrl+MyVolley.userPostfix,
                userDtoToJson(newUserDto),
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToUserDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /********** PRIVATE METHODS **********/

    // Success Listeners

    private static Response.Listener<JSONArray> userListCallSuccessListener(final Consumer<List<UserDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<UserDto> userDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    userDtoList.add(jsonToUserDto(response.optJSONObject(i)));
                }
                fn.accept(userDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> userCallSuccessListener(final Consumer<UserDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToUserDto(response));
            }
        };
    }

    // Error Listeners

    private static Response.ErrorListener userListCallErrorListener(final Consumer<List<UserDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener userCallErrorListener(final Consumer<UserDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
                fn.accept(null);
            }
        };
    }

    // JSON - DTO converters

    private static UserDto jsonToUserDto(JSONObject json) {
        String email = json.optString("email");
        String name = json.optString("name");
        String username = json.optString("username");
        String password = json.optString("password");
        int age = json.optInt("age");
        boolean sex = json.optBoolean("sex");
        int height = json.optInt("height");

        return new UserDto(email, name, username, password, age, height, sex);
    }

    private static JSONObject userDtoToJson(UserDto user) {
        JSONObject json = new JSONObject();

        try {
            json.put("email", user.getEmail());
            json.put("name", user.getName());
            json.put("username", user.getUserName());
            json.put("password", user.getPassword());
            json.put("age", user.getAge());
            json.put("sex", user.getSex());
            json.put("height", user.getHeight());
            json.put("password", user.getPassword());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }

}
