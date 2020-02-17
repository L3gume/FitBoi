package com.ecse428.project.fitboi.TestHelpMethods;

import com.ecse428.project.fitboi.model.ActivityLevel;

public class SerializableGoal {

    public int baseCalories;
    public boolean result;
    public String startDate;
    public float weight;
    public ActivityLevel activityLevel;
    public float carbs;
    public float proteins;
    public float fats;

    public SerializableGoal(int baseCalories, boolean result, String startDate, float weight, ActivityLevel activityLevel, float fats, float carbs, float proteins) {
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.fats =fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

}