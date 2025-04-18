package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase

class GetItalianLargeGroupMealsUi(
    private val useCase: GetItalianLargeGroupMealsUseCase
) {
    fun invoke() {
        useCase.invoke().fold(
            onSuccess = { meals ->
                println("--- ITALIAN MEALS FOR LARGE GROUPS ---")
                meals.forEachIndexed { idx, meal ->
                    println("${idx + 1}. ${meal.name ?: "Unnamed"} (ID: ${meal.id})")
                    println("   Time: ${meal.minutes} min | Tags: ${meal.tags.joinToString()}")
                    println("   Ingredients: ${meal.ingredients.joinToString()}\n")
                }
            },
            onFailure = { err ->
                when (err) {
                    is FoodException.NoItalianLargeGroupMealFound -> {
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