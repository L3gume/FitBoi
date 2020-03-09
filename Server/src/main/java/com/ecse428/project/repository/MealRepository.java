package com.ecse428.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecse428.project.fitboi.model.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {
	Meal findMealById(int id);
	Meal deleteById(int id);
}
