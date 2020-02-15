package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitboi.dto.FoodItemDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.function.Consumer;

public class FoodItemAPI {

    private static final String foodItemUrl = "https://fitboi-dev.herokuapp.com/foodItems/";

    /**
     * Get a list of all food items
     * /foodItems
     * @param fn will store the response
    **/
    public static void getAllFoodItems(Consumer<List<FoodItemDTO>> fn) {
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
     * /foodItems/{foodItem}
     * @param fn will store the response
     **/
    public static void getFoodItem(Consumer<FoodItemDTO> fn, String foodItem) {
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

    private static Response.Listener<JSONArray> foodItemListCallSuccessListener(final Consumer<List<FoodItemDTO>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<FoodItemDTO> foodItemDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    foodItemDtoList.add( jsonToFoodItemDto(response.optJSONObject(i)));
                }
                fn.accept(foodItemDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> foodItemCallSuccessListener(final Consumer<FoodItemDTO> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToFoodItemDto(response));
            }
        };
    }


    // Error Listeners

    private static Response.ErrorListener foodItemListCallErrorListener(final Consumer<List<FoodItemDTO>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener foodItemCallErrorListener(final Consumer<FoodItemDTO> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    @NonNull
    private static FoodItemDTO jsonToFoodItemDto(JSONObject json) {
        String name = json.optString("name");
        int calories = json.optInt("calories");
        float portionSize = (float) json.optDouble("portionSize");

        return new FoodItemDTO(name, calories, portionSize);
    }

    private static JSONObject foodItemToJson(FoodItemDTO foodItem) {
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
