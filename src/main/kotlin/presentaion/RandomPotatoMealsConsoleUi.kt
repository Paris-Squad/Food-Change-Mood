package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

class RandomPotatoMealsConsoleUi(private val getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase) {

    operator fun invoke() {
        try {
            val potatoMeals = getRandomPotatoMealsUseCase.getRandomPotatoMeals()

            println("========== RANDOM POTATO MEALS ==========\n")

            potatoMeals.forEachIndexed { index, meal ->
                println("Meal ${index + 1}: ${meal.mealName ?: "Unnamed Recipe"}")
                println("--------------------------------------------------")
                println("ID: ${meal.mealId}")
                println("Preparation Time: ${meal.minutesForPreparation} minutes")
                println("Contributor ID: ${meal.contributorId}")
                println("Date Submitted: ${meal.submittedDate}")
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

        } catch (e: MealException.NoPotatoMealFound) {
            println("No meals containing potatoes were found.")
        }
    }
}
