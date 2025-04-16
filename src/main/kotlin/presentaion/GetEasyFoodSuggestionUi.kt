package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GetEasyFoodSuggestionUC


class GetEasyFoodSuggestionUi(private val useCase: GetEasyFoodSuggestionUC) {
    fun invoke() {
        try {
            val easyFoods = useCase.invoke()
            println("--- EASY MEAL SUGGESTIONS ---")
            easyFoods.forEachIndexed { index, food ->
                println("${index + 1}. ${food.name ?: "Unnamed recipe"}")
                println("   Time: ${food.minutes} minutes")
                println("   Ingredients (${food.numberOfIngredients}): ${food.ingredients.joinToString(", ")}")
                println("   Steps: ${food.numberOfSteps} \n")
            }
        } catch (e: FoodException.NoEasyFoodFound) {
            println("--- EASY MEAL SUGGESTIONS ---")
            println("No easy meals found that match your criteria")
        } catch (e: Exception) {
            println("--- UNEXPECTED ERROR ---")
            println("An unexpected error occurred: ${e.message}")
        }
    }
}