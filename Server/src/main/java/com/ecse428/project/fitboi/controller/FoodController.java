package com.ecse428.project.fitboi.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.dto.DBFoodDto;
import com.ecse428.project.fitboi.model.DBFood;
import com.ecse428.project.fitboi.service.FoodService;

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


@RestController
@RequestMapping("/food/")
public class FoodController {
    @Autowired
    private FoodService foodService;

    /**
     * GET
     * /food/{foodName}/ -> returns a list of food in the food DB that have that exact name
     * @param foodName
     * @return
     */
    @GetMapping("{foodName}")
    public ResponseEntity<?> getUser(@PathVariable String foodName) {
        List<DBFood> foodList = foodService.getDBFoodFromName(foodName);
        List<DBFoodDto> foodDtoList = new ArrayList<DBFoodDto>();
        for(DBFood food : foodList)
        {
            foodDtoList.add(converToDto(food));
        }

    	return new ResponseEntity<List<DBFoodDto>>(foodDtoList, HttpStatus.OK);
    }


    public DBFoodDto converToDto(DBFood food)
    {
        return new DBFoodDto(food.getName(), food.getCal(), food.getProtein(), food.getFat(),
         food.getCarbs());
    }
}
