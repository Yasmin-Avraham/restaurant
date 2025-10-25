package com.restaurant.menu;

import com.restaurant.menu.model.Meal;
import com.restaurant.menu.repository.MealRepository;
import com.restaurant.menu.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMeal_shouldThrowException_whenMealNameExists() {
        // given
        Meal meal = new Meal();
        meal.setName("Pasta");

        when(mealRepository.existsByName("Pasta")).thenReturn(true);

        // when + then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> mealService.addMenuItem(meal)
        );

        assertEquals("Meal with name 'Pasta' already exists", exception.getMessage());
        verify(mealRepository, never()).save(any(Meal.class));
    }
}
