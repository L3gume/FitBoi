package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.FoodDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FoodAPI {

    /**
     * Get a list of all food items in a meal
     * path:    /users/{user_id}/metrics/{metric_id}/meal/{meal_id}/food
     * type:    GET
     *
     * Example of how to use asynchronously:
     * List listOfFoods;
     * Consumer addAllFoodsToList = new Consumer<List<FoodDto>>() {
     *   @Override
     *   public void accept(List<FoodDto> foodItems) {
     *     listOfFoods.addAll(foodItems);
     *   }
     * };
     * FoodAPI.getFoodsFromMeal("test@gmail".com, 1,1,addAllFoodToList);
     *
     * Example of how to use synchronously:
     * List<FoodDto> foods = FoodAPI.getFoodsFromMeal("test@gmail".com, 1,1,null);
     *
     * @param userId String: Unique id of user
     * @param metricId Integer: Unique id of metric
     * @param mealId Integer: Unique id of meal whose foods should be gotten
     * @param fn to be called by response, if null wait for response and return it directly
    **/
    public static List<FoodDto> getFoodsFromMeal(String userId, Integer metricId, Integer mealId, Consumer<List<FoodDto>> fn) {
        if (userId == null || metricId == null || mealId == null) return null;

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodListCallSuccessListener(fn);
            errorListener = foodListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix+mealId
                        +MyVolley.foodPostfix,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<FoodDto> foods = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    foods.add(jsonToFoodDto(result.optJSONObject(i)));
                }

                return foods;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get food items that start with given foodPrefix
     * path:    /food/{foodPrefix}/{numReturn}
     * type:    GET
     *
     * Example of how to use asynchronously:
     *
     * Consumer addFoundFoodToList = new Consumer(<FoodDto> fn) {
     *   @Override
     *   public void accept(List<FoodDto> foodDtos) {
     *      // do something
     *   }
     * };
     *
     * FoodAPI.getFoodsByPrefix("Chicken",3,addFoundFoodToList);
     *
     * Example of how to use synchronously:
     * List<FoodDto> foods = FoodAPI.getFoodsByPrefix("banana", 4, null);
     *
     * @param foodPrefix String: prefix of foods to be returned (i.e. "ban" will return Banana food as part of list)
     * @param numReturn Integer: maximum number of foods to be returned
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<FoodDto> getFoodsByPrefix(String foodPrefix, Integer numReturn, Consumer<List<FoodDto>> fn) {
        if (foodPrefix == null || numReturn == null) return null;

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodListCallSuccessListener(fn);
            errorListener = foodListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+MyVolley.foodPostfix+foodPrefix+"/"+numReturn+'/',
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                List<FoodDto> foods = new ArrayList<>();
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    foods.add(jsonToFoodDto(result.optJSONObject(i)));
                }

                return foods;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Add/log a food item to a user
     * path:    /users/{user_id}/metrics/{metric_id}/meals/{meal_id}/food
     * type:    POST
     *
     * Example of how to use asynchronously:
     * FoodDto foundFoodItem;
     * Consumer logFoodItem = new Consumer(<FoodDto> fn) {
     *   @Override
     *   public void accept(FoodDto foodDTO) {
     *      // do something
     *   }
     * };
     *
     * FoodAPI.addFoodToMeal(foundFoodItem.id,"test@gmail.com",1,1,logFoodItem);
     *
     * Example of how to use synchronously:
     * FoodDto foundFoodItem;
     * FoodDto foodAdded = FoodAPI.addFoodToMeal(foundFoodItem,"test@gmail.com",1,1,null);
     *
     * @param foodDto FoodDto: FoodDto to be added to user
     * @param userId String: Unique id of user to which food should be added
     * @param metricId Integer: Unique id of metric to which food should be added
     * @param mealId Integer: Unique id of meal to which food should be added
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static FoodDto addFoodToMeal(FoodDto foodDto, String userId, Integer metricId, Integer mealId, Consumer<FoodDto> fn) {
        if (foodDto == null || userId == null || metricId == null || mealId == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodCallSuccessListener(fn);
            errorListener = foodCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix +metricId
                        +MyVolley.mealPostfix+mealId
                        +MyVolley.foodPostfix,
                foodDtoToJson(foodDto),
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToFoodDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Delete a food item from a user
     * path:    /users/{user_id}/metrics/{metric_id}/meal/{meal_id}/food/{food_id}
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * FoodDto toBeDeletedFoodItem;
     * Consumer deleteFoodItem = new Consumer(<FoodDto> fn) {
     *   @Override
     *   public void accept(FoodDto foodDto) {
     *      // do something
     *   }
     * };
     *
     * FoodAPI.deleteFoodFromMeal(toBeDeletedFoodItem.id, "test@gmail.com", deleteFoodItem);
     *
     * Example of how to use synchronously:
     * FoodDto toBeDeletedFoodItem;
     * FoodDto foodAdded = FoodAPI.deleteFoodFromMeal(toBeDeletedFoodItem.id, "test@gmail.com", null);
     *
     * @param userId String: Unique id of user to which food item should be added
     * @param foodId Integer: Unique id of food item to be added
     * @param metricId Integer: Unique id of metric
     * @param mealId Integer: Unique id of meal
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static FoodDto deleteFoodFromMeal(String userId, Integer metricId, Integer mealId, Integer foodId, Consumer<FoodDto> fn) {
        if (userId == null || metricId == null || mealId == null || foodId == null) return null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodCallSuccessListener(fn);
            errorListener = foodCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId
                        +MyVolley.mealPostfix+mealId
                        +MyVolley.foodPostfix+foodId,
                null,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToFoodDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    // Success Listeners

    private static Response.Listener<JSONArray> foodListCallSuccessListener(final Consumer<List<FoodDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<FoodDto> foodDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    foodDtoList.add( jsonToFoodDto(response.optJSONObject(i)));
                }
                fn.accept(foodDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> foodCallSuccessListener(final Consumer<FoodDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToFoodDto(response));
            }
        };
    }


    // Error Listeners

    private static Response.ErrorListener foodListCallErrorListener(final Consumer<List<FoodDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener foodCallErrorListener(final Consumer<FoodDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static FoodDto jsonToFoodDto(JSONObject json) {
        int id = json.optInt("id");
        String name = json.optString("name");
        double cal = json.optDouble("cal");
        double protein = json.optDouble("protein");
        double fat = json.optDouble("fat");
        double carbs = json.optDouble("carbs");

        // The frontend receives a DBFoodDto object which is the same in every
        // way to the FoodDto object except for portionSize, so to simplify life
        // on the frontend we just deal with FoodDtos and set the portionSize to 0
        // when we receive a DBFoodDto.
        return new FoodDto(id,name,cal,0,protein,fat,carbs);
    }

    private static JSONObject foodDtoToJson(FoodDto foodDto) {
        JSONObject json = new JSONObject();

        try {
            // json.put("id", foodDto.getId()); id is autogenerated
            json.put("name", foodDto.getName());
            json.put("cal", foodDto.getCal());
            json.put("fat", foodDto.getFat());
            json.put("carbs", foodDto.getCarbs());
            json.put("portionSize", foodDto.getPortionSize());
        } catch (Exception e){
            // TODO: something with exception
        }
        return json;
    }

}
