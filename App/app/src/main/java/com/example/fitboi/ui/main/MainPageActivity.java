package com.example.fitboi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fitboi.R;
import com.example.fitboi.ui.meal.AddMealActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainPageActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiSetup();
    }

    private void uiSetup() {
        getSupportActionBar().setTitle("Home");
        
        final CardView createMeal = findViewById(R.id.main_meals_card);
        createMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateMealActivity();
            }
        });
        final ImageButton profileButton = findViewById((R.id.profile_button));

        createMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateMealActivity();
            }
        });

        profileButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchUserProfileActivity();
            }
        }));

    }

    private void launchCreateMealActivity() {
        Intent intent = new Intent(this, AddMealActivity.class);
        startActivity(intent);
    }

    private void launchUserProfileActivity() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}
