package com.ecse428.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecse428.project.fitboi.model.Goal;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Integer> {
	Goal findGoalById(int id);
}
