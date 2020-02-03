package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.*;

//------------------------
// ENUMERATIONS
//------------------------

enum MealType { BreakFast, Lunch, Dinner }

// line 27 "model.ump"
// line 86 "model.ump"
public class Meals
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Meals Attributes
  private MealType mealType;

  //Meals Associations
  private List<FoodItem> foodItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Meals(MealType aMealType)
  {
    mealType = aMealType;
    foodItems = new ArrayList<FoodItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMealType(MealType aMealType)
  {
    boolean wasSet = false;
    mealType = aMealType;
    wasSet = true;
    return wasSet;
  }

  public MealType getMealType()
  {
    return mealType;
  }
  /* Code from template association_GetMany */
  public FoodItem getFoodItem(int index)
  {
    FoodItem aFoodItem = foodItems.get(index);
    return aFoodItem;
  }

  public List<FoodItem> getFoodItems()
  {
    List<FoodItem> newFoodItems = Collections.unmodifiableList(foodItems);
    return newFoodItems;
  }

  public int numberOfFoodItems()
  {
    int number = foodItems.size();
    return number;
  }

  public boolean hasFoodItems()
  {
    boolean has = foodItems.size() > 0;
    return has;
  }

  public int indexOfFoodItem(FoodItem aFoodItem)
  {
    int index = foodItems.indexOf(aFoodItem);
    return index;
  }

  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfFoodItemsValid()
  {
    boolean isValid = numberOfFoodItems() >= minimumNumberOfFoodItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFoodItems()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public FoodItem addFoodItem(String aName, int aCalories, int aPortionSize, MacroDistribution aMacroDistribution)
  {
    FoodItem aNewFoodItem = new FoodItem(aName, aCalories, aPortionSize, aMacroDistribution);
    return aNewFoodItem;
  }

  public boolean addFoodItem(FoodItem aFoodItem)
  {
    boolean wasAdded = false;
    if (foodItems.contains(aFoodItem)) {
      return false;
    }
    else{
      foodItems.add(aFoodItem);
      return true;
    }
  }

  public boolean removeFoodItem(FoodItem aFoodItem)
  {
    if(!foodItems.contains(aFoodItem)){
      return false;
    }
    else{
      foodItems.remove(aFoodItem);
      return true;
    }
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFoodItemAt(FoodItem aFoodItem, int index)
  {  
    boolean wasAdded = false;
    if(addFoodItem(aFoodItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodItems()) { index = numberOfFoodItems() - 1; }
      foodItems.remove(aFoodItem);
      foodItems.add(index, aFoodItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodItemAt(FoodItem aFoodItem, int index)
  {
    boolean wasAdded = false;
    if(foodItems.contains(aFoodItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodItems()) { index = numberOfFoodItems() - 1; }
      foodItems.remove(aFoodItem);
      foodItems.add(index, aFoodItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodItemAt(aFoodItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=foodItems.size(); i > 0; i--)
    {
      FoodItem aFoodItem = foodItems.get(i - 1);
      aFoodItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mealType" + "=" +
            (getMealType() != null ? !getMealType().equals(this)  ? getMealType().toString().replaceAll("  ","    ") : "this" : "null") +
            System.getProperties().getProperty("line.separator");
  }
}