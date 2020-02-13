package com.example.fitboi.ui.meal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.fitboi.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class AddMealActivity extends AppCompatActivity {
    private static final String LOG_TAG = AddMealActivity.class.getSimpleName();

    private MealListAdapter mealAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        //Populate list view
        ArrayList<MealTest> mealEntries = new ArrayList<MealTest>();
        mealEntries.add(new MealTest("Pizza", 100));
        mealEntries.add(new MealTest("Mac+Cheese", 200));
        mealEntries.add(new MealTest("Salad", 300));
        mealEntries.add(new MealTest("MickeyDees", 400));
        mealEntries.add(new MealTest("Timmies", 500));

        this.mealAdapter = new MealListAdapter(this, mealEntries);

        uiSetup();
    }

    private void uiSetup() {
        getSupportActionBar().setTitle("Add meal");

        Button addMealButton = findViewById(R.id.add_meal_create_new_button);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCreateMealActivity();
            }
        });

        ListView mealListView = findViewById(R.id.add_meal_listview);
        mealListView.setAdapter(this.mealAdapter);
    }

    private void launchCreateMealActivity() {
            Intent intent = new Intent(this, CreateMealActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
    }
}
