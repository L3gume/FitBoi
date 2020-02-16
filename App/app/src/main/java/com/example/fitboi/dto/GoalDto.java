package com.example.fitboi.dto;

public class GoalDto {

    private int baseCalories;
    private boolean result;
    private String startDate;
    private double weight;

    private String activityLevel;
    private int fats;
    private int carbs;
    private int proteins;

    public GoalDto(){

    }

    public GoalDto(int baseCalories, boolean result, String startDate, double weight, String activityLevel, int fats, int carbs, int proteins){
        this.baseCalories = baseCalories;
        this.result = result;
        this.startDate = startDate;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
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

    public String getStartDate(){
        return this.startDate;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public double getWeight(){
        return this.weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;

    }
}

