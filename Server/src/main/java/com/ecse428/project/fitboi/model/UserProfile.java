package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.sql.Date;

// line 2 "model.ump"
// line 69 "model.ump"
@Entity
public class UserProfile {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// UserProfile Attributes
	private String email;
	private String name;

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}

	private String userName;
	private String password;
	private int age;
	private int height;

	public void setWeights(List<Weight> weights) {
		this.weights = weights;
	}

	private boolean biologicalSex;

	// UserProfile Associations
	private List<Weight> weights;
	private List<Metrics> metrics;
	private List<Goal> goals;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public UserProfile(String aEmail, String aName, String aUserName, String aPassword, int aAge, int aHeight,
			boolean aBiologicalSex) {
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
	
	public UserProfile() {
		goals = new ArrayList<Goal>();
		weights = new ArrayList<Weight>();
		metrics = new ArrayList<Metrics>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setEmail(String aEmail) {
		boolean wasSet = false;
		email = aEmail;
		wasSet = true;
		return wasSet;
	}

	public boolean setName(String aName) {
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public boolean setUserName(String aUserName) {
		boolean wasSet = false;
		userName = aUserName;
		wasSet = true;
		return wasSet;
	}

	public boolean setPassword(String aPassword) {
		boolean wasSet = false;
		password = aPassword;
		wasSet = true;
		return wasSet;
	}

	public boolean setAge(int aAge) {
		boolean wasSet = false;
		age = aAge;
		wasSet = true;
		return wasSet;
	}

	public boolean setHeight(int aHeight) {
		boolean wasSet = false;
		height = aHeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setBiologicalSex(boolean aBiologicalSex) {
		boolean wasSet = false;
		biologicalSex = aBiologicalSex;
		wasSet = true;
		return wasSet;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	@Id
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public boolean getBiologicalSex() {
		return biologicalSex;
	}

	/* Code from template association_GetMany */
	public Weight getWeight(int index) {
		Weight aWeight = weights.get(index);
		return aWeight;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public List<Weight> getWeights() {
		return weights;
	}

	public int numberOfWeights() {
		int number = weights.size();
		return number;
	}

	public boolean hasWeights() {
		boolean has = weights.size() > 0;
		return has;
	}

	public int indexOfWeight(Weight aWeight) {
		int index = weights.indexOf(aWeight);
		return index;
	}

	/* Code from template association_GetMany */
	public Metrics getMetric(int index) {
		Metrics aMetric = metrics.get(index);
		return aMetric;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public List<Metrics> getMetrics() {
		return metrics;
	}

	public int numberOfMetrics() {
		int number = metrics.size();
		return number;
	}

	public boolean hasMetrics() {
		boolean has = metrics.size() > 0;
		return has;
	}

	public int indexOfMetric(Metrics aMetric) {
		int index = metrics.indexOf(aMetric);
		return index;
	}

	/* Code from template association_GetMany */
	//@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Goal getGoal(int index) {
		Goal aGoal = goals.get(index);
		return aGoal;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public List<Goal> getGoals() {
		return goals;
	}

	public int numberOfGoals() {
		int number = goals.size();
		return number;
	}

	public boolean hasGoals() {
		boolean has = goals.size() > 0;
		return has;
	}

	public int indexOfGoal(Goal aGoal) {
		int index = goals.indexOf(aGoal);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfWeights() {
		return 1;
	}

	/* Code from template association_AddMandatoryManyToOne */
	public Weight addWeight(Date aDate, float aWeight) {
		Weight aNewWeight = new Weight(aDate, aWeight);
		return aNewWeight;
	}

	public boolean addWeight(Weight aWeight) {
		if (weights.contains(aWeight)) {
			return false;
		} else {
			weights.add(aWeight);
			return true;
		}
	}

	public boolean removeWeight(Weight aWeight) {
		if (!weights.contains(aWeight)) {
			return false;
		} else {
			weights.remove(aWeight);
			return true;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addWeightAt(Weight aWeight, int index) {
		boolean wasAdded = false;
		if (addWeight(aWeight)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfWeights()) {
				index = numberOfWeights() - 1;
			}
			weights.remove(aWeight);
			weights.add(index, aWeight);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveWeightAt(Weight aWeight, int index) {
		boolean wasAdded = false;
		if (weights.contains(aWeight)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfWeights()) {
				index = numberOfWeights() - 1;
			}
			weights.remove(aWeight);
			weights.add(index, aWeight);
			wasAdded = true;
		} else {
			wasAdded = addWeightAt(aWeight, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfMetrics() {
		return 1;
	}

	/* Code from template association_AddMandatoryManyToOne */
	public Metrics addMetric(Date aDate, int aExerciseSpending) {
		Metrics aNewMetric = new Metrics(aDate, aExerciseSpending);
		return aNewMetric;
	}

	public boolean addMetric(Metrics aMetric) {
		if (metrics.contains(aMetric)) {
			return false;
		} else {
			metrics.add(aMetric);
			return true;
		}
	}

	public boolean removeMetric(Metrics aMetric) {
		if (!metrics.contains(aMetric)) {
			return false;
		} else {
			metrics.remove(aMetric);
			return true;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addMetricAt(Metrics aMetric, int index) {
		boolean wasAdded = false;
		if (addMetric(aMetric)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfMetrics()) {
				index = numberOfMetrics() - 1;
			}
			metrics.remove(aMetric);
			metrics.add(index, aMetric);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveMetricAt(Metrics aMetric, int index) {
		boolean wasAdded = false;
		if (metrics.contains(aMetric)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfMetrics()) {
				index = numberOfMetrics() - 1;
			}
			metrics.remove(aMetric);
			metrics.add(index, aMetric);
			wasAdded = true;
		} else {
			wasAdded = addMetricAt(aMetric, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfGoals() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Goal addGoal(int aBaseCalories, boolean aResult, Date aStartDate, float aWeight,
			ActivityLevel aActivityLevel, MacroDistribution aMacroDistribution) {
		return new Goal(aBaseCalories, aResult, aStartDate, aWeight, aActivityLevel, aMacroDistribution);
	}

	public boolean addGoal(Goal aGoal) {
		if (goals.contains(aGoal)) {
			return false;
		} else {
			goals.add(aGoal);
			return true;
		}
	}

	public boolean removeGoal(Goal aGoal) {
		if (!goals.contains(aGoal)) {
			return false;
		} else {
			goals.remove(aGoal);
			return true;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addGoalAt(Goal aGoal, int index) {
		boolean wasAdded = false;
		if (addGoal(aGoal)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfGoals()) {
				index = numberOfGoals() - 1;
			}
			goals.remove(aGoal);
			goals.add(index, aGoal);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveGoalAt(Goal aGoal, int index) {
		boolean wasAdded = false;
		if (goals.contains(aGoal)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfGoals()) {
				index = numberOfGoals() - 1;
			}
			goals.remove(aGoal);
			goals.add(index, aGoal);
			wasAdded = true;
		} else {
			wasAdded = addGoalAt(aGoal, index);
		}
		return wasAdded;
	}

	public void delete() {
		for (int i = metrics.size(); i > 0; i--) {
			Metrics aMetric = metrics.get(i - 1);
			aMetric.delete();
		}
		for (int i = goals.size(); i > 0; i--) {
			Goal aGoal = goals.get(i - 1);
			aGoal.delete();
		}
	}

	public String toString() {
		return super.toString() + "[" + "email" + ":" + getEmail() + "," + "name" + ":" + getName() + "," + "userName"
				+ ":" + getUserName() + "," + "password" + ":" + getPassword() + "," + "age" + ":" + getAge() + ","
				+ "height" + ":" + getHeight() + "," + "biologicalSex" + ":" + getBiologicalSex() + "]";
	}
}