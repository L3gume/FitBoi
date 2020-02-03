package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;

// line 48 "model.ump"
// line 99 "model.ump"
public class MacroDistribution
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MacroDistribution Attributes
  private float fats;
  private float carbs;
  private float protein;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MacroDistribution(float aFats, float aCarbs, float aProtein)
  {
    fats = aFats;
    carbs = aCarbs;
    protein = aProtein;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFats(int aFats)
  {
    boolean wasSet = false;
    fats = aFats;
    wasSet = true;
    return wasSet;
  }

  public boolean setCarbs(int aCarbs)
  {
    boolean wasSet = false;
    carbs = aCarbs;
    wasSet = true;
    return wasSet;
  }

  public boolean setProtein(int aProtein)
  {
    boolean wasSet = false;
    protein = aProtein;
    wasSet = true;
    return wasSet;
  }

  public float getFats()
  {
    return fats;
  }

  public float getCarbs()
  {
    return carbs;
  }

  public float getProtein()
  {
    return protein;
  }


  public String toString()
  {
    return super.toString() + "["+
            "fats" + ":" + getFats()+ "," +
            "carbs" + ":" + getCarbs()+ "," +
            "protein" + ":" + getProtein()+ "]" + System.getProperties().getProperty("line.separator");
  }
}