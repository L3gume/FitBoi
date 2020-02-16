package com.ecse428.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ecse428.project.fitboi.model.DBFood;

@Repository
public interface FoodRepository extends CrudRepository<DBFood, Integer> {
    DBFood findDBFoodById(int id);
    List<DBFood> findAll();

    /*
    TODO Sprint 2: figure out how to do this properly. This may be faster than
    doing the filtering manually, since food table can be indexed (which it isnt rn).
    @Query("SELECT f FROM food WHERE f.name = name")
    List<DBFood> getDBFoodsByName(@Param("name") String name);
    */
}
