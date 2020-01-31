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
  private UserProfile userProfile;
  private List<Meals> meals;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Metrics(Date aDate, int aExerciseSpending, UserProfile aUserProfile)
  {
    date = aDate;
    exerciseSpending = aExerciseSpending;
    boolean didAddUserProfile = setUserProfile(aUserProfile);
    if (!didAddUserProfile)
    {
      throw new RuntimeException("Unable to create metric due to userProfile. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public UserProfile getUserProfile()
  {
    return userProfile;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setUserProfile(UserProfile aUserProfile)
  {
    boolean wasSet = false;
    //Must provide userProfile to metric
    if (aUserProfile == null)
    {
      return wasSet;
    }

    if (userProfile != null && userProfile.numberOfMetrics() <= UserProfile.minimumNumberOfMetrics())
    {
      return wasSet;
    }

    UserProfile existingUserProfile = userProfile;
    userProfile = aUserProfile;
    if (existingUserProfile != null && !existingUserProfile.equals(aUserProfile))
    {
      boolean didRemove = existingUserProfile.removeMetric(this);
      if (!didRemove)
      {
        userProfile = existingUserProfile;
        return wasSet;
      }
    }
    userProfile.addMetric(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMeals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Meals addMeal(MealType aMealType)
  {
    return new Meals(aMealType, this);
  }

  public boolean addMeal(Meals aMeal)
  {
    boolean wasAdded = false;
    if (meals.contains(aMeal)) { return false; }
    Metrics existingMetrics = aMeal.getMetrics();
    boolean isNewMetrics = existingMetrics != null && !this.equals(existingMetrics);
    if (isNewMetrics)
    {
      aMeal.setMetrics(this);
    }
    else
    {
      meals.add(aMeal);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMeal(Meals aMeal)
  {
    boolean wasRemoved = false;
    //Unable to remove aMeal, as it must always have a metrics
    if (!this.equals(aMeal.getMetrics()))
    {
      meals.remove(aMeal);
      wasRemoved = true;
    }
    return wasRemoved;
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
    UserProfile placeholderUserProfile = userProfile;
    this.userProfile = null;
    if(placeholderUserProfile != null)
    {
      placeholderUserProfile.removeMetric(this);
    }
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
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getUserProfile()!=null?Integer.toHexString(System.identityHashCode(getUserProfile())):"null");
  }
}