package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.FoodItemDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FoodItemAPI {

    /**
     * Get a list of all food items
     * path:    /foodItems
     * type:    GET
     *
     * Example of how to use asynchronously:
     * List listOfFoodItems;
     * Consumer addAllFoodItemsToList = new Consumer<List<FoodItemDto>>() {
     *   @Override
     *   public void accept(List<FoodItemDto> foodItems) {
     *     listOfFoodItems.addAll(foodItems);
     *   }
     * };
     * FoodAPI.getFoodItems(addAllFoodItemsToList);
     *
     * Example of how to use synchronously:
     * List<FoodItemDto> foods = FoodItemAPI.getFoodItems(null);
     *
     * @param fn to be called by response, if null wait for response and return it directly
    **/
    public static List<FoodItemDto> getFoodItems(Consumer<List<FoodItemDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodItemListCallSuccessListener(fn);
            errorListener = foodItemListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+MyVolley.foodItemPostfix,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);

        if (fn == null) {
            try {
                List<FoodItemDto> foods = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    foods.add(jsonToFoodItemDto(result.optJSONObject(i)));
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
     * path:    /foodItems/{foodItem}
     * type:    GET
     *
     * Example of how to use asynchronously:
     * FoodItemDto foodItem;
     * Consumer addFoundFoodItemsToList = new Consumer(<FoodItemDto> fn) {
     *   @Override
     *   public void accept(FoodItemDto foodItemDTO) {
     *      foodItem = foodItemDTO;
     *   }
     * };
     *
     * FoodAPI.getFoodItemsByPrefix("Chicken",addFoundFoodItemsToList);
     *
     * Example of how to use synchronously:
     * List<FoodDto> food = FoodItemAPI.getFoodItemsByPrefix("banana", null);
     *
     * @param foodPrefix String: prefix of foods to be returned (i.e. "ban" will return Banana food item as part of list)
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<FoodItemDto> getFoodItemsByPrefix(String foodPrefix, Consumer<List<FoodItemDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodItemListCallSuccessListener(fn);
            errorListener = foodItemListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl+MyVolley.foodItemPostfix+foodPrefix+"/",
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                List<FoodItemDto> foods = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    foods.add(jsonToFoodItemDto(result.optJSONObject(i)));
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
     * path:    /users/{userId}/foods/
     * type:    POST
     *
     * Example of how to use asynchronously:
     * FoodItemDto foundFoodItem;
     * Consumer logFoodItem = new Consumer(<FoodItemDto> fn) {
     *   @Override
     *   public void accept(FoodItemDto foodItemDTO) {
     *      foodItemDTO = foundFoodItem;
     *   }
     * };
     *
     * FoodAPI.addFoodItemToUser(foundFoodItem.id, "test@gmail.com", logFoodItem);
     *
     * Example of how to use synchronously:
     * FoodItemDto foundFoodItem;
     * FoodDto foodAdded = FoodItemAPI.addFoodItemToUser(foundFoodItem.id, "test@gmail.com", null);
     *
     * @param foodDto FoodItemDto: FoodDto to be added to user
     * @param userId String: Unique id of user to which food item should be added
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static FoodItemDto addFoodItemToUser(FoodItemDto foodDto, String userId, Consumer<FoodItemDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodItemCallSuccessListener(fn);
            errorListener = foodItemCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl+MyVolley.userPostfix+userId+MyVolley.foodItemPostfix,
                foodItemToJson(foodDto),
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToFoodItemDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Delete a food item from a user
     * path:    /users/{userId}/foods/{foodId}
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * FoodItemDto toBeDeletedFoodItem;
     * Consumer deleteFoodItem = new Consumer(<FoodItemDto> fn) {
     *   @Override
     *   public void accept(FoodItemDto foodItemDTO) {
     *      foodItemDTO = toBeDeletedFoodItem;
     *   }
     * };
     *
     * FoodAPI.deleteFoodItemFromUser(toBeDeletedFoodItem.id, "test@gmail.com", deleteFoodItem);
     *
     * Example of how to use synchronously:
     * FoodItemDto toBeDeletedFoodItem;
     * FoodDto foodAdded = FoodItemAPI.deleteFoodItemFromUser(toBeDeletedFoodItem.id, "test@gmail.com", null);
     *
     * @param foodId Integer: Unique id of food item to be added
     * @param userId String: Unique id of user to which food item should be added
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static FoodItemDto deleteFoodItemFromUser(Integer foodId, String userId, Consumer<FoodItemDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = foodItemCallSuccessListener(fn);
            errorListener = foodItemCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                MyVolley.serverUrl+MyVolley.userPostfix+userId+MyVolley.foodItemPostfix+foodId,
                null,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToFoodItemDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    // Success Listeners

    private static Response.Listener<JSONArray> foodItemListCallSuccessListener(final Consumer<List<FoodItemDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<FoodItemDto> foodItemDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    foodItemDtoList.add( jsonToFoodItemDto(response.optJSONObject(i)));
                }
                fn.accept(foodItemDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> foodItemCallSuccessListener(final Consumer<FoodItemDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToFoodItemDto(response));
            }
        };
    }


    // Error Listeners

    private static Response.ErrorListener foodItemListCallErrorListener(final Consumer<List<FoodItemDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener foodItemCallErrorListener(final Consumer<FoodItemDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    @NonNull
    private static FoodItemDto jsonToFoodItemDto(JSONObject json) {
        String name = json.optString("name");
        int calories = json.optInt("calories");
        float portionSize = (float) json.optDouble("portionSize");

        return new FoodItemDto(name, calories, portionSize);
    }

    private static JSONObject foodItemToJson(FoodItemDto foodItem) {
        JSONObject json = new JSONObject();

        try {
            json.put("name", foodItem.getName());
            json.put("calories", foodItem.getCalories());
            json.put("portionSize", foodItem.getPortionSize());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }
}
