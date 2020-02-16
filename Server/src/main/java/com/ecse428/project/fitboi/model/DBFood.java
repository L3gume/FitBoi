package com.ecse428.project.fitboi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

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
