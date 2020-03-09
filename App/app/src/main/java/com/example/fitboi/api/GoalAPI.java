package com.example.fitboi.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.GoalDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GoalAPI {

    /********** PUBLIC METHODS **********/

    /**
     * Add new goal to given user
     * path:    /users/{userEmail}/goal
     * type:    POST
     *
     * Example of how to use asynchronously:
     * Consumer addGoalConfirmation = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto goal) {
     *     // do something
     *   }
     * };
     * GoalAPI.createGoal(goalDto, addGoalConfirmation);
     *
     * Example of how to use synchronously:
     * GoalDto newGoal = GoalDto();
     * GoalDto addedGoal = GoalAPI.createGoal("test@gmail.com", newGoal, null);
     *
     * @param userEmail of user to which to add the goal
     * @param goalToAdd GoalDto of goal to add
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto createGoal(String userEmail, GoalDto goalToAdd, Consumer<GoalDto> fn) {
        if (userEmail == null || goalToAdd == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = goalCallSuccessListener(fn);
            errorListener = goalCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userEmail
                        +MyVolley.goalPostfix,
                goalDtoToJson(goalToAdd),
                successListener,
                errorListener
        );
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToGoalDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Delete a goal from a given user
     * path:    /users/{userEmail}/goal
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * Consumer deleteGoalConfirmation = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto user) {
     *     // do something
     *   }
     * };
     * GoalAPI.deleteGoal("test@gmail.com", deleteGoalConfirmation);
     *
     * Example of how to use synchronously:
     * GoalDto deletedGoal = GoalAPI.deleteGoal("test@gmail.com", null);
     *
     * @param userEmail: email of user for which the goal should be deleted
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto deleteGoal(String userEmail, Consumer<GoalDto> fn) {
        if (userEmail == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = goalCallSuccessListener(fn);
            errorListener = goalCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                MyVolley.serverUrl+
                        MyVolley.userPostfix+userEmail
                        +MyVolley.goalPostfix,
                null,
                successListener,
                errorListener);

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToGoalDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get a goal of given user
     * path:    /users/{userEmail}/goal
     * type:    GET
     *
     * Example of how to use asynchronously:
     * Consumer addAllGoalsToList = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto goal) {
     *     // do something
     *   }
     * };
     * GoalAPI.getUserGoal(userEmail, addAllGoalsToList);
     *
     * Example of how to use synchronously:
     * GoalDto goal = GoalAPI.getUserGoal(userEmail, null);
     *
     * @param userEmail: email of user to get the goals of
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto getUserGoal(String userEmail, Consumer<GoalDto> fn) {
        if (userEmail == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = goalCallSuccessListener(fn);
            errorListener = goalCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userEmail
                        +MyVolley.goalPostfix,
                null,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToGoalDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;

    }

    /**
     * Update a given user's goal
     * path:    /users/{userEmail}/goal
     * type:    PUT
     *
     * Example of how to use asynchronously:
     * GoalDto goalToUpdate;
     * Consumer updateGoal = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto updatedGoal) {
     *     // do something
     *   }
     * };
     * GoalAPI.updateGoal(userEmail, goalToUpdate, updateGoal);
     *
     * Example of how to use synchronously:
     * GoalDto goalToUpdate;
     * GoalDto goal = GoalAPI.updateGoal(userEmail, goalToUpdate, null);
     *
     * @param userEmail : email of user to update goal of
     * @param newGoalDto : new information of goal
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto updateGoal(String userEmail, GoalDto newGoalDto, Consumer<GoalDto> fn) {
        if (userEmail == null || newGoalDto == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = goalCallSuccessListener(fn);
            errorListener = goalCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userEmail
                        +MyVolley.goalPostfix,
                goalDtoToJson(newGoalDto),
                successListener,
                errorListener);

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToGoalDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /********** PRIVATE METHODS **********/

    // Success Listeners

    private static Response.Listener<JSONArray> goalListCallSuccessListener(final Consumer<List<GoalDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<GoalDto> goalDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    goalDtoList.add(jsonToGoalDto(response.optJSONObject(i)));
                }
                fn.accept(goalDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> goalCallSuccessListener(final Consumer<GoalDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToGoalDto(response));
            }
        };
    }

    // Error Listeners

    private static Response.ErrorListener goalListCallErrorListener(final Consumer<List<GoalDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener goalCallErrorListener(final Consumer<GoalDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    // JSON - DTO converters
    private static GoalDto jsonToGoalDto(JSONObject json) {
        int id = json.optInt("id");
        int baseCalories = json.optInt("baseCalories");
        boolean result = json.optBoolean("result");
        String startDate = json.optString("startDate");
        String endDate = json.optString("endDate");
        double weight = json.optDouble("weight");
        String activityLevel = json.optString("activityLevel");
        String goalType = json.optString("goalType");
        double fats = json.optDouble("fats");
        double carbs = json.optDouble("carbs");
        double proteins = json.optDouble("proteins");

        return new GoalDto(id, baseCalories, result, startDate, endDate, weight,
                activityLevel, goalType, fats, carbs, proteins);
    }

    private static JSONObject goalDtoToJson(GoalDto goal) {
        JSONObject json = new JSONObject();

        try {
            // json.put("id", goal.getId()); id is autogenerated
            json.put("baseCalories", goal.getBaseCalories());
            json.put("result", goal.isResult());
            json.put("startDate", goal.getStartDate());
            json.put("endDate", goal.getEndDate());
            json.put("weight", goal.getWeightGoal());
            json.put("activityLevel", goal.getActivityLevel());
            json.put("goalType", goal.getGoalType());
            json.put("fats", goal.getFats());
            json.put("carbs", goal.getCarbs());
            json.put("proteins", goal.getProtein());
        } catch (Exception e) {
            // TODO: something with exception
        }
        return json;
    }
}
