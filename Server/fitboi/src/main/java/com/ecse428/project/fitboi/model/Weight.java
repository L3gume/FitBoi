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

  //Weight Associations
  private UserProfile userProfile;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weight(Date aDate, float aWeight, UserProfile aUserProfile)
  {
    date = aDate;
    weight = aWeight;
    boolean didAddUserProfile = setUserProfile(aUserProfile);
    if (!didAddUserProfile)
    {
      throw new RuntimeException("Unable to create weight due to userProfile. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public UserProfile getUserProfile()
  {
    return userProfile;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setUserProfile(UserProfile aUserProfile)
  {
    boolean wasSet = false;
    //Must provide userProfile to weight
    if (aUserProfile == null)
    {
      return wasSet;
    }

    if (userProfile != null && userProfile.numberOfWeights() <= UserProfile.minimumNumberOfWeights())
    {
      return wasSet;
    }

    UserProfile existingUserProfile = userProfile;
    userProfile = aUserProfile;
    if (existingUserProfile != null && !existingUserProfile.equals(aUserProfile))
    {
      boolean didRemove = existingUserProfile.removeWeight(this);
      if (!didRemove)
      {
        userProfile = existingUserProfile;
        return wasSet;
      }
    }
    userProfile.addWeight(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    UserProfile placeholderUserProfile = userProfile;
    this.userProfile = null;
    if(placeholderUserProfile != null)
    {
      placeholderUserProfile.removeWeight(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userProfile = "+(getUserProfile()!=null?Integer.toHexString(System.identityHashCode(getUserProfile())):"null");
  }
}