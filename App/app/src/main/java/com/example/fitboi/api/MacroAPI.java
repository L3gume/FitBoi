package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.MacroDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MacroAPI {

    /**
     * Get aggregate data of macros in given date range
     * path:    /users/{user_id}/macro/range/{start_date}/{end_date}
     * type:    GET
     *
     * Example of how to use asynchronously:
     *
     * Consumer addStats = new Consumer(List<MacroDto> fn) {
     *   @Override
     *   public void accept(List<MacroDto> macroDtos) {
     *      // do something
     *   }
     * };
     *
     * MacroAPI.getUserMacrosInRange("test@gmail.com", "2020-01-01", "2020-02-01", addStats );
     *
     * Example of how to use synchronously:
     * List<MacroDto> macros = MacroAPI.getUserMacrosInRange("test@gmail.com", "2020-01-01", "2020-02-01", null);
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<MacroDto> getUserMacrosInRange(String userId, String startDate, String endDate, Consumer<List<MacroDto>> fn) {
        if (userId == null) return null;

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = macroListCallSuccessListener(fn);
            errorListener = macroListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.macroPostfix
                        +MyVolley.rangePostfix
                        +startDate+'/'+endDate,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                List<MacroDto> metrics = new ArrayList<>();
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    metrics.add(jsonToMacroDto(result.optJSONObject(i)));
                }

                return metrics;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    // Success Listeners

    private static Response.Listener<JSONArray> macroListCallSuccessListener(final Consumer<List<MacroDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<MacroDto> macroDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    macroDtoList.add( jsonToMacroDto(response.optJSONObject(i)));
                }
                fn.accept(macroDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> macroCallSuccessListener(final Consumer<MacroDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToMacroDto(response));
            }
        };
    }

    // Error Listeners

    private static Response.ErrorListener macroListCallErrorListener(final Consumer<List<MacroDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener macroCallErrorListener(final Consumer<MacroDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    @NonNull
    private static MacroDto jsonToMacroDto(JSONObject json) {
        String date = json.optString("date");
        Double proteins = json.optDouble("proteins");
        Double fats = json.optDouble("fats");
        Double carbs = json.optDouble("carbs");

        return new MacroDto(date,proteins,fats,carbs);
    }

    private static JSONObject macroDtoToJson(MacroDto macroDto) {
        JSONObject json = new JSONObject();

        try {
            json.put("date", macroDto.getDate());
            json.put("proteins", macroDto.getProteins());
            json.put("fats", macroDto.getFats());
            json.put("carbs", macroDto.getCarbs());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }
}
