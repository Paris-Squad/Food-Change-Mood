package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetEasyMealSuggestionUseCase

class GetEasyMealSuggestionConsoleUi(private val useCase: GetEasyMealSuggestionUseCase) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = {
                println("--- EASY MEAL SUGGESTIONS ---")
                it.forEachIndexed { index, food ->
                    println("${index + 1}. ${food.mealName ?: "Unnamed recipe"}")
                    println("   Time: ${food.minutesForPreparation} minutes")
                    println("   Ingredients (${food.numberOfIngredients}): ${food.ingredients.joinToString(", ")}")
                    println("   Steps: ${food.numberOfSteps} \n")
                }
            },
            onFailure = { error ->
                when (error) {
                    is MealException.NoEasyMealsFound -> {
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
