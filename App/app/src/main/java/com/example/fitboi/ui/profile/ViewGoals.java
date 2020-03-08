package com.example.fitboi.ui.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitboi.R;

public class ViewGoals extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view_goals);
        getSupportActionBar().setTitle("My Goals");

    }

}
