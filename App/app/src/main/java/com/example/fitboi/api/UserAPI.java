package com.example.fitboi.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.data.Result;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.lang.Boolean.*;

public class UserAPI {

    // debug stuff
    static final String ip_localhost = "127.0.0.1:8080";
    static final String ip_dev_machine = "10.0.2.2:8080";
    static final boolean usingEmulator = true;
    static final String userUrl = "http://"+(usingEmulator ? ip_dev_machine : ip_localhost) + "/users/";

    /********** PUBLIC METHODS **********/

    /**
     * Example of how to use:
     * Consumer addUserConfirmation = new Consumer<UserDto> fn) {
     *   @Override
     *   public void accept(UserDto user) {
     *     if (user.email == userDto.email) {
     *         Message.setText("User Added!");
     *     }
     *   }
     * };
     * UserAPI.addNewUser(userDto, addUserConfirmation);
     * @param userToAdd
     * @param fn
     */
    public static void addNewUser(UserDto userToAdd, Consumer<UserDto> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl+MyVolley.userPostfix,
                userDtoToJson(userToAdd),
                userCallSuccessListener(fn),
                userCallErrorListener(fn)
        );
        queue.add(request);
    }

    /**
     * Example of how to use:
     * Consumer addAllUsersToList = new Consumer<List<UserDto>> fn) {
     *   @Override
     *   public void accept(List<UserDto> users) {
     *     ListObject.add(users);
     *   }
     * };
     * UserAPI.getAllUsers(addAllUsersToList);
     * @param fn to be called by response
     */
    public static void getAllUsers(Consumer<List<UserDto>> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+MyVolley.userPostfix,
                userListCallSuccessListener(fn),
                userListCallErrorListener(fn)
        );
        queue.add(request);
    }

    /**
     * Example of how to use:
     * Consumer addUserEmailToList = new Consumer<UserDto>() {
     *   @Override
     *   public void accept(UserDto user) {
     *       TextBox.add(user.age);
     *   }
     * };
     * UserAPI.getUserByLoginInfo(addUserEmailToList, emailText);
     * @param fn to be called by response
     */
    public static void getUserByLoginInfo(Consumer<UserDto> fn, String email) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl+MyVolley.userPostfix+email+"/",
                null,
                userCallSuccessListener(fn),
                userCallErrorListener(fn)
        );
        request.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        queue.add(request);
    }
    
    /**
     * Synchronous call to authenticate user
     * @param username user's username
     * @param password user's password
     * @return UserDto is successful, null if not
     */
    public static UserDto loginUser(String username, String password) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                userUrl+username+"/"+password+"/",
                null,
                future,
                future
        );
        req.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(req);
        try {
            return jsonToUserDto(future.get(10, TimeUnit.SECONDS));
        } catch (Exception e) {
            return null;
        }
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
