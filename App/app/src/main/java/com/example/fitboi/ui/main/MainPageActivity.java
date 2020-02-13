package com.example.fitboi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fitboi.R;
import com.example.fitboi.ui.meal.CreateMealActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class MainPageActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        uiSetup();
    }

    private void uiSetup() {
        final ImageButton createMeal = findViewById(R.id.main_page_button_create_meal);
        createMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateMealActivity();
            }
        });

    }

    private void launchCreateMealActivity() {
        Intent intent = new Intent(this, CreateMealActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
