package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 20 "model.ump"
// line 80 "model.ump"
public class Metrics
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Metrics Attributes
  private Date date;
  private int exerciseSpending;

  //Metrics Associations
  private List<Meals> meals;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Metrics(Date aDate, int aExerciseSpending)
  {
    date = aDate;
    exerciseSpending = aExerciseSpending;
    meals = new ArrayList<Meals>();
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

  public boolean setExerciseSpending(int aExerciseSpending)
  {
    boolean wasSet = false;
    exerciseSpending = aExerciseSpending;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getExerciseSpending()
  {
    return exerciseSpending;
  }
  /* Code from template association_GetMany */
  public Meals getMeal(int index)
  {
    Meals aMeal = meals.get(index);
    return aMeal;
  }

  public List<Meals> getMeals()
  {
    List<Meals> newMeals = Collections.unmodifiableList(meals);
    return newMeals;
  }

  public int numberOfMeals()
  {
    int number = meals.size();
    return number;
  }

  public boolean hasMeals()
  {
    boolean has = meals.size() > 0;
    return has;
  }

  public int indexOfMeal(Meals aMeal)
  {
    int index = meals.indexOf(aMeal);
    return index;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMeals()
  {
    return 0;
  }

  public boolean addMeal(Meals aMeal)
  {
    boolean wasAdded = false;
    if (meals.contains(aMeal)) {
      return false;
    }
    meals.add(aMeal);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMeal(Meals aMeal)
  {
   if(!meals.contains(aMeal)){
     return false;
   }
   else {
     meals.remove(aMeal);
     return true;
   }
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMealAt(Meals aMeal, int index)
  {  
    boolean wasAdded = false;
    if(addMeal(aMeal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeals()) { index = numberOfMeals() - 1; }
      meals.remove(aMeal);
      meals.add(index, aMeal);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMealAt(Meals aMeal, int index)
  {
    boolean wasAdded = false;
    if(meals.contains(aMeal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeals()) { index = numberOfMeals() - 1; }
      meals.remove(aMeal);
      meals.add(index, aMeal);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMealAt(aMeal, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=meals.size(); i > 0; i--)
    {
      Meals aMeal = meals.get(i - 1);
      aMeal.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "exerciseSpending" + ":" + getExerciseSpending()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" +
            (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") +
            System.getProperties().getProperty("line.separator");
  }
}