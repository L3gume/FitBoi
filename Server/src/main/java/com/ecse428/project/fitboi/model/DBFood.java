package com.ecse428.project.fitboi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBFood
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;
    private long cal;
    private double protein;
    private double fat;
    private double carbs;

    // id is auto-generated, no need to have it in the constructor
    public DBFood(String aName, long aCal, double aProtein, double aFat,
        double aCarbs)
    {
        name = aName;
        cal = aCal;
        protein = aProtein;
        fat = aFat;
        carbs = aCarbs;
    }

    public DBFood()
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

    public boolean setId(int iId) {
        this.id = iId;
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

    public int getId() { return this.id; }
}
