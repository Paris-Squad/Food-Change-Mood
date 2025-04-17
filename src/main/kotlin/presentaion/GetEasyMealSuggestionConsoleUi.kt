package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetEasyMealSuggestionUseCase

class GetEasyMealSuggestionConsoleUi(private val useCase: GetEasyMealSuggestionUseCase) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = {
                println("--- EASY MEAL SUGGESTIONS ---")
                it.forEachIndexed { index, meal ->
                    println("${index + 1}. ${meal.mealName ?: "Unnamed recipe"}")
                    println("   Time: ${meal.minutesForPreparation} minutes")
                    println("   Ingredients (${meal.numberOfIngredients}): ${meal.ingredients.joinToString(", ")}")
                    println("   Steps: ${meal.numberOfSteps} \n")
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
