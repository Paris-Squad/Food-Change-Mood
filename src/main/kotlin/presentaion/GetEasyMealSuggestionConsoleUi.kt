package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetEasyMealSuggestionUseCase

class GetEasyMealSuggestionConsoleUi(private val useCase: GetEasyMealSuggestionUseCase) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = {
                println("--- EASY MEAL SUGGESTIONS ---")
                it.forEachIndexed { index, meal ->
                    println(meal.formatDetails())
                }
            },
            onFailure = { error ->
                when (error) {
                    is MealException.NoMealsFoundException -> {
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
