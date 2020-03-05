package com.example.fitboi.dto;

public class MetricsDto {

    private int id;
    private String date;
    private int exerciseSpending;

    public MetricsDto(){

    }

    public MetricsDto(int id, String date, int exerciseSpending){
        this.id = id;
        this.date = date;
        this.exerciseSpending = exerciseSpending;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    public String getDate (){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getExerciseSpending(){
        return this.exerciseSpending;
    }

    public void setExerciseSpending (int exerciseSpending){
        this.exerciseSpending = exerciseSpending;
    }
}