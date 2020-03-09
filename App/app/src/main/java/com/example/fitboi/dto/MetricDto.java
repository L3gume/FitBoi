package com.example.fitboi.dto;

import java.util.Objects;

public class MetricDto {

    private int id;
    private String date;
    private Integer exerciseSpending;

    public MetricDto() {
    }

    public MetricDto(int id, String date, Integer exerciseSpending) {
        this.id = id;
        this.date = date;
        this.exerciseSpending = exerciseSpending;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getExerciseSpending() {
        return exerciseSpending;
    }

    public void setExerciseSpending(Integer exerciseSpending) {
        this.exerciseSpending = exerciseSpending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricDto metricDto = (MetricDto) o;
        return id == metricDto.id &&
                date.equals(metricDto.date) &&
                exerciseSpending.equals(metricDto.exerciseSpending);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, exerciseSpending);
    }

    @Override
    public String toString() {
        return "MetricDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exerciseSpending=" + exerciseSpending +
                '}';
    }
}
