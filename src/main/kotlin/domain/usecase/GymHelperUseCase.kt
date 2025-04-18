package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import domain.model.Meal
import org.example.utils.inRange

class GymHelperUseCase(
    private val mealRepository: MealRepository
) {
    fun execute(calories: Float, protein: Float): List<Meal> {
        val caloriesRange = (calories - 3).coerceAtLeast(0f)..(calories + 3)
        val proteinRange = (protein - 3).coerceAtLeast(0f)..(protein + 3)

        return mealRepository.getMeals().filter { meal ->
            meal.nutrition.calories.inRange(caloriesRange) &&
                    meal.nutrition.protein.inRange(proteinRange)
        }
    }
}