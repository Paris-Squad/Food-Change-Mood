package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase

class GetItalianLargeGroupMealsPresenter(
    private val useCase: GetItalianLargeGroupMealsUseCase
) {
    fun startItalianLargeGroupMeal() {
        useCase.invoke().fold(onSuccess = ::handleSuccess, onFailure = ::handleFailure)
    }

    private fun handleFailure(err: Throwable) {
        when (err) {
            is MealException.NoMealsFoundException -> {
                println("--- ITALIAN MEALS FOR LARGE GROUPS ---")
                println(err.message)
            }

            else -> {
                println("Unexpected error: ${err.message}")
            }
        }
    }

    private fun handleSuccess(meals: List<Meal>) {
        println("--- ITALIAN MEALS FOR LARGE GROUPS ---")
        meals.forEachIndexed { idx, meal ->
            println("${idx + 1}. ${meal.mealName ?: "Unnamed"} (ID: ${meal.mealId})")
            println("   Time: ${meal.minutesForPreparation} min | Tags: ${meal.tags.joinToString()}")
            println("   Ingredients: ${meal.ingredients.joinToString()}\n")
        }
    }
}