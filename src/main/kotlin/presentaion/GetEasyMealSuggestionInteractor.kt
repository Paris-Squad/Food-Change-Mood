package org.example.presentaion

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.utils.formatDetails

class GetEasyMealSuggestionInteractor(private val useCase: GetEasyMealSuggestionUseCase) {
    fun invoke() {
        useCase.invoke().fold(onSuccess = ::handleSuccess, onFailure = ::handleFailure)
    }

    private fun handleSuccess(meals: List<Meal>) {
        println("--- EASY MEAL SUGGESTIONS ---")
        meals.forEachIndexed { index, meal ->
            println(meal.formatDetails())
        }
    }

    private fun handleFailure(error: Throwable) {
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
}
