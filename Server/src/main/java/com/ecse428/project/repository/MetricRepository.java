package com.ecse428.project.repository;

import com.ecse428.project.fitboi.model.Metric;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends CrudRepository<Metric, Integer> {
	Metric findMetricsById(int id);
}
