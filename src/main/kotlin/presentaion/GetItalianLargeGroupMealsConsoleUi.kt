package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase

class GetItalianLargeGroupMealsConsoleUi(
    private val useCase: GetItalianLargeGroupMealsUseCase
) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = { meals ->
                println("--- ITALIAN MEALS FOR LARGE GROUPS ---")
                meals.forEachIndexed { idx, meal ->
                    println("${idx + 1}. ${meal.mealName ?: "Unnamed"} (ID: ${meal.mealId})")
                    println("   Time: ${meal.minutesForPreparation} min | Tags: ${meal.tags.joinToString()}")
                    println("   Ingredients: ${meal.ingredients.joinToString()}\n")
                }
            },
            onFailure = { err ->
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
        )
    }
}