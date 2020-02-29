package com.ecse428.project.repository;

import com.ecse428.project.fitboi.model.Metrics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends CrudRepository<Metrics, Integer> {
	Metrics findMetricsById(int id);
}
