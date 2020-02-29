package com.ecse428.project.fitboi.dto;

public class FoodDto
{
    private String name;
    private float cal;
    private float portionSize;
    private double protein;
    private double fat;
    private double carbs;


    public FoodDto(String aName, float aCal, float aPortionSize, double aProtein, double aFat,
        double aCarbs)
    {
        name = aName;
        cal = aCal;
        portionSize = aPortionSize;
        protein = aProtein;
        fat = aFat;
        carbs = aCarbs;
    }

    public FoodDto()
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

    public boolean setPortionSize(long aPortionSize)
    {
        this.portionSize = aPortionSize;
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

    public float getCal()
    {
        return this.cal;
    }

    public float getPortionSize()
    {
        return this.portionSize;
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
