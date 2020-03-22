package com.example.fitboi.dto;

import java.util.Objects;

public class MetricDto {

    private int id;
    private String date;
    private Integer exerciseSpending;
    private String footNote;

    public MetricDto() {
    }

    public MetricDto(int id, String date, Integer exerciseSpending, String footNote) {
        this.id = id;
        this.date = date;
        this.exerciseSpending = exerciseSpending;
        this.footNote = footNote;
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

    public String getFootNote() {
        return footNote;
    }

    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricDto metricDto = (MetricDto) o;
        return id == metricDto.id &&
                Objects.equals(date, metricDto.date) &&
                Objects.equals(exerciseSpending, metricDto.exerciseSpending) &&
                Objects.equals(footNote, metricDto.footNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, exerciseSpending, footNote);
    }

    @Override
    public String toString() {
        return "MetricDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exerciseSpending=" + exerciseSpending +
                ", footNote='" + footNote + '\'' +
                '}';
    }
}
