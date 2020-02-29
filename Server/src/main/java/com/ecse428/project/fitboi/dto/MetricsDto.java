package com.ecse428.project.fitboi.dto;

import java.sql.Date;
import java.util.List;

import com.ecse428.project.fitboi.model.Meal;


public class MetricsDto {

    private int id;
    private Date date;
    private int exerciseSpending;

    public MetricsDto(){

    }

    public MetricsDto(int id, Date date, int exerciseSpending){
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

    public Date getDate (){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getExerciseSpending(){
        return this.exerciseSpending;
    }

    public void setExerciseSpending (int exerciseSpending){
        this.exerciseSpending = exerciseSpending;
    }
}