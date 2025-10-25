package com.restaurant.menu.repository;

import com.restaurant.menu.model.Meal;

import com.restaurant.menu.model.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MealRepository extends JpaRepository<Meal,Long> {

    List<Meal> findByMealCategory(MealCategory mealCategory);

    boolean existsByName(String name);

    List<Meal> findByPriceBetween(Double min, Double max);


}
