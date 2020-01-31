package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 69 "model.ump"
public class UserProfile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserProfile Attributes
  private String email;
  private String name;
  private String userName;
  private String password;
  private int age;
  private int height;
  private boolean biologicalSex;

  //UserProfile Associations
  private List<Weight> weights;
  private List<Metrics> metrics;
  private List<Goal> goals;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserProfile(String aEmail, String aName, String aUserName, String aPassword, int aAge, int aHeight, boolean aBiologicalSex)
  {
    email = aEmail;
    name = aName;
    userName = aUserName;
    password = aPassword;
    age = aAge;
    height = aHeight;
    biologicalSex = aBiologicalSex;
    weights = new ArrayList<Weight>();
    metrics = new ArrayList<Metrics>();
    goals = new ArrayList<Goal>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    age = aAge;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setBiologicalSex(boolean aBiologicalSex)
  {
    boolean wasSet = false;
    biologicalSex = aBiologicalSex;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getName()
  {
    return name;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public int getAge()
  {
    return age;
  }

  public int getHeight()
  {
    return height;
  }

  public boolean getBiologicalSex()
  {
    return biologicalSex;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isBiologicalSex()
  {
    return biologicalSex;
  }
  /* Code from template association_GetMany */
  public Weight getWeight(int index)
  {
    Weight aWeight = weights.get(index);
    return aWeight;
  }

  public List<Weight> getWeights()
  {
    List<Weight> newWeights = Collections.unmodifiableList(weights);
    return newWeights;
  }

  public int numberOfWeights()
  {
    int number = weights.size();
    return number;
  }

  public boolean hasWeights()
  {
    boolean has = weights.size() > 0;
    return has;
  }

  public int indexOfWeight(Weight aWeight)
  {
    int index = weights.indexOf(aWeight);
    return index;
  }
  /* Code from template association_GetMany */
  public Metrics getMetric(int index)
  {
    Metrics aMetric = metrics.get(index);
    return aMetric;
  }

  public List<Metrics> getMetrics()
  {
    List<Metrics> newMetrics = Collections.unmodifiableList(metrics);
    return newMetrics;
  }

  public int numberOfMetrics()
  {
    int number = metrics.size();
    return number;
  }

  public boolean hasMetrics()
  {
    boolean has = metrics.size() > 0;
    return has;
  }

  public int indexOfMetric(Metrics aMetric)
  {
    int index = metrics.indexOf(aMetric);
    return index;
  }
  /* Code from template association_GetMany */
  public Goal getGoal(int index)
  {
    Goal aGoal = goals.get(index);
    return aGoal;
  }

  public List<Goal> getGoals()
  {
    List<Goal> newGoals = Collections.unmodifiableList(goals);
    return newGoals;
  }

  public int numberOfGoals()
  {
    int number = goals.size();
    return number;
  }

  public boolean hasGoals()
  {
    boolean has = goals.size() > 0;
    return has;
  }

  public int indexOfGoal(Goal aGoal)
  {
    int index = goals.indexOf(aGoal);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWeightsValid()
  {
    boolean isValid = numberOfWeights() >= minimumNumberOfWeights();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeights()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Weight addWeight(Date aDate, float aWeight)
  {
    Weight aNewWeight = new Weight(aDate, aWeight, this);
    return aNewWeight;
  }

  public boolean addWeight(Weight aWeight)
  {
    boolean wasAdded = false;
    if (weights.contains(aWeight)) { return false; }
    UserProfile existingUserProfile = aWeight.getUserProfile();
    boolean isNewUserProfile = existingUserProfile != null && !this.equals(existingUserProfile);

    if (isNewUserProfile && existingUserProfile.numberOfWeights() <= minimumNumberOfWeights())
    {
      return wasAdded;
    }
    if (isNewUserProfile)
    {
      aWeight.setUserProfile(this);
    }
    else
    {
      weights.add(aWeight);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeight(Weight aWeight)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeight, as it must always have a userProfile
    if (this.equals(aWeight.getUserProfile()))
    {
      return wasRemoved;
    }

    //userProfile already at minimum (1)
    if (numberOfWeights() <= minimumNumberOfWeights())
    {
      return wasRemoved;
    }

    weights.remove(aWeight);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeightAt(Weight aWeight, int index)
  {  
    boolean wasAdded = false;
    if(addWeight(aWeight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeights()) { index = numberOfWeights() - 1; }
      weights.remove(aWeight);
      weights.add(index, aWeight);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeightAt(Weight aWeight, int index)
  {
    boolean wasAdded = false;
    if(weights.contains(aWeight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeights()) { index = numberOfWeights() - 1; }
      weights.remove(aWeight);
      weights.add(index, aWeight);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeightAt(aWeight, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMetricsValid()
  {
    boolean isValid = numberOfMetrics() >= minimumNumberOfMetrics();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMetrics()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Metrics addMetric(Date aDate, int aExerciseSpending)
  {
    Metrics aNewMetric = new Metrics(aDate, aExerciseSpending, this);
    return aNewMetric;
  }

  public boolean addMetric(Metrics aMetric)
  {
    boolean wasAdded = false;
    if (metrics.contains(aMetric)) { return false; }
    UserProfile existingUserProfile = aMetric.getUserProfile();
    boolean isNewUserProfile = existingUserProfile != null && !this.equals(existingUserProfile);

    if (isNewUserProfile && existingUserProfile.numberOfMetrics() <= minimumNumberOfMetrics())
    {
      return wasAdded;
    }
    if (isNewUserProfile)
    {
      aMetric.setUserProfile(this);
    }
    else
    {
      metrics.add(aMetric);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMetric(Metrics aMetric)
  {
    boolean wasRemoved = false;
    //Unable to remove aMetric, as it must always have a userProfile
    if (this.equals(aMetric.getUserProfile()))
    {
      return wasRemoved;
    }

    //userProfile already at minimum (1)
    if (numberOfMetrics() <= minimumNumberOfMetrics())
    {
      return wasRemoved;
    }

    metrics.remove(aMetric);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMetricAt(Metrics aMetric, int index)
  {  
    boolean wasAdded = false;
    if(addMetric(aMetric))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMetrics()) { index = numberOfMetrics() - 1; }
      metrics.remove(aMetric);
      metrics.add(index, aMetric);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMetricAt(Metrics aMetric, int index)
  {
    boolean wasAdded = false;
    if(metrics.contains(aMetric))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMetrics()) { index = numberOfMetrics() - 1; }
      metrics.remove(aMetric);
      metrics.add(index, aMetric);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMetricAt(aMetric, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGoals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Goal addGoal(int aBaseCalories, boolean aResult, Date aStartDate, float aWeight, ActivityLevel aActivityLevel, MacroDistribution aMacroDistribution)
  {
    return new Goal(aBaseCalories, aResult, aStartDate, aWeight, aActivityLevel, this, aMacroDistribution);
  }

  public boolean addGoal(Goal aGoal)
  {
    boolean wasAdded = false;
    if (goals.contains(aGoal)) { return false; }
    UserProfile existingUserProfile = aGoal.getUserProfile();
    boolean isNewUserProfile = existingUserProfile != null && !this.equals(existingUserProfile);
    if (isNewUserProfile)
    {
      aGoal.setUserProfile(this);
    }
    else
    {
      goals.add(aGoal);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGoal(Goal aGoal)
  {
    boolean wasRemoved = false;
    //Unable to remove aGoal, as it must always have a userProfile
    if (!this.equals(aGoal.getUserProfile()))
    {
      goals.remove(aGoal);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGoalAt(Goal aGoal, int index)
  {  
    boolean wasAdded = false;
    if(addGoal(aGoal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGoals()) { index = numberOfGoals() - 1; }
      goals.remove(aGoal);
      goals.add(index, aGoal);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGoalAt(Goal aGoal, int index)
  {
    boolean wasAdded = false;
    if(goals.contains(aGoal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGoals()) { index = numberOfGoals() - 1; }
      goals.remove(aGoal);
      goals.add(index, aGoal);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGoalAt(aGoal, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=weights.size(); i > 0; i--)
    {
      Weight aWeight = weights.get(i - 1);
      aWeight.delete();
    }
    for(int i=metrics.size(); i > 0; i--)
    {
      Metrics aMetric = metrics.get(i - 1);
      aMetric.delete();
    }
    for(int i=goals.size(); i > 0; i--)
    {
      Goal aGoal = goals.get(i - 1);
      aGoal.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "userName" + ":" + getUserName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "age" + ":" + getAge()+ "," +
            "height" + ":" + getHeight()+ "," +
            "biologicalSex" + ":" + getBiologicalSex()+ "]";
  }
}