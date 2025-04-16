package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetEasyFoodSuggestionUseCase(private val repository: FoodRepository) {
    fun invoke(): Result<List<Food>> {
        val easyFood = repository.getFood().filter(::isEasyFood).shuffled().take(10)

        return if (easyFood.isEmpty()) {
            Result.failure(FoodException.NoEasyFoodFound("No easy food recipes found matching the criteria"))
        } else Result.success(easyFood)
    }

    private fun isEasyFood(food: Food): Boolean =
        food.minutes <= 30 && food.numberOfIngredients <= 5 && food.numberOfSteps <= 6
}