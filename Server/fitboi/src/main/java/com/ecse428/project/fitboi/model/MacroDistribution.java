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
  private int fats;
  private int carbs;
  private int protein;

  //MacroDistribution Associations
  private Goal goal;
  private FoodItem foodItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MacroDistribution(int aFats, int aCarbs, int aProtein, Goal aGoal, FoodItem aFoodItem)
  {
    fats = aFats;
    carbs = aCarbs;
    protein = aProtein;
    if (aGoal == null || aGoal.getMacroDistribution() != null)
    {
      throw new RuntimeException("Unable to create MacroDistribution due to aGoal. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    goal = aGoal;
    if (aFoodItem == null || aFoodItem.getMacroDistribution() != null)
    {
      throw new RuntimeException("Unable to create MacroDistribution due to aFoodItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    foodItem = aFoodItem;
  }

  public MacroDistribution(int aFats, int aCarbs, int aProtein, int aBaseCaloriesForGoal, boolean aResultForGoal, Date aStartDateForGoal, float aWeightForGoal, ActivityLevel aActivityLevelForGoal, UserProfile aUserProfileForGoal, String aNameForFoodItem, int aCaloriesForFoodItem, int aPortionSizeForFoodItem, Meals aMealsForFoodItem)
  {
    fats = aFats;
    carbs = aCarbs;
    protein = aProtein;
    goal = new Goal(aBaseCaloriesForGoal, aResultForGoal, aStartDateForGoal, aWeightForGoal, aActivityLevelForGoal, aUserProfileForGoal, this);
    foodItem = new FoodItem(aNameForFoodItem, aCaloriesForFoodItem, aPortionSizeForFoodItem, aMealsForFoodItem, this);
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

  public int getFats()
  {
    return fats;
  }

  public int getCarbs()
  {
    return carbs;
  }

  public int getProtein()
  {
    return protein;
  }
  /* Code from template association_GetOne */
  public Goal getGoal()
  {
    return goal;
  }
  /* Code from template association_GetOne */
  public FoodItem getFoodItem()
  {
    return foodItem;
  }

  public void delete()
  {
    Goal existingGoal = goal;
    goal = null;
    if (existingGoal != null)
    {
      existingGoal.delete();
    }
    FoodItem existingFoodItem = foodItem;
    foodItem = null;
    if (existingFoodItem != null)
    {
      existingFoodItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "fats" + ":" + getFats()+ "," +
            "carbs" + ":" + getCarbs()+ "," +
            "protein" + ":" + getProtein()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "goal = "+(getGoal()!=null?Integer.toHexString(System.identityHashCode(getGoal())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "foodItem = "+(getFoodItem()!=null?Integer.toHexString(System.identityHashCode(getFoodItem())):"null");
  }
}