package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;

// line 14 "model.ump"
// line 75 "model.ump"
public class Weight
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weight Attributes
  private Date date;
  private float weight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weight(Date aDate, float aWeight)
  {
    date = aDate;
    weight = aWeight;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(float aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public float getWeight()
  {
    return weight;
  }

  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" +
            (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") +
            System.getProperties().getProperty("line.separator");
  }
}