package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.FoodException
import org.example.domain.repository.MealRepository



class GetEasyFoodSuggestionUseCase(private val repository: MealRepository) {
    fun invoke(): Result<List<Meal>> {
        val easyFood = repository.getMeals().filter(::isEasyFood).shuffled().take(10)

        return if (easyFood.isEmpty()) {
            Result.failure(FoodException.NoEasyFoodFound("No easy food recipes found matching the criteria"))
        } else Result.success(easyFood)
    }

    private fun isEasyFood(food: Meal): Boolean =
        food.minutesForPreparation <= 30 && food.numberOfIngredients <= 5 && food.numberOfSteps <= 6
}