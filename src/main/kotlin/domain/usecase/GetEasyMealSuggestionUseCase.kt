package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetEasyMealSuggestionUseCase(private val repository: MealRepository) {
    fun invoke(): Result<List<Meal>> {
        val easyMeals = repository.getMeals().filter(::isEasyMeal).shuffled().take(10)

        return if (easyMeals.isEmpty()) {
            Result.failure(MealException.NoEasyMealsFound("No easy meal recipes found matching the criteria"))
        } else Result.success(easyMeals)
    }

    private fun isEasyMeal(meal: Meal): Boolean =
        meal.minutesForPreparation <= 30 && meal.numberOfIngredients <= 5 && meal.numberOfSteps <= 6
}