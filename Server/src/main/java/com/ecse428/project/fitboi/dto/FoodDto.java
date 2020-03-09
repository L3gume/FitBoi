package com.ecse428.project.fitboi.dto;

public class FoodDto
{
    private int id;
    private String name;
    private double cal;
    private double portionSize;
    private double protein;
    private double fat;
    private double carbs;


    public FoodDto(int id, String name, double cal, double portionSize, double protein, double fat, double carbs)
    {
        this.id = id;
        this.name = name;
        this.cal = cal;
        this.portionSize = portionSize;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public FoodDto()
    {

    }

    public boolean setId(int id)
    {
        this.id = id;
        return true;
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

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public double getCal()
    {
        return this.cal;
    }

    public double getPortionSize()
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
