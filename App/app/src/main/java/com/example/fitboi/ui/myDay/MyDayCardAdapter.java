package com.example.fitboi.ui.myDay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitboi.R;
import com.example.fitboi.dto.FoodDto;

import java.util.List;

public class MyDayCardAdapter extends RecyclerView.Adapter<MyDayCardAdapter.MyViewHolder> {
    // This will be replaced with meals
    private List<FoodDto> mFoodItemDtos;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView vCalorieCount;
        private TextView vCardName;
        private ImageView vCardImage;
        private ImageView vExpandButton;
        private ImageView vDeleteButton;

        public MyViewHolder(View v){
            super(v);
            vCalorieCount = v.findViewById(R.id.my_day_calorie_count);
            vCardName = v.findViewById(R.id.my_day_meal_type);
            vCardImage = v.findViewById(R.id.my_day_circle);
            vExpandButton = v.findViewById(R.id.expand_meal);
            vDeleteButton = v.findViewById(R.id.delete_activity);
        }
    }

    public MyDayCardAdapter(List<FoodDto> foodItemDtos) {
        this.mFoodItemDtos = foodItemDtos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_day_meal_card, parent, false);
        this.mContext = parent.getContext();
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        FoodDto foodItem = mFoodItemDtos.get(position);
        String numberOfCalories = foodItem.getCal() + " Calories";
        holder.vCardImage.setImageResource(R.drawable.my_day_circle);
        holder.vCardName.setText(foodItem.getName());
        holder.vCalorieCount.setText(numberOfCalories);
        holder.vExpandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MyDayActivity.class);
//                mContext.startActivity(intent);

            }
        });
        holder.vDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodItemDtos.size();
    }

    private void removeItem(int index) {
        mFoodItemDtos.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mFoodItemDtos.size());
    }
}