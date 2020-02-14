package com.example.fitboi.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.function.Consumer;

import static java.lang.Boolean.*;

public class UserAPI {

    static final String ip_localhost = "127.0.0.1";
    static final String ip_dev_machine = "10.0.2.2";
    static final boolean usingEmulator = TRUE;
    static final String userUrl = "http://"+(usingEmulator ? ip_dev_machine : ip_localhost)+"/users/";

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
                userUrl,
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
                userUrl,
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
     *       ListObject.add(user.age);
     *   }
     * };
     * UserAPI.getUserByLoginInfo(addUserEmailToList, emailText);
     * @param fn to be called by response
     */
    public static void getUserByLoginInfo(Consumer<UserDto> fn, String email) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                userUrl+email+"/",
                null,
                userCallSuccessListener(fn),
                userCallErrorListener(fn)
        );
        request.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        queue.add(request);
    }
    
    /**
     * @param fn to be called by the response
     * @param username user's username
     * @param password user's password
     */
    public static void loginUser(Consumer<UserDto> fn, String username, String password) {
        JsonObjectRequest req = new JsonObjectRequest(
                userUrl+username+"/"+password+"/",
                null,
                userCallSuccessListener(fn),
                userCallErrorListener(fn)
        );
        req.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        MyVolley.getRequestQueue().add(req);
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
            }
        };
    }

    // JSON - DTO converters

    private static UserDto jsonToUserDto(JSONObject json) {
        String email = json.optString("email");
        int age = json.optInt("age");
        boolean sex = json.optBoolean("sex");
        int weight = json.optInt("weight");
        int height = json.optInt("height");

        return new UserDto(email, age, sex, weight, height);
    }

    private static JSONObject userDtoToJson(UserDto user) {
        JSONObject json = new JSONObject();

        try {
            json.put("email", user.getEmail());
            json.put("age", user.getAge());
            json.put("sex", user.getSex());
            json.put("weight", user.getWeight());
            json.put("height", user.getHeight());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }

}
