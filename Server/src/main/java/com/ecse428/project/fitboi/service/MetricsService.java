package com.ecse428.project.fitboi.service;

import java.util.List;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.repository.MetricRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetricsService {
    
	@Autowired
	MetricRepository metricsRepository;

	@Autowired
	UserService userService;

	@Transactional
	public Iterable<Metric> getAllMetrics() {
		return metricsRepository.findAll();
	}
	
	@Transactional
	public Metric getCurrentUserMetrics(String userEmail) {
		Iterable<Metric> metrics_list = userService.getAllUserMetrics(userEmail);

		return metrics_list.iterator().hasNext() ? 
			metrics_list.iterator().next() : null;
	}

	@Transactional
	public int getCurrentExerciseCount(String userEmail)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return -1;

		return cur_metrics.getExerciseSpending();
	}

	@Transactional 
	public Metric addExerciseCount(String userEmail, int cal)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;
		
		cur_metrics.setExerciseSpending(cal +
			cur_metrics.getExerciseSpending());
		metricsRepository.save(cur_metrics);

		return cur_metrics;
	}

	@Transactional 
	public Metric setExerciseCount(String userEmail, int cal)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;
		
		cur_metrics.setExerciseSpending(cal);
		metricsRepository.save(cur_metrics);

		return cur_metrics;
	}

	@Transactional
	public Metric getMetrics(int id) {
		return metricsRepository.findMetricsById(id);
	}


	@Transactional
	public boolean addNewMetrics(Metric metrics) {
		if (metricsRepository.existsById(metrics.getId())) {
			return false;
		}
		
		metricsRepository.save(metrics);
		return true;
	}

	@Transactional
	public boolean updateMetrics(Metric metrics) {
		if (metricsRepository.existsById(metrics.getId())) {
			metricsRepository.save(metrics);
			return true;
		}
		return false;
	}

	@Transactional
	public Metric deleteMetrics(int id) {
    	if (!metricsRepository.existsById(id)) {
    		return null;
    	}
    	Metric deletedMetrics = metricsRepository.findMetricsById(id);
    	metricsRepository.deleteById(id);
		return deletedMetrics;
	}

	@Transactional
	public List<Meal> getAllMeals(Metric metric) {
		if (metric == null) {
			return null;
		}
		return metric.getMeals();
	}

	@Transactional
	public Meal getUserMeal(Metric metric, int meal_id) {
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

	public Meal deleteMeal(Metric metric, int meal_id) {
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
