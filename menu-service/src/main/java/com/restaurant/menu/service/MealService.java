package com.restaurant.menu.service;

import com.restaurant.menu.model.Meal;
import com.restaurant.menu.model.MealCategory;
import com.restaurant.menu.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository repo;

    public MealService(MealRepository repo) {
        this.repo = repo;
    }

    public Meal addMenuItem(Meal meal) {
        if (repo.existsByName(meal.getName())) {
            throw new IllegalArgumentException("Meal with name '" + meal.getName() + "' already exists");
        }

        if (meal.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        Meal item = new Meal();
        item.setName(meal.getName());
        item.setPrice(meal.getPrice());
        item.setMealCategory(meal.getMealCategory());
        item.setDescription(meal.getDescription());
        item.setGlutenFree(meal.isGlutenFree());
        item.setVegan(meal.isVegan());

        return repo.save(item); //save in DB
    }

    public List<Meal> getMealsByCategory(MealCategory mealCategory,Boolean isVegan, Boolean isGlutenFree){
        if (mealCategory != null){
            List<Meal> results = repo.findByMealCategory(mealCategory);
            if (isVegan != null){
                if(isVegan) {
                    results = results.stream().filter(Meal::isVegan
                    ).toList();
                }else{
                    results = results.stream().filter(meal -> !meal.isVegan()
                    ).toList();
                }
            }
            if (isGlutenFree != null){
                if(isGlutenFree) {
                    results = results.stream().filter(Meal::isGlutenFree
                    ).toList();
                }else{
                    results = results.stream().filter(meal -> !meal.isGlutenFree()
                    ).toList();
                }
            }

            return results;
        }
        return null;
    }

    public List<Meal> findByPriceBetween(double min,double max){
        if(min < 0 || max < 0){
            throw new IllegalArgumentException("Prices must be positive");
        }
        return repo.findByPriceBetween(min,max);
    }


}
