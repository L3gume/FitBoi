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
     * @param userEmail: email of user for which the goal should be deleted
     * @param goalId of goal to be deleted
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto deleteUserGoal(String userEmail, Integer goalId, Consumer<GoalDto> fn) {
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
                (MyVolley.serverUrl+MyVolley.userPostfix+userEmail+MyVolley.goalPostfix+goalId+'/'),
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
     * @param userEmail: email of user to get the goals of
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

    /**
     * Update a given user's goal
     * path:    /users/{userEmail}/goals/{goalId}
     * type:    PUT
     *
     * Example of how to use asynchronously:
     * GoalDto goalToUpdate;
     * Consumer updateGoal = new Consumer<GoalDto>() {
     *   @Override
     *   public void accept(GoalDto updatedGoal) {
     *     if (updatedGoal == goalToUpdate) {
     *         String message = "Succesfully updated goal!");
     *     }
     *   }
     * };
     * GoalAPI.updateUserGoal(userEmail, goalToUpdate.id, goalToUpdate, updateGoal);
     *
     * Example of how to use synchronously:
     * GoalDto goalToUpdate;
     * GoalDto goal = GoalAPI.updateUserGoal(userEmail, goalToUpdate.id, goalToUpdate, null);
     *
     * @param userEmail : email of user to update goal of
     * @param goalId : id of goal to update
     * @param newGoalDto : new information of goal
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static GoalDto updateUserGoal(String userEmail, Integer goalId, GoalDto newGoalDto, Consumer<GoalDto> fn) {
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
                (MyVolley.serverUrl+MyVolley.userPostfix+userEmail+MyVolley.goalPostfix+goalId+'/'),
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
        int baseCalories = json.optInt("baseCalories");
        boolean result = json.optBoolean("result");
        String startDate = json.optString("startDate");
        double weight = json.optDouble("weight");
        String activityLevel = json.optString("activityLevel");
        int fats = json.optInt("fats");
        int carbs = json.optInt("carbs");
        int proteins = json.optInt("proteins");

        return new GoalDto(baseCalories,result,startDate,weight,activityLevel,fats,carbs,proteins);
    }

    private static JSONObject goalDtoToJson(GoalDto goal) {
        JSONObject json = new JSONObject();

        try {
            json.put("baseCalories", goal.getBaseCalories());
            json.put("result", goal.getResult());
            json.put("startDate", goal.getStartDate());
            json.put("weight", goal.getWeight());
            json.put("activityLevel", goal.getActivityLevel());
            json.put("fats", goal.getFats());
            json.put("carbs", goal.getCarbs());
            json.put("proteins", goal.getProteins());
        } catch (Exception e) {
            // TODO: something with exception
        }
        return json;
    }
}
