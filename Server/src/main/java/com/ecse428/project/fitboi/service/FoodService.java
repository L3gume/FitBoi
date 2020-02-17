package com.ecse428.project.fitboi.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.model.DBFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    public List<DBFood> getDBFoodFromName(String name)
    {
        List<DBFood> all_food = FoodQueryManager.getDBFoodsWithName(name);
        List<DBFood> target_food = new ArrayList<DBFood>();

        // TODO Sprint 2: auto complete feature for name
        for(DBFood food : all_food)
        {
            if(food.getName().equals(name)) target_food.add(food);
        }

        return target_food;
    }
}
