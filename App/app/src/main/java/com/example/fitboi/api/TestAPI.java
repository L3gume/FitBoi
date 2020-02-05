package com.example.fitboi.api;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.function.Consumer;

public class TestAPI {

    public static void addStuffToUsersThing(Consumer<String> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        StringRequest myReq = new StringRequest(Method.GET,
                "https://www.google.com/",
                testAPICallSuccessListener(fn),
                testAPICallErrorListener(fn));
        queue.add(myReq);
    }

    private static Response.Listener<String> testAPICallSuccessListener(final Consumer<String> fn) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fn.accept(response);
            }
        };
    }

    private static Response.ErrorListener testAPICallErrorListener(final Consumer<String> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                fn.accept(error.getMessage());
            }
        };
    }
}
