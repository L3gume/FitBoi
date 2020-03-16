package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.fitboi.service.MetricService;
import com.ecse428.project.repository.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MetricsServiceTests {
    
    @Autowired
	private MetricService metricService;
    
    @MockBean
	private MetricRepository mockRepository;
    
    @Test
	public void testAddMetricsSuccess(){
		Metric tMetrics = new Metric(new Date(0), 3, "Test note");
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricService.addNewMetric(tMetrics);
		assertTrue(addStatus);
	}
    
    @Test
	public void testAddMetricsFailure(){
		Metric tMetrics = new Metric(new Date(0), 3, "Test note");
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricService.addNewMetric(tMetrics);
		assertTrue(!addStatus);
    }
    
    @Test
	public void testGetAllMetrics(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");
        List<Metric> metrics = new ArrayList<>();
        metrics.add(tMetrics);
		when(mockRepository.findAll()).thenReturn(metrics);
        List<Metric> results = new ArrayList<>();
        for(Metric m : metricService.getAllMetrics()){
            results.add(m);
        }
		assertTrue(!results.isEmpty());
    }
    
    @Test
	public void testGetMetrics(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");
        when(mockRepository.findMetricById(anyInt())).thenReturn(tMetrics);
		assertTrue(tMetrics.getDate().equals(metricService.getMetric(tMetrics.getId()).getDate()));
    }
    
    @Test
	public void testUpdateMetricsSuccess(){
		Metric tMetrics = new Metric(new Date(0), 3, "Test note");
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricService.updateMetric(tMetrics);
		assertTrue(addStatus);
	}
    
    @Test
	public void testUpdaeMetricsFailure(){
		Metric tMetrics = new Metric(new Date(0), 3, "Test note");
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricService.updateMetric(tMetrics);
		assertTrue(!addStatus);
    }

    @Test
    public void testGetAllMeals(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");
        tMetrics.addMeal(new Meal());
        assertTrue(!metricService.getAllMeals(tMetrics).isEmpty());
    }

    @Test
    public void testGetuserMeal(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");
        Meal tMeal = new Meal();
        tMetrics.addMeal(tMeal);
        assertTrue(metricService.getUserMeal(tMetrics, tMeal.getId()).equals(tMeal));
    }

    @Test
	public void testDeleteMeal(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");
        Meal tMeal = new Meal();
        tMetrics.addMeal(tMeal);
        when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		Meal deleted = metricService.deleteMeal(tMetrics, tMeal.getId());
		assertTrue(deleted.getId() == tMeal.getId());

    }

    @Test
    public void testDeleteMetrics(){
        Metric tMetrics = new Metric(new Date(0), 3, "Test note");

		when(mockRepository.existsById(tMetrics.getId())).thenReturn(true);
		when(mockRepository.findMetricById(tMetrics.getId())).thenReturn(tMetrics);

		Metric deleted = metricService.deleteMetric(tMetrics.getId());
		assertTrue(deleted.getId() == tMetrics.getId());

    }
}