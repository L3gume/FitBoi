package com.example.fitboi.dto;

import java.util.Objects;

public class MacroDto {

    private String date;
    private Double proteins;
    private Double fats;
    private Double carbs;

    public MacroDto() {

    }

    public MacroDto(String date, Double proteins, Double fats, Double carbs) {
        this.date = date;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MacroDto macroDto = (MacroDto) o;
        return Objects.equals(date, macroDto.date) &&
                Objects.equals(proteins, macroDto.proteins) &&
                Objects.equals(fats, macroDto.fats) &&
                Objects.equals(carbs, macroDto.carbs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, proteins, fats, carbs);
    }

    @Override
    public String toString() {
        return "MacroDto{" +
                "date='" + date + '\'' +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbs=" + carbs +
                '}';
    }
}
