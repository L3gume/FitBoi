package com.ecse428.project.fitboi.dto;

public class DBFoodDto
{
    private String name;
    private long cal;
    private double protein;
    private double fat;
    private double carbs;


    public DBFoodDto(String aName, long aCal, double aProtein, double aFat,
        double aCarbs)
    {
        name = aName;
        cal = aCal;
        protein = aProtein;
        fat = aFat;
        carbs = aCarbs;
    }

    public DBFoodDto()
    {

    }

    public boolean setName(String aName)
    {
        this.name = aName;
        return true;
    }

    public boolean setCal(long aCal)
    {
        this.cal = aCal;
        return true;
    }

    public boolean setProtein(double aProtein)
    {
        this.protein = aProtein;
        return true;
    }

    public boolean setFat(double aFat)
    {
        this.fat = aFat;
        return true;
    }

    public boolean setCarbs(double aCarbs)
    {
        this.carbs = aCarbs;
        return true;
    }

    public String getName()
    {
        return this.name;
    }

    public long getCal()
    {
        return this.cal;
    }

    public double getProtein()
    {
        return this.protein;
    }

    public double getFat()
    {
        return this.fat;
    }

    public double getCarbs()
    {
        return this.carbs;
    }

}