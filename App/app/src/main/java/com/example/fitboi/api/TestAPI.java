package com.example.fitboi.api;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request.Method;
import com.android.volley.VolleyError;

import java.util.function.Function;

public class TestAPI {

    public void testAPICall(Function<String, Boolean> fn) {
        RequestQueue queue = MyVolley.getRequestQueue();
        StringRequest myReq = new StringRequest(Method.GET,
                "https://www.google.com/",
                testAPICallSuccessListener(fn),
                testAPICallErrorListener(fn));
        queue.add(myReq);
    }


    private Response.Listener<String> testAPICallSuccessListener(final Function<String, Boolean> fn) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    fn.apply(response.substring(0,2));
            }
        };
    }

    private Response.ErrorListener testAPICallErrorListener(final Function<String, Boolean> fn) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                fn.apply(error.getMessage());
            }
        };
    }
}
