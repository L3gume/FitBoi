package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

	
	private String userName;
	private String password;
	private Date dOB;
	private int height;

	
	@Enumerated(EnumType.STRING)
	private Sex biologicalSex;

	// UserProfile Associations
	private List<Weight> weights;
	private List<Metrics> metrics;
	private List<Footnote> footnotes;

	@OneToOne(cascade={CascadeType.ALL})
	private Goal goal;

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}

	public void setWeights(List<Weight> weights) {
		this.weights = weights;
	}

	public void setFootnotes(List<Footnote> footnotes){
		this.footnotes = footnotes;
	}


	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public UserProfile(String aEmail, String aName, String aUserName, String aPassword, Date adOB, int aHeight,
			Sex aBiologicalSex) {
		email = aEmail;
		name = aName;
		userName = aUserName;
		password = aPassword;
		dOB = adOB;
		height = aHeight;
		biologicalSex = aBiologicalSex;
		weights = new ArrayList<Weight>();
		metrics = new ArrayList<Metrics>();
		footnotes = new ArrayList<Footnote>();
		goal = new Goal();
	}
	
	public UserProfile() {
		goal = new Goal();
		weights = new ArrayList<Weight>();
		metrics = new ArrayList<Metrics>();
		footnotes = new ArrayList<Footnote>();
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

	public boolean setDOB(Date adOB) {
		boolean wasSet = false;
		dOB = adOB;
		wasSet = true;
		return wasSet;
	}

	public boolean setHeight(int aHeight) {
		boolean wasSet = false;
		height = aHeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setBiologicalSex(Sex aBiologicalSex) {
		boolean wasSet = false;
		biologicalSex = aBiologicalSex;
		wasSet = true;
		return wasSet;
	}

	public boolean setGoal(Goal goal) {
		boolean wasSet = false;
		this.goal = goal;
		wasSet = true;
		return wasSet;
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

	public Date getDOB() {
		return dOB;
	}

	public int getHeight() {
		return height;
	}

	@Enumerated
	public Sex getBiologicalSex() {
		return biologicalSex;
	}

	/* Code from template association_GetMany */
	public Footnote getFootnote(int index) {
		Footnote aFootnote = footnotes.get(index);
		return aFootnote;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public List<Footnote> getFootnotes() {
		return footnotes;
	}

	public int numberOfFootnotes() {
		int number = footnotes.size();
		return number;
	}

	public boolean hasFootnotes() {
		boolean has = footnotes.size() > 0;
		return has;
	}

	public int indexOfFootnote(Footnote aFootnote) {
		int index = footnotes.indexOf(aFootnote);
		return index;
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

	
  	/* Code from template association_GetOne */
	@OneToOne(cascade={CascadeType.ALL})
	public Goal getGoal() {
		return this.goal;
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

	public boolean removeGoal(){
		if (this.goal == null){
			return false;
		} 
		else {
			this.goal = null;
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
	public Goal setGoal(int aBaseCalories, boolean aResult, Date aStartDate, Date aEndDate, float aWeightGoal,
			ActivityLevel aActivityLevel, GoalType aGoalType, MacroDistribution aMacroDistribution) {
		return new Goal(aBaseCalories, aResult, aStartDate, aEndDate, aWeightGoal, aActivityLevel, aGoalType, aMacroDistribution);
	}


	public void delete() {
		for (int i = metrics.size(); i > 0; i--) {
			Metrics aMetric = metrics.get(i - 1);
			aMetric.delete();
		}
		goal.delete();
	}

	public String toString() {
		return super.toString() + "[" + "email" + ":" + getEmail() + "," + "name" + ":" + getName() + "," + "userName"
				+ ":" + getUserName() + "," + "password" + ":" + getPassword() + "," + "age" + ":" + getDOB() + ","
				+ "height" + ":" + getHeight() + "," + "biologicalSex" + ":" + getBiologicalSex() + "]";
	}
}