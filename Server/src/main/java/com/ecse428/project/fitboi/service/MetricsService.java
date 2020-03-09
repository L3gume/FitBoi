package com.ecse428.project.fitboi.service;

import java.util.List;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.repository.MetricsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetricsService {
    
	@Autowired
	MetricsRepository metricsRepository;

	@Transactional
	public Iterable<Metrics> getAllMetrics() {
		return metricsRepository.findAll();
	}

	@Transactional
	public Metrics getCurrentMetrics() {
		Iterable<Metrics> metrics_list = getAllMetrics();

		return metrics_list.iterator().hasNext() ? 
			metrics_list.iterator().next() : null;
	}

	@Transactional 
	public boolean addExerciseCount(int cal)
	{
		Metrics cur_metrics = getCurrentMetrics();
		if(cur_metrics == null) return false;
		
		cur_metrics.setExerciseSpending(cal +
			cur_metrics.getExerciseSpending());
		metricsRepository.save(cur_metrics);

		return true;
	}

	@Transactional 
	public boolean setExerciseCount(int cal)
	{
		Metrics cur_metrics = getCurrentMetrics();
		if(cur_metrics == null) return false;
		
		cur_metrics.setExerciseSpending(cal);
		metricsRepository.save(cur_metrics);

		return true;
	}

	@Transactional
	public Metrics getMetrics(int id) {
		return metricsRepository.findMetricsById(id);
	}


	@Transactional
	public boolean addNewMetrics(Metrics metrics) {
		if (metricsRepository.existsById(metrics.getId())) {
			return false;
		}
		
		metricsRepository.save(metrics);
		return true;
	}

	@Transactional
	public boolean updateMetrics(Metrics metrics) {
		if (metricsRepository.existsById(metrics.getId())) {
			metricsRepository.save(metrics);
			return true;
		}
		return false;
	}

	@Transactional
	public Metrics deleteMetrics(int id) {
    	if (!metricsRepository.existsById(id)) {
    		return null;
    	}
    	Metrics deletedMetrics = metricsRepository.findMetricsById(id);
    	metricsRepository.deleteById(id);
		return deletedMetrics;
	}

	@Transactional
	public List<Meal> getAllMeals(Metrics metric) {
		if (metric == null) {
			return null;
		}
		return metric.getMeals();
	}

	@Transactional
	public Meal getUserMeal(Metrics metric, int meal_id) {
		if (metric == null) {
			return null;
		}
		for (Meal meal : metric.getMeals()) {
			if (meal.getId() == meal_id) {
				return meal;
			}
		}
		return null;
	}

	public Meal deleteMeal(Metrics metric, int meal_id) {
		for (Meal meal : metric.getMeals()) {
			if (meal.getId() == meal_id) {
				if (metric.removeMeal(meal)) {
					metricsRepository.save(metric);
					return meal;
				}
			}
		}
		return null;
	}

}
