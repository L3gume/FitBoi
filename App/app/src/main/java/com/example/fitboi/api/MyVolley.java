package com.example.fitboi.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static java.lang.Boolean.TRUE;

public class MyVolley {
    private static RequestQueue mRequestQueue;
//    private static ImageLoader mImageLoader;

    private MyVolley() {
        // no instances
    }

    public static final String ip_localhost = "http://127.0.0.1:8080";
    public static final String ip_dev_machine_for_emulator = "http://10.0.2.2:8080";
    public static final String ip_heroku = "http://fitboi-dev.herokuapp.com";

    public static final String serverUrl = ip_heroku;
    public static final String userPostfix = "/users/";
    public static final String goalPostfix = "/goals/";
    public static final String foodItemPostfix = "/foods/";

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        // int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        //  int cacheSize = 1024 * 1024 * memClass / 8;
        //  mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
    }


    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }


//    /**
//     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
//     * that no memory caching is used. This is useful for images that you know that will be show
//     * only once.
//     *
//     * @return
//     */
//    public static ImageLoader getImageLoader() {
//        if (mImageLoader != null) {
//            return mImageLoader;
//        } else {
//            throw new IllegalStateException("ImageLoader not initialized");
//        }
//    }
}
