package com.example.fitboi.ui.meal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitboi.R;
import com.example.fitboi.api.FoodAPI;
import com.example.fitboi.dto.FoodDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class AddMealActivity extends AppCompatActivity {
    private static final String LOG_TAG = AddMealActivity.class.getSimpleName();

    private RecyclerView.Adapter mealAdapter;
    private ArrayList<FoodDto> mealEntries;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        //ArrayList<MealTest> mealEntries = getRecentMeals();

        //Populate list view (testing)
        this.mealEntries = new ArrayList<>();
        this.mealAdapter = new MealListAdapter(mealEntries);

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

        RecyclerView mealListView = findViewById(R.id.add_meal_recyclerView);
        mealListView.setAdapter(this.mealAdapter);
        this.layoutManager = new LinearLayoutManager(this);
        mealListView.setLayoutManager(layoutManager);

        TextView emptyView = findViewById(R.id.add_meal_emptyview);
        if(mealEntries.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
        }else{
            emptyView.setVisibility(View.INVISIBLE);
        }

        androidx.appcompat.widget.SearchView searchView  =
                findViewById(R.id.add_meal_searchview);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProgressBar progressBar = findViewById(R.id.add_meal_loading_bar);
                progressBar.setVisibility(View.VISIBLE);

                Log.d(LOG_TAG,query + " searched");
                Consumer foodConsumer = new Consumer<List<FoodDto>>() {
                    @Override
                    public void accept(List<FoodDto> foods) {
                        for (FoodDto food: foods) {
                            mealEntries.add(food);
                            Log.d(LOG_TAG, food.getName() + "added");
                        }
                    }
                };
                FoodAPI.getFoodsByPrefix(query,10,foodConsumer);

                progressBar.setVisibility(View.INVISIBLE);
                uiSetup();
                return true;
            }

            /**
             * This override is required but this functionality is not on the MVP
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

    }

    private void launchCreateMealActivity() {
            Intent intent = new Intent(this, CreateMealActivity.class);
            startActivity(intent);
    }
}
