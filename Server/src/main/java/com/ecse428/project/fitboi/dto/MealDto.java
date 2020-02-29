package com.ecse428.project.fitboi.dto;

import java.util.List;

import com.ecse428.project.fitboi.model.FoodItem;
import com.ecse428.project.fitboi.model.MealType;;


public class MealDto {

    private int id;
    private MealType mealType;
    private List<FoodItem> foodItems;

    public MealDto(){

    }

    public MealDto(int id, MealType mealType, List<FoodItem> foodItems){
        this.id = id;
        this.mealType = mealType;
        this.foodItems = foodItems;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    public MealType getMealType (){
        return this.mealType;
    }

    public void setMealType(MealType mealType){
        this.mealType = mealType;
    }

    public List<FoodItem> getFoodItems(){
        return this.foodItems;
    }
  
    public void setFoodItems(List<FoodItem> foodItems){
        this.foodItems = foodItems;
    }
}