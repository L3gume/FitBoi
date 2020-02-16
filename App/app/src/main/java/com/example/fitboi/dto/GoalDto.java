package com.example.fitboi.dto;

public class GoalDto {

    private String tmp;

    public GoalDto(String tmp) {
        this.tmp = tmp;
    }

    public String getTmp() {
        return this.tmp;
    }

    public GoalDto tmp(String email) {
        this.tmp = tmp;
        return this;
    }
}

