package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetEasyMealSuggestionUseCase(private val repository: MealRepository) {
    fun invoke(): Result<List<Meal>> {
        val easyFood = repository.getMeals().filter(::isEasyFood).shuffled().take(10)

        return if (easyFood.isEmpty()) {
            Result.failure(MealException.NoEasyMealsFound("No easy food recipes found matching the criteria"))
        } else Result.success(easyFood)
    }

    private fun isEasyFood(food: Meal): Boolean =
        food.minutesForPreparation <= 30 && food.numberOfIngredients <= 5 && food.numberOfSteps <= 6
}