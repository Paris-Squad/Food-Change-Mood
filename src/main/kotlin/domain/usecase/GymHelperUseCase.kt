package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import domain.model.Meal
import org.example.domain.MealException
import org.example.utils.inRange

class GymHelperUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(calories: Float, protein: Float): Result<List<Meal>> {
        val caloriesRange = (calories - 3).coerceAtLeast(0f)..(calories + 3)
        val proteinRange = (protein - 3).coerceAtLeast(0f)..(protein + 3)

        val filteredMeals = mealRepository.getMeals().filter { meal ->
            meal.nutrition.calories.inRange(caloriesRange) &&
                    meal.nutrition.protein.inRange(proteinRange)
        }

        if (filteredMeals.isEmpty()) return Result.failure(MealException.NoMealsFoundException("No meals found"))

        return Result.success(filteredMeals)
    }
}