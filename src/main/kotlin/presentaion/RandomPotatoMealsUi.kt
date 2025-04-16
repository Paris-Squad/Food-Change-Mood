package org.example.presentation

import org.example.domain.FoodException
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

class RandomPotatoMealsUi(private val getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase) {

    operator fun invoke() {
        try {
            val potatoMeals = getRandomPotatoMealsUseCase.getRandomPotatoMeals()

            println("========== RANDOM POTATO MEALS ==========\n")

            potatoMeals.forEachIndexed { index, meal ->
                println("Meal ${index + 1}: ${meal.name ?: "Unnamed Recipe"}")
                println("--------------------------------------------------")
                println("ID: ${meal.id}")
                println("Preparation Time: ${meal.minutes} minutes")
                println("Contributor ID: ${meal.contributorId}")
                println("Date Submitted: ${meal.submitted}")
                println("Tags: ${meal.tags.joinToString(", ").ifEmpty { "No tags available" }}")
                println("Nutrition Information: ${meal.nutrition}")
                println("Steps (${meal.numberOfSteps}):")
                meal.steps.forEachIndexed { stepIndex, step ->
                    println("   ${stepIndex + 1}. $step")
                }
                println("Description: ${meal.description ?: "No description available"}")
                println("Ingredients (${meal.numberOfIngredients}):")
                meal.ingredients.forEach { ingredient ->
                    println("   - $ingredient")
                }
                println("--------------------------------------------------\n")
            }

        } catch (e: FoodException.NoPotatoMealFound) {
            println("No meals containing potatoes were found.")
        }
    }
}
