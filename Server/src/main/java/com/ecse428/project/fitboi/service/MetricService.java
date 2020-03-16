package com.ecse428.project.fitboi.service;

import java.util.List;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.repository.MetricRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetricService {
    
	@Autowired
	MetricRepository metricRepository;

	@Autowired
	UserService userService;

	@Transactional
	public Iterable<Metric> getAllMetrics() {
		return metricRepository.findAll();
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
	public String getCurrentFootNote(String userEmail)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;

		return cur_metrics.getFootNote();
	}

	@Transactional 
	public Metric addExerciseCount(String userEmail, int cal)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;
		
		cur_metrics.setExerciseSpending(cal +
			cur_metrics.getExerciseSpending());
		metricRepository.save(cur_metrics);

		return cur_metrics;
	}

	@Transactional 
	public Metric setExerciseCount(String userEmail, int cal)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;
		
		cur_metrics.setExerciseSpending(cal);
		metricRepository.save(cur_metrics);

		return cur_metrics;
	}

	@Transactional 
	public Metric setFootNote(String userEmail, String footNote)
	{
		Metric cur_metrics = getCurrentUserMetrics(userEmail);
		if(cur_metrics == null) return null;
		
		cur_metrics.setFootNote(footNote);
		metricRepository.save(cur_metrics);

		return cur_metrics;
	}

	@Transactional
	public Metric getMetric(int id) {
		return metricRepository.findMetricById(id);
	}


	@Transactional
	public boolean addNewMetric(Metric metric) {
		if (metricRepository.existsById(metric.getId())) {
			return false;
		}
		
		metricRepository.save(metric);
		return true;
	}

	@Transactional
	public boolean updateMetric(Metric metric) {
		if (metricRepository.existsById(metric.getId())) {
			metricRepository.save(metric);
			return true;
		}
		return false;
	}

	@Transactional
	public Metric deleteMetric(int id) {
    	if (!metricRepository.existsById(id)) {
    		return null;
    	}
    	Metric deletedMetric = metricRepository.findMetricById(id);
    	metricRepository.deleteById(id);
		return deletedMetric;
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
					metricRepository.save(metric);
					return meal;
				}
			}
		}
		return null;
	}

}
