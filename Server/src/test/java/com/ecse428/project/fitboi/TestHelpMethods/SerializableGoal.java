package com.ecse428.project.fitboi.TestHelpMethods;

import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.GoalType;

public class SerializableGoal {

    public int baseCalories;
    public boolean result;
    public String startDate;
    public String endDate;
    public float weightGoal;
    public ActivityLevel activityLevel;
    public GoalType goalType;
    public float carbs;
    public float proteins;
    public float fats;

    public SerializableGoal(int baseCalories, boolean result, String startDate, String endDate, float weightGoal, ActivityLevel activityLevel, GoalType goalType, float fats, float carbs, float proteins) {
        
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weightGoal = weightGoal;
        this.activityLevel = activityLevel;
        this.goalType = goalType;
        this.fats =fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

}