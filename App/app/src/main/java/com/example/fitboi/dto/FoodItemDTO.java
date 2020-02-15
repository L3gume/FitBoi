package com.example.fitboi.dto;

import androidx.annotation.NonNull;

public class FoodItemDTO {

    private String name;
    private int calories;
    private float portionSize;

    public FoodItemDTO (String name, int calories, float portionSize) {
        this.name = name;
        this.calories = calories;
        this.portionSize = portionSize;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public float getPortionSize() {
        return portionSize;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setPortionSize(float portionSize) {
        this.portionSize = portionSize;
    }

    @Override
    @NonNull
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", calories='" + getCalories() + "'" +
                ", portionSize='" + getPortionSize() + "'" +
                "}";
    }

}
