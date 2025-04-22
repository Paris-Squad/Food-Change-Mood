package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import domain.model.Meal
import org.example.domain.MealException

class GetQuickHealthyPicksUseCase(private val mealRepository: MealRepository) {
    private fun getAllMeals(): List<Meal> = mealRepository.getMeals()

    operator fun invoke(): Result<List<Meal>> {
        if (getAllMeals().isEmpty()) return Result.failure(MealException.NoMealsFoundException())

        val result = getAllMeals()
            .filter(::isValidQuickMeal)
            .sortedBy { it.nutrition.totalFat!! + it.nutrition.saturatedFat!! + it.nutrition.carbohydrates!! }
        return Result.success(result)
    }

    private fun isValidQuickMeal(meal: Meal): Boolean {
        return meal.minutesForPreparation <= 15
                && meal.mealName != null
                && meal.nutrition.totalFat != null
                && meal.nutrition.saturatedFat != null
                && meal.nutrition.carbohydrates != null
    }

}