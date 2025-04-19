package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetMealUseCase(private val mealRepository : MealRepository) {
    fun invoke():List<Meal> = mealRepository.getMeals()
}