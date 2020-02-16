package com.example.fitboi.ui.meal;

import android.os.Bundle;

import com.example.fitboi.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class CreateMealActivity extends AppCompatActivity{
    private static final String LOG_TAG = CreateMealActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
    }

}
