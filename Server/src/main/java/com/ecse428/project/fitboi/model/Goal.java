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
  private Date endDate;
  private float weightGoal;
  
  @Enumerated(EnumType.STRING)
  private ActivityLevel activityLevel;

  @Enumerated(EnumType.STRING)
  private GoalType goalType;

  //Goal Associations
  @OneToOne(cascade={CascadeType.ALL})
  private MacroDistribution macroDistribution;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Goal(int aBaseCalories, boolean aResult, Date aStartDate, Date aEndDate, float aWeightGoal, ActivityLevel aActivityLevel, GoalType aGoalType, MacroDistribution aMacroDistribution)
  {
    baseCalories = aBaseCalories;
    result = aResult;
    startDate = aStartDate;
    endDate = aEndDate;
    weightGoal = aWeightGoal;
    activityLevel = aActivityLevel;
    goalType = aGoalType;
    if (aMacroDistribution == null)
    {
      throw new RuntimeException("Unable to create Goal due to aMacroDistribution. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    macroDistribution = aMacroDistribution;
  }

  public Goal(int aBaseCalories, boolean aResult, Date aStartDate, Date aEndDate, float aWeightGoal, ActivityLevel aActivityLevel, GoalType aGoalType, float aFatsForMacroDistribution, float aCarbsForMacroDistribution, float aProteinForMacroDistribution)
  {
    baseCalories = aBaseCalories;
    result = aResult;
    startDate = aStartDate;
    endDate = aEndDate;
    weightGoal = aWeightGoal;
    activityLevel = aActivityLevel;
    goalType = aGoalType;
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

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeightGoal(float aWeightGoal)
  {
    boolean wasSet = false;
    weightGoal = aWeightGoal;
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

  public boolean setGoalType(GoalType aGoalType)
  {
    boolean wasSet = false;
    goalType = aGoalType;
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

  public Date getEndDate()
  {
    return endDate;
  }

  public float getWeightGoal()
  {
    return weightGoal;
  }

  @Enumerated
  public ActivityLevel getActivityLevel()
  {
    return activityLevel;
  }

  @Enumerated
  public GoalType getGoalType()
  {
    return goalType;
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

  //@OneToOne(cascade = {CascadeType.ALL})
  public void setMacroDistribution(MacroDistribution aMacroDistribution){
    this.macroDistribution = aMacroDistribution;
  }
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
            "weight" + ":" + getWeightGoal()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "activityLevel" + "=" + (getActivityLevel() != null ? !getActivityLevel().equals(this)  ? getActivityLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "goalType" + "=" + (getGoalType() != null ? !getGoalType().equals(this)  ? getGoalType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "macroDistribution = "+(getMacroDistribution()!=null?Integer.toHexString(System.identityHashCode(getMacroDistribution())):"null");
  }
}
