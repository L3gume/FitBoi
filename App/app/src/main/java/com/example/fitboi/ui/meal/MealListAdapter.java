package com.example.fitboi.ui.meal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitboi.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
public class MealListAdapter extends ArrayAdapter<MealTest> {
    private static final String LOG_TAG = MealListAdapter.class.getSimpleName();

    public MealListAdapter(Activity context, ArrayList<MealTest> mealEntries) {
        super(context, 0, mealEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.meal_item, parent, false);
        }

        MealTest currentMeal = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.meal_item_name);
        nameTextView.setText(currentMeal.getName());

        TextView caloriesTextView =
                (TextView) listItemView.findViewById(R.id.meal_item_calorie_count);
        caloriesTextView.setText(Integer.toString(currentMeal.getCalories()));

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
