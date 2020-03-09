package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.fitboi.service.MetricsService;
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
	private MetricsService metricsService;
    
    @MockBean
	private MetricsRepository mockRepository;
    
    @Test
	public void testAddMetricsSuccess(){
		Metrics tMetrics = new Metrics(new Date(0), 3);
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricsService.addNewMetrics(tMetrics);
		assertTrue(addStatus);
	}
    
    @Test
	public void testAddMetricsFailure(){
		Metrics tMetrics = new Metrics(new Date(0), 3);
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricsService.addNewMetrics(tMetrics);
		assertTrue(!addStatus);
    }
    
    @Test
	public void testGetAllMetrics(){
        Metrics tMetrics = new Metrics(new Date(0), 3);
        List<Metrics> metrics = new ArrayList<>();
        metrics.add(tMetrics);
		when(mockRepository.findAll()).thenReturn(metrics);
        List<Metrics> results = new ArrayList<>();
        for(Metrics m : metricsService.getAllMetrics()){
            results.add(m);
        }
		assertTrue(!results.isEmpty());
    }
    
    @Test
	public void testGetMetrics(){
        Metrics tMetrics = new Metrics(new Date(0), 3);
        when(mockRepository.findMetricsById(anyInt())).thenReturn(tMetrics);
		assertTrue(tMetrics.getDate().equals(metricsService.getMetrics(tMetrics.getId()).getDate()));
    }
    
    @Test
	public void testUpdateMetricsSuccess(){
		Metrics tMetrics = new Metrics(new Date(0), 3);
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricsService.updateMetrics(tMetrics);
		assertTrue(addStatus);
	}
    
    @Test
	public void testUpdaeMetricsFailure(){
		Metrics tMetrics = new Metrics(new Date(0), 3);
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMetrics);
		boolean addStatus = metricsService.updateMetrics(tMetrics);
		assertTrue(!addStatus);
    }

    @Test
    public void testGetAllMeals(){
        Metrics tMetrics = new Metrics(new Date(0), 3);
        tMetrics.addMeal(new Meal());
        assertTrue(!metricsService.getAllMeals(tMetrics).isEmpty());
    }

    @Test
    public void testGetuserMeal(){
        Metrics tMetrics = new Metrics(new Date(0), 3);
        Meal tMeal = new Meal();
        tMetrics.addMeal(tMeal);
        assertTrue(metricsService.getUserMeal(tMetrics, tMeal.getId()).equals(tMeal));
    }
}