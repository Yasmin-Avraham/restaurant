package com.restaurant.menu.controller;

import com.restaurant.menu.model.Meal;
import com.restaurant.menu.model.MealCategory;
import com.restaurant.menu.service.MealService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MealController {


    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/meal")
    public Meal addNewMeal(@RequestBody Meal meal) {
        return mealService.addMenuItem(meal);
    }

    @GetMapping("/meals/category/{categoryName}")
    public List<Meal> getMealsByCategory(@PathVariable MealCategory categoryName, @RequestParam(required = false) Boolean isVegan, @RequestParam(required = false) Boolean isGlutenFree){
        return mealService.getMealsByCategory(categoryName,isVegan,isGlutenFree);
    }

    @GetMapping("/getMealsByPrices")
    public List<Meal> getMealsByPrices(@RequestParam(required = true) double minPrice, @RequestParam(required = true) double maxPrice){
        return mealService.findByPriceBetween(minPrice, maxPrice);
    }


}
