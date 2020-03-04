package com.ecse428.project.fitboi.service;

import java.util.List;

import com.ecse428.project.fitboi.model.DBFood;
import com.ecse428.project.fitboi.model.Meal;

import org.springframework.stereotype.Service;

@Service
public class FoodService {
    public List<DBFood> getDBFoodFromName(String name)
    {
        List<DBFood> all_food = FoodQueryManager.getDBFoodsWithName(name);

        return all_food;
    }


    public boolean setMealFoods(Meal meal) {




        return true;
    }
}
