package com.example.fitboi.ui.meal;


import java.sql.Time;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
class MealTest {

    private String name;
    private Integer calories;
    private Time date = new Time(69420);

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    public Time getDate() {
        return date;
    }

    public MealTest(String name, Integer calories){
        this.name = name;
        this.calories = calories;
    }
}
