package com.example.fitboi.ui.meal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.fitboi.R;

import java.util.AbstractQueue;
import java.util.ArrayList;
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
    private ArrayList<FoodDtoTest> mealEntries;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        //ArrayList<MealTest> mealEntries = getRecentMeals();

        //Populate list view (testing)
        ArrayList<FoodDtoTest> mealEntries = new ArrayList<FoodDtoTest>();
        mealEntries.add(new FoodDtoTest("Pizza", 100));
        mealEntries.add(new FoodDtoTest("Mac+Cheese", 200));
        mealEntries.add(new FoodDtoTest("Salad", 300));
        mealEntries.add(new FoodDtoTest("MickeyDees", 400));
        mealEntries.add(new FoodDtoTest("Timmies", 500));

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
        mealListView.setLayoutManager(new LinearLayoutManager(this));

        androidx.appcompat.widget.SearchView searchView  =
                findViewById(R.id.add_meal_searchview);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProgressBar progressBar = findViewById(R.id.add_meal_loading_bar);
                progressBar.setVisibility(View.VISIBLE);
//                this.searchRequest = query;
//                Log.d(LOG_TAG,query + "searched");
//
//                loaderManager = getLoaderManager();
//                loaderManager.initLoader(BOOK_LOADER_ID, null,MainActivity.this);
                  // Test without threads

//                Consumer foodConsumer = new Consumer<List<FoodItemDTO>>() {
//                    @Override
//                    public void accept(List<FoodItemDTO> food) {
//
//                        mealEntries.add(f.name, f.calories);
//                    }
//                };
//                FoodItemAPI.getFoodItem(foodConsumer, searchText);

                progressBar.setVisibility(View.INVISIBLE);
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

//        ???
    }

    private void launchCreateMealActivity() {
            Intent intent = new Intent(this, CreateMealActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
    }
}
