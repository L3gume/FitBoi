package com.ecse428.project.fitboi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/



// line 55 "model.ump"
// line 104 "model.ump"
@Entity
public class FoodItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodItem Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  
  private String name;
  private int calories;
  private float portionSize;

  //FoodItem Associations
  @OneToOne(cascade={CascadeType.ALL})
  private MacroDistribution macroDistribution;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FoodItem(String aName, int aCalories, float aPortionSize, MacroDistribution aMacroDistribution)
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

  public FoodItem(String aName, int aCalories, float aPortionSize, float aFatsForMacroDistribution, float aCarbsForMacroDistribution, float aProteinForMacroDistribution, Goal aGoalForMacroDistribution)
  {
    name = aName;
    calories = aCalories;
    portionSize = aPortionSize;
    macroDistribution = new MacroDistribution(aFatsForMacroDistribution, aCarbsForMacroDistribution, aProteinForMacroDistribution);
  }
  
  public FoodItem()
  {
	  
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

  public boolean setPortionSize(float aPortionSize)
  {
    boolean wasSet = false;
    portionSize = aPortionSize;
    wasSet = true;
    return wasSet;
  }

  public int getId() {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public int getCalories()
  {
    return calories;
  }

  public float getPortionSize()
  {
    return portionSize;
  }
  /* Code from template association_GetOne */
  @OneToOne(cascade={CascadeType.ALL})
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
