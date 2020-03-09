package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.service.MealService;
import com.ecse428.project.repository.*;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MealServiceTests {
    
    @Autowired
	private MealService mealService;
    
    @MockBean
	private MealRepository mockRepository;
    
    @Test
	public void testAddMealSuccess(){
		Meal tMeal = new Meal(MealType.Breakfast);
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMeal);
		boolean addStatus = mealService.addNewMeal(tMeal);
		assertTrue(addStatus);
	}
    
    @Test
	public void testAddMealFailure(){
		Meal tMeal = new Meal(MealType.Breakfast);
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMeal);
		boolean addStatus = mealService.addNewMeal(tMeal);
		assertTrue(!addStatus);
    }
    
    @Test
	public void testGetAllMeals(){
        Meal tmeal = new Meal(MealType.Breakfast);
        List<Meal> meals = new ArrayList<>();
        meals.add(tmeal);
		when(mockRepository.findAll()).thenReturn(meals);
        List<Meal> results = new ArrayList<>();
        for(Meal m : mealService.getAllMeals()){
            results.add(m);
        }
		assertTrue(!results.isEmpty());
    }
    
    @Test
	public void testGetMeals(){
        Meal tMeal = new Meal(MealType.Breakfast);
        when(mockRepository.findMealById(anyInt())).thenReturn(tMeal);
		assertTrue(tMeal.getMealType().equals(mealService.getMeal(tMeal.getId()).getMealType()));
    }
    
    @Test
	public void testUpdateMealSuccess(){
        Meal tMeal = new Meal(MealType.Breakfast);
		when(mockRepository.existsById(anyInt())).thenReturn(true);
		when(mockRepository.save(any())).thenReturn(tMeal);
		boolean addStatus = mealService.updateMeal(tMeal);
		assertTrue(addStatus);
	}
    
    @Test
	public void testUpdaeMealFailure(){
        Meal tMeal = new Meal(MealType.Breakfast);
		when(mockRepository.existsById(anyInt())).thenReturn(false);
		when(mockRepository.save(any())).thenReturn(tMeal);
		boolean addStatus = mealService.updateMeal(tMeal);
		assertTrue(!addStatus);
    }

}