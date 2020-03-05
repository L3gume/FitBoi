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
     * path:    /users/{userEmail}/goals/
     * type:    POST
     *
     * Example of how to use asynchronously:
     * Consumer addGoalConfirmation = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto goal) {
     *     if (goal.goalId == goalDto.goalId) {
     *         Message.setText("Goal Added!");
     *     }
     *   }
     * };
     * GoalAPI.addNewGoal(goalDto, addGoalConfirmation);
     *
     * Example of how to use synchronously:
     * GoalDto newGoal = GoalDto();
     * GoalDto addedGoal = GoalAPI.addNewGoal("test@gmail.com", newGoal, null);
     * if (addedGoal == newGoal) {
     *     String message = "Adding new goal worked!";
     * }
     * @param userEmail of user to which to add the goal
     * @param goalToAdd GoalDto of goal to add
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto addUserGoal(String userEmail, GoalDto goalToAdd, Consumer<GoalDto> fn) {
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
                MyVolley.serverUrl+MyVolley.userPostfix+userEmail+MyVolley.goalPostfix,
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
     * path:    /users/{userEmail}/goals
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * Consumer deleteGoalConfirmation = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto user) {
     *     if (goal.goalId == goalDto.goalId) {
     *         String message = "Goal Deleted!";
     *     }
     *   }
     * };
     * GoalAPI.addNewGoal(goal.goalId, deleteGoalConfirmation);
     *
     * Example of how to use synchronously:
     * GoalDto deletedGoal = GoalAPI.deleteGoal(goal.goalId, null);
     * if (goal.goalId = deletedGoal.goalId) {
     *     String message = "Goal Deleted";
     * }
     * @param goalId of goal to be deleted
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto deleteUserGoal(String goalId, Consumer<GoalDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;

        if (fn == null) {
            successListener = future;
        } else {
            successListener = goalCallSuccessListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                (MyVolley.serverUrl+MyVolley.userPostfix+MyVolley.goalPostfix+goalId+'/'),
                null,
                successListener,
                null);

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
     * Get a list of all goal of a given user
     * path:    /users/{userEmail}/goals
     * type:    GET
     * Example of how to use asynchronously:
     * Consumer addAllGoalsToList = new Consumer<List<GoalDto>>() {
     *   @Override
     *   public void accept(List<GoalDto> goals) {
     *     ListObject.add(goals);
     *   }
     * };
     * GoalAPI.getUserGoals(userEmail, addAllGoalsToList);
     *
     * Example of how to use synchronously:
     * GoalDto goal = GoalAPI.getUserGoals(userEmail, null);
     *
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static List<GoalDto> getUserGoals(String userEmail, Consumer<List<GoalDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = goalListCallSuccessListener(fn);
            errorListener = goalListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                (MyVolley.serverUrl+MyVolley.userPostfix+userEmail+MyVolley.goalPostfix),
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<GoalDto> goals = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    goals.add(jsonToGoalDto(result.optJSONObject(i)));
                }

                return goals;
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
        long startDate = json.optLong("startDate");
        long endDate = json.optLong("endDate");
        float weight = (float)json.optDouble("weight");
        String activityLevel = json.optString("activityLevel");
        String goalType = json.optString("goalType");
        float fats = (float)json.optDouble("fats");
        float carbs = (float)json.optDouble("carbs");
        float proteins = (float)json.optDouble("proteins");

        return new GoalDto(id, baseCalories, result, new Date(startDate), new Date(endDate), weight,
                activityLevel, goalType, fats, carbs, proteins);
    }

    private static JSONObject goalDtoToJson(GoalDto goal) {
        JSONObject json = new JSONObject();

        try {
            json.put("id", goal.getId());
            json.put("baseCalories", goal.getBaseCalories());
            json.put("result", goal.isResult());
            json.put("startDate", goal.getStartDate().getTime());
            json.put("endDate", goal.getEndDate().getTime());
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
