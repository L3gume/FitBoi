package com.ecse428.project.fitboi.dto;

import java.sql.Date;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.GoalType;
import com.ecse428.project.fitboi.model.MacroDistribution;;


public class GoalDto {

    private int id;
    private int baseCalories;
    private boolean result;
    private Date startDate;
    private Date endDate;
    private float weightGoal;
    private String activityLevel;
    private String goalType;
    private float fats;
    private float carbs;
    private float protein;

    public GoalDto(){

    }

    public GoalDto(int id, int baseCalories, boolean result, Date startDate, Date endDate, float weightGoal, String activityLevel, String goalType, float fats, float carbs, float protein) {
        this.id = id;
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weightGoal = weightGoal;
        this.activityLevel = activityLevel;
        this.goalType = goalType;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseCalories() {
        return baseCalories;
    }

    public void setBaseCalories(int baseCalories) {
        this.baseCalories = baseCalories;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(float weightGoal) {
        this.weightGoal = weightGoal;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GoalDto goalDto = (GoalDto) object;
        return id == goalDto.id &&
                baseCalories == goalDto.baseCalories &&
                result == goalDto.result &&
                java.lang.Float.compare(goalDto.weightGoal, weightGoal) == 0 &&
                java.lang.Float.compare(goalDto.fats, fats) == 0 &&
                java.lang.Float.compare(goalDto.carbs, carbs) == 0 &&
                java.lang.Float.compare(goalDto.protein, protein) == 0 &&
                startDate.equals(goalDto.startDate) &&
                java.util.Objects.equals(endDate, goalDto.endDate) &&
                activityLevel.equals(goalDto.activityLevel) &&
                goalType.equals(goalDto.goalType);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, baseCalories, result, startDate, endDate, weightGoal, activityLevel, goalType, fats, carbs, protein);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "GoalDto{" +
                "id=" + id +
                ", baseCalories=" + baseCalories +
                ", result=" + result +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", weightGoal=" + weightGoal +
                ", activityLevel='" + activityLevel + '\'' +
                ", goalType='" + goalType + '\'' +
                ", fats=" + fats +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}';
    }
}