package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import domain.model.Meal
import org.example.domain.MealException

class GetQuickHealthyPicksUseCase(private val mealRepository: MealRepository) {

    operator fun invoke(): Result<List<Meal>> {
        val allMeals = mealRepository.getMeals()

        val result = allMeals
            .filter(::isValidQuickMeal)
            .sortedBy { (it.nutrition.totalFat ?: 0f) + (it.nutrition.saturatedFat ?: 0f) + (it.nutrition.carbohydrates ?: 0f) }

        if (result.isEmpty()) return Result.failure(MealException.NoMealsFoundException())

        return Result.success(result)
    }

    private fun isValidQuickMeal(meal: Meal): Boolean {
        return meal.minutesForPreparation <= PREPARATION_MINUTES
                && meal.mealName != null
                && meal.nutrition.totalFat != null
                && meal.nutrition.saturatedFat != null
                && meal.nutrition.carbohydrates != null
    }

    companion object {
        const val PREPARATION_MINUTES = 15
    }
}