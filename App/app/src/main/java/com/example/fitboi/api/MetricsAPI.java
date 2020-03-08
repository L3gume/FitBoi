package com.example.fitboi.api;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix,
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

    /**
     * Get all the metrics for a user
     * path:    /users/{user_id}/metrics
     * type:    GET
     *
     * Example of how to use asynchronously:
     *
     * Consumer addFoundMetricsToList = new Consumer(List<MetricDto> fn) {
     *   @Override
     *   public void accept(List<MetricDto> metricDtos) {
     *      // do something
     *   }
     * };
     *
     * FoodAPI.getAllUserMetrics("test@gmail.com",addFoundMetricsToList );
     *
     * Example of how to use synchronously:
     * List<MetricDto> metrics = MetricAPI.getAllUserMetrics("test@gmail.com", null);
     *
     * @param userId
     * @param fn to be called by response, if null wait for response and return it directly
     **/
    public static List<MetricDto> getAllUserMetrics(String userId, Consumer<List<MetricDto>> fn) {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        Response.Listener<JSONArray> successListener;
        Response.ErrorListener errorListener;

        if (fn == null) {
            successListener = future;
            errorListener = future;
        } else {
            successListener = metricListCallSuccessListener(fn);
            errorListener = metricListCallErrorListener(fn);
        }

        JsonArrayRequest request = new JsonArrayRequest(
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix,
                successListener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 0));
        MyVolley.getRequestQueue().add(request);
        if (fn == null) {
            try {
                List<MetricDto> metrics = null;
                JSONArray result = future.get(10, TimeUnit.SECONDS);
                for (int i=0; i<result.length(); i++) {
                    metrics.add(jsonToMetricDto(result.optJSONObject(i)));
                }

                return metrics;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Get a metric of given user
     * path:    /users/{user_id}/metrics/{metric_id}
     * type:    GET
     *
     * Example of how to use asynchronously:
     * Consumer addMetricToList = new Consumer<MetricDto>() {
     *   @Override
     *   public void accept(MetricDto goal) {
     *     // do something
     *   }
     * };
     * MetricAPI.getUserMetric(userEmail, 1,addMetricToList);
     *
     * Example of how to use synchronously:
     * MetricDto metric = MetricAPI.getUserMetric(userEmail, 1, null);
     *
     * @param userId: email of user to get the goals of
     * @param metricId
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static MetricDto getUserMetric(String userId, Integer metricId, Consumer<MetricDto> fn) {
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
                Request.Method.GET,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId,
                null,
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

    /**
     * Delete a metric from a given user
     * path:    /users/{user_id}/metrics/{metrics_id}
     * type:    DELETE
     *
     * Example of how to use asynchronously:
     * Consumer deleteMetricConfirmation = new Consumer<MetricDto>() {
     *   @Override
     *   public void accept(MetricDto metric) {
     *     // do something
     *   }
     * };
     * MetricAPI.deleteMetric("test@gmail.com",1,deleteMetricConfirmation);
     *
     * Example of how to use synchronously:
     * MetricDto deletedMetric = MetricAPI.deleteMetric("test@gmail.com",1, null);
     *
     * @param userId: email of user for which the metric should be deleted
     * @param metricId: unique id of metric to be deleted
     * @param fn to be called by response, if null wait for response and return it directly
     */
    public static MetricDto deleteMetric(String userId, Integer metricId, Consumer<MetricDto> fn) {
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
                Request.Method.DELETE,
                MyVolley.serverUrl
                        +MyVolley.userPostfix+userId
                        +MyVolley.metricPostfix+metricId,
                null,
                successListener,
                errorListener);

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
        int id = json.optInt("id");
        String date = json.optString("date");
        int exerciseSpending = json.optInt("exerciseSpending");

        return new MetricDto(id, date, exerciseSpending);
    }

    private static JSONObject metricDtoToJson(MetricDto metricDto) {
        JSONObject json = new JSONObject();

        try {
            // json.put("id", metricDto.getId()); id is autogenerated
            json.put("date", metricDto.getDate());
            json.put("exerciseSpending", metricDto.getExerciseSpending());
        } catch (Exception e) {
            // TODO: something with exception
        }

        return json;
    }
}
