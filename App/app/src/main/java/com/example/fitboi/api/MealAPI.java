package com.example.fitboi.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.MealDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MealAPI {

    /**
     * Get all the meals in the system
     * path:    /users/meals
     * type:    GET
     *
     * Example of how to use asynchronously:
     * List listOfMeals;
     * Consumer addAllMealsToList = new Consumer<List<MealDto>>() {
     *   @Override
     *   public void accept(List<MealDto> meals) {
     *     listOfMeals.addAll(meals);
     *   }
     * };
     * MealAPI.getAllMeals(addAllMealsToList);
     *
     * Example of how to use synchronously:
     * List<MealDto> meals = MealAPI.getAllMeals(null);
     *
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<MealDto> getAllMeals(Consumer<List<MealDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = mealListCallSuccessListener(fn);
            errorListener = mealListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+"/meals",
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<MealDto> meals = new ArrayList<>();
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    meals.add(jsonToMealDto(result.optJSONObject(i)));
                }

                return meals;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get all the meals for a users metric
     * path:    /users/{user_id}/metrics/{metric_id}/meal
     * type:    GET
     *
     * Example of how to use asynchronously:
     * List listOfMeals;
     * Consumer addAllMealsToList = new Consumer<List<MealDto>>() {
     *   @Override
     *   public void accept(List<MealDto> meals) {
     *     listOfMeals.addAll(meals);
     *   }
     * };
     * MealAPI.getUserMeals("test@gmail".com, 1,1,addAllMealsToList);
     *
     * Example of how to use synchronously:
     * List<MealDto> meals = MealAPI.getUserMeals("test@gmail".com, 1,1,null);
     *
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<MealDto> getUserMeals(String userId, Integer metricId, Consumer<List<MealDto>> fn) {
        if (userId == null || metricId == null) return null;

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = mealListCallSuccessListener(fn);
            errorListener = mealListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<MealDto> meals = new ArrayList<>();
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    meals.add(jsonToMealDto(result.optJSONObject(i)));
                }

                return meals;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get a meal of given user
     * path:    /users/{user_id}/metrics/{metric_id}/meal/{meal_id}
     * type:    GET
     *
     * Example of how to use asynchronously:
     * Consumer addMealToList = new Consumer<MealDto>() {
     *   @Override
     *   public void accept(MealDto meal) {
     *     // do something
     *   }
     * };
     * MealAPI.getMeal(userEmail, addMealToList);
     *
     * Example of how to use synchronously:
     * MealDto meal = MealAPI.getMeal(userEmail, null);
     *
     * @param userId: email of user to get the meal of
     * @param mealId: unique id of meal
     * @param metricId: unique id of metric
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static MealDto getMeal(String userId, Integer metricId, Integer mealId, Consumer<MealDto> fn) {
        if (userId == null || metricId == null || mealId == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = mealCallSuccessListener(fn);
            errorListener = mealCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix+mealId,
                null,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToMealDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;

    }

    /**
     * Create a meal for a given user
     * path:    /users/{user_id}/metrics/{metric_id}/meal
     * type:    POST
     *
     * Example of how to use asynchronously:
     * MealDto newMeal = new MealDto();
     * Consumer mealCreated = new Consumer<MealDto>() {
     *   @Override
     *   public void accept(MealDto meal) {
     *     // do something
     *   }
     * };
     * MealAPI.createMeal("test@gmail.com", 1, newMeal, mealCreated);
     *
     * Example of how to use synchronously:
     * MealDto newMeal = new MealDto();
     * MealDto meal = MealAPI.createMeal("test@gmail.com", 1, newMeal null);
     *
     * @param userId: email of user to get the meal of
     * @param metricId: unique id of metric
     * @param mealDto MealDto: mealDto to be created
     * @param fn to be called by response, if null wait for response and return it directly
     * @return meal created
     */
    public static MealDto createMeal(String userId, Integer metricId, MealDto mealDto, Consumer<MealDto> fn) {
        if (userId == null || metricId == null || mealDto == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = mealCallSuccessListener(fn);
            errorListener = mealCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix,
                mealDtoToJson(mealDto),
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToMealDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;

    }

    /**
     * Delete a meal from a given user
     * path:    /users/{user_id}/metrics/{metric_id}/meal/{mealId}
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * Consumer deleteMealConfirmation = new Consumer<MealDto>() {
     *   @Override
     *   public void accept(MealDto meal) {
     *     // do something
     *   }
     * };
     * MealAPI.deleteMeal("test@gmail.com",1,1,deleteMealConfirmation);
     *
     * Example of how to use synchronously:
     * MealDto deletedMeal = MealAPI.deleteMeal("test@gmail.com",1,1, null);
     *
     * @param userId: email of user for which the meal should be deleted
     * @param mealId: unique id of meal to delete
     * @param metricId: unique id of metric in which meal is
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static MealDto deleteMeal(String userId, Integer metricId, Integer mealId, Consumer<MealDto> fn) {
        if (userId == null || metricId == null || mealId == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = mealCallSuccessListener(fn);
            errorListener = mealCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix+mealId,
                null,
                successListener,
                errorListener);

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                return jsonToMealDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    // Success Listeners

    private static Response.Listener<JSONArray> mealListCallSuccessListener(final Consumer<List<MealDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<MealDto> mealDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    mealDtoList.add(jsonToMealDto(response.optJSONObject(i)));
                }
                fn.accept(mealDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> mealCallSuccessListener(final Consumer<MealDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToMealDto(response));
            }
        };
    }


    // Error Listeners

    private static Response.ErrorListener mealListCallErrorListener(final Consumer<List<MealDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener mealCallErrorListener(final Consumer<MealDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static MealDto jsonToMealDto(JSONObject json) {
        int id = json.optInt("id");
        String mealType = json.optString("mealType");

        return new MealDto(id,mealType);
    }

    private static JSONObject mealDtoToJson(MealDto mealDto) {
        JSONObject json = new JSONObject();

        try {
            // json.put("id", mealDto.getId()); id is autogenerated
            json.put("mealType", mealDto.getMealType());
        } catch (Exception e){
            // TODO: something with exception
        }
        return json;
    }
}
