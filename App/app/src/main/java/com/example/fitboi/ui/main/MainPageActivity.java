package com.example.fitboi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fitboi.R;
import com.example.fitboi.ui.meal.AddMealActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class MainPageActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainPageActivity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        uiSetup();
    }

    private void uiSetup() {
        getSupportActionBar().setTitle("Home");

        final ImageButton createMeal = findViewById(R.id.main_page_button_create_meal);




        createMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateMealActivity();
            }
        });

    }

    private void launchCreateMealActivity() {
        Intent intent = new Intent(this, AddMealActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
