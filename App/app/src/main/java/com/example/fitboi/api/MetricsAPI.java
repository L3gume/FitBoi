package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.fitboi.dto.MetricDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MetricsAPI {

    /**
     * Add new metric to given user
     * path:    /users/{userEmail}/metrics/
     * type:    POST
     *
     * Example of how to use asynchronously:
     * Consumer addMetricConfirmation = new Consumer<MetricDto>() {
     *   @Override
     *   public void accept(MetricDto metric) {
     *     if (metric.id == metric.id) {
     *         Message.setText("Metric Added!");
     *     }
     *   }
     * };
     * MetricAPI.addNewMetric(metricDto, addMetricConfirmation);
     *
     * Example of how to use synchronously:
     * MetricDto newGoal = MetricDto();
     * MetricDto addedGoal = MetricAPI.addNewGoal("test@gmail.com", newGoal, null);
     * if (addedGoal == newGoal) {
     *     String message = "Adding new goal worked!";
     * }
     * @param userId of user to which to add the goal
     * @param metricToAdd GoalDto of goal to add
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static MetricDto addMetric(String userId, MetricDto metricToAdd, Consumer<MetricDto> fn) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        Response.Listener<JSONObject> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = metricCallSuccessListener(fn);
            errorListener = metricCallErrorListener(fn);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                MyVolley.serverUrl+MyVolley.userPostfix+userId+MyVolley.metricPostfix,
                metricDtoToJson(metricToAdd),
                successListener,
                errorListener
        );
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                return jsonToMetricDto(future.get(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    // Success Listeners

    private static Response.Listener<JSONArray> metricListCallSuccessListener(final Consumer<List<MetricDto>> fn) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<MetricDto> metricDtoList = null;
                for (int i=0; i<response.length(); i++) {
                    metricDtoList.add( jsonToMetricDto(response.optJSONObject(i)));
                }
                fn.accept(metricDtoList);
            }
        };
    }

    private static Response.Listener<JSONObject> metricCallSuccessListener(final Consumer<MetricDto> fn) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fn.accept(jsonToMetricDto(response));
            }
        };
    }


    // Error Listeners

    private static Response.ErrorListener metricListCallErrorListener(final Consumer<List<MetricDto>> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    private static Response.ErrorListener metricCallErrorListener(final Consumer<MetricDto> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: do something with error
            }
        };
    }

    @NonNull
    private static MetricDto jsonToMetricDto(JSONObject json) {

        String tmp = json.optString("tmp");

        return new MetricDto(tmp);
    }

    private static JSONObject metricDtoToJson(MetricDto metricDto) {
        JSONObject json = new JSONObject();

        try {
            json.put("tmp", metricDto.getTmp());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }
}
