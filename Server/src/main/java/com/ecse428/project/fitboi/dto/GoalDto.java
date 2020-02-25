package com.ecse428.project.fitboi.dto;

import java.sql.Date;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.GoalType;
import com.ecse428.project.fitboi.model.MacroDistribution;;


public class GoalDto {

    private int baseCalories;
    private boolean result;
    private Date startDate;
    private Date endDate;
    private float weightGoal;
    
    private ActivityLevel activityLevel;
    private GoalType goalType;
    private MacroDistribution macroDistribution;

    public GoalDto(){

    }

    public GoalDto(int baseCalories, boolean result, Date startDate, Date endDate, float weightGoal, ActivityLevel activityLevel, GoalType goalType, MacroDistribution macroDistribution){
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weightGoal = weightGoal;
        this.activityLevel = activityLevel;
        this.goalType = goalType;
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

    public Date getEndDate(){
        return this.endDate;
    }
  
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public float getWeightGoal(){
        return this.weightGoal;
    }

    public void setWeightGoal(float weightGoal){
        this.weightGoal = weightGoal;
    }

    public ActivityLevel getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel){
        this.activityLevel = activityLevel;
    }

    public GoalType getGoalType() {
        return this.goalType;
    }

    public void setGoalType(GoalType goalType){
        this.goalType = goalType;
    }

    public MacroDistribution getMacroDistribution(){
        return this.macroDistribution;
    }

    public void setMacroDistribution(MacroDistribution macroDistribution){
        this.macroDistribution = macroDistribution;
    }
}