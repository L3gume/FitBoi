package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitboi.dto.FoodItemDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.function.Consumer;

import static java.lang.Boolean.TRUE;

public class FoodItemAPI {

    static final String ip_localhost = "127.0.0.1";
    static final String ip_dev_machine = "10.0.2.2";
    static final boolean usingEmulator = TRUE;
    static final String foodItemUrl = "http://"+(usingEmulator ? ip_dev_machine : ip_localhost)+"/users/";

    /**
     * Get a list of all food items
     * path :    /foodItems
     *
     * Example of how to use:
     * List listOfFoodItems;
     * Consumer addAllUsersToList = new Consumer<List<FoodItemDto>> fn) {
     *   @Override
     *   public void accept(List<FoodItemDto> foodItems) {
     *     listOfFoodItems.addAll(foodItems);
     *   }
     * };
     * FoodAPI.getAllUsers(addAllFoodItemsToList);
     *
     *
     * @param fn will map the response to the food item list
    **/
    public static void getAllFoodItems(Consumer<List<FoodItemDto>> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                foodItemUrl,
                foodItemListCallSuccessListener(fn),
                foodItemListCallErrorListener(fn)
        );
        queue.add(request);
    }


    /**
     * Get a specific food items
     * path:     /foodItems/{foodItem}
     *
     * Example of how to use:
     * FoodItemDto foodItem;
     * Consumer addAllUsersToList = new Consumer(<FoodItemDto> fn) {
     *   @Override
     *   public void accept(FoodItemDto foodItemDTO) {
     *      foodItem = foodItemDTO;
     *   }
     * };
     *
     * FoodAPI.getFoodItem(foodItem, "Chicken");
     *
     *
     * @param fn will map the response to food item
     **/
    public static void getFoodItem(Consumer<FoodItemDto> fn, String foodItem) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(
                foodItemUrl + foodItem + "/",
                null,
                foodItemCallSuccessListener(fn),
                foodItemCallErrorListener(fn)
        );
        request.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        queue.add(request);
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
