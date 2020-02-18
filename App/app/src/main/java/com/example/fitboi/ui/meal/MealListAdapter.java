package com.example.fitboi.ui.meal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitboi.R;
import com.example.fitboi.dto.FoodItemDto;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MyViewHolder>{
    private static final String LOG_TAG = MealListAdapter.class.getSimpleName();
    private final ArrayList<FoodItemDto> mfoods;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView foodNameView;
        public TextView foodCalCountView;
        public TextView foodPortionSizeView;

        public MyViewHolder(final View v) {
            super(v);

            foodNameView = (TextView) v.findViewById(R.id.meal_item_name);
            foodCalCountView = (TextView) v.findViewById(R.id.meal_item_calorie_count);
            foodPortionSizeView = (TextView) v.findViewById(R.id.meal_item_portion_size);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Toast.makeText(v.getContext(), mfoods.get(pos).getName() + " added",
                            Toast.LENGTH_LONG).show();
                    Log.d(LOG_TAG,
                            "Index: " + mfoods.get(pos) + ", name" + mfoods.get(pos).getName());
                }
            });
        }

    }

    public MealListAdapter(ArrayList<FoodItemDto> foods) {
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
        FoodItemDto food = mfoods.get(position);

        TextView foodNameView = (TextView) holder.foodNameView;
        TextView foodCalCountView = (TextView) holder.foodCalCountView;
        TextView foodPortionSizeView = (TextView) holder.foodPortionSizeView;

        String foodCalCountText = String.valueOf(food.getCalories()) +  " Calories";
        String foodPortionSizeText =
                (int) (Math.round(food.getPortionSize())) +  " " + "portions";

        foodNameView.setText(food.getName());
        foodCalCountView.setText(foodCalCountText);
        foodPortionSizeView.setText(foodPortionSizeText);
    }

    @Override
    public int getItemCount() {
        return mfoods.size();
    }
}
