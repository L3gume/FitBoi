package com.example.fitboi.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitboi.dto.GoalDto;
import com.example.fitboi.dto.UserDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.function.Consumer;

public class GoalAPI {

    /********** PUBLIC METHODS **********/

    /**
     * Example of how to use:
     * Consumer addGoalConfirmation = new Consumer<GoalDto> fn) {
     *   @Override
     *   public void accept(GoalDto user) {
     *     if (goal.goalId == goalDto.goalId) {
     *         Message.setText("Goal Added!");
     *     }
     *   }
     * };
     * GoalAPI.addNewGoal(goalDto, addGoalConfirmation);
     * @param goalToAdd
     * @param fn
     */
    public static void addNewGoal(GoalDto goalToAdd, Consumer<GoalDto> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                (MyVolley.userUrl+MyVolley.goalUrl),
                goalDtoToJson(goalToAdd),
                goalCallSuccessListener(fn),
                goalCallErrorListener(fn)
        );
        queue.add(request);
    }

    /**
     * Example of how to use:
     * Consumer deleteGoalConfirmation = new Consumer<GoalDto> fn) {
     *   @Override
     *   public void accept(GoalDto user) {
     *     if (goal.goalId == goalDto.goalId) {
     *         Message.setText("Goal Deleted!");
     *     }
     *   }
     * };
     * GoalAPI.addNewGoal(goal.goalId, deleteGoalConfirmation);
     * @param goalId
     * @param fn
     */
    public static void deleteGoal(String goalId, Consumer<GoalDto> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                (MyVolley.userUrl+MyVolley.goalUrl),
                null,
                goalCallSuccessListener(fn),
                null);
        queue.add(request);
    }

    /**
     * Example of how to use:
     * Consumer addAllGoalsToList = new Consumer<List<GoalDto>> fn) {
     *   @Override
     *   public void accept(List<GoalDto> goals) {
     *     ListObject.add(goals);
     *   }
     * };
     * GoalAPI.getUserGoals(userEmail, addAllGoalsToList);
     * @param fn to be called by response
     */
    public static void getUserGoals(String userEmail, Consumer<List<GoalDto>> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                (MyVolley.userUrl+userEmail+MyVolley.goalUrl),
                goalListCallSuccessListener(fn),
                goalListCallErrorListener(fn)
        );
        queue.add(request);
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
        String tmp = json.optString("tmp");

        return new GoalDto(tmp);
    }

    private static JSONObject goalDtoToJson(GoalDto goal) {
        JSONObject json = new JSONObject();

        try {
            json.put("tmp", goal.getTmp());
        } catch (Exception e) {
            // TODO: something with exception
        }
    }
}
