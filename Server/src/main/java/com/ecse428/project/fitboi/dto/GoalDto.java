package com.ecse428.project.fitboi.dto;

import java.sql.Date;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.MacroDistribution;;


public class GoalDto {

    private int baseCalories;
    private boolean result;
    private Date startDate;
    private float weight;
    
    private ActivityLevel activityLevel;
    private MacroDistribution macroDistribution;

    public GoalDto(){

    }

    public GoalDto(int baseCalories, boolean result, Date startDate, float weight, ActivityLevel activityLevel, MacroDistribution macroDistribution){
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.macroDistribution = macroDistribution;
    }

    public int getBaseCalories(){
        return this.baseCalories;
    }

    public void setBaseCalories (int baseCalories){
        this.baseCalories = baseCalories;
    }

    public boolean getResult (){
        return this.result;
    }

    public void setResult(boolean result){
        this.result = result;
    }

    public Date getStartDate(){
        return this.startDate;
    }
  
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public float getWeight(){
        return this.weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public ActivityLevel getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel){
        this.activityLevel = activityLevel;
    }

    public MacroDistribution getMacroDistribution(){
        return this.macroDistribution;
    }

    public void setMacroDistribution(MacroDistribution macroDistribution){
        this.macroDistribution = macroDistribution;
    }
}