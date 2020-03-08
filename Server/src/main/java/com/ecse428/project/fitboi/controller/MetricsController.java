package com.ecse428.project.fitboi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.dto.MetricsDto;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.fitboi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/users/")
public class MetricsController {

    @Autowired
    private UserService userService;

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
        Metrics metric = new Metrics(date, exercise);
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
        for (Metrics metrics : user.getMetrics()) {
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
        Metrics metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user does not have a metric with the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

    	return new ResponseEntity<MetricsDto>(convertToDto(metric), HttpStatus.OK);
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
        Metrics metric = userService.getUserMetric(user_id, metric_id);
        if (metric == null) {
            return new ResponseEntity<String>("The user does not have a metric with the given metric_id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Delete the metric from the DB
        metric = userService.deleteMetrics(user_id,metric_id);

        return new ResponseEntity<MetricsDto>(convertToDto(metric), HttpStatus.OK);


    }

    private MetricsDto convertToDto(Metrics metrics) {
    	return new MetricsDto(
            metrics.getId(),
            metrics.getDate().toString(),
            metrics.getExerciseSpending()	
    		);
    }
}
