package com.example.fitboi.dto;

public class MetricDto {

    private static String tmp;

    public MetricDto() {

    }

    public MetricDto(String tmp) {
        this.tmp = tmp;
    }

    public String getTmp() {
        return this.tmp;
    }

    public void setTmp(String tmp) {
        MetricDto.tmp = tmp;
    }



}
