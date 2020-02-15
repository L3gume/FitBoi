package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

// line 37 "model.ump"
// line 92 "model.ump"
@Entity
public class Goal
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Goal Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
  private int baseCalories;
  private boolean result;
  private Date startDate;
  private float weight;
  
  @Enumerated(EnumType.STRING)
  private ActivityLevel activityLevel;

  //Goal Associations
  @OneToOne(cascade={CascadeType.ALL})
  private MacroDistribution macroDistribution;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Goal(int aBaseCalories, boolean aResult, Date aStartDate, float aWeight, ActivityLevel aActivityLevel, MacroDistribution aMacroDistribution)
  {
    baseCalories = aBaseCalories;
    result = aResult;
    startDate = aStartDate;
    weight = aWeight;
    activityLevel = aActivityLevel;
    if (aMacroDistribution == null)
    {
      throw new RuntimeException("Unable to create Goal due to aMacroDistribution. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    macroDistribution = aMacroDistribution;
  }

  public Goal(int aBaseCalories, boolean aResult, Date aStartDate, float aWeight, ActivityLevel aActivityLevel, float aFatsForMacroDistribution, float aCarbsForMacroDistribution, float aProteinForMacroDistribution, FoodItem aFoodItemForMacroDistribution)
  {
    baseCalories = aBaseCalories;
    result = aResult;
    startDate = aStartDate;
    weight = aWeight;
    activityLevel = aActivityLevel;
    macroDistribution = new MacroDistribution(aFatsForMacroDistribution, aCarbsForMacroDistribution, aProteinForMacroDistribution);
  }
  
  public Goal()
  {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId(){
    return this.id;
  }

  public boolean setBaseCalories(int aBaseCalories)
  {
    boolean wasSet = false;
    baseCalories = aBaseCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setResult(boolean aResult)
  {
    boolean wasSet = false;
    result = aResult;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
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

  public boolean setActivityLevel(ActivityLevel aActivityLevel)
  {
    boolean wasSet = false;
    activityLevel = aActivityLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setMacroDistribution(MacroDistribution aMacroDistribution){
    boolean wasSet = false;
    macroDistribution = aMacroDistribution;
    wasSet = true;
    return wasSet;

  }

  public int getBaseCalories()
  {
    return baseCalories;
  }

  public boolean getResult()
  {
    return result;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public float getWeight()
  {
    return weight;
  }

  @Enumerated
  public ActivityLevel getActivityLevel()
  {
    return activityLevel;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isResult()
  {
    return result;
  }
  /* Code from template association_GetOne */
  @OneToOne(cascade={CascadeType.ALL})
  public MacroDistribution getMacroDistribution()
  {
    return macroDistribution;
  }
  /* Code from template association_SetOneToMany */

  public void delete()
  {
    MacroDistribution existingMacroDistribution = macroDistribution;
    macroDistribution = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "baseCalories" + ":" + getBaseCalories()+ "," +
            "result" + ":" + getResult()+ "," +
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "activityLevel" + "=" + (getActivityLevel() != null ? !getActivityLevel().equals(this)  ? getActivityLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "macroDistribution = "+(getMacroDistribution()!=null?Integer.toHexString(System.identityHashCode(getMacroDistribution())):"null");
  }
}