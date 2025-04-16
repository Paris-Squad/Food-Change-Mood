package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetEasyFoodSuggestionUC(private val repository: FoodRepository) {
    fun invoke(): List<Food> {
        val easyFood =  repository.getFood().filter { food ->
            isEasyFood(food)
        }.shuffled().take(10)

        if (easyFood.isEmpty()){
            throw FoodException.NoEasyFoodFound()
        }
        else return easyFood
    }

    private fun isEasyFood(food: Food): Boolean =
        food.minutes <= 30 && food.numberOfIngredients <= 5 && food.numberOfSteps <= 6
}