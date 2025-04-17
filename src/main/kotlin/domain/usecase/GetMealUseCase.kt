package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetMealUseCase(private val repository : MealRepository) {
    fun invoke():List<Meal> = repository.getMeals()
}