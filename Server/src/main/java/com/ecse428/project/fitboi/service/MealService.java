package com.ecse428.project.fitboi.service;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.repository.MealRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MealService {
    
	@Autowired
	MealRepository mealRepository;

	@Transactional
	public Iterable<Meal> getAllMeals() {
		return mealRepository.findAll();
	}


	@Transactional
	public Meal getMeal(int id) {
		return mealRepository.findMealById(id);
	}


	@Transactional
	public boolean addNewMeal(Meal meal) {
		if (mealRepository.existsById(meal.getId())) {
			return false;
		}
		
		mealRepository.save(meal);
		return true;
	}

	@Transactional
	public boolean updateMeal(Meal meal) {
		if (mealRepository.existsById(meal.getId())) {
			mealRepository.save(meal);
			return true;
		}
		return false;
	}

	@Transactional
	public Meal deleteMeal(int id) {
    	if (!mealRepository.existsById(id)) {
    		return null;
    	}
    	Meal deletedMeal = mealRepository.findMealById(id);
    	mealRepository.deleteById(id);
		return deletedMeal;
	}


}
