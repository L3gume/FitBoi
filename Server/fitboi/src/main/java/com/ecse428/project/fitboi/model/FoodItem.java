package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/



// line 55 "model.ump"
// line 104 "model.ump"
public class FoodItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodItem Attributes
  private String name;
  private int calories;
  private int portionSize;

  //FoodItem Associations
  private Meals meals;
  private MacroDistribution macroDistribution;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FoodItem(String aName, int aCalories, int aPortionSize, Meals aMeals, MacroDistribution aMacroDistribution)
  {
    name = aName;
    calories = aCalories;
    portionSize = aPortionSize;
    boolean didAddMeals = setMeals(aMeals);
    if (!didAddMeals)
    {
      throw new RuntimeException("Unable to create foodItem due to meals. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aMacroDistribution == null || aMacroDistribution.getFoodItem() != null)
    {
      throw new RuntimeException("Unable to create FoodItem due to aMacroDistribution. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    macroDistribution = aMacroDistribution;
  }

  public FoodItem(String aName, int aCalories, int aPortionSize, Meals aMeals, int aFatsForMacroDistribution, int aCarbsForMacroDistribution, int aProteinForMacroDistribution, Goal aGoalForMacroDistribution)
  {
    name = aName;
    calories = aCalories;
    portionSize = aPortionSize;
    boolean didAddMeals = setMeals(aMeals);
    if (!didAddMeals)
    {
      throw new RuntimeException("Unable to create foodItem due to meals. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    macroDistribution = new MacroDistribution(aFatsForMacroDistribution, aCarbsForMacroDistribution, aProteinForMacroDistribution, aGoalForMacroDistribution, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    calories = aCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setPortionSize(int aPortionSize)
  {
    boolean wasSet = false;
    portionSize = aPortionSize;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getCalories()
  {
    return calories;
  }

  public int getPortionSize()
  {
    return portionSize;
  }
  /* Code from template association_GetOne */
  public Meals getMeals()
  {
    return meals;
  }
  /* Code from template association_GetOne */
  public MacroDistribution getMacroDistribution()
  {
    return macroDistribution;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setMeals(Meals aMeals)
  {
    boolean wasSet = false;
    //Must provide meals to foodItem
    if (aMeals == null)
    {
      return wasSet;
    }

    if (meals != null && meals.numberOfFoodItems() <= Meals.minimumNumberOfFoodItems())
    {
      return wasSet;
    }

    Meals existingMeals = meals;
    meals = aMeals;
    if (existingMeals != null && !existingMeals.equals(aMeals))
    {
      boolean didRemove = existingMeals.removeFoodItem(this);
      if (!didRemove)
      {
        meals = existingMeals;
        return wasSet;
      }
    }
    meals.addFoodItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Meals placeholderMeals = meals;
    this.meals = null;
    if(placeholderMeals != null)
    {
      placeholderMeals.removeFoodItem(this);
    }
    MacroDistribution existingMacroDistribution = macroDistribution;
    macroDistribution = null;
    if (existingMacroDistribution != null)
    {
      existingMacroDistribution.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calories" + ":" + getCalories()+ "," +
            "portionSize" + ":" + getPortionSize()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "meals = "+(getMeals()!=null?Integer.toHexString(System.identityHashCode(getMeals())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "macroDistribution = "+(getMacroDistribution()!=null?Integer.toHexString(System.identityHashCode(getMacroDistribution())):"null");
  }
}