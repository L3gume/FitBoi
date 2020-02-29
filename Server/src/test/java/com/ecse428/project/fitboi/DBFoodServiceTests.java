package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.ecse428.project.fitboi.model.DBFood;
import com.ecse428.project.fitboi.service.FoodService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class DBFoodServiceTests {
    
    // @Autowired
    // private FoodService foodService;

    // @Test
    // public void testKnownFoodFetch()
    // {
    //     List<DBFood> eggnog = foodService.getDBFoodFromName("EGGNOG");
    //     assertTrue(eggnog.size() > 0);
    // }
}
