package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GetEasyFoodSuggestionUseCase

class GetEasyFoodSuggestionConsoleUi(private val useCase: GetEasyFoodSuggestionUseCase) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = {
                println("--- EASY MEAL SUGGESTIONS ---")
                it.forEachIndexed { index, food ->
                    println("${index + 1}. ${food.name ?: "Unnamed recipe"}")
                    println("   Time: ${food.minutes} minutes")
                    println("   Ingredients (${food.numberOfIngredients}): ${food.ingredients.joinToString(", ")}")
                    println("   Steps: ${food.numberOfSteps} \n")
                }
            },
            onFailure = { error ->
                when (error) {
                    is FoodException.NoEasyFoodFound -> {
                        println("--- EASY MEAL SUGGESTIONS ---")
                        println("No easy meals found that match your criteria")
                    }

                    else -> {
                        println("--- UNEXPECTED ERROR ---")
                        println("An unexpected error occurred: ${error.message}")
                    }
                }
            }
        )
    }
}
