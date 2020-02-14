package com.example.fitboi.ui.meal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitboi.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MyViewHolder>{
    private static final String LOG_TAG = MealListAdapter.class.getSimpleName();
    private final ArrayList<FoodDtoTest> mfoods;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView foodNameView;
        public TextView foodCalCountView;

        public MyViewHolder(View v) {
            super(v);
            foodNameView = (TextView) v.findViewById(R.id.meal_item_name);
            foodCalCountView = (TextView) v.findViewById(R.id.meal_item_calorie_count);
        }


//        public void onClick(View view) {
//
//        }

    }

    public MealListAdapter(ArrayList<FoodDtoTest> foods) {
        this.mfoods = foods;
        Log.d(LOG_TAG, String.valueOf(foods.size()));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.meal_item, parent, false);

        return new MyViewHolder(listItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodDtoTest food = mfoods.get(position);

        TextView foodName = (TextView) holder.foodNameView;
        TextView foodCalCount= (TextView) holder.foodCalCountView;


        foodName.setText(food.getName());
        foodCalCount.setText(String.valueOf(food.getCalories()));

    }

    @Override
    public int getItemCount() {
        return mfoods.size();
    }
}
