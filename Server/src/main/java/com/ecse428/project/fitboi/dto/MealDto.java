package com.ecse428.project.fitboi.dto;

public class MealDto {

    private int id;
    private String mealType;

    public MealDto(){

    }

    public MealDto(int id, String mealType){
        this.id = id;
        this.mealType = mealType;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    public String getMealType (){
        return this.mealType;
    }

    public void setMealType(String mealType){
        this.mealType = mealType;
    }
}