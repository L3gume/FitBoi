package com.example.fitboi.dto;

import java.sql.Date;
import java.util.Objects;


public class GoalDto {

    private int id;
    private int baseCalories;
    private boolean result;
    private String startDate;
    private String endDate;
    private double weightGoal;
    private String activityLevel;
    private String goalType;
    private double fats;
    private double carbs;
    private double protein;

    public GoalDto(){

    }

    public GoalDto(int id, int baseCalories, boolean result, String startDate, String endDate, double weightGoal, String activityLevel, String goalType, double fats, double carbs, double protein) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(double weightGoal) {
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

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalDto goalDto = (GoalDto) o;
        return id == goalDto.id &&
                baseCalories == goalDto.baseCalories &&
                result == goalDto.result &&
                Double.compare(goalDto.weightGoal, weightGoal) == 0 &&
                Double.compare(goalDto.fats, fats) == 0 &&
                Double.compare(goalDto.carbs, carbs) == 0 &&
                Double.compare(goalDto.protein, protein) == 0 &&
                startDate.equals(goalDto.startDate) &&
                endDate.equals(goalDto.endDate) &&
                activityLevel.equals(goalDto.activityLevel) &&
                goalType.equals(goalDto.goalType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, baseCalories, result, startDate, endDate, weightGoal, activityLevel, goalType, fats, carbs, protein);
    }

    @Override
    public String toString() {
        return "GoalDto{" +
                "id=" + id +
                ", baseCalories=" + baseCalories +
                ", result=" + result +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", weightGoal=" + weightGoal +
                ", activityLevel='" + activityLevel + '\'' +
                ", goalType='" + goalType + '\'' +
                ", fats=" + fats +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}';
    }
}