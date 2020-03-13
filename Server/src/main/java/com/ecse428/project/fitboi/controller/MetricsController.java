package com.ecse428.project.fitboi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.dto.MetricsDto;
import com.ecse428.project.fitboi.model.Metric;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.MetricsService;
import com.ecse428.project.fitboi.service.UserService;
import com.ecse428.project.repository.MetricRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/users/")
public class MetricsController {

    @Autowired
    private UserService userService;

    @Autowired
    private MetricsService metricsService;

        /**
     * POST
     * /users/{user_email}/addExercise/{cal} -> Adds cal to user's current exercise count
     * @param user_id
     * @return
     */
    @PostMapping("{user_email}/addExercise/{cal}")
    public ResponseEntity<?> addExerciseCount(@PathVariable String user_email,
        @PathVariable int cal)
    {
        Metric curMetrics = metricsService.addExerciseCount(user_email, cal);
        MetricsDto curMetricsDto = convertToDto(curMetrics);
        HttpStatus status = curMetrics == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<MetricsDto>(curMetricsDto, status);
    }

    /**
     * POST
     * /users/{user_email}/addExercise/{cal} -> Adds cal to user's current exercise count
     * @param user_id
     * @return
     */
    @PostMapping("{user_email}/setExercise/{cal}")
    public ResponseEntity<?> setExerciseCount(@PathVariable String user_email,
        @PathVariable int cal)
    {
        Metric curMetrics = metricsService.setExerciseCount(user_email, cal);
        MetricsDto curMetricsDto = convertToDto(curMetrics);
        HttpStatus status = curMetrics == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<MetricsDto>(curMetricsDto, status);
    }

    /**
     * GET
     * 
     * /users/{user_email}/curExercise
     * 
     * @param user_email
     * @return
     */
    @GetMapping("{user_email}/curExercise")
    public ResponseEntity<?> getExerciseCount(@PathVariable String user_email)
    {
        int exerciseCount = metricsService.getCurrentExerciseCount(user_email);
        return new ResponseEntity<Integer>(exerciseCount, HttpStatus.OK);
    }

    /**
     * POST
     * /users/{user_id}/metrics -> Adds a metric to the user
     * @param user_id
     * @return
     */
    @PostMapping("{user_id}/metrics")
    public ResponseEntity<?> addMetric(@PathVariable String user_id, @RequestBody ObjectNode objectNode)
    {
        // Get the user from the DB
        UserProfile user = userService.getUser(user_id);
        if (user == null) {
            return new ResponseEntity<String>("The user does not exist", HttpStatus.NOT_ACCEPTABLE);
        }

        // Extract the information from the body
        Date date = Date.valueOf(objectNode.get("date").asText());
        int exercise = objectNode.get("exerciseSpending").asInt();
        
        // Create the new metric and add it to the user
        Metric metric = new Metric(date, exercise);
        user.addMetric(metric);
        
        // Persist the user and the new metric
        userService.updateUser(user);

        // Convert to DTO
        MetricsDto metricsDto = convertToDto(metric);

    	return new ResponseEntity<MetricsDto>(metricsDto, HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics -> returns all the metrics for a user
     * @param user_id
     * @return
     */
    @GetMapping("{user_id}/metrics")
    public ResponseEntity<?> getAllUserMetrics(@PathVariable String user_id)
    {

        // Get the user from the DB
        UserProfile user = userService.getUser(user_id);
        if (user == null) {
            return new ResponseEntity<String>("The user does not exist", HttpStatus.NOT_ACCEPTABLE);
        }

        // Get all the users metrics and convert to DTO
        List<MetricsDto> metricsDto = new ArrayList<MetricsDto>();
        for (Metric metrics : user.getMetrics()) {
            metricsDto.add(convertToDto(metrics));
        }

    	return new ResponseEntity<List<MetricsDto>>(metricsDto, HttpStatus.OK);
    }

    /**
     * GET
     * /users/{user_id}/metrics/{metric_id} -> returns all the metric with the given metric_id for the user
     * @param user_id
     * @param metric_id
     * @return
     */
    @GetMapping("{user_id}/metrics/{metric_id}")
    public ResponseEntity<?> getUserMetrics(@PathVariable String user_id, @PathVariable int metric_id)
    {
        // Get the metric from the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user does not have a metric with the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

    	return new ResponseEntity<MetricsDto>(convertToDto(metric), HttpStatus.OK);
    }

    /**
     * GET
     * 
     * /users/{user_email}/metrics/current
     */
    @GetMapping("{user_email}/metrics/current")
    public ResponseEntity<?> getCurUserMetrics(@PathVariable String user_email)
    {
        Metric metric = metricsService.getCurrentUserMetrics(user_email);
        return new ResponseEntity<MetricsDto>(convertToDto(metric), HttpStatus.OK);
    }

    /**
     * POST
     * 
     * users/metrics/{metric_id}
    */
    @PutMapping("metrics/{metric_id}")
    public ResponseEntity<?> updateMetric(@PathVariable String metric_id, @RequestBody ObjectNode objectNode)
    {
        // Extract the information from the body
        Date date = Date.valueOf(objectNode.get("date").asText());
        int exercise = objectNode.get("exerciseSpending").asInt();
        
        // Create the new metric and add it to the user
        Metric metric = metricsService.getMetrics(Integer.parseInt(metric_id));
        metric.setDate(date);
        metric.setExerciseSpending(exercise);
        metricsService.updateMetrics(metric);

        // Convert to DTO
        MetricsDto metricsDto = convertToDto(metric);

    	return new ResponseEntity<MetricsDto>(metricsDto, HttpStatus.OK);
    }

    /**
     * DELETE
     * /users/{user_id}/metrics/{metrics_id}-> deletes a Metric from the DB
     * @param user_id 
     * @param metrics_id
     * @return
     */
    @DeleteMapping("{user_id}/metrics/{metric_id}")
    public ResponseEntity<?> deleteMetrics(@PathVariable String user_id, @PathVariable int metric_id) 
    {
        // Get the metric from the user
        Metric metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user does not have a metric with the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Delete the metric from the DB
        metric = userService.deleteMetrics(user_id,metric_id);

        return new ResponseEntity<MetricsDto>(convertToDto(metric), HttpStatus.OK);


    }

    private MetricsDto convertToDto(Metric metrics) {
    	return new MetricsDto(
            metrics.getId(),
            metrics.getDate().toString(),
            metrics.getExerciseSpending()	
    		);
    }
}
