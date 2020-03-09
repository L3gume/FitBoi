package com.example.fitboi.ui.meal;


import java.sql.Time;

/**
 * Created by Andi-Camille Bakti on 13/02/2020.
 */
class FoodDtoTest {

    private String name;
    private Integer calories;
    private Time date = new Time(69420);


    private Float portion;

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    public Float getPortionSize() {
        return portion;
    }
    public Time getDate() {
        return date;
    }

    public FoodDtoTest(String name, Integer calories){
        this.name = name;
        this.calories = calories;
    }
}
