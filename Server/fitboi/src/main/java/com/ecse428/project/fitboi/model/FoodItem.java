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
  private MacroDistribution macroDistribution;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FoodItem(String aName, int aCalories, int aPortionSize, MacroDistribution aMacroDistribution)
  {
    name = aName;
    calories = aCalories;
    portionSize = aPortionSize;
    if (aMacroDistribution == null)
    {
      throw new RuntimeException("Unable to create FoodItem due to aMacroDistribution. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    macroDistribution = aMacroDistribution;
  }

  public FoodItem(String aName, int aCalories, int aPortionSize, int aFatsForMacroDistribution, int aCarbsForMacroDistribution, int aProteinForMacroDistribution, Goal aGoalForMacroDistribution)
  {
    name = aName;
    calories = aCalories;
    portionSize = aPortionSize;
    macroDistribution = new MacroDistribution(aFatsForMacroDistribution, aCarbsForMacroDistribution, aProteinForMacroDistribution);
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
  public MacroDistribution getMacroDistribution()
  {
    return macroDistribution;
  }
  /* Code from template association_SetOneToMandatoryMany */

  public void delete()
  {
    MacroDistribution existingMacroDistribution = macroDistribution;
    macroDistribution = null;
  }

  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calories" + ":" + getCalories()+ "," +
            "portionSize" + ":" + getPortionSize()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "macroDistribution = "+(getMacroDistribution()!=null?Integer.toHexString(System.identityHashCode(getMacroDistribution())):"null");
  }
}