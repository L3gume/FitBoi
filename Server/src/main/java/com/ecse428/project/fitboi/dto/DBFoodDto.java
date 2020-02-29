package com.ecse428.project.fitboi.dto;

public class DBFoodDto
{
    private int id;
    private String name;
    private long cal;
    private double protein;
    private double fat;
    private double carbs;

    public DBFoodDto() {

    }

    public DBFoodDto(int id, String name, long cal, double protein, double fat, double carbs) {
        this.id = id;
        this.name = name;
        this.cal = cal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCal() {
        return cal;
    }

    public void setCal(long cal) {
        this.cal = cal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        DBFoodDto dbFoodDto = (DBFoodDto) object;
        return id == dbFoodDto.id &&
                cal == dbFoodDto.cal &&
                java.lang.Double.compare(dbFoodDto.protein, protein) == 0 &&
                java.lang.Double.compare(dbFoodDto.fat, fat) == 0 &&
                java.lang.Double.compare(dbFoodDto.carbs, carbs) == 0 &&
                name.equals(dbFoodDto.name);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, name, cal, protein, fat, carbs);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "DBFoodDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cal=" + cal +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}
