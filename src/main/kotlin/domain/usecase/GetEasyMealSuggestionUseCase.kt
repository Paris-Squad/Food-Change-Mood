package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetEasyMealSuggestionUseCase(private val mealRepository: MealRepository) {
    fun invoke(): Result<List<Meal>> {
        val easyMeals = mealRepository.getMeals().filter(::isEasyFood).shuffled().take(10)
        return if (easyMeals.isEmpty()) {
            Result.failure(MealException.NoMealsFoundException("No easy food recipes found matching the criteria"))
        } else Result.success(easyMeals)
    }

    private fun isEasyFood(food: Meal): Boolean =
        food.minutesForPreparation <= 30 && food.numberOfIngredients <= 5 && food.numberOfSteps <= 6
}